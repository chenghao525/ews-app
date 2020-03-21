<%@ page contentType="text/html; charset=UTF-8" import="com.corp.oa.util.G"%>
<html>
<HEAD>
    <title>首页</title>
    <script type="text/javascript" src="/js/js.js?r=1222"></script>
    <link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
    <script src="/scripts/boot.js" type="text/javascript"></script>
    <LINK href="/images/css.css" type="text/css" rel="STYLESHEET">
    <script src="/report/js/highcharts.js" type="text/javascript"></script>
    <script type="text/javascript">
        var monthsArray = _getPrevious12MonthsOfTheCurrent();
        ajaxRequest("/AjaxService?method=CheckUserPass",null,checkPass);
        function checkPass(data){
            if(data=="0"){
                mini.open({
                    url: "/system/org/password.jsp?ref=simple",
                    title: "请更新密码",
                    iconCls: "icon-lock",
                    showMaxButton: false,
                    showCloseButton:false,
                    width: 600,
                    height: 400,
                    allowResize:false,
                    ondestroy: function(action) {
                        if (action == "ok") {
                            //checkTask();
                        }
                    }
                });
            }else{
                //checkTask();
            }
        }
    </script>
    <style>
        .warn{background:#FFFF00;}
        * {
            padding: 0;
            margin: 0;
        }
        ul, li{
            list-style-type: none;
        }
        .bg_f6f6f6{
            background: #f6f6f6;
        }
        .ul_1{
            padding-right: 0.5%;
        }
        .ul_2{
            padding-left: 0.5%;
        }
        .ul_1 li{
            width: 200px;
            width: 46.7%;
            height: 140px;
            margin: 1.12%;
            float: left;
            border: 1px solid #ddd;
            text-align: center;
            line-height: 35px;
            border-top: 3px solid #ec7944;
            background: #fff;
            font-weight: 700;
            font-size: 14px;
        }
        .ul_1 li span{
            color: #ec7944;
        }
        .ul_2 li{
            width: 200px;
            width: 18.5%;
            height: 140px;
            margin: 0.45%;
            float: left;
            border: 1px solid #ddd;
            text-align: center;
            line-height: 35px;
            border-top: 3px solid #007AFF;
            background: #fff;
            font-weight: 700;
            font-size: 14px;
        }
        .ul_2 li span{
            color: #007AFF;
        }
        .ul li p{
            margin-top: 25px;
        }
        .ul li span{
            display: block;
            margin-top: 10px;
            font-weight: 700;
            font-size: 18px;
        }
        .ec_cont{
            width: 45%;
            padding: 20px;
        }
        .ec_table{
            width: 100%;
            height: 40px;
            /*border: 1px solid #999;*/
            border-collapse: collapse;
            /*border-collapse: separate;*/
        }
        .ec_table th{
            background: #f6f6f6;
        }
        .ec_table th, .ec_table td{
            padding: 10px;
            border: 1px solid #999;
            /*width: 50%;*/
            text-align: center;
        }
        .ec_table tfoot{
            font-weight: 700;
        }
        .rpt-box {
            display: -webkit-flex;
            display: -moz-flex;
            display: flex;
            display: -ms-flex;
            align-items: center;
            -webkit-align-items: center;
            -moz-align-items: center;
            -ms-align-items: center;
            flex-direction: row;
            width: 100%;
            height: 100%;
        }
        .highcharts-credits {
            display: none;
        }

    </style>
</HEAD>
<body width="100%" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
    <div class="mini-tabs" activeIndex="0" style="width:100%;height:100%;" onactivechanged="onActivechanged">
        <%if (G.isOwnRight("13801000", request)) {%>
        <div title="工作统计" iconCls="icon-report">
            <% if (G.isOwnRight("13801001", request)) {%>
            <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
                <a class="mini-button" iconCls="icon-new" plain="true" onclick="recordCost()">新增</a>
            </div>
            <%}%>
            <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
                <form id="searchRpt" style="margin-bottom:0">
                    <table>
                        <tr>
                            <td>
                                <%if(EWSConstant.MANAGER.equals((String)request.getSession().getAttribute("EMP_TYPE"))) {%>
                                门店：<input id="S_PROV_SID" class="mini-combobox" showNullItem="true" nullItemText="请选择" url='/AjaxService?method=GetProviders' style="width:150px;" onenter="search(2)"/>
                                <span class="separator"></span>
                                <%}%>
                                调色时间：<input id='S_MIXED_DATE' class="mini-combobox" data="monthsArray"
                                            value="<%=G.getCurrDt("yyyy-MM")%>" onenter="search(2)"/>
                                <span class="separator"></span>
                                <a class="mini-button" onclick="search(2)">查询</a>
                            </td>
                        </tr>

                    </table>
                </form>
            </div>
            <div class="mini-fit">
                <div class="bg_f6f6f6" style="padding-left: 20px;">
                    <div class="ul bg_f6f6f6" style="overflow: hidden; width: 100%">
                        <div class="" style="float: left;width: 28.6%;padding: 20px 0;border-right: 1px solid #ccc;" onclick="goRoList()">
                            <h3 style="margin-left: 1.12%;line-height: 30px;" id="showDate"></h3>
                            <ul class="ul_1" style="overflow: hidden;">
                                <li>
                                    <p>施工钣面（面）</p>
                                    <span id="shfId"></span>
                                </li>
                                <li>
                                    <p>施工台次（台）</p>
                                    <span id="totalId"></span>
                                </li>
                            </ul>
                        </div>
                        <div class="" style="float: left;width: 71%;padding: 20px 0;">
                            <h3 style="margin-left: 0.95%;line-height: 30px;" id="shfAvgt"></h3>
                            <ul class="ul_2">
                                <li>
                                    <p>色母</p>
                                    <span id="color"></span>
                                </li>
                                <li>
                                    <p>清漆</p>
                                    <span id="clear"></span>
                                </li>
                                <li>
                                    <p>辅料</p>
                                    <span id="sundry"></span>
                                </li>
                                <li>
                                    <p>耗材</p>
                                    <span id="other"></span>
                                </li>
                                <li style="color: red;">
                                    <p>总额</p>
                                    <span style="color: red;" id="total">55</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="rpt-box" style="font-size: 20px;">
                    <div id="chartContainer" style="width: 48%; padding: 20px; height: 40%;"></div>
                    <div class="ec_cont" style="font-size: 20px;">
                        <table  class="mini-tabs-table ec_table" cellspacing="0" cellpadding="0">
                            <tbody id="dtlTable">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <%if(G.isOwnRight("13801001", request)){%>
            <div id="win" class="mini-window" iconCls="icon-edit" title="添加单钣面月成本"
                 style="width: 60%;display: none; height: 40%" showModal="true" allowResize="true" allowDrag="true" onbuttonclick="resetVar">
                <form id="addRecordForm" style="margin-bottom:0">
                    <table width="98%" class="detail-table" cellspacing="0">
                        <tr height="40px">
                            <td width="100px" align="right" bgcolor="f7f7f7">记录时间：</td>
                            <td>
                                <input class="mini-combobox" name="RPT_MONTH" data="monthsArray" required="true" style="width: 100%"/>
                            </td>
                            <td width="100px" align="right" bgcolor="f7f7f7">门店：</td>
                            <td>
                                <input name="PROV_SID" class="mini-combobox"  url='/AjaxService?method=GetProviders' required="true" style="width:100%;"/>
                            </td>
                        </tr>
                        <tr height="40px">
                            <td width="100px" align="right" bgcolor="f7f7f7">色母：</td>
                            <td>
                                <input class="mini-spinner" name="COLOR_MASTER_COST" minValue="0" maxValue="99999" format="n2" style="width: 100%"/>
                            </td>
                            <td width="100px" align="right" bgcolor="f7f7f7">清漆：</td>
                            <td>
                                <input class="mini-spinner" name="CLEAR_COAT_COST" minValue="0" maxValue="99999" format="n2"  style="width: 100%;"/>
                            </td>
                        </tr>
                        <tr height="40px">
                            <td width="100px" align="right" bgcolor="f7f7f7">辅料：</td>
                            <td>
                                <input class="mini-spinner" name="SUNDRY_COST" minValue="0" maxValue="99999" format="n2" style="width: 100%"/>
                            </td>
                            <td width="100px" align="right" bgcolor="f7f7f7">耗材：</td>
                            <td>
                                <input class="mini-spinner" name="OTHER_COST" minValue="0" maxValue="99999" format="n2"  style="width: 100%;"/>
                            </td>
                        </tr>
                        <tr height="40px">
                            <td width="100px" align="right" bgcolor="f7f7f7">总额：</td>
                            <td>
                                <input class="mini-spinner" name="TOTAL_COST" minValue="0" maxValue="99999" format="n2" style="width: 100%"/>
                            </td>
                            <td width="100px" align="right" bgcolor="f7f7f7"></td>
                            <td>
                                <%--<input class="mini-spinner" name="TOTAL_COST" minValue="0" maxValue="99999" format="n2"  style="width: 100%;"/>--%>
                            </td>
                        </tr>
                    </table>
                </form>
                <div align="center">
                    <a class="mini-button" onclick="hideWindow('win')">取 消</a>
                    <a class="mini-button" style="margin-left: 20px;" onclick="saveRecord()">保 存</a>
                </div>
            </div>
            <%}%>
        </div>
        <%}%>
        <%if(G.isOwnRight("13808000", request)) {%>
        <div title="查询sql" iconCls="icon-channel" style="display:none;">
            <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
                <textarea id="sqlTxt" class="mini-textarea" emptyText="请输入sql" style="width: 45%" onenter="excuteSql"></textarea>
                <a class="mini-button" iconCls="icon-check" onclick="excuteSql" plain="true">查询</a>
                <span class="separator"></span>
                <a class="mini-button" iconCls="icon-eps-export" plain="true"><a download="dataexcel" id="excelOut" href="#">导出Excel</a></a>
                <span class="separator"></span>
                <a class="mini-button" iconCls="icon-data" onclick="showWindow('win1')">源数据</a>
            </div>
            <div class="mini-fit">
                <table cellspacing="0" cellpadding="0" border="1" id="tableToExcel"
                       class="detail-table" style="width: 98%; height: 100%;"></table>
            </div>
        </div>
        <%}%>
        <%if(G.isOwnRight("13802000", request)){%>
        <div title="U8C同步失败" iconCls="icon-channel">
            <%@include file="/ews/ro/u8cSyncFailed.jsp"%>
        </div>
        <%}%>

    </div>
    <div id="win1"  class="mini-window" title="数据" style="width:600px;height:400px; display: none"
         showMaxButton="false" showToolbar="false"  allowResize="false" allowDrag="true">
        <input class="mini-textarea"  id="sqlData" style="width: 100%;height:100%" />
    </div>
    <script type="text/javascript">
        mini.parse();
        function onActivechanged(e){
            var tab = e.tab;
            var name = tab.name;

            var myGrid = eval(name);
            if(!myGrid) return;

            // if(myGrid.getUrl() == ""){
            //     if (name == "grid") loadU8c();
            // }
        }


        function search(idx) {

            var prov = mini.get("S_PROV_SID");
            var rptForm = mini.get("S_MIXED_DATE");
            var dt = rptForm.getValue();

            var values = {
                'S_MIXED_DATE': dt
            };
            if (prov) {
               values.S_PROV_SID = prov.getValue();
            }
            var data = mini.encode(values);

            ajaxRequest("/AjaxService?method=BungleTotalRpt", data, function (rs) {
                if (rs && rs.code == '0') {
                    rs = mini.decode(rs);
                    showTotal(rs.details, dt);
                    showCost(rs.details.cost, dt);

                    drawCharts(rs.details.dtl, dt);
                    drawHtml(rs.details.dtl);
                }
            });
        }
        search();
        function drawHtml(list) {

            var html = '<tr height="30px" bgcolor="#5f9ea0"><td align="center">技师</td>' +
                '<td align="center">台次</td><td align="center">施工面</td><td align="center">均值(面/天)</td></tr>';

            for (var i = 0; i < list.length; i ++) {
                var item = list[i];
                html += '<tr height="30px"><td align="center" bgcolor="#f5f5dc">' + item.MECHANIC_NAME +'</td><td align="right">' +
                    item.RO_COUNT + '</td><td align="right">'+ item.SHF_COUNT +'</td><td align="center">' + item.AVG_VAL + '</td></tr>';
            }

            $("#dtlTable").html(html);
        }
        function showTotal(dtl, dt) {

            $("#showDate").html(dt.replace("-", "年") + "月");
            $("#shfId").html(dtl.SHF_TOTAL);
            $("#totalId").html(dtl.TOTAL);
        }

        function showCost(cost, dt) {
            $("#shfAvgt").html(dt.replace("-", "年") + "月单钣面成本(元)");
            if (cost) {
                $("#color").html(cost.COLOR_MASTER_COST);
                $("#clear").html(cost.CLEAR_COAT_COST);
                $("#sundry").html(cost.SUNDRY_COST);
                $("#total").html(cost.TOTAL_COST);
                $("#other").html(cost.OTHER_COST);
            } else {
                $("#color").html("");
                $("#clear").html("");
                $("#sundry").html("");
                $("#total").html("");
                $("#other").html("");
            }

        }
        function drawCharts(list, dt) {

            var categories = [], roArray = [], shfArray = [], avgArray = [];
            var roCount = 0, shfCount = 0;
            for (var i = 0; i < list.length; i++) {
                var item = list[i];
                var roNum = parseFloat(item.RO_COUNT);
                var shfNum = parseFloat(item.SHF_COUNT);
                var avgNum = parseFloat(item.AVG_VAL);

                categories.push(item.MECHANIC_NAME);
                roArray.push(roNum);
                shfArray.push(shfNum);
                avgArray.push(avgNum);

                roCount += roNum;
                shfCount += shfNum;
            }
            var roAvg = (roCount / categories.length).toFixed(2);
            var shfAvg = (shfCount / categories.length).toFixed(2);

            Highcharts.chart('chartContainer', {
                title: {
                    text: "技师工作统计",
                },
                subtitle: {
                    text: dt.replace("-", "年") + "月"
                },
                xAxis: {
                    title: {
                        text: "技师"
                    },
                    categories: categories
                },
                yAxis: {
                    title: {
                        text: "数量"
                    }
                },
                series: [{
                    type: 'column',
                    name: '台次',
                    data: roArray
                }, {
                    type: 'column',
                    name: '钣面数',
                    data: shfArray
                }, {
                    type: 'spline',
                    name: '均值(面/天)',
                    data: avgArray,
                    marker: {
                        lineWidth: 2,
                        fillColor: 'white'
                    }
                }
                ]
            });

        }
        // 新增一条记录
        function recordCost() {
            var win = mini.get("win");
            win.show();
        }

        function goRoList() {
            var rptForm = mini.get("S_MIXED_DATE");
            var dt = rptForm.getValue();
            mini.open({
                title: dt + "工单列表",
                url: '/ews/ro/roDetailsList.jsp?mixedDate=' + dt,
                width: '100%',
                height: '100%',
                iconCls: 'icon-node'
            });
        }
        function resetVar() {
            var crdForm = new mini.Form("addRecordForm");
            crdForm.setData("");
        }
        function hideWindow(win) {
            resetVar();
            var wind = mini.get(win);
            wind.hide();
        }
        function saveRecord() {
            var crdForm = new mini.Form("addRecordForm");
            crdForm.validate();
            if (!crdForm.isValid()) {
                return;
            }
            var json = crdForm.getData();
            var mData = mini.encode(json);
            ajaxRequest('/AjaxService?method=AddRptRecord', mData, function (rs) {
                if (rs && rs.code == '0') {
                    resetVar();
                }
                mini.alert(rs.message);
            });
        }

        function excuteSql() {
            var sql = mini.get("sqlTxt").getValue();
            if (!sql || sql.length == 0) {
                mini.alert('请输入sql');
                return;
            }
            var prt = mini.loading("查询中....", "查询");
            ajaxRequest('/AjaxService?method=ExecuteSql', sql, function (res) {
                if (res.code == '0') {
                    var dp = mini.get("sqlData");
                    dp.setValue(JSON.stringify(res.details).replace(/\r\s\t/ig, ""));
                    drawTable(res.details)
                } else {
                    mini.alert(res.message);
                }
                mini.hideMessageBox(prt);
            });
        }
        function drawTable(data) {

            var list = [];
            // 认为是对象
            if (Object.prototype.toString.call(data) === "[object Object]") {
                list.push(data);
            } else {
                list = data;
            }
            $("#tableToExcel").html("");

            var fields = [];
            for (var key in list[0]) {
                fields.push(key);
            }

            var html = "<tbody>";
            for (var i = 0; i < list.length; i++) {
                var it = list[i];
                html += '<tr>'
                for (var j = 0; j < fields.length; j++) {
                    var key = fields[j];
                    html += '<td>' + it[key] + '</td>';
                }
                html += '</tr>'
            }
            html += "</tbody>"
            $("#tableToExcel").html(html);

            _tableToExcel("tableToExcel", "excelOut", "sheet");
        }
        function hideWindow(w) {
            var win = mini.get(w);
            win.hide();
        }
        function showWindow(w) {
            var win = mini.get(w);
            win.show();
        }
    </script>
</body>
</html>
