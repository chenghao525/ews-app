<%@ page contentType="text/html; charset=UTF-8" import="com.corp.oa.util.G,com.corp.servlet.common.CommonUtils"%>
<html>
<HEAD><TITLE></TITLE>
<script type="text/javascript" src="/js/js.js?r=1220"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
<LINK href="/images/css.css" type="text/css" rel="STYLESHEET">
</HEAD>

<BODY width="100%" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div style="width:100%;">
	<div class="mini-toolbar" style="border-bottom:0;padding:0px;">
		<table style="width:100%;">
			<tr><td style="width:100%;text-align:left;">
				<a class="mini-button" iconCls="icon-ok" onclick="onOk()" plain="true">选择确认</a>
				<span class="separator"></span>
				<a class="mini-button" iconCls="icon-cancel" onclick="CloseWindow()" plain="true">取消</a>
			</td></tr>
		</table>
	</div>
</div>
<div style="width:100%;" id="search-div">
	<div class="mini-toolbar" style="border-bottom:0;padding:0px;">
		<form id="formsearch" style="margin-bottom:0">
		<table>
			<tr style="text-align:left;float:left;"><td>
				商户编号：<input id="S_PROV_ID" class="mini-textbox" style="width:80px;" onenter="search"/>
				<span class="separator"></span>
				简称：<input id="S_PROV_NAME_SUM" class="mini-textbox" style="width:80px;" onenter="search"/>
				<span class="separator"></span>
				全称：<input id="S_PROV_NAME" class="mini-textbox" style="width:80px;" onenter="search"/>
				<span class="separator"></span>
				分类：<input id="S_PROV_TYPE" class="mini-combobox" style="width:100px;" url="/data/provtype.txt"  showNullItem="true" onenter="search"/>
				<span class="separator"></span>
				城市：<input id="S_CITY_NAME" class="mini-textbox" style="width:80px;" onenter="search"/>
				<span class="separator"></span>
				<a class="mini-button" onclick="reset">重置</a>&nbsp;<a class="mini-button" onclick="search">查询</a>
			</td></tr>
		</table></form>
	</div>
</div>
<div class="mini-fit">
	<div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" idField="SID" allowResize="false" sizeList="[50,100]"  pageSize="50" allowCellEdit="false" allowCellSelect="false" multiSelect="true" allowCellValid="false" sortMode="server" url='/AjaxService?method=GetCommonList&code=<%=CommonUtils.CODE_PROV_GRID_LIST%>'>
		<div property="columns">
			<div type="indexcolumn" width="15"></div>
			<div type="checkcolumn" width="15"></div>
			<div field="PROV_ID" width="80" allowSort="true">商户编号</div>
			<div field="PROV_NAME" width="150" allowSort="true">商户</div>
			<div field="PROV_NAME_SUM" width="100" allowSort="true">简称</div>
			<div type="comboboxcolumn" field="PROV_TYPE" width="80" allowSort="true">分类
				<input property="editor" class="mini-combobox" style="width:100%;" url="/data/provtype.txt"/>
			</div>
			<div field="CITY_NAME" width="60" allowSort="true">城市</div>                
		</div>
	</div>
</div>

<script type="text/javascript">
<!--
var a = new Array();

mini.parse();
var grid = mini.get("datagrid1");

function onOk(){
	var rows = grid.getSelecteds();
	if (rows.length <= 0){mini.alert("请选择行");return false;}

	var rSid="",rId="",rName="";
	for(var i=0;i<rows.length;i++){
		rSid+=rows[i].SID+",";
		rId+=rows[i].PROV_ID+",";
		rName+=rows[i].PROV_NAME_SUM+",";
	}
	rSid=rSid.substring(0,rSid.length-1);
	rId=rId.substring(0,rId.length-1);
	rName=rName.substring(0,rName.length-1);
	
	a[0]=rSid;a[1]=rId;a[2]=rName;

	mini.confirm("您确认保存？", "请确认",
		function(action){
			if(action == "ok"){CloseWindow("ok");}
			else return false;
		}
	);
	return false;
}

function reset(){_formReset("formsearch");}
function search() {
	var json=_getGridSearch("formsearch");
	if(json==null||!json) grid.load();
	else grid.load(json);
}
function getData(){return a;}
function CloseWindow(action) {if (window.CloseOwnerWindow) window.CloseOwnerWindow(action); else window.close();}
function onCancel() {CloseWindow("cancel");}
//-->
</script>
</body>
</html>
