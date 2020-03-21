package com.corp.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.corp.frame.bean.JSONArray;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.Excel;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

public class ExportProvList implements AjaxInterface {

	protected final Log logger = LogFactory.getLog(getClass());

	@SuppressWarnings("unchecked")
	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		String sql = "";
		String errSql = "error";
		String fldList = "";

		if (!G.isOwnRight("10202006", request)) {
			// response = G.setRespNoRight(response);
			logger.error("ExportProvList0:您没有权限");
			sql = errSql;
		} else {
			String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);
			fldList = request.getParameter("FLD_LIST");// 需要导出的字段列表
			if (key == null) {
				sql = errSql;
				logger.error("ExportProvList1:导出之前请先对数据进行过滤");
			}
			if (G.isNvl(fldList)) {
				sql = errSql;
				logger.error("ExportProvList1:导出之前请选择过滤字段");
			} else {
				ProviderBO bo = new ProviderBO();
				sql = (bo.getSearchSql(bo.getProvList(), G.getSearchFromRequest(request)));
				sql = "select aa.*,f_getmapcontent(aa.province) as PROVINCE_NAME,"
						+ "f_getmapcontent(aa.area) as area_name from(" + sql
						+ ") aa order by crt_time";
			}
		}
		Excel ex = new Excel();
		String[] fc = new String[] { "内部编号", "商户编号", "商户全称", "商户简称", "省份", "城市", "区县", "地址",
				"商户分类", "收货人", "SA姓名", "SA电话", "创建时间" };
		String[] fe = new String[] { "SID", "PROV_ID", "PROV_NAME", "PROV_NAME_SUM",
				"PROVINCE_NAME", "CITY_NAME", "AREA_NAME", "ADDR", "PROV_TYPE_NAME", "DLV_INFO",
				"SRV_MAN_NAME", "SRV_MAN_TEL", "CRT_TIME" };

		String names = "", fields = "";
		fldList = G.commaBE(fldList);
		for (int i = 0; i < fe.length; i++) {
			if (fldList.indexOf(G.commaBE(fe[i])) >= 0) {// 报表导出字段
				names += fc[i] + ",";
				fields += fe[i] + ",";
			}
		}
		if (G.isNvl(names) || G.isNvl(fields)) {
			sql = errSql;
			logger.error("ExportProvList1:导出之前请先对数据进行过滤");
		} else {
			names = names.substring(0, names.length() - 1);
			fields = fields.substring(0, fields.length() - 1);
		}

		ex.setTitle(names);
		ex.setField(fields);

		ex.setMaskField(null);
		ex.setMaskLength(0);

		if (errSql.equals(sql)) {
			String ef = fields.replaceAll(",", ",'没有权限' as ");
			sql = "select '没有权限' as " + ef + " from dual";
		}

		Iterator<DataObject> it = dm.find(sql, null).iterator();
		DataObject dob = null;
		Collection<DataObject> col = new ArrayList<DataObject>();
		JSONArray ja = null;
		while (it.hasNext()) {
			dob = it.next();
			if (!errSql.equals(sql)) {
				if (ja == null) {
					ja = G.getJaFromDataText("provtype.txt", request);
				}
				dob.setString("PROV_TYPE_NAME", G.exchangeDataText(dob.getString("PROV_TYPE"), ja));
			}
			col.add(dob);
		}

		dm.close();
		dm = null;

		if (!errSql.equals(sql)) {
			// 按照显示的顺序
			it = col.iterator();
			col = new ArrayList<DataObject>();
			while (it.hasNext()) {
				dob = it.next();
				col.add(dob);
			}
		}

		ex.makeExcel(col);
		ex.pushFileToDownload(response);
	}
}
