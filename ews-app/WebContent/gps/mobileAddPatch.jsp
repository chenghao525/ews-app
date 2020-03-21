<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://www.shanghaicorp.com/struts/extend" prefix="extend" %>
<html:html>
<HEAD>
<script type="text/javascript" src="/js/sort.js"></script>
<script type="text/javascript" src="/js/js.js?r=1221"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
<title></title>
<LINK href="/images/css.css" type="text/css" rel="STYLESHEET"> 
</HEAD> 
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"> 

<html:form action="/gps/mobile"> 

<table width="100%" class="detail-table" cellpadding="5" cellspacing="0" align=center>
	
	<tr bgcolor="f7f7f7" height=25>
		<td width=35% align="right">请输入终端编号：</td>
		<td width=65%>
			<html:text property="mobileList" size="50"  />&nbsp;<font color="red">*</font><br>(注意：编号之间请使用<font color="red">英文逗号</font>隔开)<br>
		<font color="RED">智能终端编号规则</font>：城市汉语拼音第一个字母+M+4位数字。<br>上海SHM0001,北京BJM0001,广州GZM0001,深圳SZM0001,南京NJM0001,...
		</td>
	</tr>
	
	<tr bgcolor="f7f7f7" height=25 align="center">
		<td colspan="2">
			<extend:submit StyleClass="button-ui" property="b_save" onclick="return checkInfo();">保 存</extend:submit>&nbsp;&nbsp;&nbsp;&nbsp;
			<INPUT TYPE="button" class="button-ui" name="b_back" value="<bean:message key="button.back" />" onclick="history.back()">
		</td>
	</tr>
</table>
<html:hidden name="gpsForm" property="current"/>
</html:form>
</body>
</html:html>

<SCRIPT LANGUAGE="JavaScript">
<!--

mini.parse();
function checkInfo(){
	if(check())	{
		if(confirm("<bean:message key="alert.addMessage" />")){
			mini.loading("正在提交数据，请稍后 ...", "提交");
			return true;
		}
	}
	return false;

}

function check(){
	if(""==getTrimValue("mobileList"))	{
		alert("请输入终端设备的编号！");
		return false;
	}
	
	return true;
}
//-->
</SCRIPT>
