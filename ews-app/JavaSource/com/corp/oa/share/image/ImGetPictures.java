package com.corp.oa.share.image;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.basic.Constant;
//import com.corp.bu.review.ReviewBO;
import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

public class ImGetPictures implements AjaxInterface {

	@SuppressWarnings("unchecked")
	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);

		if (G.isNvl(key)) {
			return;
		}

		JSONObject json = new JSONObject(key);
		String pid = json.getString("id");
		String type = json.getString("type");

		if (Constant.SYSTEM_KEY_STR_REVIEW.equals(type)) {
			// 如果是点评，通过taskSid获取电平表的主sid
//			ReviewBO rbo = new ReviewBO();
//			pid = rbo.getReviewSidByTaskSid(pid, dm);
		}

		ImImageBO ibo = new ImImageBO();

		String sql = ibo.getImagesListByTaskId();

		if (!G.isNvl(type)) {
			type = type.replaceAll(",", "','");
		}
		String newSql = (G.formatSql(sql, new String[] { pid, type }));

		Iterator<DataObject> it = dm.find(newSql, null).iterator();
		DataObject dob = null;
		JSONArray jsona = new JSONArray();
		json = null;

		while (it.hasNext()) {
			dob = (DataObject) it.next();
			if (!G.isNvl(dob.getString("PATH"))) {
				dob.setString("PATH", G.getImgFullPath(dob.getString("PATH")));
			}
			json = dob.toJson();
			jsona.put(json);
		}
		response.getWriter().write(jsona.toString());
	}
}
