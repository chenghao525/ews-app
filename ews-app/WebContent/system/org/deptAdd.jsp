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

<script type="text/javascript" src="/js/js.js?r=1223"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
<body align="center" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<html:form action="/system/org">
<html:hidden name="orgForm" property="org_sid"/>
<html:hidden name="orgForm" property="org_psid"/>
<html:hidden name="orgForm" property="dept_sid"/>
<html:hidden name="orgForm" property="deptAdd"/>
<html:hidden name="orgForm" property="parentDept"/>
<html:hidden name="orgForm" property="t_INFO_DEPT.string(CRITICAL_VALUE)"/>

<input type="hidden" name="PROVINCE" value="<bean:write name="orgForm" property="t_INFO_DEPT.string(PROVINCE)"/>">
<input type="hidden" name="CITY" value="<bean:write name="orgForm" property="t_INFO_DEPT.string(DEPT_CITY)"/>">

<TABLE width="98%" class="detail-table" cellpadding="5" cellspacing="0" align=center>

<tr align="center" bgcolor="#e0e3e8" height=25><td colspan="2"><b>单位部门信息</b></td></tr>
<tr height=25>
	<td align="right" width="35%" bgcolor="f7f7f7">名称：</td>
	<td width="65%"><html:text property="t_INFO_DEPT.string(DEPTNAME)" size="50" />&nbsp;<font color="red">*</font></td>
</tr>
<tr height=25>
	<td align="right" width="35%" bgcolor="f7f7f7">单位类别：</td>
	<td width="65%">
		<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="13" n2="1"/>
		<html:select property="t_INFO_DEPT.string(DEPT_TYPE)" size="1">
		<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
		</html:select>
	</td>
</tr>
<tr height=25>
	<td align="right" width="35%" bgcolor="f7f7f7">所在城市：</td>
	<td width="65%">
		<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="161" n2="1"/>
		<html:select property="t_INFO_DEPT.string(PROVINCE)" size="1" onchange="getCityArea('16',this.value,'t_INFO_DEPT.string(DEPT_CITY)',getValue('CITY'));">
		<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
		</html:select>
		<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="16" n2="1"/>
		<html:select property="t_INFO_DEPT.string(DEPT_CITY)" size="1">
		<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
		</html:select>
	</td>
</tr>

<tr height=25>
	<td align="right" width="35%" bgcolor="f7f7f7">排列顺序：</td>
	<td width="65%">
		<html:text property="t_INFO_DEPT.string(DEPTRANK)" size="5" />&nbsp;<font color="red">*</font>
	</td>
</tr>

<tr height=25>
	<td align="center" colspan="2">
	<extend:submit StyleClass="button-ui" property="b_deptsave" onclick="return checkData();">保存</extend:submit>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<INPUT TYPE="button" class="button-ui" value="取消" onclick="history.back();">
</td></tr>
</table>

<SCRIPT LANGUAGE="JavaScript">
<!--

function checkData(){
	if(getTrimValue('t_INFO_DEPT.string(DEPTNAME)')=="")	{
		alert("名称必须填写");
		return false;
	}

	if(getTrimValue("t_INFO_DEPT.string(DEPTRANK)")==""){
		alert("排列顺序必须填写");
		return false;
	}
	if(isNaN(getTrimValue("t_INFO_DEPT.string(DEPTRANK)"))){
		alert("排列顺序不能为空");
		return false;
	}

	return (confirm("您确定保存吗？"));
}	
getCityArea('161',null,'t_INFO_DEPT.string(PROVINCE)',getValue("PROVINCE"));
getCityArea('16',getValue("PROVINCE"),'t_INFO_DEPT.string(DEPT_CITY)',getValue("CITY"));
$('select[name="t_INFO_DEPT.string(PROVINCE)"]').change();
//-->
</SCRIPT>


</html:form>	
</body>
</html:html>