<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://www.shanghaicorp.com/struts/extend" prefix="extend" %>

<head>
<title>巴枪日轨迹监控</title>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type="text/css" rel="STYLESHEET"> 
<script language="JavaScript" src="/js/js.js?r=1221"></script>
<script type="text/javascript" src="/js/sort.js"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"> 
<html:form action="/gps/gps">
<script type="text/javascript">
<!--

function DrawLine(){

	var x = '['+'<logic:iterate id="list" name="gpsForm" property="gpsList">[<bean:write name="list" property="string(LATITUDE)" />,<bean:write name="list" property="string(LONGITUDE)" />],</logic:iterate>';

	if(x=='['){
		x=null;
		alert("没有数据！");
		return false;
	}else{
		x=x.substring(0,x.length-1)+']';
	}
	
	map.draw_line(x);
	window.focus();
}

//-->
</script>
</html:form>
<iframe id="map" name="map" src="mapMonitor.jsp" onload="DrawLine();" width="100%" height=100% frameBorder="0" scrolling="no"></iframe>
</body>
</html>
