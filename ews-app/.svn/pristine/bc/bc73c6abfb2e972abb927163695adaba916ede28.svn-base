<%@ page contentType="text/html; charset=UTF-8" import="org.apache.struts.util.RequestUtils,com.corp.oa.util.G"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://www.shanghaicorp.com/struts/extend" prefix="extend" %>

<%
	String d=G.getCurrentDate();
%>
<head>
<title></title>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57GMT">
<LINK href="/images/css.css" type="text/css" rel="STYLESHEET"> 
<script language="JavaScript" src="/js/js.js?r=1221"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>

</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" align="left"> 
<html:form action="/gps/gps">
<html:hidden property="sid" />
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<tr><td>
<table width="380" border="0" cellpadding="3" cellspacing="0" align="left">
	<tr height="25"><td valign="top" colspan="4">
		城市：
		<logic:equal name="gpsForm" property="superRole" value="true">
			<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="16" n2="1"/>
			<html:select property="city" size="1" onchange="changeAttr()">
			<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
			</html:select>
		</logic:equal>
		<logic:notEqual name="gpsForm" property="superRole" value="true">
			<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="16" n2="1"/>
			<html:select property="city" size="1" onchange="changeAttr()" disabled="true">
			<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
			</html:select>
		</logic:notEqual>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;终端范围：
			<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="21" n2="1"/>
			<html:select property="usermode" size="1" onchange="changeAttr()">
			<html:option value="">全部</html:option>
			<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
			</html:select>
	</td></tr>
	<tr height="35"><td valign="top" colspan="4">
		终端日轨迹跟踪日期：<html:text styleId="monitorDay" property="monitorDay" styleClass="mini-datepicker" style="width:120px;vertical-align:bottom;" value="<%=d%>"/>
	</td></tr>
<%
	int i=0;
	String bg1="#DFDFDF";
	String bg2="#FFFFFF";

	String[] st=(String[])RequestUtils.lookup(pageContext, "gpsForm", "status", null);
	String ss="";
	if(st!=null){for(int j=0;j<st.length;j++){ss+=st[j]+",";}}

	String mode=(String)RequestUtils.lookup(pageContext, "gpsForm", "usermode", null);

	String gpsJson=(String)RequestUtils.lookup(pageContext, "gpsForm", "gpsListJsonString", null);

	if(!"170".equals(mode)){//非易维达模式
%>
<tr bgcolor="#CCFFFF"><td colspan="4">
	过滤状态：
	<logic:iterate id="list" name="gpsForm" property="statusList">
		<input type="checkbox" name="status" style="cursor:hand;" value="<bean:write name="list" property="string(SID)" />" checked><bean:write name="list" property="string(CONTENT)" />&nbsp;&nbsp;
	</logic:iterate>
</td></tr>
<%
	}
%>
<tr bgcolor="#CCFFFF">
	<td colspan="3" nowrap>
		页面刷新时间：<bean:write name="gpsForm" property="refreshTime" />
	</td>
	<td align="center"><extend:submit StyleClass="button-ui" property="b_monitor" value="刷 新" onclick="return refreshClick();"/></td>
</tr>
<logic:iterate id="list" name="gpsForm" property="gpsList">
<%
	if(i%2==0){
%>
	<tr bgcolor="<%=bg1%>">
<%
	}else{	
%>
	<tr bgcolor="<%=bg2%>">
<%
	}
%>
		<td width="*" nowrap>
			<%=i+1%>.
		</td>
		<td width="40%" nowrap>
			<b><a href="#0" onclick="showEmployee('<bean:write name="list" property="string(EMPSID)" />');" title="<bean:write name="list" property="string(mobile_id)" />/查看人员个人信息"><bean:write name="list" property="string(empname)" />[<bean:write name="list" property="string(emp_id)" />/<bean:write name="list" property="string(mp)" />]</a></b><br>
			GPS定位：<bean:write name="list" property="string(GPS_TIME_NAME)" />
		</td>
		<td width="50%" nowrap>
			<img src="../images/location.png" width="28" height="28" border="0" title="最后定位点" style="cursor:hand" onclick="setLocation(<%=i%>);">&nbsp;<img src="../images/sendsms.png" width="28" height="28" border="0" style="cursor:hand" title="给Ta发短信" onclick="sendSMS('<bean:write name="list" property="string(mp)" />');">&nbsp;<img src="../images/route.png" width="28" height="28" border="0" title="显示一天的路径轨迹" style="cursor:hand" onclick="dayTrack('<bean:write name="list" property="string(mobile_id)" />');">
		</td>
		<td width="*" align="center" nowrap>
			<b><a href="#0" onclick="showMobileStatus('<bean:write name="list" property="string(mobile_id)" />')" title="查看状态切换历史"><bean:write name="list" property="string(statusname)" /></a></b>
		</td>
	</tr>
<%
	i++;
%>
</logic:iterate>
</table>
</td></tr>
<tr height="20"><td>&nbsp;</td></tr>
</table>
<script type="text/javascript">
<!--

var l=document.getElementsByName("status").length;
var status="<%=ss%>";
if(status!=""){
	for(var i=0;i<l;i++){
		var obj=document.getElementsByName("status")[i];
		obj.checked=(status.indexOf(obj.value)>=0)?true:false;
	}
}

function dayTrack(mobile){
	var d= mini.get("monitorDay"); //getTrimValue('monitorDay');
	var t=d.getFormValue();
	if(t==""){
		alert("请选择跟踪的日期！");
		document.all.monitorDay.focus();
		return false;
	}
	var w=window.open('/gps/gps.do?b_daytrack=true&mobileID='+mobile+'&monitorDay='+t,'gpsDayTrack','width=1000px,height=600px,menubar=0,toolbar=0,directories=0,location=0,status=0,scrollbars=0');	
	w.focus();
	return false;
}

function showMobileStatus(mobile){
	if(mobile=="")	return false;
	var w=window.open('/da/task/status.do?b_query=true&mobile='+mobile,'status','width=800px,height=600px,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no');	
	w.focus();
	return false;
}

function showEmployee(sid){
	var w=window.open('/expressemp/manage.do?b_expressedit=true&emp_sid='+sid,'emp','width=1000px,height=600px,menubar=0,toolbar=0,directories=0,location=0,status=0,scrollbars=1');	
	w.focus();
	return false;
}

function setLocation(idx){
	parent.map.markerClick(idx);
}

function changeAttr(){
	mini.loading("正在刷新数据，请稍后 ...", "刷新");
	window.location.href="/gps/gps.do?b_monitor=true&city="+document.all.city.value+"&usermode="+document.all.usermode.value;
}

function refreshClick(){
	mini.loading("正在刷新数据，请稍后 ...", "刷新");
	return true;
}

function searchAllMobile(){
	var x = '<%=gpsJson%>';
	try{parent.map.init_markers(x,document.all.city.value);}catch(e){}
}
setTimeout("searchAllMobile()",2000);
mini.parse();
//-->
</script>

</html:form>
</body>
</html>
