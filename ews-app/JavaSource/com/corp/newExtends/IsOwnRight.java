package com.corp.newExtends;

import com.corp.ews.EWSConstant;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IsOwnRight implements AjaxInterface {

    @Override
    public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm) throws Exception {

        String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);
        if (G.isNvl(key)) {
            response.getWriter().write(EWSConstant.returnMessage(2, "无效参数"));
            return;
        }
        JSONObject json = new JSONObject(key);
        String rightId = json.getString("RightId");
        boolean isOwnRight = G.isOwnRight(rightId, request);
        json = new JSONObject();
        json.put("isOwn", isOwnRight);

        response.getWriter().write(EWSConstant.returnMessage(0, "OK", json));
    }
}
