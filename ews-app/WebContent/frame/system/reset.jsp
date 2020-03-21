<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新系统中的一些缓存</title>
</head>
<%
/*
import com.corp.frame.core.code.CodeDescription;
import com.corp.frame.core.metadata.DatabaseObject;
import com.corp.frame.eflow.Parse;
import com.corp.frame.action.xml.ParseWorkflow;
import com.corp.frame.core.code.SystemCode;

*/
String id = request.getParameter("id");
if(id != null){
  com.corp.frame.core.runtime.CacheManager.reset(id);
  out.print("OK." +id);
}


%>
<body>
<table width="100%" border="0">
  <tr>
    <td width="13%">&nbsp;</td>
    <td width="87%"><a href="reset.jsp?id=com.corp.frame.core.code.CodeDescription">com.corp.frame.core.code.CodeDescription</a></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><a href="reset.jsp?id=com.corp.frame.core.metadata.DatabaseObject">com.corp.frame.core.metadata.DatabaseObject</a></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><a href="reset.jsp?id=com.corp.frame.eflow.Parse">com.corp.frame.eflow.Parse</a></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><a href="reset.jsp?id=com.corp.frame.action.xml.ParseWorkflow">com.corp.frame.action.xml.ParseWorkflow</a></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><a href="reset.jsp?id=com.corp.frame.core.code.CodeManager">com.corp.frame.core.code.CodeManager</a></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><a href="reset.jsp?id=com.corp.frame.core.base.SystemProperty">com.corp.frame.core.base.SystemProperty</a></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>

</body>
</html>
