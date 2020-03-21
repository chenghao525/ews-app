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
 * @author �ۼ�
 * Content:�û��ֵ���Ϣ�޸�
 *  */

public class DictionaryEditAction implements BusinessAction {

	public void doExecute(BusinessForm parm1, DataManager dm) throws java.lang.
    Exception {
		// TODO Auto-generated constructor stub
		
		DictionaryForm frm = (DictionaryForm)parm1;
		//		����ҳ��
		frm.getPagination().setCurrent(frm.getCurrent());
		
		String sql="";
		
		/**��ѯ�Ƿ����ظ���¼********/
		sql="select * from T_MAP_TRANS_DIC where STATE=1 and SID<>? and " +
				"(SEND_TEL=? and RECV_TEL=?)";
		
		DataObject dob = dm.findByPrimaryKey(sql,new String[]{frm.getSid()+"",frm.getT_MAP_TRANS_DIC().getString("SEND_TEL"),frm.getT_MAP_TRANS_DIC().getString("RECV_TEL")});
		/**������ظ���¼�������****/
		if(dob!=null){
			throw new GeneralException("error.DictionaryEditAction");
		}
		
		//��õ�½�˵���Ϣ
		UserView uv = (UserView)frm.getUserView();
		String userid = uv.getUserId();
		
		frm.getT_MAP_TRANS_DIC().setString("SID",frm.getSid()+"");
		//������
		frm.getT_MAP_TRANS_DIC().setString("UPT_EMP",userid);
		//����ʱ��
		frm.getT_MAP_TRANS_DIC().setString("UPT_DATE",G.getDateTime());
		
		dm.update(frm.getT_MAP_TRANS_DIC());
	}

}
