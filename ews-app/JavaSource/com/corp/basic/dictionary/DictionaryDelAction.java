package com.corp.basic.dictionary;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;

public class DictionaryDelAction implements BusinessAction {

	public void doExecute(BusinessForm parm1, DataManager dm) throws java.lang.
    Exception {
		
		DictionaryForm frm = (DictionaryForm)parm1;
		//		…Ë÷√“≥¬Î
		frm.getPagination().setCurrent(frm.getCurrent());
		
		String sql = "";
		
		sql="update T_MAP_TRANS_DIC set STATE=0 where sid=?";
		
		dm.update(sql,new String[]{frm.getSid()+""});	
	}

}
