package com.corp.basic.dictionary;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.base.defaultdriver.UserView;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.exception.GeneralException;
import com.corp.oa.util.G;

/** 
 * MyEclipse Struts
 * Creation date: 12-26-2006
 * @author 邵俭
 * Content:用户字典信息修改
 *  */

public class DictionaryEditAction implements BusinessAction {

	public void doExecute(BusinessForm parm1, DataManager dm) throws java.lang.
    Exception {
		// TODO Auto-generated constructor stub
		
		DictionaryForm frm = (DictionaryForm)parm1;
		//		设置页码
		frm.getPagination().setCurrent(frm.getCurrent());
		
		String sql="";
		
		/**查询是否有重复记录********/
		sql="select * from T_MAP_TRANS_DIC where STATE=1 and SID<>? and " +
				"(SEND_TEL=? and RECV_TEL=?)";
		
		DataObject dob = dm.findByPrimaryKey(sql,new String[]{frm.getSid()+"",frm.getT_MAP_TRANS_DIC().getString("SEND_TEL"),frm.getT_MAP_TRANS_DIC().getString("RECV_TEL")});
		/**如果有重复记录，不添加****/
		if(dob!=null){
			throw new GeneralException("error.DictionaryEditAction");
		}
		
		//获得登陆人的信息
		UserView uv = (UserView)frm.getUserView();
		String userid = uv.getUserId();
		
		frm.getT_MAP_TRANS_DIC().setString("SID",frm.getSid()+"");
		//更新人
		frm.getT_MAP_TRANS_DIC().setString("UPT_EMP",userid);
		//更新时间
		frm.getT_MAP_TRANS_DIC().setString("UPT_DATE",G.getDateTime());
		
		dm.update(frm.getT_MAP_TRANS_DIC());
	}

}
