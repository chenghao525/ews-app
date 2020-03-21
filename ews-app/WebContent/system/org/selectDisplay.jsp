<%@ page contentType="text/html; charset=UTF-8" %>
<HEAD><title></title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type=text/css rel=stylesheet>
</HEAD>

<%
	//区别人员还是部门的编号
	String dept_emp=request.getParameter("dept_emp");
	//区别是否有确定按钮
	String button_flag=request.getParameter("button_flag");
%>

<body align="center" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<TABLE width="100%" border=0 cellpadding="5" cellspacing="0" valign=middle>
	<tr valign="middle" height=29>
		<td valign="middle" nowrap>已选择：<input type="text" name="selView" readonly>
<%
	if("1".equals(button_flag)){//部门
			if("1".equals(dept_emp)){
		%>
				<INPUT TYPE="submit" value="确 定" onclick="return submitDeptSelect();">
		<%
			}else{
		%>		
				<INPUT TYPE="submit" value="确 定" onclick="return submitEmpSelect();">
		<% 		
			}
%>
		&nbsp;&nbsp
		<INPUT TYPE="button" class="button-ui" value="关 闭" onclick="parent.parent.window.close();">
<%
	}
%>
		</td>
	</tr>
</table>

<script type="text/javascript">
<!--
function setSelectView(content){
	document.all.selView.value=content;
}
function submitDeptSelect(){
	parent.tree_main.getDeptSel();
}
function submitEmpSelect(){
	parent.tree_main.getEmpSel();
}
//-->
</script>
</body>
</html>
