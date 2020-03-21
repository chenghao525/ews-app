<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://www.shanghaicorp.com/struts/extend" prefix="extend" %>
<html:html>
<head>
<title></title>
<LINK href="/images/css.css" type="text/css" rel="STYLESHEET"> 
<script language="JavaScript" src="/js/js.js?r=1221"></script>
<script type="text/javascript" src="/js/sort.js"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="/gps/mobile">

<table width="100%"  border="0" cellpadding="0" cellspacing="0" align=center>
	<tr class="nprint"><td>
		<div class="mini-toolbar" style="padding:2px;">
			<span class="separator"></span>
			<a class="mini-button" iconCls="icon-add" plain="true" href="/gps/mobile.do?b__mobileadd=true&current=<bean:write name="gpsForm" property="current" />">批量增加</a>
			<span class="separator"></span>
			<a class="mini-button" iconCls="icon-refresh" plain="true" onclick="window.location.reload();">刷 新</a>
		</div>
	</td></tr>
	<tr><td background="../images/mainleft_3.jpg" WIDTH="100%" BORDER=0>
		<TABLE cellSpacing="2" cellPadding="2" border="0" width="100%">
			<tr><td>
				<%
					String    property  = "queryView";
					String    domainName ="T_INFO_MOBILE";
				%>
				<%@include file="/frame/dataQuery.jsp" %>
			</td></tr>
		</table>
	</td></tr>	
	<tr><td valign="top">
		<TABLE cellSpacing="0" cellPadding="0" border="0" align="left" width="100%">
			<tr><td valign="top" width="100%">		
				<extend:dataPrint paginationName="pagination" property="mpList" domainName="T_INFO_MOBILE"  />
				<extend:paginationLink  property="pagination" buttonName="b_query"/>
			</td></tr>
		</table>
	</td></tr>
</table>
</html:form>
</body>
</html:html>

<SCRIPT LANGUAGE="JavaScript">
<!--
	
function DelConform(){
	return confirm("<bean:message key="alert.delMessage" />");
}
//-->
</SCRIPT>






