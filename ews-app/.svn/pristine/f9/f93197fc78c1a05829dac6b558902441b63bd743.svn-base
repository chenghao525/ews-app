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

		// 获得操作人员的信息
		UserInfo userView = userForm.getUserView();
		// 内部编号
		String sessionId = userForm.getSessionId();
		if (dm.findByPrimaryKey(
				"select * from T_INFO_LOGIN WHERE EMPSID = ? AND SESSIONID = ?",
				new String[] { userView.getUserId(), sessionId }) == null) {
			// 其它用户用这个用户名登陆过
			userForm.setLogout(true);
			return;
		}

	}
}
