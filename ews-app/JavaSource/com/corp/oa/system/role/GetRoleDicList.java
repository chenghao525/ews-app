package com.corp.oa.system.role;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;

import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

/**
 * 获取商户对应的礼品包列表
 * 
 * @author Administrator
 * 
 */
public class GetRoleDicList implements AjaxInterface {

	@SuppressWarnings("unchecked")
	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		// 内部empsid
		String empSid = G.getUserIdFromSession(request);

		String sql = "";

		RoleUtils ru = new RoleUtils();

		// 获得角色信息列表
		sql = ru.getRoleDicListSql(empSid);

		Iterator<DataObject> it = dm.find(sql, null).iterator();
		DataObject dob = null;
		JSONArray jsona = new JSONArray();
		JSONObject json = null;
		while (it.hasNext()) {
			dob = (DataObject) it.next();
			json = new JSONObject();
			json.put("id", dob.getString("ID"));
			json.put("text", dob.getString("TEXT"));
			jsona.put(json);
		}
		response.getWriter().write(jsona.toString());
	}
}
