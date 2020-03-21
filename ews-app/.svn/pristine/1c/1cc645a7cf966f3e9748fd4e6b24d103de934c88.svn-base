package com.corp.provider;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.servlet.AjaxInterface;

public class GetProvAttrList implements AjaxInterface {

	@SuppressWarnings("unchecked")
	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		JSONObject json = new JSONObject();
		JSONArray jsona = new JSONArray();

		String sql = "select sid as id,content as text,c.parent as pid from t_list_status c"
				+ " where c.state='1' and  ((c.infotype=7 and"
				+ " (select count(1) as cnt from t_list_status t where t.parent=c.sid)>0)"
				+ " or c.infotype=42) Start With c.infotype=7 Connect By Prior C.SID=c.parent"
				+ " order by c.parent,c.optorder";

		Iterator<DataObject> it = dm.find(sql, null).iterator();
		DataObject dob = null;
		while (it.hasNext()) {
			dob = (DataObject) it.next();
			json = dob.toJson();
			jsona.put(json);
		}

		response.getWriter().write(jsona.toString());
	}
}
