package com.corp.oa.system.system;

import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;

public class SystemBO {

	public String getSystemInfoList() {
		String sql = "";
		sql = "select * from T_INFO_system";

		return sql;
	}

	public String getSearchSql(String sql, DataObject dobSearch) {
		String re = "";

		String an = dobSearch.getString("S_attrname");
		String av = dobSearch.getString("S_attrvalue");
		String memo = dobSearch.getString("S_memo");

		if (!G.isNvl(an)) {
			re += " and upper(attrname) like '%" + an.toUpperCase() + "%'";
		}

		if (!G.isNvl(av)) {
			re += " and upper(attrvalue) like '%" + av.toUpperCase() + "%'";
		}

		if (!G.isNvl(memo)) {
			re += " and upper(memo) like '%" + memo.toUpperCase() + "%'";
		}

		re = "select * from (" + sql + ") where 1=1 " + re;

		return re;
	}

}
