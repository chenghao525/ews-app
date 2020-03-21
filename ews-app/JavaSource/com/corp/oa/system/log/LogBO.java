package com.corp.oa.system.log;

import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;

public class LogBO {

	public String getLogList() {
		return "select SID,OPTEMPID,LOGTYPE,OPTTYPE,OPTATTR,OPTTIME,OPTSECONDS"
				+ " from T_INFO_LOG t";
	}

	public String getSearchSql(String sql, DataObject dobSearch) {
		String re = "";

		String empId = dobSearch.getString("S_OPTEMPID");
		String optType = dobSearch.getString("S_OPTTYPE");
		String st = dobSearch.getString("S_OPTTIME_START");
		String se = dobSearch.getString("S_OPTTIME_END");
		String desc = dobSearch.getString("S_OPTATTR");

		if (!G.isNvl(empId)) {
			re += " and OPTEMPID = '" + empId + "'";
		}
		if (!G.isNvl(optType)) {
			re += " and OPTTYPE = '" + optType + "'";
		}
		if (!G.isNvl(st)) {
			re += " and OPTTIME>='" + st + "'";
		}
		if (!G.isNvl(se)) {
			re += " and OPTTIME<='" + se + " 24:00:00'";
		}

		if (!G.isNvl(desc)) {
			re += " and OPTATTR like '%" + G.rqm(desc) + "%'";
		}
		re = "select * from (" + sql + ") where 1=1 " + re;

		return re;
	}

}
