<%--
接口说明:
类型           变量名      说明
String        |tableName: 域名
Collection    |dataList : 值的列表
Collection    |fields   : 字段列表
DataManager   |dm       : 数据库管理对象
--%>
<table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
  <tr class="tableTitle">
<%
Collection fields = (Collection)pageContext.getAttribute("fields");
Iterator iterField = fields.iterator();
    while(iterField.hasNext()){
    FieldProperty field = (FieldProperty)iterField.next();
%>
    <td><a><%=field.getFieldCNName()%></a></td>
<% } %>
  <td>操作</td>
  </tr>
<%
  CodeManager cm = CodeManager.getInstance(tableName, dm);
  Iterator iter = dataList.iterator();
  int index = -1;
  while(iter.hasNext())  {
    index++;
    out.println("<tr class=\"tableRight\">");
     DataObject do1 = (DataObject)iter.next();
     iterField = fields.iterator();
     while(iterField.hasNext()){
       FieldProperty field = (FieldProperty)iterField.next();

       String value = do1.getString(field.getFieldName());
       out.print("<td>");
       if(value != null){
         // 如果是代码
         if(field.isCode()){
            value = cm.getCodeCNName(field.getExplain(), value);
         }
         // 转化输出格式
         value = org.apache.struts.util.ResponseUtils.filter(value);
         // 输出字段值
         out.print(value);
       }
       out.print("</td>");
     }
%>
   <%-- 修改　或着删除 --%>
   <td nowrap><a style="cursor:hand;" onClick="subSubmit('<%=index%>','<%=tableName%>','preEditSubBean');">修改</a>
       <a style="cursor:hand;" onClick="if(ifdel())subSubmit('<%=index%>','<%=tableName%>','removeSubBean');">删除</a></td>
</tr>
<% }
%>
</table>

<div align="left"> 　共 <%=dataList.size()%> 条 </div>
