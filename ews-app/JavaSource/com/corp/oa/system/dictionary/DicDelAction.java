package com.corp.oa.system.dictionary;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;

public class DicDelAction implements BusinessAction {
	public DicDelAction() {
	}

	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		SysDictionaryForm frm = (SysDictionaryForm) parm1;

		frm.getT_LIST_STATUS().setString("STATE", "0");
		frm.getT_LIST_STATUS().setString("SID", frm.getSid());
		dm.update(frm.getT_LIST_STATUS());
	}
}
