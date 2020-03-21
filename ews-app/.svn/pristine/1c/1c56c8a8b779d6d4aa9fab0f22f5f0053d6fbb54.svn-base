package com.corp.oa.system.right;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.corp.oa.util.G;

public class RightUtils {

	protected final static Log logger = LogFactory.getLog(RightUtils.class);

	public RightUtils() {
	}

	// 获得角色的人员列表
	public String getRightEmpListByRoleSql(String roleId, String empSid) {
		String sql = "select * from (select SID,F_EMP_HAS_ROLE(sid,?) as ismyright"
				+ " from T_INFO_EMPLOYEE A where STATE=1 and sid<>? and sid<>0 and EMP_SRC='ews') where ismyright=1";
		sql = G.formatSql(sql, new String[] { roleId, empSid });
		return sql;
	}

	// 获得员工的角色列表
	public String getRightRoleListByEmpSql(String empSet, String empSid) {
		String sql = "select * from t_right_userrole r where r.empid=?"
				+ " and r.roleid in (select roleid from t_right_userrole where empid=?)";
		sql = G.formatSql(sql, new String[] { empSet, empSid });
		return sql;
	}
}
