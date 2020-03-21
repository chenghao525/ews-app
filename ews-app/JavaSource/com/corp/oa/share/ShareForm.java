package com.corp.oa.share;

import java.util.ArrayList;
import java.util.Collection;

import com.corp.frame.action.FrameForm;
import com.corp.frame.core.dao.DataObject;

public class ShareForm extends FrameForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1289935672110884348L;

	public ShareForm() {
	}

	// 返回的现实字符串
	private String divString;

	// 不同类别的现实图片的路径
	private String dwImg_open;

	private String dwImg_close;

	private String bmImg_open;

	private String bmImg_close;

	private String ryImg;

	private String chkImg_chk;

	private String chkImg_unchk;

	// 标识选择人员的Img 的ID打头标志
	private String empChkHead;

	// 提交的单位/职务编号
	private String dept_sid;

	// 提交的员工编号
	private String emp_sid;

	private String empdutyFlag;

	// 是否是添加数据
	private String deptAdd;

	// 保存Action中的单位部门职务信息
	private Collection<DataObject> deptInfo = new ArrayList<DataObject>();

	// 保存Action中的员工信息
	private Collection<DataObject> empInfo = new ArrayList<DataObject>();

	// 处理用户信息的数据库对象
	private com.corp.frame.core.dao.DataObject t_INFO_DEPT = new com.corp.frame.core.dao.DataObject(
			"T_INFO_DEPT");

	private com.corp.frame.core.dao.DataObject t_INFO_EMPLOYEE = new com.corp.frame.core.dao.DataObject(
			"T_INFO_EMPLOYEE");

	// 性别列表
	private Collection<DataObject> sexList = new ArrayList<DataObject>();

	// 组织结构列表
	private Collection<DataObject> orgList = new ArrayList<DataObject>();

	// 职务结构列表
	private Collection<DataObject> dutyList = new ArrayList<DataObject>();

	private String itemsid;

	public String getBmImg_close() {
		return bmImg_close;
	}

	public void setBmImg_close(String bmImg_close) {
		this.bmImg_close = bmImg_close;
	}

	public void setBmImg_open(String bmImg_open) {
		this.bmImg_open = bmImg_open;
	}

	public void setDept_sid(String dept_sid) {
		this.dept_sid = dept_sid;
	}

	public void setDeptAdd(String deptAdd) {
		this.deptAdd = deptAdd;
	}

	public void setDeptInfo(Collection<DataObject> deptInfo) {
		this.deptInfo = deptInfo;
	}

	public void setDivString(String divString) {
		this.divString = divString;
	}

	public void setDutyList(Collection<DataObject> dutyList) {
		this.dutyList = dutyList;
	}

	public void setDwImg_close(String dwImg_close) {
		this.dwImg_close = dwImg_close;
	}

	public void setDwImg_open(String dwImg_open) {
		this.dwImg_open = dwImg_open;
	}

	public void setEmp_sid(String emp_sid) {
		this.emp_sid = emp_sid;
	}

	public void setEmpInfo(Collection<DataObject> empInfo) {
		this.empInfo = empInfo;
	}

	public void setOrgList(Collection<DataObject> orgList) {
		this.orgList = orgList;
	}

	public void setRyImg(String ryImg) {
		this.ryImg = ryImg;
	}

	public void setSexList(Collection<DataObject> sexList) {
		this.sexList = sexList;
	}

	public void setT_INFO_DEPT(com.corp.frame.core.dao.DataObject t_INFO_DEPT) {
		this.t_INFO_DEPT = t_INFO_DEPT;
	}

	public void setT_INFO_EMPLOYEE(
			com.corp.frame.core.dao.DataObject t_INFO_EMPLOYEE) {
		this.t_INFO_EMPLOYEE = t_INFO_EMPLOYEE;
	}

	public com.corp.frame.core.dao.DataObject getT_INFO_EMPLOYEE() {
		return t_INFO_EMPLOYEE;
	}

	public com.corp.frame.core.dao.DataObject getT_INFO_DEPT() {
		return t_INFO_DEPT;
	}

	public Collection<DataObject> getSexList() {
		return sexList;
	}

	public String getRyImg() {
		return ryImg;
	}

	public Collection<DataObject> getOrgList() {
		return orgList;
	}

	public Collection<DataObject> getEmpInfo() {
		return empInfo;
	}

	public String getEmp_sid() {
		return emp_sid;
	}

	public String getDwImg_open() {
		return dwImg_open;
	}

	public String getDwImg_close() {
		return dwImg_close;
	}

	public Collection<DataObject> getDutyList() {
		return dutyList;
	}

	public String getDivString() {
		return divString;
	}

	public Collection<DataObject> getDeptInfo() {
		return deptInfo;
	}

	public String getDeptAdd() {
		return deptAdd;
	}

	public String getDept_sid() {
		return dept_sid;
	}

	public String getBmImg_open() {
		return bmImg_open;
	}

	public String getChkImg_chk() {
		return chkImg_chk;
	}

	public String getChkImg_unchk() {
		return chkImg_unchk;
	}

	public void setChkImg_chk(String chkImg_chk) {
		this.chkImg_chk = chkImg_chk;
	}

	public void setChkImg_unchk(String chkImg_unchk) {
		this.chkImg_unchk = chkImg_unchk;
	}

	public String getEmpChkHead() {
		return empChkHead;
	}

	public void setEmpChkHead(String empChkHead) {
		this.empChkHead = empChkHead;
	}

	public String getItemsid() {
		return itemsid;
	}

	public void setItemsid(String itemsid) {
		this.itemsid = itemsid;
	}

	public String getEmpdutyFlag() {
		return empdutyFlag;
	}

	public void setEmpdutyFlag(String empdutyFlag) {
		this.empdutyFlag = empdutyFlag;
	}

}
