package com.corp.ews.report;

import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.oa.util.Tuple;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class BungleRptUtil {

    protected final Log logger = LogFactory.getLog(getClass());


    /**
     * 按技师分类
     * @param isPaint 是否为调漆技师
     * @param searchDob
     * @param dm
     * @return
     * @throws Exception
     */
    public Iterator<DataObject> rptBungleOfMechanics(String provSid, boolean isPaint, DataObject searchDob,
                                                     DataManager dm) throws Exception {
        Tuple tuple = bungleRptSql(provSid,isPaint, searchDob);

        return dm.find(tuple.getFirst(), tuple.getParameter()).iterator();

    }

    /**
     * 统计工单数量和钣面数
     * @param searchDob
     * @param dm
     * @return
     * @throws Exception
     */
    public DataObject rptTotal(String provSid, DataObject searchDob,
                               DataManager dm) throws Exception {
        Tuple tuple = totalOfAll(provSid, searchDob);

        return dm.findByPrimaryKey(tuple.getFirst(), tuple.getParameter());
    }

    /**
     * 查找每个月的月均消耗
     * @param provSid
     * @param rptMonth
     * @param searchDob
     * @param dm
     * @return
     * @throws Exception
     */
    public DataObject rptOfCost(String provSid, String rptMonth,
                                DataObject searchDob, DataManager dm) throws Exception {
        String sql = avgOfSheetMetalSurface(provSid, rptMonth, searchDob);

        return dm.findByPrimaryKey(sql, null);
    }
    public Tuple bungleRptSql(String provSid, boolean isPaint, DataObject searchDob) {

        String columnName = "MECHANIC_ID";
        if (isPaint) {
            columnName = "PAINT_MAKER_ID";
        }

        String sql = "SELECT ro." + columnName + "," +
                "(SELECT MECHANIC_NAME FROM T_EWS_INFO_MECHANICS WHERE STATE='1'" +
                " AND SID=ro." + columnName + ") AS mechanic_name," +
                " SUM(ro.SHEET_METAL_COUNT) AS shf_count, COUNT(*) AS ro_count" +
                " FROM T_EWS_REPAIR_ORDERS ro WHERE ro.STATE='1' AND ro.repair_state='yes'";

        Tuple tuple = bungleBaseSql(sql, provSid, searchDob);

        sql = tuple.getFirst() + " GROUP BY ro." + columnName;

        return new Tuple(sql, tuple.second);
    }

    public Tuple totalOfAll(String provSid, DataObject searchDob) {

        String sql = "SELECT SUM(SHEET_METAL_COUNT) AS shf_total, COUNT(*) AS total" +
                " FROM T_EWS_REPAIR_ORDERS WHERE  STATE='1' AND repair_state='yes'";
        Tuple tuple = bungleBaseSql(sql, provSid, searchDob);

        return tuple;

    }
    public String avgOfSheetMetalSurface(String provSid, String rptMonth, DataObject searchDob) {
        String sql = "SELECT * FROM T_EWS_RPT_COST_MONTHLY WHERE PROV_SID=? AND RPT_MONTH=?";

        return G.formatSql(sql, new String[] { provSid, rptMonth });
    }

    public Tuple bungleBaseSql(String rootSql, String provSid, DataObject searchDob) {

        String sql = "";

        String paintId = searchDob.getString("S_PAINT_ID");
        String mechanicId = searchDob.getString("S_MECHANIC_ID");
        String provId = searchDob.getString("S_PROV_SID");

        String mixedDateSt = searchDob.getString("S_MIXED_DATE_ST");
        String mixedDateEd = searchDob.getString("S_MIXED_DATE_ED");
        String crtTimeSt = searchDob.getString("S_CRT_TIME_ST");
        String crtTimeEd = searchDob.getString("S_CRT_TIME_ED");
        ArrayList pl = new ArrayList<String>();

        if (!G.isNvl(provSid) || !G.isNvl(provId)) {
            sql += " and prov_sid=?";
            pl.add(G.isNvl(provSid) ? provId : provSid);
        }
        if (!G.isNvl(paintId)) {
            sql += " AND PAINT_MARKER_ID=?";
            pl.add(G.rqm(paintId));
        }
        if (!G.isNvl(mechanicId)) {
            sql += " AND MECHANIC_ID=?";
            pl.add(G.rqm(mechanicId));
        }
        if (!G.isNvl(mixedDateSt)) {
            sql += " AND MIXED_DATE >= ?";
            pl.add(G.rqm(mixedDateSt));
        }
        if (!G.isNvl(mixedDateEd)) {
            sql += " AND MIXED_DATE <= ?";
            pl.add(G.rqm(mixedDateEd));
        }
        if (!G.isNvl(crtTimeSt)) {
            sql += " AND CRT_TIME >=?";
            pl.add(G.rqm(crtTimeSt));
        }
        if (!G.isNvl(crtTimeEd)) {
            sql += " AND CRT_TIME <= ?";
            pl.add(G.rqm(crtTimeEd));
        }
        return new Tuple(rootSql + sql, pl);
    }

    public String getPreMonth(String currDt) {

        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.YEAR, Integer.parseInt(currDt.split("-")[0]));
        cale.set(Calendar.MONTH, Integer.parseInt(currDt.split("-")[1]) - 1);
        cale.add(Calendar.MONTH, -1);
        String preMonth = dft.format(cale.getTime());
        return preMonth;

    }
    public String dayOfTheMonth(String dtStr, boolean isLastDay) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt = df.parse(dtStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dt);
            if (isLastDay) {
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            } else {
                cal.set(Calendar.DAY_OF_MONTH, 1);
            }

            return df.format(cal.getTime());
        } catch (Exception e) {
            logger.error("解析字符串异常：" + e);
        }
        return dtStr;
    }
    public int getCurrentDayOfTheMonth(String month) {

        SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
        String curDate = G.getCurrDt("yyyy-MM");

        SimpleDateFormat fd1 = new SimpleDateFormat("dd");
        Date dt = null;
        try {
            //当月
            if (month.equals(curDate)) {
                curDate = G.getCurrentDate();
            } else  {
                Calendar cale = Calendar.getInstance();
                cale.set(Calendar.YEAR, Integer.parseInt(month.split("-")[0]));
                cale.set(Calendar.MONTH, Integer.parseInt(month.split("-")[1]) - 1);
                cale.add(Calendar.MONTH, 1);
                cale.set(Calendar.DAY_OF_MONTH, 0);
                curDate = fd.format(cale.getTime());
            }
            dt = fd.parse(curDate);
        } catch (Exception e) {
            dt = new Date();
        }

        return Integer.parseInt(fd1.format(dt));
    }
}
