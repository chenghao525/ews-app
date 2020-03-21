package com.corp.oa.system.org;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;
import com.corp.oa.util.G;

public class OrgEmpFindAction implements BusinessAction {
	public OrgEmpFindAction() {
	}

	@SuppressWarnings("unchecked")
	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		OrgForm of = (OrgForm) parm1;

		String empSid = of.getEmp_sid();
		String sql = "";

		sql = "select sid as CODENO,content as CODENAME from t_list_status where infotype=2 and state=1 order by optorder";
		of.setSexList(dm.find(sql, null));

		sql = "select T.*,F_GET_EMP_DUTY_CLASS(SID)as DUTYCLASS,f_get_emp_duty(SID) AS DUTY ";
		sql += " from T_INFO_EMPLOYEE t where t.sid=?";

		// 将用户点击的图片的ID保存到SESSION中
		// LSS 2007-02-27
		of.getRequest().getSession()
				.setAttribute("TREE_COLLAPSE_IMG", of.getCollapseImg());

		of.setEmpInfo(dm.find(sql, empSid));

		of.setT_INFO_EMPLOYEE(dm.findByPrimaryKey(sql, empSid));

		of.setDept_sid(of.getT_INFO_EMPLOYEE().getString("DUTYSID"));

		// 获取员工的相片
		String photo = G.getImagePath(dm, empSid, "employee");
		if (photo == null || "".equals(photo))
			photo = "/upload/photo/default.jpg";
		of.setPhotoPath(photo);
	}
}
