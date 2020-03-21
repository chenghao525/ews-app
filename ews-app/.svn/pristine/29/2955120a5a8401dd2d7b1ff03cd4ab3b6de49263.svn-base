package com.corp.oa.system.org;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;

public class OrgDutyEditAction implements BusinessAction {
	public OrgDutyEditAction() {
	}

	@SuppressWarnings("unchecked")
	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {

		OrgForm of = (OrgForm) parm1;

		of.setDeptAdd("false");

		String deptSid = of.getDept_sid();
		of.setDutyList(dm
				.find("select sid as CODENO,content as CODENAME from t_list_status where infotype=1 and state=1 order by optorder",
						null));

		String sql = "select * from T_INFO_DEPT where sid=?";

		of.setT_INFO_DEPT(dm.findByPrimaryKey(sql, deptSid));

	}

}
