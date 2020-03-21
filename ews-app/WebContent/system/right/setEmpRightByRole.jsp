<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://www.shanghaicorp.com/struts/extend" prefix="extend" %>
<HTML><HEAD>
<script language="JavaScript" src="/js/js.js?r=1221"></script>
<script language="JavaScript" src="/js/rightopt.js"></script>
<TITLE></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type="text/css" rel="STYLESHEET"> 
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
</HEAD> 
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" WIDTH="100%"> 

<html:form action="/right/rightsetbyrole">
<TABLE width="95%" class="detail-table" cellpadding="5" cellspacing="0" align=center>
	<tr bgcolor="#e0e3e8" height=25><td colspan="2" align="center"><b><bean:message key="right.list_person" /></b></td></tr>
	<tr bgcolor="f7f7f7" height=25><td>
		
		<bean:message key="right.role_info" />：
			<html:select property="curroleid" size="1" style="width:200" onchange="b_query.click();">
			<html:optionsCollection property="roleinfolist" label="string(rolename)" value="string(roleid)"/>
		</html:select>
		</td></tr>
		<tr style="display:none"><td><extend:submit property="b_query" value=""/></td></tr>
	</td></tr>
</table>
&nbsp;&nbsp;<INPUT TYPE="button" class="button-ui" value="<bean:message key="button.check_all" />" onclick="selectAll()">&nbsp;&nbsp;&nbsp;&nbsp;<INPUT TYPE="button" class="button-ui" value="<bean:message key="button.reset" />" onclick="selectNone()"><br><br>
<TABLE cellPadding="0" cellSpacing="0" width="95%" border="0" align="center">
	<tr height="30">
<%
	int num = 0; 
%>

	<logic:iterate id="allright" name="rightForm" property="emplist">
		<logic:equal name="allright" property="string(ISMYRIGHT)" value="1">
			<td width="25%" valign="middle">
				<%-- 当前用户拥有此权限 --%>
				<input type="checkbox" style="cursor:hand;" name="roleemplist" value="<bean:write name='allright' property='string(sid)'/>" checked>&nbsp;<bean:write name="allright" property="string(empinfo)"/>
			</td>
		</logic:equal>
			
		<logic:equal name="allright" property="string(ISMYRIGHT)" value="0">
			<td width="25%" valign="middle">
				<%-- 当前用户没有此权限 --%>
				<input type="checkbox" style="cursor:hand;" name="roleemplist" value="<bean:write name='allright' property='string(sid)'/>">&nbsp;<bean:write name="allright" property="string(empinfo)"/>
			</td>
		</logic:equal>
<%
	num++;
	if (num == 4 ){
%>
	</tr><tr height="30">
<%
		num=0;
	} 
%>
	</logic:iterate>
	</tr>
	<tr bgcolor="f7f7f7" height="40"><td align="center" colspan="5">
		<logic:notEqual name="rightForm" property="curroleid" value="">
			<extend:submit StyleClass="button-ui" property="b_save" onclick="return saveRight();"><bean:message key="button.save" /></extend:submit>
		</logic:notEqual>
	</td></tr>
</table>
</html:form>	

</body>
</html>

<SCRIPT LANGUAGE="JavaScript">
<!--
mini.parse();
function saveRight(){
	if(!confirm("<bean:message key="alert.info_update" />")) return false;
	mini.loading("正在提交数据，请稍后 ...", "提交");
	return true;
}

function selectAll(){
	for(var i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="checkbox"){
			document.all.item(i).checked = true;
		}
	}
}

function selectNone(){
	for(var i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="checkbox"){
			document.all.item(i).checked = false;
		}
	}
}
//-->
</SCRIPT>