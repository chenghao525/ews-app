package com.corp.city;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

//获取区域名字列表
public class GetRegionList4MU implements AjaxInterface {

	@SuppressWarnings("unchecked")
	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {
		// if (!G.isOwnRight("10204004", request)) {
		// response = G.setRespNoRight(response);
		// return;
		// }
		String data = request.getParameter(AJAX_PARAMETER_KEY_NAME);

		JSONObject jsPara = new JSONObject(data);

		// T_LIST_STATUS的INFOTYPE字段
		// String infotype = jsPara.getString("INFOTYPE");

		// T_LIST_STATUS的Parent字段
		String parent = jsPara.getString("PARENT");

		// T_LIST_STATUS的排序字符串
		String orderStr = jsPara.getString("ORDER");

		String sql = "select sid as id,(select content from t_list_status a where a.sid=t.parent)||'--'||t.content "
				+ "as text from t_list_status t where t.state='1'";

		if (!G.isNvl(parent)) {
			sql += " and t.parent in (" + parent + ")";
		} else {
			response.getWriter().write(new JSONArray().toString());
		}

		if (G.isNvl(orderStr))
			sql += " order by text,optorder";
		else
			sql += " order by " + G.getNlssort(orderStr);

		Iterator<DataObject> it = dm.find(sql, null).iterator();
		DataObject dob = null;
		JSONArray jsona = new JSONArray();
		JSONObject json = null;
		while (it.hasNext()) {
			dob = it.next();
			json = new JSONObject();
			// 此处根据miniui的要求，需要使用小写
			json.put("id", dob.getString("ID"));
			json.put("text", dob.getString("TEXT"));
			jsona.put(json);
		}
		response.getWriter().write(jsona.toString());
	}

}
