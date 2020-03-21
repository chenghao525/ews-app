package com.corp.oa.system.role;

import java.util.ArrayList;
import java.util.Collection;

import com.corp.frame.action.FrameForm;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.dao.Pagination;
import com.corp.frame.core.metadata.QueryView;

/**
 * <p>
 * 角色管理:
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
 * @author liss
 * @version 1.0
 */

public class RoleForm extends FrameForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7446233202302628133L;

	public RoleForm() {
	}

	//

	// int i=0;

	// 分类信息的集合
	private Collection<DataObject> userList = new ArrayList<DataObject>();

	// 分页对象
	private Pagination pagination = new Pagination();

	// 查看/更新/删除的内部编号
	private String sid;

	private QueryView queryView = new QueryView();

	private int current;

	private DataObject t_RIGHT_ROLEDIC = new DataObject("T_RIGHT_ROLEDIC");

	// 查看/编辑 标志位
	private String display;

	// 角色的编号
	private String roleid;

	// 角色名称
	private String rolename;

	// 保存角色的权限列表
	private Collection<DataObject> rightlist = new ArrayList<DataObject>();

	private String[] rolerightlist;

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public Collection<DataObject> getUserList() {
		return userList;
	}

	public void setUserList(Collection<DataObject> userList) {
		this.userList = userList;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public com.corp.frame.core.dao.DataObject getT_RIGHT_ROLEDIC() {
		return t_RIGHT_ROLEDIC;
	}

	public void setT_RIGHT_ROLEDIC(
			com.corp.frame.core.dao.DataObject t_RIGHT_ROLEDIC) {
		this.t_RIGHT_ROLEDIC = t_RIGHT_ROLEDIC;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public java.util.Collection<DataObject> getRightlist() {
		return rightlist;
	}

	public void setRightlist(java.util.Collection<DataObject> rightlist) {
		this.rightlist = rightlist;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String[] getRolerightlist() {
		return rolerightlist;
	}

	public void setRolerightlist(String[] rolerightlist) {
		this.rolerightlist = rolerightlist;
	}

	public QueryView getQueryView() {
		return queryView;
	}

	public void setQueryView(QueryView queryView) {
		this.queryView = queryView;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

}
