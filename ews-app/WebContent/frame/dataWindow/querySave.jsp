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
<jsp:useBean id="queryBean" scope="request"
 class="com.corp.frame.datawindow.QueryDefine" />
<%
queryBean.populate(request);
if(request.getParameter("b_execute") != null){
  queryBean.execute();
  response.sendRedirect("queryList.jsp");
}
%>

<link href="/GeneralReport/css/stylesheet.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="/GeneralReport/css/js.js"></script>
<form name="form1" method="post" action="">
  <table width="100%">
    <tr>
      <td>编号</td>
      <td><html:text name="queryBean" property="id"/></td>
    </tr>
    <tr>
      <td>标题</td>
      <td><html:text name="queryBean" property="title" size="40"/></td>
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
      <td colspan="2" align="center"> 
        <input type="submit" name="b_execute" value="保存" class="button1">
          <input type="submit" name="b_back" value="返回" onClick="action='queryList.jsp'" class="button1">
      </td>
    </tr>
  </table>
</form>
