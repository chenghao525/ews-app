<%@ page import="com.corp.ews.EWSConstant" %>
<%@ page import="com.corp.basic.Constant" %>
<%
    if (!G.isOwnRight("13802000", request)) { out.print("会话已失效或无权限"); return; }
    String empType = (String)request.getSession().getAttribute("EMP_TYPE");
%>
<html>
<head>
    <title>u8c同步失败的工单</title>
</head>
<body align="center" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
    <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
        <% if (G.isOwnRight("13802001", request)) {%>
        <a class="mini-button" iconCls="icon-check" plain="true" onclick="againSync()">再次同步</a>
        <span class="separator"></span>
        <%}%>
        <a class="mini-button" iconCls="icon-refresh" plain="true" onclick="searchU8cList()">刷新</a>
    </div>
    <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
        <form id="formsearch" style="margin-bottom:0">
            <table>
                <tr><td>
                    <%if(EWSConstant.MANAGER.equals(empType)) {%>
                    门店：<input id="S_PROV_SID" class="mini-combobox" showNullItem="true" nullItemText="请选择" url='/AjaxService?method=GetProviders' style="width:150px;" onenter="searchU8cList()"/>
                    <span class="separator"></span>
                    <%}%>
                    调色时间：<input id="S_MIXED_DATE_ST" class="mini-datepicker" format="yyyy-MM-dd HH:mm" timeFormat="HH:mm" showTime="true"
                                showOkButton="true" showClearButton="false" style="width:140px;" onenter="searchU8cList()"/>
                    ~<input id="S_MIXED_DATE_ED" class="mini-datepicker" format="yyyy-MM-dd HH:mm" timeFormat="HH:mm" showTime="true"
                            showOkButton="true" showClearButton="false"   style="width:140px;" onenter="searchU8cList()"/>
                    <span class="separator"></span>
                    <a class="mini-button" onclick="reset()">重置</a>&nbsp;&nbsp;<a class="mini-button" onclick="searchU8cList()">查询</a>
                </td></tr>
            </table></form>
    </div>
    <div class="mini-fit">
        <div id="u8cGrid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="50" allowCellSelect="true"
             sizeList="[20,30,50,100]">
            <div property="columns">
                <div type="indexcolumn" width="20"></div>
                <div type="checkcolumn" width="20"></div>
                <div field="EWS_RO_CODE" width="30" headerAlign="center">EWS工单号</div>
                <%--<div type="comboboxcolumn" field="PROV_SID" width="40" headerAlign="center" readonly="true">门店--%>
                    <%--<input property="editor" class="mini-combobox" style="width:100%;" allowInput="false" url='/AjaxService?method=GetProviders'/>--%>
                <%--</div>--%>
                <div field="RO_NUMBER" width="30" headerAlign="center">工单号</div>
                <div field="VEHICLE_LICENSE" width="30" headerAlign="center">车牌号</div>
                <div field="WEIGHT_TOTAL" width="30" decimalPlaces="2" dataType="float" align="right" headerAlign="center">色母消耗(g)</div>
                <div field="COLOR_CODE" width="30" headerAlign="center">色号</div>
                <div type="comboboxcolumn" field="REWORK_TYPE" headerAlign="center" width="40" readonly="true">工单状态
                    <input property="editor" class="mini-combobox" style="width:100%;" url='/AjaxService?method=GetDicList4MU&data={"infotype":"51"}'/>
                </div>
                <div field="MIXED_DATE" width="50" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">调色时间</div>
            </div>
        </div>
    </div>

<script type="text/javascript">
    mini.parse();
    var gridu8 = mini.get("u8cGrid");

    function reset() {
        _formReset("formsearch");
    }
    function searchU8cList(isLoad) {
        var json = _getGridSearch("formsearch", "S_MIXED_DATE_ST,S_MIXED_DATE_ED");
        if (!json && isLoad != true) {
            mini.alert("请输入筛选条件");
            return;
        }
        loadU8c(json);
    }
    function loadU8c(json) {
        gridu8.url = "/AjaxService?method=GetCommonList&code=GetU8CFailedList";
        gridu8.load(json);
    }
    loadU8c();

    function againSync() {
        var rows = gridu8.getSelecteds();
        if (!rows || rows.length == 0) {
            mini.alert("请选择需要同步的数据");
            return;
        }

        var url = "<%=Constant.isSystemDebug() ? "http://tester.wedrive.com.cn:51518/ews-ws/AjaxService?method=CallWsWs" :
                "http://ews.wedrive.com.cn:32123/ews-ws/AjaxService?method=CallWsWs"%>";
        var data = {
            CODE: "yyDecreaseStock",
            PARAMS: { ro: rows }
        }
        gridu8.loading("正在同步...")
        var encodeData = mini.encode(data);
        ajaxRequest(url, encodeData, function (rs) {
            gridu8.unmask();
            if (rs && rs.code == '0') {
                searchU8cList(true);
            } else {
                mini.alert(rs.message);
            }

        })
    }


</script>

</body>
</html>

