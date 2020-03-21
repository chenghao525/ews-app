<%@ page contentType="text/html; charset=UTF-8" %>
<% String  path=request.getParameter("path");%>
<html>
<head>
<title>请选择上传的文件</title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type=text/css rel=stylesheet>
<script language="JavaScript" src="/js/js.js?r=1221"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
</head> 
<body><br><br>
<table border="0" align="center" cellpadding="0" cellspacing="0">
   <tr>
     <td height="45" align="center" valign="middle">
	 <form action="uploadimage.jsp" method="post" enctype="multipart/form-data" name="form1">
		请选择上传的文件：<input type="file" name="file" ContentEditable="false">&nbsp;<input type="submit" name="Submit" value="上 传"  onClick="return uploadAttach(this.form);"><br><br><b>文件名请<font size="+1" color="red">不要包含汉字</font>，文件大小请不要超过<font size="+1" color="red">2M</font></b>
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
	window.opener.setPhoto('<%=path%>');
   //-->
   </script>
<%}%>
</table>
</body>
</html>
<script type="text/javascript">
<!--
function uploadAttach(theForm){
	var v=strTrim(theForm.file.value);
	if ( v == "" ){
		alert ( "请先选择要上传的文件,点击“浏览”选择本地文件" );
		theForm.file.focus();
		return false ;
	}

	v=v.toUpperCase();
	var isFormat = v.indexOf("\.XLSX") >= 0 || v.indexOf("\.XLS") >= 0;
	if (!isFormat) {
        alert("文件不是Excel格式");
        return false;
    }
	// var idx = v.indexOf("\.XLSX");
	//
	// if(idx > -1){
	// 	alert("请选择xls文件格式");
	// 	return false;
	// }
	// idx=v.indexOf("\.XLS");
	// if(idx==-1){
	// 	alert("请选择xls文件格式");
	// 	return false;
	// }
	return true;
}
//-->
</script>