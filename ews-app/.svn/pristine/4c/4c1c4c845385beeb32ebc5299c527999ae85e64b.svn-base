package com.corp.oa.share.image;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.bean.JSONObject;

import com.corp.basic.Constant;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

/**
 * 往T_INFO_IMAGE中插入一张图片
 * 
 * @author Administrator
 * 
 */

public class ImSavePicturePrim implements AjaxInterface {

	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);

		if (G.isNvl(key)) {
			return;
		}

		JSONObject json = new JSONObject(key);

		DataObject dob = new DataObject(json);

		String empSid = G.getUserIdFromSession(request);
		String path = "";
		String url = Constant.getBaseUrl();

		if (empSid != null) {

			dob.setString("CRT_EMP", empSid);

			path = dob.getString("PATH");
			if (!G.isNvl(path)) {
				dob.setString("PATH", path.replaceAll(url, ""));
				dob.setString("STATE", "1");
			} else {
				dob.setString("STATE", "0");
			}

			dob.setModelName("T_INFO_IMAGE");
			dm.save(dob);
		}

		response.getWriter().write("");
	}
}
