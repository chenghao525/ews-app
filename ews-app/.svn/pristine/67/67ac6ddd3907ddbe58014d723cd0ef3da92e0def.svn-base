package com.corp.oa.menu;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.corp.frame.action.FrameForm;

public class MenuForm extends FrameForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1636038736163433628L;

	private String menus;

	// 2级菜单图片的集合
	private String menuimg;

	private boolean logout;

	private String sessionId;

	private String flag;

	public MenuForm() {
	}

	public String getMenus() {
		return menus;
	}

	public void setMenus(String menus) {
		this.menus = menus;
	}

	public boolean isLogout() {
		return logout;
	}

	public void setLogout(boolean logout) {
		this.logout = logout;
	}

	public ActionErrors prefixAction(ActionMapping mapping,
			HttpServletRequest request) {
		this.sessionId = request.getSession().getId();
		return null;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMenuimg() {
		return menuimg;
	}

	public void setMenuimg(String menuimg) {
		this.menuimg = menuimg;
	}

}
