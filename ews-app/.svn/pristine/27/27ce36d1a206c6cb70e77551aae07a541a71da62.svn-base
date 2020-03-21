package com.corp.city.ma;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

public class GetCityMaDlvTime implements AjaxInterface {

	@SuppressWarnings("unchecked")
	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);

		if (G.isNvl(key)) {
			response = G.setRespOptFailed(response, "²ÎÊý´íÎó");
			return;
		}

		CityMaBO bo = new CityMaBO();
		DataObject dob = null;
		JSONObject json = null;
		JSONArray ja = new JSONArray();

		String ma = key;
		String sql = bo.getCityMaDlvTimeList(G.getSearchFromRequest(request));
		Iterator<DataObject> it = dm.find(sql, ma).iterator();

		while (it.hasNext()) {
			dob = it.next();
			json = dob.toJson();
			sql = G.formatSql(bo.getCityMaDlvTimeListByMaPrice(),
					new String[] { dob.getString("BOM_DLV_TIME"), ma });

			sql = "select wm_concat(CITY_SID) as CITY_SID,wm_concat(CITY_NAME) as CITY_NAME from ("
					+ sql + ")";
			dob = dm.findByPrimaryKey(sql, null);

			json.put("CITY_SID", G.nvl(dob.getString("CITY_SID"), ""));
			json.put("CITY_NAME", G.nvl(dob.getString("CITY_NAME"), ""));
			ja.put(json);
		}

		response.getWriter().write(ja.toString());
	}
}
