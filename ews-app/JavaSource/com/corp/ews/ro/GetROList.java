package com.corp.ews.ro;

import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * wqj
 */
public class GetROList implements AjaxInterface {

    @Override
    public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm) throws Exception {

//        if (!G.isOwnRight("", request)) {
//            response.getWriter().write("会话已失效或无权限");
//            return;
//        }
        String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);
        JSONObject json = new JSONObject(key);

        String[] inc = new String[] { "SID", "PPG_BRAND_CODE", "PAINT_MAKER", "PAINT_MAKER_ID",
                "MECHANIC_NAME", "MECHANIC_ID", "MECHANIC_WORK", "CAR_BRAND", "SHF_NUMBER", "VEHICLE_LICENSE", "RO_NUMBER",
                "EWS_RO_CODE", "CRT_TIME", "PAINT_MAKER", "PAINT_MAKER_ID",
                "ORIGINAL_RO_NUMBER", "SHEET_METAL_COUNT", "WEIGHT_TOTAL" };
        String provSid = G.getEWSProvSidFromSession(request);
        RepairOrdersUtils roUtl = new RepairOrdersUtils();
        JSONArray ja = roUtl.getROList(provSid, new DataObject(json), inc, dm);


        response.getWriter().write(ja.toString());
    }
}
