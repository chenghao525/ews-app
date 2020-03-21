package com.corp.oa.system.org;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.exception.GeneralException;

public class OrgEmpDelAction implements BusinessAction {
	public OrgEmpDelAction() {
	}

	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {

		OrgForm of = (OrgForm) parm1;

		String empSid = of.getEmp_sid();

		// 父节点的编号
		String deptP = of.getDept_sid();

		String sql = "";
		// 不能删除管理员账号！
		if ("0".equals(empSid))
			throw new GeneralException("error.DelAdmin");
		// 不能删除当前登录的用户！
		if (of.getUserView().getUserId().equals(empSid))
			throw new GeneralException("error.DelCurrentUser");

		sql = "Update  t_info_dept set STATE='0' where DEPTID=? and PARENTDEPT=?";
		dm.update(sql, new String[] { empSid, deptP });

		sql = "Update T_INFO_EMPLOYEE set STATE='0' where sid=?";
		dm.update(sql, empSid);

		// 删除该节点后,跳转到该节点的父节点
		of.setTransInfo(deptP);

	}

}
