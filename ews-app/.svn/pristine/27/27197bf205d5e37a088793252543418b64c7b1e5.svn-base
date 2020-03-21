package com.corp.oa.system.org;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;

public class OrgDutyAddAction implements BusinessAction {
	public OrgDutyAddAction() {
	}

	@SuppressWarnings("unchecked")
	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {

		OrgForm of = (OrgForm) parm1;
		of.setDutyList(dm
				.find("select sid as CODENO,content as CODENAME from t_list_status where infotype=1 and state=1 order by optorder",
						null));
		of.setDeptAdd("true");
	}

}
