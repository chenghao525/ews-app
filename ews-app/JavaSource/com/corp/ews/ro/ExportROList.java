package com.corp.ews.ro;

import com.corp.ews.EWSConstant;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.Excel;
import com.corp.oa.util.G;
import com.corp.oa.util.Tuple;
import com.corp.servlet.AjaxInterface;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class ExportROList implements AjaxInterface {

    protected final Log logger = LogFactory.getLog(getClass());
    RepairOrdersUtils roUtl = new RepairOrdersUtils();
    @Override
    public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm) throws Exception {

        if (!G.isOwnRight("13101202", request)) {
           response.getWriter().write("没有权限或此次会话已失效");
           return;
        }
        String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);
        if (G.isNvl(key)) {
            response.getWriter().write("");
            return;
        }
        String fldList = request.getParameter("FLD_LIST");
        String[] fc = new String[] {"EWS工单号", "工单号", "车牌号", "色号", "车身颜色", "色母消耗(g)", "车辆品牌", "钣面数", "调漆员", "技师", "工单状态", "调色时间"};
        String[] fe = new String[] {"EWS_RO_CODE", "RO_NUMBER", "VEHICLE_LICENSE", "COLOR_CODE", "COLOR_NAME", "WEIGHT_TOTAL", "CAR_BRAND",
                "SHEET_METAL_COUNT", "PAINT_MAKER", "MECHANIC_NAME", "REWORK_TYPE_NAME", "MIXED_DATE"};

        String names = "", fields = "";
        fldList = G.commaBE(fldList);
        for (int i = 0; i < fe.length; i++) {
            if (fldList.indexOf(G.commaBE(fe[i])) >= 0) {// 报表导出字段
                names += fc[i] + ",";
                fields += fe[i] + ",";
            }
        }
        if (G.isNvl(names)) {
            logger.info("[RO导出]：未找到任何需要导出的值");
            response.getWriter().write("找不到匹配字段");
            return;
        }
        JSONObject json = new JSONObject(key);
        String provSid = G.getEWSProvSidFromSession(request);
        CarUtil cUtl = new CarUtil();

        Tuple tuple = roUtl.getROListSql(roUtl.getROSql(), provSid, new DataObject(json));
        String sql = "select tero.*, f_getmapcontent(tero.rework_type) as rework_type_name from (" + tuple.getFirst() + ") tero";
        Iterator<DataObject> it = dm.find(sql, tuple.getParameter()).iterator();

        Collection<DataObject> col = new ArrayList<DataObject>();
        DataObject dob = null;
        DataObject carDob = null;

        String brandId = null;
        String mixedDate = null;
        String[] mixedDtArray = null;

        // 添加的动态计算字段
        ArrayList<String> fieldList = new ArrayList<String>();
        ArrayList<String> nameList = new ArrayList<String>();

        while (it.hasNext()) {
            dob = it.next();
            brandId = dob.getString("CAR_BRAND");
            mixedDate = dob.getString("MIXED_DATE");
            // 查找车辆信息
            carDob = cUtl.getCarBrandById(brandId, dm);
            if (carDob != null) {
                dob.setString("CAR_BRAND", carDob.getString("NODE_TEXT"));
            }
            // 将调色时间进行分割
            if (!G.isNvl(mixedDate)) {
                mixedDtArray = mixedDate.split(" ");
                dob.setString("MIXED_DATE", mixedDtArray[0]);
            }
            // 钣面详情
            sheetMetalSurfaceDeal(dob, fieldList, nameList, dm);

            col.add(dob);
        }
        // 补充导出的导出字段
        for (int i= 0; i < fieldList.size(); i++) {
            names  += nameList.get(i) + ",";
            fields += fieldList.get(i) + ",";
        }

        Excel excel = new Excel();

        excel.setTitle(names);
        excel.setField(fields);

        excel.setMaskField(null);
        excel.setMaskLength(0);

        excel.makeExcel(col);
        excel.pushFileToDownload(response);
    }

    // 钣面数的添加
    private void sheetMetalSurfaceDeal(DataObject dob, ArrayList<String> fieldList, ArrayList<String> nameList,
                                       DataManager dm) throws Exception {

        Iterator<DataObject> shfIt = roUtl.roSheetMetalSurfaceByRoSid(dob.getString("SID"), dm);

        DataObject shfDob = null;
        String shfId = null;
        String shfName = null;
        String shfNumber = null;
        String shfDescription = null;

        String field = null;
        String name = null;
        String field1 = null;
        String name1 = null;

        int idx = 0;
        while (shfIt.hasNext()) {
            shfDob = shfIt.next();

            shfId           = shfDob.getString("SHF_ID");
            shfName         = shfDob.getString("SHF_NAME");
            shfNumber       = shfDob.getString("SHF_NUMBER");
            shfDescription  = shfDob.getString("SHF_DESCRIPTION");
            idx = getLocationByShfId(shfId);
            if (idx == 0) {
                logger.info("[钣面]:id未找到");
                continue;
            }
            field = "SHF_NAME_" + idx;
            name  = "钣面" + idx;
            field1 = "SHF_COUNT_" + idx;
            name1  = "钣面数" + idx;
            // 动态组合导出字段
            if (!fieldList.contains(field)) {

                fieldList.add(field);
                nameList.add(name);

                fieldList.add(field1);
                nameList.add(name1);
            }
            // 自定义的钣面
            if (idx > 15) {
                dob.setString(field, shfDescription);
            } else {
                dob.setString(field, shfName);
            }
            dob.setString(field1, shfNumber);
        }
    }
    // 钣面数对应位置
    private int getLocationByShfId(String shfId) {

        if (EWSConstant.LEFT_FRONT_BUMPER.equals(shfId)) {
            return 1;
        } else if (EWSConstant.LEFT_FRONT_DOOR.equals(shfId)) {
            return 2;
        } else if (EWSConstant.LEFT_REAR_DOOR.equals(shfId)) {
            return 3;
        } else if (EWSConstant.LEFT_FRONT_FENDER.equals(shfId)) {
            return 4;
        } else if (EWSConstant.LEFT_REAR_BUMPER.equals(shfId)) {
            return 5;
        } else if (EWSConstant.LEFT_REAR_FENDER.equals(shfId)) {
            return 6;
        } else if (EWSConstant.RIGHT_FRONT_BUMPER.equals(shfId)) {
            return 7;
        } else if (EWSConstant.RIGHT_REAR_BUMPER.equals(shfId)) {
            return 8;
        } else if (EWSConstant.RIGHT_FRONT_DOOR.equals(shfId)) {
            return 9;
        } else if (EWSConstant.RIGHT_REAR_DOOR.equals(shfId)) {
            return 10;
        } else if (EWSConstant.RIGHT_FRONT_FENDER.equals(shfId)) {
            return 11;
        } else if (EWSConstant.RIGHT_REAR_FENDER.equals(shfId)) {
            return 12;
        } else if (EWSConstant.HOOD.equals(shfId)) {
            return 13;
        } else if (EWSConstant.TRUNK.equals(shfId)) {
            return 14;
        } else if (EWSConstant.ROOF.equals(shfId)) {
            return 15;
        } else if (shfId.indexOf("custom") >= 0) {
            String idxStr = shfId.substring("custom".length(), shfId.length());
            int idx = Integer.parseInt(idxStr);
            return 15 + idx;
        } else {
            return 0;
        }
    }
}
