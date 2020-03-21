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

<html:form action="/system/sysdictionary">

<html:hidden name="sysDicForm" property="current"/>
<html:hidden name="sysDicForm" property="sid"/>
<html:hidden name="sysDicForm" property="t_LIST_STATUS.string(CRITICAL_VALUE)"/>
<html:hidden name="sysDicForm" property="bAdd"/>


<TABLE width="95%" class="detail-table" cellpadding="5" cellspacing="0" align=center>	
<logic:equal name="sysDicForm" property="bAdd" value="true">
	<tr bgcolor="#e0e3e8" height=25><td colspan="2" align="center"><b>在该类别下新增字典项</b></td></tr>
</logic:equal>
<logic:equal name="sysDicForm" property="bAdd" value="false">
	<tr bgcolor="#e0e3e8" height=25><td colspan="2" align="center"><b>字典项修改</b></td></tr>
</logic:equal>
	<tr bgcolor="f7f7f7" height=25>
		<td align="right" width="45%">名称：</td>
		<td><html:text property="t_LIST_STATUS.string(CONTENT)" size="30" />&nbsp;<font color="red">*</font></td>
	</tr>
	<tr bgcolor="f7f7f7" height=25>
		<td align="right" width="45%">类别：</td>
		<td><html:text property="t_LIST_STATUS.string(INFOTYPE)" readonly="true" size="30" />&nbsp;<font color="red">*</font></td>
	</tr>
	<tr bgcolor="f7f7f7" height=25>
		<td align="right" width="45%">排列顺序：</td>
		<td><html:text property="t_LIST_STATUS.string(OPTORDER)" size="30" />&nbsp;<font color="red">*</font></td>
	</tr>
	<tr bgcolor="f7f7f7" height=25>
		<td align="right" width="45%">引用的类别：</td>
		<td><html:text property="t_LIST_STATUS.string(REFTYPE)" size="8" /></td>
	</tr>
	<tr bgcolor="f7f7f7" height=25>
		<td align="right" width="45%">引用父类：</td>
		<td><html:text property="t_LIST_STATUS.string(PARENT)" size="8" /></td>
	</tr>
	<tr bgcolor="f7f7f7" height=25>
		<td align="right" width="45%">KeyValue：</td>
		<td><html:text property="t_LIST_STATUS.string(KEYVALUE)" size="8" /></td>
	</tr>
</table>
<BR>
<table width="95%" >

	<tr height=25>
		<td align="center" colspan="2">
			<extend:submit StyleClass="button-ui" property="b_save" onclick="return checkData();"><bean:message key="button.save"/></extend:submit>
		<logic:equal name="sysDicForm" property="bAdd" value="false">
			<extend:submit StyleClass="button-ui" property="b_add">在该类别下新增</extend:submit>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<extend:submit StyleClass="button-ui" property="b_del"  onClick="return DelConform()">删除</extend:submit>
		</logic:equal>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<INPUT TYPE="button" class="button-ui" name="b_back" value="<bean:message key="button.back"/>" onclick="location.href='/system/sysdictionary.do?b_query=true';">
		</td>
	</tr>

</table>
</html:form>
	
</body>


<SCRIPT LANGUAGE="JavaScript">
<!--

function checkData(){

	if(getValue('t_LIST_STATUS.string(CONTENT)')==""){
		alert("字典项名称必须填写。");
		return false;
	}

	if(getValue('t_LIST_STATUS.string(排列顺序)')==""){
		alert("字典项排列顺序必须填写，且应为整数。");
		return false;
	}

	return confirm("<bean:message key="alert.editMessage"/>");
}
function DelConform(){

	return confirm("<bean:message key="alert.delMessage"/>");
}

//-->
</SCRIPT>
</html:html>