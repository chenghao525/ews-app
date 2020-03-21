package com.corp.oa.system.org;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;

public class OrgDeptFindAction implements BusinessAction {
	
	@SuppressWarnings("unchecked")
	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		OrgForm of = (OrgForm) parm1;

		of.getPagination().setCurrent(of.getCurrent());

		String deptSid = of.getDept_sid();

		String sql = "";
		sql = "select t.* ,F_GETMAPCONTENT(DUTY_CLASS) as DUTY_CLASS from T_INFO_DEPT t where sid=?";
		// sql += deptSid;

		of.setDeptInfo(dm.find(sql, deptSid));

		of.setT_INFO_DEPT(dm.findByPrimaryKey(sql, deptSid));

	}
}
