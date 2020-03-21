package com.corp.oa.system.org;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;

public class OrgDeptAddAction implements BusinessAction {
	public OrgDeptAddAction() {
	}

	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		OrgForm of = (OrgForm) parm1;

		of.setDeptAdd("true");

		// 设置父节点
		of.setParentDept(of.getDept_sid());

		of.getT_INFO_DEPT().setString("DEPTNAME", "单位/部门名称");
	}
}
