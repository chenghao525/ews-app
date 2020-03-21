package com.corp.sms;

import java.util.ArrayList;
import java.util.Collection;

import com.corp.frame.action.FrameForm;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.dao.Pagination;
import com.corp.frame.core.metadata.QueryView;

public class SmsForm extends FrameForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1939882833309007467L;

	private QueryView queryView = new QueryView();

	// 短信息集合
	private Collection<DataObject> smsList = new ArrayList<DataObject>();

	// 分页对象
	private Pagination pagination = new Pagination(20, 1);

	// 当前显示的页码
	private int current;

	private String sid;

	private String phone = "";

	private String content = "";

	private String next = "";

	private String result = "";

	public QueryView getQueryView() {
		return queryView;
	}

	public void setQueryView(QueryView queryView) {
		this.queryView = queryView;
	}

	public Collection<DataObject> getSmsList() {
		return smsList;
	}

	public void setSmsList(Collection<DataObject> smsList) {
		this.smsList = smsList;
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

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
