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
 * 保存角色信息:
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
		// 设置页码
		roleForm.getPagination().setCurrent(roleForm.getCurrent());

		// 获得操作人员的信息
		UserInfo userView = roleForm.getUserView();

		// 内部编号
		String useId = userView.getUserId();

		// 角色编号
		String roleid = roleForm.getRoleid();

		// 提交的权限编号
		String[] roleList = roleForm.getRolerightlist();

		String strSql = "";

		DataObject doe = new DataObject();

		// 新生成的角色编号
		String strNewRoleID = "";

		// System.out.println("lishush+++++++++++++++++++");
		if (roleForm.getDisplay().equals("add")) { // 添加新角色

			strSql = "select * from T_RIGHT_ROLEDIC where ROLENAME='"
					+ roleForm.getRolename() + "' and state=1";
			doe = dm.findByPrimaryKey(strSql, null);
			if (doe != null) {
				throw new GeneralException("error.SameRoleName");
			}

			strNewRoleID = G.getColumMaxID(dm, "T_RIGHT_ROLEDIC", "ROLEID",
					"1", 7); // 保存新的角色编号

			// 批量保存，只需要保存一条日志
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
			if (!"0".equals(useId)) {// 如果不是系统管理员，将该角色默认赋值给管理员
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
			// 查看是否为系统默认角色
			strSql = "select type from t_right_roledic where roleid='" + roleid
					+ "'";
			DataObject db = dm.findByPrimaryKey(strSql, null);
			if (db != null) {
				// 不是系统默认角色才可以删除,否则不能删除
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

					// 删除角色中的权限信息
					// 找到原因：估计出现了递归现象，所以删除不了
					// 如下写语句居然删除不掉 liss/2006-11-27
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
