package com.corp.oa.system.log;

import java.util.Iterator;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;

public class LogListAction implements BusinessAction {

	public LogListAction() {
	}

	@SuppressWarnings("unchecked")
	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		LogForm form = (LogForm) parm1;

		// 设置页码
		form.getPagination().setCurrent(form.getCurrent());

		String sql = "select a.sid from T_INFO_LOG a ";

		form.getQueryView().setSelect(sql);

		form.getQueryView().setExtension("ORDER BY sid DESC");
		sql = form.getQueryView().getSql();
		form.setResultList(dm.find(sql, null, false, null, form.getPagination()));

		Iterator<DataObject> it = form.getResultList().iterator();
		String list = "";
		while (it.hasNext()) {
			DataObject dob = (DataObject) it.next();
			list += dob.getString("SID") + ",";
		}
		if (!"".equals(list))
			list = list.substring(0, list.length() - 1);
		else
			list = "''";

		sql = "select a.sid,case a.logtype when 'A' then '系统日志'"
				+ " when 'B' then '业务日志' else '未知:'||a.logtype end AS LOGTYPE_NAME,"
				+ " '' as MENUID_NAME, a.optempid,a.optattr,a.opttype,"
				+ " a.opttime from T_INFO_LOG a where sid in (" + list
				+ ") order by sid desc";
		form.setResultList(dm.find(sql, null));

		// newJYJLma();
	}

	/**
	 * 当初为了将没有打包的机油机滤从小保养项目复制一份而增加的
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	private void newJYJLma() throws Exception {
		String sql = "select * from tp_prov_pdu_m m where m.prov_sid in"
				+ "('2518', '2512', '2513', '2520', '2521', '2522', '2523', '2524',"
				+ "'2525', '2436', '2515', '2437', '2438', '2434', '2517')"
				+ " and m.ma_parent = 2 and m.state='1'";

		DataManager dm = null;

		try {
			// 获得连接
			dm = DataManager.getInstance();
			dm.setLocalConnAutoCommit(false);

			Iterator<DataObject> it = dm.find(sql, null).iterator();
			DataObject dob = null;
			String newUUID = "", pduSid = "";
			while (it.hasNext()) {
				dob = (DataObject) it.next();
				pduSid = dob.getString("SID");
				newUUID = G.uuid();

				sql = " insert into tp_prov_pdu_mapcar(pdu_uuid,car_uuid,crt_emp,crt_date,car_name,car_level,prov_sid)"
						+ " select '"
						+ newUUID
						+ "',t.car_uuid,t.crt_emp,t.crt_date,t.car_name,t.car_level,t.prov_sid"
						+ " from tp_prov_pdu_mapcar t where t.pdu_uuid='"
						+ pduSid + "'";
				dm.update(sql, null);

				sql = " insert into tp_prov_pdu_bom(pdu_uuid,type_uuid,bom_uuid,bom_name,bom_brand_name,"
						+ " bom_model,bom_series,bom_spec,standar_price,use_count,unit_name,"
						+ "visit_price,book_price,prepat_price,EYC_PRICE,bom_brand,bom_order,channel_src,price_id,memo)"
						+ " select '"
						+ newUUID
						+ "',type_uuid,bom_uuid,bom_name,bom_brand_name,bom_model,"
						+ "bom_series,bom_spec,standar_price,use_count,unit_name,visit_price,"
						+ "book_price,prepat_price,EYC_PRICE,bom_brand,bom_order,channel_src,price_id,memo"
						+ " from tp_prov_pdu_bom t where t.pdu_uuid='"
						+ pduSid
						+ "'";
				dm.update(sql, null);

				sql = " insert into tp_prov_pdu_m(sid,prov_sid,pdu_name,cars_match,work_time,"
						+ " week_limit,week_days,week_time_start,week_time_end,date_limit,date_start,"
						+ " date_end,state,crtuser,crtdate,pdu_desc,vstprc_total,bookprc_total,"
						+ " prepay_total,EYC_TOTAL,ma_parent,type_id,channel_src,user_id,uptuser,uptdate,"
						+ " sold_total,pub_state,memo,rich_name) select '"
						+ newUUID
						+ "',prov_sid,pdu_name,cars_match,work_time,week_limit,week_days,"
						+ " week_time_start,week_time_end,date_limit,date_start,date_end,state,"
						+ "crtuser,crtdate,pdu_desc,vstprc_total,bookprc_total,prepay_total,EYC_TOTAL,'2031',"
						+ " type_id,channel_src,user_id,uptuser,uptdate,sold_total,pub_state,"
						+ "memo,rich_name from tp_prov_pdu_m t where t.sid='"
						+ pduSid + "'";
				dm.update(sql, null);
				dm.setLocalConnCommit();
			}
		} catch (Exception e) {
			if (dm != null)
				dm.close();
		}

	}
}
