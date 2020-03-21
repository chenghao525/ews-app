<html>
<head>
<title>
testRequest
</title>
</head>
<body bgcolor="#ffffff">
<%
java.util.Enumeration enum2 = request.getParameterNames();
while(enum2.hasMoreElements()){
  String name= (String)enum2.nextElement();
  String value = request.getParameter(name);
  out.println(name+"|" + value + "<br>");
}
%>
<form method="post">
<br><br>
<% int i=0;
while(i<100000) {
i++; %>
<input type="hidden" name="testrequest<%=i%>" value="slkfjlk<%=i%>sldk">
<% } %>
<input type="submit" name="Submit" value="Submit">
<input type="reset" value="Reset">
</form>
</body>
</html>
