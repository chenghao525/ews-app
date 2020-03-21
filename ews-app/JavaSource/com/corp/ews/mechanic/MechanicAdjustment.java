package com.corp.ews.mechanic;

import com.corp.ews.EWSConstant;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wqj
 */
public class MechanicAdjustment implements AjaxInterface {

    private static String add   = "add";
    private static String del   = "del";
    private static String edit  = "edit";

    @Override
    public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm) throws Exception {

        if (!G.isOwnRight("13401001,13401002", request)) {
            response.getWriter().write("会话已失效或无权限");
            return;
        }
        String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);
        if (G.isNvl(key)) {
            response.getWriter().write(EWSConstant.returnMessage(2, "保存错误"));
            return;
        }
        JSONObject json = new JSONObject(key);

        String type = json.getString("type");
        json.remove("type");

        String user = G.getUserNameFromSession(request);

        MechanicUtils mUtl = new MechanicUtils();
        String msg = null;
        int code = 0;
        if (add.equals(type)) {
            msg = mUtl.addMechanic(user, new DataObject(json), dm);
        } else if (del.equals(type)) {
            msg = mUtl.deleteMechanic(user, new DataObject(json), dm);
        } else if (edit.equals(type)){
            msg = mUtl.editMechanic(user, new DataObject(json), dm);
        } else {
            msg = "操作类型错误";
            code = 1;
        }

        response.getWriter().write(EWSConstant.returnMessage(code, msg));

    }
}
