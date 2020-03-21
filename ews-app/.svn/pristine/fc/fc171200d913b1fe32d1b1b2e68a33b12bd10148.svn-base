package com.corp.oa.system.org;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.exception.GeneralException;

public class OrgDeptDelAction implements BusinessAction {
	public OrgDeptDelAction() {
	}

	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		OrgForm of = (OrgForm) parm1;

		String deptSid = of.getDept_sid();

		String sql = "";
		sql = "select * from t_info_dept where PARENTDEPT=? and state='1'";

		DataObject dob = dm.findByPrimaryKey(sql, deptSid);
		// 节点下还有子信息，不能删除！
		if (dob != null)
			throw new GeneralException("节点下还有信息，不能删除！");

		// 根节点不能删除！
		if ("1".equals(deptSid))
			throw new GeneralException("根节点不能删除！");

		// 删除此部门下的所有手持终端的同步信息
		// G.insertEmployeeSyncInfo(deptSid, dm, true);

		// G.insertDictionarySyncInfo(dm, true, of.getT_INFO_DEPT()
		// .getString("DEPT_CITY"), deptSid, true);
		// 否则不更新字典数据

		sql = "Update  t_info_dept set STATE='0' where sid=?";
		dm.update(sql, deptSid);

		// 删除该节点后,跳转到该节点的父节点
		sql = "select PARENTDEPT from t_info_dept where sid=?";
		of.setTransInfo(dm.findByPrimaryKey(sql, deptSid).getString(
				"PARENTDEPT"));

	}

}
