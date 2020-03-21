<%@ page contentType="text/html; charset=UTF-8" import="org.apache.struts.util.RequestUtils" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://www.shanghaicorp.com/struts/extend" prefix="extend" %>

<% String  dept_sid=request.getParameter("dept_sid");%>
<html:html>
<HEAD><title></title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type=text/css rel=stylesheet>
<script language="JavaScript" src="/js/js.js?r=1221"></script>
</HEAD>

<body align="center" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<html:form action="/system/org">
<html:hidden name="orgForm" property="org_sid"/>
<html:hidden name="orgForm" property="org_psid"/>
<html:hidden name="orgForm" property="dept_sid"/>
<html:hidden name="orgForm" property="t_INFO_DEPT.string(CRITICAL_VALUE)"/>

<logic:iterate id="info" name="orgForm" property="deptInfo">

<!-- 单位信息 -->
<logic:equal name="info" property="string(INFO_CLASS)" value="1">
<TABLE width="98%" class="detail-table" cellpadding="5" cellspacing="0" align=center>	
<tr align="center" bgcolor="#e0e3e8" height=25><td colspan="2"><b>单位部门信息</b></td></tr>
<tr height=25>
	<td align="right"  width="35%" bgcolor="f7f7f7">名称：</td>
	<td width="65%"><html:text property="t_INFO_DEPT.string(DEPTNAME)" size="50" disabled="true" /></td>
</tr>
<tr height=25>
	<td align="right"  width="35%" bgcolor="f7f7f7">单位部门类别</td>
	<td width="65%">
		<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="13" n2="1"/>
		<html:select property="t_INFO_DEPT.string(DEPT_TYPE)" size="1" disabled="true" >
		<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
		</html:select>
	</td>
</tr>

<logic:notEqual name="orgForm" property="dept_sid" value="1"><!-- 根目录没有所在城市 -->
<tr height=25>
	<td align="right"  width="35%" bgcolor="f7f7f7">所在城市:</td>
	<td width="65%">
		<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="161" n2="1"/>
		<html:select property="t_INFO_DEPT.string(PROVINCE)" size="1"  disabled="true"  onchange="getCityArea('16',this.value,'t_INFO_DEPT.string(CITY)',getValue('CITY'));">
		<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
		</html:select>
		<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="16" n2="1"/>
		<html:select property="t_INFO_DEPT.string(DEPT_CITY)" size="1" disabled="true" >
		<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
		</html:select>
	</td>
</tr>
<tr height=25>
	<td align="right"  width="35%" bgcolor="f7f7f7">排列顺序：</td>
	<td width="65%"><html:text property="t_INFO_DEPT.string(DEPTRANK)" size="5" disabled="true"/></td>
</tr>
</logic:notEqual>

<tr height=25><td align="center" colspan="2">
	<extend:submit StyleClass="button-ui" property="b_deptadd" onclick="addChild();">新增子单位/子部门</extend:submit>
	&nbsp;&nbsp;
	<extend:submit StyleClass="button-ui" property="b_dutyadd" >新增职务</extend:submit>
	<logic:notEqual name="orgForm" property="dept_sid" value="1"><!-- 根目录没有修改、删除和增和业务人员 -->
		&nbsp;&nbsp;
		<extend:submit StyleClass="button-ui" property="b_deptedit" onclick="editDept();" >修改</extend:submit>
		&nbsp;&nbsp;
		<extend:submit StyleClass="button-ui" property="b_deptdel" onclick="return delConf();">删除</extend:submit>　
	</logic:notEqual>
</td></tr>
</table>

</logic:equal>

<!-- 职务信息 -->
<logic:equal name="info" property="string(INFO_CLASS)" value="2">
<TABLE width="98%" class="detail-table" cellpadding="5" cellspacing="0" align=center>	
<tr bgcolor="#e0e3e8" height=25><td colspan="2" align="center"><b>职务信息</b></td></tr>
<tr height=25>
	<td align="right"  width="35%" bgcolor="f7f7f7">名称：</td>
	<td width="65%"><bean:write name="info" property="string(DEPTNAME)"/>　</td>
</tr>
<tr height=25>
	<td align="right" bgcolor="f7f7f7">级别：</td>
	<td>
		<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="1" n2="1"/>
		<html:select property="t_INFO_DEPT.string(DUTY_CLASS)" size="1" disabled="true" >
		<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
		</html:select>
	</td>
</tr>
<tr height=25>
	<td align="right"  width="35%" bgcolor="f7f7f7">排列顺序：</td>
	<td width="65%"><bean:write name="info" property="string(DEPTRANK)"/>&nbsp;</td>
</tr>

<tr height=25><td align="center" colspan="2">
	<input type="button" value="新增人员" StyleClass="button-ui" onclick="return add();"></input>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<extend:submit StyleClass="button-ui" property="b_dutyedit" >修改</extend:submit>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<extend:submit StyleClass="button-ui" property="b_dutydel" onclick="return delConf();">删除</extend:submit>　
</td></tr>
</table>
</logic:equal>
</logic:iterate>

</html:form>

<SCRIPT LANGUAGE="JavaScript">
<!--
function delConf(){
	if(confirm("您确定删除吗？")){
		if(document.all("t_INFO_DEPT.string(DEPT_CITY)")!=null)
			document.all("t_INFO_DEPT.string(DEPT_CITY)").disabled=false;
		return true;
	}else{
		return false;
	}
}

function addChild(){
	document.all("t_INFO_DEPT.string(DEPT_TYPE)").disabled=false;
	document.all("t_INFO_DEPT.string(PROVINCE)").disabled=false;
	document.all("t_INFO_DEPT.string(DEPT_CITY)").disabled=false;
	return true;
}
function editDept(){
	document.all("t_INFO_DEPT.string(DEPTNAME)").disabled=false;
	document.all("t_INFO_DEPT.string(DEPT_TYPE)").disabled=false;
	document.all("t_INFO_DEPT.string(DEPT_CITY)").disabled=false;
	document.all("t_INFO_DEPT.string(PROVINCE)").disabled=false;
	document.all("t_INFO_DEPT.string(DEPTRANK)").disabled=false;
	return true;

}
function add(){
	var sid="",psid='<%=dept_sid%>';
	document.location.href="/system/user/userDetail.jsp?org_sid="+sid+"&org_psid="+psid+"&type=add";
	

}
//-->
</SCRIPT>

</body>
</html:html>