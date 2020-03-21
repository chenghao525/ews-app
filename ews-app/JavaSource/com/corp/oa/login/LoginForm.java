package com.corp.oa.login;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.corp.frame.action.Constant;
import com.corp.servlet.ValidateNumberServlet;

/**
 * 
 * <p>
 * Title: 用户登陆的Form信息
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

public class LoginForm extends ActionForm {

	// 用户登陆信息

	/**
	 * 
	 */
	private static final long serialVersionUID = -6680671774711736618L;

	// 用户编号
	private String empid;

	// 用户密码
	private String emppwd;

	// 验证码
	private String validatenum;

	private String isright;

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getEmppwd() {
		return emppwd;
	}

	public void setEmppwd(String emppwd) {
		this.emppwd = emppwd;
	}

	public String getValidatenum() {
		return validatenum;
	}

	public void setValidatenum(String validatenum) {
		this.validatenum = validatenum;
	}

	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {

		String vnSession = (String) httpServletRequest.getSession().getAttribute(
				ValidateNumberServlet.SESS_VALIDATE_NUM);

		String vn = getValidatenum();
		ActionErrors errors = new ActionErrors();

		try {
			if (vnSession != null && !"".equals(vnSession)) {
				if (vnSession.equalsIgnoreCase(vn)) {// 验证码一致
					httpServletRequest.getSession().removeAttribute(Constant.EXCEPTION);
					httpServletRequest.getSession().removeAttribute(
							ValidateNumberServlet.SESS_VALIDATE_NUM);

				} else {// 验证码不一致
					httpServletRequest.getSession().setAttribute(Constant.EXCEPTION, "验证码输入错误");
					errors.add("validatenum", new ActionMessage("验证码输入错误"));
					return errors;
				}
			}
		} catch (Exception ex) {
			// Debug.error(ex);
			httpServletRequest.setAttribute(Constant.EXCEPTION, ex);

			return null;
		}
		return null;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	}

	public String getIsright() {
		return isright;
	}

	public void setIsright(String isright) {
		this.isright = isright;
	}
}
