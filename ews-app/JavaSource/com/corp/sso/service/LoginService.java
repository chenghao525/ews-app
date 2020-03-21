package com.corp.sso.service;

import com.corp.ews.EWSConstant;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;
import com.corp.sso.utils.HttpUtil;
import com.corp.sso.utils.PropUtil;
import com.corp.sso.utils.SignUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * ���ѻ�ͨ�����¼
 * @author wqj
 */
public class LoginService implements AjaxInterface {

    public final Log logger = LogFactory.getLog(getClass());
    private static String loginUrl;
    private static String clientId;

    static {
        Properties prop = PropUtil.getProperties("/system-project.properties");
        loginUrl = prop.getProperty("login_url");
        clientId = prop.getProperty("appId");
    }

    @Override
    public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm) throws Exception {

        String userId = G.getUserIdFromSession(request);
        if (G.isNvl(userId)) {
            G.inserExceptionLog("[SSO����]��δ�ҵ���¼�û�id", dm);
            response.getWriter().write(EWSConstant.returnMessage(1, "[SSO����]��δ�ҵ���¼�û�id"));
            return;
        }

        String token = getToken(userId);
        if (G.isNvl(token)) {
            G.inserExceptionLog("[SSO����]��token���ɴ���", dm);
            response.getWriter().write(EWSConstant.returnMessage(2, "[SSO����]��token���ɴ���"));
            return;
        }
        Map<String, Object> params = getReqParams(token);

        String responseResult = HttpUtil.get(loginUrl, params);
        if (G.isNvl(responseResult)) {
            logger.info("[SSO��¼���]������ֵΪ��");
        } else {
            logger.info("[SSO��¼���]��" + responseResult);
        }
        response.getWriter().write(EWSConstant.returnMessage(0, "[SSO�ɹ�]����¼��"));
    }

    private String getToken(String userId) throws Exception {

        DataManager dm = null;
        try {
            dm = DataManager.getInstance();
            String sql = "select * from t_ews_login_session where user_id=?";
            DataObject dob = dm.findByPrimaryKey(sql, userId);

            String uuid = G.uuid();
            if (G.isNvl(uuid)) {
                return null;
            }

            //������ڣ������token, �����½�һ��
            if (dob != null) {

                dob.setModelName("T_EWS_LOGIN_SESSION");
                dob.setString("TOKEN", uuid);
                dob.setString("LOGIN_TIME", G.getCurrentTime());
                dob.setString("LOGIN_SOURCE", "EWS/BACKEND");

                dm.update(dob);
            } else {
                dob = new DataObject();
                dob.setModelName("T_EWS_LOGIN_SESSION");
                dob.setString("USER_ID", userId);
                dob.setString("TOKEN", uuid);
                dob.setString("LOGIN_TIME", G.getCurrentTime());
                dob.setString("LOGIN_SOURCE", "EWS/BACKEND");

                dm.insert(dob);
            }
            return uuid;
        } catch (Exception e) {
            throw e;
        } finally {
            if (dm != null) {
                dm.close();
            }
        }
    }

    /**
     * �����¼
     * @return
     * @throws Exception
     */
    public JSONObject login(String token) throws Exception {

        Map<String, Object> params = getReqParams(token);

        String responseResult = HttpUtil.get(loginUrl, params);
        if (!G.isNvl(responseResult)) {
            logger.info("[yy SSO]: ��¼�ص���" + responseResult);
            JSONObject json = new JSONObject(responseResult);
            return json;
        }
        return null;
    }
    public String jspSSOLogin(String userId) throws Exception {

        if (G.isNvl(userId)) {
            return EWSConstant.returnMessage(1, "userIdΪ��");
        }

        String token = getToken(userId);
        if (G.isNvl(token)) {
            return EWSConstant.returnMessage(2, "tokenΪ��");
        }
        Map<String, Object> params = getReqParams(token);

        String responseResult = HttpUtil.get(loginUrl, params);
        if (G.isNvl(responseResult)) {
            logger.info("[SSO��¼���]������ֵΪ��");
        } else {
            logger.info("[SSO��¼���]��" + responseResult);
        }
        return responseResult;
    }

    /**
     * ���������
     * @param token
     * @return
     * @throws Exception
     */
    private  Map<String, Object> getReqParams(String token) throws Exception {

        Properties prop = PropUtil.getProperties("/system-project.properties");
        String secretKey = prop.getProperty("key");

        String callbackUrl = prop.getProperty("callback_url");

        if (G.isNvl(secretKey)) {
            throw new NullPointerException("[yy SSO]�������Ϣδ����");
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("token", token);
        params.put("clientId", clientId);
        params.put("ts", System.currentTimeMillis());
        params.put("service", callbackUrl);
        //ǩ��
        String signature = SignUtil.sign(params, secretKey);

        params.put("sign", URLEncoder.encode(signature, "UTF-8"));
        params.put("service", URLEncoder.encode(callbackUrl, "UTF-8"));


        return params;
    }

}

