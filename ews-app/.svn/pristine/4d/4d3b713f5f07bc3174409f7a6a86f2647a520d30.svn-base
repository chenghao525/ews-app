package com.corp.servlet.common;

import com.corp.oa.util.G;
import com.corp.oa.util.Tuple;

import java.util.ArrayList;

public abstract class CommonSearchBase implements CommonSearchInterface {

	private String sql;
	private String[] inc = null;
	private String[] mask = null;
	private String defaultSortField = "";
	private String[] parameter = null;
	private String listStatusInfoType = "";
	private String listStatusSidName = "";


	public String getSql() {
		return sql;
	}
	public void setSql(Tuple<String, ArrayList> tuple) {
		if (tuple != null) {
			this.sql = tuple.first;
			setParameter(tuple.getParameter());
		}
	}
	public void setSql(String sql) {
		this.sql = sql;
	}

	public String[] getInc() {
		return inc;
	}

	public void setInc(String[] inc) {
		this.inc = inc;
	}

	public String[] getMask() {
		return mask;
	}

	public void setMask(String[] mask) {
		this.mask = mask;
	}

	public String getDefaultSortField() {
		return defaultSortField;
	}

	public void setDefaultSortField(String defaultSortField) {
		this.defaultSortField = defaultSortField;
	}

	public void setListStatusInfoType(String listStatusInfoType) {
		this.listStatusInfoType = listStatusInfoType;
	}

	public String getListStatusInfoType() {
		return listStatusInfoType;
	}

	public void setListStatusSidName(String listStatusSid) {
		this.listStatusSidName = listStatusSid;
	}

	public String setListStatusSidName() {
		return listStatusSidName;
	}

	public void setParameter(String[] parameter) {
		if (parameter != null && parameter.length > 0)
			this.parameter = parameter;
	}
	public void setParameter(String parameter) {
		if (!G.isNvl(parameter)) {
			this.parameter = parameter.split(",");
		}
	}
	public String[] getParameter() {
		return parameter;
	}
}
