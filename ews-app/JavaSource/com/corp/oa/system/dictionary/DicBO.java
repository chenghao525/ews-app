package com.corp.oa.system.dictionary;

import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;

public class DicBO {

	public String getDicList() {
		return "select t.* from t_list_status t where t.state='1'";
	}

	public String getSearchSql(String sql, DataObject dobSearch) {
		String re = "";

		String sid = dobSearch.getString("S_SID");
		String content = dobSearch.getString("S_CONTENT");
		String infoType = dobSearch.getString("S_INFOTYPE");
		String refType = dobSearch.getString("S_REFTYPE");
		String keyValue = dobSearch.getString("S_KEYVALUE");
		String memo = dobSearch.getString("S_MEMO");
		String parent = dobSearch.getString("S_PARENT");
		String code = dobSearch.getString("S_CODE");
		String params = dobSearch.getString("S_PARAMS");

		if (!G.isNvl(sid)) {
			re += " and sid='" + G.rqm(sid) + "'";
		}
		if (!G.isNvl(content)) {
			re += " and content like '%" + G.rqm(content)
					+ "%'";
		}
		if (!G.isNvl(infoType)) {
			re += " and infoType ='" + G.rqm(infoType) + "'";
		}
		if (!G.isNvl(refType)) {
			re += " and refType = '" + G.rqm(refType) + "'";
		}
		if (!G.isNvl(keyValue)) {
			re += " and keyValue like '%" + G.rqm(keyValue)
					+ "%'";
		}
		if (!G.isNvl(memo)) {
			re += " and memo like '%" + G.rqm(memo) + "%'";
		}
		if (!G.isNvl(parent)) {
			re += " and parent = '" + G.rqm(parent) + "'";
		}
		if (!G.isNvl(code)) {
			re += " and code like '%" + G.rqm(code) + "%'";
		}
		if (!G.isNvl(params)) {
			re += " and params like '%" + G.rqm(params)
					+ "%'";
		}

		re = "select * from (" + sql + ") where 1=1 " + re;

		return re;
	}
}
