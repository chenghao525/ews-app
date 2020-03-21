<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-extend.tld" prefix="extend" %>
<%
String msg = exception.getMessage();
msg = com.corp.frame.core.util.StringUtil.replaceString(msg,"\n","\\n");
//		out.println("<script language=\"javascript\">");
//		out.println("alert('"+msg+"');");
//		out.println("history.back();");
//		out.println("</script>");
%>
<html>
<body bgcolor="#ffffff">

<h1>出错了！</h1>

<br>错误信息:
 <%= exception.getMessage() %><br>
<INPUT TYPE="button" class="button-ui" class="button1" onClick="javascript:history.back();" value=" 返回 ">
<br/>
详细信息 :
<pre><font color="red"><%
 java.io.CharArrayWriter cw = new java.io.CharArrayWriter();
 java.io.PrintWriter pw = new java.io.PrintWriter(cw,true);
 exception.printStackTrace(pw);
 out.println(cw.toString());
 %></font></pre>
<br></body>
</html>
