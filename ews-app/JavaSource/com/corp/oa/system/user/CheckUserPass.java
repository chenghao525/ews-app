package com.corp.oa.system.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

public class CheckUserPass implements AjaxInterface {

	public void doExecute(HttpServletRequest request, HttpServletResponse response,DataManager dm)
			throws Exception {

		String empSid = G.getUserIdFromSession(request);
		String sql = "select * from t_info_employee t where t.sid=?";
		DataObject dob = dm.findByPrimaryKey(sql, empSid);

		if (dob != null) {
			String p = dob.getString("EMPPWD");
			p = G.jieMi(p);
			if (p == null || "".equals(p) || p.length() < 6) {
				// 密码为空，或者空字符串，或者长度小于6，都必须修改密码
				response.getWriter().write("0");
			} else
				response.getWriter().write("1");
		} else
			response.getWriter().write("1");
	}
}
