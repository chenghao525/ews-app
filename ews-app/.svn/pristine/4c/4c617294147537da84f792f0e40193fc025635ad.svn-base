<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title></title>
<%
	//区别人员还是部门的编号
	String dept_emp=request.getParameter("dept_emp");
	
	//区别是否有确定按钮
	String button_flag=request.getParameter("button_flag");
	
	//区别复选框
	String ckbDisp=request.getParameter("ckbDisp");
%>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" height="100%">
<table width="100%" height="100%" valign="top" cellpadding="0" cellspacing="0" >
<%
	if(dept_emp!=null && !"".equals(dept_emp)){//说明进入组织架构管理功能
%>
	<tr height="30"><td width="100%">
		<iframe id="tree_top" src="selectDisplay.jsp?button_flag=<%=button_flag%>&dept_emp=<%=dept_emp %>" frameborder="0" name="tree_top" style="width:100%;height:100%;" border="0" scrolling="auto"></iframe>
	</td></tr>
<%
	}	
%>
	<tr><td width="100%">
		<iframe id="tree_main" src="tree.jsp" frameborder="0" name="tree_main" style="width:100%;height:100%;" border="0" onload="tree_func.location.href='/system/org.do?b_query=true&dept_emp=<%=dept_emp%>&ckbDisp=<%=ckbDisp%>&button_flag=<%=button_flag%>';" scrolling="auto"></iframe>
		<iframe id="tree_func" src="tree.jsp" frameborder="0" name="tree_func" style="width:0;height:0;" border="0" scrolling="no"></iframe>
	</td></tr>
</table>
</body>
</html>