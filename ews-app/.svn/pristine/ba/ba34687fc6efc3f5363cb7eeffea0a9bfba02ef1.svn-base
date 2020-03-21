package com.corp.oa.system.dictionary;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;
import com.corp.oa.util.G;

public class DicSaveAction implements BusinessAction {
	public DicSaveAction() {
	}

	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		SysDictionaryForm frm = (SysDictionaryForm) parm1;

		if ("true".equals(frm.getbAdd())) {// ÐÂÔö
			frm.getT_LIST_STATUS().setString("SID",
					G.getSequence("SEQ_T_IWL"));
			dm.insert(frm.getT_LIST_STATUS());
		} else {// ÐÞ¸Ä
			frm.getT_LIST_STATUS().setString("SID", frm.getSid());
			dm.update(frm.getT_LIST_STATUS());
		}

	}
}
