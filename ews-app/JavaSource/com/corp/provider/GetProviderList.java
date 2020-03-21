package com.corp.provider;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;

import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

public class GetProviderList implements AjaxInterface {

	@SuppressWarnings("unchecked")
	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);

		// 以下两个字段是返回客户的最新输入和已经选择了的数据
		String newInput = request.getParameter("key");
		String valueList = request.getParameter("value");

		String city = "", srv = "";

		if (!G.isNvl(key)) {
			JSONObject json = new JSONObject(key);
			city = json.has("CITY") ? json.getString("CITY") : "";
			srv = json.has("SRV") ? json.getString("SRV") : "";
		}

		ProviderBO bo = new ProviderBO();
		String sql = bo.getProvList();

		if (newInput != null) {// 兼容选择列表的方式
			if (G.isNvl(newInput)) {// 空格输入
				response.getWriter().write("");
				return;
			}
			newInput = newInput.trim();
			sql += " and (PROV_NAME like '%" + newInput + "%' or PROV_NAME_SUM like '%" + newInput
					+ "%')";
		}

		if (!G.isNvl(valueList)) {// 兼容选择列表的方式
			valueList = valueList.replaceAll(",", "','");
			sql += " and sid not in ('" + valueList + "')";
		}

		sql = "select * from (" + sql + ") where 1=1";

		if (!G.isNvl(city)) {
			city = city.replaceAll(",", "','");
			sql += " and city in ('" + city + "')";
		}

		if (!G.isNvl(srv)) {
			String srvSql = "";
			String[] si = srv.split(",");
			for (int i = 0; i < si.length; i++) {
				if (!G.isNvl(srvSql)) {
					srvSql += " or ";
				}
				srvSql += " srv_items like '%" + si[i] + "%'";
			}
			sql += " and (" + srvSql + ")";
		}

		sql += " order by PROV_NAME";

		Iterator<DataObject> it = null;
		JSONObject json = null;
		JSONArray ja = new JSONArray();
		DataObject dob = null;
		it = dm.find(sql, null).iterator();
		String name = "";
		while (it.hasNext()) {
			dob = it.next();
			json = new JSONObject();
			// 此处根据miniui的要求，需要使用小写
			json.put("id", dob.getString("SID"));
			name = dob.getString("PROV_NAME");
			name = G.isNvl(dob.getString("PROV_NAME_SUM")) ? name : name + "/"
					+ dob.getString("PROV_NAME_SUM");
			json.put("text", name);

			ja.put(json);
		}

		response.getWriter().write(ja.toString());
	}
}
