package com.corp.oa.share.image;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.bean.JSONObject;

import com.corp.basic.Constant;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

public class ImSavePicture implements AjaxInterface {

	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);

		if (G.isNvl(key)) {
			return;
		}

		JSONObject json = new JSONObject(key);

		DataObject dob = new DataObject(json);

		dob.setString("SID", dm.getSequence("SEQ_T_FILE"));
		String empSid = G.getUserIdFromSession(request);

		String path = "";
		String url = Constant.getBaseUrl();

		if (empSid != null) {
			dob.setString("CRT_EMP", empSid);
			dob.setModelName("T_INFO_IMAGE_MUTIL");

			path = dob.getString("PATH");
			if (!G.isNvl(path)) {
				dob.setString("PATH", path.replaceAll(url, ""));
			}

			dm.insert(dob);
		}

		response.getWriter().write("");
	}
}
