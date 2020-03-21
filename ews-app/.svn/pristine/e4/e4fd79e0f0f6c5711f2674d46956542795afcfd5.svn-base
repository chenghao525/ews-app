package com.corp.provider;

import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;

public class ProviderBO {

	public String getProvInfoBySid() {
		return "select * from T_INFO_PROVIDER t where sid=?";
	}

	public String getProvList() {
		return "select p.*,f_getmapcontent(city) as city_name,"
				+ " f_getmapcontent(p.province) as province_name from T_INFO_PROVIDER p"
				+ " where state='1'";
	}

	public String getRemoveProvBrandSql() {
		return "delete from t_info_prov_brand_map t where t.prov_sid=?";
	}

	public String getProvBrandListSql(String provSid) {
		String sql = G.formatSql("select t.* from T_INFO_PROV_BRAND_MAP t"
				+ " where (t.car_level<=2 or t.car_level is null) and t.prov_sid=?", provSid);
		return sql;
	}

	public String getProvTreeInfoByProvUuidSql() {
		return "select t.* from t_wp_prov_tree t where t.PROV_TAB='yes' and t.sid=?";
	}

	public String getProvUuidBySid() {
		return "select * from t_wp_prov_tree t where t.state='1' and t.prov_tab='yes' and t.prov_sid=?";
	}

	public String getSearchSql(String sql, DataObject dobSearch) {
		String re = "";

		String sid = dobSearch.getString("S_SID");
		String provName = dobSearch.getString("S_PROV_NAME");
		String provNameSum = dobSearch.getString("S_PROV_NAME_SUM");
		String cityName = dobSearch.getString("S_CITY_NAME");
		String prvnName = dobSearch.getString("S_PROVINCE_NAME");
		String provType = dobSearch.getString("S_PROV_TYPE");
		String provId = dobSearch.getString("S_PROV_ID");
		String addr = dobSearch.getString("S_ADDR");
		String srvType = dobSearch.getString("S_SRV_TYPE");

		if (!G.isNvl(sid)) {
			re += " and sid like '" + G.rqm(sid) + "%'";
		}
		if (!G.isNvl(provName)) {
			re += " and instr(prov_name,'" + G.rqm(provName) + "')>0";
		}
		if (!G.isNvl(provId)) {
			re += " and instr(prov_id,'" + G.rqm(provId) + "')>0";
		}
		if (!G.isNvl(addr)) {
			re += " and instr(Addr,'" + G.rqm(addr) + "')>0";
		}
		if (!G.isNvl(srvType)) {
			re += " and SRV_TYPE= '" + srvType + "'";
		}
		if (!G.isNvl(provNameSum)) {
			re += " and instr(prov_name_sum,'" + G.rqm(provNameSum) + "')>0";
		}
		if (!G.isNvl(prvnName)) {
			re += " and PROVINCE_NAME like '" + G.rqm(prvnName) + "%'";
		}
		if (!G.isNvl(cityName)) {
			re += " and city_name like '" + G.rqm(cityName) + "%'";
		}
		if (!G.isNvl(provType)) {
			re += " and instr(','||prov_type||',','," + provType + ",')>0";
		}

		re = "select * from (" + sql + ") where 1=1 " + re;

		return re;
	}
}
