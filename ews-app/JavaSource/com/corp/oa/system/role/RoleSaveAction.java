package com.corp.oa.system.role;

import java.util.Iterator;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.base.UserInfo;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.exception.GeneralException;
import com.corp.oa.util.G;

/**
 * <p>
 * �����ɫ��Ϣ:
 * </p>
 * <p>
 * Description:
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

public class RoleSaveAction implements BusinessAction {
	public RoleSaveAction() {
	}

	@SuppressWarnings("unchecked")
	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		// System.out.println("come in=================");
		RoleForm roleForm = (RoleForm) parm1;
		// ����ҳ��
		roleForm.getPagination().setCurrent(roleForm.getCurrent());

		// ��ò�����Ա����Ϣ
		UserInfo userView = roleForm.getUserView();

		// �ڲ����
		String useId = userView.getUserId();

		// ��ɫ���
		String roleid = roleForm.getRoleid();

		// �ύ��Ȩ�ޱ��
		String[] roleList = roleForm.getRolerightlist();

		String strSql = "";

		DataObject doe = new DataObject();

		// �����ɵĽ�ɫ���
		String strNewRoleID = "";

		// System.out.println("lishush+++++++++++++++++++");
		if (roleForm.getDisplay().equals("add")) { // ����½�ɫ

			strSql = "select * from T_RIGHT_ROLEDIC where ROLENAME='"
					+ roleForm.getRolename() + "' and state=1";
			doe = dm.findByPrimaryKey(strSql, null);
			if (doe != null) {
				throw new GeneralException("error.SameRoleName");
			}

			strNewRoleID = G.getColumMaxID(dm, "T_RIGHT_ROLEDIC", "ROLEID",
					"1", 7); // �����µĽ�ɫ���

			// �������棬ֻ��Ҫ����һ����־
			dm.setSingleLogThisAction(true);

			doe = new DataObject();
			doe.setModelName("T_RIGHT_ROLEDIC");
			doe.setString("SID", G.getSequence("SEQ_T_IWL", dm));
			doe.setString("ROLEID", strNewRoleID);
			doe.setString("ROLENAME", roleForm.getRolename());
			doe.setString("STATE", "1");
			dm.insert(doe);
			if (roleList != null) {
				for (int i = 0; i < roleList.length; i++) {
					doe = new DataObject();
					doe.setModelName("T_RIGHT_ROLERIGHT");
					doe.setString("SID", G.getSequence("SEQ_T_IWL", dm));
					doe.setString("ROLEID", strNewRoleID);
					doe.setString("RIGHTID", roleList[i].trim());
					dm.addBatch(doe);
				}
				dm.insertBatch();
			}
			doe.setModelName("T_RIGHT_USERROLE");
			doe.setString("SID", G.getSequence("SEQ_T_IWL", dm));
			doe.setString("ROLEID", strNewRoleID);
			doe.setString("EMPID", useId);
			doe.setString("UPTUSER", useId);
			doe.setString("CRTUSER", useId);
			dm.insert(doe);
			if (!"0".equals(useId)) {// �������ϵͳ����Ա�����ý�ɫĬ�ϸ�ֵ������Ա
				doe.setModelName("T_RIGHT_USERROLE");
				doe.setString("SID", G.getSequence("SEQ_T_IWL", dm));
				doe.setString("ROLEID", strNewRoleID);
				doe.setString("EMPID", "0");
				doe.setString("UPTUSER", useId);
				doe.setString("CRTUSER", useId);
				dm.insert(doe);
			}
		}

		if (roleForm.getDisplay().equals("edit")) {
			// �鿴�Ƿ�ΪϵͳĬ�Ͻ�ɫ
			strSql = "select type from t_right_roledic where roleid='" + roleid
					+ "'";
			DataObject db = dm.findByPrimaryKey(strSql, null);
			if (db != null) {
				// ����ϵͳĬ�Ͻ�ɫ�ſ���ɾ��,������ɾ��
				if ("1".equals(db.getString("TYPE"))) {
					throw new GeneralException("error.EditDefaultRole");
				} else {

					strSql = "select * from T_RIGHT_ROLEDIC where ROLENAME='"
							+ roleForm.getRolename()
							+ "' and state=1 and roleid<>'" + roleid + "'";
					db = dm.findByPrimaryKey(strSql, null);
					if (db != null) {
						throw new GeneralException("error.SameRoleName");
					}

					strSql = "update T_RIGHT_ROLEDIC set ROLENAME='"
							+ roleForm.getRolename() + "' where roleid='"
							+ roleid + "'";
					dm.update(strSql, null);

					// ɾ����ɫ�е�Ȩ����Ϣ
					// �ҵ�ԭ�򣺹��Ƴ����˵ݹ���������ɾ������
					// ����д����Ȼɾ������ liss/2006-11-27
					// strSql = "delete from T_RIGHT_ROLERIGHT t where roleid='"
					// + roleid;
					// strSql += "' and F_EMPHASRIGHT("+ useId +
					// ",t.rightid)=1";

					strSql = "select sid from T_RIGHT_ROLERIGHT t where roleid='";
					strSql += roleid + "' and F_EMPHASRIGHT(" + useId
							+ ",t.rightid)=1";
					Iterator<DataObject> it = dm.find(strSql, null).iterator();
					strSql = "-100";
					while (it.hasNext()) {
						strSql += ","
								+ ((DataObject) it.next()).getString("sid");
					}
					strSql = "delete from T_RIGHT_ROLERIGHT a where a.sid in("
							+ strSql;
					strSql += ")";

					// strSql = "delete from T_RIGHT_ROLERIGHT t where roleid='"
					// + roleid+ "'";

					dm.delete(strSql, null);

					if (roleList != null) {
						for (int i = 0; i < roleList.length; i++) {
							doe = new DataObject();
							doe.setModelName("T_RIGHT_ROLERIGHT");
							doe.setString("SID", G.getSequence("SEQ_T_IWL", dm));
							doe.setString("ROLEID", roleid);
							doe.setString("RIGHTID", roleList[i].trim());
							dm.addBatch(doe);
						}
						dm.insertBatch();
					}
				}
			}
		}
	}

}
