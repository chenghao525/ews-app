package com.corp.oa.system.org;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.exception.GeneralException;
import com.corp.oa.util.G;

public class OrgDeptSaveAction implements BusinessAction {
	public OrgDeptSaveAction() {
	}

	public void doExecute(BusinessForm parm1, DataManager dm) throws java.lang.Exception {
		OrgForm of = (OrgForm) parm1;

		// 父部门的内部编号
		String deptParent = of.getParentDept();

		// 保存最终的编号,已被跳转页面使用
		String deptSid = "";

		if ("true".equals(of.getDeptAdd())) { // 插入信息的部门信息
			String sSql = "select * from T_INFO_DEPT where STATE=1 and DEPTNAME=?"
					+ " and INFO_CLASS=1 and PARENTDEPT=?";
			DataObject dob = dm.findByPrimaryKey(sSql, new String[] {
					of.getT_INFO_DEPT().getString("DEPTNAME"), deptParent });
			// 子单位/部门名称重复，请另填写名称！
			if (dob != null)
				throw new GeneralException("子单位/部门名称重复，请另填写名称！");

			deptSid = G.getSequence("SEQ_T_IWL", dm);

			of.getT_INFO_DEPT().setString("SID", deptSid);
			of.getT_INFO_DEPT().setString("DEPTID", deptSid);
			of.getT_INFO_DEPT().setString("CRITICAL_VALUE", "1");
			of.getT_INFO_DEPT().setString("PARENTDEPT", deptParent);
			of.getT_INFO_DEPT().setInt("INFO_CLASS", 1);

			dm.insert(of.getT_INFO_DEPT());

		} else { // 修改新的部门信息
			deptSid = of.getDept_sid();
			String sSql = "select * from T_INFO_DEPT where STATE=1 and DEPTNAME=?"
					+ " and INFO_CLASS=1 and PARENTDEPT=? and sid<>?";
			DataObject dob = dm.findByPrimaryKey(sSql, new String[] {
					of.getT_INFO_DEPT().getString("DEPTNAME"), deptParent, deptSid });
			if (dob != null)
				// 子单位/部门名称重复，请另填写名称！
				throw new GeneralException("子单位/部门名称重复，请另填写名称！");

			of.getT_INFO_DEPT().setString("SID", deptSid);
			// of.getT_INFO_DEPT().setString("DEPT_CITY", of.getParent_city());

			dm.update(of.getT_INFO_DEPT());

		}

		of.setTransInfo(deptSid);
	}

}
