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
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="/provider/provider/sel">
<html:hidden name="providerForm" property="selType"/>
<table width="100%"  border="0" cellpadding="0" cellspacing="0" align=center>
	<tr><td background="../images/mainleft_3.jpg" WIDTH="100%" BORDER=0>
		<TABLE cellSpacing="2" cellPadding="2" border="0" width="100%">
			<tr><td>
				<%
					String    property  = "queryView";
					String    domainName ="T_INFO_PROVIDER";
				%>
				<%@include file="/frame/dataQuery.jsp" %>
			</td></tr>
		</table>
	</td></tr>
	<tr><td valign="top">
		<TABLE cellSpacing="0" cellPadding="0" border="0" align="left" width="100%">
			<tr><td valign="top" width="100%">
			<logic:equal name="providerForm" property="selType" value="check">
				<extend:dataPrint paginationName="pagination" property="provLst" domainName="T_INFO_PROVIDER" checkBoxName="chk_box" showOpt="false"/>
			</logic:equal>
			<logic:equal name="providerForm" property="selType" value="radio">
				<extend:dataPrint paginationName="pagination" property="provLst" domainName="T_INFO_PROVIDER" radioName="chk_box" showOpt="false"/>
			</logic:equal>
			<extend:paginationLink  property="pagination" buttonName="b_query"/>
			</td></tr>
		</table>
	</td></tr>
	<tr><td align="left">
		<br><br>&nbsp;&nbsp;&nbsp;&nbsp;<INPUT TYPE="button" class="button-ui" value="确 定" onclick="onOk();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<INPUT TYPE="button" class="button-ui" value="关 闭" onclick="onCancel();">&nbsp;
	</td></tr>
</table>
<br><br>
</html:form>
</body>
</html:html>

<SCRIPT LANGUAGE="JavaScript">
<!--
var a = new Array();

function check(){
	var l=document.getElementsByName("chk_box").length;
	var obj;
	var rSid="",rId="",rName="";
	for(var i=0;i<l;i++){
		obj=document.getElementsByName("chk_box")[i];
		if(obj.checked){
			rSid=rSid+obj.value+",";
			obj=$(obj).parent().parent().children();
			rId=rId+$(obj).eq(2).text()+",";
			rName=rName+$(obj).eq(3).text()+"/"+$(obj).eq(9).text()+",";
		}
	}
	if(rSid==""){
		alert("请选择服务商");
		return false;
	}else{
		rSid=rSid.substring(0,rSid.length-1);
		rId=rId.substring(0,rId.length-1);
		rName=rName.substring(0,rName.length-1);
		
		a[0]=rSid;
		a[1]=rId;
		a[2]=rName;
		return true;
	}
}

function getData(){return a;}
function CloseWindow(action) {if (window.CloseOwnerWindow) window.CloseOwnerWindow(action); else window.close();}
function onOk(){if(check()) CloseWindow("ok");}
function onCancel() {CloseWindow("cancel");}
//-->
</SCRIPT>
