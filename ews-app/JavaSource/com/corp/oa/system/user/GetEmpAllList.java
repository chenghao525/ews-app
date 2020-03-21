package com.corp.oa.system.user;

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
 * 当前系统所有员工的姓名列表，for miniui
 * 
 * @author Administrator
 * 
 */
public class GetEmpAllList implements AjaxInterface {

	@SuppressWarnings("unchecked")
	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		String state = request.getParameter(AJAX_PARAMETER_KEY_NAME);
		String incSelf = request.getParameter("inc-self");

		UserBO bo = new UserBO();
		String sql = bo.getEmpAllList();
		JSONObject json = null;

		if (!G.isNvl(state) && "valid".equals(state)) {
			// 选择当前有效的账户
			sql = "select * from (" + sql + ") where state='1'";
		}
		if (!G.isNvl(incSelf) && "false".equals(incSelf)) {
			// 当前账号不选择
			sql = "select * from (" + sql + ") where sid<>'" + G.getUserIdFromSession(request)
					+ "'";
		}

		sql = "select * from (" + sql + ") order by " + G.getNlssort("EMPNAME");

		Iterator<DataObject> it = dm.find(sql, null).iterator();
		DataObject dob = null;
		JSONArray jsona = new JSONArray();
		json = null;
		while (it.hasNext()) {
			dob = (DataObject) it.next();
			json = new JSONObject();
			json.put("id", dob.getString("SID"));
			json.put("text", dob.getString("EMPNAME"));
			jsona.put(json);
		}
		response.getWriter().write(jsona.toString());
	}
}
