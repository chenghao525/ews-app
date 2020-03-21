package com.corp.city;

import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;

public class CityBO {

	public String getCityInfo(String sid) {
		String sql = "select a.*,c.city_pinyin,c.city_short from "
				+ "(select t.* from t_list_status t where t.infotype='16' and t.sid=?) a"
				+ " left join t_info_city c on a.content=c.city_full_name";
		return G.formatSql(sql, new String[] { sid });
	}

	public String getInfoCityTabInfo(String cityName) {
		String sql = "select * from t_info_city t where t.city_full_name=?";
		return G.formatSql(sql, new String[] { cityName });
	}

	// 根据城市名称返回城市不能做的服务项目
	public String getCityNoServiceList(String cityName, DataManager dm) throws Exception {
		String sql = "select t.params from t_list_status t"
				+ " where infotype=16 and t.content=? and rownum=1";
		DataObject dob = dm.findByPrimaryKey(sql, cityName);
		if (dob == null)
			return "";

		String p = dob.getString("PARAMS");
		if (G.isNvl(p))
			return "";

		JSONObject json = new JSONObject(p);
		if (!json.has("NO_SERVICE")) {
			return "";
		}
		return G.nvl(json.getString("NO_SERVICE"), "");
	}
	
	//根据区id获取省市id
	public String getProvinceCityByRegion(String region) {
		String sql = "select sid as city,parent as province from T_LIST_STATUS t where state=1 and sid=(select parent from T_LIST_STATUS where state=1 and sid=?)";
		return G.formatSql(sql, new String[] { region });
	}

}
