<%@ page contentType="text/html; charset=UTF-8" import="com.corp.oa.util.G" %>
<html>
<HEAD><title></title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type=text/css rel=stylesheet>
<script type="text/javascript" src="/js/js.js?r=1221"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
</HEAD>
<style type="text/css">
html, body{	margin:0;padding:0;border:0;width:100%;height:100%;overflow-x:hidden;}    
</style>
<body align="center" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%if(!G.isOwnRight("10202000",request)){out.println("没有权限或会话已失效");return;}%>
<div style="width:100%;">
	<div class="mini-toolbar" style="border-bottom:0;padding:0px;">
		<table style="width:100%;">
			<tr><td style="width:100%;text-align:left;">
			
			<%if(G.isOwnRight("10202001",request)){%>
				<a class="mini-button" iconCls="icon-find" onclick="provView()" plain="true">查看</a>
			<%}%>

			<%if(G.isOwnRight("10202002",request)){%>
				<a class="mini-button" iconCls="icon-add" onclick="provAdd()" plain="true">新增</a>
			<%}%>

			<%if(G.isOwnRight("10202003",request)){%>
				<a class="mini-button" iconCls="icon-edit" onclick="provEdit()" plain="true">修改</a>
			<%}%>

			<%if(G.isOwnRight("10202005",request)){%>
				<a class="mini-button" iconCls="icon-remove" onclick="provDel()" plain="true">删除</a>
			<%}%>
				<span class="separator"></span>
			<%if(G.isOwnRight("10202006",request)){%>
				<a class="mini-button" iconCls="icon-export" onclick="exportData()" plain="true">导出</a>
				<script type="text/javascript">
				<!--
					function exportData(){
						var json=_getGridSearch("formsearch");
						if(json==null){mini.alert("请输入查询条件对数据进行过滤");return;}
						showCenter('win1');
					}
				//-->
				</script>
			<%}%>
				<span class="separator"></span>
				<a class="mini-button" iconCls="icon-refresh" onclick="refreshGrid()" plain="true">刷新</a>
			</td></tr>
		</table>
	</div>
</div>
<div style="width:100%;">
	<div class="mini-toolbar" style="border-bottom:0;padding:0px;">
		<form id="formsearch" style="margin-bottom:0">
		<table>
			<tr style="text-align:left;float:left;"><td>
				内部编号：<input id="S_SID" class="mini-textbox" maxLength="50" style="width:100px;" onenter="search"/>
				<span class="separator"></span>
				商户编号：<input id="S_PROV_ID" class="mini-textbox" maxLength="50" style="width:100px;" onenter="search"/>
				<span class="separator"></span>
				名称：<input id="S_PROV_NAME" class="mini-textbox" maxLength="50" style="width:100px;" onenter="search"/> 
				<span class="separator"></span>
				简称：<input id="S_PROV_NAME_SUM" class="mini-textbox" maxLength="50" style="width:100px;" onenter="search"/> 
				<span class="separator"></span>
				地址：<input id="S_ADDR" class="mini-textbox" maxLength="50" style="width:100px;" onenter="search"/> 
			</td></tr>
			<tr style="text-align:left;float:left;"><td>
				商户类别：<input id="S_SRV_TYPE" class="mini-combobox" style="width:100px;" allowInput="true" url='/AjaxService?method=GetDicList4MU&data={"infotype":"17","order":"content"}}' onenter="search"/>
				<span class="separator"></span>
				省份：<input id="S_PROVINCE_NAME" class="mini-textbox" maxLength="50" style="width:100px;" onenter="search"/> 
				<span class="separator"></span>
				城市：<input id="S_CITY_NAME" class="mini-textbox" maxLength="50" style="width:100px;" onenter="search"/> 
				<span class="separator"></span>
				商户分类：<input id="S_PROV_TYPE" class="mini-combobox" style="width:150px;" allowInput="true" url="/data/provtype.txt" onenter="search"/>
				<span class="separator"></span>
				<a class="mini-button" onclick="reset()">重置</a>&nbsp;&nbsp;<a class="mini-button" onclick="search()">查询</a>
			</td></tr>
		</table></form>
	</div>
</div>
<div class="mini-fit">
	<div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" idField="SID" allowResize="false" sizeList="[20,30,50,100]"  pageSize="20" allowCellEdit="false" allowCellSelect="false" multiSelect="false" allowCellValid="true" sortMode="server" url="" onrowdblclick="provEdit">
		<div property="columns">
			<div type="indexcolumn" width="20"></div>
			<div type="checkcolumn" width="20"></div>
			<div field="SID" width="40" allowSort="true">内部编号</div>
			<div field="PROV_ID" width="80" allowSort="true">商户编号</div>
			<div field="PROV_NAME" width="120" allowSort="true">名称</div>
			<div field="PROV_NAME_SUM" width="120" allowSort="true">简称</div>
			<div field="ADDR" width="150" allowSort="true">地址</div>
			<div field="PROVINCE_NAME" width="40" allowSort="true">省份</div>
			<div field="CITY_NAME" width="40" allowSort="true">城市</div>
			<div type="comboboxcolumn" field="SRV_TYPE" width="60" readonly="true" allowSort="true">商户类别
				<input property="editor" class="mini-combobox" style="width:100%;" allowInput="true" url='/AjaxService?method=GetDicList4MU&data={"infotype":"17","order":"content"}'/>
			</div>
			<div type="comboboxcolumn" field="PROV_TYPE" width="150" readonly="true" allowSort="true">商户分类
				<input property="editor" class="mini-combobox" style="width:100%;" allowInput="true"  url="/data/provtype.txt"/>
			</div>
			<div field="CRT_TIME" width="100" dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">创建时间</div>
			<div field="UPTDATE" width="100" dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">更新时间</div>
		</div>
	</div>
</div>
<div id="win1" class="mini-window" title="导出列" style="width:600px;height:300px;" showMaxButton="false" showToolbar="false" showFooter="true" showModal="true" allowResize="false" allowDrag="true">
    <div property="footer" style="text-align:right;padding:1px;padding-right:15px;height:40px">
		<input type='button' class="button-ui" value='确 定' onclick="exportOk()" style='vertical-align:middle;'/>
		<input type='button' class="button-ui" value='取 消' onclick="hideWindow('win1')" style='vertical-align:middle;'/>
    </div>
	<div id="fldList" class="mini-checkboxlist" repeatItems="5" repeatLayout="table" style="margin-left:5;margin-bottom:8;"
        textField="text" valueField="id" value="SID,PROV_ID,PROV_NAME,PROV_NAME_SUM,PROVINCE_NAME,CITY_NAME,AREA_NAME,ADDR,PROV_TYPE_NAME,DLV_INFO,SRV_MAN_NAME,SRV_MAN_TEL,CRT_TIME" data="jldList">
    </div>
</div>
<SCRIPT LANGUAGE="JavaScript">
<!--
var jldList=[{id:"SID",text:"内部编号"},{id:"PROV_ID",text:"商户编号"},{id:"PROV_NAME",text:"商户全称"},{id:"PROV_NAME_SUM",text:"商户简称"},{id:"PROVINCE_NAME",text:"省份"},{id:"CITY_NAME",text:"城市"},{id:"AREA_NAME",text:"区县"},{id:"ADDR",text:"地址"},{id:"PROV_TYPE_NAME",text:"商户分类"},{id:"DLV_INFO",text:"收货人"},{id:"SRV_MAN_NAME",text:"SA姓名"},{id:"SRV_MAN_TEL",text:"SA电话"},{id:"CRT_TIME",text:"创建时间"}];

mini.parse();

var grid = mini.get("datagrid1");
grid.url="/AjaxService?method=GetCommonList&code=GetProviderList";
grid.load();
grid.deselectAll();

function refreshGrid(){search();}
function search() {
	var json=_getGridSearch("formsearch");
	grid.url="/AjaxService?method=GetCommonList&code=GetProviderList";
	grid.load(json);
}
function reset(){var form = new mini.Form("#formsearch");form.reset();}

function provAdd(){provOpt("b_add","add");}
function provEdit(){provOpt("b_edit","edit");}
function provView(){provOpt("b_see","see");}
function provDel(){
	var row=grid.getSelected();
	if(!row){mini.alert("请选择行");return;}
	var sid=row.SID;
	mini.confirm("确认删除此商户？","请确认",function(action){
		if (action == "ok"){
			grid.loading("正在提交数据，请稍后 ...", "提交");
			ajaxRequest("/AjaxService?method=RemoveCommonList&code=RemoveProvider",sid,refreshGrid);
		}
	});
}

function provOpt(cmd,opt){
	var row=grid.getSelected();
	var sid="";
	if(!row&&opt!="add"){mini.alert("请选择行");return;}
	else if(opt!="add"){sid=row.SID;}
	mini.open({
		url: "/provider/provider.do?"+cmd+"=true&sid="+sid+"&opt="+opt,                          
		title: "服务商",
		iconCls: "icon-user", 
		width: '100%',
		height: '100%',
		allowResize:false,
		allowDrag:false});
}
function exportOk(){
	mini.confirm("您确认现在导出数据？","请确认",function(action){
		if (action == "ok"){
			hideWindow('win1');
			var obj = mini.get("fldList");
			var fl=obj.getValue();
			var json=_getGridSearch("formsearch");

			if(!json){mini.alert("请输入查询条件对数据进行过滤");return;}

			grid.loading("导出中，请稍后......");
			findDataExport('/AjaxService?method=ExportProvList&FLD_LIST='+fl+'&data='+json2str(json));
			grid.unmask();
		}
	});
}
function showCenter(win) {var win = mini.get(win);win.show();}
function hideWindow(win) {var win = mini.get(win);win.hide();}
//-->
</SCRIPT>

</body>
</html>