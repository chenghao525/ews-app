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
		<td align="right" width="15%"><bean:message key="dictionary.SEND_USER_ID" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(SEND_USER_ID)" />&nbsp;</td>
		<td align="right" width="15%"><bean:message key="dictionary.RECV_ID" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(RECV_ID)" />&nbsp;</td>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.SEND_TEL" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(SEND_TEL)" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.ECV_TEL" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(RECV_TEL)" />&nbsp;</td>
		<td colspan="2">&nbsp;</td>
	</tr>
	<!--发件人中文信息 
	<tr align="center" bgcolor="#e0e3e8" height=25><td colspan="4"><b><bean:message key="title.SENDZH" /></b></td></tr>
	
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.SEND_USER" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(SEND_USER)" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.SEND_CO" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(SEND_CO)"/>&nbsp;</td>
	</tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.SEND_ADDR" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(SEND_ADDR)" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.SEND_TEL" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(SEND_TEL)" />&nbsp;</td>
	</tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.SEND_FAX" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(SEND_FAX)" />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.SEND_COUNTRY" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(SEND_COUNTRY)"/>&nbsp;</td>
	</tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.SEND_CITY" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(SEND_CITY)"  />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.SEND_CODE" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(SEND_CODE)"  />&nbsp;</td>
	</tr>-->
	<!--发件人英文信息 -->
	<tr align="center" bgcolor="#e0e3e8" height=25><td colspan="6"><b><bean:message key="title.SENEN" /></b></td></tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.SEND_USER_EN" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(SEND_USER_EN)"  />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.SEND_CO_EN" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(SEND_CO_EN)"  />&nbsp;</td>
		<td align="right" width="15%"><bean:message key="dictionary.SEND_ADDR_EN" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(SEND_ADDR_EN)"  />&nbsp;</td>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.SEND_COUNTRY_EN" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(SEND_COUNTRY_EN)"  />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.SEND_CITY_EN" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(SEND_CITY_EN)"  />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.SEND_AREA_EN" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(SEND_AREA_EN)" />&nbsp;</td>
	</tr>
	<!--发件人日文信息 -->
	<tr align="center" bgcolor="#e0e3e8" height=25><td colspan="6"><b><bean:message key="title.SENJP" /></b></td></tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.SEND_USER" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(SEND_USER)"  />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.SEND_CO" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(SEND_CO)"  />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.SEND_ADDR" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(SEND_ADDR)"  />&nbsp;</td>
	</tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.SEND_COUNTRY" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(SEND_COUNTRY)"  />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.SEND_CITY" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(SEND_CITY)"  />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.SEND_AREA" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(SEND_AREA)" />&nbsp;</td>
	</tr>
	<!--收件人中文信息
	<tr align="center" bgcolor="#e0e3e8" height=25><td colspan="4"><b><bean:message key="title.RECVZH" /></b></td></tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.RECV_USER" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(RECV_USER)"  />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.RECV_CO" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(RECV_CO)"  />&nbsp;</td>
	</tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.RECV_ADDR" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(RECV_ADDR)"  />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.ECV_TEL" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(ECV_TEL)"  />&nbsp;</td>
	</tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.RECV_FAX" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(RECV_FAX)"  />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.RECV_COUNTRY" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(RECV_COUNTRY)"  />&nbsp;</td>
	</tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.RECV_CITY" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(RECV_CITY)"  />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.RECV_CODE" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(RECV_CODE)"  />&nbsp;</td>
	</tr> -->
	<!--发件人英文信息 -->
	<tr align="center" bgcolor="#e0e3e8" height=25><td colspan="6"><b><bean:message key="title.RECVEN" /></b></td></tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.RECV_USER_EN" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(RECV_USER_EN)"  />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.RECV_CO_EN" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(RECV_CO_EN)"  />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.RECV_ADDR_EN" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(RECV_ADDR_EN)"  />&nbsp;</td>
	</tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.RECV_COUNTRY_EN" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(RECV_COUNTRY_EN)"  />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.RECV_CITY_EN" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(RECV_CITY_EN)"  />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.RECV_AREA_EN" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(RECV_AREA_EN)" />&nbsp;</td>
	</tr>
	<!--发件人日文信息 -->
	<tr align="center" bgcolor="#e0e3e8" height=25><td colspan="6"><b><bean:message key="title.RECVJP" /></b></td></tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.RECV_USER" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(RECV_USER)"  />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.RECV_CO" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(RECV_CO)"  />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.RECV_ADDR" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(RECV_ADDR)"  />&nbsp;</td>
	</tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="right"><bean:message key="dictionary.RECV_COUNTRY" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(RECV_COUNTRY)"  />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.RECV_CITY" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(RECV_CITY)"  />&nbsp;</td>
		<td align="right"><bean:message key="dictionary.RECV_AREA" />:</td>
		<td><bean:write name="dictionaryForm" property="t_MAP_TRANS_DIC.string(RECV_AREA)" />&nbsp;</td>
	</tr>
	<tr bgcolor="f7f7f7" height=22>
		<td align="center" colspan="6">	
				<INPUT TYPE="button" class="button-ui" name="b_back" value="<bean:message key="button.back" />" onclick="history.back()">
		</td>
	</tr>
</table>
<html:hidden name="dictionaryForm" property="current" />
<html:hidden name="dictionaryForm" property="t_MAP_TRANS_DIC.string(CRITICAL_VALUE)"/>

</html:form>
</body>
</html:html>
