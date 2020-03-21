package com.corp.oa.system.role;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.base.UserInfo;
import com.corp.frame.core.dao.DataManager;

/**
 * <p>
 * �޸Ľ�ɫ��Ϣ:
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

		// ��ò�����Ա����Ϣ
		UserInfo userView = roleForm.getUserView();

		// �ڲ����
		String useId = userView.getUserId();

		String strRoleID = roleForm.getRoleid();
		// System.out.println(strRoleID);

		String optType = roleForm.getDisplay();

		if (optType == null)
			roleForm.setDisplay("add");
		else if (optType.equals("edit"))
			roleForm.setDisplay("edit"); // �޸Ľ�ɫ
		else if (optType.equals("find"))
			roleForm.setDisplay("find"); // ��ѯ��ɫ
		else
			roleForm.setDisplay("add");

		optType = roleForm.getDisplay();

		String strSql = "";

		if (optType.equals("add")) {
			roleForm.setRolename("�½�ɫ");

			// �����û�û�еĽ�ɫȨ���б�
			roleForm.setRightlist(dm.find(
					"{call rightpackage.pro_get_rolerightlist(?,?)}", useId));
		} else {
			strSql = "select rolename from t_right_roledic where roleid='"
					+ strRoleID + "'";
			roleForm.setRolename(dm.findByPrimaryKey(strSql, null).getString(
					"rolename"));

			// �����û�û�еĽ�ɫȨ���б�
			roleForm.setRightlist(dm.find(
					"{call rightpackage.pro_get_rolerightlist(?,?,?)}",
					new String[] { useId, strRoleID }));
		}
	}

}
