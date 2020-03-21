package com.corp.basic.dictionary;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;

/** 
 * MyEclipse Struts
 * Creation date: 12-26-2006
 * @author 邵俭
 * Content:用户字典信息列表
 *  */

public class DictionaryListAction implements BusinessAction {

	@SuppressWarnings("unchecked")
	public void doExecute(BusinessForm parm1, DataManager dm) throws java.lang.
    Exception {
		// TODO Auto-generated constructor stub
		
		DictionaryForm frm = (DictionaryForm)parm1;
		//		设置页码
		frm.getPagination().setCurrent(frm.getCurrent());
		
		String sql="";
		sql="select * from T_MAP_TRANS_DIC where STATE=1";
		
		/**高级查询******/
		frm.getQueryView().setSelect(sql);
		frm.getQueryView().setExtension("ORDER BY SID desc");
		
		sql = frm.getQueryView().getSql();
		frm.setDicList(dm.find(sql, null, false, null, frm.getPagination()));
	}

}
