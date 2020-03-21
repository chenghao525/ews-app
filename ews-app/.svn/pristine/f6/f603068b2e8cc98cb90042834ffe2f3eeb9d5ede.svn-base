package com.corp.city.ma;

import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;

public class CityMaBO {

	public String getCityMaPriceList(DataObject dobSearch) {
		// 此方法Oracle 9i 不支持
		// return "select t.sid,wmsys.wm_concat(t.city_sid) as city_sid,"
		// + " wmsys.wm_concat(t.city_name) as city_name,t.man_hour_price"
		// +
		// " from t_list_ma_city_price t where t.sid=? group by t.sid,t.man_hour_price";

		// 此方法Oracle 9i 不支持
		// return
		// "select t.sid,dbms_lob.substr(rtrim(xmlagg(xmlparse(content city_sid || ',' wellformed)).getclobval(),"
		// +
		// " ','),4000,1),dbms_lob.substr(rtrim(xmlagg(xmlparse(content city_name || ',' wellformed)).getclobval(),"
		// +
		// " ','),4000,1),t.man_hour_price from t_list_ma_city_price t where t.sid=?"
		// + " group by t.sid,t.man_hour_price";

		String sql = " select t.sid,t.man_hour_price,t.man_hour_price_2b,t.a_class_price,t.a_class_price_2b,"
				+ " t.b_class_price,t.b_class_price_2b,t.c_class_price,t.c_class_price_2b,"
				+ " t.d_class_price,t.d_class_price_2b,t.ai_class_price,t.ai_class_price_2b"
				+ " from t_list_ma_city_price t where t.sid=?";

		String cityName = dobSearch.getString("S_CITY_NAME");
		if (!G.isNvl(cityName)) {
			sql += " and city_name like '%" + G.rqm(cityName) + "%'";
		}

		sql += " group by t.sid,t.man_hour_price,t.man_hour_price_2b,"
				+ " t.a_class_price,t.a_class_price_2b,t.b_class_price,t.b_class_price_2b,"
				+ " t.c_class_price,t.c_class_price_2b,t.d_class_price,t.d_class_price_2b,"
				+ " t.ai_class_price,t.ai_class_price_2b";

		return sql;
	}

	public String getCityMaPriceListByMaPrice() {
		return "select * from t_list_ma_city_price t where t.man_hour_price=?"
				+ " and t.man_hour_price_2b=? and t.a_class_price=? and t.a_class_price_2b=?"
				+ " and t.b_class_price=? and t.b_class_price_2b=?"
				+ " and t.c_class_price=? and t.c_class_price_2b=?"
				+ " and t.d_class_price=? and t.d_class_price_2b=?"
				+ " and t.ai_class_price=? and t.ai_class_price_2b=? and t.sid=?";
	}

	public String getCityMaDlvTimeList(DataObject dobSearch) {
		String sql = " select t.sid,t.BOM_DLV_TIME from T_LIST_MA_CITY_DLVTIME t where t.sid=?";
		String cityName = dobSearch.getString("S_CITY_NAME");
		if (!G.isNvl(cityName)) {
			sql += " and city_name like '%" + G.rqm(cityName) + "%'";
		}
		sql += " group by t.sid,t.BOM_DLV_TIME";
		return sql;
	}

	public String getCityMaDlvTimeListByMaPrice() {
		return "select * from T_LIST_MA_CITY_DLVTIME t where t.BOM_DLV_TIME=? and t.sid=?";
	}
}
