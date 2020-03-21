package com.corp.oa.system.user;
import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;


public class UserEditAction  implements BusinessAction {
	public UserEditAction() {
	}

	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		UserForm frm = (UserForm) parm1;
		
		String sql = "";
		
		sql="select t.* from T_INFO_EMPLOYEE t where t.sid="+frm.getSid();
		
		frm.setT_INFO_EMPLOYEE(dm.findByPrimaryKey(sql,null));
	}

}
