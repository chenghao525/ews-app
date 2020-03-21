<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page
import="java.util.*"
import="org.apache.struts.util.RequestUtils"
import="org.apache.commons.beanutils.PropertyUtils"

import="com.corp.frame.core.dao.DataObject"
import="com.corp.frame.core.dao.DataManager"
import="com.corp.frame.core.code.Code"
import="com.corp.frame.core.code.CodeManager"
import="com.corp.frame.core.util.DateFormat"
import="com.corp.frame.core.base.UserInfo"
import="com.corp.frame.core.metadata.DatabaseObject"
import="com.corp.frame.core.metadata.FieldProperty"
import="com.corp.frame.core.action.PrefixClass"
import="com.corp.frame.core.util.Validate"
import="com.corp.frame.core.util.StringUtil"
import="com.corp.frame.bean.MetaData"
import="com.corp.frame.bean.CustomConstants"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-extend.tld" prefix="extend" %>
<%@ page errorPage="error.jsp" %>
<%request.setCharacterEncoding("UTF-8");%>
<jsp:useBean id="dataBean" scope="request" class="com.corp.frame.bean.DataEditForm" />
<%
dataBean.populate(request);
dataBean.execute();
if(dataBean.isEnd()){
  // 操作完成,转到别的页面
  request.getRequestDispatcher("resultList.jsp").forward(request,response);
}
%>
<jsp:useBean id="fieldListBean" class="com.corp.frame.bean.FieldList"
 scope="page" />
<%fieldListBean.populate(request);%>
<link href="css/stylesheet.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="css/js.js"></script>

<html>
<head>
<title>
数据维护
</title>
</head>

<body bgcolor="#ffffff">
<form method="post">
<table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
<tr><td class="content" colspan="4">
<% if(dataBean.isCreate()){ out.print("新增"); } else { out.print("修改"); }%>
<bean:write name="fieldListBean" property="document.string(DOMAIN_CNNAME)"
 filter="true"/>（<bean:write name="fieldListBean" property="document.string(DOMAIN_NAME)"
 filter="true"/>）
</td></tr>
<%
DataManager dm  = null;
try{
  UserInfo user = dataBean.getUser();
  dm = DataManager.getInstance();
  dm.setUser(user);
  dm.setCheckPermission(true);
  String beanName = "dataBean";
  String propertyPrefix = "data.string(";
  String propertySuffix = ")";
  int operatorType = dataBean.isCreate()?CustomConstants.OPERATOR_TYPE_INSERT:CustomConstants.OPERATOR_TYPE_UPDATE;
  boolean isShowDefaultValue = true;
  Collection fieldList = DatabaseObject.getDatabaseObject(dataBean.getTableName()).getFieldList(false,true,false);
%>
<%@include file="dataForm.jsp" %>
<%
 }
 finally{
  if(dm != null){
    dm.close();
  }
 }
%>
</table>
<br/>
<table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
<tr><td class="tableCenter">
       <html:hidden name="dataBean" property="tableName"/>
       <html:hidden name="dataBean" property="primaryKeyValue"/>
       <input type="hidden" value="" name="optionType">
       <input type="submit" value=" 保存 " class="button1" onClick="optionType.value='save';">
       <input type="submit" class="button1" onClick="action='resultList.jsp';" value=" 返回 ">
</td></tr></table>
<!-- 返回到查询页面 -->
<html:hidden name="fieldListBean" property="option" />
<extend:hiddenObject name="fieldListBean" property="queryView"/>
<extend:hiddenObject name="fieldListBean" property="statistic"/>
<extend:hiddenObject name="fieldListBean" property="document"/>
<extend:hiddenObject name="fieldListBean" property="selectField"/>

</form>
<a href="system/fieldEdit.jsp?tableName=<%=dataBean.getTableName()%>">--</a>
</body>
</html>
