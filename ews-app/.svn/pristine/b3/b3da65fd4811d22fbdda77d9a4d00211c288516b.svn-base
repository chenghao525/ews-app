package com.corp.oa.system.user;

import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.exception.GeneralException;
import com.corp.oa.util.G;

public class UserBO {

	public String getEmpInfoByEmpId() {
		return "select t.* from t_info_employee t where t.state='1' and t.emp_src='ews' and upper(t.empid)=upper(?)";
	}
	
	public String getEmpInfoBySid() {
		return "select t.* from t_info_employee t where t.state='1' and t.emp_src='ews' and t.sid=?";
	}

	public String getEmpAllList() {
		return "select t.* from t_info_employee t where t.sid<>0 and emp_src='ews' order by t.empname";
	}
	
	//新增或者修改人员信息
	public void saveEmpInfo(JSONObject json, String userId, DataManager dm) throws Exception {
		DataObject dob = new DataObject();
		String type = json.getString("TYPE");

		String sid = "";
		
		if ("update".equals(type)){

			updateEmployeeInfo(userId, json, dm);
			return;
		}
		if ("add".equals(type)){
			// 向T_INFO_EMPLOYEE添加数据
			dob.setModelName("T_INFO_EMPLOYEE");

			sid = G.getSequence("SEQ_T_IWL", dm);
			dob.setString("SID", sid);
			dob.setString("CRTUSER", userId);
			dob.setString("CRTDATE", G.getCurrDt());
//			dob.setString("STATE", "1");

			dob.setString("EMPID", json.getString("EMPID"));
			dob.setString("EMPNAME", json.getString("EMPNAME"));
			dob.setString("SEX", json.getString("SEX"));
			dob.setString("EMP_ACTIVE", json.getString("EMP_ACTIVE"));
			dob.setString("COPHONE", json.getString("COPHONE"));
			dob.setString("MP", json.getString("MP"));
			dob.setString("EMAIL", json.getString("EMAIL"));
			dob.setString("ID_CARD", json.getString("ID_CARD"));
			dob.setString("DUTYSID", json.getString("DUTYSID"));
			dob.setString("MEMO", json.getString("MEMO"));
			dob.setString("EMP_TYPE", json.getString("EMP_TYPE"));
			dob.setString("PAINT_MAKER_ID", json.getString("PAINT_MAKER_ID"));
			dob.setString("U8C_CODE", json.getString("U8C_CODE"));
			dob.setString("EMP_SRC", "ews");

			dm.insert(dob);
			// 更新商户列表
			ewsProviderUpdate(json.getString("PROV_SID"), sid, userId, dm);

			// 向单位部门表中插入人员的数据,形成树结构
			insertInfoDept(sid, json.getString("DUTYSID"), dm);

		}
	}
	// 向单位部门表中插入人员的数据,形成树结构
	private void insertInfoDept(String sid, String dutySid, DataManager dm) throws Exception {

		DataObject dbnew = new DataObject();
		dbnew.setModelName("T_INFO_DEPT");
		dbnew.setString("SID", G.getSequence("SEQ_T_IWL", dm));
		dbnew.setString("DEPTID", sid);
		dbnew.setString("PARENTDEPT", dutySid);
		dbnew.setString("CRITICAL_VALUE", "1");
		dbnew.setString("DEPTRANK", G.getSequence("SEQ_T_IWL", dm));
		dbnew.setString("INFO_CLASS", "3");

		dm.insert(dbnew);

	}

	private void updateEmployeeInfo(String userId, JSONObject json, DataManager dm) throws Exception {

		DataObject dob = dm.findByPrimaryKey(getEmpInfoBySid(),json.getString("SID"));
		if(null == dob){
			throw new GeneralException("人员不存在！");
		}

		String resetPwd = json.getString("RESET_PWD");

		dob.setModelName("T_INFO_EMPLOYEE");
		// 重置密码
		if (!G.isNvl(resetPwd) && "yes".equals(resetPwd)) {
			dob.setString("EMPPWD", "");
		}
		// 如果存在，删除的时候
		if (!G.isNvl(json.getString("STATE"))) {
			dob.setString("STATE", json.getString("STATE"));
		}
		dob.setString("EMP_SRC", "ews");
		dob.setString("UPTUSER", userId);
		dob.setString("UPTDATE", G.getCurrDt());
		dm.update(dob);
		// 更新商户列表
		ewsProviderUpdate(json.getString("PROV_SID"), dob.getString("SID"), userId, dm);

		String sql = "Update T_INFO_DEPT set PARENTDEPT=? where DEPTID=? and INFO_CLASS=3";
		dm.update(sql, new String[] { json.getString("DUTYSID"), json.getString("SID") });
	}

	private void ewsProviderUpdate(String provSid, String empSId, String userId, DataManager dm) throws Exception {

		String sql = "select * from t_ews_provider_extension where prov_sid=?";
		DataObject dob = dm.findByPrimaryKey(sql, provSid);
		if (dob == null) {
			throw new NullPointerException("ews该商户的extension表未查找到商户数据");
		}

		String empSidStr = dob.getString("EMP_SID");
		if (G.isNvl(empSidStr)) {
			dob.setString("EMP_SID", empSId);
		} else {
			// 存在了就不需要更新
			String[] arr = empSidStr.split(",");
			for (String str: arr) {
				if (str.equals(empSId)) {
					return;
				}
			}
			dob.setString("EMP_SID", empSidStr + "," + empSId);
		}

		dob.setModelName("T_EWS_PROVIDER_EXTENSION");

		dob.setString("UPT_TIME", G.getCurrentTime());
		dob.setString("UPT_USER", userId);

		dm.update(dob);

	}

}
