package com.corp.oa.system.org;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.exception.GeneralException;

public class OrgDeptDelAction implements BusinessAction {
	public OrgDeptDelAction() {
	}

	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		OrgForm of = (OrgForm) parm1;

		String deptSid = of.getDept_sid();

		String sql = "";
		sql = "select * from t_info_dept where PARENTDEPT=? and state='1'";

		DataObject dob = dm.findByPrimaryKey(sql, deptSid);
		// �ڵ��»�������Ϣ������ɾ����
		if (dob != null)
			throw new GeneralException("�ڵ��»�����Ϣ������ɾ����");

		// ���ڵ㲻��ɾ����
		if ("1".equals(deptSid))
			throw new GeneralException("���ڵ㲻��ɾ����");

		// ɾ���˲����µ������ֳ��ն˵�ͬ����Ϣ
		// G.insertEmployeeSyncInfo(deptSid, dm, true);

		// G.insertDictionarySyncInfo(dm, true, of.getT_INFO_DEPT()
		// .getString("DEPT_CITY"), deptSid, true);
		// ���򲻸����ֵ�����

		sql = "Update  t_info_dept set STATE='0' where sid=?";
		dm.update(sql, deptSid);

		// ɾ���ýڵ��,��ת���ýڵ�ĸ��ڵ�
		sql = "select PARENTDEPT from t_info_dept where sid=?";
		of.setTransInfo(dm.findByPrimaryKey(sql, deptSid).getString(
				"PARENTDEPT"));

	}

}
