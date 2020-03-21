package com.corp.oa.system.log;

import java.util.Collection;

import com.corp.frame.action.FrameForm;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.dao.Pagination;
import com.corp.frame.core.metadata.QueryView;

public class LogForm extends FrameForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6105503674260613721L;

	public LogForm() {
	}

	// ��Ϣ�б�
	private Collection<DataObject> resultList;

	// �߼���ѯ����
	private QueryView queryView = new QueryView();

	// �ֲ�����
	private Pagination pagination = new Pagination(20, 1);

	private int current;

	// ��ʼʱ��
	private String starttime;

	// ��ֹʱ��
	private String endtime;

	// ·��
	private String path;

	// �ļ���
	private String name;

	// isdel=ture ѡ��ɾ����false ѡ�񵼳�
	private String isdel = "false";

	// exp��true ��ɾ����������Ҫ�ȵ���Ȼ��ɾ����falseֱ��ɾ��
	private String exp = "false";

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
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

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIsdel() {
		return isdel;
	}

	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}

}
