<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.text.SimpleDateFormat,java.io.FileOutputStream,java.io.Writer,java.io.OutputStreamWriter,com.corp.richtext.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";//
	String sid=request.getParameter("sid") != null ? request.getParameter("sid") : "";//
	String type=request.getParameter("type") != null ? request.getParameter("type") : "";//
	
	RtBO rbo=new RtBO();
	RtHelper rh=new RtHelper();
	
	if(!"".equals(htmlData)){//说明有内容
		//保存成html文件
		//文件保存目录路径
		String savePath = pageContext.getServletContext().getRealPath("/") + "kindeditor/html/";

		//文件保存目录URL
		String saveUrl  = request.getContextPath() + "/kindeditor/html/";
		
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			out.println(("上传目录不存在。"));
			return;
		}
		//检查目录写权限
		if(!uploadDir.canWrite()){
			out.println(("上传目录没有写权限。"));
			return;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + ".html";
		
		FileOutputStream fos = new FileOutputStream(savePath+newFileName);
		
		saveUrl +=newFileName;
		
		Writer outer = new OutputStreamWriter(fos, "utf-8");
		outer.write(htmlData);
		outer.close();
		fos.close();

		//相关信息保存到数据库中
		rbo.setId(sid);
		rbo.setType(type);
		rbo.setPath(saveUrl);
		rh.saveMapInfo(rbo);
	}else{
		//删除富文本的路径信息
		rbo.setId(sid);
		rbo.setType(type);
		rbo.setPath("");
		rh.saveMapInfo(rbo);
	}
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>正在保存</title>
	<link rel="stylesheet" href="../themes/default/default.css" />
	<link rel="stylesheet" href="../plugins/code/prettify.css" />
</head>
<body>
	<div align="center" style="width:100%"><b>保存成功。以下是预览</b></div><br>
	<%=htmlData%>
</body>
</html>
