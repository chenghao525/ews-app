<%@ page contentType="text/html; charset=UTF-8" import="org.apache.commons.beanutils.PropertyUtils,org.apache.struts.util.RequestUtils,com.corp.frame.eflow.*"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://www.shanghaicorp.com/struts/extend" prefix="extend" %>

<html:html>
<HEAD><title></title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type=text/css rel=stylesheet>  
</HEAD>

<style>
@media print{
.print{display:block;}
.nprint{display:none;}
}
</style>
<SCRIPT language=JAVASCRIPT>
function pageHeaderFooter(){
	try{
        var RegWsh = new ActiveXObject("WScript.Shell")
        hkey_key="header"        
        RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"")
        hkey_key="footer"    
        RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"")
	}catch(e){}
}

function printPage(){
        pageHeaderFooter();
        window.print();
}
</SCRIPT>

<body  topmargin=0 leftmargin=0>

<html:form action="/system/user">

<html:hidden name="userForm" property="sid"/>
<html:hidden name="userForm" property="current"/>

	<TABLE width="95%" class="detail-table" cellpadding="5" cellspacing="0" align=center class="print">
	<tr bgcolor="#e0e3e8" height=25><td colspan=4 align="center"><b><bean:message key="user.people_info"/></b></td></tr>
	<tr bgcolor="f7f7f7" height=25>
		<td align=right><bean:message key="user.login"/>:</td>
		<td><bean:write name="userForm" property="t_INFO_EMPLOYEE.string(EMPID)"/>&nbsp;</td>
		<td align=right><bean:message key="user.fullname"/>:</td>
		<td><bean:write name="userForm" property="t_INFO_EMPLOYEE.string(EMPNAME)"/>&nbsp;</td>
	</tr>
 	<tr bgcolor="f7f7f7" height=25>
		<td align=right><bean:message key="user.sex"/>:</td>
		<td>
			<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="2" n2="1"/>
			<html:select property="t_INFO_EMPLOYEE.string(SEX)" size="1" disabled="true">
			<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
			</html:select>
		</td>
		<td align="right"><bean:message key="user.log_status"/>:</td>
		<td>
			<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="3" n2="1"/>
			<html:select property="t_INFO_EMPLOYEE.string(EMP_ACTIVE)" size="1" disabled="true">
			<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
			</html:select>		
		</td>
	</tr>
	<tr bgcolor="f7f7f7" height=25>
		<td align=right><bean:message key="user.duty"/>:</td>
		<td>
			<extend:importCode domainName="T_INFO_DEPT" codeName="list" n2="2" />
			<html:select property="t_INFO_EMPLOYEE.string(DUTY)" size="1" disabled="true">
			<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
			</html:select>
			</td>
		<td align=right><bean:message key="user.people_type"/>:</td>
		<td>
			<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="10" n2="1"/>
			<html:select property="t_INFO_EMPLOYEE.string(EMP_TYPE)" size="1" disabled="true">
			<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
			</html:select>
	</tr>
 	<tr bgcolor="f7f7f7" height=25>
		<td align=right><bean:message key="user.corp_tel"/>:</td>
		<td><bean:write name="userForm" property="t_INFO_EMPLOYEE.string(COPHONE)"/>&nbsp;</td>
		<td   align=right><bean:message key="user.mob_tel"/>:</td>
		<td  ><bean:write name="userForm" property="t_INFO_EMPLOYEE.string(MP)"/>&nbsp;</td>
	</tr>

	<tr bgcolor="f7f7f7" height=25>
		<td align=right><bean:message key="user.email"/>E-mail:</td>
		<td><bean:write name="userForm" property="t_INFO_EMPLOYEE.string(EMAIL)"/>&nbsp;</td>
		<td align=right>&nbsp;</td>
		<td>&nbsp;</td>
    </tr>
	<tr bgcolor="f7f7f7" height=25>
		<td align=right>身份证号:</td>
		<td><bean:write name="userForm" property="t_INFO_EMPLOYEE.string(ID_CARD)"/>&nbsp;</td>
		<td align=right>驾驶证号:</td>
		<td><bean:write name="userForm" property="t_INFO_EMPLOYEE.string(DRIVER_CARD)"/>&nbsp;</td>
	</tr>

	<tr bgcolor="f7f7f7" height=25>
		<td align="right"><bean:message key="user.remark"/>:</td>
		<td colspan="3"><bean:write name="userForm" property="t_INFO_EMPLOYEE.string(MEMO)"/>&nbsp;</td>
	</tr>
</table>
<br><br>

<TABLE width="100%" align=center class="nprint">
	<TR  height=25>
		<td align="center">
			<INPUT TYPE="button" class="button-ui" name="b_back" value="<bean:message key="button.back"/>" onclick="history.back()">
		</td>
		
	</TR>
</TABLE>
</html:form>

<SCRIPT LANGUAGE="JavaScript">
<!--
function delConf(){
	return (confirm("<bean:message key="alert.del"/>"));
}
//-->
</SCRIPT>
</body>
</html:html>