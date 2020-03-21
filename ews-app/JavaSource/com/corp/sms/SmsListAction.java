package com.corp.sms;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;

public class SmsListAction implements BusinessAction {
	@SuppressWarnings("unchecked")
	@Override
	public void doExecute(BusinessForm form, DataManager dm) throws Exception {
		SmsForm frm = (SmsForm) form;
		frm.getPagination().setCurrent(frm.getCurrent());

		SmsBO sbo = new SmsBO();
		String sql = sbo.getSMSListSql();
		frm.getQueryView().setSelect(sql);
		frm.getQueryView().setExtension("order by LATEST_SEND desc");

		sql = frm.getQueryView().getSql();
		frm.setSmsList(dm.find(sql, null, false, null, frm.getPagination()));
	}
}
