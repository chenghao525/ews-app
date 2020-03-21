<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.corp.oa.util.G, com.corp.ews.report.BungleRptUtil" %>
<%@ page import="com.corp.ews.EWSConstant" %>
<%
    if (!G.isOwnRight("13101200", request)) { out.print("会话已失效或无权限"); return; }
    String mixedDate = request.getParameter("mixedDate");
    if (G.isNvl(mixedDate)) {
        mixedDate = G.getCurrentDate();
    } else {
        mixedDate = mixedDate + "-03";
    }
    BungleRptUtil utl = new BungleRptUtil();

    String firstDt = utl.dayOfTheMonth(mixedDate, false);
    String lastDt = utl.dayOfTheMonth(mixedDate, true);
%>
<html>
<head>
    <title>工单管理</title>
    <META http-equiv=Content-Type content="text/html; charset=UTF-8">
    <LINK href="/images/css.css" type=text/css rel=stylesheet>
    <script type="text/javascript" src="/js/js.js?r=1221"></script>
    <link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
    <script src="/scripts/boot.js" type="text/javascript"></script>
    <style type="text/css">
        html, body{	margin:0;padding:0;border:0;width:100%;height:100%;overflow-x:hidden;}
    </style>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
    <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
        <% if (G.isOwnRight("13101201", request)) {%>
        <a class="mini-button" iconCls="icon-check" plain="true" onclick="goDetails()">查看</a>
        <span class="separator"></span>
        <%}%>
        <% if (G.isOwnRight("13101202", request)) {%>
        <a class="mini-button" iconCls="icon-eps-export" plain="true" onclick="exportList()">导出</a>
        <span class="separator"></span>
        <script type="text/javascript">
            function exportList() {
                var json = _getGridSearch("formsearch2");
                if (!json) {
                    mini.alert("请输入筛选条件");
                    return;
                }
                show("win1");
            }
        </script>
        <%}%>
        <a class="mini-button" iconCls="icon-refresh" plain="true" onclick="search(2)">刷新</a>
    </div>
    <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
        <form id="formsearch2" style="margin-bottom:0">
            <table>
                <tr><td>
                    门店：<input id="S_PROV_SID" class="mini-combobox" showNullItem="true" nullItemText="请选择" url='/AjaxService?method=GetProviders' style="width:150px;" onenter="search(2)"/>
                    <span class="separator"></span>
                    工单号：<input id="S_RO_NUMBER" class="mini-textbox"  style="width:100px;" onenter="search(2)"/>
                    <span class="separator"></span>
                    车牌号：<input id="S_VEHICLE_LICENSE" class="mini-textbox"  style="width:100px;" onenter="search(2)"/>
                    <span class="separator"></span>
                    服务技师：<input id="S_MECHANIC_ID" class="mini-combobox" showNullItem="true" nullItemText="请选择" url='/AjaxService?method=GetMechanics' style="width:100px;" onenter="search(2)"/>
                    <span class="separator"></span>
                    调漆员：<input id="S_PAINT_MAKER_ID" class="mini-combobox" showNullItem="true" nullItemText="请选择" url='/AjaxService?method=GetMechanics&data={"work":"<%=EWSConstant.PAINT_MAKER%>"}' style="width:100px;" onenter="search(2)"/>
                </td></tr>
                <tr><td>
                    色号：<input id="S_PPG_BRAND_CODE" class="mini-textbox" style="width:100px;" onenter="search(2)"/>
                    <span class="separator"></span>
                    工单状态：<input id="S_REWORK_TYPE" class="mini-combobox" showNullItem="true" nullItemText="请选择" url='/AjaxService?method=GetDicList4MU&data={"infotype":"51"}' maxLength="50" style="width:100px;" onenter="search(2)"/>
                    <span class="separator"></span>
                    调色时间：<input id="S_MIXED_DATE_ST" class="mini-datepicker" format="yyyy-MM-dd HH:mm" timeFormat="HH:mm" showTime="true" value="<%=firstDt%>"
                                showOkButton="true" showClearButton="false" style="width:140px;" onenter="search(2)"/>
                    ~<input id="S_MIXED_DATE_ED" class="mini-datepicker" format="yyyy-MM-dd HH:mm" timeFormat="HH:mm" showTime="true" value="<%=lastDt%>"
                            showOkButton="true" showClearButton="false" \  style="width:140px;" onenter="search(2)"/>
                    <span class="separator"></span>
                    <a class="mini-button" onclick="reset(2)">重置</a>&nbsp;&nbsp;<a class="mini-button" onclick="search(2)">查询</a>
                </td></tr>
            </table></form>
    </div>
    <div class="mini-fit">
            <div id="roGrid2" class="mini-datagrid" style="width:100%;height:100%;" pageSize="50" allowCellSelect="true"
                 sizeList="[20,30,50,100]" onrowdblclick="goDetails">
                <div property="columns">
                    <div type="indexcolumn" width="20"></div>
                    <div type="checkcolumn" width="20"></div>
                    <div field="EWS_RO_CODE" width="30" headerAlign="center">EWS工单号</div>
                    <div type="comboboxcolumn" field="PROV_SID" width="40" headerAlign="center" readonly="true">门店
                        <input property="editor" class="mini-combobox" style="width:100%;" allowInput="false" url='/AjaxService?method=GetProviders'/>
                    </div>
                    <div field="RO_NUMBER" width="30" headerAlign="center">工单号</div>
                    <div field="VEHICLE_LICENSE" width="30" headerAlign="center">车牌号</div>
                    <div field="WEIGHT_TOTAL" width="30" decimalPlaces="2" dataType="float" align="right" headerAlign="center">色母消耗(g)</div>
                    <div field="COLOR_CODE" width="30" headerAlign="center">色号</div>
                    <div field="COLOR_NAME" width="30" headerAlign="center">车身颜色</div>
                    <div type="comboboxcolumn" field="CAR_BRAND" headerAlign="center" width="40" readonly="true">车辆品牌
                        <input property="editor" class="mini-combobox" style="width:100%;" url='/AjaxService?method=GetCarBrandList'/>
                    </div>
                    <div field="SHEET_METAL_COUNT" width="30" decimalPlaces="1" dataType="float" align="right" headerAlign="center">钣面数</div>
                    <div field="PAINT_MAKER" width="30" headerAlign="center">调漆技师</div>
                    <div field="MECHANIC_NAME" width="30" headerAlign="center">技师</div>
                    <div type="comboboxcolumn" field="REWORK_TYPE" headerAlign="center" width="40" readonly="true">工单状态
                        <input property="editor" class="mini-combobox" style="width:100%;" url='/AjaxService?method=GetDicList4MU&data={"infotype":"51"}'/>
                    </div>
                    <div field="MIXED_DATE" width="50" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">调色时间</div>
                </div>
            </div>
        </div>
<div id="win1" class="mini-window" title="导出列" style="width:600px;height:300px;" showMaxButton="false" showToolbar="false" showFooter="true" showModal="true" allowResize="false" allowDrag="true">
    <div property="footer" style="text-align:right;padding:1px;padding-right:15px;height:40px">
        <input type='button' class="button-ui" value='确 定' onclick="exportOk()" style='vertical-align:middle;'/>
        <input type='button' class="button-ui" value='取 消' onclick="hide('win1')" style='vertical-align:middle;'/>
    </div>
    <div id="fldList" class="mini-checkboxlist" repeatItems="5" repeatLayout="table" style="margin-left:5;margin-bottom:8;"
         textField="text" valueField="id" value="EWS_RO_CODE,RO_NUMBER,VEHICLE_LICENSE,COLOR_CODE,COLOR_NAME,WEIGHT_TOTAL,CAR_BRAND,SHEET_METAL_COUNT,PAINT_MAKER,MECHANIC_NAME,REWORK_TYPE_NAME,MIXED_DATE" data="jldList">
    </div>
</div>

<script type="text/javascript">
    var jldList = [{id: "EWS_RO_CODE", text: "EWS工单号"},{id: "RO_NUMBER", text: "工单号"}, {id: "VEHICLE_LICENSE", text: "车牌号"}, {id: "COLOR_CODE", text: "色号"},
        {id: "COLOR_NAME", text: "车身颜色"}, {id: "WEIGHT_TOTAL", text: "色母消耗(g)"},{id: "CAR_BRAND", text: "车辆品牌"}, {id: "SHEET_METAL_COUNT", text: "钣面数"},
        {id: "PAINT_MAKER", text: "调漆员"}, {id: "MECHANIC_NAME", text: "技师"}, {id: "REWORK_TYPE_NAME", text: "工单状态"},
        {id: "MIXED_DATE", text: "调色时间"}];

    mini.parse();
    var dataList = [];
    var grid2 = mini.get("roGrid2");


    function hide(win) {
        var windows = mini.get(win);
        windows.hide();
    }
    function show(win) {
        var windows = mini.get(win);
        windows.show();
    }
    function reset(idx) {
        var searchId = "formsearch" + idx;
        _formReset(searchId);
    }
    function search(idx) {
        var searchId = "formsearch" + idx;
        var json = _getGridSearch(searchId, "S_MIXED_DATE_ST,S_MIXED_DATE_ED");
        if (!json) {
            mini.alert("请输入筛选条件");
            return;
        }
        switch (idx) {
            case 1:
                loadList(json);
                break;
            case 2:
                loadGrid(json);
                break;
            default:
        }
    }

    function loadGrid(json) {

        grid2.url = "/AjaxService?method=GetCommonList&code=GetRepairOrders&repair=yes"
        grid2.load(json);
    }

    function activeChange(e) {
        var tab = e.tab;
        var name = tab.name;
        var myGrid = eval(name);
        if(!myGrid) return;
        if(myGrid.getUrl() == ""){
            // if(name == "grid2") loadGrid();
        }
    }
    search(2);

    function goDetails() {
        <% if (G.isOwnRight("13101201", request)) {%>
        var row = grid2.getSelected();
        if (!row) {
            mini.alert("请选择数据");
            return;
        }
        mini.open({
            title: "工单详情",
            iconCls: "icon-node",
            url: "/ews/ro/roDetails.jsp?roCode=" + row.EWS_RO_CODE,
            width: "80%",
            height: "70%"
        });
        <%}%>
    }
    function exportOk() {

        var json = _getGridSearch("formsearch2");
        if(!json){mini.alert("请输入查询条件对数据进行过滤");return;}

        var obj = mini.get("fldList");
        var fl = obj.getValue();
        if (!fl) {
            mini.alert("请选择列");
            return;
        }
        mini.confirm("您确认现在导出数据？","请确认",function(action){
            if (action == "ok"){
                hide('win1');

                grid2.loading("导出中，请稍后......");
                findDataExport('/AjaxService?method=ExportROList&FLD_LIST='+fl+'&data='+json2str(json));
                grid2.unmask();
            }
        });

    }

</script>

</body>
</html>

