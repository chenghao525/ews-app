package com.corp.basic;

import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;

public class SystemInfoConstant {

	protected final static Log logger = LogFactory.getLog(SystemInfoConstant.class);

	// 空对象锁
	private static final Object codeLocker = new Object();

	public static JSONObject sysKey = new JSONObject();

	@SuppressWarnings("unchecked")
	public static void refreshSysKey() {
		synchronized (codeLocker) {
			DataManager dm = null;
			try {
				if (sysKey.length() == 0)
					return;

				Iterator<String> it = sysKey.keys();
				String keyList = "";
				while (it.hasNext()) {
					keyList += it.next() + "','";
				}

				dm = DataManager.getInstance();

				String sql = "select t.attrname,t.attrvalue from t_info_system t where t.attrname in('"
						+ keyList + "')";

				Iterator<DataObject> itKey = dm.find(sql, null).iterator();
				DataObject dob = null;

				sysKey = new JSONObject();
				while (itKey.hasNext()) {
					dob = itKey.next();
					sysKey.put(dob.getString("ATTRNAME"), G.nvl(dob.getString("ATTRVALUE"), ""));
				}
				logger.info("服务器重置系统参数对象：" + sysKey.toString());

			} catch (Exception e) {
				logger.info("refreshSysKey error:" + e.getMessage());
			} finally {
				if (dm != null) {
					dm.close();
				}
			}
		}
	}

	// 根据key获取系统参数
	public static String getSysKeyInfo(String key) {
		if (sysKey == null || G.isNvl(key))
			return null;

		if (sysKey.has(key)) {
			synchronized (codeLocker) {
				return sysKey.getString(key);
			}
		} else {// 第一次读取该键值
			synchronized (codeLocker) {
				DataManager dm = null;
				String value = null;
				try {
					dm = DataManager.getInstance();
					String sql = "select t.attrvalue from t_info_system t where t.attrname=?";
					DataObject dob = dm.findByPrimaryKey(sql, key);
					if (dob != null) {
						value = G.nvl(dob.getString("ATTRVALUE"), "");
						sysKey.put(key, value);
						logger.info("首次加载参数" + key + ",value=" + value);
					} else {
						G.inserExceptionLog("参数" + key + "不存在.", dm);
					}
				} catch (Exception e) {
					G.inserExceptionLog("首次读取参数" + key + "错误：" + e.getMessage(), dm);
				} finally {
					if (dm != null) {
						dm.close();
					}
				}
				return value;
			}
		}
	}

	// 更新系统参数
	public static boolean setSysKeyInfo(String key, String value, DataManager dm) throws Exception {
		if (sysKey == null || G.isNvl(key))
			return false;

		synchronized (codeLocker) {
			sysKey.put(key, G.nvl(value, ""));
			G.setSystemInfoValueByKeyname(key, value, dm);
			return true;
		}
	}
}
