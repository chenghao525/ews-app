<%@ page contentType="text/html; charset=UTF-8" import="com.corp.basic.Constant"%>
<% String  path=request.getParameter("path");%>
<html>
<head>
<title>请选择上传的图片</title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type=text/css rel=stylesheet>
<script language="JavaScript" src="/js/js.js?r=1221"></script>
<script type="text/javascript" src="/js/sort.js"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
</head> 
<body><br><br>
<table border="0" align="center" cellpadding="0" cellspacing="0">
   <tr>
     <td height="45" align="center" valign="middle">
	 <form action="uploadimage.jsp" method="post" enctype="multipart/form-data" name="form1">
		请选择上传的图片：<input type="file" name="file" ContentEditable="false">&nbsp;<input type="submit" name="Submit" value="上 传"  onClick="return fn_CheckAttach(this.form);"><br><br><b>文件名请不要包含汉字，文件大小请不要超过2M</b>
     </form></td>
   </tr>
<%
	if(path!=null && path!=""){
%>
   <tr>
		<td align="center">上传文件成功&nbsp;&nbsp;&nbsp;<INPUT TYPE="button" class="button-ui" value="关 闭" onclick="window.close();"></td>
   </tr>
   <script type="text/javascript">
   <!--
	window.opener.setPhoto('<%=(Constant.getBaseUrl()+path)%>');
   //-->
   </script>
<%}%>
</table>
</body>
</html>
<script type="text/javascript">
<!--
function fn_CheckAttach(theForm){
	var v=strTrim(theForm.file.value);
	if ( v == "" ){
		alert ( "请先选择要上传的文件,点击“浏览”选择本地文件" );
		theForm.file.focus();
		return false ;
	}

	v=v.toUpperCase();
	if((v.indexOf("\.GIF") == -1) && (v.indexOf("\.JPG") == -1) && (v.indexOf("\.JPEG") == -1) && (v.indexOf("\.PNG") == -1) && (v.indexOf("\.BMP") == -1)){
		alert("请选择图像文件格式");
		return false;
	}
	return true;
}
//-->
</script>