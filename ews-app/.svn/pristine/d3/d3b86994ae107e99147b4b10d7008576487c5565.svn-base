<%@ page contentType="text/html; charset=UTF-8" import="org.apache.commons.beanutils.PropertyUtils,org.apache.struts.util.RequestUtils,com.corp.frame.eflow.*" import="org.apache.struts.util.RequestUtils" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<html:html>
<HEAD><title></title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type=text/css rel=stylesheet>
</HEAD>

<%
	String dwImg_o=(String)RequestUtils.lookup(pageContext, "orgForm", "dwImg_open", null);
	String dwImg_c=(String)RequestUtils.lookup(pageContext, "orgForm", "dwImg_close", null);
	String bmImg_o=(String)RequestUtils.lookup(pageContext, "orgForm", "bmImg_open", null);
	String bmImg_c=(String)RequestUtils.lookup(pageContext, "orgForm", "bmImg_close", null);

	String strDirSID=(String)RequestUtils.lookup(pageContext, "orgForm", "transInfo", null);
	if(strDirSID==null || strDirSID=="")
		strDirSID="1";

%>

<body align="center" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<html:form action="/system/org">
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr><td height=10></td></tr>
		<tr><td align=top>
			<bean:write name="orgForm" property="divString" filter="false"/>
		</td></tr>
	</table>
</html:form>
	
<SCRIPT language="javascript">
<!--
var sInfo="";
sInfo="<%=strDirSID%>";

function turnit(ss,bb)
{
	if (ss.style.display=="none") 
	{
		ss.style.display="block";
		bb.src="/images/sub.gif";
	}
	else
	{
		ss.style.display="none"; 
		bb.src="/images/plus.gif";
	}
	return false;
}


function turnit_leaf(sid,ii,objA)
{
	var i,sTemp;

	for(i=0;i<document.all.length;i++)
	{
		if(document.all.item(i).tagName=="IMG")
		{
			sTemp=document.all.item(i).src;
			//alert(sTemp);
			if(sTemp.indexOf("<%=dwImg_o%>")>=0)
				document.all.item(i).src="<%=dwImg_c%>";

			if(sTemp.indexOf("<%=bmImg_o%>")>=0)
				document.all.item(i).src="<%=bmImg_c%>";
		}
		if(document.all.item(i).tagName=="A")
		{
			document.all.item(i).style.backgroundColor ="";
			document.all.item(i).style.color="";
		}
	}	
	objA.style.backgroundColor ="#000080";
	objA.style.color="#FFFFFF";

	if(ii.src.indexOf("<%=dwImg_c%>")>=0)
		ii.src="<%=dwImg_o%>";

	if(ii.src.indexOf("<%=bmImg_c%>")>=0)
		ii.src="<%=bmImg_o%>";
	
	if(sid.indexOf("emp_")>=0)
	{
		sid=sid.replace("emp_","");
		sid=sid.replace("_","");
		parent.org_right.location.href="/system/org.do?b_empfind=true&emp_sid="+sid;
	}
	else
	{
		parent.org_right.location.href="/system/org.do?b_deptshow=true&dept_sid="+sid;
	}	
	return false;
}

function leaf_dbl(sid,ii,objA,dirObj,tagObj)
{	
	turnit_leaf(sid,ii,objA);
	turnit(dirObj,tagObj);	
	return false;
}

function getParent(el, pTagName,id) 
{
	if (el == null) return null;	
	//alert(el.tagName);
	else if (el.nodeType == 1 && el.tagName.toLowerCase() == pTagName.toLowerCase() &&  el.id!=id )
		return el;
	else
		return getParent(el.parentElement, pTagName,id);
}
//-->
</SCRIPT>


<SCRIPT LANGUAGE="JavaScript">
<!--

function DisplayDir(dirsid)
{
	if(dirsid!=null)
	{
		var i,sTemp="",sID="";
		
		sID="$"+String(dirsid)+"$";
		
		for(i=0;i<document.all.length;i++)
		{
			if(document.all.item(i).tagName=="A")
			{	
				sTemp="$"+String(document.all.item(i).id)+"$";

				if(sID.indexOf(sTemp)>=0)
				{
					document.all.item(i).click();
					
					var obj=document.all.item(i);
					obj=getParent(obj,"DIV",obj.id);
					sTemp=obj.id;

					while(sTemp!="Dir_1_")
					{
						sTemp=sTemp.replace("Dir","Tag");

						var tagObj=eval("document.all."+sTemp);
						if(tagObj!=null)
						{
							obj.style.display="block";
							tagObj.src="/images/sub.gif";
						}
						else
							obj.style.display="block";

						obj=getParent(obj,"DIV",obj.id);
						sTemp=obj.id;
					}
					break;
				}
			}
		}
	}
	return false;
}

DisplayDir(sInfo);

//-->
</SCRIPT>

</body>
</html:html>