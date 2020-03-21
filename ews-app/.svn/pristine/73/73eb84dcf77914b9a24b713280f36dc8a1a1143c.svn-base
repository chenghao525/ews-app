<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://www.shanghaicorp.com/struts/extend" prefix="extend" %>

<html:html>
<head>
<title></title>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type="text/css" rel="STYLESHEET"> 
<script language="JavaScript" src="/js/js.js?r=1221"></script>
<script type="text/javascript" src="/js/sort.js"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"> 
<html:form action="/basic/dic">

<html:hidden name="dictionaryForm" property="sid"/>
<TABLE width="100%" class="detail-table" cellpadding="5" cellspacing="0" align=center>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.SEND_USER_ID" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(SEND_USER_ID)" size="20" />&nbsp;<font color="red">*</font></td>
		<td align="right"><bean:message key="dictionary.RECV_ID" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(RECV_ID)" size="20" />&nbsp;<font color="red">*</font></td>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.SEND_TEL" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(SEND_TEL)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.ECV_TEL" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(RECV_TEL)" size="20" />&nbsp;</td>
		<td colspan="2">&nbsp;</td>
	</tr>
	<!--发件人中文信息 
	<tr align="center" bgcolor="#e0e3e8" height=25><td colspan="4"><b><bean:message key="title.SENDZH" /></b></td></tr>
	
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.SEND_USER" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(SEND_USER)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.SEND_CO" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(SEND_CO)" size="20" />&nbsp;</td>
	</tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.SEND_ADDR" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(SEND_ADDR)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.SEND_TEL" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(SEND_TEL)" size="20" />&nbsp;</td>
	</tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.SEND_FAX" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(SEND_FAX)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.SEND_COUNTRY" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(SEND_COUNTRY)" size="20" />&nbsp;</td>
	</tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.SEND_CITY" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(SEND_CITY)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.SEND_CODE" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(SEND_CODE)" size="20" />&nbsp;</td>
	</tr>-->
	<!--发件人英文信息 -->
	<tr align="center" bgcolor="#e0e3e8" height=25><td colspan="6"><b><bean:message key="title.SENEN" /></b></td></tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.SEND_USER_EN" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(SEND_USER_EN)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.SEND_CO_EN" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(SEND_CO_EN)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.SEND_ADDR_EN" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(SEND_ADDR_EN)" size="20" />&nbsp;</td>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.SEND_COUNTRY_EN" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(SEND_COUNTRY_EN)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.SEND_CITY_EN" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(SEND_CITY_EN)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.SEND_AREA_EN" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(SEND_AREA_EN)" size="20" /></td>
	</tr>
	<!--发件人日文信息 -->
	<tr align="center" bgcolor="#e0e3e8" height=25><td colspan="6"><b><bean:message key="title.SENJP" /></b></td></tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.SEND_USER_JP" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(SEND_USER_JP)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.SEND_CO_JP" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(SEND_CO_JP)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.SEND_ADDR_JP" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(SEND_ADDR_JP)" size="20" />&nbsp;</td>
	</tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.SEND_COUNTRY_JP" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(SEND_COUNTRY_JP)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.SEND_CITY_JP" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(SEND_CITY_JP)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.SEND_AREA_JP" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(SEND_AREA_JP)" size="20" /></td>
	</tr>
	<!--收件人中文信息 
	<tr align="center" bgcolor="#e0e3e8" height=25><td colspan="4"><b><bean:message key="title.RECVZH" /></b></td></tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.RECV_USER" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(RECV_USER)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.RECV_CO" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(RECV_CO)" size="20" />&nbsp;</td>
	</tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.RECV_ADDR" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(RECV_ADDR)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.ECV_TEL" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(ECV_TEL)" size="20" />&nbsp;</td>
	</tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.RECV_FAX" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(RECV_FAX)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.RECV_COUNTRY" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(RECV_COUNTRY)" size="20" />&nbsp;</td>
	</tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.RECV_CITY" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(RECV_CITY)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.RECV_CODE" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(RECV_CODE)" size="20" />&nbsp;</td>
	</tr>-->
	<!--发件人英文信息 -->
	<tr align="center" bgcolor="#e0e3e8" height=25><td colspan="6"><b><bean:message key="title.RECVEN" /></b></td></tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.RECV_USER_EN" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(RECV_USER_EN)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.RECV_CO_EN" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(RECV_CO_EN)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.RECV_ADDR_EN" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(RECV_ADDR_EN)" size="20" />&nbsp;</td>
	</tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.RECV_COUNTRY_EN" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(RECV_COUNTRY_EN)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.RECV_CITY_EN" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(RECV_CITY_EN)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.RECV_AREA_EN" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(RECV_AREA_EN)" size="20" /></td>
	</tr>
	<!--发件人日文信息 -->
	<tr align="center" bgcolor="#e0e3e8" height=25><td colspan="6"><b><bean:message key="title.RECVJP" /></b></td></tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.RECV_USER_JP" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(RECV_USER_JP)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.RECV_CO_JP" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(RECV_CO_JP)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.RECV_ADDR_JP" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(RECV_ADDR_JP)" size="20" />&nbsp;</td>
	</tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.RECV_COUNTRY_JP" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(RECV_COUNTRY_JP)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.RECV_CITY_JP" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(RECV_CITY_JP)" size="20" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.RECV_AREA_JP" />:</td>
		<td><html:text property="t_MAP_TRANS_DIC.string(RECV_AREA_JP)" size="20" /></td>
	</tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="center" colspan="6">	
			<extend:submit StyleClass="button-ui" property="b_save" onclick="return checkInfo();" ><bean:message key="button.save" /></extend:submit>&nbsp;&nbsp;&nbsp;&nbsp;
				<INPUT TYPE="button" class="button-ui" name="b_reset" value="<bean:message key="button.reset" />" onclick="window.location.href='/basic/dic.do?b_add=true'">&nbsp;&nbsp;&nbsp;&nbsp;
				<INPUT TYPE="button" class="button-ui" name="b_back" value="<bean:message key="button.back" />" onclick="history.back()">
		</td>
	</tr>
</table>
<html:hidden name="dictionaryForm" property="current" />
</html:form>
</body>
</html:html>
<SCRIPT LANGUAGE="JavaScript">
<!--
mini.parse();
function checkInfo(){
	if(check()){return confirm("<bean:message key="alert.addMessage" />");}
	return false;
}

function check(){
	if(getValue("t_MAP_TRANS_DIC.string(SEND_USER_ID)")==""){
		mini.alert("<bean:message key="required.SEND_USER_ID" />");
		return false;
	}
	if(getValue("t_MAP_TRANS_DIC.string(RECV_ID)")==""){
		mini.alert("<bean:message key="dictionary.RECV_ID" /><bean:message key="alert.isnotnull" />");
		return false;
	}
	return true;
}
//-->
</SCRIPT>