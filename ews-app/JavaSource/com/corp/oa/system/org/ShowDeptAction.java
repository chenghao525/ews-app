package com.corp.oa.system.org;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;

public class ShowDeptAction implements BusinessAction {
	public ShowDeptAction() {
	}

	@SuppressWarnings("unchecked")
	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		OrgForm of = (OrgForm) parm1;
		String strSql = "select sid,deptid,parentdept,info_class,"
				+ "case info_class when 3 then F_GETEMPNAME(deptid) else F_GET_DEPT_NAME(sid) end as deptname from T_INFO_DEPT where state=1 and parentdept=? order by deptrank";
		of.setDutyList(dm.find(strSql, of.getDept_sid()));

	}

}
