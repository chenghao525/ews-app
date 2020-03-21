<%@ page contentType="text/html; charset=UTF-8" import="org.apache.struts.util.RequestUtils" %>
<%@ page import="java.util.*,com.jspsmart.upload.*,com.corp.oa.util.G,java.io.File" %>
<%@ page import="com.corp.basic.Constant" %>
<%
//String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 
	String imgPath="/upload/";
	String absPath = null;
	String absPath2 = null;
	// 文件上传路径
	if (Constant.isSystemDebug()) {
		absPath=  "D:/ews/ews/upload";
		absPath2= "d:/ews/ews/upload";
	} else {
		absPath=  "D:/ews/ews-app/upload";
		absPath2= "D:/ews/ews-app/upload";
	}

	//当前日期
	String curDate = G.getCurrentDate();
	curDate = curDate.replaceAll("-", "");
	curDate = curDate.substring(0, 6);
	imgPath += curDate;
	
	//新建本地目录
	File dir = new File(absPath+curDate+"/");
	if (!dir.exists()) {dir.mkdirs();}
	
	//新建应用2目录
	dir = new File(absPath2+curDate+"/");
	if (!dir.exists()) {dir.mkdirs();}

//   新建一个SmartUpload对象   
	SmartUpload   su   =   new   SmartUpload();   
//   上传初始化   
	su.initialize(pageContext);   
//   设定上传限制   
//   1.限制每个上传文件的最大长度。   
	su.setMaxFileSize(100*1024*1024);   //10M 
//   2.限制总上传数据的长度。   
//   su.setTotalMaxFileSize(20000);   
//   3.设定允许上传的文件（通过扩展名限制）,仅允许doc,txt文件。   
//   su.setAllowedFilesList("doc,txt");   
//   4.设定禁止上传的文件（通过扩展名限制）,禁止上传带有exe,bat,jsp,htm,html扩展名的文件和没有扩展名的文件。   
//   su.setDeniedFilesList("exe,bat,jsp,htm,html,,");   
//   上传文件   
	su.upload();   
//   将上传文件全部保存到指定目录   
//int count=su.save(imgPath);   
//out.println(count+"个文件上传成功！<br>");   
    
//   利用Request对象获取参数之值   
//out.println("TEST="+su.getRequest().getParameter("TEST")+"<BR><BR>");   
    
//   逐一提取上传文件信息，同时可保存文件。   
com.jspsmart.upload.File file = su.getFiles().getFile(0); 
String mydate = System.currentTimeMillis()+""; //将日期时间格式化 

String path="";

//********************************
//先将文件另存到另一个项目的目录中
String imgPath_bk=absPath2+curDate;
path=imgPath_bk+"/"+mydate+"."+file.getFileExt();
try{file.saveAs(path);}catch(Exception e){}
//********************************

path=imgPath+"/"+mydate+"."+file.getFileExt();
try{file.saveAs(path);}catch(Exception e){}
    
//   若文件不存在则继续   
    
//   显示当前文件信息   
//out.println("<TABLE   BORDER=1>");   
//out.println("<TR><TD>表单项名（FieldName）</TD><TD>" + file.getFieldName()+ "</TD></TR>");   
//out.println("<TR><TD>文件长度（Size）</TD><TD>" + file.getSize()+"</TD></TR>");   
//out.println("<TR><TD>文件名（FileName）</TD><TD>" + file.getFileName()+ "</TD></TR>");   
//out.println("<TR><TD>文件扩展名（FileExt）</TD><TD>" + file.getFileExt() + "</TD></TR>");   
//out.println("<TR><TD>文件全名（FilePathName）</TD><TD>" + file.getFilePathName() + "</TD></TR>");   
//out.println("</TABLE><BR>");   
    
//   将文件另存   
//   file.saveAs("/upload/"   +   myFile.getFileName());   
//   另存到以WEB应用程序的根目录为文件根目录的目录下   
//   file.saveAs("/upload/"   +   myFile.getFileName(), su.SAVE_VIRTUAL);   
//   另存到操作系统的根目录为文件根目录的目录下   
//   file.saveAs("c:\\temp\\"   +   myFile.getFileName(), su.SAVE_PHYSICAL);   
    
%>  
<script type="text/javascript">
<!--
window.location.href='upload.jsp?path=<%=path%>';
//-->
</script>
<title>正在上传...</title>