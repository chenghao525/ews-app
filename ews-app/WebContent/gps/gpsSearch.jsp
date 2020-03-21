<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://www.shanghaicorp.com/struts/extend" prefix="extend" %>

<head>
<title></title>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type="text/css" rel="STYLESHEET"> 
<script language="JavaScript" src="/js/js.js?r=1221"></script>
<script type="text/javascript" src="/js/sort.js"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"> 
<html:form action="/gps/gps">

<table width="100%"  border="0" cellpadding="0" cellspacing="0" align=center>
	<tr><td background="../images/mainleft_3.jpg" WIDTH="100%" BORDER=0>
		<TABLE cellSpacing="2" cellPadding="2" border="0" width="100%">
			<tr><td>
			<%
				String    property  = "queryView";
				String    domainName ="T_GPS_SOURCE";
			%>
			<%@include file="/frame/dataQuery.jsp" %>
			</td></tr>
		</table>
	</td></tr>
	<tr><td valign="absmiddle">
		<font color="red">GPS模块保存最近半年的数据</font>
	</td></tr>
	<tr><td valign="top">
		<TABLE cellSpacing="0" cellPadding="0" border="0" align="left" width="100%">
		<tr><td valign="top" width="100%">		
			<extend:dataPrint paginationName="pagination" property="gpsList" domainName="T_GPS_SOURCE"/>
			<extend:paginationLink  property="pagination" buttonName="b_query"/>
		</td></tr>
		</table>
	</td></tr>
	<% /** %>
	<tr><td valign="top">
		<b>智能终端设备清单：</b><br>
		<bean:write name="gpsForm" property="mobileList" filter="false"/>
	</td></tr>
	<% */ %>
</table>
</html:form>
</body>
</html>
<script type="text/javascript">
<!--
function gpsMonitor(LATITUDE,LONGITUDE){
	window.open('gpsPointMonitor.jsp?LATITUDE='+LATITUDE+'&LONGITUDE='+LONGITUDE,'gpsPoint','width=1000px,height=600px,menubar=0,toolbar=0,directories=0,location=0,status=0,scrollbars=0');	
	return false;
}

function gpsTrack(SID){
	window.open('/gps/gps.do?b_daytrack=true&sid='+SID,'gpsDayTrack','width=1000px,height=600px,menubar=0,toolbar=0,directories=0,location=0,status=0,scrollbars=yes');	
	return false;
}
function empInfo(id){
	var w=window.open("/expressemp/manage.do?b_expresspreview=true&pret=da&empId="+id,"empp","width=350px,height=400px,menubar=0,toolbar=0,directories=0,location=0,status=0,scrollbars=1");
	w.focus();
}
//-->
</script>







