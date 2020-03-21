package com.corp.ews.report;

import com.corp.frame.bean.JSONArray;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.oa.util.Tuple;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by tanjie on 2018/8/9.
 */
public class ReportUtils {
    public Iterator<DataObject> getRptType(DataObject dob, DataManager dm) throws Exception {

        Tuple tuple = formatRptTypeSql(dob);

        Collection<?> coll = dm.find(tuple.getFirst(), tuple.getParameter());

        return (Iterator<DataObject>)coll.iterator();
    }
    public Iterator<DataObject> getRptTableType(DataObject dob, DataManager dm) throws Exception {

        Tuple tuple = formatRptTableSql(dob);

        Collection<?> coll = dm.find(tuple.getFirst(), tuple.getParameter());

        return (Iterator<DataObject>)coll.iterator();
    }
    public Iterator<DataObject> getRptTableYearType(DataObject dob, DataManager dm) throws Exception {

        Tuple tuple = formatRptTableYearSql(dob);

        Collection<?> coll = dm.find(tuple.getFirst(), tuple.getParameter());

        return (Iterator<DataObject>)coll.iterator();
    }



    private Tuple formatRptTypeSql(DataObject dob) {
        String crtTimeSt = dob.getString("S_CREATE_TIME_ST");
        String crtTimeEd = dob.getString("S_CREATE_TIME_ED");
        String prov_sid = dob.getString("PROV_SID");
        String paint_maker_id = dob.getString("PAINT_MAKER_ID");
        String mechanic_id = dob.getString("MECHANIC_ID");

        String sql = "SELECT REWORK_TYPE, SUM(SHEET_METAL_COUNT) AS shf_count FROM T_EWS_REPAIR_ORDERS WHERE STATE='1'";
        ArrayList<String> pl = new ArrayList<String>();
        if (!G.isNvl(prov_sid)) {
            sql += " and PROV_SID=?";
            pl.add(prov_sid);
        }
        if (!G.isNvl(crtTimeSt)) {
            sql += " and MIXED_DATE>=?";
            pl.add(crtTimeSt);
        }
        if (!G.isNvl(crtTimeEd)) {
            sql += " and MIXED_DATE<=?";
            pl.add(crtTimeEd);
        }
        if (!G.isNvl(paint_maker_id)) {
            sql += " and PAINT_MAKER_ID=?";
            pl.add(paint_maker_id);
        }
        if (!G.isNvl(mechanic_id)) {
            sql += " and MECHANIC_ID=?";
            pl.add(mechanic_id);
        }
        sql += " GROUP BY REWORK_TYPE";
        return new Tuple(sql, pl);
    }

    private Tuple formatRptTableSql(DataObject dob) {
        String crtTimeSt = dob.getString("S_CREATE_TIME_ST");
        String crtTimeEd = dob.getString("S_CREATE_TIME_ED");
        String prov_sid = dob.getString("PROV_SID");
        String paint_maker_id = dob.getString("PAINT_MAKER_ID");
        String mechanic_id = dob.getString("MECHANIC_ID");

        String sql = "SELECT SUBSTR(MIXED_DATE, 0, 10) AS mixed_date, REWORK_TYPE," +
                " SUM(SHEET_METAL_COUNT) AS shf_count FROM T_EWS_REPAIR_ORDERS" +
                " WHERE STATE='1'";
        ArrayList<String> pl = new ArrayList<String>();
        if (!G.isNvl(prov_sid)) {
            sql += " and PROV_SID=?";
            pl.add(prov_sid);
        }
        if (!G.isNvl(crtTimeSt)) {
            sql += " and MIXED_DATE>=?";
            pl.add(crtTimeSt);
        }
        if (!G.isNvl(crtTimeEd)) {
            sql += " and MIXED_DATE<=?";
            pl.add(crtTimeEd);
        }
        if (!G.isNvl(paint_maker_id)) {
            sql += " and PAINT_MAKER_ID=?";
            pl.add(paint_maker_id);
        }
        if (!G.isNvl(mechanic_id)) {
            sql += " and MECHANIC_ID=?";
            pl.add(mechanic_id);
        }
        sql += " GROUP BY SUBSTR(MIXED_DATE, 0, 10), REWORK_TYPE ORDER BY MIXED_DATE";
        return new Tuple(sql, pl);
    }

    private Tuple formatRptTableYearSql(DataObject dob) {
        String crtTimeSt = dob.getString("S_CREATE_TIME_ST");
        String crtTimeEd = dob.getString("S_CREATE_TIME_ED");
        String prov_sid = dob.getString("PROV_SID");
        String paint_maker_id = dob.getString("PAINT_MAKER_ID");
        String mechanic_id = dob.getString("MECHANIC_ID");

        String sql = "SELECT SUBSTR(MIXED_DATE, 0, 7) AS mixed_date, REWORK_TYPE," +
                " SUM(SHEET_METAL_COUNT) AS shf_count FROM T_EWS_REPAIR_ORDERS" +
                " WHERE STATE='1'";
        ArrayList<String> pl = new ArrayList<String>();
        if (!G.isNvl(prov_sid)) {
            sql += " and PROV_SID=?";
            pl.add(prov_sid);
        }
        if (!G.isNvl(crtTimeSt)) {
            sql += " and MIXED_DATE>=?";
            pl.add(crtTimeSt);
        }
        if (!G.isNvl(crtTimeEd)) {
            sql += " and MIXED_DATE<?";
            pl.add(crtTimeEd);
        }
        if (!G.isNvl(paint_maker_id)) {
            sql += " and PAINT_MAKER_ID=?";
            pl.add(paint_maker_id);
        }
        if (!G.isNvl(mechanic_id)) {
            sql += " and MECHANIC_ID=?";
            pl.add(mechanic_id);
        }
        sql += " GROUP BY SUBSTR(MIXED_DATE, 0, 7), REWORK_TYPE ORDER BY MIXED_DATE";
        return new Tuple(sql, pl);
    }

    public String getPreMonth(String currDt) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.YEAR, Integer.parseInt(currDt.split("-")[0]));
        cale.set(Calendar.MONTH, Integer.parseInt(currDt.split("-")[1]));
        String preMonth = dft.format(cale.getTime());
        return preMonth;

    }

    //天
    public JSONArray currentDaysOfMonth(String month) {
        int currentDay = getCurrentDayOfTheMonth(month);

        JSONArray days = new JSONArray();
        for (int i = 1; i <= currentDay; i++) {
            days.put(i < 10 ? "0" + i : i + "");
        }
        return days;
    }

    //月份
    public JSONArray getMonths() {
        JSONArray months = new JSONArray();

        for (int i = 1; i <= 12; i++) {
            months.put(i < 10 ? ("0" + i) : "" + i);
        }

        return months;
    }

    public int getCurrentDayOfTheMonth(String month) {

        SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
        String curDate = G.getCurrDt("yyyy-MM");

        SimpleDateFormat fd1 = new SimpleDateFormat("dd");
        Date dt = null;
        try {
            //当月
            if (month.equals(curDate)) {
                //curDate = G.getCurrentDate();
                Date dNow = new Date(); //当前时间
                Calendar calendar = Calendar.getInstance(); //得到日历
                calendar.setTime(dNow);//把当前时间赋给日历
                calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
                curDate = fd.format(calendar.getTime());   //得到前一天的时间
            } else  {
                Calendar cale = Calendar.getInstance();
                cale.set(Calendar.YEAR, Integer.parseInt(month.split("-")[0]));
                cale.set(Calendar.MONTH, Integer.parseInt(month.split("-")[1]));
//               cale.add(Calendar.MONTH, 1);
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
