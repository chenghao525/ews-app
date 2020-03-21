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

<%request.setCharacterEncoding("UTF-8");%>
<link href="../css/stylesheet.css" rel="stylesheet" type="text/css">
<!-------------------------------------------------------------------------->
<table cellpadding="3" cellspacing="1" class="tablebgW">
<%
String strType = request.getParameter("type");
int type = 0;
if(strType == null){
  // fieldrule 中存在的表
  type = 0;
}else{
  type = Integer.parseInt(strType);
  if(type < 0 || type > 4){
    type = 0;
  }
}
String prefix = request.getParameter("prefix");
if(prefix == null){
  prefix = "%";
}else{
  prefix +="%";
}
String[][] link = new String[][]{
  {"<a href=\"domainSelect.jsp?type=0\">数据对象</a><a href=\"domainEdit.jsp\">新增</a>","数据对象<a href=\"domainEdit.jsp\">新增</a>"},
  {"<a href=\"domainSelect.jsp?type=1\">数据字典</a>","数据字典"},
  {"<a href=\"domainSelect.jsp?type=2\">数据库</a>","数据库"},
  {"<a href=\"domainSelect.jsp?type=3\">数据字典中存在但数据库中不存在</a>","数据字典中存在但数据库中不存在"},
  {"<a href=\"domainSelect.jsp?type=4\">数据库中存在但数据字典中不存在</a>","数据库中存在但数据字典中不存在"},
  {"<a href=\"domainList.jsp\">维护</a>","维护"}
};
link[type][0] = link[type][1];
java.util.Collection coll =MetaData.getTableList(type,prefix);
//<br>
request.setAttribute("tempIterate",coll);
%>
<tr>
<% for(int i=0;i<link.length;i++){ %>
<td class="content"><%=link[i][0]%></td>
<% } %>
</tr>
<tr><td class="tableRight" colspan="<%=link.length%>">
索引:<a href="domainSelect.jsp?type=<%=type%>">全部</a>
<% for(int i =(int)'A';i<=(int)'Z';i++){ %>
&nbsp;<a href="domainSelect.jsp?prefix=<%=(char)i%>&type=<%=type%>"><%=(char)i%></a>
<%} %>
</td></tr>
<tr><td class="tableRight" colspan="<%=link.length%>">
</td>
</tr>
<tr><td class="tableRight" colspan="<%=link.length%>">
&nbsp;共 <%=coll.size()%> 个
</td></tr>
</table>
<br>
<table cellpadding="5" cellspacing="1" class="tablebgW">
  <extend:iterateGroup id="element" name="tempIterate" scope="request" column="2">
  <td class="tableRight">
  <a href="maintenance.jsp?domain_name=<bean:write name="element" property="string(DOMAIN_NAME)"/>">
  <bean:write name="element" property="string(DOMAIN_CNNAME)"/></a>
 <a href="fieldEdit.jsp?tableName=<bean:write name="element" property="string(DOMAIN_NAME)"/>">
      (<bean:write name="element" property="string(DOMAIN_NAME)"/>)</a></td>
</extend:iterateGroup>
</table>
