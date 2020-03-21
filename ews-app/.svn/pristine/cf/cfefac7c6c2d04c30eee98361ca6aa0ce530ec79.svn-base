<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<head>
<title>终端定位</title>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type="text/css" rel="STYLESHEET"> 
<script language="JavaScript" src="/js/js.js?r=1221"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"> 

<%
	String LATITUDE=request.getParameter("LONGITUDE");
	String LONGITUDE=request.getParameter("LATITUDE");
%>

<script type="text/javascript">
<!--

function searchAllMobile(){
	var x = '[{latitude:<%=LATITUDE%>,longitude:<%=LONGITUDE%>,msg:"当前点所在位置"}]';
	map.init_markers(x);
	window.focus();
}
//-->
</script>
<iframe id="map" name="map" src="mapMonitor.jsp" onload="searchAllMobile();" width="100%" height=100% frameBorder="0" scrolling="no"></iframe>
</body>
</html>
