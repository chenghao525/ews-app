package com.corp.oa.system.dictionary;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;

import com.corp.basic.Constant;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.servlet.AjaxInterface;

public class GetDicList4JY implements AjaxInterface {

	@SuppressWarnings("unchecked")
	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		String sql = "select * from (select a.bom_brand as sid, l.content, l.optorder, l.state"
				+ " from (select distinct t.bom_brand from t_bom_map_list2type t,t_bom_list b"
				+ " where b.state='1' and t.bom_uuid=b.sid and t.bom_brand is not null"
				+ " and (t.type_uuid ='" + Constant.BOM_TYPE_JIYOU + "' or t.type_uuid = '"
				+ Constant.BOM_TYPE_JIYOU2
				+ "')) a left join t_list_status l on a.bom_brand = l.sid)"
				+ " where state='1' order by optorder";

		Iterator<DataObject> it = dm.find(sql, null).iterator();
		DataObject dob = null;
		JSONArray jsona = new JSONArray();
		JSONObject json = null;
		while (it.hasNext()) {
			dob = it.next();
			json = new JSONObject();
			// 此处根据miniui的要求，需要使用小写
			json.put("id", dob.getString("SID"));
			json.put("text", dob.getString("CONTENT"));
			jsona.put(json);
		}
		response.getWriter().write(jsona.toString());
	}
}
