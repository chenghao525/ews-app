package com.corp.oa.system.dictionary;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;

import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.servlet.AjaxInterface;

/**
 * 此方法是专门提供给渠道商下来列表使用的
 * 
 * @author Administrator
 * 
 */
public class GetDicList4BC implements AjaxInterface {

	@SuppressWarnings("unchecked")
	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		String sql = "select sid as id,content as text from T_LIST_STATUS"
				+ " where state='1' and infotype='32'";

		sql += " order by optorder";

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
