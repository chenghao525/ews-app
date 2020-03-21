package com.corp.servlet.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.core.dao.DataManager;

public interface CommonSearchInterface {

	public boolean doConfig(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception;
}
