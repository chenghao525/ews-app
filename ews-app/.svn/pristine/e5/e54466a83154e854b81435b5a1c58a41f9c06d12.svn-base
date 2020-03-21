<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page
import="java.util.*"
import="com.corp.frame.core.metadata.FieldProperty"
import="com.corp.frame.core.action.PrefixClass"
import="com.corp.frame.core.base.UserInfo"
import="com.corp.frame.core.dao.code.Code"
import="com.corp.frame.core.dao.DataObject"
import="com.corp.frame.core.dao.DataManager"
import="com.corp.frame.core.dao.code.CodeManager"
import="com.corp.frame.core.util.Validate"
import="com.corp.frame.core.util.StringUtil"
import="com.corp.frame.core.util.DateValue"
import="com.corp.frame.worksheet.Sheet"
import="com.corp.frame.worksheet.SheetView"
import="com.corp.frame.worksheet.SheetObject"
import="com.corp.frame.bean.MetaData"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-extend.tld" prefix="extend" %>
<%@ page errorPage="error.jsp" %>
<%request.setCharacterEncoding("UTF-8");%>
<jsp:useBean id="dataBean" scope="request" class="com.corp.frame.worksheet.FlowForm" />
<%
dataBean.populate(request);
dataBean.init();
dataBean.execute();
%>
<link href="css/stylesheet.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="css/js.js"></script>

<html>
<head>
<title>
<%=dataBean.getFlow().getFlowName()%>
</title>
</head>

<body bgcolor="#ffffff">
<%
Sheet sheet= dataBean.getCurrentSheet();
SheetView sheetView = sheet.getView();
DataManager dm  = null;
try{
  UserInfo user = dataBean.getUser();
  dm = DataManager.getInstance();
  dm.setUser(user);
  dm.setCheckPermission(true);
  boolean isCreate = true;
  String beanName = "dataBean";
  Iterator objectList = sheet.getObjectList().iterator();
%>
<!-- 抬头菜单 开始 　-->
<%
String tableCss = "tablebgW";
//if(sheet.hasTitleMenu()){
if(false){
%>
<table border="0" cellpadding="0" cellspacing="0">
<tr align="center" >
<%
    // title
    boolean first = false;
    Collection title = dataBean.getFlow().getSheet();
    Iterator iterTitle = title.iterator();
    while(iterTitle.hasNext()){
      Sheet tempSh = (Sheet)iterTitle.next();
      if(first){
%>   <td width="2"><hr width="2" size="12" noshade color="#286222"></td>
<%    }
      else{
        first = true;
      }
      if(sheet.getSheetId().equals(tempSh.getSheetId())) {
    %>
    <td width="100" bgcolor="#489840" style="border: 1 solid #006600"><nobr>
     <font color="#ffffff"><%=tempSh.getName()%></font></nobr></td>
    <% } else {   %>
    <td width="100" bgcolor="#e8f8c8" style="border: 1 solid #006600"><nobr>
     <a onClick="moveToSheet('<%=tempSh.getSheetId()%>');" style="cursor:hand;">
        <%=tempSh.getName()%></a></nobr></td>
    <% } }%>
</tr>
</table>
<% }
   if(sheetView.isTableAlignCenter()){
     tableCss = "tablebg";out.print("</br></br></br></br></br></br>");
   } %>
<!-- 抬头菜单 结束 　-->
<form method="post" name="flowPage" >
<table border="0" cellpadding="3" cellspacing="1" class="<%=tableCss%>" align="center">
<tr>
<td class="content" colspan="4">
<%=sheet.getName()%>
</td></tr>
<%
  while(objectList.hasNext()){
    SheetObject sheetObject = (SheetObject)objectList.next();
    String tableName = sheetObject.getDatabaseObject().getTableName();
    /** 如果是从表,则取其 domain name. */
    tableName = sheetObject.getObjectId();
    //out.print(tableName);
    pageContext.setAttribute("fields",sheetObject.getDatabaseObject().getFieldList(false,true,false));
    ;
    if("1".equals(sheetObject.getType())){
      // 如果是简单列表
      boolean isShowDefaultValue = isCreate;
      String PREFIX = "data("+tableName+")";
      DataObject data = dataBean.getData(tableName);
%>
<div id="<%=sheetObject.getObjectId()%>"></div>
<%@include file="dataForm.jsp" %>

<%
     dataBean.remove(tableName);
   }
   else if("2".equals(sheetObject.getType())){
      boolean isShowDefaultValue = true;
      String PREFIX = "tempData("+tableName+")";
      DataObject data = dataBean.getTempData(tableName);
%>
<tr><td class="tableCenter" colspan="4">
<div id="<%=sheetObject.getObjectId()%>">
</div>
<table border="0" cellpadding="3" cellspacing="1" class="<%=tableCss%>">
     <%@include file="dataForm.jsp" %>
<%
      int subBeanIndex = -1;
      String submitName = null;
      if(tableName.equals(dataBean.getSubBeanName()) &&
        "preEditSubBean".equals(dataBean.getOption())){
         subBeanIndex = dataBean.getSubBeanIndex();
         submitName = " 保存 ";
      }
      else{
         submitName = " 增加 ";
      }
%>
<td class="tableCenter" colspan="4">
<% //-- 暂存当前编辑的从表的索引 %>
<input type="hidden" name="subBeanIndex_<%=tableName%>" value="<%=subBeanIndex%>">
       <INPUT TYPE="button" class="button-ui" value="<%=submitName%>" class="button1"
 onClick="option.value='addSubBean';subBeanName.value='<%=tableName%>';subBeanIndex.value=subBeanIndex_<%=tableName%>.value;submit();">
<% //-- 如果点击清空,则清空索引,恢复新增状态 %>
       <INPUT TYPE="button" class="button-ui" value=" 清空 " class="button1"
 onClick="clearAll('<%=PREFIX%>');subBeanIndex_<%=tableName%>.value='-1';">
</td></tr>
</table><br/>

<%
  /** 但列表是用表名作主键存储的 */
  String tableName0 = sheetObject.getDatabaseObject().getTableName();
  ArrayList dataList = dataBean.getDataList(tableName);
%>
<%@include file="dataList.jsp" %></td></tr>
<%
   }
   // 快速编辑的链接
%>
<% if(false){ %>
<tr><td class="tableCenter" colspan="4">
<a href="system/fieldEdit.jsp?tableName=<%=sheetObject.getObjectId()%>"><font color="FFFFFF">edit</font></a>
</td></tr>
<% } %>
<%
  }// end while
%>
<tr><td  class="tableCenter" colspan="4">
<table border="0" cellpadding="3" cellspacing="1">
<tr class="tableCenter">
<td></td>
<% if(sheetView.hasConfirmButton()) { %>
<td width="20%">
       <INPUT TYPE="button" class="button-ui" value=" 确定 " class="button1" onClick="submitForm('nextFlow');">
</td>
<% } %>
<%if(sheetView.hasSaveButton()) { %>
<td width="20%">
       <INPUT TYPE="button" class="button-ui" value=" 保存 " class="button1" onClick="submitForm('save');">
</td>
<% } %>
<% if(dataBean.hasPrevious() || dataBean.hasNext()){ %>
<td width="20%" nowrap>
       <INPUT TYPE="button" class="button-ui" value="上一步" class="button1" onClick="submitForm('previousSheet');" <% if(!dataBean.hasPrevious()){ out.print("disabled"); } %>>
       <INPUT TYPE="button" class="button-ui" value="下一步" class="button1" onClick="submitForm('nextSheet');" <% if(!dataBean.hasNext()){ out.print("disabled"); } %>>
</td>
<% } %>
<% if(sheetView.hasClearButton()) { %>
<td width="20%">
       <INPUT TYPE="button" class="button-ui" value=" 清空 " class="button1" onClick="clearAll();">
</td>
<% } %>
<td></td>
<% if(sheetView.hasBackButton()) {%>
<td width="20%" align="right">
<!-- 返回到查询页面 -->
       <INPUT TYPE="button" class="button-ui" class="button1" onClick="submitForm('previousFlow');" value=" 返回 ">
</td>
<% } %>
</tr></table>
</td></tr>
</table>

<%
 }
 finally{
  if(dm != null){
    dm.close();
  }
 }
%>
<extend:hiddenObject name="dataBean" ignoreName="true"/>
</form>
</body>
</html>
<script language="JavaScript">
<!--
function subSubmit(index,subBeanName,type){
  /** 子表名称 */
  flowPage.subBeanName.value=subBeanName;
  /** 所要操作的子项 */
  flowPage.subBeanIndex.value=index;

  /** 操作类型 */
  submitForm(type);
}
function submitForm(type){
  /** 操作类型 */
  //alert('pre-'+flowPage.option.value);
  flowPage.option.value=type;
  //alert('next-'+flowPage.option.value);
  flowPage.submit();
}
function moveToSheet(sheetId){
  flowPage.moveToSheetId.value = sheetId;
  submitForm('moveToSheet');
}

location="#<%=dataBean.getSubBeanName()%>";
// -->
</script>
