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

<jsp:useBean id="domain" scope="page" class="com.corp.frame.bean.MenuListBean" />
<%request.setCharacterEncoding("UTF-8");%>
<%
domain.populate(request);
domain.getQueryView().setSelect("select DM_FL AS CODENO,MC_FL AS CODENAME from T_FUNC_XT_SJKDXFL WHERE DM_FL NOT LIKE '00%' AND DM_FL NOT LIKE '14%' ORDER BY DM_FL");
domain.execute();
%>

<!-------------------------------------------------------------------------->
<html>
<head>
<title>
domainEdit
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></head>
<link href="../css/stylesheet.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../css/js.js"></script>
<body bgcolor="#ffffff">


<table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
  <tr>
    <td class="content" colspan="5">业务数据维护 </td>
  </tr>
  <extend:iterateGroup name="domain" property="list" id="element"column="5">
  <td class="tableRight"><a href="domainList.jsp?catId=<bean:write name="element" property="string(CODENO)"/>"
    > <bean:write name="element" property="string(CODENAME)"/></a></td>
  </extend:iterateGroup>
</table>
</body>
</html>