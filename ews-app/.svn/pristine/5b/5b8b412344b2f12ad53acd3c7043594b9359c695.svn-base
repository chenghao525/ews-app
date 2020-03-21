package com.corp.oa.system.org;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;
import com.corp.oa.util.G;

public class OrgEmpEditAction implements BusinessAction {
	public OrgEmpEditAction() {
	}

	@SuppressWarnings("unchecked")
	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {

		OrgForm of = (OrgForm) parm1;
		String sql = "";

		of.setDeptAdd("false");

		String empSid = of.getEmp_sid();

		sql = "select sid as CODENO,content as CODENAME from t_list_status where infotype=2 and state=1 order by optorder";
		of.setSexList(dm.find(sql, null));

		sql = "select T.*, F_GET_EMP_DUTY_CLASS(T.SID)as DUTYCLASS,T.DUTYSID ||'_2_' as DUTY from T_INFO_EMPLOYEE T where T.sid=?";

		of.setT_INFO_EMPLOYEE(dm.findByPrimaryKey(sql, empSid));

		sql = "SELECT sid || '_' || INFO_CLASS || '_' as CODENO, "
				+ "(f_getspecstring((level-1)*2,' ') || deptname) as CODENAME FROM T_INFO_DEPT  "
				+ "where state=1 and INFO_CLASS<>3 start with PARENTDEPT=";

		empSid = of.getUserView().getUserId();
		if (!of.getUserView().isSuperBusinessRole()) {// 非超级用户
			String corp = G.getEmployeeCorp(dm, empSid);// 获取所在的公司，第二层
			if (!"".equals(corp))
				sql += corp;
		} else {
			sql += "0";
		}
		sql += " Connect By Prior SID =ParentDEPT";

		of.setOrgList(dm.find(sql, null));

		// of.setLcz_maplist(LcahDeal.Read_Lcz_Emp(dm,empSid));

	}
}
