<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page
import="java.util.*"
import="com.corp.frame.eflow.*"
import="com.corp.frame.core.dao.*"
%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%
String flowId = request.getParameter("flowId");
String businessDataId = request.getParameter("businessDataId");
DataManager dm = null;
BusinessFlow bf = null;
try{
  dm = DataManager.getInstance();
  bf = BusinessFlow.getInstance(flowId,dm);

%>

<html>
<head>
<title>
<!-- 查看某一数据在某一流程的中位置是处理意见　-->
</title>
</head>
<body bgcolor="#ffffff">
<h1>&nbsp; </h1>
<table border="1">
  <tr>
    <td> 开始 </td>
    <td>部门</td>
    <td>人员</td>
    <td>到达时间</td>
    <td>结束时间</td>
    <td>处理完毕标志</td>
    <td>流转方向</td>
  </tr>
  <%
  for(Node node = bf.getStartNode();node != null;node = node.getNextNode()){
    Collection coll = WorkFlowProcess.getProcessData(businessDataId,node.getFlowId(),node.getId(),"%","%",dm);
  %>
  <tr>
    <td rowspan="<%=coll.size()%>"> <%=node.getName()%></td>
    <%
    Iterator iter = coll.iterator();
    DataObject do1 = null;
    if(iter.hasNext()){
      do1 = (DataObject)iter.next();
    }else{
      do1 = new DataObject();
    }
    %>
    <td><%=getStr(do1.getString("DEPTID"))%></td>
    <td><%=getStr(do1.getString("USERID"))%></td>
    <td><%=getStr(do1.getString("STARTTIME"))%></td>
    <td><%=getStr(do1.getString("ENDTIME"))%></td>
    <td><%=getStr(do1.getString("PROCESSED"))%></td>
     <td><%=getStr(do1.getString("FORWARD"))%></td>
	  </tr>
  <%
    while(iter.hasNext()){
      do1 = (DataObject)iter.next();
    %>
  <tr>
    <td><%=getStr(do1.getString("DEPTID"))%></td>
    <td><%=getStr(do1.getString("USERID"))%></td>
    <td><%=getStr(do1.getString("STARTTIME"))%></td>
    <td><%=getStr(do1.getString("ENDTIME"))%></td>
    <td><%=getStr(do1.getString("PROCESSED"))%></td>
     <td><%=getStr(do1.getString("FORWARD"))%></td>
 </tr>
  <% } %>
  <% } %>
  <tr>
    <td> 结束 </td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
</html>
<%
}
finally{
  if(dm!= null){
    dm.close();
  }
}
%>
<%!
String getStr(String str){
  if(str == null){
    return "";
  }
  return str;
}
%>
