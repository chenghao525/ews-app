package com.corp.oa.system.right;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.bean.JSONObject;

import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

/**
 * 获取角色的人员列表/人员的角色列表
 * 
 * @author Administrator
 * 
 */
public class GetRightIdList implements AjaxInterface {

	@SuppressWarnings("unchecked")
	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		String type = request.getParameter("type");
		String id = request.getParameter("id");

		// 内部empsid
		String empSid = G.getUserIdFromSession(request);

		String sql = "";

		RightUtils ru = new RightUtils();

		if ("role".equals(type)) {
			sql = "select sid as id from (" + ru.getRightEmpListByRoleSql(id, empSid) + ")";
		} else if ("employee".equals(type)) {
			sql = "select roleid as id from (" + ru.getRightRoleListByEmpSql(id, empSid) + ")";
		}

		Iterator<DataObject> it = dm.find(sql, null).iterator();
		DataObject dob = null;
		String right = ",";
		while (it.hasNext()) {
			dob = (DataObject) it.next();
			right += dob.getString("ID") + ",";
		}
		JSONObject json = new JSONObject();
		json.put("right", right);
		response.getWriter().write(json.toString());
	}
}
