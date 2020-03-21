<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page
import="java.sql.*"
import="com.corp.frame.core.dao.DataObject"
import="com.corp.frame.bean.MetaData"
%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-extend.tld" prefix="extend" %>
<%@ page errorPage="../error.jsp" %>

<jsp:useBean id="domain" scope="page" class="com.corp.frame.bean.ListDomainBean" />
<%request.setCharacterEncoding("UTF-8");%>
<%
domain.populate(request);
domain.execute();
%>

<!-------------------------------------------------------------------------->
<html>
<head>
<title>
domainEdit
</title>
</head>
<link href="../css/stylesheet.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../css/js.js"></script>
<body bgcolor="#ffffff">

<form name="batAdd" method="post">
  <table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
    <tr>
      <td class="content" colspan="4">数据对象维护</td>
    </tr>
    <tr>
      <td class="tableLeft">对象名称</td>
      <td class="tableRight">
        <html:text name="domain" property="queryView.queryObject(MC_DX).value" />
        <input type="hidden" name="queryView.queryObject(MC_DX).field" value="MC_DX">
        <input type="hidden" name="queryView.queryObject(MC_DX).operator" value="LIKE">
      </td>
      <td class="tableLeft">对象中文名称</td>
      <td class="tableRight">
        <html:text name="domain" property="queryView.queryObject(MC_DX_CN).value" />
        <input type="hidden" name="queryView.queryObject(MC_DX_CN).field" value="MC_DX_CN">
        <input type="hidden" name="queryView.queryObject(MC_DX_CN).operator" value="LIKE">
     </td>
    </tr>
	<tr class="tableCenter"><td colspan="4">
        <input type="submit" name="b_query" value=" 查询 "  class="button1">
	</td></tr>
  </table>
  <br/>
  <table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
    <tr class="tableTitle">
      <td>子系统</td>
      <td>对象代码</td>
      <td>对象名称</td>
      <td>对象中文名称</td>
      <td>权限控制类型</td>
      <td>操作</td>
    </tr>
	<logic:iterate name="domain" property="resultList" id="element">
    <tr class="tableRight">
      <td class="tableRight"><bean:write name="element" property="string(CAT)"/></td>
      <td class="tableRight"><bean:write name="element" property="string(DOMAIN_ID)"/></td>
      <td class="tableRight"><bean:write name="element" property="string(DOMAIN_NAME)"/></td>
      <td class="tableRight"><bean:write name="element" property="string(DOMAIN_CNNAME)"/></td>
      <td class="tableCenter"><bean:write name="element" property="string(PERMISSION_TYPE)"/></td>
      <td class="tableCenter"><a style="cursor:hand;"
onClick="redirect('maintenance.jsp?domain_name=<bean:write name="element" property="string(DOMAIN_NAME)"/>');">维护</a></td>
    </tr>
	</logic:iterate>
  </table>
<extend:paginationLink name="domain" property="pagination" buttonClass="button1"/>
<%--
<INPUT TYPE="button" class="button-ui" value=" 返回 " class="button1" onClick="redirect('index.jsp');">
--%>
<br>
</form>
</body>
</html>