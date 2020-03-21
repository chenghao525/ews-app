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

	// 处理用户信息的数据库对象
	private com.corp.frame.core.dao.DataObject t_GPS_SOURCE = new com.corp.frame.core.dao.DataObject(
			"T_GPS_SOURCE");

	// 处理用户信息的数据库对象
	private com.corp.frame.core.dao.DataObject t_INFO_MOBILE = new com.corp.frame.core.dao.DataObject(
			"T_INFO_MOBILE");

	private QueryView queryView = new QueryView();

	// GPS信息的集合
	private Collection<DataObject> gpsList = new ArrayList<DataObject>();

	// GPS信息的集合 json strring
	private String gpsListJsonString;

	// 业务员的相片地址
	private Collection<DataObject> empPhoto = new ArrayList<DataObject>();

	// 终端设备的集合
	private Collection<DataObject> mpList = new ArrayList<DataObject>();

	// 分页对象
	private Pagination pagination = new Pagination();

	// 上次刷新的时间
	private String refreshTime;

	// 当前显示的页码
	private int current;

	// 当前人员所在的城市
	private String city;

	private String mobileList;

	// 是否是超级用户，可以查看所有城市
	private String superRole;

	// gps数据的SID，用于日轨迹跟踪
	// Mobile的SID，用於對設備進行修改
	private String sid;

	// 日轨迹跟踪的日期
	private String monitorDay;

	// 巴枪的编号
	private String mobileID;

	// 终端对象类别
	private String usermode;

	// 状态的编号
	private String[] status;
	// 状态的list
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
