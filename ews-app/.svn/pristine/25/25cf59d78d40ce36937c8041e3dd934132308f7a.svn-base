package com.corp.oa.login;

import com.corp.frame.action.Constant;
import com.corp.frame.core.base.SystemConfig;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.debug.Debug;
import com.corp.frame.core.util.DateFormat;
import com.corp.oa.menu.MenuAction;
import com.corp.oa.util.G;
import com.corp.sso.service.LoginService;
import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.Locale;

/**
 *
 * Title:处理用户登陆action
 *
 * @author 李恕圣 2006/07/05
 * @version 1.0
 */

public class LoginAction extends Action {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Debug debug = new Debug("begin..");

		LoginForm loginForm = (LoginForm) actionForm;
		Connection conn = null;
		// 验证成功
		HttpSession session = null;

		try {
			// 当前时间
			String CurrTime = DateFormat.getCurrent();
			// 获得用户编号和密码
			String userId = loginForm.getEmpid().toUpperCase().trim();
			String passWord = loginForm.getEmppwd();

			// 对密码加密
			String passWordJm = "";
			if (!"".equals(passWord))
				passWordJm = G.jiaMi(passWord);

			// boolean flag = false;

			// 验证成功
			session = request.getSession(true);

			// 验证

			// 获得连接
			conn = DataManager.getConnection();
			conn.setAutoCommit(false);
			debug.addAlert("get connection..");
			DataManager dm = DataManager.getInstance(conn);
			dm.setCheckPermission(false);

			String logIp = G.getIpAddr(request);

			if (isTryTimesOut(userId, logIp, dm)) {
				// 尝试次数超过5次，锁定10分钟
				session.invalidate();
				return (actionMapping.findForward("tryout"));
			}

			String sql = "";

			// 选出帐户状态正在使用的，没有删除的帐户信息
			sql = "select F_getdeptname(a.sid)as deptname,f_get_emp_duty_class(a.SID) AS DUTY,";
			sql += " F_GETMAPCONTENT(F_GETDEPT_AREA(a.sid))as ARAENAME,a.sid,a.emppwd, a.U8C_CODE, a.PAINT_MAKER_ID,";
			sql += "a.empid,a.empname,new_f_getdeptsid(a.sid) as DUTYSID,a.MP,a.PAGE_NATIONAL, a.EMP_TYPE";
			sql += " from t_info_employee a where a.emp_src='ews' and upper(a.empid)=?";

			String[] loginfo = null;
			if ("".equals(passWordJm)) {
				sql += " and (emppwd is null or emppwd='')";
				loginfo = new String[] { userId };
			} else {
				sql += " and emppwd=?";
				loginfo = new String[] { userId, passWordJm };
			}
			sql += " and a.EMP_ACTIVE=16 and a.state='1'";
			DataObject do1 = dm.findByPrimaryKey(sql, loginfo);

			if (do1 != null) {

				sql = "select PROV_SID from t_ews_provider_extension WHERE INSTR(EMP_SID, '" +do1.getString("SID")+ "') > 0";
				DataObject mDob = dm.findByPrimaryKey(sql, null);

				// 将语言选项放入到Session中
				session.setAttribute(Globals.LOCALE_KEY, new Locale("zh_CN"));

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
				if  (mDob != null) {
					session.setAttribute("PROV_SID", mDob.getString("PROV_SID"));
				}


				// 将语言图片目录后缀放入到Session中
				session.setAttribute("LangImg", "");

				String RightList = MenuAction.getEmpRightList(dm, SID);
				if (RightList == null)
					return (actionMapping.findForward("failure"));

				// 用户信息保存在 SESSION 中
				session.setAttribute(SystemConfig.getWebUserViewName(), userView);
				debug.addAlert("sucessful..");

				// 保存登录信息
				dm.callProcedure("CALL P_LOGIN(?,?,?,?,?)", new String[] { userView.getUserId(),
						userView.getUserCNName(), CurrTime, session.getId(), logIp });

				initTryTimes(userId, logIp, dm);

				conn.commit();
				return (actionMapping.findForward("success"));

			} else {
				session.invalidate();
				addOneTries(userId, logIp, dm);// 增加一次尝试
				conn.commit();
				// throw new GeneralException("用户名或密码有误！");
				return (actionMapping.findForward("failure"));
			}

		} catch (Throwable ex) {
			if (conn != null) {
				conn.rollback();
			}
			if (session != null)
				session.invalidate();
			// Debug.error(ex);
			request.setAttribute(Constant.EXCEPTION, ex);
			// 出错后返回到全局出错页面 2004-09-06
			return actionMapping.findForward(Constant.GLOBAL_ERROR_FORWARD);
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * 在此判断客户的登陆次数是否已经大于3次了
	 *
	 * @param empId
	 * @param ip
	 * @param dm
	 * @return
	 * @throws Exception
	 */
	private boolean isTryTimesOut(String empId, String ip, DataManager dm) throws Exception {
		boolean limit = true;
		DataObject dob = null;
		String min = "10"; // 10分钟之内
		String sql = "select * from t_info_login_error t where ((sysdate -"
				+ " to_date(t.logintime, 'YYYY-MM-DD HH24:MI:SS')) * 24 * 3600) <=(" + min
				+ " * 60)" + " and t.empid=? and t.ip=?";

		dob = dm.findByPrimaryKey(sql, new String[] { empId, ip });
		if (dob == null) {// 还没有信息
			limit = false;
		} else {// 判断是否大于3次了
			int times = dob.getInt("ERR_TIMES");
			limit = !(times < 3);
		}
		return limit;
	}

	/**
	 * 增加一次错误登录的次数
	 *
	 * @param empId
	 * @param ip
	 * @param dm
	 * @throws Exception
	 */
	private void addOneTries(String empId, String ip, DataManager dm) throws Exception {
		String sql = "select * from t_info_login_error t where t.empid=? and t.ip=?";
		DataObject dob = dm.findByPrimaryKey(sql, new String[] { empId, ip });
		if (dob == null) {// 还没有登录错误信息
			dob = new DataObject();
			dob.setString("EMPID", empId);
			dob.setString("IP", ip);
			dob.setString("LOGINTIME", G.getCurrentTime());
			dob.setString("ERR_TIMES", "1");
		} else {// 已有错误登录信息
			int times = dob.getInt("ERR_TIMES");
			times++;
			dob.setInt("ERR_TIMES", times);
			dob.setString("LOGINTIME", G.getCurrentTime());
		}
		dob.setModelName("T_INFO_LOGIN_ERROR");
		dm.save(dob);
	}

	private void initTryTimes(String empId, String ip, DataManager dm) throws Exception {

		DataObject dob = new DataObject();
		dob.setString("EMPID", empId);
		dob.setString("IP", ip);
		dob.setString("LOGINTIME", G.getCurrentTime());
		dob.setString("ERR_TIMES", "0");
		dob.setModelName("T_INFO_LOGIN_ERROR");
		dm.save(dob);

	}
	private DataObject getBranchCompanyInfo(String branchId, DataManager dm) throws Exception {
		String sql = "SELECT BRAND_SHORT_ID, BRAND_SHORT, CITY, SID, PROVINCE FROM T_CPIC_BRAND WHERE SID=?";
		sql = G.formatSql(sql, branchId);

		return dm.findByPrimaryKey(sql, null);
	}
}
