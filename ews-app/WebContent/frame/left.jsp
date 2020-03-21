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
<%@ page errorPage="error.jsp" %>

<jsp:useBean id="domain" scope="page" class="com.corp.frame.bean.MenuListBean" />
<%request.setCharacterEncoding("UTF-8");%>
<%
domain.populate(request);
domain.getQueryView().setSelect("SELECT DM_FL AS CODENO,MC_FL AS CODENAME FROM T_FUNC_XT_SJKDXFL");
domain.execute();
%>

<!-------------------------------------------------------------------------->
<html>
<head>
<title>
domainEdit
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></head>
<link href="css/stylesheet.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="css/js.js"></script>
<body bgcolor="#ffffff">


<table border="0" cellpadding="3" cellspacing="1" class="1tablebg">
  <logic:iterate name="domain" property="resultList" id="element"> 
  <tr class="tableRight"> 
    <td class="tableRight"><a href="/frame/system/domainList.jsp?catId=<bean:write name="element" property="string(CODENO)"/>" 
      target="mainFrame"> <bean:write name="element" property="string(CODENAME)"/></a></td>
  </tr>
  </logic:iterate> 
  <tr class="tableRight"> 
    <td class="tableRight"><a href="/frame/system/domainSelect.jsp" target="mainFrame"> 数据字典维护</a></td>
  </tr>
  <tr class="tableRight"> 
    <td class="tableRight"><a href="/frame/system/maintenance.jsp?domain_name=PROFILE" target="mainFrame"> 
      用户管理</a></td>
  </tr>
  <tr class="tableRight"> 
    <td class="tableRight"><a href="/frame/system/maintenance.jsp?domain_name=ORGANIZATION" target="mainFrame"> 
      机构管理</a></td>
  </tr>
  <tr class="tableRight"> 
    <td class="tableRight"><a href="/frame/system/maintenance.jsp?domain_name=ROLES" target="mainFrame"> 
      角色管理</a></td>
  </tr>
  <tr class="tableRight"> 
    <td class="tableRight"><a href="/frame/system/maintenance.jsp?domain_name=DATAPARTITION" target="mainFrame"> 
      数据权限维护</a></td>
  </tr>
  <tr class="tableRight"> 
    <td class="tableRight"><a href="/frame/system/maintenance.jsp?domain_name=PERMISSION" target="mainFrame"> 
      权限维护</a></td>
  </tr>
  <tr class="tableRight"> 
    <td class="tableRight"><a href="/frame/system/maintenance.jsp?domain_name=PERMISSIONGROUP" target="mainFrame"> 
      权限组维护</a></td>
  </tr>
  <tr class="tableRight"> 
    <td class="tableRight"><a href="/frame/system/maintenance.jsp?domain_name=OPERATION" target="mainFrame"> 
      操作维护</a></td>
  </tr>
  <tr class="tableRight"> 
    <td class="tableRight"><a href="/frame/system/maintenance.jsp?domain_name=OPERATIONGROUP" target="mainFrame"> 
      操作组维护</a></td>
  </tr>
  <tr class="tableRight"> 
    <td class="tableRight"><a href="/frame/system/grant/rolepermission.jsp?grantType=PermissionToUser" target="mainFrame"> 
      用户权限管理</a></td>
  </tr>
  <tr class="tableRight"> 
    <td class="tableRight"><a href="/frame/system/grant/rolepermission.jsp?grantType=PermissionToRole" target="mainFrame"> 
      角色权限管理</a></td>
  </tr>
  <tr class="tableRight"> 
    <td class="tableRight"><a href="/frame/system/grant/rolepermission.jsp?grantType=UserToRole" target="mainFrame"> 
      角色用户管理</a></td>
  </tr>
  <tr class="tableRight"> 
    <td class="tableRight">操作权限管理</td>
  </tr>
  <tr class="tableRight">
    <td class="tableRight"><a href="/frame/tiles/dataWindow/queryList.jsp" target="mainFrame">Tiles演示 </a> 
    </td>
  </tr>
  <tr class="tableRight"> 
    <td class="tableRight"> <a href="/frame/eflow/index.jsp" target="mainFrame" >工作流</a></td>
  </tr>
  <tr class="tableRight"> 
    <td class="tableRight"> <a href="/frame/system/reset.jsp" target="mainFrame" >更新缓存</a></td>
  </tr>
</table>

</body>
</html>