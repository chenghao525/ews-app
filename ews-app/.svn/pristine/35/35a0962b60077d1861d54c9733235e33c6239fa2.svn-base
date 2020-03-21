package com.corp.servlet;

import com.corp.ews.EWSConstant;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class YYCallbackServlet extends HttpServlet {

    public final Log logger = LogFactory.getLog(getClass());

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml;charset=GBK");

        String token = request.getParameter("token");
        String sign  = request.getParameter("sign");
        if (G.isNvl(token)) {
            logger.error("[SSO响应]：得到参数为空");
            response.getWriter().write("");
            return;
        }

        DataManager dm = null;
        String para = "";

        try {
            dm = DataManager.getInstance();
            // 保存日志
            para = G.nvl(token, "") + G.nvl(sign, "");
            if (para.length() > 2000) {
                para = para.substring(0, 2000);
            }
            G.insertLog(request, getClass() + "#" + para, dm);
            // 事务手动管理
            dm.setLocalConnAutoCommit(false);

            // 获取到响应
            String rs = responseSso(token, sign, dm);
            response.getWriter().write(rs);

            // 提交事务
            try {
                dm.setLocalConnCommit();
            } catch (Exception ex) {
                logger.error(getClass() + " sso ajax commit error:" + ex.getMessage());
            }

        } catch (Exception e) {
            String errMsg = "EWS [SSO响应]：" + getClass() + " ajax error:" + e.getMessage() +
                    ", params:{token:" + token + ", sign:" + sign + "}";
            try {
                // 事务回滚
                if (dm != null) {
                    dm.setLocalConnRollback();
                }
                G.inserExceptionLog(errMsg, dm);
            } catch (Exception ex) {
                logger.error(getClass() + " sso ajax rollback error:" + ex.getMessage());
            }
            logger.error(errMsg, e);
            response.getWriter().write(EWSConstant.returnMessage(1, "运行时错误"));
        } finally {
            if (dm != null) {
                dm.close();
            }
        }
    }
    private String responseSso(String token, String sign, DataManager dm) throws Exception {


        String sql = "select * from t_ews_login_session where token=?";
        DataObject dob = dm.findByPrimaryKey(sql, token);

        JSONObject rsJson = new JSONObject();
        if (dob != null) {
            // 写回调 userId为必填字段
            rsJson.put("userId", dob.getString("USER_ID"));

            rsJson.put("userName", "system");
            rsJson.put("userMobile", "13589748906");
            rsJson.put("userEmail", "www.system@wedrive.com.cn");
        }
        return rsJson.toString();
    }
}
