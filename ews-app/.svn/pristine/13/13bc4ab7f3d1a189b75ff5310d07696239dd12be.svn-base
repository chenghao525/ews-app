package com.corp.oa.system.dictionary;

import java.util.Collection;

import com.corp.frame.action.FrameForm;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.dao.Pagination;
import com.corp.frame.core.metadata.QueryView;

public class SysDictionaryForm extends FrameForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2820705684769361864L;

	public SysDictionaryForm() {
	}

	// 处理用户信息的数据库对象
	private DataObject t_LIST_STATUS = new DataObject("T_LIST_STATUS");

	// 信息列表
	private Collection<DataObject> resultList;

	private String sid;

	// 高级查询对像
	private QueryView queryView = new QueryView();

	// 分布对象
	private Pagination pagination = new Pagination(20, 1);

	private int current;

	private String bAdd = "true";

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public DataObject getT_LIST_STATUS() {
		return t_LIST_STATUS;
	}

	public void setT_LIST_STATUS(DataObject t_LIST_STATUS) {
		this.t_LIST_STATUS = t_LIST_STATUS;
	}

	public String getbAdd() {
		return bAdd;
	}

	public void setbAdd(String bAdd) {
		this.bAdd = bAdd;
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

	public Collection<DataObject> getResultList() {
		return resultList;
	}

	public void setResultList(Collection<DataObject> resultList) {
		this.resultList = resultList;
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
