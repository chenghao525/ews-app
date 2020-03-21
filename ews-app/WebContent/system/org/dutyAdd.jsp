<%@ page contentType="text/html; charset=UTF-8" import="org.apache.struts.util.RequestUtils" %>
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

<html:form action="/system/org" method="post">
<html:hidden name="orgForm" property="org_sid"/>
<html:hidden name="orgForm" property="org_psid"/>
<html:hidden name="orgForm" property="dept_sid"/>
<html:hidden name="orgForm" property="deptAdd"/>
<html:hidden name="orgForm" property="t_INFO_DEPT.string(CRITICAL_VALUE)"/>

<TABLE width="98%" class="detail-table" cellpadding="5" cellspacing="0" align=center>		
<tr bgcolor="#e0e3e8" height=25><td colspan="2" align="center"><b>职务信息</b></td></tr>
<tr height=25>
	<td align="right" bgcolor="f7f7f7" width="35%">名称：</td>
	<td width="65%"><html:text property="t_INFO_DEPT.string(DEPTNAME)" size="50" />&nbsp;<font color="red">*</font>　</td>
</tr>

<tr height=25>
	<td align="right" bgcolor="f7f7f7">级别：</td>
	<td>
		<html:select property="t_INFO_DEPT.string(DUTY_CLASS)" size="1">
		<html:optionsCollection property="dutyList" label="string(CODENAME)" value="string(CODENO)"/>
		</html:select>　
	</td>
</tr>
<tr height=25>
	<td align="right" bgcolor="f7f7f7" width="35%">排列顺序：</td>
	<td width="65%">
		<html:text property="t_INFO_DEPT.string(DEPTRANK)" size="5" />&nbsp;<font color="red">*</font>
	</td>
</tr>

<tr height=25><td align="center" colspan="2">
	<extend:submit StyleClass="button-ui" property="b_dutysave" onclick="return checkData();">保存</extend:submit>
	&nbsp;&nbsp;
	<INPUT TYPE="button" class="button-ui" value="取消" onclick="history.back();">
</td></tr>

</table>
</html:form>
	
</body>
</html:html>

<SCRIPT LANGUAGE="JavaScript">
<!--
function checkData(){
	if(getValue('t_INFO_DEPT.string(DEPTNAME)')==""){
		alert(confirm("名称必须填写"));
		return false;
	}

	if(getValue("t_INFO_DEPT.string(DEPTRANK)")==""){
		alert("排列顺序必须填写");
		return false;
	}
	if(isNaN(getValue("t_INFO_DEPT.string(DEPTRANK)"))){
		alert("排列顺序必须是数字");
		return false;
	}

	return confirm("您确认保存？");
}
//-->
</SCRIPT>