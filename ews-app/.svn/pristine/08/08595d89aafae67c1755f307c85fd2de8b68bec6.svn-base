<%@ page contentType="text/html; charset=UTF-8" import="org.apache.commons.beanutils.PropertyUtils"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://www.shanghaicorp.com/struts/extend" prefix="extend" %>

<html:html>
<HEAD><title></title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type=text/css rel=stylesheet>
</HEAD>
<script type="text/javascript" src="/js/js.js?r=1221"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
<body align="center" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<html:form action="/system/user">

<html:hidden name="userForm" property="current"/>
<html:hidden name="userForm" property="sid"/>
<html:hidden name="userForm" property="t_INFO_EMPLOYEE.string(CRITICAL_VALUE)"/>
<html:hidden name="userForm" property="t_INFO_EMPLOYEE.string(sid)"/>


<TABLE width="98%" class="detail-table" cellpadding="5" cellspacing="0" align=center>	

	<tr bgcolor="#e0e3e8" height=25><td colspan="4" align="center"><b>用户信息编辑</b></td></tr>
	<tr bgcolor="f7f7f7" height=25>
		<td align="right">登录账户</td>
		<td><html:text property="t_INFO_EMPLOYEE.string(EMPID)" size="30" />&nbsp;<font color="red">*</font>　</td>
		<td align="right">姓名</td>
		<td><html:text property="t_INFO_EMPLOYEE.string(EMPNAME)" size="30"/>&nbsp;<font color="red">*</font>　</td>
	</tr>
	<tr bgcolor="f7f7f7" height=25>
		<td align="right">性别</td>
		<td>	
			<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="2" n2="1"/>
			<html:select property="t_INFO_EMPLOYEE.string(SEX)" size="1">
			<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
			</html:select>
		</td>
		<td align="right">使用状态</td>
		<td>
			<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="3" n2="1"/>
			<html:select property="t_INFO_EMPLOYEE.string(EMP_ACTIVE)" size="1">
			<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
			</html:select>		
		</td>
	</tr>
	<tr bgcolor="f7f7f7" height=25>
		<td align=right>用户类别</td>
		<td>
			<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="10" n2="1"/>
			<html:select property="t_INFO_EMPLOYEE.string(EMP_TYPE)" size="1">
			<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
			</html:select>
		<td align="right">公司电话</td>
		<td><html:text property="t_INFO_EMPLOYEE.string(COPHONE)" size="30"/>　</td>
	</tr>
	<tr bgcolor="f7f7f7" height=25>
		<td align="right">手机</td>
		<td><html:text property="t_INFO_EMPLOYEE.string(MP)" size="30"/>　</td>
		<td align="right">邮件</td>
		<td><html:text property="t_INFO_EMPLOYEE.string(EMAIL)" size="30"/>　</td>
	</tr>
	<tr bgcolor="f7f7f7" height=25>
		<td align="right">身份证号</td>
		<td><html:text property="t_INFO_EMPLOYEE.string(ID_CARD)" size="30"/>　</td>
		<td align="right">驾驶证号</td>
		<td><html:text property="t_INFO_EMPLOYEE.string(DRIVER_CARD)" size="30"/>　</td>
	</tr>
	<tr bgcolor="f7f7f7" height=25>
		<td align="right">备注</td>
		<td colspan="3"><html:textarea property="t_INFO_EMPLOYEE.string(MEMO)" rows="5" cols="65%"/></td>
	</tr>
</table>
<BR>
<table width="100%" >

	<tr height=25>
		<td align="center" colspan="2">
			<extend:submit StyleClass="button-ui" property="b_save" onclick="return checkData();">保存</extend:submit>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<INPUT TYPE="button" class="button-ui" name="b_back" value="返回" onclick="history.back()">
		</td>
	</tr>

</table>
</html:form>
	
</body>


<SCRIPT LANGUAGE="JavaScript">
<!--

function checkData()
{

	if(getValue('t_INFO_EMPLOYEE.string(EMPID)')=="")
	{
		alert("请填写登录名！");
		return false;
	}

	if(getValue('t_INFO_EMPLOYEE.string(EMPNAME)')=="")
	{
		alert("请填写用户的姓名！");
		return false;
	}

	return confirm("您确认保存数据吗？");
}
//-->
</SCRIPT>
</html:html>