package com.corp.oa.system.org;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.base.UserInfo;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.exception.GeneralException;
import com.corp.oa.util.G;

public class OrgEmpSaveAction implements BusinessAction {
	public OrgEmpSaveAction() {
	}

	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {

		OrgForm of = (OrgForm) parm1;

		// ��ò�����Ա����Ϣ
		UserInfo userView = of.getUserView();
		// �ڲ����
		String useId = userView.getUserId();

		// �����ŵ��ڲ����
		String deptP = of.getDept_sid();

		// ��Ա���ڲ���� ����Ϊnull
		String empSid = of.getEmp_sid();

		// �������յı��,�ѱ���תҳ��ʹ��
		String sLastID = "";

		if ("true".equals(of.getDeptAdd())) { // ������Ϣ����Ա��Ϣ
			String sSql = "select * from T_INFO_EMPLOYEE where STATE=1 and upper(EMPID)=?";
			DataObject dob = dm.findByPrimaryKey(sSql, of.getT_INFO_EMPLOYEE()
					.getString("EMPID").toUpperCase().trim());
			if (dob != null)
				// ��Ա�ĵ�¼���ظ���������д��
				throw new GeneralException("�˺��Ѵ���");

			// �²������Ա���ڲ����
			empSid = G.getSequence("SEQ_T_IWL", dm);

			// ����Ա���в���������
			of.getT_INFO_EMPLOYEE().setString("SID", empSid);
			of.getT_INFO_EMPLOYEE().setString("EMPPWD", "");
			of.getT_INFO_EMPLOYEE().setString("CRITICAL_VALUE", "1");
			of.getT_INFO_EMPLOYEE().setString("CRTUSER", useId);
			of.getT_INFO_EMPLOYEE().setString("UPTUSER", useId);
			of.getT_INFO_EMPLOYEE().setString("DUTYSID", deptP);

			dm.insert(of.getT_INFO_EMPLOYEE());

			// ��λ���ű��в�����Ա������,�γ����ṹ
			DataObject dbnew = new DataObject();
			dbnew.setModelName("T_INFO_DEPT");
			dbnew.setString("SID", G.getSequence("SEQ_T_IWL", dm));
			dbnew.setString("DEPTID", empSid);
			dbnew.setString("PARENTDEPT", deptP);
			dbnew.setString("CRITICAL_VALUE", "1");
			dbnew.setString("DEPTRANK", G.getSequence("SEQ_T_IWL", dm));
			dbnew.setString("INFO_CLASS", "3");

			dm.insert(dbnew);
		} else { // �޸���Ա����Ϣ
			of.getT_INFO_EMPLOYEE().setString("UPTUSER", useId);
			of.getT_INFO_EMPLOYEE().setString("SID", empSid);
			String dutys = of.getT_INFO_EMPLOYEE().getString("DUTY");

			dutys = dutys.replaceAll("_2_", "");
			of.getT_INFO_EMPLOYEE().setString("DUTYSID", dutys);
			dm.update(of.getT_INFO_EMPLOYEE());
			sLastID = empSid;

			String sql = "";
			sql = "Update T_INFO_DEPT set PARENTDEPT=? where DEPTID=? and INFO_CLASS=3";
			dm.update(sql, new String[] { dutys, empSid });
		}

		// ���ϱ�ʶ���ַ���,��ΪJSPҳ���в����˴�����ʽ
		of.setTransInfo("emp_" + sLastID + "_");

		G.setImagePath(dm, empSid, "employee",
				of.getPhotoPath());

	}

}
