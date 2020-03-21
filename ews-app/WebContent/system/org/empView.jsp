<%@ page contentType="text/html; charset=UTF-8" import="org.apache.commons.beanutils.PropertyUtils,org.apache.struts.util.RequestUtils,com.corp.frame.eflow.*"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://www.shanghaicorp.com/struts/extend" prefix="extend" %>

<html:html>
<HEAD><title></title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type=text/css rel=stylesheet>  
<script type="text/javascript" src="/js/js.js?r=1221"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
</HEAD>

<body  topmargin=0 leftmargin=0>

<html:form action="/system/org">
<html:hidden name="orgForm" property="org_sid"/>
<html:hidden name="orgForm" property="org_psid"/>
<html:hidden name="orgForm" property="emp_sid"/>
<html:hidden name="orgForm" property="dept_sid"/>
<html:hidden name="orgForm" property="photoPath"/>

<TABLE width="98%" class="detail-table" cellpadding="5" cellspacing="0" align=center>
	<logic:iterate id="info" name="orgForm" property="empInfo">
	<tr bgcolor="#e0e3e8" height=25><td colspan="3" align="center"><b>人员信息</b></td></tr>
	<tr height=25>
		<td width="35%" align="right" bgcolor="f7f7f7">登录账号：</td>
		<td width="45%"><bean:write name="info" property="string(EMPID)"/></td>
		<td width="1%" rowspan="7">
			<img src="<bean:write name="orgForm" property="photoPath"/>" width="180" height="240" border="0" alt="">
		</td>
	</tr>
	<tr height=25>
		<td align="right" bgcolor="f7f7f7">姓名：</td>
		<td><bean:write name="info" property="string(EMPNAME)"/>&nbsp;</td>
	</tr>
 	<tr height=25>
		<td align="right" bgcolor="f7f7f7">性别：</td>
		<td>
			<logic:equal name="info" property="string(SEX)" value="13">男</logic:equal>
			<logic:notEqual name="info" property="string(SEX)" value="13">女</logic:notEqual>
		</td>
	</tr>
	<tr height=25>
		<td align="right" bgcolor="f7f7f7">登录状态：</td>
		<td>
			<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="3" n2="1"/>
			<html:select property="t_INFO_EMPLOYEE.string(EMP_ACTIVE)" size="1" disabled="true">
			<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
			</html:select>
		</td>
	</tr>
	<tr height=25>
		<td align="right" bgcolor="f7f7f7">人员类别：</td>
		<td>
			<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="10" n2="1"/>
			<html:select property="t_INFO_EMPLOYEE.string(EMP_TYPE)" size="1" disabled="true">
			<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
			</html:select>
		</td>
	</tr>
	<tr height=25>
		<td align="right" bgcolor="f7f7f7">职务：</td>
		<td><bean:write name="info" property="string(DUTY)"/>&nbsp;</td>
	</tr>
 	<tr height=25>
		<td align="right" bgcolor="f7f7f7">公司电话：</td>
		<td><bean:write name="info" property="string(COPHONE)"/>&nbsp;</td>
	</tr>
	<tr height=25>
		<td   align="right" bgcolor="f7f7f7">手机：</td>
		<td colspan="2" ><bean:write name="info" property="string(MP)"/>&nbsp;</td>
	</tr>

	<tr height=25>
		<td   align="right" bgcolor="f7f7f7">邮箱：</td>
		<td colspan="2"><bean:write name="info" property="string(EMAIL)"/>&nbsp;</td>
	</tr>
	<tr height=25>
		<td   align="right" bgcolor="f7f7f7">身份证号：</td>
		<td colspan="2"><bean:write name="info" property="string(ID_CARD)"/>&nbsp;</td>
	</tr>
	<tr height=25>
		<td   align="right" bgcolor="f7f7f7">驾驶证号：</td>
		<td colspan="2"><bean:write name="info" property="string(DRIVER_CARD)"/>&nbsp;</td>
	</tr>
	<tr height=25>
		<td align="right" bgcolor="f7f7f7">备注：</td>
		<td colspan="2"><bean:write name="info" property="string(MEMO)"/>&nbsp;</td>
	</tr>
	<tr height=25>
		<td width="100%"   align=center colspan="3">
		<extend:submit StyleClass="button-ui" property="b_empedit" >修改</extend:submit>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<extend:submit StyleClass="button-ui" property="b_empdel" onclick="return delConf();">删除</extend:submit>
		</td>
	</tr>		

</logic:iterate>
</table>
</html:form>

<SCRIPT LANGUAGE="JavaScript">
<!--
function delConf(){return (confirm("您确认删除？"));}

mini.parse();
//-->
</SCRIPT>
</body>
</html:html>