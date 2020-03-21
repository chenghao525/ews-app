package com.corp.oa.menu;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.base.UserInfo;
import com.corp.frame.core.dao.DataManager;

public class HeadRefresh implements BusinessAction {
	public HeadRefresh() {
	}

	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {

		MenuForm userForm = (MenuForm) parm1;

		// ��ò�����Ա����Ϣ
		UserInfo userView = userForm.getUserView();
		// �ڲ����
		String sessionId = userForm.getSessionId();
		if (dm.findByPrimaryKey(
				"select * from T_INFO_LOGIN WHERE EMPSID = ? AND SESSIONID = ?",
				new String[] { userView.getUserId(), sessionId }) == null) {
			// �����û�������û�����½��
			userForm.setLogout(true);
			return;
		}

	}
}
