package com.corp.oa.system.role;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.base.UserInfo;
import com.corp.frame.core.dao.DataManager;

/**
 * <p>
 * 修改角色信息:
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

public class RoleEditAction implements BusinessAction {
	public RoleEditAction() {
	}

	@SuppressWarnings("unchecked")
	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		RoleForm roleForm = (RoleForm) parm1;

		// 获得操作人员的信息
		UserInfo userView = roleForm.getUserView();

		// 内部编号
		String useId = userView.getUserId();

		String strRoleID = roleForm.getRoleid();
		// System.out.println(strRoleID);

		String optType = roleForm.getDisplay();

		if (optType == null)
			roleForm.setDisplay("add");
		else if (optType.equals("edit"))
			roleForm.setDisplay("edit"); // 修改角色
		else if (optType.equals("find"))
			roleForm.setDisplay("find"); // 查询角色
		else
			roleForm.setDisplay("add");

		optType = roleForm.getDisplay();

		String strSql = "";

		if (optType.equals("add")) {
			roleForm.setRolename("新角色");

			// 保存用户没有的角色权限列表
			roleForm.setRightlist(dm.find(
					"{call rightpackage.pro_get_rolerightlist(?,?)}", useId));
		} else {
			strSql = "select rolename from t_right_roledic where roleid='"
					+ strRoleID + "'";
			roleForm.setRolename(dm.findByPrimaryKey(strSql, null).getString(
					"rolename"));

			// 保存用户没有的角色权限列表
			roleForm.setRightlist(dm.find(
					"{call rightpackage.pro_get_rolerightlist(?,?,?)}",
					new String[] { useId, strRoleID }));
		}
	}

}
