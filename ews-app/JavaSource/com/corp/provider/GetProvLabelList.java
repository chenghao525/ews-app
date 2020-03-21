package com.corp.provider;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.basic.Constant;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

public class GetProvLabelList implements AjaxInterface {

	@SuppressWarnings("unchecked")
	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);
		if (G.isNvl(key)) {
			response.getWriter().write("");
			return;
		}

		ProviderTools pt = new ProviderTools();
		JSONObject jsonOut = pt.getProvLabel(key, dm);

		JSONObject json = new JSONObject();
		json.put("PROV", jsonOut.getString("LBL"));
		json.put("PROV_SHOW", jsonOut.getString("LBL_TIMES"));

		String sql = "select * from t_info_provider_label t where t.state='1' and t.sid='0'"
				+ " and t.lbl_name not in (select lbl_name from t_info_provider_label"
				+ " where state='1' and sid=?) order by t.lbl_order";
		Iterator<DataObject> it = dm.find(sql, key).iterator();
		String list = "";
		DataObject dob = null;
		while (it.hasNext()) {
			dob = (DataObject) it.next();
			list += dob.getString("LBL_NAME") + Constant.STRING_BREAK_NEW;
		}
		if (!"".equals(list)) {
			list = list.substring(0, list.length() - Constant.STRING_BREAK_NEW.length());
		}
		json.put("ALL", list);

		response.getWriter().write(json.toString());
	}
}
