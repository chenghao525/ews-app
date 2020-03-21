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
 * Title: Ȩ����Ϣ�Ĵ���
 * </p>
 * <p>
 * ֵ����Ϣ:
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

	// ����鿴���޸�Ȩ�޵���Ա�ڲ����
	private String empsid;

	// ��Ա������
	private String txt_ToUser;

	// Ա��ӵ�еĵĲ���Ȩ�ޣ���������ɫ�е�Ȩ��
	private Collection<DataObject> rightlist = new ArrayList<DataObject>();

	// Ա�����еĽ�ɫ
	private Collection<DataObject> emphasrole = new ArrayList<DataObject>();

	// Ա����û�еĽ�ɫ
	private Collection<DataObject> rightrole = new ArrayList<DataObject>();

	// Ա��ӵ�е����еĲ���Ȩ�ޣ�������ɫ�е�Ȩ��
	private Collection<DataObject> totalrightlist = new ArrayList<DataObject>();

	// �����ύ������Ȩ���б�
	private String[] emprightlist;

	// �����ύ�����Ľ�ɫ�б�
	private String[] emprolelist;

	// Ȩ�޺ͽ�ɫ�ֱ�����������б�ʽ��ɫ��������Ȩ�޲���
	private boolean boptrole = true;

	// ����ɫ�����Ǳ����û����б�
	private Collection<DataObject> emplist = new ArrayList<DataObject>();

	// ����ɫ����ʱ�Ľ�ɫ��Ϣ�б�
	private Collection<DataObject> roleinfolist = new ArrayList<DataObject>();

	// ��ǰѡ��Ľ�ɫ���
	private String curroleid;

	private com.corp.frame.core.dao.DataObject t_role_id = new com.corp.frame.core.dao.DataObject(
			"t_right_roledic");

	// ���ؽ�ɫ�б��е�Ա��
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

		if (request.getParameter("b_saverole") != null) {// �û�������Ǳ����ɫ��Ϣ
			this.boptrole = true;
		} else if (request.getParameter("b_saveright") != null) {// �û�������Ǳ���Ȩ����Ϣ
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
