package com.corp.oa.system.role;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.corp.oa.util.G;

public class RoleUtils {

	protected final static Log logger = LogFactory.getLog(RoleUtils.class);

	public RoleUtils() {
	}

	// 获得角色信息列表
	public String getRoleDicListSql(String empSid) {

		String sql = "select a.rolename as text,roleid as id from t_right_roledic a where a.state='1'"
				+ " and a.roleid in (select roleid from t_right_userrole where empid="
				+ empSid
				+ ") order by " + G.getNlssort("rolename");
		return sql;
	}
}
