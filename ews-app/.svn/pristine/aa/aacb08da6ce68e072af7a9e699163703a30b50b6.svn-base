<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page
import="java.util.*"
import="com.corp.frame.core.metadata.FieldProperty"
import="com.corp.frame.bean.MetaData"%>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-extend.tld" prefix="extend" %>
<%request.setCharacterEncoding("UTF-8");%>
<link href="css/stylesheet.css" rel="stylesheet" type="text/css">

<jsp:useBean id="fieldList" class="com.corp.frame.bean.FieldList"
 scope="page" />
<%
String formAction = null;
fieldList.populate(request);
if("statistic".equals(fieldList.getOption())){
  formAction = "fieldList.jsp";
}
else if("reStatistic".equals(fieldList.getOption())){
  fieldList.getQueryView().remove("row_tag");
  fieldList.getQueryView().remove("column_tag");
  fieldList.setOption("statistic");
  formAction = "resultList2.jsp";
}
Collection coll = MetaData.getFieldListAndLevel(fieldList.getTableName());
Iterator iter = coll.iterator();
%>
<form action="<%=formAction%>" method="post">
<table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
    <tr><td class="content">输入统计条件</td></tr>
</table>
<br/>
<table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
<tr>
<td class="tableLeft">
查询文书：</td><td class="tableRight"><bean:write name="fieldList" property="document.string(DOMAIN_CNNAME)"
 filter="true"/>（<bean:write name="fieldList" property="document.string(DOMAIN_NAME)"
 filter="true"/>）
</td></tr>
<tr><td class="tableLeft">
查询条件：</td><td class="tableRight"><bean:write name="fieldList" property="queryView.cnWhere" filter="true"/>
</td></tr></table>

<br/>
<table width="100%">
<tr>
<td valign="top">
  <table border="0" cellpadding="3" cellspacing="1" class="tablebg">
    <tr>
      <td class="tableRight"> 选择行
      <td class="tableRight"> 层次</td>
      <% /*
        <input type="radio" name="tempStatistic.string(rowLength)" value="2">一级
        <input type="radio" name="tempStatistic.string(rowLength)" value="4">二级
        <input type="radio" name="tempStatistic.string(rowLength)" value="6">三级
         */ %>
</tr>
<%
while(iter.hasNext())
{
  FieldProperty field = (FieldProperty)iter.next();
  if(field.isCode()){
%>
<tr>
      <td class="tableRight">
        <input name="tempStatistic.string(rowName)" type="radio" value="<%=field.getFieldName()%>">
<%=field.getFieldCNName()%>
      </td>
      <td class="tableRight">
   <%
      // 得到该代码共有多少个级别
      int level = field.getLevelCount();
      // 如果级别不只一个
      if(level != -1) {
   %>
     <% //该代码在数据库中的表名 %>
     <input type="hidden" name="tempStatistic.string(<%=field.getFieldName()%>_row_name)" value="<%=field.getLevelName()%>">
     <select name="tempStatistic.string(<%=field.getFieldName()%>_row_level)" size="1">
   <%
      for(int i=1;i<=level && i<=12;i++){
   %>  <option value="<%=i%>"><%=levelCnName[i]%></option>
   <% } %>
     </select>
   <% } %>
</td>
</tr>
<%
  }
} %>
</table>
</td>
<td valign="top">
  <table border="0" cellpadding="3" cellspacing="1" class="tablebg">
<tr>
      <td class="tableRight"> 选择列</td>
      <td class="tableRight"> 层次</td>
</tr>
<%
//coll = MetaData.getFieldList(fieldList.getTableName());
iter = coll.iterator();
while(iter.hasNext())
{
  FieldProperty field = (FieldProperty)iter.next();
  if(field.isCode()){
%>
<tr>
      <td class="tableRight">
        <input name="tempStatistic.string(columnName)" type="radio" value="<%=field.getFieldName()%>">
<%=field.getFieldCNName()%>
      </td>
      <td class="tableRight">
   <% int level = field.getLevelCount();
      if(level != -1){
   %>
     <input type="hidden" name="tempStatistic.string(<%=field.getFieldName()%>_column_name)" value="<%=field.getLevelName()%>">
     <select name="tempStatistic.string(<%=field.getFieldName()%>_column_level)" size="1">
   <%
      for(int i=1;i<=level && i<=12;i++){
   %>  <option value="<%=i%>"><%=levelCnName[i]%></option>
   <% } %>
     </select>
   <% } %>
</td>
</tr>
<%
  }
}
%>
</table>
</td>
<td valign="top">
  <table border="0" cellpadding="3" cellspacing="1" class="tablebg">
<tr>
      <td class="tableRight"> 所要统计的列：
      <% /*
        <input name="tempStatistic.string(statisticType)" type="radio" value="SUM">SUM
        <input name="tempStatistic.string(statisticType)" type="radio" value="COUNT">COUNT
         */ %>
</td>

</tr>
<%
//coll = MetaData.getFieldList(fieldList.getTableName());
iter = coll.iterator();
while(iter.hasNext()){
  FieldProperty field = (FieldProperty)iter.next();
  // 统计类型（sum or count)
  String statisticType = null;
  if(field.isNumber())  {
     statisticType = "SUM";
  }else if(field.isString())  {
     statisticType = "COUNT";
  }
  if(statisticType != null){
%>
<tr>
      <td class="tableRight">
        <input name="tempStatistic.string(<%=field.getFieldName()%>_statisticType)" type="hidden" value="<%=statisticType%>">
        <input name="tempStatistic.string(statisticName)" type="radio" value="<%=field.getFieldName()%>">
<%=field.getFieldCNName()%>
</td>
</tr>
<% }
} %>
</table>
</td>
</tr>
</table>
  <table class="tablebgWW">
    <tr><td>
<html:hidden name="fieldList" property="option" />
<extend:hiddenObject name="fieldList" property="document"/>
<extend:hiddenObject name="fieldList" property="selectField" />
<extend:hiddenObject name="fieldList" property="queryView"/>
  <input name="b_sure" type="submit" value=" 确定 " class="button1">
  <INPUT TYPE="button" class="button-ui" class="button1" onClick="javascript:history.back();" value=" 返回 ">
</td></tr></table>
</form>
<%!
final static String[] levelCnName = {
"","一级","二级","三级","四级","五级","六级","七级","八级","九级","十级","十一级","十二级"};
%>
