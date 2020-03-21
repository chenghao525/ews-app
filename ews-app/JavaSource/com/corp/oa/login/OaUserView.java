package com.corp.oa.login;

import java.io.UnsupportedEncodingException;

import com.corp.frame.core.base.SystemProperty;
import com.corp.frame.core.base.defaultdriver.UserView;
import com.corp.frame.core.dao.DataManager;

/**
 *
 * <p>
 * Title:用户的信息
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

public class OaUserView extends UserView {
	/**
	 * 返回数据权限
	 *
	 * @return java.lang.String
	 */
	public String getDataPermission() {
		DataManager dm = null;
		String strResult = "";
		try {
			dm = DataManager.getInstance();
			String strSql = "";
			strSql = "select F_EMPHASDATARIGHT(" + this.getUserId()
					+ ") as rt from t_ews_func_menu  where rownum<2";
			strResult = dm.findByPrimaryKey(strSql, null).getString("rt");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dm != null)
				dm.close();
		}

		if (strResult == null || strResult.equals(""))
			strResult = "-1";

		return strResult;
	}

	/**
	 * 判断用户是否拥有些功能权限
	 *
	 * @param string
	 *            String
	 * @return boolean
	 */
	public boolean hasPermission(String commandId) {
		DataManager dm = null;
		String strResult = "0";
		try {
			dm = DataManager.getInstance();

			String strSql = "";

			strSql = "select F_EMPHASRIGHT(" + this.getUserId() + ","
					+ commandId + ") as rt from t_ews_func_menu  where rownum<2";
			strResult = dm.findByPrimaryKey(strSql, null).getString("rt");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dm != null)
				dm.close();
		}

		return (!"0".equals(strResult));
	}

	private final static String TITLE = "title";

	private static String title;

	public String getTitle() {
		if (title == null) {
			title = SystemProperty.getProperty(TITLE);
			if (title == null) {
				title = "";
			}

			try {
				title = new String(title.getBytes("iso-8859-1"), "GBK");
			} catch (UnsupportedEncodingException ex) {
			}
		}
		return title;
	}

	public static void main(String[] args) {
		String title = null;// getTitle();
		System.out.println(title);

	}
}
