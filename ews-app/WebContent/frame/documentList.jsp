<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page
import="java.util.*"
import="com.corp.frame.core.dao.DataObject"
import="com.corp.frame.bean.MetaData"%>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-extend.tld" prefix="extend" %>
<%request.setCharacterEncoding("UTF-8");%>
<link href="css/stylesheet.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="css/js.js"></script>
<html>
<head>
<title>
文书大类
</title>
</head>
<jsp:useBean id="document" scope="request"
class="com.corp.frame.bean.Document" />
<body>
<form action="tableList.jsp" method="post">
<%
document.populate(request);
document.execute();
Collection coll = document.getDocumentList();
pageContext.setAttribute("tempColl",coll);
%>
<table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
    <tr>
      <td class="content">查询文书</td>
    </tr>
</table>
<br/>
<table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
<extend:iterateGroup id="element" name="tempColl" column="2">
<td><a style="cursor:hand;" onClick="setValue('queryView.queryObject(DM_WSDL).value','<bean:write name="element" property="string(ID)"/>');setValue('queryView.queryObject(DM_WSZL).value','');setValue('queryView.queryObject(MC_WSZL).value','');submit();">
<bean:write name="element" property="string(NAME)"/></a></td>
</extend:iterateGroup>
</table>
<br/>
  <table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
    <tr>
      <td class="tableLeft"> 编号： </td>
      <td class="tableRight">
        <input type="text" name="queryView.queryObject(DM_WSZL).value" >
        <input type="hidden" name="queryView.queryObject(DM_WSZL).field" value="DM_WSZL">
        <input type="hidden" name="queryView.queryObject(DM_WSZL).operator" value="LIKE">
        <input type="hidden" name="queryView.queryObject(DM_WSZL).type" value="1">
      </td>
    </tr>
    <tr>
      <td class="tableLeft"> 名称： </td>
      <td class="tableRight">
        <input type="text" name="queryView.queryObject(MC_WSZL).value" >
        <input type="hidden" name="queryView.queryObject(MC_WSZL).field" value="MC_WSZL">
        <input type="hidden" name="queryView.queryObject(MC_WSZL).operator" value="LIKE">
        <input type="hidden" name="queryView.queryObject(MC_WSZL).type" value="1">
      </td>
    </tr>
    <tr>
      <td colspan="2" class="tableCenter">
        <input type="hidden" name="queryView.queryObject(DM_WSDL).value" value="" >
        <input type="hidden" name="queryView.queryObject(DM_WSDL).field" value="DM_WSDL">
        <input type="hidden" name="queryView.queryObject(BZ_XY).value" value="Y" >
        <input type="hidden" name="queryView.queryObject(BZ_XY).field" value="BZ_XY">
       <input type="hidden" name="queryView.select"
value="select DM_WSZL as DOMAIN_NAME,MC_WSZL as DOMAIN_CNNAME,SJKBM AS TABLE_NAME from DM_WSZL ">
<input type="hidden" name="queryView.extension" value="">
<input type="submit" name="b_query" value=" 查询 "  onClick="setValue('queryView.queryObject(DM_WSDL).value','');" class="button1">

     </td>
    </tr>
  </table>
</form>
</body>

</html>
