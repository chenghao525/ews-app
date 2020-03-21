<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.corp.oa.util.G" %>
<%
    if (!G.isOwnRight("13101201", request)) { out.print("会话已失效或无权限"); return; }
    String ewsCode = request.getParameter("roCode");
    String roNumber = request.getParameter("roNumber");
%>
<html>
<head>
    <title>工单详情</title>
    <META http-equiv=Content-Type content="text/html; charset=UTF-8">
    <LINK href="/images/css.css" type=text/css rel=stylesheet>
    <script type="text/javascript" src="/js/js.js?r=1221"></script>
    <link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
    <script src="/scripts/boot.js" type="text/javascript"></script>
    <script src="/js/printRo.js?r=9" type="text/javascript"></script>
    <object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
        <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
    </object>
    <style>
        .title-box {
            background-color: #E7EAEE;
            text-align: left;
            width: 98%;
            margin-left: 1em;
            margin-right: 1em;
            border-left: 1px solid #999999;
            border-right: 1px solid #999999;
        }

    </style>
</head>
<body align="center" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<form id="dtlForm">
    <table width="98%" class="detail-table" style="margin-bottom: 0em">
        <tr>
            <td width="110px" align="right" bgcolor="f7f7f7">EWS工单号：</td>
            <td>
                <input class="mini-textbox asLabel" name="EWS_RO_CODE" readonly="true" style="width: 100%"/>
            </td>
            <td width="110px" align="right" bgcolor="f7f7f7">门店工单号：</td>
            <td>
                <input class="mini-textbox asLabel" name="RO_NUMBER" readonly="true" style="width: 100%;"/>
            </td>
        </tr>
        <tr>
            <td width="110px" align="right" bgcolor="f7f7f7">门店：</td>
            <td>
                <input class="mini-combobox asLabel" name="PROV_SID" readonly="true"
                       url='/AjaxService?method=GetProviders' style="width: 100%;"/>
            </td>
            <td width="110px" align="right" bgcolor="f7f7f7">调色时间：</td>
            <td>
                <input class="mini-textbox asLabel" name="MIXED_DATE" readonly="true" style="width: 100%;"/>
            </td>
        </tr>
        <tr>
            <td width="110px" align="right" bgcolor="f7f7f7">车牌号：</td>
            <td>
                <input class="mini-textbox asLabel" name="VEHICLE_LICENSE" readonly="true" style="width: 100%"/>
            </td>
            <td width="110px" align="right" bgcolor="f7f7f7">车辆品牌：</td>
            <td>
                <input class="mini-combobox asLabel" name="CAR_BRAND" readonly="true" url='/AjaxService?method=GetCarBrandList' style="width: 100%;"/>
            </td>
        </tr>
        <tr>
            <td width="110px" align="right" bgcolor="f7f7f7">色号：</td>
            <td>
                <input class="mini-textbox asLabel" name="COLOR_CODE" readonly="true" style="width: 100%"/>
            </td>
            <td width="110px" align="right" bgcolor="f7f7f7">车身颜色：</td>
            <td>
                <input class="mini-textbox asLabel" name="COLOR_NAME" readonly="true" style="width: 100%;"/>
            </td>
        </tr>
        <tr>
            <td width="110px" align="right" bgcolor="f7f7f7">技师：</td>
            <td>
                <input class="mini-textbox asLabel" name="MECHANIC_NAME" readonly="true" style="width: 100%"/>
            </td>
            <td width="110px" align="right" bgcolor="f7f7f7">调漆技师：</td>
            <td>
                <input class="mini-textbox asLabel" name="PAINT_MAKER" readonly="true" style="width: 100%;"/>
            </td>
        </tr>
        <tr>
            <td width="110px" align="right" bgcolor="f7f7f7">色母总消耗(g)：</td>
            <td>
                <input class="mini-textbox asLabel" name="WEIGHT_TOTAL" readonly="true" style="width: 100%"/>
            </td>
            <td width="110px" align="right" bgcolor="f7f7f7">工单状态：</td>
            <td>
                <input class="mini-combobox asLabel" id="pMaker" name="REWORK_TYPE" readonly="true"
                       url='/AjaxService?method=GetDicList4MU&data={"infotype":"51"}' style="width: 100%;"/>
            </td>
        </tr>
        <tr>
            <td width="110px" align="right" bgcolor="f7f7f7">原工单号：</td>
            <td onclick="showOtherRO()">
                <input class="mini-textbox asLabel" id="oglRO" name="ORIGINAL_RO_NUMBER"
                       readonly="true" style="width: 100%;"/>
            </td>
            <td width="110px" align="right" bgcolor="f7f7f7">钣面数：</td>
            <td>
                <input class="mini-textbox asLabel" name="SHEET_METAL_COUNT" readonly="true" style="width: 100%"/>
            </td>
        </tr>
    </table>
</form>
<div class="title-box">
    <span style="margin-left: 10px;">色母详情</span>
</div>
<div id="dtlCpts" class="mini-datagrid" style="width: 98%; margin-left: 1em;" showPager="false" pageSize="50" sizeList="[20,30,50,100]">
    <div property="columns">
        <div type="indexcolumn" width="20"></div>
        <%--<div type="checkcolumn" width="20"></div>--%>
        <%--<div field="EWS_RO_CODE" width="30" headerAlign="center">EWS工单号</div>--%>
        <%--<div field="RO_NUMBER" width="30" headerAlign="center">工单号</div>--%>
        <div field="COMPONENT_CODE" width="30" headerAlign="center">色母编号</div>
        <div field="COMPONENT_DESCRIPTION" width="30" headerAlign="center">色母描述</div>
        <div field="COMPONENT_WEIGHT_TARGET" width="30" decimalPlaces="2" dataType="float" align="right" headerAlign="center">称重目标(g)</div>
        <div field="COMPONENT_WEIGHT_APPLIED" width="30" decimalPlaces="2" dataType="float" align="right" headerAlign="center">实际称重(g)</div>
        <div field="COMPONENT_DENSITY" width="30" decimalPlaces="2" dataType="float" align="right" headerAlign="center">密度</div>
    </div>
</div>
<div class="title-box">
    <span style="margin-left: 10px;">钣面详情</span>
</div>
<div id="dtlShf" class="mini-datagrid" style="width: 98%; margin-left: 1em; margin-bottom: 1em;" showPager="false" pageSize="50" sizeList="[20,30,50,100]">
    <div property="columns">
        <div type="indexcolumn" width="20"></div>
        <%--<div type="checkcolumn" width="20"></div>--%>
        <%--<div field="EWS_RO_CODE" width="30" headerAlign="center">EWS工单号</div>--%>
        <%--<div field="RO_NUMBER" width="30" headerAlign="center">工单号</div>--%>
        <div field="SHF_NAME" width="30" headerAlign="center">钣面名称</div>
        <div field="SHF_NUMBER" width="30" decimalPlaces="2" dataType="float" align="right" headerAlign="center">面数</div>
        <div field="SHF_DESCRIPTION" width="30" headerAlign="center">描述</div>
    </div>
</div>
<div class="mini-toolbar" style="border-bottom:0;padding:0px; text-align: center">
    <span class="mini-button" plain="true" iconCls="icon-eps-print" onclick="printDeliverys()">打印</span>
</div>
<script type="text/javascript">

    mini.parse();
    var dtlForm = new mini.Form("dtlForm");

    var gridCpt = mini.get("dtlCpts");
    var gridShf = mini.get("dtlShf");

    function details() {

        var obj = {
            EWS_RO_CODE: "<%=G.isNvl(ewsCode) ? "" : ewsCode%>",
            RO_NUMBER: "<%=G.isNvl(roNumber) ? "" : roNumber%>"
        }
        var edJson = mini.encode(obj);
        ajaxRequest("/AjaxService?method=GetRODetails", edJson, function (res) {
            // res = mini.decode(res);
            if (res.code == '0') {
                dtlForm.setData(res.details);
            } else {
                mini.alert(res.message);
            }
        });
        var t = setInterval(function () {
            clearInterval(t);
            loadGridDetals();
            loadGridShf();
        }, 0);

    }
    details();

    function showOtherRO() {
        var otherToNode = mini.get("oglRO");
        var roNumber = otherToNode.getValue();

        if (!roNumber || roNumber.length == 0) {
            return;
        }
        mini.open({
            title: "工单详情",
            iconCls: "icon-node",
            url: "/ews/ro/roDetails.jsp?roNumber=" + roNumber,
            width: "80%",
            height: "65%"
        });
    }

    function loadGridDetals() {
        gridCpt.url = "/AjaxService?method=GetCommonList&code=GetROComponents&" + getReqParam();
        gridCpt.load();
    }
    function loadGridShf() {
        gridShf.url = "/AjaxService?method=GetCommonList&code=GetROSHFList&" + getReqParam();
        gridShf.load();
    }

    function getReqParam() {
        <% if (!G.isNvl(ewsCode)){%>
        var code = "ewsRoCode=<%=ewsCode%>";
        <% } else {%>
        var code = "roNumber=<%=roNumber%>";
        <%}%>
        return code;
    }
    function printDeliverys() {


        LODOP=getLodop();
        if(!LODOP) {
            mini.confirm("需安装CLodop云打印插件后刷新页面才能使用打印功能,确定安装吗?", "确定", function (action) {
                if (action == "ok") {
                    window.location.href='../../data/CLodop_Setup_for_Win32NT.exe';
                }
            });
            return;
        }

        printOk();

    }

    function printOk() {
        var rows=[];
        rows.push(dtlForm.getData());
        designRo(rows);
        window.CloseOwnerWindow();
    }

    function showCenter(win) {var win = mini.get(win);win.show();}
    function hideWindow(win) {var win = mini.get(win);win.hide();}

</script>
</body>
</html>
