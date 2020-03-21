package com.corp.oa.system.right;

import java.util.Collection;
import java.util.Iterator;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.base.UserInfo;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;

/**
 * <p>
 * Title: Ȩ������
 * </p>
 * <p>
 * Description: Ȩ�����ù���ģ��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author liss
 * @version 1.0
 */

public class RightByRoleAction implements BusinessAction {
	public RightByRoleAction() {
	}

	@SuppressWarnings("unchecked")
	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		RightForm rightForm = (RightForm) parm1;

		// ��ò�����Ա����Ϣ
		UserInfo userView = rightForm.getUserView();

		// �ڲ����
		String useId = userView.getUserId();

		String strRoleID = rightForm.getCurroleid();

		String strSql = "";
		// ��ø���Ա��ӵ�еĽ�ɫ��Ϣ�б�
		strSql = "select a.rolename,sid,roleid from t_right_roledic a where a.state='1' and a.roleid in";
		strSql += "(select roleid from t_right_userrole where empid=" + useId
				+ ") order by sid";

		Collection<DataObject> ct = dm.find(strSql, null);
		rightForm.setRoleinfolist(ct);

		if (strRoleID == null || strRoleID.equals("")) { // ��ʼ����״̬
			strRoleID = "";
			if (ct.size() > 0) { // ��Աӵ�н�ɫ���ݵ����
				Iterator<DataObject> it = ct.iterator();
				while (it.hasNext()) {
					DataObject doe = it.next();
					// �����ݵ������,ȡ�õ�һ����¼�Ľ�ɫ���
					strRoleID = doe.getString("roleid");
					break;
				}
			}
		}

		rightForm.setCurroleid(strRoleID);

		// ��Ա������ѡ�������,��ȷ����Ա�Ƿ�ӵ��ָ���Ľ�ɫ
		/*
		 * strSql = "select SID,EMPID || '/' || EMPNAME || '[' ||
		 * F_GETDEPTNAME(DEPTSID)"; strSql += " || '/' ||
		 * F_GETMAPCONTENT(EMPRANK) || ']' as empinfo,F_EMP_HAS_ROLE(sid,'";
		 * strSql += strRoleID + "') as ismyright from T_INFO_EMPLOYEE where
		 * STATE=3";
		 */
		strSql = "select SID,EMPID || '/' || EMPNAME|| '[' ||F_GETDEPTNAME(sid)|| ";
		strSql += " '/'||F_GETDUTYNAME(sid)||']' as empinfo,F_EMP_HAS_ROLE(sid,'";
		strSql += strRoleID
				+ "') as ismyright,f_get_emp_duty_class(sid) as emporder";
		strSql += " from T_INFO_EMPLOYEE A where STATE=1 and sid<>" + useId
				+ " and sid<>0 order by emporder";

		// ѡ��Ա���б�
		rightForm.setEmplist(dm.find(strSql, null));

	}

}
