package com.corp.oa.system.user;

import java.util.ArrayList;
import java.util.Collection;

import com.corp.frame.action.FrameForm;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.dao.Pagination;
import com.corp.frame.core.metadata.QueryView;

public class UserForm extends FrameForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4811284913796404078L;

	public UserForm() {
	}

	// 处理用户信息的数据库对象
	private DataObject t_INFO_EMPLOYEE = new DataObject("T_INFO_EMPLOYEE");

	// 用户信息的集合
	private Collection<DataObject> userList = new ArrayList<DataObject>();

	// 分页对象
	private Pagination pagination = new Pagination();

	private QueryView queryView = new QueryView();

	// 当前显示的页码
	private int current;

	private String sid;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public void setUserList(Collection<DataObject> userList) {
		this.userList = userList;
	}

	public Collection<DataObject> getUserList() {
		return userList;
	}

	public QueryView getQueryView() {
		return queryView;
	}

	public void setQueryView(QueryView queryView) {
		this.queryView = queryView;
	}

	public com.corp.frame.core.dao.DataObject getT_INFO_EMPLOYEE() {
		return t_INFO_EMPLOYEE;
	}

	public void setT_INFO_EMPLOYEE(
			com.corp.frame.core.dao.DataObject T_INFO_EMPLOYEE) {
		this.t_INFO_EMPLOYEE = T_INFO_EMPLOYEE;
	}

}
