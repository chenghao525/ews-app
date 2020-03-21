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

<jsp:useBean id="domain" scope="page" class="com.corp.frame.datawindow.QueryList" />
<%request.setCharacterEncoding("UTF-8");%>
<%
if(request.getParameter("b_query") != null){
  domain.populate(request);
  domain.execute();
}
%>

<DIV  class=menu style="BACKGROUND: #efefef;">
系统查询
</DIV>
<form name="batAdd" method="post">
  <table border="0" cellpadding="3" cellspacing="1" width=100%>
    <tr>
      <td>标题</td>
      <td>
        <html:text name="domain" property="queryView.queryObject(TITLE).value" />
        <input type="hidden" name="queryView.queryObject(TITLE).field" value="TITLE">
        <input type="hidden" name="queryView.queryObject(TITLE).operator" value="LIKE">
      </td>
    </tr>
    <tr>
      <td>语句</td>
      <td>
        <html:text name="domain" property="queryView.queryObject(SQL).value" />
        <input type="hidden" name="queryView.queryObject(SQL).field" value="SQL">
        <input type="hidden" name="queryView.queryObject(SQL).operator" value="LIKE">
     </td>
    </tr>
    <tr>

      <td>日期</td>
      <td>
        <html:text name="domain" property="queryView.queryObject(CREATE_DATE).value" />
        <input type="hidden" name="queryView.queryObject(CREATE_DATE).field" value="CREATE_DATE">
        <input type="hidden" name="queryView.queryObject(CREATE_DATE).operator" value=">=">
        至 <html:text name="domain" property="queryView.queryObject(CREATE_DATE_Z).value" />
        <input type="hidden" name="queryView.queryObject(CREATE_DATE_Z).field" value="CREATE_DATE">
        <input type="hidden" name="queryView.queryObject(CREATE_DATE_Z).operator" value="<=">
      </td>
    </tr>
	<tr ><td colspan="2">
        <html:hidden name="domain" property="queryView.select" value="select * from XT_STATEMENT_DEFINE"/>
        <input type="submit" name="b_query" value=" 查询 "  class="button1">
        <INPUT TYPE="button" class="button-ui" name="b_new" value=" 新增 " onClick="redirect('queryDefine.jsp');" class="button1">
	</td></tr>
  </table>
 <HR align=left width="85%" noShade SIZE=1>
  <table border="0" cellpadding="3" cellspacing="1" width=100%>
    <tr>
      <td>编号</td>
      <td>标题</td>
      <td>SQL</td>
      <td>是否查询</td>
      <td>用户编号</td>
      <td>日期</td>
      <td>操作</td>
    </tr>
	<logic:iterate name="domain" property="resultList" id="element">
    <tr >
      <td><bean:write name="element" property="string(ID)"/></td>
      <td><bean:write name="element" property="string(TITLE)"/></td>
      <td><bean:write name="element" property="string(SQL)"/></td>
      <td><bean:write name="element" property="string(TYPE)"/></td>
      <td><bean:write name="element" property="string(USER_ID)"/></td>
      <td><bean:write name="element" property="string(CREATE_DATE)"/></td>
      <td><a style="cursor:hand;"
onClick="redirect('queryDefine.jsp?b_execute=true&findById=true&id=<bean:write name="element" property="string(ID)"/>');">执行</a></td>
    </tr>
	</logic:iterate>
  </table>
<extend:paginationLink name="domain" property="pagination" buttonClass="button1"/>
<%--
<INPUT TYPE="button" class="button-ui" value=" 返回 " class="button1" onClick="redirect('index.jsp');">
--%>
<br>
</form>
