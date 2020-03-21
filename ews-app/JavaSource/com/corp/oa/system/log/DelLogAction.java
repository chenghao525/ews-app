package com.corp.oa.system.log;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;

public class DelLogAction implements BusinessAction {

	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {

		LogForm frm = (LogForm) parm1;

		if ("true".equals(frm.getExp())) {
			ExportListAction ela = new ExportListAction();
			ela.doExecute(parm1, dm);
		}

		// String sql = "";
		// sql = "delete from t_info_log a where 1=1";
		// if (!"".equals(frm.getStarttime())) {
		// sql += " and  a.OPTTIME>='" + frm.getStarttime() + " 00:00:00'";
		// }
		//
		// if (!"".equals(frm.getEndtime())) {
		// sql += " and  a.OPTTIME<='" + frm.getEndtime() + " 24:00:00'";
		// }

		//dm.delete(sql, null);

	}

}
