package com.corp.oa.system.org;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.exception.GeneralException;

public class OrgEmpDelAction implements BusinessAction {
	public OrgEmpDelAction() {
	}

	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {

		OrgForm of = (OrgForm) parm1;

		String empSid = of.getEmp_sid();

		// ���ڵ�ı��
		String deptP = of.getDept_sid();

		String sql = "";
		// ����ɾ������Ա�˺ţ�
		if ("0".equals(empSid))
			throw new GeneralException("error.DelAdmin");
		// ����ɾ����ǰ��¼���û���
		if (of.getUserView().getUserId().equals(empSid))
			throw new GeneralException("error.DelCurrentUser");

		sql = "Update  t_info_dept set STATE='0' where DEPTID=? and PARENTDEPT=?";
		dm.update(sql, new String[] { empSid, deptP });

		sql = "Update T_INFO_EMPLOYEE set STATE='0' where sid=?";
		dm.update(sql, empSid);

		// ɾ���ýڵ��,��ת���ýڵ�ĸ��ڵ�
		of.setTransInfo(deptP);

	}

}
