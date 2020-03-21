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

public class GetDicList4MU implements AjaxInterface {

	@SuppressWarnings("unchecked")
	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		String data = request.getParameter(AJAX_PARAMETER_KEY_NAME);
		if (!G.isNvl(data)) {
			data = data.toUpperCase();
		}

		JSONObject jsPara = new JSONObject(data);

		// T_LIST_STATUS��INFOTYPE�ֶ�
		String infotype = jsPara.getString("INFOTYPE");

		// T_LIST_STATUS��Parent�ֶ�
		String parent = jsPara.getString("PARENT");

		// T_LIST_STATUS�������ַ���
		String orderStr = jsPara.getString("ORDER");

		String filter = jsPara.getString("SID");

		String sql = "select sid as id,content as text from T_LIST_STATUS"
				+ " where state='1' and infotype in(" + infotype + ")";

		if (!G.isNvl(parent))
			sql += " and parent='" + parent + "'";
		if (!G.isNvl(filter)) {
			sql += " and instr('" + filter + "', sid) = 0";
		}

		if (G.isNvl(orderStr))
			sql += " order by optorder";
		else
			sql += " order by " + G.getNlssort(orderStr);

		Iterator<DataObject> it = dm.find(sql, null).iterator();
		DataObject dob = null;
		JSONArray jsona = new JSONArray();
		JSONObject json = null;
		while (it.hasNext()) {
			dob = it.next();
			json = new JSONObject();
			// �˴�����miniui��Ҫ����Ҫʹ��Сд
			json.put("id", dob.getString("ID"));
			json.put("text", dob.getString("TEXT"));
			jsona.put(json);
		}
		response.getWriter().write(jsona.toString());
	}
}
