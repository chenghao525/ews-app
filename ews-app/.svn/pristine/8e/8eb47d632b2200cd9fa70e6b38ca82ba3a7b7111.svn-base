package com.corp.city.ma;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

public class SaveCityMaDlvTime implements AjaxInterface {

	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);

		if (G.isNvl(key)) {
			response = G.setRespOptFailed(response, "参数错误");
			return;
		}

		if (!G.isOwnRight("10204004", request)) {
			response = G.setRespNoRight(response);
			return;
		}

		JSONArray jsa = new JSONArray(key);
		JSONObject json = null;
		DataObject dob = null;

		String cityList = "", cityNameList = "";
		String[] city = null, cityName = null;

		boolean bRemove = false;
		String sql = "delete from T_LIST_MA_CITY_DLVTIME t where t.sid=?";

		for (int i = 0; i < jsa.length(); i++) {
			json = jsa.getJSONObject(i);
			dob = new DataObject(json);

			if (!bRemove) {
				// 删除所有的
				sql = G.formatSql(sql, dob.getString("SID"));
				dm.delete(sql, null);
				bRemove = true;
			}

			cityList = dob.getString("CITY_SID");
			cityNameList = dob.getString("CITY_NAME");
			if (!G.isNvl(cityList)) {
				city = cityList.split(",");
				cityName = cityNameList.split(",");
				for (int j = 0; j < city.length; j++) {
					dob.setString("CITY_SID", city[j]);
					dob.setString("CITY_NAME", cityName[j]);
					dob.setModelName("T_LIST_MA_CITY_DLVTIME");
					dm.save(dob);
				}
			}
		}
		response.getWriter().write("");
	}
}
