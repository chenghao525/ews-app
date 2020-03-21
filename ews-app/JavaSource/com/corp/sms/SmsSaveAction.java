package com.corp.sms;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;
import com.corp.oa.util.G;

public class SmsSaveAction implements BusinessAction {

	@Override
	public void doExecute(BusinessForm form, DataManager dm) throws Exception {
		SmsForm frm = (SmsForm) form;
		// 保留当前页码
		frm.getPagination().setCurrent(frm.getCurrent());

		String p = frm.getPhone().replaceAll("，", ",");
		p = p.replaceAll(";", ",");
		p = p.replaceAll("；", ",");

		String[] m = p.split(",");

		String empSid = frm.getUserView().getUserId();

		for (int i = 0; i < m.length; i++) {
			G.sendSMS(empSid, m[i], frm.getContent(), dm);
		}

		if (frm.getNext() != null && !"".equals(frm.getNext())) {
			frm.setPhone(p);
		} else {
			frm.setPhone("");
		}
		frm.setContent("");

		frm.setResult("短信发送成功");
	}
}
