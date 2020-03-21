package com.corp.gps;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;

public class GpsSearchAction implements BusinessAction {
	public GpsSearchAction() {

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doExecute(BusinessForm parm1, DataManager dm) throws Exception {
		GpsForm frm = (GpsForm) parm1;
		// 设置页码
		frm.getPagination().setCurrent(frm.getCurrent());

		String sql = "";

		// String empSid = dm.getUser().getUserId();

		// sql =
		// "select t.*,a.empname as EMP_NAME,F_GET_DEPT_NAME(a.dept_parnet) as DEPT_NAME,"
		// +
		// " (select b.dept_city from t_info_dept b where b.sid=a.dept_parnet) as DEPT_CITY "
		// + " from t_gps_source t LEFT join t_info_express_employee a"
		// + " on a.empid=t.emp_id where a.state='1'";

		sql = "select * from t_gps_source t ";

		// // 不是超级业务管理员只显示当地城市的数据
		// String city = "";
		// if (!dm.getUser().isSuperBusinessRole()) {
		// city = G.getEmployeeCity(dm, empSid);
		// sql = sql + " and DEPT_CITY='" + city + "'";
		// }

		frm.getQueryView().setSelect(sql);
		frm.getQueryView().setExtension("order by gps_time desc");
		sql = frm.getQueryView().getSql();
		frm.setGpsList(dm.find(sql, null, frm.getPagination()));

		/**
		 * // 查找出所有的手持终端列表，显示在页面上 sql =
		 * "select t.sid,t.content,a.mobile_list from t_list_status t " +
		 * " left join t_info_dept a on a.dept_city=t.sid where t.state='1' " +
		 * " and a.state='1'and a.mobile_list is not null and infotype='16' ";
		 * 
		 * // // 不是超级业务管理员只显示当地城市的数据 // if (!dm.getUser().isSuperBusinessRole())
		 * { // sql = sql + " and DEPT_CITY='" + city + "'"; // } sql +=
		 * " order by optorder"; Iterator it = dm.find(sql, null).iterator();
		 * String sCity = ""; String list = "";
		 * 
		 * DataObject dob = null; frm.setMobileList("");
		 * 
		 * while (it.hasNext()) { dob = (DataObject) it.next(); if
		 * (!sCity.equals(dob.getString("CONTENT"))) {// 遇到新的城市 if
		 * (!"".equals(list)) {// 保存上一个城市的数据 String[] mList = list.split(",");
		 * frm.setMobileList(frm.getMobileList() + sCity + "[ " +
		 * String.valueOf(mList.length) + " ]：" + list + "<br>
		 * "); } sCity = dob.getString("CONTENT"); list =
		 * dob.getString("MOBILE_LIST") + ","; } else {// 将数据合并 list +=
		 * dob.getString("MOBILE_LIST") + ","; } } if (!"".equals(list)) {//
		 * 处理最后一个城市的数据 String[] mList = list.split(",");
		 * frm.setMobileList(frm.getMobileList() + sCity + "[ " +
		 * String.valueOf(mList.length) + " ]：" + list + "<br>
		 * "); }
		 */
	}

}
