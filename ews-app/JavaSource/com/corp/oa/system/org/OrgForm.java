package com.corp.oa.system.org;

import java.util.ArrayList;
import java.util.Collection;

import com.corp.frame.action.FrameForm;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.dao.Pagination;
import com.corp.frame.core.metadata.QueryView;

public class OrgForm extends FrameForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2589274176591610554L;

	// ���ص���ʵ�ַ���
	private String divString;

	private String button_flag;

	// ��ѡ��ͼƬ��·��
	private String unchkImg;

	private String chkImg;

	// ��ʾ���ṹ�ı���
	private String idx = "0"; // ����

	private String rowID = "0"; // ���š�ְ����ڲ���ţ�Ĭ��Ϊ���ڵ�

	private String divid = "Dir_0_"; // ��ǰ��ʾ��DIV��id

	private String ckbDisp = "000"; // �ж��Ƿ���ʾ��ѡ����λ���ֱַ���� ���� ְ��
									// ��Ա��ÿ������Ϊ������0��ʱ����ʾ��ѡ��

	// �ύ�ĵ�λ/ְ����
	private String dept_sid;

	// �ύ��Ա�����
	private String emp_sid;

	// �Ƿ����������
	private String deptAdd;

	private String dutyClass;

	// �����û������ͼƬ��ID����;����������ṹ��������ṹ�۵�
	private String collapseImg = "";

	// ����Action�еĵ�λ����ְ����Ϣ
	private Collection<DataObject> deptInfo = new ArrayList<DataObject>();

	// ����Action�е�Ա����Ϣ
	private Collection<DataObject> empInfo = new ArrayList<DataObject>();

	// �����û���Ϣ�����ݿ����
	private DataObject t_INFO_DEPT = new DataObject("T_INFO_DEPT");

	private DataObject t_INFO_EMPLOYEE = new DataObject("T_INFO_EMPLOYEE");

	private DataObject t_INFO_EXPRESS_EMPLOYEE = new DataObject(
			"T_INFO_EXPRESS_EMPLOYEE");

	private String dept_emp;

	private String sort;

	// ���ڵ��SID
	private String parentDept;

	// �Ա��б�
	private Collection<DataObject> sexList = new ArrayList<DataObject>();

	// ��֯�ṹ�б�
	private Collection<DataObject> orgList = new ArrayList<DataObject>();

	// ְ��ṹ�б�
	private Collection<DataObject> dutyList = new ArrayList<DataObject>();

	// ����ҵ����Ա�˻���ˮ�˵���Ϣ
	private Collection<DataObject> accountList = new ArrayList<DataObject>();

	// ��Ա��Ƭ���ļ�·��
	private String photoPath = "";

	// ��ҳ����
	private Pagination pagination = new Pagination(20, 1);
	// �߼���ѯ����
	private QueryView queryView = new QueryView();

	// ��ǰ��ʾ��ҳ��
	private int current;

	// Ԥ��ʱ���õ�Ա������
	private String pret = "";
	// Ա���ı�ţ����ڲ����
	private String empId = "";
	// �洢һЩһ����Ϣ��dob
	private DataObject dob_Ex_EMP_Info = new DataObject();
	// ҵ��Աִ���������Ϣ
	private Collection<DataObject> taskList = new ArrayList<DataObject>();

	private String duty[];

	// ����Action��������רҵ��Ĳ���
	private String transInfo;

	private String ymima;

	private String xmima;

	// ��ͬ������ʵͼƬ��·��
	private String dwImg_open;

	private String dwImg_close;

	private String bmImg_open;

	private String bmImg_close;

	private String ryImg;

	// ����������ṹѡ�е�node��sid�Լ����ڵ��sid;
	private String org_psid;
	private String org_sid;

	private String json_string;

	private String type;

	public OrgForm() {
	}

	public String getDivString() {
		return divString;
	}

	public void setDivString(String divString) {
		this.divString = divString;
	}

	public String getButton_flag() {
		return button_flag;
	}

	public void setButton_flag(String button_flag) {
		this.button_flag = button_flag;
	}

	public String getUnchkImg() {
		return unchkImg;
	}

	public void setUnchkImg(String unchkImg) {
		this.unchkImg = unchkImg;
	}

	public String getChkImg() {
		return chkImg;
	}

	public void setChkImg(String chkImg) {
		this.chkImg = chkImg;
	}

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getRowID() {
		return rowID;
	}

	public void setRowID(String rowID) {
		this.rowID = rowID;
	}

	public String getDivid() {
		return divid;
	}

	public void setDivid(String divid) {
		this.divid = divid;
	}

	public String getCkbDisp() {
		return ckbDisp;
	}

	public void setCkbDisp(String ckbDisp) {
		this.ckbDisp = ckbDisp;
	}

	public String getDept_sid() {
		return dept_sid;
	}

	public void setDept_sid(String dept_sid) {
		this.dept_sid = dept_sid;
	}

	public String getEmp_sid() {
		return emp_sid;
	}

	public void setEmp_sid(String emp_sid) {
		this.emp_sid = emp_sid;
	}

	public String getDeptAdd() {
		return deptAdd;
	}

	public void setDeptAdd(String deptAdd) {
		this.deptAdd = deptAdd;
	}

	public String getDutyClass() {
		return dutyClass;
	}

	public void setDutyClass(String dutyClass) {
		this.dutyClass = dutyClass;
	}

	public String getCollapseImg() {
		return collapseImg;
	}

	public void setCollapseImg(String collapseImg) {
		this.collapseImg = collapseImg;
	}

	public Collection<DataObject> getDeptInfo() {
		return deptInfo;
	}

	public void setDeptInfo(Collection<DataObject> deptInfo) {
		this.deptInfo = deptInfo;
	}

	public Collection<DataObject> getEmpInfo() {
		return empInfo;
	}

	public void setEmpInfo(Collection<DataObject> empInfo) {
		this.empInfo = empInfo;
	}

	public DataObject getT_INFO_DEPT() {
		return t_INFO_DEPT;
	}

	public void setT_INFO_DEPT(DataObject t_INFO_DEPT) {
		this.t_INFO_DEPT = t_INFO_DEPT;
	}

	public DataObject getT_INFO_EMPLOYEE() {
		return t_INFO_EMPLOYEE;
	}

	public void setT_INFO_EMPLOYEE(DataObject t_INFO_EMPLOYEE) {
		this.t_INFO_EMPLOYEE = t_INFO_EMPLOYEE;
	}

	public DataObject getT_INFO_EXPRESS_EMPLOYEE() {
		return t_INFO_EXPRESS_EMPLOYEE;
	}

	public void setT_INFO_EXPRESS_EMPLOYEE(DataObject t_INFO_EXPRESS_EMPLOYEE) {
		this.t_INFO_EXPRESS_EMPLOYEE = t_INFO_EXPRESS_EMPLOYEE;
	}

	public String getDept_emp() {
		return dept_emp;
	}

	public void setDept_emp(String dept_emp) {
		this.dept_emp = dept_emp;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getParentDept() {
		return parentDept;
	}

	public void setParentDept(String parentDept) {
		this.parentDept = parentDept;
	}

	public Collection<DataObject> getSexList() {
		return sexList;
	}

	public void setSexList(Collection<DataObject> sexList) {
		this.sexList = sexList;
	}

	public Collection<DataObject> getOrgList() {
		return orgList;
	}

	public void setOrgList(Collection<DataObject> orgList) {
		this.orgList = orgList;
	}

	public Collection<DataObject> getDutyList() {
		return dutyList;
	}

	public void setDutyList(Collection<DataObject> dutyList) {
		this.dutyList = dutyList;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public String getPret() {
		return pret;
	}

	public void setPret(String pret) {
		this.pret = pret;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String[] getDuty() {
		return duty;
	}

	public void setDuty(String[] duty) {
		this.duty = duty;
	}

	public String getTransInfo() {
		return transInfo;
	}

	public void setTransInfo(String transInfo) {
		this.transInfo = transInfo;
	}

	public String getYmima() {
		return ymima;
	}

	public void setYmima(String ymima) {
		this.ymima = ymima;
	}

	public String getXmima() {
		return xmima;
	}

	public void setXmima(String xmima) {
		this.xmima = xmima;
	}

	public String getDwImg_open() {
		return dwImg_open;
	}

	public void setDwImg_open(String dwImg_open) {
		this.dwImg_open = dwImg_open;
	}

	public String getDwImg_close() {
		return dwImg_close;
	}

	public void setDwImg_close(String dwImg_close) {
		this.dwImg_close = dwImg_close;
	}

	public String getBmImg_open() {
		return bmImg_open;
	}

	public void setBmImg_open(String bmImg_open) {
		this.bmImg_open = bmImg_open;
	}

	public String getBmImg_close() {
		return bmImg_close;
	}

	public void setBmImg_close(String bmImg_close) {
		this.bmImg_close = bmImg_close;
	}

	public String getRyImg() {
		return ryImg;
	}

	public void setRyImg(String ryImg) {
		this.ryImg = ryImg;
	}

	public QueryView getQueryView() {
		return queryView;
	}

	public void setQueryView(QueryView queryView) {
		this.queryView = queryView;
	}

	public DataObject getDob_Ex_EMP_Info() {
		return dob_Ex_EMP_Info;
	}

	public void setDob_Ex_EMP_Info(DataObject dob_Ex_EMP_Info) {
		this.dob_Ex_EMP_Info = dob_Ex_EMP_Info;
	}

	public Collection<DataObject> getTaskList() {
		return taskList;
	}

	public void setTaskList(Collection<DataObject> taskList) {
		this.taskList = taskList;
	}

	public Collection<DataObject> getAccountList() {
		return accountList;
	}

	public void setAccountList(Collection<DataObject> accountList) {
		this.accountList = accountList;
	}

	public String getOrg_psid() {
		return org_psid;
	}

	public void setOrg_psid(String org_psid) {
		this.org_psid = org_psid;
	}

	public String getOrg_sid() {
		return org_sid;
	}

	public void setOrg_sid(String org_sid) {
		this.org_sid = org_sid;
	}

	public String getJson_string() {
		return json_string;
	}

	public void setJson_string(String json_string) {
		this.json_string = json_string;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
