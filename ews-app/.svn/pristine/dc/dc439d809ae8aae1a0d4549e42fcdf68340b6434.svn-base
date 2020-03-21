package com.corp.sso.service;

import com.corp.ews.EWSConstant;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFuncNode implements AjaxInterface {

    @Override
    public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm) throws Exception {

        String data = request.getParameter(AJAX_PARAMETER_KEY_NAME);
        if (G.isNvl(data)) {
            response.getWriter().write("参数为空");
            return;
        }

        RegisterU8C rgtU8c = new RegisterU8C();

        String userId = G.getUserIdFromSession(request);
        String loginAccount = G.getU8cAccountFromSession(request);
        if (G.isNvl(loginAccount)) {
            loginAccount = G.getUserLoginAccountFromSession(request);
        }

        String pkCrop = pkCropByUserId(userId, dm);
        if (G.isNvl(pkCrop)) {
            response.getWriter().write(EWSConstant.returnMessage(3, "pkCrop为空，请维护！"));
            return;
        }

        String key = rgtU8c.register(loginAccount, pkCrop);
        if (G.isNvl(key)) {
            response.getWriter().write(EWSConstant.returnMessage(1, "验证签名key为空"));
            return;
        }
        if (key.indexOf("Error") >= 0) {
            response.getWriter().write(EWSConstant.returnMessage(2, "验证签名key错误：" + key));
            return;
        }
        JSONObject json = new JSONObject(data);
        String billId = json.getString("billId");
        String funcNode = json.getString("funcNode");
        if (G.isNvl(funcNode)) {
            response.getWriter().write("funcNode为空");
            return;
        }
        // 登录到对应节点的链接
        String url = RegisterU8C.serviceUrl + "login.jsp?key=" + key
                + "&funcNode=" + funcNode + "&billId=" + billId;
        response.getWriter().write(EWSConstant.returnMessage(0, url));
    }

    private String pkCropByUserId(String userId, DataManager dm) throws Exception {

        String sql = "SELECT * FROM T_EWS_PROVIDER_EXTENSION WHERE INSTR(EMP_SID, ?) > 0";
//        sql = G.formatSql(sql, userId);
        DataObject dob = dm.findByPrimaryKey(sql, userId);
        if (dob == null) {
            return null;
        }
        return dob.getString("PK_CROP");
    }
}
