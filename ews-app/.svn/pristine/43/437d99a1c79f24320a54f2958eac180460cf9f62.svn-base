package com.corp.city;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

public class GetCityInfo implements AjaxInterface {

	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);

		if (G.isNvl(key)) {
			response = G.setRespOptFailed(response, "参数错误");
			return;
		}

		CityBO bo = new CityBO();
		DataObject dob = null;
		JSONObject json = new JSONObject();

		String sql = bo.getCityInfo(key);
		dob = dm.findByPrimaryKey(sql, null);

		if (dob == null) {
			response.getWriter().write("");
			return;
		}

		json.put("CITY_NAME", dob.getString("CONTENT"));
		json.put("HEADALL_PY", G.nvl(dob.getString("CITY_SHORT"), "").toLowerCase());
		json.put("ALL_PY", G.nvl(dob.getString("CITY_PINYIN"), "").toLowerCase());

		// 先默认
		json.put("BU_OPEN", "no");
		json.put("BU_COMM", "no");
		json.put("NO_SERVICE", "");
		json.put("HEAD_PY", "");
		json.put("ASSESS_CEIL", "0");
		json.put("CARID_HEAD", "");

		String p = dob.getString("PARAMS");
		if (!G.isNvl(p)) {
			try {
				JSONObject pr = new JSONObject(p);
				// 先默认
				json.put("BU_OPEN", pr.has("BU_OPEN") ? pr.getString("BU_OPEN") : "");
				json.put("BU_COMM", pr.has("BU_COMM") ? pr.getString("BU_COMM") : "");
				json.put("NO_SERVICE", pr.has("NO_SERVICE") ? pr.getString("NO_SERVICE") : "");
				json.put("HEAD_PY", pr.has("PY") ? pr.getString("PY").toUpperCase() : "");
				json.put("ASSESS_CEIL", pr.has("ASSESS_CEIL") ? pr.getString("ASSESS_CEIL") : "0");
				json.put("CARID_HEAD", pr.has("CARID_HEAD") ? pr.getString("CARID_HEAD") : "");
			} catch (Exception e) {
			}
		}

		response.getWriter().write(json.toString());
	}
}
