package com.corp.oa.system.user;
import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.base.defaultdriver.UserView;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.exception.GeneralException;


public class UserSaveAction  implements BusinessAction {
	public UserSaveAction() {
	}

	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		UserForm frm = (UserForm) parm1;
		//		设置页码
		frm.getPagination().setCurrent(frm.getCurrent());
		
		UserView uv=(UserView)frm.getUserView();
		String useId=uv.getUserId();		
		
		String sql = "select * from T_INFO_EMPLOYEE where STATE=1 and upper(EMPID)='"
			+ frm.getT_INFO_EMPLOYEE().getString("EMPID").toUpperCase().trim() + "'";
		sql+=" and sid<>"+frm.getT_INFO_EMPLOYEE().getString("SID");
		
		DataObject dob = dm.findByPrimaryKey(sql,null);
		if (dob != null)
			//人员的登录名重复，请重新填写！
			throw new GeneralException("人员的登录名重复，请重新填写！");
		
		frm.getT_INFO_EMPLOYEE().setString("UPTUSER", useId);
		frm.getT_INFO_EMPLOYEE().setString("SID",frm.getSid());
		
		dm.update(frm.getT_INFO_EMPLOYEE());
		
	}
		
}
