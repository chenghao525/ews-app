package com.corp.oa.system.role;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.base.UserInfo;
import com.corp.frame.core.dao.DataManager;

/**
 * <p>
 * 角色信息:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author liss
 * @version 1.0
 */

public class RoleListAction implements BusinessAction {
	public RoleListAction() {
	}

	@SuppressWarnings("unchecked")
	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		RoleForm userForm = (RoleForm) parm1;

		// 设置页码
		userForm.getPagination().setCurrent(userForm.getCurrent());

		// 获得操作人员的信息
		UserInfo userView = userForm.getUserView();

		// 内部编号
		String useId = userView.getUserId();

		String strSql = "";

		// 获得角色信息列表
		strSql = "select a.rolename,sid,roleid from t_right_roledic a where a.state='1' and a.roleid in";
		strSql += "(select roleid from t_right_userrole where empid=" + useId
				+ ")";
		userForm.getQueryView().setSelect(strSql);
		userForm.getQueryView().setExtension("order by sid");
		userForm.setUserList(dm.find(userForm.getQueryView().getSql(), null,
				false, null, userForm.getPagination()));

	}

}
