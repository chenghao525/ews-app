<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,com.corp.richtext.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	RtHelper rh=new RtHelper();
	RtBO rbo=new RtBO();

	String sid = request.getParameter("sid") != null ? request.getParameter("sid") : "";//
	String type = request.getParameter("type") != null ? request.getParameter("type") : "";//
	
	rbo.setId(sid);
	rbo.setType(type);
	String htmlData=rh.getContent(rbo);
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>编辑器</title>
	<link rel="stylesheet" href="../themes/default/default.css" />
	<link rel="stylesheet" href="../plugins/code/prettify.css" />
	<script charset="utf-8" src="../kindeditor.js"></script>
	<script charset="utf-8" src="../lang/zh_CN.js"></script>
	<script charset="utf-8" src="../plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content1"]', {
				cssPath : '../plugins/code/prettify.css',
				uploadJson : '../jsp/upload_json.jsp',
				fileManagerJson : '../jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['editor'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['editor'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
</head>
<body>
	<form name="editor" method="post" action="saveHtml.jsp">
		<input type="hidden" name="sid" value="<%=sid%>">
		<input type="hidden" name="type" value="<%=type%>">
		<textarea name="content1" cols="100" rows="8" style="width:760px;height:400px;visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea>
		<br />
		<input type="submit" name="button" value="保存内容" /> (保存快捷键: Ctrl + Enter)
	</form>
</body>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>