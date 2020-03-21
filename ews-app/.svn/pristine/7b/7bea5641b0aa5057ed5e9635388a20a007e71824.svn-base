package com.corp.city.ma;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

public class RemoveCityMaPrice implements AjaxInterface {

	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);

		if (G.isNvl(key)) {
			response = G.setRespOptFailed(response, "参数错误");
			return;
		}

		if (!G.isOwnRight("10204004", request)) {
			// 没有删除的权限
			response = G.setRespNoRight(response);
			return;
		}

		JSONObject json = new JSONObject(key);
		String cityList = json.getString("CITY_SID");
		String maSid = json.getString("SID");

		cityList = cityList.replaceAll(",", "','");

		String sql = "delete from T_LIST_MA_CITY_PRICE t where t.sid='" + maSid
				+ "' and t.CITY_sid in ('" + cityList + "')";
		dm.delete(sql, null);

		response.getWriter().write("");
	}
}
