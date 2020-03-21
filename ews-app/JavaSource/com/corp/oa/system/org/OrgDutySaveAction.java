package com.corp.oa.system.org;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.exception.GeneralException;
import com.corp.oa.util.G;

public class OrgDutySaveAction implements BusinessAction {
	public OrgDutySaveAction() {
	}

	public void doExecute(BusinessForm parm1, DataManager dm) throws java.lang.Exception {

		OrgForm of = (OrgForm) parm1;

		// 父部门的内部编号
		String deptP = of.getDept_sid();

		// 保存最终的编号,已被跳转页面使用
		String sLastID = "";

		if ("true".equals(of.getDeptAdd())) {// 插入新的职务信息
			String sSql = "select * from T_INFO_DEPT where STATE=1 and DEPTNAME=?"
					+ " and INFO_CLASS=2 and PARENTDEPT=?";
			com.corp.frame.core.dao.DataObject dob = dm.findByPrimaryKey(sSql, new String[] {
					of.getT_INFO_DEPT().getString("DEPTNAME"), deptP });
			// 职务名称重复，请另填写名称！
			if (dob != null)
				throw new GeneralException("error.DutyRepeat");

			sLastID = G.getSequence("SEQ_T_IWL", dm);

			of.getT_INFO_DEPT().setString("SID", sLastID);
			of.getT_INFO_DEPT().setInt("CRITICAL_VALUE", 1);
			of.getT_INFO_DEPT().setString("DEPTID", sLastID);
			of.getT_INFO_DEPT().setInt("PARENTDEPT", Integer.parseInt(deptP));
			of.getT_INFO_DEPT().setInt("INFO_CLASS", 2);

			dm.insert(of.getT_INFO_DEPT());
		} else {// 修改职务信息
			String sSql = "select * from T_INFO_DEPT where STATE=1 and DEPTNAME=?"
					+ " and INFO_CLASS=1 and PARENTDEPT=(select PARENTDEPT from T_INFO_DEPT where sid=?) and sid<>?";

			DataObject dob = dm.findByPrimaryKey(sSql, new String[] {
					of.getT_INFO_DEPT().getString("DEPTNAME"), deptP, deptP });
			// 职务名称重复，请另填写名称！
			if (dob != null)
				throw new GeneralException("error.DutyRepeat");

			of.getT_INFO_DEPT().setInt("SID", Integer.parseInt(deptP));

			dm.update(of.getT_INFO_DEPT());

			sLastID = deptP;

		}
		of.setTransInfo(sLastID);
	}

}
