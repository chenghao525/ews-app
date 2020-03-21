package com.corp.oa.system.org;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.base.UserInfo;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.exception.GeneralException;
import com.corp.oa.util.G;

public class OrgEmpSaveAction implements BusinessAction {
	public OrgEmpSaveAction() {
	}

	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {

		OrgForm of = (OrgForm) parm1;

		// 获得操作人员的信息
		UserInfo userView = of.getUserView();
		// 内部编号
		String useId = userView.getUserId();

		// 父部门的内部编号
		String deptP = of.getDept_sid();

		// 人员的内部编号 增加为null
		String empSid = of.getEmp_sid();

		// 保存最终的编号,已被跳转页面使用
		String sLastID = "";

		if ("true".equals(of.getDeptAdd())) { // 插入信息的人员信息
			String sSql = "select * from T_INFO_EMPLOYEE where STATE=1 and upper(EMPID)=?";
			DataObject dob = dm.findByPrimaryKey(sSql, of.getT_INFO_EMPLOYEE()
					.getString("EMPID").toUpperCase().trim());
			if (dob != null)
				// 人员的登录名重复，请另填写！
				throw new GeneralException("账号已存在");

			// 新插入的人员的内部编号
			empSid = G.getSequence("SEQ_T_IWL", dm);

			// 向人员表中插入新数据
			of.getT_INFO_EMPLOYEE().setString("SID", empSid);
			of.getT_INFO_EMPLOYEE().setString("EMPPWD", "");
			of.getT_INFO_EMPLOYEE().setString("CRITICAL_VALUE", "1");
			of.getT_INFO_EMPLOYEE().setString("CRTUSER", useId);
			of.getT_INFO_EMPLOYEE().setString("UPTUSER", useId);
			of.getT_INFO_EMPLOYEE().setString("DUTYSID", deptP);

			dm.insert(of.getT_INFO_EMPLOYEE());

			// 向单位部门表中插入人员的数据,形成树结构
			DataObject dbnew = new DataObject();
			dbnew.setModelName("T_INFO_DEPT");
			dbnew.setString("SID", G.getSequence("SEQ_T_IWL", dm));
			dbnew.setString("DEPTID", empSid);
			dbnew.setString("PARENTDEPT", deptP);
			dbnew.setString("CRITICAL_VALUE", "1");
			dbnew.setString("DEPTRANK", G.getSequence("SEQ_T_IWL", dm));
			dbnew.setString("INFO_CLASS", "3");

			dm.insert(dbnew);
		} else { // 修改人员的信息
			of.getT_INFO_EMPLOYEE().setString("UPTUSER", useId);
			of.getT_INFO_EMPLOYEE().setString("SID", empSid);
			String dutys = of.getT_INFO_EMPLOYEE().getString("DUTY");

			dutys = dutys.replaceAll("_2_", "");
			of.getT_INFO_EMPLOYEE().setString("DUTYSID", dutys);
			dm.update(of.getT_INFO_EMPLOYEE());
			sLastID = empSid;

			String sql = "";
			sql = "Update T_INFO_DEPT set PARENTDEPT=? where DEPTID=? and INFO_CLASS=3";
			dm.update(sql, new String[] { dutys, empSid });
		}

		// 加上辨识的字符串,因为JSP页面中采用了此种形式
		of.setTransInfo("emp_" + sLastID + "_");

		G.setImagePath(dm, empSid, "employee",
				of.getPhotoPath());

	}

}
