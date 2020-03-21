<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page
import="java.util.*"
import="com.corp.frame.core.dao.DataObject"
import="com.corp.frame.core.dao.DataManager"
import="com.corp.frame.bean.FieldList"
import="com.corp.frame.bean.MetaData"%>

<%@ page errorPage="error.jsp" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-extend.tld" prefix="extend" %>
<%request.setCharacterEncoding("UTF-8");%>
<link href="css/stylesheet.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="css/js.js"></script>

<jsp:useBean id="fieldListBean" class="com.corp.frame.bean.FieldList"
 scope="page" />
<%
 fieldListBean.populate(request);
   String domain_name = fieldListBean.getDocument().getString("DOMAIN_NAME");
   String optionType = fieldListBean.getDocument().getString(FieldList.OPTION_TYPE);
  DataObject document = MetaData.getDocument(domain_name,optionType);
  document.setString(FieldList.OPTION_TYPE,optionType);
  fieldListBean.setDocument(document);
 String formAction = null;
 if("query".equals(fieldListBean.getOption())) {
   formAction = "fieldList.jsp";
 } else if("reQuery".equals(fieldListBean.getOption())){
   fieldListBean.setOption("query");
   formAction = "resultList.jsp";
 } else if("statistic".equals(fieldListBean.getOption())){
   formAction = "statisticList.jsp";
 } else if("reStatistic".equals(fieldListBean.getOption())){
   fieldListBean.setOption("statistic");
   formAction = "resultList2.jsp";
 }
 %>
<table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
    <tr><td class="content">设置查询条件</td></tr>
</table>
<br/>
<table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
<tr class="tableRight">
<td>
查询文书：<bean:write name="fieldListBean" property="document.string(DOMAIN_CNNAME)"
 filter="true"/>（<bean:write name="fieldListBean" property="document.string(DOMAIN_NAME)"
 filter="true"/>）
<!--
<br>
查询条件：<bean:write name="fieldListBean" property="queryView.cnWhere" filter="true"/>
-->
</td></tr></table>
<form action="<%=formAction%>" method="post">
  <table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
<%
String beanName = "fieldListBean";
String property="queryView";
String domainName = fieldListBean.getTableName();
%>
<%@include file="/frame/dataQueryCondition.jsp" %>
  </table>
  <table class="tablebgWW">
    <tr><td>
<html:hidden name="fieldListBean" property="option" />
<extend:hiddenObject name="fieldListBean" property="document" />
<extend:hiddenObject name="fieldListBean" property="selectField" />
<extend:hiddenObject name="fieldListBean" property="statistic"/>

  <input name="b_sure" type="submit" value=" 确定 " class="button1">
  <INPUT TYPE="button" class="button-ui" class="button1" onClick="javascript:history.back();" value=" 返回 ">
</td></tr></table>
</form>
