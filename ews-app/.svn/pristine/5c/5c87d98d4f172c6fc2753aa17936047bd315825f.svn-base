package com.corp.provider;

import java.util.Iterator;

import com.corp.basic.Constant;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;

public class ProviderTools {

	public String addOrEditCustomInfo(DataObject dob, DataManager dm) throws Exception {

		if (!checkDataObject(dob, dm))
			return null;

		String sid = dob.getString("SID");
		if (G.isNvl(sid)) { // add
			// get a sid from sequence
			sid = G.getSequence("SEQ_INFO_PROVIDER", dm);
			dob.setString("sid", sid);

			// 设置一个随机数
			dob.setInt("SOLD_TOTAL_ADD", G.RandomScope(50, 30));

			dm.insert(dob);
		} else { // edit
			dm.update(dob);
		}
		return sid;
	}

	// 1、检查dataobject中的model，确保其为T_INFO_PROVIDER
	// 2、检查cust_id是否已存在
	private boolean checkDataObject(DataObject dob, DataManager dm) throws Exception {
		if (dob.getModelName() == null)
			dob.setModelName("T_INFO_PROVIDER");
		if (!"T_INFO_PROVIDER".equals(dob.getModelName())) {
			return false;
			// throw new GeneralException("无效的ModelName");
		}

		String sid = dob.getString("SID");
		DataObject tmpDob = null;
		if (G.isNvl(sid)) {
			tmpDob = dm.findByPrimaryKey(
					"select sid from T_INFO_PROVIDER where upper(PROV_ID)=upper('"
							+ dob.getString("PROV_ID") + "') and state=1", null);
		} else {
			tmpDob = dm.findByPrimaryKey(
					"select sid from T_INFO_PROVIDER where upper(PROV_ID)=upper('"
							+ dob.getString("PROV_ID") + "') and sid<>'" + sid + "' and state=1",
					null);
		}

		if (tmpDob != null) {
			return false;
			// throw new GeneralException("服务商编号[" + dob.getString("PROV_ID") +
			// "]已存在，请重新输入");
		}
		return true;
	}

	public void removeProvider(String sid, DataManager dm) throws Exception {
		DataObject dob = new DataObject("T_INFO_PROVIDER");
		dob.setString("SID", sid);
		dob.setString("STATE", "0");
		dm.update(dob);

		String sql = "";

		// 删除商户与汽车品牌的映射关系
		sql = "delete from t_info_prov_brand_map t where t.prov_sid=?";
		dm.delete(sql, sid);

		// 删除商户与配件的映射关系
		sql = "delete from t_bom_map_list2prov t where t.prov_sid=?";
		dm.delete(sql, sid);
	}

	/**
	 * 删除服务商对应的汽车品牌映射
	 * 
	 * @param provSid
	 * @param dm
	 * @throws Exception
	 */
	public void removeProvBrandMap(String provSid, DataManager dm) throws Exception {
		if (G.isNvl(provSid))
			return;
		ProviderBO bo = new ProviderBO();
		String sql = bo.getRemoveProvBrandSql();
		dm.delete(sql, provSid);
	}

	/**
	 * 插入服务商与所有汽车品牌的映射关系
	 * 
	 * @param provSid
	 * @param brandList
	 * @param nameList
	 * @param empSid
	 * @param dm
	 * @throws Exception
	 */
	public void insertProvAllCarsMap(String provSid, String empSid, DataManager dm)
			throws Exception {

		if (G.isNvl(provSid))
			return;

		DataObject dob = null;
		dob = new DataObject("T_INFO_PROV_BRAND_MAP");
		dob.setString("PROV_SID", provSid);
		dob.setString("CAR_UUID", Constant.ALL_CAR_TYPE_MATCH);
		dob.setString("CRT_EMP", empSid);
		dm.insert(dob);
	}

	/**
	 * 插入服务商与汽车品牌的映射关系
	 * 
	 * @param provSid
	 * @param brandList
	 * @param nameList
	 * @param empSid
	 * @param dm
	 * @throws Exception
	 */
	public void insertProvBrandMap(String provSid, String brandList, String empSid, DataManager dm)
			throws Exception {

		if (G.isNvl(provSid) || G.isNvl(brandList))
			return;

		// 此处进行了修改，前台商户处只需要选择第二层级的车辆品牌即可
		String curTime = G.getCurrentTime();

		String list = brandList.replaceAll(",", "','");
		String sql = "insert into T_INFO_PROV_BRAND_MAP(PROV_SID,CAR_UUID,CRT_EMP,CRT_DATE,"
				+ "CAR_NAME,CAR_LEVEL) select distinct '" + provSid + "',t.car_id,'" + empSid
				+ "','" + curTime + "',t.node_text,t.car_level from t_info_car_list_tree_bu t"
				+ " where t.car_id in ('" + list + "') or (t.car_level=3 and t.parent_id in('"
				+ list + "'))";
		dm.update(sql, null);
	}

	/**
	 * 插入服务商与汽车品牌的映射关系(排除车型)
	 * 
	 * @param provSid
	 * @param brandList
	 * @param nameList
	 * @param empSid
	 * @param dm
	 * @throws Exception
	 */
	public void insertProvBrandMapEx(String provSid, String brandList, String empSid, DataManager dm)
			throws Exception {

		if (G.isNvl(provSid))
			return;

		// 先删除
		String sql = "delete from t_info_prov_brand_map_ex t where t.prov_sid=?";
		dm.delete(sql, provSid);

		if (G.isNvl(brandList)) {
			return;
		}

		String list = brandList.replaceAll(",", "','");
		sql = "insert into T_INFO_PROV_BRAND_MAP_EX(PROV_SID,CAR_UUID,CRT_EMP,"
				+ "CAR_NAME,CAR_LEVEL) select distinct '" + provSid + "',t.car_id,'" + empSid
				+ "',t.node_text,t.car_level from t_info_car_list_tree_bu t"
				+ " where t.car_id in ('" + list + "') or (t.car_level=3 and t.parent_id in('"
				+ list + "'))";
		dm.update(sql, null);
	}

	/**
	 * 返回服务商与品牌之间的映射关系
	 * 
	 * @param bomId
	 * @param dm
	 * @return
	 * @throws Exception
	 */
	public String[] getProvBrandMap(String provSid, DataManager dm) throws Exception {
		if (G.isNvl(provSid))
			return null;

		ProviderBO bo = new ProviderBO();
		String sql = bo.getProvBrandListSql(provSid);

		sql = "select wm_concat(car_uuid) as car_uuid,wm_concat(car_name) as car_name from (" + sql
				+ ")";
		DataObject dob = dm.findByPrimaryKey(sql, null);
		String carId = G.nvl(dob.getString("CAR_UUID"), "");
		String carName = G.nvl(dob.getString("CAR_NAME"), "");
		return new String[] { carId, carName };
	}

	/**
	 * 返回服务商与品牌之间的映射关系
	 * 
	 * @param bomId
	 * @param dm
	 * @return
	 * @throws Exception
	 */
	public String[] getProvBrandMapEx(String provSid, DataManager dm) throws Exception {
		if (G.isNvl(provSid))
			return null;

		String sql = "select wm_concat(car_uuid) as car_uuid,wm_concat(car_name) as car_name"
				+ " from T_INFO_PROV_BRAND_MAP_EX t where t.car_level<=2 and t.prov_sid=?";

		DataObject dob = dm.findByPrimaryKey(sql, provSid);
		String carId = G.nvl(dob.getString("CAR_UUID"), "");
		String carName = G.nvl(dob.getString("CAR_NAME"), "");
		return new String[] { carId, carName };
	}

	/**
	 * 插入服务商与汽车品牌的映射关系(排除车型)
	 * 
	 * @param provSid
	 * @param brandList
	 * @param nameList
	 * @param empSid
	 * @param dm
	 * @throws Exception
	 */
	public void insertProvBrandAuthMap(String provSid, String brandList, String empSid,
			DataManager dm) throws Exception {

		if (G.isNvl(provSid))
			return;

		// 先删除
		String sql = "delete from T_INFO_PROVIDER_AUTH t where t.sid=?";
		dm.delete(sql, provSid);

		if (G.isNvl(brandList)) {
			return;
		}

		String list = brandList.replaceAll(",", "','");
		sql = "insert into T_INFO_PROVIDER_AUTH(SID,CAR_UUID,CRT_EMP,"
				+ "CAR_NAME) select distinct '" + provSid + "',t.car_id,'" + empSid
				+ "',t.node_text from t_info_car_list_tree_bu t where t.car_id in ('" + list + "')";
		dm.update(sql, null);
	}

	/**
	 * 返回服务商与品牌之间的映射关系
	 * 
	 * @param bomId
	 * @param dm
	 * @return
	 * @throws Exception
	 */
	public String[] getProvBrandAuthMap(String provSid, DataManager dm) throws Exception {
		if (G.isNvl(provSid))
			return null;

		String sql = "select wm_concat(car_uuid) as car_uuid,wm_concat(car_name) as car_name"
				+ " from T_INFO_PROVIDER_AUTH t where t.sid=?";

		DataObject dob = dm.findByPrimaryKey(sql, provSid);
		String carId = G.nvl(dob.getString("CAR_UUID"), "");
		String carName = G.nvl(dob.getString("CAR_NAME"), "");
		return new String[] { carId, carName };
	}

	/**
	 * 返回当前的商户匹配的所有车型字段信息
	 * 
	 * @param sid
	 * @param dm
	 * @return
	 */
	public String getProvMatchCarsString(String sid, DataManager dm) {
		String re = "";
		try {
			ProviderBO pbo = new ProviderBO();
			String sql = pbo.getProvInfoBySid();
			DataObject dob = dm.findByPrimaryKey(sql, sid);
			if (dob != null) {
				re = dob.getString("MATCH_ALL");
			}
		} catch (Exception e) {
		}
		return re;
	}

	/**
	 * 修改商户与车型等信息映射的匹配关系
	 * 
	 * @param dm
	 */
	public void repairProvCarsMaping(DataManager dm) {
		try {
			String sql = "";
			sql = "update t_info_prov_brand_map t set t.car_level="
					+ "(select car_level from t_info_car_list_tree_bu b where t.car_uuid=b.car_id)"
					+ " where t.car_level is null";
			dm.update(sql, null);

			sql = "update t_bom_map_list2prov t set t.bom_brand=(select bom_brand from t_bom_list b where b.sid=t.bom_uuid)"
					+ " where t.bom_brand is null";
			dm.update(sql, null);

			sql = "update tp_prov_pdu_mapcar t set t.car_level=(select b.car_level"
					+ " from t_info_car_list_tree_bu b where b.car_id=t.car_uuid)"
					+ " where t.car_level is null";
			dm.update(sql, null);
		} catch (Exception e) {

		}
	}

	// 返回商户能够提供的维保项目
	public String getProvMaList(String provSid, DataManager dm) throws Exception {
		String sql = "select wm_concat(MA_SID) as MA_SID from t_info_provider_ma t where t.sid=?";
		sql = G.formatSql(sql, provSid);

		DataObject dob = dm.findByPrimaryKey(sql, null);
		return G.nvl(dob.getString("MA_SID"), "");
	}

	public void saveProvMaList(String provSid, String maList, DataManager dm) throws Exception {

		// 先删除
		String sql = "delete from t_info_provider_ma t where t.sid=?";
		dm.delete(sql, provSid + "");

		// 保存
		if (!G.isNvl(maList)) {
			DataObject dob = null;
			String[] ma = maList.split(",");
			for (int i = 0; i < ma.length; i++) {
				dob = new DataObject("T_INFO_PROVIDER_MA");
				dob.setString("SID", provSid);
				dob.setString("MA_SID", ma[i]);
				dob.setString("UPT_TIME", G.getCurrentTime());
				dm.insert(dob);
			}
		}

	}

	// 返回商户的服务属性
	public String getProvAttrList(String provSid, DataManager dm) throws Exception {
		String sql = "select wm_concat(ATTR_SID) as ATTR_SID from t_info_provider_attr t where t.sid=?";
		sql = G.formatSql(sql, provSid);
		DataObject dob = dm.findByPrimaryKey(sql, null);
		return G.nvl(dob.getString("ATTR_SID"), "");
	}

	// 保存服务属性
	public void saveProvAttrList(String provSid, String attrList, DataManager dm) throws Exception {

		// 先删除
		String sql = "delete from t_info_provider_attr t where t.sid=?";
		dm.delete(sql, provSid + "");

		boolean isPaintImp = false;
		// 保存
		if (!G.isNvl(attrList)) {
			DataObject dob = null;
			DataObject dobP = null;
			String[] attr = attrList.split(",");
			for (int i = 0; i < attr.length; i++) {
				dob = new DataObject("T_INFO_PROVIDER_ATTR");
				dob.setString("SID", provSid);
				dob.setString("ATTR_SID", attr[i]);

				// 查找父类
				sql = "select t.parent,t.keyvalue from t_list_status t where t.sid='" + attr[i]
						+ "'";
				dobP = dm.findByPrimaryKey(sql, null);

				dob.setString("ATRR_PARENT", dobP.getString("PARENT"));
				dob.setString("UPT_TIME", G.getCurrentTime());
				dm.insert(dob);

				if (!isPaintImp && "import".equals(dobP.getString("KEYVALUE"))) {
					isPaintImp = true;
				}
			}
		}

		// 更新商户表，设置是进口油漆
		sql = "update t_info_provider p set p.paint_imp=? where p.sid=?";
		dm.update(sql, new String[] { (isPaintImp ? "1yes" : "9no"), provSid });
	}

	// 返回商户的标签属性，一个带数字，一个不带数字
	@SuppressWarnings("unchecked")
	public JSONObject getProvLabel(String provSid, DataManager dm) throws Exception {
		String sql = "select LBL_NAME,LBL_TIMES from t_info_provider_label t"
				+ " where t.state='1' and t.sid=? order by t.LBL_TIMES desc,t.lbl_order";
		Iterator<DataObject> it = dm.find(sql, provSid).iterator();
		DataObject dob = null;
		String list1 = "", list2 = "";
		while (it.hasNext()) {
			dob = (DataObject) it.next();
			list1 += dob.getString("LBL_NAME") + "(" + dob.getInt("LBL_TIMES") + ")"
					+ Constant.STRING_BREAK_NEW;
			list2 += dob.getString("LBL_NAME") + Constant.STRING_BREAK_NEW;
		}
		if (!"".equals(list1)) {
			list1 = list1.substring(0, list1.length() - Constant.STRING_BREAK_NEW.length());
			list2 = list2.substring(0, list2.length() - Constant.STRING_BREAK_NEW.length());
		}
		JSONObject json = new JSONObject();
		json.put("LBL_TIMES", list1);
		json.put("LBL", list2);
		return json;
	}
	
	//更新商户信息表的标签字段
	public void updateProvLabelList(String sid,DataManager dm)throws Exception{
		JSONObject json = getProvLabel(sid, dm);

		String sql = "update t_info_provider t set t.lbl_list=? where t.sid=?";
		dm.update(sql, new String[] { json.getString("LBL_TIMES"), sid });
	}
}
