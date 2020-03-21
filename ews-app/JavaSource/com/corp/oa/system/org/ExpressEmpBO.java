package com.corp.oa.system.org;

import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;

public class ExpressEmpBO {

	/**
	 * ����˾�����ڲ���ŷ���˾���ı��
	 * 
	 * @param dm
	 * @param driverSId
	 * @return
	 * @throws Exception
	 */
	public String getDriverID(DataManager dm, String driverSId)
			throws Exception {
		String sql = "";
		if (driverSId == null)
			return null;

		sql = "select t.empid from t_info_express_employee t where t.sid=?";
		DataObject dob = dm.findByPrimaryKey(sql, driverSId);
		if (dob != null)
			return dob.getString("EMPID");
		else
			return null;
	}

	public String getEmployeeInfoSql() {
		String sql = "select * from T_INFO_EXPRESS_EMPLOYEE where state='1' and sid=?";
		return sql;
	}
}
