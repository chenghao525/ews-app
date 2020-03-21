package com.corp.oa.system.dictionary;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

public class GetDicList implements AjaxInterface {

	@SuppressWarnings("unchecked")
	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		String data = request.getParameter(AJAX_PARAMETER_KEY_NAME);
		
		JSONObject jsPara = new JSONObject(data);
		DataObject dob = new DataObject(jsPara);

		// T_LIST_STATUSµÄINFOTYPE×Ö¶Î
		String infotype = dob.getString("INFOTYPE");

		// T_LIST_STATUSµÄParent×Ö¶Î
		String parent = dob.getString("PARENT");

		String sql = "select sid,content from T_LIST_STATUS" + " where state='1' and infotype='"
				+ infotype + "'";

		if (!G.isNvl(parent))
			sql += " and parent='" + parent + "'";

		sql += " order by optorder";

		Iterator<DataObject> it = dm.find(sql, null).iterator();
		dob = null;
		JSONArray jsona = new JSONArray();
		while (it.hasNext()) {
			dob = it.next();
			jsona.put(dob.toJson());
		}
		response.getWriter().write(jsona.toString());
	}
}
