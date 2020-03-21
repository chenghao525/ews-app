package com.corp.sms;

import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;

public class SmsBO {

	/**
	 * 返回单条短信息的 sql
	 * 
	 * @param sid
	 * @return sql
	 */
	public String getSMSInfoSql(String sid) {
		String sql = "select t.* from t_sms_send t where t.sid='" + sid + "'";
		return sql;
	}

	/**
	 * 返回短信的list sql
	 * 
	 * @return
	 */
	public String getSMSListSql() {
		String sql = "select t.* from t_sms_send t";
		return sql;
	}

	public String getSMSHisListSql() {
		String sql = "select t.* from t_sms_send_his t";
		return sql;
	}

	public String getSearchSql(String sql, DataObject dobSearch) {
		String re = "";

		String custId = dobSearch.getString("S_REC_PHONE");
		String custIdSlow = dobSearch.getString("S_REC_PHONE_S");
		String content = dobSearch.getString("S_SMS_CONTENT");
		String reState = dobSearch.getString("S_RETURN_STATE");
		String crtTimeS = dobSearch.getString("S_CRT_TIME_START");
		String crtTimeE = dobSearch.getString("S_CRT_TIME_END");

		if (!G.isNvl(custId)) {
			re += " and REC_PHONE like '" + G.rqm(custId) + "%'";
		} else if (!G.isNvl(custIdSlow)) {
			re += " and instr(REC_PHONE,'" + G.rqm(custIdSlow) + "')>0";
		}
		
		if (!G.isNvl(content)) {
			content = content.toUpperCase().trim();
			re += " and instr(upper(SMS_CONTENT),'" + G.rqm(content) + "')>0";
		}
		if (!G.isNvl(reState)) {
			reState = reState.toUpperCase().trim();
			re += " and instr(upper(RETURN_STATE),'" + G.rqm(reState) + "')>0";
		}

		if (!G.isNvl(crtTimeS)) {
			re += " and CRT_TIME>='" + crtTimeS + "'";
		}

		if (!G.isNvl(crtTimeE)) {
			re += " and CRT_TIME<='" + crtTimeE + " 24:00:00'";
		}

		re = "select * from (" + sql + ") where 1=1 " + re;

		return re;
	}

}
