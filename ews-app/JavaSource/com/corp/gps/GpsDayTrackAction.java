package com.corp.gps;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;

public class GpsDayTrackAction implements BusinessAction {
	public GpsDayTrackAction() {

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doExecute(BusinessForm parm1, DataManager dm) throws Exception {
		// TODO Auto-generated method stub
		GpsForm frm = (GpsForm) parm1;
		// 设置页码
		frm.getPagination().setCurrent(frm.getCurrent());
		String sql = "";
		String day = "";
		String mobile = "";

		DataObject dob = null;

		if (frm.getSid() != null && !"".equals(frm.getSid())) {// 根据单条GPS记录来跟踪一天的轨迹
			sql = "select mobile_id,gps_time from t_gps_source where sid=?";
			dob = dm.findByPrimaryKey(sql, new String[] { frm.getSid() });
			if (dob != null) {
				day = dob.getString("GPS_TIME").substring(0, 10);
				mobile = dob.getString("MOBILE_ID");
			}
		} else {// 根据巴枪的编号和选中的日期来跟踪一天的轨迹
			day = frm.getMonitorDay();
			mobile = frm.getMobileID();
		}
		sql = "select LONGITUDE,LATITUDE from t_gps_source where GPS_TIME>='"
				+ day + " 00:00:00' and GPS_TIME<='" + day
				+ " 24:00:00' and MOBILE_ID=?"
				+ " and longitude is not null and latitude is not null order by GPS_TIME";

		frm.setGpsList(dm.find(sql, mobile));
	}
}
