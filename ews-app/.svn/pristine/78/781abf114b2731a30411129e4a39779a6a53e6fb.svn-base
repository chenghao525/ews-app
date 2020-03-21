<%
/**
* 统计结果显示
* liu hongchao 2004-1-1
*/
%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page
import="java.util.*"
import="com.corp.frame.core.dao.DataObject"
import="com.corp.frame.bean.MetaData"%>
<%@ page errorPage="error.jsp" %>
<%request.setCharacterEncoding("UTF-8");%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-extend.tld" prefix="extend" %>
<link href="css/stylesheet.css" rel="stylesheet" type="text/css">

<jsp:useBean id="fieldList" class="com.corp.frame.bean.FieldList"
 scope="page" />
<jsp:useBean id="tempStatisticForm" class="com.corp.frame.bean.TempStatisticForm"
 scope="page" />
<%
fieldList.populate(request);
tempStatisticForm.populate(request);
tempStatisticForm.getStatistic(fieldList.getStatistic());
fieldList.getQueryView().remove("row_tag");
fieldList.getQueryView().remove("column_tag");
fieldList.setOption("statistic");
fieldList.execute();
%>
<table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
    <tr><td class="content">统计报表</td></tr>
</table>
<br/>
<table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
<tr>
<td class="tableLeft">
统计文书：</td><td class="tableRight"><bean:write name="fieldList" property="document.string(DOMAIN_CNNAME)"
 filter="true"/>（<bean:write name="fieldList" property="document.string(DOMAIN_NAME)"
 filter="true"/>）
</td></tr>
<tr><td class="tableLeft">
统计条件：</td><td class="tableRight"><bean:write name="fieldList" property="queryView.cnWhere" filter="true"/>
</td></tr></table>
<br/>
<table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
<!-- begin　列标题 -->
  <tr class="tableTitle">
    <td>序号</td>
    <td>项目</td><%
  DataObject title = fieldList.getTitle();
  int end = fieldList.N_BEGIN + title.getInt("TITLE_SIZE_LIU");
  // 存入列的合计
  double total[] = new double[end+1];
  for(int i=fieldList.N_BEGIN;i<end;i++)
  {
    // 初始化
    total[i] = 0;
    // 列名
    String titleName = "N" + i ;
    // 横标题
  %>
    <td><a style="cursor:hand;" onClick="sub('row','<%=title.getString(titleName)%>');"><%=title.getString(titleName + "_CN")%></a></td>
<% }	%><td>合计</td>
  </tr>
<!-- end 列标题 -->
<%
  // 所统计的列的代码集合
  Collection columnColl = fieldList.getColumnCode().getValue();
  // 取得统计结果
  Collection coll = fieldList.getResultList();

  // 得到第一列的字段名（英文），通过该名去取列值
  final String columnName = fieldList.getStatistic().getString("columnName");
  //final String columnCodeName = fieldList.getColumnCode().getCodeName();
  //System.out.println(columnName);
  if(columnColl != null && coll != null)
  {
    int count = 1;
    Iterator columnIter = columnColl.iterator();
    while(columnIter.hasNext())
    {
      // 得到某一代码项
      DataObject columnData = (DataObject)columnIter.next();
      // 取得该代码
      String columnCode = columnData.getString(fieldList.getColumnCode().getCodeName());
      // 取得代码中文名
      String columnCNName = columnData.getString(fieldList.getColumnCode().getCodeCNName());
      if(columnCNName == null){
        columnCNName = columnCode;
      }
%>
  <tr class="<%=((count%2==0)?"tableCenter":"tableCenter2")%>">
    <td align="right"><%=count ++%></td>
    <td nowrap><a style="cursor:hand;" onClick="sub('column','<%=columnCode%>');"><%=columnCNName%></a></td>
<%
  // 迭代结果集，如果找到与本行代码值相同的行，则显示
    boolean exist = false;
    Iterator iter = coll.iterator();
    while(iter.hasNext())
    {
       DataObject do1 = (DataObject)iter.next();
       // 如果该项目存在
       if(columnCode.equals(do1.getString(columnName)))
       {
         exist = true;
         double rowTotal = 0;
         for(int i=fieldList.N_BEGIN;i<end;i++)
         {
           String titleName = "N" + i;
           String value = do1.getString(titleName);
           try{
           // 计算行列合计
             double dValue = Double.parseDouble(value);
             total[i] += dValue;
             rowTotal += dValue;
           }catch(Throwable t){}
%>
    <td align="right"><a style="cursor:hand;" onClick="query('<%=title.getString(titleName)%>','<%=do1.getString(columnName)%>');">
        <%=parseString(value)%></a></td>
<%       }
        // 行的合计
        do1.setString("N" + end,String.valueOf(rowTotal));
 %><td align="right"><%=parseDouble(rowTotal)%></td>
<%
       // 找到后则跳出循环
       break;
     }
  } // while(iter.hasNext())
  // 如果该列不存在，则填空
  if(!exist)
  {
    // end = end + 合计
    for(int i=fieldList.N_BEGIN;i<=end;i++)
    {
%><td></td>
<%
    }
  }
  }// end while(columnIter.hasNext())
%>
  </tr>
<!-- begin 合计 -->
  <tr class="<%=((count%2==0)?"tableCenter":"tableCenter2")%>">
    <td align="right"><%=count ++%></td>
    <td nowrap>合计</td>
<%
    // 计算全表合计
    double tTotal = 0;
    for(int i=fieldList.N_BEGIN;i<end;i++)
    {
      tTotal += total[i];
%><td align="right"><%=parseDouble(total[i])%></td><%
    }
%>
    <td align="right"><%=parseDouble(tTotal)%></td>
  </tr>
<!-- end 合计 -->
<%   }// end if(columnColl != null) %>
</table>
<form name="dig" method="post">

<html:hidden name="fieldList" property="option" />
<%-- 查询条件 --%>
<extend:hiddenObject name="fieldList" property="queryView"/>
<%-- 统计条件 --%>
<extend:hiddenObject name="fieldList" property="statistic"/>
<%-- 所查询统计的文书对象 --%>
<extend:hiddenObject name="fieldList" property="document"/>
<%--　所选择要显示的列 --%>
<extend:hiddenObject name="fieldList" property="selectField"/>

<%-- 向下一级挖掘时的条件,比如要挖掘营业税则查询条件就要增加　(征收项目　等于　营业税) --%>
<input name="queryView.queryObject(row_tag).field" value="<%=fieldList.getStatistic().getString("rowName")%>" type="hidden">
<input name="queryView.queryObject(row_tag).operator" value="LIKE" type="hidden">
<input name="queryView.queryObject(row_tag).value" value="" type="hidden">

<input name="queryView.queryObject(column_tag).field" value="<%=fieldList.getStatistic().getString("columnName")%>" type="hidden">
<input name="queryView.queryObject(column_tag).operator" value="LIKE" type="hidden">
<input name="queryView.queryObject(column_tag).value" value="" type="hidden">

  <table class="tablebgWW">
    <tr><td>
       <input type=submit value="重新查询" onClick="action='queryList.jsp';option.value='reStatistic';submit();" class="button1">
       <input type=submit value="重新统计" onClick="action='statisticList.jsp';option.value='reStatistic';submit();" class="button1">
       <INPUT TYPE="button" class="button-ui" class="button1" onClick="javascript:history.back();" value=" 返回 ">
       <input type=submit value="返回首页" onClick="action='documentList.jsp';submit();" class="button1">
</td></tr></table>
</form>
<script language="javascript">
// 查看详细信息
function query(row,column){
  document.dig.reset();
  row += '%';
  column += '%';
  o_row = MM_findObj_('queryView.queryObject(row_tag).value');
  o_column = MM_findObj_('queryView.queryObject(column_tag).value');
  o_row.value = row;
  o_column.value= column;

  // 曲调统计条件中的 GROUP BY 语句
  o_extension = MM_findObj_('queryView.extension');
  if(o_extension){
    o_extension.value='';
  }
  document.dig.action="resultList.jsp";
  document.dig.submit();
}

// 向下一级挖掘
function sub(type,value){
  document.dig.reset();
  s_type = 'queryView.queryObject(' + type + '_tag).value';
  // 所挖掘字段的级别
  s_length = 'statistic.object(' + type + 'Level)';
  s_value = 'statistic.object(' + type + 'Value)';

  o_type = MM_findObj_(s_type);
  o_type.value = (value + '%');

  o_value = MM_findObj_(s_value);
　//  if(o_value){
    o_value.value = (value + '%');
　//}

  o_length = MM_findObj_(s_length);
  //if(o_length){
    length_value = o_length.value;
    //if(length_value == null || length_value  == ''){
    //  length_value = '6';
    //}
    if(length_value != '-1'){
      o_length.value = (1 + parseInt(length_value));
    }
  //}

  document.dig.action="resultList2.jsp";
  document.dig.submit();
}
// 返回上一级
function up(){
  // 将所挖掘代码的级别减一
  s_rowLevel = 'statistic.object(rowLevel)';
  s_columnLevel = 'statistic.object(columnLevel)';

}

//通过控件名字得到object
function MM_findObj_(n, d){
	var p,i,x;

	if(!d)
		d=document;

	if((p=n.indexOf("?"))>0&&parent.frames.length)
	{
		d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);
	}
	if( !(x=d[n]) && d.all )
		x=d.all[n];

	for (i=0;!x&&i<d.forms.length;i++)
		x=d.forms[i][n];

	for(i=0;!x&&d.layers&&i<d.layers.length;i++)
		x=MM_findObj_(n,d.layers[i].document);

	return x;
}
</script>
<%!
private static java.text.NumberFormat format = null;
private static String parseDouble(double value){
  if(format == null){
   format = java.text.NumberFormat.getInstance();
   format.setMinimumFractionDigits(0);
   format.setMaximumFractionDigits(2);
  }
  if(value >=(-0.999) && value <= (0.001)){
    return "";
  }
  //if((value-(int)value) > 0.001){
  return format.format(value);
  //}
  //else{
  //  return String.valueOf(value);
  //}
}
private static String parseString(String value){
  if(value == null || "".equals(value) || "0".equals(value)){
    return "";
  }
  try{
    return parseDouble(Double.parseDouble(value));
  }catch(Throwable t){
    return "";
  }
}
%>