package com.corp.oa.system.right;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.bean.JSONObject;

import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

/**
 * 设置角色的人员列表/人员的角色列表
 * 
 * @author Administrator
 * 
 */
public class SetRightIdList implements AjaxInterface {

	public void doExecute(HttpServletRequest request, HttpServletResponse response,DataManager dm)
			throws Exception {

		String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);
		JSONObject json = new JSONObject(key);

		String type = json.getString("type");
		String list = json.getString("sid");

		String id = "", sql = "", empSid = "";
		DataObject dob = null;
		empSid = G.getUserIdFromSession(request);

		if ("role".equals(type)) {
			id = json.getString("roleid");

			// 先删除
			sql = "delete from t_right_userrole t where t.roleid=? and t.empid<>? and t.empid<>'0'";
			sql = G.formatSql(sql, new String[] { id, empSid });
			dm.delete(sql, null);

			String[] emp = list.split(",");
			for (int i = 0; i < emp.length; i++) {
				if (!G.isNvl(emp[i])) {
					dob = new DataObject("T_RIGHT_USERROLE");
					dob.setString("SID", dm.getSequence("SEQ_T_IWL"));
					dob.setString("ROLEID", id);
					dob.setString("EMPID", emp[i]);
					dob.setString("CRTUSER", empSid);
					dob.setString("UPTUSER", empSid);
					dob.setString("UPTDATE", G.getCurrentTime());
					dm.addBatch(dob);
				}
			}
			dm.insertBatch();

		} else if ("employee".equals(type)) {
			id = json.getString("empid");

			// 先删除
			sql = "delete from t_right_userrole t where t.empid=? and t.empid<>? and t.empid<>'0'";
			sql = G.formatSql(sql, new String[] { id, empSid });
			dm.delete(sql, null);

			String[] role = list.split(",");
			for (int i = 0; i < role.length; i++) {
				if (!G.isNvl(role[i])) {
					dob = new DataObject("T_RIGHT_USERROLE");
					dob.setString("SID", dm.getSequence("SEQ_T_IWL"));
					dob.setString("ROLEID", role[i]);
					dob.setString("EMPID", id);
					dob.setString("CRTUSER", empSid);
					dob.setString("UPTUSER", empSid);
					dob.setString("UPTDATE", G.getCurrentTime());
					dm.addBatch(dob);
				}
			}
			dm.insertBatch();
		}

		dm.setLocalConnCommit();

		response.getWriter().write("");
	}
}
