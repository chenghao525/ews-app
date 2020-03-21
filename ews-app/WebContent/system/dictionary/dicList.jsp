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
	if(!G.isOwnRight("19905000",request)){out.println("没有权限或会话已失效");return;}
%>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"> 
<div class="mini-toolbar" style="border-bottom:0;padding:0px;">
	<table style="width:100%;"><tr><td style="width:100%;text-align:left;">
		<%
			if(G.isOwnRight("19905001",request)){
		%>
			<a class="mini-button" iconCls="icon-add" onclick="addRow();" plain="true">新增</a>
			<script type="text/javascript">
			<!--
				function addRow(){var newRow = { name: "New Row" };grid.addRow(newRow, 0);}
			//-->
			</script>
		<%	}%>
		<%
			if(G.isOwnRight("19905003",request)){
		%>
			<a class="mini-button" iconCls="icon-remove" onclick="removeRow()" plain="true">删除</a>
			<script type="text/javascript">
			<!--
				function removeRow() {
					var rows = grid.getSelecteds();
					if (rows.length > 0) {
						mini.confirm("您确认删除？","请确认",function(action){
							if (action == "ok"){
								var sid="";
								for(var i=0;i<rows.length;i++){sid+=rows[i].SID+",";}
								grid.loading("删除中，请稍后......");
								ajaxRequest("/AjaxService?method=RemoveCommonList&code=RemoveDicList",sid,function(){gridIniLoad();});
							}
						});
					}
				}
			//-->
			</script>
		<%	}%>
		<%
			if(G.isOwnRight("19905004",request)){
		%>
			<a class="mini-button" iconCls="icon-save" onclick="saveData()" plain="true">保存</a>
			<script type="text/javascript">
			<!--
				function saveData() {
					var rows = grid.getSelecteds();
					if (rows.length > 0) for(var i=0;i<rows.length;i++){grid.validateRow(rows[i]);}
					else{
						mini.alert("请选中您要保存的行");
						return false;
					}

					if (grid.isValid() == false) {
						mini.alert("请输入必填内容（红底色）");
						return false;
					}
					var json = mini.encode(rows);
					mini.confirm("您确认保存？","请确认",function(action){
						if (action == "ok"){
							grid.loading("保存中，请稍后......");
							ajaxRequest("/AjaxService?method=SaveCommonList&code=SaveDicList",json,function(){
								grid.unmask();
								//gridIniLoad();
								mini.alert("保存成功");
							});
						}
					});
				}
			//-->
			</script>
		<%	}%>

		<span class="separator"></span>
		<a class="mini-button" iconCls="icon-reload" onclick="reset();gridIniLoad()" plain="true">刷新</a>
	</td></tr></table>
</div>
<div class="mini-toolbar" style="border-bottom:0;padding:0px;">
	<form id="formsearch" style="margin-bottom:0">
	<table style="width:100%;"><tr><td style="width:100%;text-align:left;">
		SID：<input id="S_SID" class="mini-textbox" maxLength="50" style="width:100px;" onenter="search"/> 
		<span class="separator"></span>
		内容：<input id="S_CONTENT" class="mini-textbox" maxLength="50" style="width:100px;" onenter="search"/>
		<span class="separator"></span>
		类别：<input id="S_INFOTYPE" class="mini-textbox" maxLength="50" style="width:100px;" onenter="search"/>
		<span class="separator"></span>
		引用类别：<input id="S_REFTYPE" class="mini-textbox" maxLength="50" style="width:100px;" onenter="search"/>
		<span class="separator"></span>
		键值：<input id="S_KEYVALUE" class="mini-textbox" maxLength="50" style="width:100px;" onenter="search"/>
		</tr></td>
		<tr><td style="width:100%;text-align:left;">
		备注：<input id="S_MEMO" class="mini-textbox" maxLength="50" style="width:100px;" onenter="search"/>
		<span class="separator"></span>
		父类别：<input id="S_PARENT" class="mini-textbox" maxLength="50" style="width:100px;" onenter="search"/>
		<span class="separator"></span>
		code：<input id="S_CODE" class="mini-textbox" maxLength="50" style="width:100px;" onenter="search"/>
		<span class="separator"></span>
		参数：<input id="S_PARAMS" class="mini-textbox" maxLength="50" style="width:100px;" onenter="search"/>
		<span class="separator"></span>
		<a class="mini-button" onclick="reset()">重置</a>&nbsp;&nbsp;<a class="mini-button" onclick="search()">查询</a>
	</td></tr></table>
	</form>
</div>
<div class="mini-fit">
	<div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" idField="SID" allowResize="false" sizeList="[50,100]"  pageSize="50" allowCellEdit="true" allowCellSelect="true" multiSelect="true" allowCellValid="true" sortMode="server" url="">
		<div property="columns">
			<div type="indexcolumn" width="20"></div>
			<div type="checkcolumn" width="20"></div>
			<div field="SID" width="40" allowSort="true">SID
				<input property="editor" class="mini-spinner" minValue="1" maxValue="50000" style="width:100%;"/>
			</div>
			<div field="CONTENT" width="150">内容
				<input property="editor" class="mini-textbox" style="width:100%;"/>
			</div>
			<div field="INFOTYPE" width="40" allowSort="true">类别
				<input property="editor" class="mini-spinner" minValue="1" maxValue="500" style="width:100%;"/>
			</div>
			<div field="OPTORDER" width="40" allowSort="true">排序
				<input property="editor" class="mini-spinner" minValue="1" maxValue="200" style="width:100%;"/>
			</div>
			<div field="REFTYPE" width="50" allowSort="true">引用类别
				<input property="editor" class="mini-spinner" minValue="1" maxValue="50000" style="width:100%;"/>
			</div>
			<div field="KEYVALUE" width="40" allowSort="true">键值
				<input property="editor" class="mini-textbox" style="width:100%;"/>
			</div>
			<div field="MEMO" width="150">备注
				<input property="editor" class="mini-textbox" style="width:100%;"/>
			</div>
			<div field="PARENT" width="40" allowSort="true">父类别
				<input property="editor" class="mini-spinner" minValue="1" maxValue="50000" style="width:100%;"/>
			</div>
			<div field="CODE" width="40" allowSort="true">code
				<input property="editor" class="mini-textbox" style="width:100%;"/>
			</div>
			<div field="PARAMS" width="250" allowSort="true">参数
				<input property="editor" class="mini-textbox" style="width:100%;"/>
			</div>
			<div field="UPTDATE" width="100" dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">更新时间</div>
		</div>
	</div>
</div>

<SCRIPT LANGUAGE="JavaScript">
<!--
var _total=null;
mini.parse();
var grid = mini.get("datagrid1");

function gridIniLoad(json){
	grid.url="/AjaxService?method=GetCommonList&code=GetDicList";
	grid.load(json);
	grid.deselectAll();
}

gridIniLoad();

function reset(){var form = new mini.Form("#formsearch");form.reset();}
function search() {gridIniLoad(getSearchJson());}
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
//-->
</SCRIPT>
</body>
</html>
