<%@ page contentType="text/html; charset=UTF-8" import="com.corp.oa.util.G" %>

<html>
<HEAD>
<title></title>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type="text/css" rel="STYLESHEET"> 
<script language="JavaScript" src="/js/js.js?r=1221"></script>
<script type="text/javascript" src="/js/sort.js"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
</head>
<%
	if(!G.isOwnRight("19906000",request)){out.println("没有权限或会话已失效");return;}
%>
<body align="center" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div style="width:100%;">
	<div class="mini-toolbar" style="border-bottom:0;padding:0px;">
		<table style="width:100%;">
			<tr><td style="width:100%;text-align:left;">
			<%if(G.isOwnRight("19906001",request)){%>
				<a class="mini-button" iconCls="icon-add" onclick="addRow()" plain="true">新增</a>
				<a class="mini-button" iconCls="icon-remove" onclick="removeRow()" plain="true">删除</a>
				<a class="mini-button" iconCls="icon-save" onclick="saveData()" plain="true">保存</a>
			<%}%>
				<span class="separator"></span>
				<a class="mini-button" iconCls="icon-reload" onclick="refreshGrid()" plain="true">刷新</a>
			</td></tr>
		</table>
	</div>
</div>
<div class="mini-toolbar" style="border-bottom:0;padding:0px;">
	<form id="formsearch" style="margin-bottom:0">
	<table>
		<tr style="text-align:left;float:left;"><td>
			key：<input id="S_ATTRNAME" class="mini-textbox" maxLength="50" style="width:150px;" onenter="search"/> 
			<span class="separator"></span>
			value：<input id="S_ATTRVALUE" class="mini-textbox" maxLength="50" style="width:150px;" onenter="search"/>
			<span class="separator"></span>
			描述：<input id="S_MEMO" class="mini-textbox" maxLength="50" style="width:200px;" onenter="search"/>
			<span class="separator"></span>
			<a class="mini-button" onclick="reset()">重置</a>&nbsp;<a class="mini-button" onclick="search()">查询</a>
		</td></tr>
	</table></form>
</div>
<div class="mini-fit">
	<div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" idField="ATTRNAME" allowResize="false" sizeList="[50,100]"  pageSize="50" allowCellEdit="true" allowCellSelect="true" multiSelect="true" allowCellValid="false" sortMode="server" url="">
		<div property="columns">
			<div type="indexcolumn" width="20"></div>
			<div type="checkcolumn" width="20"></div>
			<div field="ATTRNAME" width="100" allowSort="true" vtype="required">key
				<input property="editor" class="mini-textbox" style="width:100%;"/>  
			</div>
			<div field="ATTRVALUE" width="300" allowSort="true">value
				<input property="editor" class="mini-textbox" style="width:100%;"/>
			</div>
			<div field="MEMO" width="200" allowSort="true">描述
				<input property="editor" class="mini-textbox" style="width:100%;"/>
			</div>
		</div>
	</div>
</div>

<SCRIPT LANGUAGE="JavaScript">
<!--
mini.parse();
var grid = mini.get("datagrid1");
grid.url="/AjaxService?method=GetCommonList&code=GetSystemInfoList";
grid.load();
grid.deselectAll();

function reset(){var form = new mini.Form("#formsearch");form.reset();}
function search() {
	var json=getSearchJson();
	if(json!=null){grid.load(json);}
	else{grid.load();}
}
function getSearchJson(){
	var form = new mini.Form("#formsearch");
	var fields = form.getFields();
	var ui,v,obj;
	var json={};
	var bs=false;
	for(var i=0;i<fields.length;i++){
		 ui = fields[i];
		 v=ui.value;
		 if(nvl(v)!=""){
			 bs=true;
			 json[ui.id]=v;
		}
	}
	if(bs) return json;
	else return null;
}
function refreshGrid(){grid.reload();grid.deselectAll();}
function addRow(){var newRow = { name: "New Row" };grid.addRow(newRow, 0);}
function saveData() {
	var rows = grid.getSelecteds();
	if (rows.length > 0) for(var i=0;i<rows.length;i++){grid.validateRow(rows[i]);}
	else{
		alert("请选中您要保存的行");
		return false;
	}

	if (grid.isValid() == false) {
		alert("请输入必填内容（红底色）");
		return false;
	}
	var json = mini.encode(rows);
	mini.confirm("您确认保存？","请确认",function(action){
		if (action == "ok"){
			grid.loading("保存中，请稍后......");
			ajaxRequest("/AjaxService?method=SaveCommonList&code=SaveSystemInfoList",json,refreshGrid);
		}
	});
}
function removeRow() {
	var rows = grid.getSelecteds();
	if (rows.length > 0) {
		mini.confirm("您确认删除？","请确认",function(action){
			if (action == "ok"){
				var sid="";
				for(var i=0;i<rows.length;i++){sid+=rows[i].ATTRNAME+",";}
				grid.loading("删除中，请稍后......");
				ajaxRequest("/AjaxService?method=RemoveCommonList&code=RemoveSystemInfoList",sid,refreshGrid);
			}
		});
	}
}
//-->
</SCRIPT>
</body>
</html>