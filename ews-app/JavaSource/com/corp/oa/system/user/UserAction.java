package com.corp.oa.system.user;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;
import com.corp.oa.util.G;

public class UserAction implements BusinessAction {
	public UserAction() {
	}

	@SuppressWarnings("unchecked")
	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		UserForm userForm = (UserForm) parm1;
		// 设置页码
		userForm.getPagination().setCurrent(userForm.getCurrent());

		// 内部编号
		String sql = "";
		// 超级管理员不显示
		sql = "select * from (select a.*,(select dept_city from t_info_dept where sid=";
		sql += " (select t.parentdept from t_info_dept t where t.sid=";
		sql += " (select dutysid from t_info_employee where sid=a.sid and emp_src='ews'))) as dept_city "
				+ " from T_INFO_EMPLOYEE a) where state='1' and sid<>0";

		if (!dm.getUser().isSuperBusinessRole()) {// 不是超级用户，只能显示本城市的用户
			String city = G.getEmployeeCity(dm, dm.getUser().getUserId());
			sql = "select * from (" + sql + ") where dept_city='" + city + "'";
		}

		userForm.getQueryView().setSelect(sql);
		userForm.getQueryView().setExtension("order by sid desc");
		sql = userForm.getQueryView().getSql();
		userForm.setUserList(dm.find(sql, null, userForm.getPagination()));
	}

}
