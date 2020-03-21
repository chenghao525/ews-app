package com.corp.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;

import com.corp.frame.core.base.SystemConfig;
import com.corp.frame.core.base.UserInfo;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;

/*
 * 返回菜单字符串
 */
@SuppressWarnings("serial")
public class MenuServletV12 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		DataManager dm = null;

		try {
			dm = DataManager.getInstance();
			// 获得操作人员的信息
			UserInfo userView = (UserInfo) request.getSession().getAttribute(
					SystemConfig.getWebUserViewName());

			// 内部编号
			String useId = userView.getUserId();

			JSONArray jsona = getMenuJson(dm, useId);

			// Set to expire far in the past.
			response.setDateHeader("Expires", 0);
			// Set standard HTTP/1.1 no-cache headers.
			response.setHeader("Cache-Control",
					"no-store, no-cache, must-revalidate");
			// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
			response.addHeader("Cache-Control", "post-check=0, pre-check=0");
			// Set standard HTTP/1.0 no-cache header.
			response.setHeader("Pragma", "no-cache");

			response.setContentType("text/html");

			OutputStream os = response.getOutputStream();

			os.write(jsona.toString().getBytes("UTF-8"));

			os.close();
		} catch (Exception e) {

		} finally {
			if (dm != null) {
				dm.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	private JSONArray getMenuJson(DataManager dm, String useId)
			throws Exception {
		// 获得用户所有的权限

		dm.setCheckPermission(false);

		String sql = "select sid,menuname,posturl,imagename,parentsid from ("
				+ " select t.sid,T.menuname,t.POSTURL,T.IMAGENAME,T.RIGHTID,"
				+ " t.parentsid,case t.parentsid when 0 then F_EMPHASRIGHTROOT(?,t.rightid)"
				+ " else F_EMPHASRIGHT(?,t.rightid) end as hasright"
				+ " from t_ews_func_menu t where t.state = '1' order by t.parentsid, t.optorder"
				+ " ) where hasright='1'";

		Iterator<DataObject> it = dm.find(sql, new String[] { useId, useId })
				.iterator();
		DataObject dob = null;

		String p = "";
		String h = "id-";

		JSONObject js = null;
		JSONArray jsa = new JSONArray();

		String rootSid = ",";

		while (it.hasNext()) {
			dob = it.next();
			if (dob != null) {
				js = new JSONObject();
				p = dob.getString("PARENTSID");
				if ("0".equals(p)) {// 第一层菜单
					js.put("id", h + dob.getString("SID"));
					js.put("text", dob.getString("MENUNAME"));
					js.put("iconCls", dob.getString("IMAGENAME"));
					jsa.put(js);
					rootSid += dob.getString("SID") + ",";
				} else if (rootSid.indexOf("," + p + ",") >= 0) {// 第二层菜单
					js.put("id", h + dob.getString("SID"));
					js.put("pid", h + p);
					js.put("text", dob.getString("MENUNAME"));
					js.put("iconCls", dob.getString("IMAGENAME"));
					js.put("url", dob.getString("POSTURL"));
					js.put("isLeaf", "true");
					jsa.put(js);
				}
			}
		}
		return jsa;
	}

}
