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
<%@ page errorPage="../../error.jsp" %>
<jsp:useBean id="relation" scope="page" class="com.corp.frame.grant.RelationBean" />
<%request.setCharacterEncoding("UTF-8");%>
<%
relation.populate(request);
relation.initGrant();
if(request.getParameter("b_save")!= null){
  relation.setOption("save");
}
relation.execute();
pageContext.setAttribute("grantList",relation.getGrantList());
pageContext.setAttribute("fatherList",relation.getFatherList());
pageContext.setAttribute("fatherList0",relation.getFather0List());
final int COLUMN_COUNT = 5;
%>
<html>
<head>
<title>无标题文档</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../css/stylesheet.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../css/js.js"></script>
</head>
<form method="post">
<body>
 <table id="table_mk" border="0" cellspacing="1" cellpadding="3" class="tablebgW">
  <extend:iterateGroup id="element" name="relation" property="groupBean.groupList" column="5">
    <td  class="content0" id="outlooktitle<bean:write name="element" property="groupName"/>"
onMouseOver="if(this.className == 'content0' ){this.className='content1';}"
onMouseOut="if(this.className == 'content1' ){this.className='content0';}"
onClick="switchoutlookBar('<bean:write name="element" property="groupName"/>')">
<bean:write name="element" property="groupCNName"/></td>
  </extend:iterateGroup>
</table>
<br/>
  <table class="tablebgW" border="0" cellpadding="3" cellspacing="1">
    <tr>
      <td class="content">  <bean:write name="relation" property="father0Name"/>：</td>
      <td class="tableRight"> <html:select name="relation" property="fatherId0" size="1" onchange="submit();">
        <html:option value="">请选择．．．</html:option> <html:options collection="fatherList0" property="string(CODENO)" labelProperty="string(CODENAME)"/>
        </html:select> </td>
      <td class="content">  <bean:write name="relation" property="fatherName"/>：</td>
      <td class="tableRight"> <html:select name="relation" property="fatherId" size="1" onchange="submit();">
        <html:option value="">请选择．．．</html:option> <html:options collection="fatherList" property="string(CODENO)" labelProperty="string(CODENAME)"/>
        </html:select> </td>
    </tr>
    <tr>
      <td class="content"> <bean:write name="relation" property="grantName"/>：</td>
      <td class="tableRight"> <html:select name="relation" property="grantId" size="1" onchange="submit();">
        <html:option value="">请选择．．．</html:option> <html:options collection="grantList" property="string(CODENO)" labelProperty="string(CODENAME)"/>
        </html:select> </td>
      <td colspan="3" class="tableRight"> <html:hidden name="relation" property="grantType"/>
        <html:hidden name="relation" property="menuIndex"/>
        <input type="submit" name="b_save" value=" 保存 " class="button1">
        <input type="reset" name="" value=" 重置 " class="button1">
      </td>
    </tr>
  </table>
<br/>
<!-- 可以查到但未分类的死数据 -->
<table class="tablebgW" border="0" cellpadding="3" cellspacing="1">
  <!-- 一级 A -->
  <extend:iterateGroup id="element" name="relation" property="groupBean.unitList" column="<%=COLUMN_COUNT%>">
    <td>
     <% DataObject do1 = (DataObject)pageContext.getAttribute("element");
        String property="selectedList.string(" + do1.getString("CODENO") + ")"; %>
     <html:checkbox name="relation" property="<%=property%>" value="true" />
   <bean:write name="element" property="string(CODENAME)"/></td>
  </extend:iterateGroup>
</table>
<br/>
<!-- 一级 B -->
  <logic:iterate name="relation" property="groupBean.groupList" id="groupList1">
<div id="outlookdivin<bean:write name="groupList1" property="groupName"/>"
style="display:none;overflow:auto;width:100%;">
 <table  border="0" cellspacing="1" cellpadding="3" class="tablebgW">
  <!-- 二级详细列表 A -->
  <extend:iterateGroup id="element" name="groupList1" property="unitList" column="<%=COLUMN_COUNT%>">
    <td>
     <% DataObject do1 = (DataObject)pageContext.getAttribute("element");
        String property="selectedList.string(" + do1.getString("CODENO") + ")"; %>
     <html:checkbox name="relation" property="<%=property%>" value="true" />
     <bean:write name="element" property="string(CODENAME)"/></td>
  </extend:iterateGroup>
  <!-- END 二级详细列表 A -->
  <!-- 二级菜单 B -->
  <logic:iterate name="groupList1" property="groupList" id="groupList2">
  <tr class="tableRight">
    <% String rowNumber = "rowNumber("+String.valueOf(COLUMN_COUNT-1)+")";
       String title = "title("+String.valueOf(COLUMN_COUNT-1)+")";
       //String groupName=null;
       // 导航
 %>
    <td rowspan="<bean:write name="groupList2" property="<%=rowNumber%>"/>" class="tableTitle1" align="left">
    <input type="checkbox" onclick="selectAll(this);">
    <bean:write name="groupList2" property="groupCNName"/></td>
    <!-- 三级详细列表 A -->
    <logic:iterate name="groupList2" property="<%=title%>" id="element">
    <td>
     <% DataObject do1 = (DataObject)pageContext.getAttribute("element");
        String property = do1.getString("CODENO");
        if(property != null){
          property="selectedList.string(" + property + ")"; %>
     <html:checkbox name="relation" property="<%=property%>" value="true" />
     <bean:write name="element" property="string(CODENAME)"/>
    <% } %>
    </td>
    </logic:iterate>
    <!-- END 三级详细列表 A -->
  </tr>
  <!-- 三级详细列表 B -->
  <extend:iterateGroup id="element" name="groupList2" property="unitList" column="<%=COLUMN_COUNT-1%>">
    <td>
     <% DataObject do1 = (DataObject)pageContext.getAttribute("element");
        String property="selectedList.string(" + do1.getString("CODENO") + ")"; %>
     <html:checkbox name="relation" property="<%=property%>" value="true" />
   <bean:write name="element" property="string(CODENAME)"/></td>
  </extend:iterateGroup>
  <!-- END 三级详细列表 B -->
  </logic:iterate>
  <!-- END 二级菜单 B -->
</table>
</div>
 </logic:iterate>
 <!-- END 一级菜单 -->

</form>
</body>
</html>
<script language="javascript">
 <!--
  switchoutlookBar('<bean:write name="relation" property="menuIndex"/>');

 //-->
</script>
