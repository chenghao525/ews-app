package com.corp.oa.share.image;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

public class ImDelPicture implements AjaxInterface {

	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);

		if (G.isNvl(key)) {
			return;
		}

		ImImageBO ibo = new ImImageBO();

		String sql = ibo.getImageInfoBySid();

		DataObject dob = dm.findByPrimaryKey(sql, key);
		if (dob != null) {
			String sid = dob.getString("SID");
			sql = "update T_INFO_IMAGE_MUTIL t set t.state='0' where t.sid='" + sid + "'";
			dm.update(sql, null);
		}
		response.getWriter().write("");
	}
}
