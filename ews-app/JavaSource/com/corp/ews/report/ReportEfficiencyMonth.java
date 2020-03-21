package com.corp.ews.report;

import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

/**
 * Created by tanjie on 2018/8/9.
 */
public class ReportEfficiencyMonth implements AjaxInterface{

    ReportUtils rptUtl = new ReportUtils();
    @Override
    public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm) throws Exception {

        if (!G.isOwnRight("13301000", request)) {
            response.getWriter().write("没有权限或会话已失效");
            return;
        }

        String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);
        if (G.isNvl(key)) {
            response.getWriter().write("");
            return;
        }

        JSONObject json = new JSONObject(key);

        String dtStr = json.getString("TYPE");
        //导入时间
        json.put("S_CREATE_TIME_ST", dtStr);
        json.put("S_CREATE_TIME_ED", rptUtl.getPreMonth(dtStr));

        //String companyId = G.getExpressCompanyId(request);

        DataObject dob = new DataObject(json);
        Iterator<DataObject> it = rptUtl.getRptType(dob, dm);

        Iterator<DataObject> it2 = rptUtl.getRptTableType(dob, dm);

        JSONArray days = rptUtl.currentDaysOfMonth(json.getString("TYPE"));
        JSONArray ja = new JSONArray();
        JSONArray ja2 = new JSONArray();

        json = null;

        while (it.hasNext()) {
            dob = it.next();
            json = new JSONObject();
            json.put("REWORK_TYPE", dob.getString("REWORK_TYPE"));
            json.put("shf_count", dob.getString("shf_count"));
            ja.put(json);
        }
        while (it2.hasNext()) {
            dob = it2.next();
            json = new JSONObject();
            json.put("REWORK_TYPE", dob.getString("REWORK_TYPE"));
            json.put("count", dob.getString("SHF_COUNT"));
            json.put("day", dob.getString("MIXED_DATE"));
            ja2.put(json);
        }
        json = new JSONObject();
        json.put("series", ja);
        json.put("tableSeries", ja2);
        json.put("x", days);

        response.getWriter().write(json.toString());

    }

}
