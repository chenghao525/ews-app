<%
{
   java.util.Collection coll = com.corp.frame.grant.OperationToPermission.getTopOperationGroup();
   java.util.Iterator iter = coll.iterator();
   while(iter.hasNext()){
     com.corp.frame.core.dao.DataObject do1 =
     (com.corp.frame.core.dao.DataObject)iter.next();
%>
  <tr class="tableRight">
    <td class="tableRight"><a href="/frame/system/grant/rolepermission.jsp?grantType=OperationToPermission&groupBean.groupName=<%=do1.getString("CODENO")%>&groupBean.level=1" target="mainFrame">
        <%=do1.getString("CODENAME")%></a></td>
  </tr>
<% }
}
%>
<%
  {
   domain.getQueryView().setSelect("SELECT FLOW_ID AS CODENO,FLOW_NAME AS CODENAME FROM WORKFLOW ORDER BY FLOW_ID");
   domain.execute();
   java.util.Collection coll = domain.getResultList();
   java.util.Iterator iter = coll.iterator();
   while(iter.hasNext()){
     com.corp.frame.core.dao.DataObject do1 =
     (com.corp.frame.core.dao.DataObject)iter.next();
%>
  <tr class="tableRight">
    <td class="tableRight"><a href="/frame/flowAction.jsp?flowId=<%=do1.getString("CODENO")%>&groupBean.level=1" target="mainFrame">
        <%=do1.getString("CODENAME")%></a></td>
  </tr>
<% }
  }
%>