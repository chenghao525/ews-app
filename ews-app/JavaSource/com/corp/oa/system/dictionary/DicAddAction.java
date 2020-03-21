package com.corp.oa.system.dictionary;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;

public class DicAddAction implements BusinessAction {
	public DicAddAction() {
	}

	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		SysDictionaryForm frm = (SysDictionaryForm) parm1;

		//标明是新增；
		frm.setbAdd("true");
		
		frm.getT_LIST_STATUS().setString("CONTENT","新字典项名称");
	}
}


