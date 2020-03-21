package com.corp.oa.system.system;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;

public class SysInfoAction implements BusinessAction {

	@SuppressWarnings("unchecked")
	public void doExecute(BusinessForm arg0, DataManager dm) throws Exception {
		SysForm of = (SysForm) arg0;

		String sql = "";
		sql = "select * from t_info_system t order by attrname";

		of.setInfolist(dm.find(sql, null));

	}

}
