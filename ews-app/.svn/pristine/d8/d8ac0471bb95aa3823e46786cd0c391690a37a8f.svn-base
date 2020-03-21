package com.corp.ews.ro;

import com.corp.ews.EWSConstant;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetRODetails implements AjaxInterface {

    @Override
    public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm) throws Exception {

        if (!G.isOwnRight("13101201", request)) {
            response.getWriter().write("会话已失效或无权限");
            return;
        }
        String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);
        if (G.isNvl(key)) {
            response.getWriter().write(EWSConstant.returnMessage(1, "数据错误"));
            return;
        }
        JSONObject json = new JSONObject(key);

        String ewsRoCode = json.getString("EWS_RO_CODE");
        String roNumber = json.getString("RO_NUMBER");

        String provSid = G.getEWSProvSidFromSession(request);

        RepairOrdersUtils roUtl = new RepairOrdersUtils();

        json = roUtl.roDetailsByEwsRoCode(ewsRoCode, roNumber, provSid, dm);

        String result = null;
        if (json == null) {
            result = EWSConstant.returnMessage(1, "工单不存在");
        } else {
            result = EWSConstant.returnMessage(0, "获取成功", json);
        }
        response.getWriter().write(result);
    }
}
