package com.corp.oa.system.dictionary;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;

public class DicListAction implements BusinessAction {
	public DicListAction() {
	}

	@SuppressWarnings("unchecked")
	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		SysDictionaryForm frm = (SysDictionaryForm) parm1;
		// 设置页码
		frm.getPagination().setCurrent(frm.getCurrent());

		String sql = "";

		// 超级管理员不显示
		sql = "select t.* from t_list_status t where t.state='1'";
		frm.getQueryView().setSelect(sql);
		frm.getQueryView().setExtension("order by infotype,OPTORDER");
		sql = frm.getQueryView().getSql();
		frm.setResultList(dm.find(sql, null, frm.getPagination()));
	}
}
