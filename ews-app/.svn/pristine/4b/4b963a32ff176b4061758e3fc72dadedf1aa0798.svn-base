<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page
import="java.util.*"
import="com.corp.frame.core.dao.DataObject"
import="com.corp.frame.core.dao.DataManager"
import="com.corp.frame.core.dao.code.Code"
import="com.corp.frame.core.dao.code.CodeManager"
import="com.corp.frame.core.util.DateValue"
import="com.corp.frame.core.base.UserInfo"
import="com.corp.frame.core.metadata.FieldProperty"
import="com.corp.frame.core.action.PrefixClass"
import="com.corp.frame.core.util.Validate"
import="com.corp.frame.core.util.StringUtil"
import="com.corp.frame.bean.MetaData"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-extend.tld" prefix="extend" %>
<%@ page errorPage="../error.jsp" %>
<%request.setCharacterEncoding("UTF-8");%>
<jsp:useBean id="queryBean" scope="request" class="com.corp.frame.datawindow.QueryExecute" />
<%
if(request.getParameter("b_execute") != null){
  queryBean.populate(request);
  queryBean.execute();
}
%>

<link href="/GeneralReport/css/stylesheet.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="/GeneralReport/css/js.js"></script>
<form name="form1" method="post" action="">
  <table width="100%">
    <tr>
      <td>&nbsp;</td>
      <td><bean:write name="queryBean" property="message"/></td>
    </tr>
    <tr>
      <td>语句</td>
      <td><html:textarea name="queryBean" property="sql" cols="60" rows="4"/></td>
    </tr>
    <tr>
      <td></td>
      <td><html:checkbox name="queryBean" property="query" value="true"/>是否查询</td>
    </tr>
    <tr>
      <td colspan="2" height="23" align="center">
           <html:hidden name="queryBean" property="title"/>
		   <html:hidden name="queryBean" property="id"/>
		   <input type="submit" name="b_execute" value="执行" onClick="action='queryDefine.jsp';" class="button1">
          <input type="submit" name="b_reset" value="清空" onClick="action='queryDefine.jsp';" class="button1">
          <input type="submit" name="b_save" value="保存" onClick="action='querySave.jsp';" class="button1">
          <input type="submit" name="b_back" value="返回" onClick="action='queryList.jsp'" class="button1" >
      </td>
    </tr>
  </table>
</form>

<table width="100%">
  <%
  Object[] key = null;
  Collection coll = queryBean.getResultList();
  Iterator iter = coll.iterator();
  int count = 0;
  while(iter.hasNext()){
    count++;
    DataObject do1 = (DataObject)iter.next();
    if(key == null){
       key = do1.getKeyName();
       out.print("<tr bgcolor=#CCCCCC>");
       out.print("<td>序号</td>");
       for(int i= 0;i<key.length;i++){
         out.print("<td>");
         out.print(key[i]);
         out.print("</td>");
       }
      out.println("</tr>");
    }
	if(count%2==0){
      out.print("<tr bgcolor=#EEEEEE>");
	}
	else{
      out.print("<tr bgcolor=#F9F9F9>");
	}
    out.print("<td>"+count+"</td>");
    for(int i= 0;i<key.length;i++){
       out.print("<td>");
       if(!do1.isNull((String)key[i])){
         out.print(do1.getString((String)key[i]));
       }
       out.print("</td>");
    }
    out.println("</tr>");
  }
%>
</table>
<%System.gc();%>