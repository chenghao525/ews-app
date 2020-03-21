<%@ page contentType="text/html; charset=UTF-8" import="org.apache.commons.beanutils.PropertyUtils,org.apache.struts.util.RequestUtils,com.corp.frame.eflow.*"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html:html>
<HEAD>
<title></title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type=text/css rel=stylesheet>
</HEAD>
<%String ckbDisp=(String)RequestUtils.lookup(pageContext, "orgForm", "ckbDisp", null);%>
<%String dept_emp=(String)RequestUtils.lookup(pageContext, "orgForm", "dept_emp", null);%>
<%String button_flag=(String)RequestUtils.lookup(pageContext, "orgForm", "button_flag", null);%>
<%String sort=(String)RequestUtils.lookup(pageContext, "orgForm", "sort", null);%>
<body align="center" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<html:form action="/system/org">
<html:hidden name="orgForm" property="org_sid"/>
<html:hidden name="orgForm" property="org_psid"/>
	<html:hidden name="orgForm" property="idx"/>
	<html:hidden name="orgForm" property="rowID"/>
	<html:hidden name="orgForm" property="divid"/>
	
<SCRIPT language="javascript">
<!--

parent.tree_main.setParaValue("<bean:write name="orgForm" property="chkImg"/>","<bean:write name="orgForm" property="unchkImg"/>");

parent.tree_main.divInnerHTML("<bean:write name="orgForm" property="divid"/>","<bean:write name="orgForm" property="divString" filter="false" />");
	
function submitFunc(divid,rowid){
	document.all.orgForm.rowID.value=rowid;
	document.all.orgForm.divid.value=divid;
	
	document.all.orgForm.action="/system/org.do?b_query=true&ckbDisp=<%=ckbDisp%>&dept_emp=<%=dept_emp%>&button_flag=<%=button_flag%>&sort=<%=sort%>";
	//alert(document.all.orgForm.action);
	document.all.orgForm.submit();
}
//-->
</SCRIPT>

</html:form>
</body>
</html:html>
