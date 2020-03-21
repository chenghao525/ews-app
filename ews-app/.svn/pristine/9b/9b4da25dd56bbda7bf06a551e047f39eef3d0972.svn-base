package com.corp.city;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

public class SaveCityInfo implements AjaxInterface {

	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);

		if (G.isNvl(key)) {
			response = G.setRespOptFailed(response, "参数错误");
			return;
		}

		CityBO bo = new CityBO();
		DataObject dob = null;
		JSONObject json = new JSONObject(key);

		String sid = json.getString("SID");
		String sql = bo.getCityInfo(sid);
		dob = dm.findByPrimaryKey(sql, null);

		if (dob == null) {
			response = G.setRespOptFailed(response, "数据错误");
			return;
		}
		String cityName = dob.getString("CONTENT");

		JSONObject js = new JSONObject();
		js.put("BU_OPEN", json.has("BU_OPEN") ? json.getString("BU_OPEN") : "");
		js.put("BU_COMM", json.has("BU_COMM") ? json.getString("BU_COMM") : "");
		js.put("NO_SERVICE", json.has("NO_SERVICE") ? json.getString("NO_SERVICE") : "");
		js.put("PY", (json.has("HEAD_PY") ? json.getString("HEAD_PY") : "").toUpperCase());
		js.put("CITY", "yes");
		js.put("ASSESS_CEIL", json.has("ASSESS_CEIL") ? json.getString("ASSESS_CEIL") : "");
		js.put("CARID_HEAD", json.has("CARID_HEAD") ? json.getString("CARID_HEAD") : "");
		
		dob.setModelName("T_LIST_STATUS");
		dob.setString("PARAMS", js.toString());

		dm.update(dob);

		// 继续更新info_city表
		sql = bo.getInfoCityTabInfo(cityName);
		dob = dm.findByPrimaryKey(sql, null);
		if (dob == null) {
			dob = new DataObject("T_INFO_CITY");
			sql = "select max(sid)+1 as sid from t_info_city t";
			DataObject dobCity = dm.findByPrimaryKey(sql, null);
			dob.setInt("SID", dobCity.getInt("SID"));
			dob.setString("CITY_NAME", cityName);
			dob.setString("CITY_SHORT", json.getString("HEADALL_PY").toLowerCase());
			dob.setString("CITY_PINYIN", json.getString("ALL_PY").toLowerCase());
			dob.setString("CITY_FULL_NAME", cityName);
			dm.insert(dob);
		} else {
			dob.setModelName("T_INFO_CITY");
			dob.setString("CITY_SHORT", json.getString("HEADALL_PY").toLowerCase());
			dob.setString("CITY_PINYIN", json.getString("ALL_PY").toLowerCase());
			dob.setString("CITY_FULL_NAME", cityName);
			dm.update(dob);
		}

		response.getWriter().write("");
	}
}
