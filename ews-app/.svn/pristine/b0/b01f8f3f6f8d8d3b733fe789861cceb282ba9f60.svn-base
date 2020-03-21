package com.corp.oa.system.user;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;

public class UserPwdResetAction implements BusinessAction {
	public UserPwdResetAction() {
	}

	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		UserForm frm = (UserForm) parm1;
		// …Ë÷√“≥¬Î
		frm.getPagination().setCurrent(frm.getCurrent());
		String sql = "";

		sql = "update T_INFO_EMPLOYEE set EMPPWD=null where sid=";
		sql += frm.getSid();

		dm.update(sql, null);
	}
}
