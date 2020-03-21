package com.corp.oa.system.log;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;
import com.corp.oa.util.Excel;

public class ExportListAction implements BusinessAction {

	@SuppressWarnings("unchecked")
	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {

		LogForm frm = (LogForm) parm1;
		String sql = "";
		sql = "select distinct case a.logtype when 'A' then '系统日志'";
		sql += " when 'B' then '业务日志' else '未知:'||a.logtype end ";
		sql += " AS LOGTYPE_NAME,b.rightname as MENUID_NAME, f_getempname(a.optempid) as optempid,";
		sql += " a.optattr,f_getmapcontent(a.opttype) as opttype,a.opttime,a.optdesc";
		sql += " from T_INFO_LOG a left join t_right_rightdic b on substr(a.menuid,1,5)||'000'=b.rightid where 1=1";

		if (!"".equals(frm.getStarttime())) {
			sql += " and  a.OPTTIME>='" + frm.getStarttime() + " 00:00:00'";
		}

		if (!"".equals(frm.getEndtime())) {
			sql += " and  a.OPTTIME<='" + frm.getEndtime() + " 24:00:00'";
		}

		sql += " order by OPTTIME DESC";

		Excel ex = new Excel();
		ex.setTitle("日志类型,操作人员,操作类型,操作描述,操作时间,内容");
		ex.setField("LOGTYPE_NAME,optempid,opttype,optattr,opttime,optdesc");
		ex.makeExcel(dm.find(sql, null));
		ex.pushFileToDownload(frm.getResponse());
	}

}
