<%@ page contentType="text/html; charset=UTF-8" import="com.corp.basic.Constant"%>
<html>
<HEAD><title>图片管理</title>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="/js/js.js?r=1221"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
<link rel="stylesheet" href="/viewer/dist/viewer.css">
<link rel="stylesheet" href="/viewer/css/main.css">
<%
	String id=(String)request.getParameter("id");
	String type=(String)request.getParameter("type");
	String read=(String)request.getParameter("read");
	if(read==null) read="false";
%>
<body align="center" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<input type="hidden" id="IMG_COUNT" name="IMG_COUNT" value="0">

<TABLE width="90%" class="detail-table-none" cellpadding="5" cellspacing="0" align="center"><tr><td>
<%	if(!"true".equals(read)){%>
	&nbsp;&nbsp;&nbsp;&nbsp;<a href="#0" onclick="openUpload()">&raquo;新增图片</a>
<%	}%>
</td></tr></TABLE>
<TABLE width="90%" class="detail-table" cellpadding="5" cellspacing="0" align="center"><tr><td id="pic"></td></tr></TABLE>

<SCRIPT LANGUAGE="JavaScript">
mini.parse();
var _msgboxId=null;

function setPictures(r){

	setValue("IMG_COUNT","0");
	$('#pic').html("无图片");
	if(_msgboxId!=null){mini.hideMessageBox(_msgboxId);_msgboxId=null;}

	var data;
	try {data = eval(r);} catch (e) {return;}
	if (data.length == undefined || data.length < 1) {return;}
	
	setValue("IMG_COUNT",data.length+"");

	var tab='<div><ul class="docs-pictures images">';
	for(var i=0;i<data.length;i++){
		tab+='<li><img data-original="'+data[i].PATH+'" src="'+data[i].PATH+'" height="84">';
		<%	if(!"true".equals(read)){%>
		tab+="<br><a style='cursor:hand;' onclick='delPic(\""+data[i].SID+"\")'>&raquo;删除</a>";
		<%	}%>
		tab+='</li>';
	}
	tab+="</ul></div>";
	$('#pic').html(tab);
	$('.images').viewer();
}

function delPic(sid){
	mini.confirm("确认删除？","请确认",function(action){
		if (action == "ok"){
			ajaxRequest("/AjaxService?method=ImDelPicture",sid,loadPictures);
		}
	});
}
function loadPictures(){
	var json={};
	json.id="<%=id%>";
	json.type="<%=type%>";

	$('#pic').html('正在加载...');
	ajaxRequest("/AjaxService?method=ImGetPictures",json2str(json),setPictures);
}
function openUpload(){
	var w=window.open("/file/imageUpload/upload.jsp","upload",'width=500px,height=280px,menubar=0,toolbar=0,directories=0,location=0,status=0,scrollbars=1');
	w.focus();
	return false;
}
function setPhoto(path){
	var json={};
	json.PARENT_ID="<%=id%>";
	json.INFO_TYPE="<%=type%>";
	json.PATH=path;
	json.PIC_NAME="上传图片";
	_msgboxId=mini.loading("正在提交数据，请稍后 ...", "提交");
	ajaxRequest("/AjaxService?method=ImSavePicture",json2str(json),loadPictures);
}
function getData(){return nvl(getValue('IMG_COUNT'));}

loadPictures();
//-->
</SCRIPT>
<script src="/viewer/dist/viewer.js"></script>
<script src="/viewer/js/main.js"></script>
</body>
</html>