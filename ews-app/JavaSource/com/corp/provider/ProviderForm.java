package com.corp.provider;

import java.util.ArrayList;
import java.util.Collection;

import com.corp.frame.action.FrameForm;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.dao.Pagination;
import com.corp.frame.core.metadata.QueryView;

public class ProviderForm extends FrameForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7839196497375364839L;

	// 服务商基本信息
	private DataObject t_INFO_PROVIDER = new DataObject("T_INFO_PROVIDER");

	private QueryView queryView = new QueryView();

	// 服务商信息集合
	private Collection<DataObject> provLst = new ArrayList<DataObject>();

	// 分页对象
	private Pagination pagination = new Pagination(20, 1);

	// 当前显示的页码
	private int current;

	private String sid;

	private String provName;

	// 判断是查看还是修改还是增加 see/edit/add
	private String opt = "";

	// 判断选择对话框是多选还是单选，默认多选 -check ,-radio
	private String selType = "check";

	private String bPop = "false";

	// 图片的文件路径
	private String photoPath = "";
	// 图片的文件路径(老路径)
	private String photoPathOld = "";

	// 商户的服务范围（对应的维保项目）
	private String maList = "";

	// 商户的服务属性
	private String attrList = "";

	public String getSelType() {
		return selType;
	}

	public void setSelType(String selType) {
		this.selType = selType;
	}

	public String getbPop() {
		return bPop;
	}

	public void setbPop(String bPop) {
		this.bPop = bPop;
	}

	public DataObject getT_INFO_PROVIDER() {
		return t_INFO_PROVIDER;
	}

	public void setT_INFO_PROVIDER(DataObject t_INFO_PROVIDER) {
		this.t_INFO_PROVIDER = t_INFO_PROVIDER;
	}

	public QueryView getQueryView() {
		return queryView;
	}

	public void setQueryView(QueryView queryView) {
		this.queryView = queryView;
	}

	public Collection<DataObject> getProvLst() {
		return provLst;
	}

	public void setProvLst(Collection<DataObject> provLst) {
		this.provLst = provLst;
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

	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getPhotoPathOld() {
		return photoPathOld;
	}

	public void setPhotoPathOld(String photoPathOld) {
		this.photoPathOld = photoPathOld;
	}

	public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}

	public String getMaList() {
		return maList;
	}

	public void setMaList(String maList) {
		this.maList = maList;
	}

	public String getAttrList() {
		return attrList;
	}

	public void setAttrList(String attrList) {
		this.attrList = attrList;
	}

}
