package com.corp.oa.system.right;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.corp.frame.action.FrameForm;
import com.corp.frame.core.dao.DataObject;

/**
 * <p>
 * Title: 权限信息的窗体
 * </p>
 * <p>
 * 值班信息:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: net
 * </p>
 * 
 * @author liss
 * @version 1.0
 */

public class RightForm extends FrameForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5671745496190144649L;

	// 保存查看、修改权限的人员内部编号
	private String empsid;

	// 人员的名称
	private String txt_ToUser;

	// 员工拥有的的操作权限，不包括角色中的权限
	private Collection<DataObject> rightlist = new ArrayList<DataObject>();

	// 员工已有的角色
	private Collection<DataObject> emphasrole = new ArrayList<DataObject>();

	// 员工还没有的角色
	private Collection<DataObject> rightrole = new ArrayList<DataObject>();

	// 员工拥有的所有的操作权限，包括角色中的权限
	private Collection<DataObject> totalrightlist = new ArrayList<DataObject>();

	// 保存提交上来的权限列表
	private String[] emprightlist;

	// 保存提交上来的角色列表
	private String[] emprolelist;

	// 权限和角色分别操作，依此判别式角色操作还是权限操作
	private boolean boptrole = true;

	// 按角色设置是保存用户的列表
	private Collection<DataObject> emplist = new ArrayList<DataObject>();

	// 按角色设置时的角色信息列表
	private Collection<DataObject> roleinfolist = new ArrayList<DataObject>();

	// 当前选择的角色编号
	private String curroleid;

	private com.corp.frame.core.dao.DataObject t_role_id = new com.corp.frame.core.dao.DataObject(
			"t_right_roledic");

	// 返回角色列表中的员工
	private String[] roleemplist;

	public RightForm() {
	}

	public String getEmpsid() {
		return empsid;
	}

	public void setEmpsid(String empsid) {
		this.empsid = empsid;
	}

	public String getTxt_ToUser() {
		return txt_ToUser;
	}

	public void setTxt_ToUser(String txt_ToUser) {
		this.txt_ToUser = txt_ToUser;
	}

	public Collection<DataObject> getRightlist() {
		return rightlist;
	}

	public void setRightlist(Collection<DataObject> rightlist) {
		this.rightlist = rightlist;
	}

	public Collection<DataObject> getEmphasrole() {
		return emphasrole;
	}

	public void setEmphasrole(Collection<DataObject> emphasrole) {
		this.emphasrole = emphasrole;
	}

	public Collection<DataObject> getRightrole() {
		return rightrole;
	}

	public void setRightrole(Collection<DataObject> rightrole) {
		this.rightrole = rightrole;
	}

	public String[] getEmprightlist() {
		return emprightlist;
	}

	public void setEmprightlist(String[] emprightlist) {
		this.emprightlist = emprightlist;
	}

	public String[] getEmprolelist() {
		return emprolelist;
	}

	public void setEmprolelist(String[] emprolelist) {
		this.emprolelist = emprolelist;
	}

	public boolean isBoptrole() {
		return boptrole;
	}

	public void setBoptrole(boolean boptrole) {
		this.boptrole = boptrole;
	}

	public Collection<DataObject> getTotalrightlist() {
		return totalrightlist;
	}

	public void setTotalrightlist(Collection<DataObject> totalrightlist) {
		this.totalrightlist = totalrightlist;
	}

	public Collection<DataObject> getEmplist() {
		return emplist;
	}

	public void setEmplist(Collection<DataObject> emplist) {
		this.emplist = emplist;
	}

	public Collection<DataObject> getRoleinfolist() {
		return roleinfolist;
	}

	public void setRoleinfolist(Collection<DataObject> roleinfolist) {
		this.roleinfolist = roleinfolist;
	}

	/**
	 * prefixAction
	 * 
	 * @param mapping
	 *            ActionMapping
	 * @param request
	 *            HttpServletRequest
	 * @return ActionErrors
	 * @todo Implement this com.corp.frame.action.BusinessForm method
	 */
	public ActionErrors prefixAction(ActionMapping mapping,
			HttpServletRequest request) {

		if (request.getParameter("b_saverole") != null) {// 用户点击的是保存角色信息
			this.boptrole = true;
		} else if (request.getParameter("b_saveright") != null) {// 用户点击的是保存权限信息
			this.boptrole = false;
		}
		return null;
	}

	public String getCurroleid() {
		return curroleid;
	}

	public void setCurroleid(String curroleid) {
		this.curroleid = curroleid;
	}

	public com.corp.frame.core.dao.DataObject getT_role_id() {
		return t_role_id;
	}

	public void setT_role_id(com.corp.frame.core.dao.DataObject t_role_id) {
		this.t_role_id = t_role_id;
	}

	public String[] getRoleemplist() {
		return roleemplist;
	}

	public void setRoleemplist(String[] roleemplist) {
		this.roleemplist = roleemplist;
	}
}
