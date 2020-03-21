package com.corp.ews.report;

import com.corp.ews.EWSConstant;
import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

public class BungleTotalRpt implements AjaxInterface {

    @Override
    public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm) throws Exception {

        if (!G.isOwnRight("13801000", request)) {
            response.getWriter().write("会话已失效或无权限");
            return;
        }

        String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);
        JSONObject json = new JSONObject(key);

        BungleRptUtil brptUtl = new BungleRptUtil();

        String mixedDt = json.getString("S_MIXED_DATE");

        String startDt = brptUtl.dayOfTheMonth(mixedDt + "-03", false);
        String endDt = brptUtl.dayOfTheMonth(mixedDt + "-03", true);
        String provSid = G.getEWSProvSidFromSession(request);


        json.put("S_MIXED_DATE_ED", endDt);
        json.put("S_MIXED_DATE_ST", startDt);

        DataObject searchDob = new DataObject(json);


        String[] inc = new String[] { "MECHANIC_NAME", "SHF_COUNT", "RO_COUNT"};
        // 总数量
        DataObject totalDob = brptUtl.rptTotal(provSid, searchDob, dm);

        JSONArray ja = new JSONArray();

        int days = brptUtl.getCurrentDayOfTheMonth(mixedDt);

        // 调漆之外的技师
        Iterator<DataObject> it = brptUtl.rptBungleOfMechanics(provSid, false, searchDob, dm);

        DataObject dob = null;

        while (it.hasNext()) {
            dob = it.next();
            ja.put(jsonFromDob(days, dob, inc));
        }
//        // 调漆技师
//        it = brptUtl.rptBungleOfMechanics(true, searchDob, dm);
//        while (it.hasNext()) {
//            dob = it.next();
//            ja.put(jsonFromDob(days, dob, inc));
//        }
        // 获取花费
        DataObject costDob = brptUtl.rptOfCost(provSid, mixedDt, searchDob, dm);

        JSONObject rs = totalDob.toJson();
        rs.put("dtl", ja);
        if (costDob != null) {
            rs.put("cost", costDob.toJson());
        }

        response.getWriter().write(EWSConstant.returnMessage(0, "ok", rs));
    }

    private JSONObject jsonFromDob(int days, DataObject dob, String[] inc)  {

        double shfCount = Double.parseDouble(dob.getString("SHF_COUNT"));

        JSONObject json = dob.toJsonByArray(inc);

        json.put("AVG_VAL", String.format("%.2f", shfCount / days ));

        return json;
    }
}
