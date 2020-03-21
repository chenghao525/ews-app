package com.corp.oa.system.role;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.exception.GeneralException;

/**
 * <p>
 * 删除角色信息:
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

public class RoleDelAction implements BusinessAction {
	public RoleDelAction() {
	}

	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		RoleForm roleForm = (RoleForm) parm1;
		// 设置页码
		roleForm.getPagination().setCurrent(roleForm.getCurrent());

		String strSql = "";

		String RoleID = roleForm.getRoleid();

		// 查看是否为系统默认角色
		strSql = "select type from t_right_roledic where roleid='" + RoleID
				+ "'";
		DataObject db = dm.findByPrimaryKey(strSql, null);
		if (db != null) {
			// 不是系统默认角色才可以删除,否则不能删除
			if ("1".equals(db.getString("TYPE"))) {
				// 不能删除系统默认角色！
				throw new GeneralException("error.DelDefaultRole");
			} else {
				strSql = "delete from T_RIGHT_USERROLE where roleid='" + RoleID
						+ "'";
				dm.delete(strSql, null);

				strSql = "delete from T_RIGHT_ROLERIGHT where roleid='"
						+ RoleID + "'";
				dm.delete(strSql, null);

				strSql = "delete from T_RIGHT_ROLEDIC where roleid='" + RoleID
						+ "'";
				dm.delete(strSql, null);
			}
		}
	}

}
