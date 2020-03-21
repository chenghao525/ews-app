<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page
import="java.util.*"
import="com.corp.frame.core.dao.DataObject"
import="com.corp.frame.core.metadata.DatabaseObject"
import="com.corp.frame.core.metadata.FieldProperty"
import="com.corp.frame.bean.FieldList"
import="com.corp.frame.bean.MetaData"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-extend.tld" prefix="extend" %>
<%@ page errorPage="../error.jsp" %>
<%request.setCharacterEncoding("UTF-8");%>
<%
String domain_name = request.getParameter("domain_name");
if(domain_name == null || "".equals(domain_name)){
  // 退出
  return;
}
// 得到该表的所有字段
Collection coll = DatabaseObject.getDatabaseObject(domain_name)
  .getFieldList(false,false,true);

if(coll == null || coll.size() == 0)
{
  throw new Exception("the domain["+domain_name+"] not define in fieldrule.");
}
String[] selectField = new String[coll.size()];
Iterator iter = coll.iterator();
int i=0;
while(iter.hasNext()){
  FieldProperty do1 = (FieldProperty)iter.next();
  selectField[i] = do1.getFieldName();
  i++;
}
DataObject document =
MetaData.getDocument(domain_name,FieldList.OPTION_TYPE_MAINTENANCE);
// 设置类型
document.setString(FieldList.OPTION_TYPE,FieldList.OPTION_TYPE_MAINTENANCE);

request.setAttribute("document",document);
request.setAttribute("option","query");
request.setAttribute("selectField",selectField);
%>
<form name="temp" action="../resultList.jsp" method="post">
<extend:hiddenObject name="document"/>
<extend:hiddenObject name="option"/>
<extend:hiddenObject name="selectField"/>
<script language="javascript">
  document.temp.submit();
</script>
</form>
