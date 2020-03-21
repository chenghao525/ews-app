<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://www.shanghaicorp.com/struts/extend" prefix="extend" %>

<%
	String t=request.getParameter("type");
	String s=request.getParameter("search");
	String g=request.getParameter("gps");
	if(s!=null)	s = new String(s.getBytes("ISO-8859-1"),"UTF-8"); 
%>

<html>
<HEAD><title>终端监控</title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type=text/css rel=stylesheet>
</HEAD>
<script type="text/javascript" src="/js/js.js?r=1221"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>

<body align="left" leftmargin="1" topmargin="1" marginwidth="0" marginheight="0">
	<div class="mini-splitter" style="width:100%;height:100%;border:0px;">
		<div size="400px" showCollapseButton="true" style="border:0px;">
			<iframe id="left" name="left" frameborder="0" style="width:100%;height:100%;" border="0" scrolling="auto"></iframe>
		</div>
		<div showCollapseButton="false">
			<iframe id="map" src="mapMonitor.jsp?type=<%=t%>&search=<%=s%>&gps=<%=g%>" frameborder="0" name="map" style="width:100%;height:100%;" border="0" scrolling="auto"></iframe>
		</div>        
	</div>
</body>
</html>
<script type="text/javascript">
<!--
function setAddr(){
	$("#left").attr("src","/gps/gps.do?b_monitor=true");
}
//-->
</script>