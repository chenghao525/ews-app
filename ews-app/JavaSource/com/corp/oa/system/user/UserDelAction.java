package com.corp.oa.system.user;
import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.exception.GeneralException;

public class UserDelAction  implements BusinessAction {
	public UserDelAction() {
	}

	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {
		UserForm frm = (UserForm) parm1;
		//		设置页码
		frm.getPagination().setCurrent(frm.getCurrent());
		
		String SID=String.valueOf(frm.getSid());
		
		if ("0".equals(SID))
			//不能删除管理员账号！
			throw new GeneralException("error.DelAdmin");
		
		if (frm.getUserView().getUserId().equals(SID))
			//不能删除当前登录的用户！
			throw new GeneralException("error.DelCurrentUser");
		
		String sql = "Update  t_info_dept set STATE='0' where DEPTID='" +SID + "'";
		dm.update(sql, null);
		
		sql = "Update T_INFO_EMPLOYEE set STATE='0' where sid=" + SID;
		dm.update(sql, null);
		
	}
		
	
}
