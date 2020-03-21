package com.corp.servlet.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.core.dao.DataManager;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

public class RemoveCommonList implements AjaxInterface {

	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);

		if (G.isNvl(key)) {
			response = G.setRespOptFailed(response, "参数错误");
			return;
		}

		String rightId = "19906001,19905003,10202005", tabName = "T_INFO_SYSTEM";

		if (!G.isOwnRight(rightId, request)) {
			// 没有保存的权限
			response = G.setRespNoRight(response);
			return;
		}

		String[] sidArr = key.split(",");
		// String empSid = G.getUserIdFromSession(request);
		String sql = "";

		for (int i = 0; i < sidArr.length; i++) {
			if (!G.isNvl(sidArr[i])) {
				if (G.isNvl(tabName)) {

				} else {
					// dob = new DataObject(tabName);

					sql = String.format("update %s set state='0' where sid='%s'", tabName,
							sidArr[i]);
					dm.update(sql, null);

				}
			}
		}

		response.getWriter().write("");
	}
}
