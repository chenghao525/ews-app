<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://www.shanghaicorp.com/struts/extend" prefix="extend" %>
<html>
<HEAD><title></title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type=text/css rel=stylesheet>
</HEAD>
<%
	//区别人员还是部门的编号
	String dept_emp=request.getParameter("dept_emp");
	//区别是否有确定按钮
	String button_flag=request.getParameter("button_flag");

	//排序参数
//	String sort = request.getParameter("sort");
%>
<script language="JavaScript" src="/js/divmove.js"></script>

<body align="center" leftmargin="2" topmargin="5" marginwidth="0" marginheight="0">
<div id='Dir_0_' noWrap style='display:block'></div>
<%
	if("1".equals(button_flag)){
%>		
		<DIV  align="right" style="width:100%;TOP:80px;POSITION:absolute;" id="ToolsKit">
		<%
			//部门
			if("1".equals(dept_emp)){
		%>
				<INPUT TYPE="submit" value="<bean:message key="button.confirm" />" onclick="return getDeptSel();">
				&nbsp;&nbsp;&nbsp;&nbsp;
		<%
			}else{
		%>		
				<INPUT TYPE="submit" value="<bean:message key="button.confirm" />" onclick="return getEmpSel();">
				&nbsp;&nbsp;&nbsp;&nbsp;	
		<% 		
			}
		%>			
			<INPUT TYPE="button" class="button-ui" value="<bean:message key="button.cancel" />" onclick="parent.close();">
			&nbsp;&nbsp;
			<HR align="right" width="30%">
		</DIV>
<%
	}
%>
<BR>
</body>
</html>
<SCRIPT language="javascript">
<!--
var chkImg_c="",chkImg_u="";

function getDeptSel(){
	var sEmpList,sEmpNameList,sTemp;

	sEmpList="";
	sEmpNameList="";

	for(var i=0;i<document.all.length;i++){	
		sTemp=document.all.item(i).id;

		if(sTemp.indexOf("_chk_")>=0){
			if(document.all.item(i).src.indexOf("/images/check.gif")>=0){
				sTemp=getparentid(sTemp,"0");
				sEmpList+=sTemp+",";
				sEmpNameList+=document.all.item(i+1).value+",";

			}
		}
	}
	
	if(sEmpList==""){
		alert("<bean:message key="alert.choose_dep" />");
		return false;
	}

	sEmpList=sEmpList.substring(0,sEmpList.length-1);
	sEmpNameList=sEmpNameList.substring(0,sEmpNameList.length-1);


	var arr=new Array();
	arr["id"]=sEmpList;
	arr["name"]=sEmpNameList;
	window.returnValue=arr;
	window.close();

	return false;
}

function setDeptDisplay(){
	var sEmpNameList,sTemp;

	sEmpNameList="";

	for(var i=0;i<document.all.length;i++){	
		sTemp=document.all.item(i).id;

		if(sTemp.indexOf("_chk_")>=0){
			if(document.all.item(i).src.indexOf("/images/check.gif")>=0){
				sTemp=getparentid(sTemp,"0");
				sEmpNameList+=document.all.item(i+1).value+",";
			}
		}
	}
	
	sEmpNameList=sEmpNameList.substring(0,sEmpNameList.length-1);
	
	try{
		parent.tree_top.setSelectView(sEmpNameList);
	}catch(e){}
}

function getEmpSel(){
	var sEmpList,sEmpNameList,sTemp;

	sEmpList="";
	sEmpNameList="";
	sEmpMbList="";

	for(var i=0;i<document.all.length;i++){	
		sTemp=document.all.item(i).id;

		if(sTemp.indexOf("_chk_")>=0){
			if(document.all.item(i).src.indexOf("/images/check.gif")>=0){
				sTemp=sTemp.replace("_chk_","");
				sEmpList+=sTemp+",";
				sEmpNameList+=document.all.item(i+1).value+",";

				if(document.all.item(i+2).value!="-")
					sEmpMbList+=document.all.item(i+2).value+",";
			}
		}
	}
	
	if(sEmpList==""){
		alert("<bean:message key="alert.choose_emp" />");
		return false;
	}

	sEmpList=sEmpList.substring(0,sEmpList.length-1);
	sEmpNameList=sEmpNameList.substring(0,sEmpNameList.length-1);
	sEmpMbList=sEmpMbList.substring(0,sEmpMbList.length-1);

	var arr=new Array();
	arr["id"]=sEmpList;
	arr["name"]=sEmpNameList;
	arr["mb"]=sEmpMbList;
	window.returnValue=arr;
	
	window.close();

	return false;
}



function setParaValue(chk,uchk){
	chkImg_c=chk;
	chkImg_u=uchk;
}

function divInnerHTML(objDiv,DivStr){
	//alert(DivStr);

	var obj=eval("document.all."+objDiv);
	if(DivStr==""){
		obj.style.display="none";
		obj.innerHTML="";
	}else{
		obj.innerHTML=DivStr;
	}
}

function turnit(ss,bb,rowid){
	if (ss.style.display=="none"){
		var idx=bb.offsetLeft/16;
		var str="";

		ss.innerHTML="";
		for(var i=0;i<=idx;i++)
			str+="&nbsp;&nbsp;&nbsp;";

		ss.innerHTML="<font style='FONT-SIZE: 9pt;' color='blue'>"+str+"Loading...</font>";
		parent.tree_func.submitFunc(ss.id,rowid);

		ss.style.display="block";
		bb.src="/images/sub.gif";
	}else{
		ss.style.display="none"; 
		bb.src="/images/plus.gif";
	}
	return false;
}

function turnit_chk(obj){
	try{
		var id;
		if(obj.src.indexOf(chkImg_c)>=0){
			obj.src=chkImg_u;
		}	
		else if(obj.src.indexOf(chkImg_u)>=0){
			obj.src=chkImg_c;
			id=obj.id;
			chenck(getparentid(id,0),0);
		}
		setDeptDisplay();
	}catch(e){}
	return false;
}

function chenck(id,tag){

	//定义5层
	if(tag<=5){	
		try{
		for(var i=0;i<document.all.length;i++){	
			sTemp=document.all.item(i).id;

			if(sTemp.indexOf("_chk_")>=0){
				if(getparentid(document.all.item(i).id,"1")==id){
						document.all.item(i).src=chkImg_c;
						tag++;
						chenck(getparentid(document.all.item(i).id,"0"),tag);
					}
				}
			}
		}
		catch(e){}
		return false;
	}
}

function getparentid(id,flag){
	var id,index;
	id=id.replace("_chk_","");
	index=id.indexOf("_");
	//当前节点
	if(flag=="0")
		id=id.substring(index+1);
	//父节点
	else
		id=id.substring(0,index);
	return id;
	//alert(id);
	
}


function turnit_leaf(sid,ii,objA){
	try{
		var i,sTemp;

		for(i=0;i<document.all.length;i++){
			if(document.all.item(i).tagName=="A"){
				document.all.item(i).style.backgroundColor ="";
				document.all.item(i).style.color="";
			}
		}	
		objA.style.backgroundColor ="#000080";
		objA.style.color="#FFFFFF";
		

		if(sid!=null && ii!=null && sid.indexOf("emp_")>=0){
			sid=sid.replace("emp_","");
			sid=sid.replace("_","");
			parent.parent.org_right.location.href="/system/org.do?b_empfind=true&emp_sid="+sid+"&collapseImg="+ii.id;
		}else{
			parent.parent.org_right.location.href="/system/org.do?b_deptfind=true&dept_sid="+sid+"&collapseImg="+ii.id;
		}
	}
	catch(e){}

	return false;
}

function collapseTree(id){
	var tmpObj,tmpObj1,tmpObj2;
	var childObj;
	var temp="";
	var tempid="";
	if(id!=null && id!=""){
		tmpObj=document.getElementById(id);
		
		tmpObj1=getParent(tmpObj,"div",tmpObj.id);
		tmpObj2=getParent(tmpObj1,"div",tmpObj1.id);
		if(tmpObj2==null)
			tmpObj=tmpObj1;
		else
			tmpObj=tmpObj2;

		//alert(tmpObj.id);

		if(tmpObj!=null){
			tempid=tmpObj.id;
			tmpObj=getParent(tmpObj,"div",tmpObj.id);
			if(tmpObj!=null){
				for(var i=0;i<tmpObj.childNodes.length;i++){
					childObj=tmpObj.childNodes.item(i);
					if(childObj.tagName.toLowerCase()=="font"){
						temp=childObj.onclick+"";
						if(temp.indexOf(tempid)>=0){
							childObj.click();
							setTimeout("",100);
							childObj.click();
							return;
						}
					}
				}
			}
		}
	}
}
function test11(){
	alert("tree_main...");
}
//-->
</SCRIPT>



