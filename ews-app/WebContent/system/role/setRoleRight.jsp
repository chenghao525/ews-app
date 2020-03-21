<%@ page contentType="text/html; charset=UTF-8" import="org.apache.struts.util.RequestUtils,com.corp.oa.util.G"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://www.shanghaicorp.com/struts/extend" prefix="extend" %>
<HTML><HEAD>
<script language="JavaScript" src="/js/js.js?r=1221"></script>
<TITLE></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type="text/css" rel="STYLESHEET"> 
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
</HEAD> 
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" WIDTH="100%"> 

<html:form action="/role/roleedit">
<TABLE width="97%" class="detail-table" cellpadding="5" cellspacing="0" align=center>
	<tr bgcolor="#e0e3e8" height=30>
		<td colspan="2" align="center"><b>角色管理</b></td>
	</tr>
	<tr bgcolor="f7f7f7" height=30>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;
		角色名称：
		<logic:equal name="roleForm" property="display" value="find">
			<bean:write name="roleForm" property="rolename" />
		</logic:equal>
		<logic:notEqual name="roleForm" property="display" value="find">
			<html:text property="rolename" readonly="false" style="width:600px;" />
		</logic:notEqual>
		<html:hidden name="roleForm" property="display"/>
		<html:hidden name="roleForm" property="roleid"/>
		</td>
	</tr>

	<tr bgcolor="f7f7f7"><td>
		<TABLE align=center cellPadding=3 class="detail-table-none" cellSpacing=1 width="95%">
			<tr><td>
			<logic:notEqual name="roleForm" property="display" value="find">
				&nbsp;&nbsp;&nbsp;&nbsp;<INPUT TYPE="button" class="button-ui" value="全选" onclick="selectAll()">&nbsp;&nbsp;<INPUT TYPE="button" class="button-ui" value="清空" onclick="selectNone()">
				</TD>
				<td align="right">
					<extend:submit StyleClass="button-ui" property="b_submit" onclick="return saveRight();">保存</extend:submit>
					&nbsp;&nbsp;
			</logic:notEqual>
				<INPUT TYPE="button" class="button-ui" name="b_back" value="返回" onclick="history.back()">
			</td></tr>

			<tr><td align="center" valign="middle" width="100%" colspan="2">
				<TABLE align=center class="detail-table" cellPadding=0 border="0" cellSpacing=0 width="95%">
<%
	int	i=0;
	int j=0;
	String rightid="";

	String lang=(String)session.getAttribute("LangImg");
%>
			<logic:iterate id="allright" name="roleForm" property="rightlist">
				<logic:equal name="allright" property="string(isParent)" value="Y">
<%
					j=0;
%>
					<%-- 根节点的情况 --%>
					<tr height="30" bgcolor="#FFFF66">
						<td colspan="7">
							&nbsp;<b><bean:write name="allright" property="string(rightname)"/></b>
						</td>
					</tr>
				</logic:equal>

				<logic:equal name="allright" property="string(isParent)" value="N">
				
				<%-- 不是根节点的情况 --%>
<%
	rightid=(String)RequestUtils.lookup(pageContext, "allright", "string(rightid)", null);
	rightid=rightid.substring(5,8);

	if("000".equals(rightid)){//子菜单
		if(i!=0){
			i=0;
%>
			</tr>
<%
		}
		if(j!=0){
%>
					<tr height="5"><td colspan="7">&nbsp;&nbsp;</td></tr>
<%
		}
		j++;
%>
					<tr height="25">
						<td width="40">&nbsp;</td>
						<td colspan="6">
						<logic:equal name="allright" property="string(ismyright)" value="Y">
						<%-- 当前用户拥有此权限 --%>
							<input type="checkbox" style="background-color:#f7f7f7;cursor:hand;" name="rolerightlist" value="<bean:write name="allright" property="string(rightid)"/>" checked	<logic:equal name="roleForm" property="display" value="find">disabled</logic:equal>><B><bean:write name="allright" property="string(rightname)"/></B>
						</logic:equal>

						<logic:equal name="allright" property="string(ismyright)" value="N">
						<%-- 当前用户没有此权限 --%>
							<input type="checkbox" style="background-color:#f7f7f7;cursor:hand;" name="rolerightlist" value="<bean:write name="allright" property="string(rightid)"/>" <logic:equal name="roleForm" property="display" value="find">disabled</logic:equal>><B><bean:write name="allright" property="string(rightname)"/></B>
						</logic:equal>
						</td>
					</tr>
<%
	}else{//子菜单中的操作功能
		if(i==4){
%>
					</tr>
<%
			i=0;
		}
		if(i==0 || i==5){//开始新行
%>
					<tr height="25">
						<td width="40">&nbsp;</td><td width="40">&nbsp;</td>
<%
		}
%>
						<td>
						<logic:equal name="allright" property="string(ismyright)" value="Y">
						<%-- 当前用户拥有此权限 --%>
							<input type="checkbox" style="background-color:#f7f7f7;cursor:hand;" name="rolerightlist" value="<bean:write name="allright" property="string(rightid)"/>" checked	<logic:equal name="roleForm" property="display" value="find">disabled</logic:equal>><bean:write name="allright" property="string(rightname)"/>

						</logic:equal>
						<logic:equal name="allright" property="string(ismyright)" value="N">
						<%-- 当前用户没有此权限 --%> 
							<input type="checkbox" style="background-color:#f7f7f7;cursor:hand;" name="rolerightlist" value="<bean:write name="allright" property="string(rightid)"/>"<logic:equal name="roleForm" property="display" value="find">disabled</logic:equal>><bean:write name="allright" property="string(rightname)"/>

						</logic:equal>
						</td>
<%
		i++;
	}
%>
			
				</logic:equal>
			</logic:iterate>
				</table>
			</td></tr>
		</table>
		<BR>
	</td></tr>
</table>
</html:form>

</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
<!--
function saveRight(){
	return confirm("确认保存？");
}

function selectAll(){
	for(var i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="checkbox"){
			document.all.item(i).checked = true;
		}
	}
}

function selectNone(){
	for(var i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="checkbox"){
			document.all.item(i).checked = false;
		}	
	}
}

//-->
</SCRIPT>