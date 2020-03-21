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
 * Content:用户字典信息添加
 *  */

public class DictionarySaveAction implements BusinessAction {

	public void doExecute(BusinessForm parm1, DataManager dm) throws java.lang.
    Exception {
		// TODO Auto-generated constructor stub
		
		DictionaryForm frm = (DictionaryForm)parm1;
		//		设置页码
		frm.getPagination().setCurrent(frm.getCurrent());
		
		String sql="";
		
		/**查询是否有重复记录********/
		sql="select * from T_MAP_TRANS_DIC where SEND_USER_ID = ? " +
				"and RECV_ID = ? or (SEND_TEL=? or SEND_FAX=? or RECV_TEL=? or RECV_FAX=?)";
		
		DataObject dob = dm.findByPrimaryKey(sql,new String[]{frm.getT_MAP_TRANS_DIC().getString("SEND_USER_ID"),frm.getT_MAP_TRANS_DIC().getString("RECV_ID"),frm.getT_MAP_TRANS_DIC().getString("SEND_TEL"),frm.getT_MAP_TRANS_DIC().getString("SEND_FAX"),frm.getT_MAP_TRANS_DIC().getString("RECV_TEL"),frm.getT_MAP_TRANS_DIC().getString("RECV_FAX")});
		/**如果有重复记录，不添加****/
		if(dob!=null){
			throw new GeneralException("error.DictionaryEditAction");
		}
		String sid = G.getSequence("SEQ_T_MAP_TRANS", dm);
		
		//获得登陆人的信息
		UserView uv = (UserView)frm.getUserView();
		String userid = uv.getUserId();
		
		frm.getT_MAP_TRANS_DIC().setString("SID",sid);
		frm.getT_MAP_TRANS_DIC().setString("UPT_EMP",userid);
		
		dm.insert(frm.getT_MAP_TRANS_DIC());
	}

}
