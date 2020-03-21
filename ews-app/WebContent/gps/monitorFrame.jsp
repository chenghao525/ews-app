<%@ page contentType="text/html; charset=UTF-8" import="java.net.URLEncoder"%>
<html>
<head>
<title>终端监控</title>
<%
	String t=request.getParameter("type");
	String s=request.getParameter("search");
	String g=request.getParameter("gps");
	if(s!=null)
		s = new String(s.getBytes("ISO-8859-1"),"UTF-8");  
	
%>
</head>

	<FRAMESET name="content" cols="370,*" border="0" frameborder="1" FRAMESPACING="5"  TOPMARGIN="0"  LEFTMARGIN="0" MARGINHEIGHT="0" MARGINWIDTH="0" >
		<FRAME name="gps" id="gps" src="" TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0" MARGINWIDTH="0" FRAMEBORDER="0" BORDER="0"></FRAME>
		<FRAME name="map" id="map" src="mapMonitor.jsp?type=<%=t%>&search=<%=s%>&gps=<%=g%>" FRAMEBORDER="no" bordercolor="#7FCBFF" ></FRAME>
	</FRAMESET>

	<body>
		<p>此网页使用了框架，但您的浏览器不支持框架。</p>
	</body>
	</noframes>

</frameset>
</html>
