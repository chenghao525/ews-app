package com.corp.gps;

import java.util.ArrayList;
import java.util.Collection;

import com.corp.frame.action.FrameForm;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.dao.Pagination;
import com.corp.frame.core.metadata.QueryView;

public class GpsForm extends FrameForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8991271906175609066L;

	// �����û���Ϣ�����ݿ����
	private com.corp.frame.core.dao.DataObject t_GPS_SOURCE = new com.corp.frame.core.dao.DataObject(
			"T_GPS_SOURCE");

	// �����û���Ϣ�����ݿ����
	private com.corp.frame.core.dao.DataObject t_INFO_MOBILE = new com.corp.frame.core.dao.DataObject(
			"T_INFO_MOBILE");

	private QueryView queryView = new QueryView();

	// GPS��Ϣ�ļ���
	private Collection<DataObject> gpsList = new ArrayList<DataObject>();

	// GPS��Ϣ�ļ��� json strring
	private String gpsListJsonString;

	// ҵ��Ա����Ƭ��ַ
	private Collection<DataObject> empPhoto = new ArrayList<DataObject>();

	// �ն��豸�ļ���
	private Collection<DataObject> mpList = new ArrayList<DataObject>();

	// ��ҳ����
	private Pagination pagination = new Pagination();

	// �ϴ�ˢ�µ�ʱ��
	private String refreshTime;

	// ��ǰ��ʾ��ҳ��
	private int current;

	// ��ǰ��Ա���ڵĳ���
	private String city;

	private String mobileList;

	// �Ƿ��ǳ����û������Բ鿴���г���
	private String superRole;

	// gps���ݵ�SID�������չ켣����
	// Mobile��SID����춌��O���M���޸�
	private String sid;

	// �չ켣���ٵ�����
	private String monitorDay;

	// ��ǹ�ı��
	private String mobileID;

	// �ն˶������
	private String usermode;

	// ״̬�ı��
	private String[] status;
	// ״̬��list
	private Collection<DataObject> statusList;

	public String getGpsListJsonString() {
		return gpsListJsonString;
	}

	public void setGpsListJsonString(String gpsListJsonString) {
		this.gpsListJsonString = gpsListJsonString;
	}

	public Collection<DataObject> getStatusList() {
		return statusList;
	}

	public void setStatusList(Collection<DataObject> statusList) {
		this.statusList = statusList;
	}

	public String[] getStatus() {
		return status;
	}

	public void setStatus(String[] status) {
		this.status = status;
	}

	public String getUsermode() {
		return usermode;
	}

	public void setUsermode(String usermode) {
		this.usermode = usermode;
	}

	public com.corp.frame.core.dao.DataObject getT_INFO_MOBILE() {
		return t_INFO_MOBILE;
	}

	public void setT_INFO_MOBILE(
			com.corp.frame.core.dao.DataObject t_INFO_MOBILE) {
		this.t_INFO_MOBILE = t_INFO_MOBILE;
	}

	public Collection<DataObject> getMpList() {
		return mpList;
	}

	public void setMpList(Collection<DataObject> mpList) {
		this.mpList = mpList;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getMonitorDay() {
		return monitorDay;
	}

	public void setMonitorDay(String monitorDay) {
		this.monitorDay = monitorDay;
	}

	public String getMobileID() {
		return mobileID;
	}

	public void setMobileID(String mobileID) {
		this.mobileID = mobileID;
	}

	public String getMobileList() {
		return mobileList;
	}

	public void setMobileList(String mobileList) {
		this.mobileList = mobileList;
	}

	public String getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(String refreshTime) {
		this.refreshTime = refreshTime;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSuperRole() {
		return superRole;
	}

	public void setSuperRole(String superRole) {
		this.superRole = superRole;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public com.corp.frame.core.dao.DataObject getT_GPS_SOURCE() {
		return t_GPS_SOURCE;
	}

	public void setT_GPS_SOURCE(com.corp.frame.core.dao.DataObject t_GPS_SOURCE) {
		this.t_GPS_SOURCE = t_GPS_SOURCE;
	}

	public Collection<DataObject> getEmpPhoto() {
		return empPhoto;
	}

	public void setEmpPhoto(Collection<DataObject> empPhoto) {
		this.empPhoto = empPhoto;
	}

	public Collection<DataObject> getGpsList() {
		return gpsList;
	}

	public void setGpsList(Collection<DataObject> gpsList) {
		this.gpsList = gpsList;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public QueryView getQueryView() {
		return queryView;
	}

	public void setQueryView(QueryView queryView) {
		this.queryView = queryView;
	}
}
