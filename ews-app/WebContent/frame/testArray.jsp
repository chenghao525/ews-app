<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="bean1" scope="request" class="com.corp.frame.test.TestArray"/>
<%
bean1.populate(request);
%>
<html>
<head>
<title>
testArray
</title>
</head>
<body bgcolor="#ffffff">
<h1>

1<%=bean1.getTestArr()%>
JBuilder Generated JSP
<form>
<input type=text name="testArr[0].string(aaa)" value="">
<input type=text name="testArr[0].string(bbb)" value="">
<input type=text name="testArr[0].string(ccc)" value="">
1<%=bean1.getData("saa")%>
<input type=text name="data(saa)[0].string(aaa)" value="">
<input type=text name="data(saa)[0].string(bbb)" value="">
<input type=text name="data(saa)[0].string(ccc)" value="">
1<%=bean1.getData("saa")%>
<input type=text name="data(eee).data(0).string(aaa)" value="">
<input type=text name="data(eee).data(0).string(bbb)" value="">
<input type=text name="data(eee).data(0).string(ccc)" value="">

<input type=submit name=submit>
</form>
</h1>
</body>
</html>
