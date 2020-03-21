package com.corp.oa.system.dictionary;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;

public class DicEditAction implements BusinessAction {
	public DicEditAction() {
	}

	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		SysDictionaryForm frm = (SysDictionaryForm) parm1;

		//标明是修改；
		frm.setbAdd("false");
		
		String sql="select * from t_list_status where sid="+frm.getSid();
		frm.setT_LIST_STATUS(dm.findByPrimaryKey(sql,null));
	}
}


