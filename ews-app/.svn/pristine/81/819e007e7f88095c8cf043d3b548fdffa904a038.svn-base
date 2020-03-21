package com.corp.oa.system.org;

import java.util.Iterator;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;

public class UpdateOrgAction implements BusinessAction {
	public UpdateOrgAction() {
	}

	@SuppressWarnings("unchecked")
	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		OrgForm of = (OrgForm) parm1;
		String sql;
		String sqlC = "";
		String sid[] = of.getDuty();

		for (int i = 0; i < sid.length; i++) {
			String info[] = sid[i].split("_");
			// 人员的排列顺序
			if ("3".equals(info[2])) {
				sql = "update t_info_dept set deptrank=" + (i + 1)
						+ " where deptid=" + info[1] + " and sid=" + info[0]
						+ " and info_class=3";

				sqlC = " deptid=" + info[1] + " and info_class=3";
			}
			// 非人员的排列顺序
			else {
				sql = "update t_info_dept set deptrank=" + (i + 1)
						+ " where sid=" + info[0];
				sqlC = " sid=" + info[0];
			}
			dm.update(sql, null);
		}

		sqlC = "select PARENTDEPT from t_info_dept where " + sqlC;
		Iterator<DataObject> it = dm.find(sqlC, null).iterator();
		if (it.hasNext()) {
			DataObject dob = it.next();
			of.setTransInfo(dob.getString("PARENTDEPT"));
		} else {
			of.setTransInfo("");
		}
	}

}
