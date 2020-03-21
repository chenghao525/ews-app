<%@page contentType="text/html; charset=UTF-8" %><%@page
import="java.util.*"
import="java.io.*"
import="com.corp.frame.core.dao.DataObject"
import="com.corp.frame.report.*"
import="com.corp.frame.bean.MetaData"%><%@ page
errorPage="error.jsp" %><jsp:useBean
id="fieldList" class="com.corp.frame.bean.FieldList"
 scope="page" /><%
/**
* 打印页面
*/
request.setCharacterEncoding("UTF-8");
fieldList.populate(request);
fieldList.setOption("query");
fieldList.setPagination(null);
fieldList.execute();
String tableName = fieldList.getTableName();
DataObject table = MetaData.getTable(tableName);
String[] selectField = fieldList.getSelectField();

TestForm test = new TestForm();
FormData formData = new FormData();
formData.setTitleObject(table);
formData.setTitles(selectField);
String title = fieldList.getDocument().getString("DOMAIN_CNNAME") +
                "(" + fieldList.getDocument().getString("DOMAIN_NAME") + ")";
formData.setTableTitle(title);
formData.setCondition(fieldList.getQueryView().getCnWhere());

formData.analyse();
formData.setData(fieldList.getResultList());
java.io.File file = java.io.File.createTempFile("autoPrint",".pdf");
test.createPDF(file.getAbsolutePath(),fieldList.getPageType(),formData);

response.setContentType("application/octet-stream; charset=UTF-8");
//response.addHeader("Content-Disposition","attachment; filename=" + object.getFileName());

//response.setContentType(object.getType() + "; charset=UTF-8");
//response.addHeader("Content-Disposition","attachment; filename=" + object.getFileName());
response.addHeader("Content-Disposition","application; filename=" + file.getName());
int len=0;
byte[] b=new byte[256];
InputStream input = new FileInputStream(file);
OutputStream output = response.getOutputStream();

while((len=input.read(b))!=-1){
     output.write(b,0,len);
}
input.close();
output.close();
%>
