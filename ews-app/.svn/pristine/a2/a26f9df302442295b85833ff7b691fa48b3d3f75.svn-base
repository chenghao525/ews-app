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

	// 信息列表
	private Collection<DataObject> resultList;

	// 高级查询对像
	private QueryView queryView = new QueryView();

	// 分布对象
	private Pagination pagination = new Pagination(20, 1);

	private int current;

	// 起始时间
	private String starttime;

	// 结止时间
	private String endtime;

	// 路径
	private String path;

	// 文件名
	private String name;

	// isdel=ture 选择删除，false 选择导出
	private String isdel = "false";

	// exp＝true 在删除弹出框中要先导出然后删除，false直接删除
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
