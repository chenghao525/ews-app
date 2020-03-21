<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://www.shanghaicorp.com/struts/extend" prefix="extend"%>
<html:html>
<HEAD>
<script type="text/javascript" src="/js/sort.js"></script>
<script type="text/javascript" src="/js/js.js?r=1221"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
<LINK href="/images/css.css" type="text/css" rel="STYLESHEET">
</HEAD>
<body align="center" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="/system/log"> 
<SCRIPT LANGUAGE="JavaScript">

</SCRIPT>
<%
	String upload="";
	upload=application.getRealPath("upload")+"\\";
	Date currentdate=new Date();
	java.text.DateFormat d=java.text.DateFormat.getDateInstance();
	String dd=d.format(currentdate);
%>
<input type="hidden" name="path" value=<%=upload%> > 
<BR><BR><BR>
<TABLE width="98%" bordercolorlight="#cccccc" bordercolordark="#FFFFFF" border=0 cellpadding="5" cellspacing="0" align=center>
	<tr height=25>
		<td width="85%">
			<bean:message key="date.startdate" />:&nbsp;
			<html:text styleId="starttime" property="starttime" styleClass="mini-datepicker" style="width:120px;vertical-align:bottom;"/>&nbsp;&nbsp;&nbsp;<bean:message key="date.to" />&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="date.enddate" />:
			<html:text styleId="endtime" property="endtime" styleClass="mini-datepicker" style="width:120px;vertical-align:bottom;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

			<logic:equal name="logForm" property="isdel" value="false">
				<extend:submit StyleClass="button-ui" property="b_expdata" onclick="return checkData();"><bean:message key="button.educe"/></extend:submit> &nbsp;&nbsp;&nbsp;&nbsp;
				<INPUT TYPE="button" class="button-ui" name="b_back" value="<bean:message key="button.back"/>" onclick="history.back();">
			</logic:equal>
			<logic:equal name="logForm" property="isdel" value="true">
				<extend:submit StyleClass="button-ui" property="b_deldata" onclick="return checkDel();"><bean:message key="button.del"/></extend:submit>&nbsp;&nbsp;&nbsp;&nbsp;
				<INPUT TYPE="button" class="button-ui" name="b_back" value="<bean:message key="button.back"/>" onclick="history.back();">
			</logic:equal>
			
		</td>
	<html:hidden name="logForm"	property="exp"/>
	</tr>
</table>
<html:hidden name="logForm"	property="isdel"/>
</html:form>
</body>
</html:html>
<SCRIPT LANGUAGE="JavaScript">
<!--
function check(){
	var d= mini.get("starttime"); 
	var t=d.getFormValue();

	if(""==t){
	   alert("<bean:message key="date.startdate" /><bean:message key="alert.isnotnull" />");
	   return false;
	}
	setValue("starttime",t);

	d= mini.get("endtime"); 
	t=d.getFormValue();
	if(""==t){
	   alert("<bean:message key="date.enddate" /><bean:message key="alert.isnotnull" />");
	   return false;
	}
	setValue("endtime",t);

	return true;
}
function checkData(){
	var tem = check();
	return tem;
}

function checkDel()   
{  
	var tem = check();
	if(false==tem){
		return false;
	}else{
		var tem= delLog('<bean:message key="alert.del_before_exp" />');
		if(tem==6){//是 
			document.all.exp.value="true";
			return true;
		}else if (tem==7){//否 
			document.all.exp.value="false";
			return true;
		}else{//取消
		   return false;
		}
	}
	
}
 
Function delLog(str)   
  delLog=msgbox(str,3)   
  //是 6   
  //否 7   
  //取消 2   
end function   
</SCRIPT>