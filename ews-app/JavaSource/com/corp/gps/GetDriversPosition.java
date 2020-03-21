package com.corp.gps;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.bean.JSONObject;

import com.corp.frame.core.dao.DataManager;
import com.corp.oa.util.Json;
import com.corp.servlet.AjaxInterface;

public class GetDriversPosition implements AjaxInterface {

	public void doExecute(HttpServletRequest request,
			HttpServletResponse response,DataManager dm) throws Exception {

		String r = request.getParameter(AJAX_PARAMETER_KEY_NAME);

		JSONObject json = new JSONObject(r);
		System.out.println("json===" + json.toString());
		response.getWriter().write(Json.Encode(json));

	}
}
