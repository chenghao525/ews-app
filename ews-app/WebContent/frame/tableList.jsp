<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page
import="com.corp.frame.core.dao.DataObject"
import="com.corp.frame.bean.MetaData"
%>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="/WEB-INF/struts-extend.tld" prefix="extend" %>
<%request.setCharacterEncoding("UTF-8");%>
<link href="css/stylesheet.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="css/js.js"></script>
<jsp:useBean id="queryAction" class="com.corp.frame.core.action.QueryAction"
 scope="page" />

<%
queryAction.populate(request);
queryAction.execute();
java.util.Iterator iter;
iter = queryAction.getResultList().iterator();
%>

<table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
<tr class="content">
<td>通用查询统计
</td></tr>
</table>
<br/>
<table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
<tr class="tableTitle">
<td>编号</td>
<td>名称</td>
<td>操作</td>
</tr>
<%
while(iter.hasNext())
{
  DataObject dataObject = (DataObject)iter.next();
%>
<tr class="tableCenter">
    <td><%=dataObject.getString("DOMAIN_NAME")%></td>
    <td> <%=dataObject.getString("DOMAIN_CNNAME")%></td>
    <td>
    <% if(dataObject.getString("TABLE_NAME") != null){ %>
      <a href="queryList.jsp?option=query&document.string(DOMAIN_NAME)=<%=dataObject.getString("DOMAIN_NAME")%>">
      查询</a>
      <a href="queryList.jsp?option=statistic&document.string(DOMAIN_NAME)=<%=dataObject.getString("DOMAIN_NAME")%>">
      统计</a>
    <% } %>
</td>
</tr>
<% } %>
</table>
<form method="post" name="test">
<extend:paginationLink name="queryAction" property="pagination" buttonClass="button1"/>
<extend:hiddenObject name="queryAction" property="queryView"/>

  <table class="tablebgWW">
    <tr><td>
       <input type=submit value="返回首页" onClick="action='documentList.jsp';submit();" class="button1">
</td></tr></table>
</form>
