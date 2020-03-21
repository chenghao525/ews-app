package com.corp.oa.system.right;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.base.UserInfo;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;

/**
 * <p>
 * Title: 根据角色列表的权限设置
 * </p>
 * <p>
 * Description: 保存权限设置信息功能模块
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

public class RightSaveByRoleAction implements BusinessAction {
	public RightSaveByRoleAction() {
	}

	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		RightForm rightForm = (RightForm) parm1;

		// 获得操作人员的信息
		UserInfo userView = rightForm.getUserView();

		// 内部编号
		String useId = userView.getUserId();

		// 取得当前选中的角色编号
		String strRoleID = rightForm.getCurroleid();

		String[] emprole = rightForm.getRoleemplist();

		// 删除该角色对应的所有人员（不能删除系统管理员的角色）
		String strSql = "delete from T_RIGHT_USERROLE where ROLEID=? and EMPID<>0 and EMPID<>"+useId;;
		dm.delete(strSql, strRoleID);

		if (emprole != null) {
			for (int i = 0; i < emprole.length; i++) { // 添加人员角色信息
				if (!"0".equals(emprole[i].trim())) {//不能删除系统管理员的角色
					DataObject dbnew = new DataObject();
					dbnew.setModelName("T_RIGHT_USERROLE");
					dbnew.setString("SID", G
							.getSequence("SEQ_T_IWL", dm));
					dbnew.setString("ROLEID", strRoleID);
					dbnew.setString("EMPID", emprole[i].trim());
					dbnew.setString("CRTUSER", useId);
					// dbnew.setString("CRTDATE", DateFormat.getCurrent());
					dbnew.setString("UPTUSER", useId);
					// dbnew.setString("UPTDATE", DateFormat.getCurrent());
					dm.addBatch(dbnew);
				}
			}
			dm.insertBatch();

		}

	}
}
