package com.corp.basic.dictionary;

import java.util.ArrayList;
import java.util.Collection;

import com.corp.frame.action.FrameForm;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.dao.Pagination;
import com.corp.frame.core.metadata.QueryView;

/**
 * MyEclipse Struts Creation date: 12-26-2006
 * 
 * @author 邵俭 Content:用户字典信息FORM
 * */

public class DictionaryForm extends FrameForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2689567460592711112L;

	// 运单收件人、发件人信息表的数据库对象
	private DataObject t_MAP_TRANS_DIC = new DataObject("T_MAP_TRANS_DIC");

	// 运单收件人、发件人信息的集合
	private Collection<DataObject> dicList = new ArrayList<DataObject>();

	// 分页对象
	private Pagination pagination = new Pagination();

	// 当前页码
	private int current;

	// 高级查询
	private QueryView queryView = new QueryView();

	// 内部编号
	private int sid;

	// 多用户操作标志位
	private int CRITICAL_VALUE;

	public int getCRITICAL_VALUE() {
		return CRITICAL_VALUE;
	}

	public void setCRITICAL_VALUE(int critical_value) {
		CRITICAL_VALUE = critical_value;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public Collection<DataObject> getDicList() {
		return dicList;
	}

	public void setDicList(Collection<DataObject> dicList) {
		this.dicList = dicList;
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

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public DataObject getT_MAP_TRANS_DIC() {
		return t_MAP_TRANS_DIC;
	}

	public void setT_MAP_TRANS_DIC(DataObject t_map_trans_dic) {
		t_MAP_TRANS_DIC = t_map_trans_dic;
	}

}
