package com.corp.oa.menu;

import java.util.Collection;
import java.util.Iterator;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.base.UserInfo;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.util.StringUtil;

/**
 *
 * <p>
 * Title: 动态加载左边菜单
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
 * @author 李恕圣 2006/07/05
 * @version 1.0
 */

public class MenuAction implements BusinessAction {
	private final static String OutBarFolder = "OutBarFolder";

	private final static String arrayBegin = "=new Array(";

	private final static String arrayEnd = ");";

	private final static String f1 = "'";

	private final static String f2 = ",";

	private final static String f3 = "'parent.parent.right'";

	// 获得菜单根目录记录集的SQL
	private final static String sqlRoot = "select T.sid,T.menuname,MENUNAME_EN,MENUNAME_JP,T.IMAGENAME  from t_ews_func_menu T where state=1 and T.PARENTSID=0 order by OPTORDER";

	// 获得相应子目录记录集的SQL
	private final static String sqlNode = "select T.menuname,MENUNAME_EN,MENUNAME_JP,t.POSTURL,T.IMAGENAME ,T.RIGHTID from t_ews_func_menu T where state=1 and T.PARENTSID=? order by OPTORDER";

	public MenuAction() {
	}

	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		MenuForm userForm = (MenuForm) parm1;

		// 获得操作人员的信息
		UserInfo userView = userForm.getUserView();
		// 内部编号
		String useId = userView.getUserId();
		String menus[] = getMenu(dm, useId, "zh_CN");

		// 获得菜单
		userForm.setMenus(menus[0]);
		userForm.setMenuimg((menus[1]));
	}

	// 获得菜单
	@SuppressWarnings("unchecked")
	private String[] getMenu(DataManager dm, String useId, String lang)
			throws Exception {
		// 获得用户所有的权限
		String rightlist = getEmpRightList(dm, useId);

		String result[] = new String[2];
		result[0] = "";
		result[1] = "";

		int i = 0;
		dm.setCheckPermission(false);
		Iterator<DataObject> it = dm.find(sqlRoot, null).iterator();
		DataObject dol = null;
		String menuName = "menuname";
		if ("zh_cn".equals(lang)) {
			menuName = "menuname";
		} else if ("en".equals(lang)) {
			menuName = "menuname_en";
		} else if ("ja".equals(lang)) {
			menuName = "menuname_jp";
		}
		while (it.hasNext()) {

			dol = (DataObject) it.next();
			if (StringUtil.isNotNull(getNodeMenus(dol.getString("sid"), dm,
					rightlist, lang))) {
				i++;
				result[1] = result[1] + dol.getString("IMAGENAME") + ";";

				// 获得父节点
				result[0] = result[0] + OutBarFolder + i + arrayBegin + f1
						+ dol.getString(menuName) + f1 + f2;

				/** ********获得子菜单的内容************** */
				result[0] = result[0]
						+ getNodeMenus(dol.getString("sid"), dm, rightlist,
								lang);
				/** *********************************** */
				result[0] = result[0].substring(0, result[0].length() - 1);
				// 节点结束
				result[0] = result[0] + arrayEnd;
			}
		}
		return result;
	}

	//
	// /**
	// * 参数:父节点编号 返回:父节点对应的子目录
	// */
	// private String getNodeMenu(String sid, DataManager dm, String useId,
	// String lang) throws Exception {
	// {
	// String result = "";
	// Collection col = dm.find(sqlNode, sid);
	// Iterator it = col.iterator();
	// DataObject dol = null;
	// String menuName = "menuname";
	// if ("zh_cn".equals(lang)) {
	// menuName = "menuname";
	// } else if ("en".equals(lang)) {
	// menuName = "menuname_en";
	// } else if ("ja".equals(lang)) {
	// menuName = "menuname_jp";
	// }
	// while (it.hasNext()) {
	// dol = (DataObject) it.next();
	// if (getRightNodeMenu(dm, dol.getString("RIGHTID"), useId))
	// result = result + f1 + dol.getString("IMAGENAME") + f1 + f2
	// + f1 + dol.getString(menuName) + f1 + f2 + f1
	// + dol.getString("POSTURL") + f1 + f2 + f3 + f2;
	// }
	//
	// return result;
	// }
	//
	// }

	/**
	 * 参数:父节点编号 返回:父节点对应的子目录
	 */
	@SuppressWarnings("unchecked")
	private String getNodeMenus(String sid, DataManager dm, String rightlist,
			String lang) throws Exception {
		{
			String result = "";
			Collection<DataObject> col = dm.find(sqlNode, sid);
			Iterator<DataObject> it = col.iterator();
			DataObject dol = null;
			String menuName = "menuname";
			if ("zh_cn".equals(lang)) {
				menuName = "menuname";
			} else if ("en".equals(lang)) {
				menuName = "menuname_en";
			} else if ("ja".equals(lang)) {
				menuName = "menuname_jp";
			}
			while (it.hasNext()) {
				dol = (DataObject) it.next();
				if (isRightMenu(rightlist, dol.getString("RIGHTID")))
					result = result + f1 + dol.getString("IMAGENAME") + f1 + f2
							+ f1 + dol.getString(menuName) + f1 + f2 + f1
							+ dol.getString("POSTURL") + f1 + f2 + f3 + f2;
			}

			return result;
		}

	}

	/**
	 * 参数: menuRightId 节点的权限编号 empsid 员工的内部编号 返回值:true 有权限 false 无权限
	 */

	private static boolean getRightNodeMenu(DataManager dm, String menuRightId,
			String empsid) throws Exception {
		int iempsid = Integer.parseInt(empsid);
		String sql = "select distinct F_EMPHASRIGHT(" + iempsid + ","
				+ menuRightId + ",0)as right from t_right_rightdic";
		DataObject dob = (DataObject) dm.findByPrimaryKey(sql, null);
		if (dob != null) {
			if ("1".equals(dob.getString("right")))
				return true;
			else
				return false;
		}
		return false;
	}

	/**
	 * 功能: 判断每个节点是否有权限 参数: menuRightId 节点的权限编号数组 empsid 员工的内部编号 返回值:0,1,1
	 *
	 */
	public static String IsRight(DataManager dm, String[] menuRightIds,
			String empsid) throws Exception {
		String result = "", flag = "";

		for (int i = 0; i < menuRightIds.length; i++) {
			if (getRightNodeMenu(dm, menuRightIds[i], empsid))
				flag = "1";
			else
				flag = "0";
			result += flag + ",";
		}

		return result.substring(0, result.length() - 1);

	}

	/**
	 * dm 数据库 empsid 员工的内部编号 返回值: 拥有的权限列表
	 */

	public static String getEmpRightList(DataManager dm, String empsid)
			throws Exception {
		int iempsid = Integer.parseInt(empsid);
		String sql = "select  F_EMPHASRIGHTLIST(" + iempsid
				+ ")as rightlist from t_right_rightdic";
		DataObject dob = (DataObject) dm.findByPrimaryKey(sql, null);
		if (dob == null)
			return "";
		else
			return dob.getString("rightlist");

	}

	public static boolean isRightMenu(String EmpRightList, String menuRightId)
			throws Exception {
		if (EmpRightList == null)

			return false;
		if (menuRightId == null)
			return true;
		return (EmpRightList.indexOf(menuRightId) >= 0);

	}

}
