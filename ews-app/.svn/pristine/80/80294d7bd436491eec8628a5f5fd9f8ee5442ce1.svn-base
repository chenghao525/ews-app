<%@ page contentType="text/html; charset=UTF-8" import="org.apache.commons.beanutils.PropertyUtils,org.apache.struts.util.RequestUtils,com.corp.frame.eflow.*"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://www.shanghaicorp.com/struts/extend" prefix="extend" %>

<html:html>
<HEAD><title></title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type=text/css rel=stylesheet>
</HEAD>
<script type="text/javascript" src="/js/js.js?r=1221"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
<body align="center" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%
	boolean bAdd=true;
	String dutyClass;
	dutyClass=(String)RequestUtils.lookup(pageContext, "orgForm", "dutyClass", null);
%>
<html:form action="/system/org">
<html:hidden name="orgForm" property="org_sid"/>
<html:hidden name="orgForm" property="org_psid"/>
<html:hidden name="orgForm" property="dept_sid"/>
<html:hidden name="orgForm" property="emp_sid"/>
<html:hidden name="orgForm" property="deptAdd"/>
<html:hidden name="orgForm" property="t_INFO_EMPLOYEE.string(CRITICAL_VALUE)"/>
<html:hidden name="orgForm" property="t_INFO_EMPLOYEE.string(EXPRESS_COMPANY_ID)"/>
<html:hidden name="orgForm" property="photoPath"/>

<logic:equal name="orgForm" property="deptAdd" value="false">
<%
	bAdd=false;
	dutyClass=(String)RequestUtils.lookup(pageContext, "orgForm", "t_INFO_EMPLOYEE.string(DUTYCLASS)", null);
%>
</logic:equal>
<TABLE width="98%" class="detail-table" cellpadding="5" cellspacing="0" align=center>	

	<tr bgcolor="#e0e3e8" height=25><td colspan="3" align="center"><b>人员信息</b></td></tr>
	<tr height=25>
		<td align="right" bgcolor="f7f7f7" width="35%">登录账号：</td>
		<td width="45%"><html:text property="t_INFO_EMPLOYEE.string(EMPID)" size="50" readonly="<%=!bAdd%>"/>&nbsp;<font color="red">*</font></td>
		<td align="center" width="1%" rowspan="8">
			<img id="empPhoto" src="<bean:write name="orgForm" property="photoPath"/>" width="180" height="240" border="0" alt="">
			<br><INPUT TYPE="button" class="button-ui" value="上传相片" onclick="openPhotoUpload();">
		</td>
	</tr>

	<tr height=25>
		<td align="right" bgcolor="f7f7f7">姓名：</td>
		<td><html:text property="t_INFO_EMPLOYEE.string(EMPNAME)" size="50"/>&nbsp;<font color="red">*</font>　</td>
	</tr>
	<tr height=25>
		<td align="right" bgcolor="f7f7f7">性别：</td>
		<td>			
		<html:select property="t_INFO_EMPLOYEE.string(SEX)" size="1">
		<html:optionsCollection property="sexList" label="string(CODENAME)" value="string(CODENO)"/>
		</html:select> 　
		</td>
	</tr>
	<tr height=25>
		<td align="right" bgcolor="f7f7f7">登录状态：</td>
		<td>
			<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="3" n2="1"/>
			<html:select property="t_INFO_EMPLOYEE.string(EMP_ACTIVE)" size="1">
			<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
			</html:select>		
		</td>
	</tr>
	<tr height=25>
		<td align=right bgcolor="f7f7f7">所属公司：</td>
		<td>
			<input id="company" name="EXPRESS_COMPANY_ID" class="mini-combobox" valueField="EXPRESS_COMPANY_ID"
				   style="width: 200px" textField="EXPRESS_COMPANY_NAME" idField="EXPRESS_COMPANY_ID" allowInput="true">
		</td>
	</tr>
	<tr height=25>
		<td align=right bgcolor="f7f7f7">人员类别：</td>
		<td>
			<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="10" n2="1"/>
			<html:select property="t_INFO_EMPLOYEE.string(EMP_TYPE)" size="1">
			<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
			</html:select>
		</td>
	</tr>
	<logic:equal name="orgForm" property="deptAdd" value="false">
	<tr height=25>
		<td align="right" bgcolor="f7f7f7">职务：</td>
		<td>			
			<html:select property="t_INFO_EMPLOYEE.string(DUTY)" size="1">
			<html:optionsCollection property="orgList" label="string(CODENAME)" value="string(CODENO)"/>
			</html:select> 　
		</td>
	</tr>
	</logic:equal>

	<tr height=25>
		<td align="right" bgcolor="f7f7f7">单位电话：</td>
		<td><html:text property="t_INFO_EMPLOYEE.string(COPHONE)" size="50"/>　</td>
	</tr>
	<tr height=25>
		<td align="right" bgcolor="f7f7f7">手机：</td>
		<td ><html:text property="t_INFO_EMPLOYEE.string(MP)" size="50"/>　</td>
	</tr>
	<logic:equal name="orgForm" property="deptAdd" value="false">
	<tr height=25>
		<td align="right" bgcolor="f7f7f7">邮箱：</td>
		<td colspan="2"><html:text property="t_INFO_EMPLOYEE.string(EMAIL)" size="50"/>　</td>
	</tr>
	</logic:equal>
	<logic:equal name="orgForm" property="deptAdd" value="true">
	<tr height=25>
		<td align="right" bgcolor="f7f7f7">邮箱：</td>
		<td><html:text property="t_INFO_EMPLOYEE.string(EMAIL)" size="50"/>　</td>
	</tr>
	</logic:equal>
	<tr height=25>
		<td align=right bgcolor="f7f7f7">身份证号：</td>
		<td colspan="2"><html:text property="t_INFO_EMPLOYEE.string(ID_CARD)" size="50"/></td>
	</tr>
	<tr height=25>
		<td align=right bgcolor="f7f7f7">驾驶证号：</td>
		<td colspan="2"><html:text property="t_INFO_EMPLOYEE.string(DRIVER_CARD)" size="50"/></td>
	</tr>
	<tr height=25>
		<td align="right" bgcolor="f7f7f7">备注：</td>
		<td colspan="2"><html:textarea property="t_INFO_EMPLOYEE.string(MEMO)" rows="3" cols="50"/>　</td>
	</tr>

	<tr height=25>
		<td align="center" colspan="3">
			<extend:submit StyleClass="button-ui" property="b_empsave" onclick="return checkData();">保存</extend:submit>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<INPUT TYPE="button" class="button-ui" value="取消" onclick="history.back();">
		</td>
	</tr>

</table>

</html:form>
	
</body>


<SCRIPT LANGUAGE="JavaScript">
<!--
mini.parse();
var company = mini.get("company");

function checkData(){

	if(getValue('t_INFO_EMPLOYEE.string(EMPID)')==""){
		alert("登录账号必须填写");
		return false;
	}

	if(getValue('t_INFO_EMPLOYEE.string(EMPNAME)')==""){
		alert("姓名必须填写");
		return false;
	}

	if("false"=="<%=bAdd%>"){
		if(getValue('t_INFO_EMPLOYEE.string(DUTY)').indexOf("_1_")>=0){
			alert("职务必须填写");	   <!--请选择正确的职务信息！-->
			return false;
		}
	}
    var v = company.getValue();
    setValue("t_INFO_EMPLOYEE.string(EXPRESS_COMPANY_ID)", v);

	return confirm("您确认保存吗？");	   <!--您确认保存数据信息吗？-->
}

function openPhotoUpload(){
	var w=window.open("/file/imageUpload/upload.jsp","upload",'width=500px,height=280px,menubar=0,toolbar=0,directories=0,location=0,status=0,scrollbars=1');
	w.focus();
}
function setPhoto(path){
	setValue("photoPath",path);
	document.all.empPhoto.src=path;
}


function getExpressCompany() {
	ajaxRequest("/AjaxService?method=ExpressCompanyList", null, function (res) {
		if (res) {
		    var list = mini.decode(res);
            company.setData(list);
		}
    });
}
getExpressCompany();
company.setValue("<bean:write name="orgForm" property="t_INFO_EMPLOYEE.string(EXPRESS_COMPANY_ID)" />");



//-->
</SCRIPT>
</html:html>