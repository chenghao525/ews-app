<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%
	Exception exception = (Exception)request.getAttribute("exception");
	String msg="";
	if(exception==null){
%>
<script type="text/javascript">
<!--
	// alert("您的访问出现错误！");
	//top.location.href="/";
	location = location.protocol + "//" + location.host + "/";
//-->
</script>
<%
	}else{
		String t = (String)request.getAttribute("timeout");
		if("true".equals(t)){
			msg="您的会话已过期，请重新登录";
%>
<script type="text/javascript">
<!--
	alert("<%=msg%>");
	top.location.href="/";
//-->
</script>
<%
		}else{
			msg = exception.getMessage();
			msg = com.corp.frame.core.util.StringUtil.replaceString(msg,"\n","\\n");
%>
<html>
<HEAD><TITLE></TITLE>
<LINK href="/images/css.css" type=text/css rel=STYLESHEET>
</HEAD>
<BODY leftMargin=0 background="/images/main_bg.gif" topMargin=0>
<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
<TR><TD valign=middle align=center>
	<TABLE cellSpacing=0 cellPadding=0 border=0 align=center>
	<TR><TD background="/images/error.gif" WIDTH="501" HEIGHT="277" BORDER=0>
		<TABLE cellSpacing=0 cellPadding=0 border=0 width=500 height="90%">
			<tr height=160>
				<td width=140></td>
				<td width=340>&nbsp;</td>
			</tr>
			<tr height=80 valign="middle">
				<td></td>
				<td><FONT SIZE="3pt" COLOR="red"><%=msg%></FONT></td>
			</tr>
			<tr height=20>
				<td></td>
				<td align="right">
					<input type="submit" onClick="javascript:window.close();history.back();return false;" value="返 回">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	</TD></TR>
	</TABLE>
</TD></TR>
</TABLE>

</BODY>
</HTML>
<%
		}
	}
%>
