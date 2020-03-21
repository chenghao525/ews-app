package com.corp.oa.system.system;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;
import com.corp.oa.util.G;

public class SysInfoSaveAction implements BusinessAction {

	public void doExecute(BusinessForm arg0, DataManager dm) throws Exception {
		SysForm frm = (SysForm) arg0;

		String sql = "";
		String names[] = frm.getNames();
		String attrs[] = frm.getAttrs();

		String empsid = frm.getUserView().getUserId();// 当前登录人
		String currentTime = G.getCurrentTime();// 当前时间
		
		//批量保存，只需要保存一条日志
		dm.setSingleLogThisAction(true);
		for (int i = 0; i < names.length; i++) {
			sql = "update t_info_system set attrvalue='" + attrs[i]
					+ "',uptuser='" + empsid + "',uptdate='" + currentTime
					+ "' where ATTRNAME='" + names[i] + "'";
			dm.update(sql, null);
		}

	}
}
