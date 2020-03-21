<%@ page
contentType="text/html; charset=UTF-8"
import="java.util.*"
import="com.corp.frame.bean.MetaData"
import="com.corp.frame.core.dao.DataObject"
import="com.corp.frame.core.metadata.DatabaseObject"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page errorPage="../error.jsp" %>
<%request.setCharacterEncoding("UTF-8");%>
<link href="../css/stylesheet.css" rel="stylesheet" type="text/css">
<script src="../css/js.js" language="JavaScript"></script>
<jsp:useBean id="addFieldBean" scope="page"
             class="com.corp.frame.bean.AddFieldBean"/>
<jsp:useBean id="deleteFieldBean" scope="page"
             class="com.corp.frame.bean.DeleteFieldBean"/>
<jsp:useBean id="copyFieldBean" scope="page"
             class="com.corp.frame.bean.CopyFieldBean"/>
<script language="javascript">
function CA1(){
  for(var i=0;i<document.Headers.elements.length;i++){
    var e=document.Headers.elements[i];
    if(e.name =='selectedField') e.checked=document.Headers.allbox.checked;
  }
}
function CA2(){
  for(var i=0;i<document.batAdd.elements.length;i++){
    var e=document.batAdd.elements[i];
    if(e.name =='addFields') e.checked=document.batAdd.allbox.checked;
  }
}
</script>

<%
  // 批量增加
  addFieldBean.populate(request);
  String tableName = addFieldBean.getTableName();

  if(request.getParameter("b_batAddField")!=null){
    addFieldBean.setOption("multi");
    addFieldBean.setRequest(request);
    addFieldBean.execute();
  }
  else if(request.getParameter("b_add")!=null){
    addFieldBean.setOption("single");
    addFieldBean.execute();
  }
  else if(request.getParameter("b_update")!=null){
    addFieldBean.setOption("update");
    addFieldBean.execute();
  }
  // 删除
  else if(request.getParameter("b_delete") != null){
    deleteFieldBean.populate(request);
    deleteFieldBean.execute();
  }
  // COPY
  else if(request.getParameter("b_copy") != null){
    copyFieldBean.populate(request);
    copyFieldBean.execute();
  }
  // 从数据库中导入
  else if(request.getParameter("b_importFromDataBase")!= null){
    addFieldBean.setOption("importFromDataBase");
    addFieldBean.execute();
  }
%>


<form name="Headers">
<br>
  <table cellpadding="3" cellspacing="1" class="tablebgW">
    <tr>

      <td class="content" colspan="5">已登记字段</td>
    </tr>
    <tr>
        <td class="tableRight" colspan="5">
           <input name="allbox" type="checkbox" onclick="javascript:CA1();">
        全部</td>
    </tr>
    <tr class="tableCenter">
      <td>选中</td>
      <td>表中字段名</td>
      <td>中文字段名</td>
      <td>数据类型</td>
      <td>EXPLAIN</td>
    </tr>
<%
{
 Collection coll = DatabaseObject.getDatabaseObject(tableName).getFieldList();
 pageContext.setAttribute("list",coll);
%>
<logic:iterate id="element" name="list" scope="page">
    <tr class="tableCenter2">
      <td>
        <input name="selectedField" type="checkbox" value="<bean:write name="element" property="fieldName"/>">
      <td><bean:write name="element" property="fieldName"/></td>
      <td><bean:write name="element" property="fieldCNName"/></td>
      <td><bean:write name="element" property="expression"/></td>
      <td><bean:write name="element" property="explain"/></td>
  </tr>
</logic:iterate>
<% }%>
</table>

<table class="tablebgWW">
<tr><td>
<html:hidden name="addFieldBean" property="tableName"/>
  <input name="b_update" type="submit" value=" 修改 " class="button1" >
  <input name="b_delete" type="submit" value=" 删除 " class="button1"  >
  <INPUT TYPE="button" class="button-ui" value=" 更改字段顺序 " onClick="redirect('fieldOrderEdit.jsp?domainName=<bean:write
   name="addFieldBean" property="tableName"/>');" class="button1">
  <input name="copyToDomainName" type="text" value="">
  <input name="b_copy" type="submit" value=" 保存 " class="button1"  >
  <INPUT TYPE="button" class="button-ui" value=" 返回 " onClick="redirect('domainSelect.jsp');" class="button1">
  <input name="b_importFromDataBase" type="submit" value=" 数据库中全部导入 " class="button1" >
      </td>
    </tr>
</table>
</form>

<form name="Headers1">
  <table cellpadding="3" cellspacing="1" class="tablebgW">
    <tr>
        <td class="content" colspan="2">单项增加</td>
    </tr>
    <tr>
      <td class="tableLeft">表名</td>
      <td class="tableRight"><html:text name="addFieldBean" property="tableName"/></td>
    </tr>
       <tr>
      <td class="tableLeft">字段名</td>
      <td class="tableRight"><html:text name="addFieldBean" property="fieldRule.string(FIELD_NAME)"/></td>
    </tr>
       <tr>
      <td class="tableLeft">中文字段名</td>
      <td class="tableRight"><html:text name="addFieldBean" property="fieldRule.string(FIELD_CNNAME)"/></td>
    </tr>
       <tr>
      <td class="tableLeft">字段类型</td>
      <td class="tableRight">
      <html:text name="addFieldBean" property="fieldRule.string(TYPE)"/>
	</td>
    </tr>
        <tr>
      <td class="tableLeft">EXPLAIN</td>
      <td class="tableRight"><html:text name="addFieldBean" property="fieldRule.string(EXPLAIN)"/></td>
    </tr>
        <tr>
      <td class="tableLeft">前置类</td>
      <td class="tableRight"><html:text name="addFieldBean" property="fieldRule.string(PREFIX_CLASS)"/></td>
    </tr>
        <tr>
      <td class="tableLeft">后置类</td>
      <td class="tableRight"><html:text name="addFieldBean" property="fieldRule.string(SUFFIX_CLASS)"/></td>
    </tr>
        <tr>
      <td class="tableLeft">显示风格</td>
      <td class="tableRight"><html:text name="addFieldBean" property="fieldRule.string(DISPLAY_TYPE)"/></td>
    </tr>
</table>
<input name="b_add" type="submit" value=" 保存 " class="button1" >
</form>
<br>

<form name="batAdd" method="post">
  <table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
    <tr>
        <td class="content" colspan="5">此表实际存在的字段</td>
    </tr>
    <tr>
        <td class="tableRight" colspan="5">
           <input name="allbox" type="checkbox" onclick="javascript:CA2();">
        全部</td>
    </tr>
    <tr class="tableCenter">
      <td>选择</td>
      <td>字段名</td>
      <td>中文名</td>
      <td>类型</td>
      <td>EXPLAIN</td>
   </tr>
<%
{
  try{
  Iterator iter = MetaData.getExistFieldList(tableName).iterator();
  while(iter.hasNext()){
   String field = (String)iter.next();
   String fieldType = field + "_FIELD_TYPE";
%>
    <tr class="tableCenter2">
       <td><input name="addFields" type="checkbox" value="<%=field%>"></td>
      <td><%=field%></td>
      <td><input type="text" name="<%=field%>_FIELD_CNNAME"></td>
      <td><input type="text" name="<%=fieldType%>">
	</td>
      <td>
        <input type="text" name="<%=field%>_FIELD_EXPLAIN"></td>
  </tr>
<% }
}
catch(Exception e){
  out.print(e.getMessage());
}
}%>
</table>
<html:hidden name="addFieldBean" property="tableName"/>
<input name="b_batAddField" type="submit" value=" 保存 " class="button1" >
<br>
<table><tr><td>
操作说明：选择要添加的字段，输入其中文名，选择其类型，如果该字段是代码，在ＥＸＰＬＡＩＮ中
中输入其代码所对应的代码表名（如果与字段名一致可不填）．
</td></tr></table>
</form>
<pre>
 字段规则

 规则简称　名称　　　　　　　作用范围　　ＥＸＰＬＡＩＮ中的值

 S 字符串（ＳＴＲＩＮＧ）（录入、修改）  长度[10][5][5,2][5~10]
 C 代码（ＣＯＤＥ）（录入、修改）       代码名称
 I 整数值                            0~100
 F 浮点数                            0.00~1
 D 日期（ＤＡＴＥ）（录入、修改）       2004-10-01~2004-12-30
                                    ~T,T~

 E 大文本框                          高度,长度,[2,10]
 L 列表（ＬＩＳＴ）
 M 复选框ＣＨＥＣＫＢＯＸ
 N 单选按钮(RADIO)
 P 密码 (password)
 ~ 显示该字段的值(以显示的格式而不是输入的格式)

 W 显示完后换一行(newLine)


 R 必输（required)（录入、修改）
 O 只读（read-only)（修改），
 V 保存时校验（录入、修改）

 A 自动编号（ＡＵＴＯＮＵＭ）（录入）　　SEQUENCE_NAME(必输)
 U 默认当前用户(CurrentUser)（录入）
 T 默认当前时间(CurrentTime)（录入）

 B 在显示录入页面时是否执行一段JAVA代码
 G CODE的值是从前执类中取,一般和 CL 联用
 H 是否在输入之后\保存之前调用后执类一定的较验

 Y 是否校验CODE的权限
 Z 列表时是否显示CODE项

 J 临时字段
 K 该临时字段在录入是显示
 Q 该临时字段在查询时显示
 X 该临时字段在列表时显示

 / 其但更改后提交FORM(类似ORACLE)
 = 合并字段名和输入框为一个单元格

</pre>
