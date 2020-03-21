package com.corp.oa.system.org;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

public class GetOrgUsers implements AjaxInterface {

	@SuppressWarnings("unchecked")
	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		JSONObject json = new JSONObject();
		JSONArray jsona = new JSONArray();

		String empSid = G.getUserIdFromSession(request);
		String root = "";
		if (G.isSuperSystemUserRole(dm, empSid)) {
			root = "1";
		}
		String sql = "select sid,c.deptname,c.deptid,c.PARENTDEPT as pid,"
				+ " c.info_class from T_INFO_DEPT C where state='1' "
				+ " Start With sid=? Connect By Prior C.SID=C.PARENTDEPT"
				+ " order BY LEVEL,c.deptrank";

		Iterator<DataObject> it = dm.find(sql, root).iterator();
		DataObject dob = null, dobe = null;
		String ic = "";
		while (it.hasNext()) {
			dob = it.next();

			ic = dob.getString("INFO_CLASS");
			if ("3".equals(ic)) {// »À‘±
				sql = "select empid,empname from t_info_employee t" + " where sid=? and state='1'";
				dobe = dm.findByPrimaryKey(sql, dob.getString("DEPTID"));
				if (dobe != null) {
					json = dobe.toJson();
					jsona.put(json);
				}
			}
		}

		response.getWriter().write(jsona.toString());
	}
}
