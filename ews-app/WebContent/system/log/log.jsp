<%@ page contentType="text/html; charset=UTF-8" import="com.corp.oa.util.G" %>

<head>
<title></title>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type="text/css" rel="STYLESHEET"> 
<script language="JavaScript" src="/js/js.js?r=1221"></script>
<script type="text/javascript" src="/js/sort.js"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
</head>
<%
	if(!G.isOwnRight("19907000",request)){out.println("没有权限或会话已失效");return;}
%>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"> 
<div class="mini-toolbar" style="border-bottom:0;padding:0px;">
	<form id="formsearch" style="margin-bottom:0">
	<table>
		<tr style="text-align:left;float:left;"><td>
			操作员：<input id="S_OPTEMPID" class="mini-combobox" style="width:100px;" showNullItem="true" url="/AjaxService?method=GetEmpAllList" onenter="search"/> 
			<span class="separator"></span>
			操作类型：<input id="S_OPTTYPE" class="mini-combobox" style="width:150px;" allowInput="true" showNullItem="true" url='/AjaxService?method=GetDicList4MU&data={"infotype":"15","order":"content"}}' onenter="search"/>
			<span class="separator"></span>
			操作时间：<input id="S_OPTTIME_START" class="mini-datepicker" value=""/>~<input id="S_OPTTIME_END" class="mini-datepicker" value=""/> 
			<span class="separator"></span>
			日志描述：<input id="S_OPTATTR" class="mini-textbox" style="width:150px;" onenter="search"/>
			<span class="separator"></span>
			<a class="mini-button" onclick="reset()">重置</a>&nbsp;<a class="mini-button" onclick="search()">查询</a>
		</td></tr>
	</table></form>
</div>
<div class="mini-fit">
	<div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" idField="SID" allowResize="false" sizeList="[50,100]"  pageSize="100" allowCellEdit="false" allowCellSelect="false" multiSelect="false" allowCellValid="false" sortMode="server" url="">
		<div property="columns">
			<div type="indexcolumn" width="20"></div>
			<div type="comboboxcolumn" field="LOGTYPE" width="50">日志类型
				<input property="editor" class="mini-combobox" style="width:100%;" data="logType"/>  
			</div>
			<div type="comboboxcolumn" field="OPTEMPID" width="50">操作人员
				<input property="editor" class="mini-combobox" style="width:100%;" url="/AjaxService?method=GetEmpAllList"/>
			</div>
			<div type="comboboxcolumn" field="OPTTYPE" width="80">操作类型
				<input property="editor" class="mini-combobox" style="width:100%;" url='/AjaxService?method=GetDicList4MU&data={"infotype":"15","order":"content"}}'/>
			</div>
			<div field="OPTTIME" width="120" dateFormat="yyyy-MM-dd HH:mm:ss">操作时间</div>
			<div field="OPTATTR" width="650">日志描述</div>
			<!-- <div field="OPTSECONDS" width="50" dataType='currency' currencyUnit='' align='right'>响应时间</div> -->
		</div>
	</div>
</div>

<SCRIPT LANGUAGE="JavaScript">
<!--
mini.parse();
var grid = mini.get("datagrid1");

function gridIniLoad(json){
	if(json==null){mini.alert("请输入查询条件");return;}
	grid.url="/AjaxService?method=GetCommonList&code=GetSystemLogList";
	grid.load(json);
	grid.deselectAll();
}

var logType=[{ id: "A", text: '系统日志' }, { id: "B", text: '操作日志'}];

function reset(){var form = new mini.Form("#formsearch");form.reset();}
function search() {gridIniLoad(getSearchJson());}
function getSearchJson(){
	var form = new mini.Form("#formsearch");
	var fields = form.getFields();
	var ui,v;
	var json={};
	var bs=false;
	for(var i=0;i<fields.length;i++){
		 ui = fields[i];
		 v=ui.value;
		 if(nvl(v)!=""){
			 bs=true;
			 if(ui.id=="S_OPTTIME_START"||ui.id=="S_OPTTIME_END"){json[ui.id]=ui.getFormValue();}
			 else{json[ui.id]=v;}
		}
	}
	if(bs) return json;
	else return null;
}
//-->
</SCRIPT>
</body>
</html>
