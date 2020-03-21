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
<jsp:useBean id="domain" scope="page" class="com.corp.frame.bean.AddDomainBean" />
<%request.setCharacterEncoding("UTF-8");%>
<%
domain.populate(request);
if(request.getParameter("b_batAddDomain") != null){
  domain.setOption("save");
}
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
<script language="javascript">
function CA3(){
  for(var i=0;i<document.batAdd.elements.length;i++){
    var e=document.batAdd.elements[i];
    if(e.type =='checkbox') e.checked=document.batAdd.allbox.checked;
  }
}
</script>
<body bgcolor="#ffffff">

<form name="batAdd" method="post">
  <table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
    <tr>
      <td class="content" colspan="2">数据库中存在的对象</td>
    </tr>
    <tr>
      <td class="tableLeft">对象名称</td>
      <td class="tableRight">
        <html:text name="domain" property="queryView.queryObject(MC_DX).value" />
        <input type="hidden" name="queryView.queryObject(MC_DX).field" value="a.tname">
        <input type="hidden" name="queryView.queryObject(MC_DX).operator" value="LIKE">
        <%--
        <input type="hidden" name="queryView.queryObject(MC_DX).type" value="1">
        --%>
        <input type="submit" name="b_query" value=" 查询 "  class="button1">
      </td>
    </tr>
  </table>
  <br/>
      <% pageContext.setAttribute("PermissionType",
         com.corp.frame.bean.MetaData.getPermissionType());
		 System.out.print(pageContext.getAttribute("PermissionType"));
		 
		 %>
  <table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
    <tr class="tableTitle">
      <td><input type="checkbox" name="allbox" onclick="CA3();" >选择</td>
      <td>对象名称</td>
      <td>对象中文名称</td>
      <td>权限控制类型</td>
      <td>权限控制字段</td>
      <td>子系统分类</td>
    </tr>
    <%
{
  try{
  for(int i=0;i<domain.getDomainList().size();i++){
   String domainId = "domainList[" + i + "].string(DM_DX)";
   String domainName = "domainList[" + i + "].string(MC_DX)";
   String domainCNName = "domainList[" + i + "].string(MC_DX_CN)";
   String permissionType = "domainList[" + i + "].string(BZ_QX)";
   String permissionField = "domainList[" + i + "].string(QXKZZD)";
   String cat = "domainList[" + i + "].string(DM_FL)";
%>
    <tr class="tableRight">
      <td class="tableCenter">
        <input type=checkbox name="<%=domainName%>" value="<bean:write name="domain" property="<%=domainName%>"/>">
      <input type="hidden" name="<%=domainId%>" value="<bean:write name="domain" property="<%=domainName%>"/>"></td>
     <td><bean:write name="domain" property="<%=domainName%>"/></td>
      <td><html:text name="domain" property="<%=domainCNName%>"/></td>
      <td>
        <html:select name="domain" property="<%=permissionType%>" size="1">
        <html:options collection="PermissionType" property="string(CODENO)" labelProperty="string(CODENAME)"/>
        </html:select> </td>
      <td><html:text name="domain" property="<%=permissionField%>" size="10"/></td>
      <td><html:text name="domain" property="<%=cat%>" size="10"
          onkeydown="getHelp('T_FUNC_XT_SJKDXFL',this,'');"/></td>
    </tr>
    <% }
  }
  catch(Exception e){
  e.printStackTrace();
  out.print(e.getMessage());
 }
}
%>
  </table>
<extend:paginationLink name="domain" property="pagination" buttonClass="button1"/>
<input name="b_batAddDomain" type="submit" value=" 保存 " class="button1" >
<INPUT TYPE="button" class="button-ui" value=" 返回 " class="button1" onClick="redirect('domainSelect.jsp');">
<br>
<table><tr><td>
操作说明：选择要添加的数据库表名，输入其中文名，选择其权限控制类型，如果该字段是分税务机关
或分税务人员，在权限控制字段中输入字段名称，否则可不填。在子系统中输入所属子系统代码。
</td></tr></table>
</form>
</body>
</html>
