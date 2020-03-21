package com.corp.oa.system.right;

import java.util.Collection;
import java.util.Iterator;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.base.UserInfo;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;

/**
 * <p>
 * Title: 权限设置
 * </p>
 * <p>
 * Description: 权限设置功能模块
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

public class RightByRoleAction implements BusinessAction {
	public RightByRoleAction() {
	}

	@SuppressWarnings("unchecked")
	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		RightForm rightForm = (RightForm) parm1;

		// 获得操作人员的信息
		UserInfo userView = rightForm.getUserView();

		// 内部编号
		String useId = userView.getUserId();

		String strRoleID = rightForm.getCurroleid();

		String strSql = "";
		// 获得该人员所拥有的角色信息列表
		strSql = "select a.rolename,sid,roleid from t_right_roledic a where a.state='1' and a.roleid in";
		strSql += "(select roleid from t_right_userrole where empid=" + useId
				+ ") order by sid";

		Collection<DataObject> ct = dm.find(strSql, null);
		rightForm.setRoleinfolist(ct);

		if (strRoleID == null || strRoleID.equals("")) { // 初始化的状态
			strRoleID = "";
			if (ct.size() > 0) { // 人员拥有角色数据的情况
				Iterator<DataObject> it = ct.iterator();
				while (it.hasNext()) {
					DataObject doe = it.next();
					// 有数据的情况下,取得第一条记录的角色编号
					strRoleID = doe.getString("roleid");
					break;
				}
			}
		}

		rightForm.setCurroleid(strRoleID);

		// 从员工表中选择出数据,并确定人员是否拥有指定的角色
		/*
		 * strSql = "select SID,EMPID || '/' || EMPNAME || '[' ||
		 * F_GETDEPTNAME(DEPTSID)"; strSql += " || '/' ||
		 * F_GETMAPCONTENT(EMPRANK) || ']' as empinfo,F_EMP_HAS_ROLE(sid,'";
		 * strSql += strRoleID + "') as ismyright from T_INFO_EMPLOYEE where
		 * STATE=3";
		 */
		strSql = "select SID,EMPID || '/' || EMPNAME|| '[' ||F_GETDEPTNAME(sid)|| ";
		strSql += " '/'||F_GETDUTYNAME(sid)||']' as empinfo,F_EMP_HAS_ROLE(sid,'";
		strSql += strRoleID
				+ "') as ismyright,f_get_emp_duty_class(sid) as emporder";
		strSql += " from T_INFO_EMPLOYEE A where STATE=1 and sid<>" + useId
				+ " and sid<>0 order by emporder";

		// 选择员工列表
		rightForm.setEmplist(dm.find(strSql, null));

	}

}
