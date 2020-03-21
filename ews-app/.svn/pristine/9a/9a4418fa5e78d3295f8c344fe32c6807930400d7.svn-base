package com.corp.oa.system.org;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;
import com.corp.oa.util.G;

public class OrgEmpAddAction implements BusinessAction {
	
	@SuppressWarnings("unchecked")
	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {

		OrgForm of = (OrgForm) parm1;

		String empSid = of.getUserView().getUserId();

		String sql = "SELECT sid || '_' || INFO_CLASS || '_' as CODENO, "
				+ "(f_getspecstring((level-1)*2,' ') || deptname) as CODENAME FROM T_INFO_DEPT  "
				+ "where state=1 and INFO_CLASS<>3 start with PARENTDEPT=";
		if (!of.getUserView().isSuperBusinessRole()) {// 非超级用户
			String corp = G.getEmployeeCorp(dm, empSid);// 获取所在的公司，第二层
			if (!"".equals(corp))
				sql += corp;
		} else {
			sql += "0";
		}

		sql += " Connect By Prior SID=ParentDEPT";

		of.setDeptAdd("true");
		of.setSexList(dm
				.find("select sid as CODENO,content as CODENAME from t_list_status where infotype=2 and state=1 order by optorder",
						null));
		of.setOrgList(dm.find(sql, null));

		sql = "select duty_class from T_INFO_DEPT where sid=?";
		String dutyClass = dm.findByPrimaryKey(sql, of.getDept_sid())
				.getString("duty_class");

		of.setDutyClass(dutyClass);
	}
}
