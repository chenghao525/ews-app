<%@ page contentType="text/html; charset=UTF-8" import="org.apache.struts.util.RequestUtils" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html>
<HEAD><title></title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type=text/css rel=stylesheet>

</HEAD>
<script type="text/javascript" src="/js/js.js?r=1221"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
<body align="center" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" align=center width="100%">

<html:form action="/system/org">
<html:hidden name="orgForm" property="org_sid"/>
<html:hidden name="orgForm" property="org_psid"/>
<TABLE width="100%" border=0 cellpadding="0" cellspacing="0" align=center height=380>

	<tr>
		<td width="20%"></td>
		<td align="center" width="40%">
		<select name="duty" style="width:300" size=20>
			<logic:iterate id="dutylist" name="orgForm" property="dutyList">
				<option value="<bean:write name='dutylist' property='string(sid)'/>_<bean:write name='dutylist' property='string(deptid)'/>_<bean:write name='dutylist' property='string(info_class)'/>">
					<bean:write name="dutylist" property="string(deptname)"/>
				</option>		
			</logic:iterate>
		</select>
		</td>

		<td width="20%">
			<INPUT TYPE="button" class="button-ui" value="&nbsp;上&nbsp;" onclick="up()">
			<br><br><br>
			<INPUT TYPE="button" class="button-ui" value="&nbsp;下&nbsp;"  onclick="down()">
		</td>

		<td width="20%"></td>

	</tr>
 
	<tr>
		<td></td>
		<td align="center">
			<extend:submit StyleClass="button-ui" property="b_savesort" onClick="allSelect()"/><bean:message key="button.save"/></extend:submit>
		</td>
		<td></td>
		<td></td>
	</tr>

</table>
</html:form>	
</body>
</html:html>

<script language="JavaScript">
	function up(){
		var text;
		var textvalue;
		if(document.orgForm.duty.selectedIndex!=0){
			text=document.orgForm.duty.options[document.orgForm.duty.selectedIndex].text;
			textvalue=document.orgForm.duty.options[document.orgForm.duty.selectedIndex].value;
			document.orgForm.duty.options[document.orgForm.duty.selectedIndex].text=document.orgForm.duty.options[document.orgForm.duty.selectedIndex-1].text;
			document.orgForm.duty.options[document.orgForm.duty.selectedIndex].value=document.orgForm.duty.options[document.orgForm.duty.selectedIndex-1].value;

			document.orgForm.duty.options[document.orgForm.duty.selectedIndex-1].text=text;
			document.orgForm.duty.options[document.orgForm.duty.selectedIndex-1].value=textvalue;
			document.orgForm.duty.options[document.orgForm.duty.selectedIndex-1].selected=true;
		}
	}
	
	function down(){
		var text1;
		var textvalue1;
		if(document.orgForm.duty.selectedIndex!=document.orgForm.duty.length-1){
			text1=document.orgForm.duty.options[document.orgForm.duty.selectedIndex+1].text;
			textvalue1=document.orgForm.duty.options[document.orgForm.duty.selectedIndex+1].value;
			document.orgForm.duty.options[document.orgForm.duty.selectedIndex+1].text=document.orgForm.duty.options[document.orgForm.duty.selectedIndex].text;
			document.orgForm.duty.options[document.orgForm.duty.selectedIndex+1].value=document.orgForm.duty.options[document.orgForm.duty.selectedIndex].value;

			document.orgForm.duty.options[document.orgForm.duty.selectedIndex].text=text1;

			document.orgForm.duty.options[document.orgForm.duty.selectedIndex].value=textvalue1;
			document.orgForm.duty.options[document.orgForm.duty.selectedIndex+1].selected=true;
			document.orgForm.duty.options[document.orgForm.duty.selectedIndex].selected=false;	
		}
	}

	function allSelect(){
		var i;
		document.orgForm.duty.multiple=true;
		if(confirm("<bean:message key="alert.editMessage" />")){
			for(i=0;i<document.orgForm.duty.length;i++){
				document.orgForm.duty.options[i].selected=true;	
			}
		}
		else{
			document.orgForm.duty.multiple=false;
		}
	}
</script>