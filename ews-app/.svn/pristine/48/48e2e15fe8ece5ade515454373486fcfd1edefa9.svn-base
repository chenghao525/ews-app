package com.corp.code;

import javax.servlet.jsp.PageContext;

import com.corp.frame.core.action.PrefixClass;
import com.corp.frame.core.base.UserInfo;
import com.corp.frame.core.code.Code;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;

public class DutyListAction implements PrefixClass {
	
	public Code doExecute(DataObject dataObject, UserInfo userInfo,
			DataManager dm,PageContext pageContext) throws Exception {
		
		String sql="";
		
		String empSid=userInfo.getUserId();
		String corp="";
		
		//保存类别信息列表 
		sql="SELECT sid as CODENO,";
		sql+=" (f_getspecstring((level-1)*2,' ') || deptname) as CODENAME FROM T_INFO_DEPT";
		sql+=" where state=1 and INFO_CLASS<>3 start with PARENTDEPT='";
		
		if(!dm.getUser().isSuperBusinessRole()){//非超级业务管理员
			corp=G.getEmployeeCorp(dm, empSid);//获取所在的公司，第二层
			if(!"".equals(corp))
				sql+=corp;
		}else{
			sql+="0";
		}
		sql+="'";
		
		sql+=" Connect By Prior SID =ParentDEPT";
		Code code = new Code();
		code.setCodeName("CODENO");
		code.setCodeCNName("CODENAME");
		code.setValue(dm.find(sql,null));
		return code;
	}


}
