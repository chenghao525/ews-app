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

	// �˵���Ϣ������ݿ����
	private DataObject t_INFO_DELIVERY = new DataObject("T_INFO_DELIVERY");

	// �˵���ϸ��Ϣ�ļ���
	private Collection<DataObject> orderDtlList = new ArrayList<DataObject>();

	// �˵���Ϣ�ļ���
	private Collection<DataObject> orderList = new ArrayList<DataObject>();

	// �ڲ����
	private int sid;

	// ��ҳ����
	private Pagination pagination = new Pagination();

	// ��ǰҳ��
	private int current;

	// �߼���ѯ
	private QueryView queryView = new QueryView();

	// ��־λ ��1���˵���Ϣ, ��2���ձ������˵���Ϣ
	private String flag;

	// ��� �������˵�����, 1 �˵��鿴 t_info_delivery 2 �˵��鿴��sid ����T_DELIVERY_FLOW
	private String sign;

	// �˵��������̱�(�ձ�����)�� ���ݿ���� flag=2 ʱ�õ�
	private DataObject t_JP_INFO_DELIVERY = new DataObject("T_JP_INFO_DELIVERY");

	// �˵�������Ϣ�ļ��� flag=2 ʱ�õ�
	private Collection<DataObject> flowList = new ArrayList<DataObject>();

	// �˵���ϸ��Ϣ�ļ���(JP)
	private Collection<DataObject> detList = new ArrayList<DataObject>();

	// ���û�������־λ
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
