<%@ page contentType="text/html; charset=UTF-8" import="org.apache.commons.beanutils.PropertyUtils,org.apache.struts.util.RequestUtils,com.corp.frame.eflow.*" import="org.apache.struts.util.RequestUtils" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<html:html>
<HEAD><title></title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type=text/css rel=stylesheet>
</HEAD>

<%
	String info=(String)RequestUtils.lookup(pageContext, "orgForm", "transInfo", null);

	if(info==null || info=="")
	{//若没有任何信息，执行了取消操作
%>
		<SCRIPT LANGUAGE="JavaScript">
		<!--
			history.go(-2);
		//-->
		</SCRIPT>
<%
	}
	else
	{
%>
		<SCRIPT LANGUAGE="JavaScript">
		<!--
			parent.org_left.location.href="/system/org.do?b_queryorg=true&transInfo=<%=info%>";
		//-->
		</SCRIPT>
<%
	}
%>

<body align="center" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
</body>
</html:html>