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

<html:form action="/gps/mobile">
<html:hidden name="gpsForm" property="t_INFO_MOBILE.string(SID)"/>
<html:hidden name="gpsForm" property="t_INFO_MOBILE.string(DEPT_SID)"/>

<TABLE width="98%" class="detail-table" cellpadding="5" cellspacing="0" align=center>
<tr align="center" bgcolor="#e0e3e8" height=25><td colspan="2"><b>终端设备信息</b></td></tr>
<tr bgcolor="f7f7f7" height=25>
	<td align="right" width="35%">终端设备编号：</td>
	<td width="65%"><html:text property="t_INFO_MOBILE.string(MOBILE)" size="50" />&nbsp;<font color="red">*</font></td>
</tr>
<!-- <tr bgcolor="f7f7f7" height=25>
	<td align="right" width="35%">手机号码：</td>
	<td width="65%">
		<html:text property="t_INFO_MOBILE.string(SIM_NUMBER)" size="20" />
	</td>
</tr> -->
<tr bgcolor="f7f7f7" height=25>
	<td align="right" width="35%">所在城市：</td>
	<td width="65%">
		<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="16" n2="1"/>
		<html:select property="t_INFO_MOBILE.string(CITY)" size="1" disabled="true">
		<html:option value=""></html:option>
		<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
		</html:select>
	</td>
</tr>

<tr bgcolor="f7f7f7" height=25>
	<td align="right" width="35%">所在部门：</td>
	<td width="65%">
		<html:text property="t_INFO_MOBILE.string(DEPT)" size="50" readonly="true" />
	</td>
</tr>
<tr bgcolor="f7f7f7" height=25>
	<td align="right" width="35%">手机串号：</td>
	<td width="65%">
		<html:text property="t_INFO_MOBILE.string(IMEI_NUMBER)" size="50" maxlength="30"/>
	</td>
</tr>
<tr bgcolor="f7f7f7" height=25>
	<td align="right" width="35%">使用对象：</td>
	<td width="65%">
		<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="21" n2="1"/>
		<html:select property="t_INFO_MOBILE.string(USER_MODE)" size="1">
		<html:option value=""></html:option>
		<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
		</html:select>&nbsp;<font color="red">*</font>
	</td>
</tr>
<tr bgcolor="f7f7f7" height=25>
	<td align="right" width="35%">备注：</td>
	<td width="65%">
		<html:textarea property="t_INFO_MOBILE.string(MEMO)" rows="3" cols="50"/>&nbsp;　
	</td>
</tr>

<tr bgcolor="f7f7f7" height=25>
	<td align="center" colspan="2">
	<extend:submit StyleClass="button-ui" property="b_save" onclick="return checkData();"><bean:message key="button.save" /></extend:submit>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<INPUT TYPE="button" class="button-ui" value="<bean:message key="button.cancel" />" onclick="history.back();">
</td></tr>
</table>

<SCRIPT LANGUAGE="JavaScript">
<!--
mini.parse();

function checkData(){
	if(getTrimValue('t_INFO_MOBILE.string(MOBILE)')==""){
		alert("请输入终端设备编号！");
		return false;
	}
	if(getTrimValue('t_INFO_MOBILE.string(USER_MODE)')==null || getTrimValue('t_INFO_MOBILE.string(USER_MODE)')==""){
		alert("请输入终端设备的使用对象！");
		return false;
	}

	if(confirm("您确定保存吗？")){
		mini.loading("正在提交数据，请稍后 ...", "提交");
		document.all("t_INFO_MOBILE.string(DEPT_CITY)").disabled=false;
		return true;
	}else{
		return false;
	}
}	

//-->
</SCRIPT>
</html:form>	
</body>
</html:html>