package com.corp.newExtends;

import com.corp.ews.EWSConstant;
import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.base.SystemConfig;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.login.OaUserView;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Writer;

public class LoginControl implements AjaxInterface {

    protected ExtendUtils exu = new ExtendUtils();

    @Override
    public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm) throws Exception {

        String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);

        Writer writer = response.getWriter();
        if (G.isNvl(key)) {

            writer.write(EWSConstant.returnMessage(2, "参数错误"));
            return;
        }
        JSONObject json = new JSONObject(key);
        if (json == null) {
            writer.write(EWSConstant.returnMessage(2, "参数错误"));
            return;
        }
        String logIp = G.getIpAddr(request);
        String user = json.getString("USER").toUpperCase().trim();
        String password = json.getString("PWD");
        String authCode = json.getString("AUTH_CODE");
        if (G.isNvl(user) || G.isNvl(authCode)) {
            writer.write(EWSConstant.returnMessage(3, "无效参数"));
            return;
        }

        if (exu.isTryTimesOut(user, logIp, dm)) {
            // 尝试次数超过5次，锁定10分钟
            writer.write(EWSConstant.returnMessage(4, "登录次数过多，请稍后再试"));
        }
        String passwordJm = "";
        if (!G.isNvl(password)) {
            passwordJm = G.jiaMi(password);
        }
        json = loginAction(user, passwordJm, logIp, request, dm);
        if (json == null) {
            writer.write(EWSConstant.returnMessage(5, "账户名或密码错误"));
            return;
        }
        JSONArray ja = exu.getMenuList(json.getString("SID"), "zh_CN", dm);
        json.put("MENU", ja);
        writer.write(EWSConstant.returnMessage(0, "成功", json));
    }

    private JSONObject loginAction(String user, String pwd, String logIp,
                                   HttpServletRequest request, DataManager dm) throws Exception {

        String sql = "select F_getdeptname(a.sid)as deptname,f_get_emp_duty_class(a.SID) AS DUTY," +
                "F_GETMAPCONTENT(F_GETDEPT_AREA(a.sid))as ARAENAME,a.sid,a.emppwd, a.U8C_CODE, a.PAINT_MAKER_ID," +
                "a.empid,a.empname,new_f_getdeptsid(a.sid) as DUTYSID,a.MP,a.PAGE_NATIONAL, a.EMP_TYPE" +
                " from t_info_employee a where upper(a.empid)=?";
        String[] info = null;
        if (G.isNvl(pwd)) {
            sql += " and (emppwd is null or emppwd='')";
            info = new String[] {user};
        } else {
            sql += " and emppwd=?";
            info = new String[] { user, pwd };
        }
        sql += " and a.EMP_ACTIVE=16 and a.state='1'";
        DataObject do1 = dm.findByPrimaryKey(sql, info);
        if (do1 != null) {
            sql = "select PROV_SID from t_ews_provider_extension WHERE INSTR(EMP_SID, ?) > 0";
            DataObject mDob = dm.findByPrimaryKey(sql, do1.getString("SID"));

            HttpSession session = request.getSession(true);
            String userId = do1.getString("SID");
            String UserCNName = do1.getString("EMPNAME");

            // 保存登录信息
            dm.callProcedure("CALL P_LOGIN(?,?,?,?,?)", new String[] { userId,
                    UserCNName, G.getCurrentTime(), session.getId(), logIp });

            exu.initTryTimes(userId, logIp, dm);

            String[] inc = new String[] {
                    "EMPID", "EMPNAME", "DEPTNAME", "DUTYSID", "MP", "SID", "EMP_TYPE",
                    "U8C_CODE", "PAINT_MAKER_ID"
            };
            JSONObject json = do1.toJsonByArray(inc);
            json.put("PROV_SID", mDob.getString("PROV_SID"));

            json.put("IS_SUPER_ADMIN", G.isSuperSystemUserRole(dm, json.getString("SID")));

//            sessionStore(session, do1, mDob.getString("PROV_SID"), dm);


            return json;
        }
        return null;
    }
    private void sessionStore(HttpSession session, DataObject do1, String provSid, DataManager dm) throws Exception {
        OaUserView userView = new OaUserView();

        String SID = do1.getString("SID");
        // 用户内部编号
        userView.setUserId(do1.getString("SID"));
        // 用户姓名
        userView.setUserCNName(do1.getString("EMPNAME"));
        // 获得用户登陆名
        userView.setUserName(do1.getString("EMPID"));
        // 用户的部门编号,名称
        userView.setDepartmentId(do1.getString("DUTYSID"));
        userView.setDepartmentName(do1.getString("DEPTNAME"));

        // 用户的手机
        userView.setMp(do1.getString("MP"));

        // 是否是超级系统管理员用户
        userView.setSuperSystemRole(G.isSuperSystemUserRole(dm, SID));

        // 是否是超级业务管理员用户
        userView.setSuperBusinessRole(G.isSuperBusinessUserRole(dm, SID));

        session.setAttribute("ESID", do1.getString("SID"));
        session.setAttribute("UserSID", do1.getString("SID"));
        session.setAttribute("UserID", do1.getString("EMPID"));
        session.setAttribute("DEPTNAME", do1.getString("DEPTNAME"));
        session.setAttribute("ARAENAME", do1.getString("ARAENAME"));
        session.setAttribute("DUTY", do1.getString("DUTY"));
//				String empType = G.nvl(do1.getString("EMP_TYPE"), "");
        session.setAttribute("EMP_TYPE", do1.getString("EMP_TYPE"));
        session.setAttribute("EMPNAME", do1.getString("EMPNAME"));
        session.setAttribute("U8C_CODE", do1.getString("U8C_CODE"));
        session.setAttribute("PAINT_MAKER_ID", do1.getString("PAINT_MAKER_ID"));
        if  (!G.isNvl(provSid)) {
            session.setAttribute("PROV_SID", provSid);
        }

        // 将语言图片目录后缀放入到Session中
        session.setAttribute("LangImg", "");

        // 用户信息保存在 SESSION 中
        session.setAttribute(SystemConfig.getWebUserViewName(), userView);
    }

}
