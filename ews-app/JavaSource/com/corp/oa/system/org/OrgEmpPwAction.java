package com.corp.oa.system.org;

import com.corp.frame.bean.JSONObject;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.base.UserInfo;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.exception.GeneralException;
import com.corp.oa.util.G;

public class OrgEmpPwAction implements BusinessAction {
	public OrgEmpPwAction() {
	}

	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {

		OrgForm of = (OrgForm) parm1;
		// 获得操作人员的信息
		UserInfo userView = of.getUserView();
		String useId = userView.getUserId();
		boolean flag = false;

		JSONObject json = new JSONObject(of.getJson_string());

		// 获得原密码
		String ymima = json.getString("ymima");
		// 获得新密码
		String xmima = json.getString("xmima");

		// 判断密码是否正确
		String strSql = "";
		strSql = "select * from t_info_employee where state=1 and sid=?";
		DataObject dol = dm.findByPrimaryKey(strSql, useId);
		if (dol != null) {
			if (dol.getString("emppwd") == null) {
				if (ymima == null || "".equals(ymima))
					flag = true;
			} else {
				if (dol.getString("emppwd").equals(G.jiaMi(ymima)))
					flag = true;
			}
		}

		if (flag) {
			dol.setModelName("t_info_employee");
			dol.setString("emppwd", G.jiaMi(xmima));
			dm.update(dol);
		} else {
			// 原密码不正确！
			throw new GeneralException("原密码不正确！");
		}

	}

}
