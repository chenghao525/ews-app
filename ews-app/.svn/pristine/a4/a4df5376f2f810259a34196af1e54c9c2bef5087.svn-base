<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page
import="com.corp.frame.navigate.*"
import="com.corp.frame.bean.MetaData"
%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-extend.tld" prefix="extend" %>

<A name=Top></A>
<table width="100%">
  <tr>
    <td><a href="/">AdWords 主页</a></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><a href="#"><b>AdWords 支持</b></a></td>
  </tr>
  <%
    Menu.reset();
    Navigator nv = Menu.getRootNavigator();
    if(nv != null){
      request.setAttribute("children",nv.getChildren());
    }
    else{
      request.setAttribute("children",new java.util.ArrayList());
    }
  %>
  <logic:iterate name="children" id="children1" scope="request">
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><b><font color="#004f27"><bean:write name="children1" property="name"/></font></b></td>
  </tr>
  <logic:iterate name="children1" property="children" id="children2">
  <tr>
    <td><a href="<bean:write name="children2" property="url"/>"><b><bean:write name="children2" property="name"/></b></a></td>
  </tr>
  </logic:iterate>
  </logic:iterate>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><b><font color="#004f27">数据查询平台</font></b></td>
  </tr>
  <tr>
    <td><a href="/frame/tiles/dataWindow/queryDefine.jsp"><b>执行查询</b></a></td>
  </tr>
  <tr>
    <td><b><a
      href="/frame/tiles/dataWindow/queryList.jsp">历史记录</a></b></td>
  </tr>
</table>
