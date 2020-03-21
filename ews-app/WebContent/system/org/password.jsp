<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://www.shanghaicorp.com/struts/extend" prefix="extend" %>
<%
	String ref=(String)request.getParameter("ref");
%>
<html:html>
<HEAD>
<script type="text/javascript" src="/js/js.js?r=1221"></script>
<script type="text/javascript" src="/js/sort.js"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type=text/css rel=stylesheet>
</HEAD> 
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"> 

<form id="form1">
<%if(ref!=null && "simple".equals(ref)){%>
<font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您的密码过于简单，请修改您的密码</font>
<%}%>
<TABLE width="95%" class="detail-table" cellpadding="5" cellspacing="0" align="center">
	<tr bgcolor="#e0e3e8" height=25><td colspan="2" align="center"><b>修改密码</b></td></tr>

	<tr bgcolor="f7f7f7" height=25>
		<td width=35% align=right>旧密码：</td>
		<td width=65%><input class="mini-password" name="ymima" id="ymima" value=""/></td>
	</TR>
	<tr bgcolor="f7f7f7" height=25>
		<td align=right>新密码：</td>
		<td><input class="mini-password" name="xmima" id="xmima" value="" vtype="rangeLength:6,6" required="true"/>&nbsp;限定为6位的字符串</td>
	</TR>
	<tr bgcolor="f7f7f7" height=25>
		<td align=right>确认新密码：</td>
		<td><input class="mini-password" name="rxmima" id="rxmima" value="" vtype="rangeLength:6,6" required="true"/></td>
	</TR>
</form>
</TABLE>
<html:form action="/system/org" styleId="form00">
<html:hidden name="orgForm" property="org_sid"/>
<html:hidden name="orgForm" property="org_psid"/>
<html:hidden name="orgForm" property="json_string"/>
<TABLE width="95%" cellpadding="0" cellspacing="0" align="center">
	<tr bgcolor="f7f7f7" height=25><td align="center">
		<extend:submit StyleClass="button-ui" property="b_pwdsubmit" onclick="return check()">保存</extend:submit>
	</td></tr>
</table>
</html:form>
</body>
</html:html>

<script>
<!--
mini.parse();
function check(){
	var form = new mini.Form("#form1");
	form.validate();
	if (!form.isValid()){var errors = form.getErrors();errors[0].focus();return false;}

	var data=form.getData();
	if(data.xmima!=data.rxmima){
		mini.alert("输入的新密码不一致");
		return false;
	}
	
	mini.confirm("您确认保存？", "请确认",
		function(action){
			if(action == "ok"){
				setValue("json_string",json2str(data));
				mini.loading("正在提交数据，请稍后 ...", "提交");
				var form=document.forms["form00"];
				form.action=form.action+"?b_pwdsubmit=true";
				form.submit();
				return false;
			}
		}
	);
	return false;
}

//-->
</script>

