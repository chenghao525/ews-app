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

public class GetOrgTree implements AjaxInterface {

	@SuppressWarnings("unchecked")
	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		JSONObject json = new JSONObject();
		JSONArray jsona = new JSONArray();

		String empSid = G.getUserIdFromSession(request);
		String root = "";
		if (G.isSuperSystemUserRole(dm, empSid)) {
			root = "2";
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
			if ("0".equals(dob.getString("PID")))
				dob.setString("PID", "");

			ic = dob.getString("INFO_CLASS");
			if ("3".equals(ic)) {// 人员
				sql = "select t.sid,empid,empname from t_info_employee t"
						+ " where sid=? and state='1'";
				dobe = dm.findByPrimaryKey(sql, dob.getString("DEPTID"));
				if (dobe != null) {
					dob.setString("SID", dobe.getString("SID"));
					dob.setString("DEPTID", dobe.getString("EMPID"));
					dob.setString("DEPTNAME", dobe.getString("EMPNAME"));
				}
				dob.setString("iconCls", "icon-user");
			} else if ("1".equals(ic)) {// 公司
				dob.setString("iconCls", "icon-building");
			} else if ("2".equals(ic)) {// 部门
				dob.setString("iconCls", "icon-org");
			}
			json = dob.toJson();
			jsona.put(json);
		}

		response.getWriter().write(jsona.toString());
	}
}
