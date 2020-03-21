<%@ page contentType="text/html; charset=UTF-8" import="com.corp.oa.util.G,com.corp.servlet.common.CommonUtils"%>

<%
	if(G.isNvl(_inputWidth)) _inputWidth="500px";
	else if(G.isNumeric(_inputWidth)) _inputWidth+="%";
%>

<input id="PROV_LIST" name="PROV_LIST" displayField="PROV_LIST_NAME" class="mini-lookup" style="width:<%=_inputWidth%>;" textField="PROV_NAME_SUM" valueField="SID" popupWidth="auto" popup="#_provGridPanel" grid="#_provLookDataGrid" multiSelect="true" value="" text="" emptyText="请选择商户"/>

<!--选择面板-->
<div id="_provGridPanel" class="mini-panel" title="选择商户" iconCls="icon-add" style="width:560px;height:200px;" visible="false" showToolbar="true" showCloseButton="true" showHeader="true" bodyStyle="padding:0" allowResize="true" borderStyle="border:0">
	<div property="toolbar" style="padding:5px;padding-left:3px;text-align:left;">   
		<span>简称：<input id="S_PROV_NAME_SUM" class="mini-textbox" style="width:60px;" onenter="onSearchClick"/>&nbsp;</span><span>全称：<input id="S_PROV_NAME" class="mini-textbox" style="width:60px;" onenter="onSearchClick"/>&nbsp;</span><span>分类：<input id="S_PROV_TYPE" class="mini-combobox" style="width:120px;" url="/data/provtype.txt" showNullItem="true" onenter="onSearchClick"/></span><span>城市：<input id="S_CITY_NAME" class="mini-textbox" style="width:60px;" onenter="onSearchClick"/></span><a class="mini-button" onclick="onClearClick">清除</a>&nbsp;<a class="mini-button" onclick="onSearchClick">查询</a>
	</div>
	<div class="mini-fit">
		<div id="_provLookDataGrid" class="mini-datagrid" style="width:100%;height:100%;" borderStyle="border:0"  showPageIndex="false" idField="SID" sizeList="[100,200,500]" pageSize="100"  url='/AjaxService?method=GetCommonList&code=<%=CommonUtils.CODE_PROV_GRID_LIST%>'>
			<div property="columns">
				<div type="checkcolumn" width="15"></div>
				<div type="indexcolumn" width="15"></div>
				<div field="PROV_NAME" width="100" allowSort="true">商户</div>
				<div field="PROV_NAME_SUM" width="100" allowSort="true">简称</div>
				<div type="comboboxcolumn" field="PROV_TYPE" width="80" allowSort="true">分类
					<input property="editor" class="mini-combobox" style="width:100%;" url="/data/provtype.txt"/>
				</div>
				<div field="CITY_NAME" width="60" allowSort="true">城市</div>                
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
<!--
mini.parse();
var _provLookGrid=mini.get("_provLookDataGrid");
//_provLookGrid.load();
function onSearchClick(e){_provLookGrid.load(getSearchJson());}
function onClearClick(e){
	var obj=mini.get("S_PROV_NAME_SUM");
	obj.setValue("");
	obj=mini.get("S_PROV_NAME");
	obj.setValue("");
	obj=mini.get("S_PROV_TYPE");
	obj.setValue("");
	obj=mini.get("S_CITY_NAME");
	obj.setValue("");

	try{suffixSetting();}catch(e){}
}
function getSearchJson(){

	try{suffixSetting();}catch(e){}

	var json={},obj=null;
	var bs=false;
	obj=mini.get('S_PROV_NAME_SUM');
	if(obj){json[obj.id]=obj.value;bs=true;}

	obj=mini.get('S_PROV_NAME');
	if(obj){json[obj.id]=obj.value;bs=true;}
	
	obj=mini.get('S_PROV_TYPE');
	if(obj){json[obj.id]=obj.value;bs=true;}

	obj=mini.get('S_CITY_NAME');
	if(obj){json[obj.id]=obj.value;bs=true;}

	if(bs) return json;
	else return null;
}
//-->
</script>