<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://www.shanghaicorp.com/struts/extend" prefix="extend" %>
<html:html>
<head>
<title></title>
<LINK href="/images/css.css" type="text/css" rel="STYLESHEET"> 
<script language="JavaScript" src="/js/js.js?r=1221"></script>
<script type="text/javascript" src="/js/sort.js"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="/gps/mobile/sel">

<table width="100%"  border="0" cellpadding="0" cellspacing="0" align=center>
	<tr><td background="../images/mainleft_3.jpg" WIDTH="100%" BORDER=0>
		<TABLE cellSpacing="2" cellPadding="2" border="0" width="100%">
			<tr><td>
				<%
					String    property  = "queryView";
					String    domainName ="T_INFO_MOBILE";
				%>
				<%@include file="/frame/dataQuery.jsp" %>
			</td></tr>
		</table>
	</td></tr>	
	<tr><td valign="top">
		<TABLE cellSpacing="0" cellPadding="0" border="0" align="left" width="100%">
			<tr><td valign="top" width="100%">		
				<extend:dataPrint paginationName="pagination" property="mpList" showOpt="false" domainName="T_INFO_MOBILE" checkBoxName="chk_box"/>
				<extend:paginationLink  property="pagination" buttonName="b_query"/>
			</td></tr>
		</table>
	</td></tr>
	<tr><td align="left">
		&nbsp;&nbsp;&nbsp;&nbsp;<INPUT TYPE="button" class="button-ui" value="确 定" onclick="onOk();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<INPUT TYPE="button" class="button-ui" value="关 闭" onclick="onCancel();">&nbsp;
	</td></tr>
</table>
</html:form>
</body>
</html:html>

<SCRIPT LANGUAGE="JavaScript">
<!--
var a="";
function check(){
	
	var l=document.getElementsByName("chk_box").length;

	var obj;
	var m="";
	for(var i=0;i<l;i++){
		obj=document.getElementsByName("chk_box")[i];
		if(obj.checked){
			obj=$(obj).parent().parent().children();
			m=m+$(obj).eq(2).text()+",";
		}
	}
	if(m==""){
		alert("请选择终端设备");
		return false;
	}else{
		a=m.substring(0,m.length-1);
		return true;
	}
}
function getData(){return a;}
function CloseWindow(action) {if (window.CloseOwnerWindow) window.CloseOwnerWindow(action); else window.close();}
function onOk(){if(check()) CloseWindow("ok");}
function onCancel() {CloseWindow("cancel");}
//-->
</SCRIPT>






