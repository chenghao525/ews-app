<%@page contentType="text/html; charset=UTF-8" %>
<% /*
///////////////////////////////////////////////////////////////
接口说明:
类型           变量名      说明
String         property   :Form 中QueryView的名称;
String         domainName :域名;
*/ %>
<%
String queryName = request.getParameter("queryname");
if(queryName==null || "".equals(queryName) || "null".equals(queryName))
	queryName="";

String beanName = Constants.BEAN_KEY;
%>
<div id="filterTab" class="mini-tabs" activeIndex="0" style="width:99%">
    <div title="数据过滤器" iconCls="icon-find">
		<table width='99%'>
			<%@include file="../frame/dataQueryCondition.jsp" %>
			<tr id="_customSearch"></tr>
			<tr height="5"><td colspan="6" align="right"></td></tr>
			<tr>
				<td colspan="6" align="right">
			<%
				if("".equals(queryName)){
			%>
					<html:submit StyleClass="button-ui" property="b_query" onclick="try{setPageValue(this,'current',0)}catch(e){};return searchRequest();">查&nbsp;&nbsp;询</html:submit>
					&nbsp;
					<INPUT TYPE="button" class="button-ui" onclick="javascirpt:clearAll();"  value="重&nbsp;&nbsp;置">
					&nbsp;&nbsp;
					<html:submit StyleClass="button-ui" property="b_query" onclick="showAll(this);">显示全部</html:submit>
			<%
				}else{
			%>
					<input type="submit" name="<%=queryName%>" value="查&nbsp;&nbsp;询" onclick="try{setPageValue(this,'current',0)}catch(e){};return searchRequest();">
					&nbsp;
					<INPUT TYPE="button" class="button-ui" onclick="javascirpt:clearAll();"  value="重&nbsp;&nbsp;置">
					&nbsp;&nbsp;
					<input type="submit" name="b_query" value="显示全部" onclick="showAll(this);">
			<%
				}
			%>
			</td></tr>
		</table>
	</div>
	<div title="操作帮助" iconCls="icon-help" showCloseButton="true" style="display:none;">
		<B>查询模块功能帮助：</B>
		<OL style="margin-top:0cm;margin-bottom:5pt;">
			<LI>查询模块中的查询字段均采用联合查询方式，及系统根据完全满足填入的查询条件查询数据并显示；
			<LI>查询中的所有字段不需要全部填写，用户根据需要填入查询条件。如果查询字段没有填写信息，系统将忽略此字段查询；
			<LI>填写完查询条件后，点击“查询”按钮或者直接敲回车即可进行查询；
			<LI>点击“重置”按钮将清空所有查询条件，然后点击“查询”按钮或者直接敲回车即可查询所有数据；
		</OL>
		<B>查询字段填写帮助：</B>
		<OL style="margin-top:0cm;margin-bottom:5pt;">
			<LI>查询字段基本上分为文本框查询、日期查询和下拉框查询；
			<LI>文本框查询均为字符串模糊查询，只要填入字符串，系统自动进行模糊查询，并且系统支持<B><FONT COLOR="red">空格</FONT></B>、“<B><FONT COLOR="red">+</FONT></B>”、“<B><FONT COLOR="red">-</FONT></B>”查询符号，具体规则如下：
				<UL>
					<LI>填入的字符串可以使用空格进行分隔，系统自动使用“或”进行查询，例如：用户输入“a  b”，系统将查询该属性中包含“a”或者包含“b”的数据；
					<LI>填入的字符串前面如果加入“+”（加号），系统自动使用“和”进行查询，例如：用户输入“a  +b”，系统将查询该属性中包含“a”并且包含“b”的数据；
					<LI>填入的字符串前面如果加入“-”（减号），系统自动排除该字符串信息，及将包含该字符串的信息排除在查询数据之外，例如：用户输入“a b  +c -d”，系统将查询该属性中包含“a”或者包含“b”，但必须包含“c”，但不包含“d”的数据；
				</UL>
			<LI>日期查询将使用区间范围查询，及系统查询用户选择的日期范围之间的数据，如果用户选择了开始和结束时间，系统将在此开区间进行查询，用户也可以只选择开始时间或者只选择结束时间，例如：
				<UL>
					<LI>用户选择开始时间“2012-01-01”和结束时间“2012-01-31”，系统将选择 ≥2012-01-01 并且 ≤2012-01-31 的所有数据；
					<LI>如果只选择开始时间“2012-01-01”，系统将选择 ≥2012-01-01的所有数据；
					<LI>如果只选择结束时间“2012-01-31”，系统将选择 ≤2012-01-31的所有数据；
				</UL>
			<LI>下拉框查询使用精确匹配查询，及系统将根据下拉框选定的值进行精确匹配，查询属性字段等于选定值的所有数据；
		</OL>
	</div>
</div>

<SCRIPT LANGUAGE="JavaScript">
<!--
mini.parse();
function showAll(obj){
	clearAll();
	try{
		setPageValue(obj,'current',0);
	}catch(e){}
	return searchRequest();

}
function searchRequest(){
	if(typeof(_customSearchFunc) == "function") _customSearchFunc();
	mini.loading("正在查询数据，请稍后 ...", "查询");
	return true;
}
//-->
</SCRIPT>



