package com.corp.provider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.basic.Constant;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

public class SaveProvLabelList implements AjaxInterface {

	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);
		if (G.isNvl(key)) {
			response.getWriter().write("");
			return;
		}

		JSONObject json = new JSONObject(key);
		String sid = json.getString("SID");
		String[] lbl = G.nvl(json.getString("LABEL"), "").split(Constant.STRING_BREAK_NEW);

		dm.setLocalConnAutoCommit(false);

		String curTime = G.getCurrentTime(), empSid = G.getUserIdFromSession(request);

		// 先将去除的label置为删除状态
		String lblList = G.nvl(json.getString("LABEL"), "").replaceAll(Constant.STRING_BREAK_NEW,
				"','");
		String sql = G.formatSql(
				"update t_info_provider_label t set t.state='0',t.upt_time=?,t.upt_emp=?"
						+ " where t.state='1' and t.sid=?", new String[] { curTime, empSid, sid });
		if (!G.isNvl(lblList)) {
			sql += " and t.lbl_name not in ('" + lblList + "')";
		}
		dm.update(sql, null);

		// 批量保存
		DataObject dob = null;
		for (int i = 0; i < lbl.length && !G.isNvl(lbl[i]); i++) {
			dob = new DataObject("T_INFO_PROVIDER_LABEL");
			dob.setString("SID", sid);
			dob.setString("LBL_NAME", lbl[i]);
			dob.setInt("LBL_ORDER", (i + 1));
			dob.setString("UPT_EMP", empSid);
			dob.setString("STATE", "1");
			dob.setString("UPT_TIME", curTime);
			dm.save(dob);
		}

		ProviderTools pt = new ProviderTools();
		pt.updateProvLabelList(sid, dm);

		dm.setLocalConnCommit();

		response.getWriter().write("");
	}
}
