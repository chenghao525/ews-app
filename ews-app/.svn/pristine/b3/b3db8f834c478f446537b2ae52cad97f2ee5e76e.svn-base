package com.corp.servlet.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

public class SaveCommonList implements AjaxInterface {

	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);

		if (G.isNvl(key)) {
			response = G.setRespOptFailed(response, "参数错误");
			return;
		}

		String rightId = "19906001,19905004", tabName = "T_INFO_SYSTEM";

		if (!G.isOwnRight(rightId, request)) {
			// 没有保存的权限
			response = G.setRespNoRight(response);
			return;
		}

		JSONArray jsa = new JSONArray(key);
		JSONObject json = null;
		DataObject dob = null;

		for (int i = 0; i < jsa.length(); i++) {
			json = jsa.getJSONObject(i);
			dob = new DataObject(json);

			dob.setModelName(tabName);
			dob.setString("UPT_TIME", G.getCurrentTime());
			dob.setString("UPT_DATE", G.getCurrentTime());
			dob.setString("UPTDATE", G.getCurrentTime());
			dm.save(dob);

		}
		response.getWriter().write("");
	}
}
