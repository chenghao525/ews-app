<%@ page contentType="text/html; charset=utf-8" import="com.corp.basic.Constant,com.corp.frame.core.base.SystemConfig,com.corp.frame.core.base.defaultdriver.UserView"%>
<%
	String sysModel="",titleModel="";
	if(com.corp.basic.Constant.isSystemDebug()){sysModel="<font size='+1' color='blue'>【测试系统】</font>";titleModel="【测试系统】";
	}else{sysModel="<font size='+1' color='red'>【生产系统】</font>";titleModel="【生产系统】";}

	//去掉缓存
	response.setHeader("Pragma","No-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires",0);
	UserView uv = (UserView) session.getAttribute(SystemConfig.getWebUserViewName());
	String empSid=null;
	if(uv==null){
%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<script type="text/javascript">
<!--
top.location.href="../login/loginout.do";
//-->
</script>
<%
		return;
	}else{empSid=uv.getUserId();}
%>  
<HTML>
<HEAD>
<TITLE></TITLE>
<script type="text/javascript" src="/js/js.js?r=1221"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
<LINK href="/images/css.css" type="text/css" rel="STYLESHEET">
	<SCRIPT LANGUAGE="JavaScript">
        //<!--
        function loadCity(){top._cityData=$("#city").contents().find("body").html();}
        parent.setWelcomeInfo("欢迎您，<bean:write name="userView" property="userCNName"/><%=sysModel%>");
        parent.setTitle("EWS管理系统<%=titleModel%>");
        //-->
	</SCRIPT>
</HEAD>
<body  leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%--<iframe id="city" src="/data/CityTreeService.jsp?method=LoadNodes" onload="loadCity()" frameborder="0" name="info" style="width:100%;height:0px;" border="0" scrolling="no"></iframe>--%>
</BODY>
</HTML>

