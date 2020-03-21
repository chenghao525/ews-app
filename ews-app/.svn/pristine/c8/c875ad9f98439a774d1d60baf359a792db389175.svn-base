<%@ page
contentType="text/html; charset=UTF-8"
import="java.util.*"
import="com.corp.frame.core.dao.code.*"
import="com.corp.frame.core.dao.*"
%>
<jsp:useBean id="getHelp" class="com.corp.frame.bean.GetHelpBean"
 scope="page" />
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-extend.tld" prefix="extend" %>
<%@ page errorPage="error.jsp" %>
<%request.setCharacterEncoding("UTF-8");%>
<%
getHelp.populate(request);
getHelp.execute();
Code code = getHelp.getCode();
%>

<html>
<head>
<title>代码查询系统</title>
<link rel="stylesheet" href="css/stylesheet.css" type="text/css">
<script language="JavaScript" src="css/js.js"></script>

<body onKeyDown="return pf_ChangeFocus();">

<form name="valueForm" method="get">
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="tablebgW">
    <tr >
      <td colspan="2" class="content">代码帮助系统</td>
    </tr>
    <tr class="tableRight">
      <td align="right"> 代码值： </td>
      <td>
        <html:text name="getHelp" property="codeValue" />
        <html:hidden name="getHelp" property="codeType" />
        <html:hidden name="getHelp" property="controlName" />
        <html:hidden name="getHelp" property="fieldType" />
        <html:hidden name="getHelp" property="checkCodePermission" />
        <input class="button1" type="submit" name="search" value="查 询">
      </td>
    </tr>
    <tr>
      <td align="center" class="tableTitle" >代码</td>
      <td align="center" class="tableTitle" >代码值</td>
    </tr>
    <%
     Iterator iter = code.getValue().iterator();
     while(iter.hasNext())
	{
		DataObject do1 = (DataObject)iter.next();
		out.println("<tr class=\"tableRight\"> ");
            	out.println("<td>&nbsp;<input type=\"radio\" name=\"myValue\" value=\"1\" onclick=\"document.valueForm.codeValue.value='"+do1.getString(code.getCodeName())+"'\">");
		out.println(do1.getString(code.getCodeName())+"</td>");
		out.println("<td>&nbsp;<input type=\"radio\" name=\"myValue\" value=\"1\" onclick=\"document.valueForm.codeValue.value='"+do1.getString(code.getCodeCNName())+"'\">");
		out.println(do1.getString(code.getCodeCNName())+"</td>");
		out.println("</tr>");
	}
%>
  </table>
    <table width="90%" border="0" cellspacing="2" cellpadding="8">
  <tr>
    <td align="center">
	  <INPUT TYPE="button" class="button-ui" class="button1" value="取值并关闭窗口" onclick="setCodeValue('<bean:write name="getHelp" property="controlName"/>');window.close();">
	  <INPUT TYPE="button" class="button-ui" class="button1" value=" 取  值 " onclick="setCodeValue('<bean:write name="getHelp" property="controlName"/>');">
	  <INPUT TYPE="button" class="button-ui" class="button1" value=" 关闭窗口" onclick="window.close();">
    </td>
  </tr>
</table>
<input type="hidden" name="codeValue" value="">
</form>
</body>
</html>