<%@ page contentType="text/html; charset=UTF-8" import="com.corp.oa.util.G,com.corp.servlet.common.CommonUtils"%>

<!--选择面板-->
<div id="_provGridPanel" class="mini-panel" title="选择商户" iconCls="icon-add" style="width:600px;height:300px;" visible="false" showToolbar="true" showCloseButton="true" showHeader="true" bodyStyle="padding:0" borderStyle="border:0" allowResize="true">
	<div property="toolbar" style="padding:5px;padding-left:3px;text-align:left;">
		<form id="_provLookupSearch" style="margin-bottom:0">
			<span>全称：<input id="S_PROV_NAME" class="mini-textbox" style="width:80px;" onenter="onSearchClick"/>&nbsp;</span><span>简称：<input id="S_PROV_NAME_SUM" class="mini-textbox" style="width:80px;" onenter="onSearchClick"/>&nbsp;</span><span>分类：<input id="S_PROV_TYPE" class="mini-combobox" style="width:100px;" url="/data/provtype.txt"  showNullItem="true" onenter="onSearchClick"/></span><span>城市：<input id="S_CITY_NAME" class="mini-textbox" style="width:80px;" onenter="onSearchClick"/>&nbsp;</span>
			<a class="mini-button" onclick="onClearClick">清除</a>&nbsp;<a class="mini-button" onclick="onSearchClick">查询</a>
		</form>
	</div>
	<div class="mini-fit">
		<div id="_provLookDataGrid" class="mini-datagrid" style="width:100%;height:100%;" borderStyle="border:0" showPageSize="true" showPageIndex="true" idField="SID" sizeList="[100,200,500]" pageSize="100" url=''>
			<div property="columns">
				<div type="checkcolumn" width="15"></div>
				<div type="indexcolumn" width="15"></div>
				<div field="PROV_NAME" width="100" allowSort="true">全称</div>
				<div field="PROV_NAME_SUM" width="100" allowSort="true">简称</div>
				<div type="comboboxcolumn" field="PROV_TYPE" width="90" allowSort="true">分类
					<input property="editor" class="mini-combobox" style="width:100%;" url="/data/provtype.txt"/>
				</div>
				<div field="CITY_NAME" width="50" allowSort="true">城市</div>                
			</div>
		</div>  
	</div>
</div>

<script type="text/javascript">
<!--
mini.parse();
var _provLookGrid=mini.get("_provLookDataGrid");
function onShowPopup(e){
	_provLookGrid.url="/AjaxService?method=GetCommonList&code=<%=CommonUtils.CODE_PROV_GRID_LIST%>";
	_provLookGrid.load();
}
function onShowPopupScope(e){
	var obj=mini.get("datagrid1");
	var sender=e.sender;
	var row=obj.getEditorOwnerRow(sender);
	_provLookGrid.url="/AjaxService?method=GetCommonList&code=<%=CommonUtils.CODE_PROV_GRID_LIST%>&giftId="+row.GIFT_SID;
	_provLookGrid.load();
}
function onSearchClick(e){_provLookGrid.load(_getGridSearch("_provLookupSearch"));}
function onClearClick(e){_formReset("_provLookupSearch");}
//-->
</script>