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
 String formAction = null;
 if("query".equals(fieldList.getOption()) ||
    "reQuery".equals(fieldList.getOption())) {
   formAction = "resultList.jsp";
   fieldList.setOption("query");
 } else if("statistic".equals(fieldList.getOption())){
   formAction = "resultList2.jsp";
   tempStatisticForm.populate(request);
   tempStatisticForm.getStatistic(fieldList.getStatistic());
 } else if("reStatistic".equals(fieldList.getOption())){
   formAction = "resultList.jsp";
   fieldList.setOption("statistic");
 }
 %>
<table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
    <tr><td class="content">选择要显示的列</td></tr>
</table>
<br/>
<table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
<tr>
<td class="tableLeft">
查询文书：</td><td class="tableRight"><bean:write name="fieldList" property="document.string(DOMAIN_CNNAME)"
 filter="true"/>（<bean:write name="fieldList" property="document.string(DOMAIN_NAME)"
 filter="true"/>）
</td></tr>
<tr><td class="tableLeft">
查询条件：</td><td class="tableRight"><bean:write name="fieldList" property="queryView.cnWhere" filter="true"/>
</td></tr></table>

<form name="Headers" action="<%=formAction%>"  method="post">
 <table border="0" cellpadding="3" cellspacing="1" class="tablebgW">
    <tr>
      <td class="tableRight" colspan="5">
        <input name="allbox" type="checkbox" onclick="javascript:CA(this.checked);">
全部
</td>
</tr>
<%
pageContext.setAttribute("fieldListColl",
MetaData.getFieldList(fieldList.getTableName(),false,false,true));
%>
<extend:iterateGroup id="element" name="fieldListColl" column="4">
   <td><input name="showField" type="checkbox"
         value="<bean:write name="element" property="fieldName"/>"
         onmouseover="cc(this)" onmouseout="out()" onClick="change(this)">
       <bean:write name="element" property="fieldCNName"/></td>
</extend:iterateGroup>
</table>
  <table class="tablebgWW">
    <tr><td>

<html:hidden name="fieldList" property="option" />

<extend:hiddenObject name="fieldList" property="statistic"/>
<extend:hiddenObject name="fieldList" property="document"/>
<extend:hiddenObject name="fieldList" property="queryView"/>
<!-- 用户选择的顺序 -->
<input type="hidden" name="index" value="0">
  <input name="b_sure" type="submit" onClick="return ifSelectedOne();"value=" 确定 " class="button1" >
  <INPUT TYPE="button" class="button-ui" class="button1" onClick="CA(false);" value=" 清空 ">
  <INPUT TYPE="button" class="button-ui" class="button1" onClick="javascript:history.back();" value=" 返回 ">
</td></tr></table>
<DIV id=pop
style="DISPLAY: none; FONT-SIZE: 13px; Z-INDEX: 99; BACKGROUND: #ffff00; WIDTH: 60px; POSITION: absolute"></DIV>
<div id=hidChkbox>
</div>
</form>
<!--
style="FONT-SIZE: 13px; Z-INDEX: 99; BACKGROUND: #ffff00; WIDTH: 60px; POSITION: absolute"></DIV>
-->
<SCRIPT language=javascript>
// 确定至少要选择一个
function ifSelectedOne(){
  if(document.Headers.index.value == 0){
    alert('请至少选择一个列。');
    return false;
  }
  return true;
}
 var hiddenText="";
 function selectbox(e){
   var str = '<input type=hidden value=' + e.value + ' name=selectField>';
    if(e.checked){
     hiddenText += str;
    }else{
      var start = hiddenText.indexOf(str);
      if(start > -1){
        hiddenText = hiddenText.substring(0,start) + hiddenText.substring(start+str.length);
      }
   }
   document.all.hidChkbox.innerHTML = hiddenText;
}
function CA(checked){
  var indexValue;
  if(checked){
    indexValue = parseInt(document.Headers.index.value);
  }
  else{
    indexValue = 0;
  }
  for(var i=0;i<document.Headers.elements.length;i++){
    var e=document.Headers.elements[i];
    if(e.name =='showField' && e.checked != checked){
        e.checked = checked;
        if(e.checked){
          indexValue++;
          e.index = indexValue;
        }
        selectbox(e);
    }
  }
  document.Headers.index.value = indexValue;
}
var cen = document.all.pop;
function cc(e)
{
    if(!e.checked) {
      return;
    }
    cen.style.background = "#FFFF00";
    cen.innerText = "索引：" + e.index;
    var ttop = e.offsetTop;
    var tleft= e.offsetLeft;
    var h = e.clientHeight;   //在下面这个 while 循环里的e已经不是那个形参了
    var w = e.clientWidth;

    while (e = e.offsetParent){ttop += e.offsetTop; tleft += e.offsetLeft;}
    cen.style.display = "";  //层显示
    cen.style.top = ttop + h;
    cen.style.left= tleft + w - cen.clientWidth;

}
function out(){cen.style.display = "none";}
function change(e){
    var indexValue = parseInt(document.Headers.index.value);
    if(e.checked) {
      // 选中
      indexValue += 1;
      e.index = indexValue;
    }
    else{
      var index = e.index;
      e.index = '';
      for(var i=0;i<document.Headers.elements.length;i++){
        var temp=document.Headers.elements[i];
        if(temp.name =='showField' && temp.checked && temp.index > index){
           temp.index -= 1;
        }
      }
      indexValue -= 1;
    }
    document.Headers.index.value = indexValue;
    selectbox(e);
}
//清除form里面的控件值
function clearAll()
{
	var formId = '';
	var id;
	var args = clearAll.arguments;
	if( args.length == 0 )
		for( var i = 0 ; i < document.forms.length ; i++ )
			formId = formId + i +',';
	else
		formId = args[0]+',';

	while( formId.indexOf(',') > 0 )
	{
		id = formId.substring(0,formId.indexOf(','));
		formId = formId.substring(formId.indexOf(',')+1);
		for( i = 0 ;i < document.forms[id].elements.length ; i ++ )
		{

			if( document.forms[id].elements[i].type=='text' && document.forms[id].elements[i].disabled==false )
				document.forms[id].elements[i].value = '';
			else if( document.forms[id].elements[i].type=='textarea' && document.forms[id].elements[i].disabled==false )
				document.forms[id].elements[i].value = '';
			else if(document.forms[id].elements[i].type=='select-one')
				document.forms[id].elements[i].value = document.forms[id].elements[i].options[0].value;

		}
	}
}

</SCRIPT>
