package com.corp.oa.autoalert;

import java.util.Collection;
import java.util.Iterator;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.base.UserInfo;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;

/**
 * Title: 重要事务动态提醒、自动事务执行 Description: Company:
 * 
 * @author liss
 * @version 1.0
 */

public class AutoAction implements BusinessAction {
	public AutoAction() {
	}

	private String copyMin = "";

	// 判断系统所使用的区域（0--Jnet，1--物流代理公司，-1--测试使用）
	private String local = "";

	private String empSID = "";

	@SuppressWarnings("unchecked")
	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		AutoForm frm = (AutoForm) parm1;

		// 获得操作人员的信息
		UserInfo userView = frm.getUserView();

		// 内部编号
		String useId = userView.getUserId();
		empSID = useId;
		DataObject dob = null;

		String sql = "";

		String sessionId = frm.getSessionId();
		sql = "select * from T_INFO_LOGIN WHERE EMPSID = ? AND SESSIONID = ?";

		if (dm.findByPrimaryKey(sql, new String[] { useId, sessionId }) == null) {
			// 其它用户用这个用户名登陆过
			frm.setLogout(true);
		}

		String refreshTime = "", loginSingle = "";

		// 取系统参数信息
		sql = "select SID,ATTRVALUE from T_INFO_SYSTEM";
		Collection<DataObject> col = dm.find(sql, null);
		Iterator<DataObject> it = col.iterator();
		while (it.hasNext()) {
			dob = (DataObject) it.next();

			// 系统使用的区域
			if ("-1".equals(dob.getString("SID")))
				local = dob.getString("ATTRVALUE");

			if ("1".equals(dob.getString("SID")))
				refreshTime = dob.getString("ATTRVALUE");

			if ("2".equals(dob.getString("SID")))
				loginSingle = dob.getString("ATTRVALUE");

			// // 进行复制的间隔分钟
			// if ("3".equals(dob.getString("SID")))
			// copyMin = dob.getString("ATTRVALUE");
		}

		// 取得自动刷新的时间间隔
		try {
			if (Integer.parseInt(refreshTime) <= 0)
				// 因为系统需要使用自动刷新进行数据同步操作
				// 因此此处不允许不自动刷新
				refreshTime = "300";

		} catch (Exception e) {
			refreshTime = "300";
		}

		frm.setRefreshTime(refreshTime);

		// 取得是否单用户登录功能
		if (!"1".equals(loginSingle))
			loginSingle = "0";
		frm.setLoginSingle(loginSingle);

		// 取得自动复制的时间间隔
		try {// 默认为30分钟
			if (Integer.parseInt(copyMin) <= 0)
				copyMin = "30";

		} catch (Exception e) {
			copyMin = "30";
		}
		// 执行国内部分的提醒操作
		leafAlert(dm, frm);

	}

	// 国内物流公司的提醒设置
	private void leafAlert(DataManager dm, AutoForm frm)
			throws java.lang.Exception {

		if ("1".equals(local) || "-1".equals(local)) {// 这些状态下需要获取本地提醒
			String sql = "";
			sql = "select F_alert_new_reg('" + empSID;
			sql += "') as f1,F_alert_new_record('" + empSID;
			sql += "') as f2 from dual";

			DataObject dob = dm.findByPrimaryKey(sql, null);
			frm.setRegDly(dob.getString("f1"));// 多少运单已登记
			frm.setNewDlyInfo(dob.getString("f2"));// 多少运单信息录入
		}
	}

}
