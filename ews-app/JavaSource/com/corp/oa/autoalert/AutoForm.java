package com.corp.oa.autoalert;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.corp.frame.action.FrameForm;

/**
 * <p>Title: 重要事务动态提醒的窗体</p>
 * <p></p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author liss
 * @version 1.0
 */

public class AutoForm extends FrameForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2839078429024428060L;

	/**
	 * Session Id
	 */
	private String sessionId;

	private boolean logout = false;

	//定时刷新的时间  单位 秒
	private String refreshTime = "0";

	//判断是否使用单用户登录
	private String loginSingle = "0";
	
	//新登记的运单
	private String regDly="0";
	
	//新录入信息的运单
	private String newDlyInfo="0";
	
	//新到多少条运单
	private String jpNewArrive="0";
	
	//运单等待配送
	private String dlySending="0";
	
	//运单等待配送结果
	private String dlySended="0";
	
	public AutoForm() {
	}

	public String getSessionId() {
		return sessionId;
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

	public String getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(String refreshTime) {
		this.refreshTime = refreshTime;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getLoginSingle() {
		return loginSingle;
	}

	public void setLoginSingle(String loginSingle) {
		this.loginSingle = loginSingle;
	}

	public String getDlySended() {
		return dlySended;
	}

	public void setDlySended(String dlySended) {
		this.dlySended = dlySended;
	}

	public String getDlySending() {
		return dlySending;
	}

	public void setDlySending(String dlySending) {
		this.dlySending = dlySending;
	}

	public String getJpNewArrive() {
		return jpNewArrive;
	}

	public void setJpNewArrive(String jpNewArrive) {
		this.jpNewArrive = jpNewArrive;
	}

	public String getNewDlyInfo() {
		return newDlyInfo;
	}

	public void setNewDlyInfo(String newDlyInfo) {
		this.newDlyInfo = newDlyInfo;
	}

	public String getRegDly() {
		return regDly;
	}

	public void setRegDly(String regDly) {
		this.regDly = regDly;
	}

}
