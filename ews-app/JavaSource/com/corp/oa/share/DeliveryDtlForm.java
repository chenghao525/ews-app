package com.corp.oa.share;

import java.util.ArrayList;
import java.util.Collection;

import com.corp.frame.action.FrameForm;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.dao.Pagination;
import com.corp.frame.core.metadata.QueryView;

public class DeliveryDtlForm extends FrameForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8352488702841539825L;

	// 运单信息表的数据库对象
	private DataObject t_INFO_DELIVERY = new DataObject("T_INFO_DELIVERY");

	// 运单详细信息的集合
	private Collection<DataObject> orderDtlList = new ArrayList<DataObject>();

	// 运单信息的集合
	private Collection<DataObject> orderList = new ArrayList<DataObject>();

	// 内部编号
	private int sid;

	// 分页对象
	private Pagination pagination = new Pagination();

	// 当前页码
	private int current;

	// 高级查询
	private QueryView queryView = new QueryView();

	// 标志位 等1是运单信息, 等2是日本部分运单信息
	private String flag;

	// 标记 区分是运单种类, 1 运单查看 t_info_delivery 2 运单查看的sid 来自T_DELIVERY_FLOW
	private String sign;

	// 运单处理流程表(日本部分)的 数据库对象 flag=2 时用到
	private DataObject t_JP_INFO_DELIVERY = new DataObject("T_JP_INFO_DELIVERY");

	// 运单配送信息的集合 flag=2 时用到
	private Collection<DataObject> flowList = new ArrayList<DataObject>();

	// 运单详细信息的集合(JP)
	private Collection<DataObject> detList = new ArrayList<DataObject>();

	// 多用户操作标志位
	private int CRITICAL_VALUE;

	public DataObject getT_INFO_DELIVERY() {
		return t_INFO_DELIVERY;
	}

	public void setT_INFO_DELIVERY(DataObject t_info_delivery) {
		t_INFO_DELIVERY = t_info_delivery;
	}

	public Collection<DataObject> getOrderDtlList() {
		return orderDtlList;
	}

	public void setOrderDtlList(Collection<DataObject> orderDtlList) {
		this.orderDtlList = orderDtlList;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
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

	public QueryView getQueryView() {
		return queryView;
	}

	public void setQueryView(QueryView queryView) {
		this.queryView = queryView;
	}

	public Collection<DataObject> getOrderList() {
		return orderList;
	}

	public void setOrderList(Collection<DataObject> orderList) {
		this.orderList = orderList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Collection<DataObject> getFlowList() {
		return flowList;
	}

	public void setFlowList(Collection<DataObject> flowList) {
		this.flowList = flowList;
	}

	public DataObject getT_JP_INFO_DELIVERY() {
		return t_JP_INFO_DELIVERY;
	}

	public void setT_JP_INFO_DELIVERY(DataObject t_jp_info_delivery) {
		t_JP_INFO_DELIVERY = t_jp_info_delivery;
	}

	public Collection<DataObject> getDetList() {
		return detList;
	}

	public void setDetList(Collection<DataObject> detList) {
		this.detList = detList;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public int getCRITICAL_VALUE() {
		return CRITICAL_VALUE;
	}

	public void setCRITICAL_VALUE(int critical_value) {
		CRITICAL_VALUE = critical_value;
	}

}
