<%--
  Created by IntelliJ IDEA.
  User: tanjie
  Date: 2018/8/8
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.corp.oa.util.G,com.corp.ews.EWSConstant" %>
<%
    if (!G.isOwnRight("13301000", request)) { out.println("没有权限或会话已失效");return; }
    String empType = (String)session.getAttribute("EMP_TYPE");
%>
<html>
<head>
    <title>人效报表</title>
    <meta charset="utf-8">
    <script src="/js/js.js" type="text/javascript"></script>
    <link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
    <script src="/scripts/boot.js" type="text/javascript"></script>
    <script src="/report/js/highcharts.js" type="text/javascript"></script>
    <script src="/report/js/modules/exporting.js" type="text/javascript"></script>
    <LINK href="/images/css.css" type="text/css" rel="STYLESHEET">
    <style type="text/css">
        html, body{	margin:0;padding:0;border:0;width:100%;height:100%;overflow-x:hidden;}
        .highcharts-credits {
            display: none;
        }
    </style>

    <script type="text/javascript">
        function makeReportMonth(res,reqData) {
            //var series = res.series;
            var series = res.series;
            console.log("11111", res);
            var paint={};
            var merchanic={};
            var pieSeries=[];
            for(var i=0;i<series.length;i++){
                 if(series[i].REWORK_TYPE=='910') {
                     paint.name = "油漆版面";
                     paint.y = parseInt(series[i].shf_count);
                 }else{
                    merchanic.name="返工版面";
                    merchanic.y=parseInt(series[i].shf_count);
                }
            }
            pieSeries.push(paint);
            pieSeries.push(merchanic);

            var tableSeries=res.tableSeries;
            var cSeries=[];
            var pdata=[];
            //pdata=$.extend(true,{},res.x);
            pdata=[].concat(res.x);
            var mdata=[];
            mdata=[].concat(res.x);
            var pobj={};
            var mobj={};
            for(var i=0;i<pdata.length;i++) {
                pdata[i]=0;
                for (var j = 0; j < tableSeries.length; j++) {
                    if (parseInt(tableSeries[j].day.substring(8, 10)) - 1 ==i) {
                        if (tableSeries[j].REWORK_TYPE == '910') {
                            pdata[i]=parseInt(tableSeries[j].count);
                        }
                    }
                }
            }


            for(var i=0;i<mdata.length;i++) {
                mdata[i]=0;
                for (var j = 0; j < tableSeries.length; j++) {
                    if (parseInt(tableSeries[j].day.substring(8, 10)) - 1 ==i) {
                        if (tableSeries[j].REWORK_TYPE != '910') {
                            mdata[i]=parseInt(tableSeries[j].count);
                        }
                    }
                }
            }

            pobj.name = "油漆版面";
            pobj.data = pdata;
            mobj.name="返工版面";
            mobj.data = mdata;
            if(reqData.PAINT_MAKER_ID==""&&reqData.MECHANIC_ID==""){
                pobj.type = 'column';
                mobj.type = 'column';
            }else{
                pobj.type = 'spline';
                mobj.type = 'spline';
            }
            cSeries.push(pobj);
            cSeries.push(mobj);
            console.log("222222", cSeries);

            var sumarry=[];
            for(var i=0;i<pdata.length;i++){
                for(var j=0;j<mdata.length;j++){
                    if(i==j){
                        var sum=pdata[i]+mdata[j];
                        sumarry.push(sum);
                    }

                }
            }
            var account=0;
            for(var i=0;i<sumarry.length;i++){
               account+=sumarry[i];
            }
            sumarry.push(account);
            showMonthTable(res,pieSeries,cSeries,sumarry);


            exception(function () {
                if(reqData.PROV_SID==""&&reqData.PAINT_MAKER_ID==""&&reqData.MECHANIC_ID=="") {
                    Highcharts.chart("container", {
                        chart: {type: 'pie', plotBackgroundColor: null, plotBorderWidth: null, plotShow: false},
                        title: {text: "人效统计月度报表" + "（" + typeValue + "）"},
                        tooltip: {pintFormat: '{series.name}: <b>{point.percentage:.1f}%'},
                        colors: ['#FFC0CB', '#FFA500', '#7cb5ec', '	#32CD32',
                            '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
                        plotOptions: {
                            pie: {
                                allowPointSelect: true,
                                cursor: 'pointer',
                                dataLabels: {
                                    enabled: true,
                                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                                    style: {
                                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                                    }
                                }
                            }
                        },
                        series: [{
                            name: '面数',
                            colorByPoint: true,
                            data: pieSeries
                        }],
                    });
                }else if(reqData.PROV_SID!=""&&reqData.PAINT_MAKER_ID==""&&reqData.MECHANIC_ID==""){
                    Highcharts.chart("container", {
                        //普通柱状图
                        title: {text: "人效统计月度报表" + "（" + typeValue + "）"},
                        min: 0,
                        yAxis: {min: 0, title: {text: "面数"}},
                        xAxis: {title: {text: "日"}, crosshair: true, categories: res.x},
                        tooltip: {
                            crosshairs: true,
                            shared: true
                        },
                        series: cSeries
                    });
                }else{
                    Highcharts.chart("container", {
                        //普通柱状图
                        title: {type:"spline",text: "人效统计月度报表" + "（" + typeValue + "）"},
                        min: 0,
                        yAxis: {min: 0, title: {text: "面数"}},
                        xAxis: {title: {text: "日"}, crosshair: true, categories: res.x},
                        tooltip: {
                            crosshairs: true,
                            shared: true
                        },
                        series: cSeries
                    });
                }


                });

        }

        function showMonthTable(res,pieSeries,cSeries,sumarry) {
            //异步
            var t = setInterval(function () {
                clearInterval(t);
                var table = $("#table");
                var trs = '<tr>'
                for (var i = 0; i <= res.x.length; i ++) {
                    trs += '<td bgcolor="#a9a9a9" align="center" style="font-weight:700;">' + (i == 0 ? '订单类型(日)' : i) + '</td>';
                }
                trs += '<td bgcolor="#a9a9a9" align="center" style="font-weight:700;">' + '月度累计' + '</td>';
                trs += "</tr>";
                for (var i = 0; i < cSeries.length; i++) {
                    var it = cSeries[i];

                    trs += '<tr><td align="center" style="font-weight:700;">' +it.name + '</td>';

                    for (var j = 0; j < it.data.length; j++) {
                        var dt = it.data[j];
                        trs += '<td>' + dt + '</td>';

                    }

                     for (var j = 0; j < pieSeries.length; j++) {
                            var it = pieSeries[j];
                            if (i == j) {
                                if(it.y==undefined) {
                                    trs += '<td>0</td>';
                                }else {
                                    trs += '<td>' + it.y + '</td>';
                                }
                            }
                        }
                    trs += '</tr>';

                }

                trs += '<tr><td align="center" style="font-weight:700;">' + '每日总量' + '</td>';
                for (var j = 0;j< sumarry.length; j++){
                    trs += '<td>' + sumarry[j] + '</td>';
                }
                trs += '</tr>';

                $(table).html(trs);
            }, 0);
        }



        function makeReportYear(res,reqData) {
            var series = res.series;
            console.log("11111", res);
            var paint={};
            var merchanic={};
            var pieSeries=[];
            for(var i=0;i<series.length;i++){
                if(series[i].REWORK_TYPE=='910') {
                    paint.name = "油漆版面";
                    paint.y = parseInt(series[i].shf_count);
                }else{
                    merchanic.name="返工版面";
                    merchanic.y=parseInt(series[i].shf_count);
                }
            }
            pieSeries.push(paint);
            pieSeries.push(merchanic);

            var tableSeries=res.tableSeries;
            var cSeries=[];
            var pdata=[];
            //pdata=$.extend(true,{},res.x);
            pdata=[].concat(res.x);
            var mdata=[];
            mdata=[].concat(res.x);
            var pobj={};
            var mobj={};
            for(var i=0;i<pdata.length;i++) {
                pdata[i]=0;
                for (var j = 0; j < tableSeries.length; j++) {
                    if (parseInt(tableSeries[j].day.substring(5, 7)) - 1 ==i) {
                        if (tableSeries[j].REWORK_TYPE == '910') {
                            pdata[i]=parseInt(tableSeries[j].count);
                        }
                    }
                }
            }


            for(var i=0;i<mdata.length;i++) {
                mdata[i]=0;
                for (var j = 0; j < tableSeries.length; j++) {
                    if (parseInt(tableSeries[j].day.substring(5, 7)) - 1 ==i) {
                        if (tableSeries[j].REWORK_TYPE != '910') {
                            mdata[i]=parseInt(tableSeries[j].count);
                        }
                    }
                }
            }

            pobj.name = "油漆版面";
            pobj.data = pdata;
            mobj.name="返工版面";
            mobj.data = mdata;
            if(reqData.PAINT_MAKER_ID==""&&reqData.MECHANIC_ID==""){
                pobj.type = 'column';
                mobj.type = 'column';
            }else{
                pobj.type = 'spline';
                mobj.type = 'spline';
            }
            cSeries.push(pobj);
            cSeries.push(mobj);
            console.log("222222", cSeries);

            var sumarry=[];
            for(var i=0;i<pdata.length;i++){
                for(var j=0;j<mdata.length;j++){
                    if(i==j){
                        var sum=pdata[i]+mdata[j];
                        sumarry.push(sum);
                    }

                }
            }
            var account=0;
            for(var i=0;i<sumarry.length;i++){
                account+=sumarry[i];
            }
            sumarry.push(account);
            showYearTable(res,pieSeries,cSeries,sumarry);


            exception(function () {
                if(reqData.PROV_SID==""&&reqData.PAINT_MAKER_ID==""&&reqData.MECHANIC_ID=="") {
                    Highcharts.chart("container", {
                        chart: {type: 'pie', plotBackgroundColor: null, plotBorderWidth: null, plotShow: false},
                        title: {text: "人效统计年度报表" + "（" + typeValue + "）"},
                        tooltip: {pintFormat: '{series.name}: <b>{point.percentage:.1f}%'},
                        colors: ['#FFC0CB', '#FFA500', '#7cb5ec', '	#32CD32',
                            '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
                        plotOptions: {
                            pie: {
                                allowPointSelect: true,
                                cursor: 'pointer',
                                dataLabels: {
                                    enabled: true,
                                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                                    style: {
                                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                                    }
                                }
                            }
                        },
                        series: [{
                            name: '面数',
                            colorByPoint: true,
                            data: pieSeries
                        }],
                    });
                }else if(reqData.PROV_SID!=""&&reqData.PAINT_MAKER_ID==""&&reqData.MECHANIC_ID==""){
                    Highcharts.chart("container", {
                        //普通柱状图
                        title: {text: "人效统计年度报表" + "（" + typeValue + "）"},
                        min: 0,
                        yAxis: {min: 0, title: {text: "面数"}},
                        xAxis: {title: {text: "月"}, crosshair: true, categories: res.x},
                        tooltip: {
                            crosshairs: true,
                            shared: true
                        },
                        series: cSeries
                    });
                }else{
                    Highcharts.chart("container", {
                        //普通柱状图
                        title: {type:"spline",text: "人效统计年度报表" + "（" + typeValue + "）"},
                        min: 0,
                        yAxis: {min: 0, title: {text: "面数"}},
                        xAxis: {title: {text: "月"}, crosshair: true, categories: res.x},
                        tooltip: {
                            crosshairs: true,
                            shared: true
                        },
                        series: cSeries
                    });
                }


            });

        }

        function showYearTable(res,pieSeries,cSeries,sumarry) {
            //异步
            var t = setInterval(function () {
                clearInterval(t);
                var table = $("#table");
                var trs = '<tr>'
                for (var i = 0; i <= res.x.length; i ++) {
                    trs += '<td bgcolor="#a9a9a9" align="center" style="font-weight:700;">' + (i == 0 ? '订单类型' : i+"月") + '</td>';
                }
                trs += '<td bgcolor="#a9a9a9" align="center" style="font-weight:700;">' + '年度累计' + '</td>';
                trs += "</tr>";
                for (var i = 0; i < cSeries.length; i++) {
                    var it = cSeries[i];

                    trs += '<tr><td align="center" style="font-weight:700;">' +it.name + '</td>';

                    for (var j = 0; j < it.data.length; j++) {
                        var dt = it.data[j];
                        trs += '<td>' + dt + '</td>';

                    }

                    for (var j = 0; j < pieSeries.length; j++) {
                        var it = pieSeries[j];
                        if (i == j) {
                            if(it.y==undefined) {
                                trs += '<td>0</td>';
                            }else {
                                trs += '<td>' + it.y + '</td>';
                            }
                        }
                    }
                    trs += '</tr>';

                }

                trs += '<tr><td align="center" style="font-weight:700;">' + '每月总量' + '</td>';
                for (var j = 0;j< sumarry.length; j++){
                    trs += '<td>' + sumarry[j] + '</td>';
                }
                trs += '</tr>';

                $(table).html(trs);
            }, 0);
        }

        function exception(callback) {
            try {
                callback && callback();
            } catch (e) { console.log(e); mini.alert("生成图表出现错误，请与管理员联系");}
        }
    </script>
</head>
<body>
<table>
    <tr>
        <td width="*" nowrap>
            &nbsp;&nbsp;<label><input type="radio" name="type" style="cursor:hand;" value="month" checked>按月统计</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <label><input type="radio" name="type" style="cursor:hand;" value="year">按年统计</label>
        </td>
        <td>
            <span class="separator"></span>
            请选择图表的时间：<input id="START_DAY" class="mini-datepicker" value="<%=G.getCurrDate()%>"/>
            <span class="separator"></span>
        </td>

        <%if(EWSConstant.MANAGER.equals(empType)) {%>
        <td>
        门店：<input id="S_PROV_SID" class="mini-combobox" showNullItem="true" nullItemText="请选择"
                  url='/AjaxService?method=GetProviders' style="width:150px;"/>
        <span class="separator"></span>
        </td>
        <%}%>
    </tr>
    <tr>
        <td>
            <span>调漆员：</span>
            <input id="S_PAINT_MAKER_ID" class="mini-combobox" showNullItem="true" nullItemText="请选择" url='/AjaxService?method=GetMechanics&data={"work":"<%=EWSConstant.PAINT_MAKER%>"}' style="width:100px;"  onvaluechanged="pChange" />
            <span class="separator"></span>
        </td>
        <td>
            <span>油漆员：</span>
            <input class="mini-combobox" id="MECHANIC" url='/AjaxService?method=GetMechanics&data={"work":"<%=EWSConstant.PAINTER%>"}' showNullItem="true" nullItemText="请选择"  onvaluechanged="mChange"
                   allowInput="true" style="width:100px;" />
            <span class="separator"></span>
            <a class="mini-button mini-button-primary" style="margin-left: 5px;" onclick="reset">重置</a>
            <a class="mini-button" style="margin-left: 5px;" onclick="loadReport()">确定</a>
        </td>

    </tr>
</table>
<style>
    td{
        padding: 0.25em 0.2em !important;
    }
</style>
<script type="text/javascript">
    mini.parse();
    var prov_sid = mini.get("S_PROV_SID");
    var clickedFlag = false;
    function reset(){
        _formReset("START_DAY");
        if (prov_sid) {
            _formReset("S_PROV_SID");
        }
         _formReset("S_PAINT_MAKER_ID");
        _formReset("MECHANIC");
    }
    function pChange(e){
        _formReset("MECHANIC");
    }
    function mChange(e){
        _formReset("S_PAINT_MAKER_ID");
    }

    function loadReport() {
        var d = mini.get("START_DAY");
        var date = d.getFormValue();
        var prov_sid = mini.get("S_PROV_SID");
        var ctg = getRadioValue("type");
        var paint_maker = mini.get("S_PAINT_MAKER_ID");
        var mechanic = mini.get("MECHANIC");
        if (clickedFlag) {
            return;
        }
        clickedFlag = true;


        var reqData = {};

        if (prov_sid) {
            reqData.PROV_SID = prov_sid.getValue();
        }else{
            reqData.PROV_SID = "";
        }
        if (paint_maker) {
            reqData.PAINT_MAKER_ID = paint_maker.getValue();
        }else{
            reqData.PAINT_MAKER_ID ="";
        }
        if (mechanic) {
            reqData.MECHANIC_ID = mechanic.getValue();
        }else{
            reqData.MECHANIC_ID ="";
        }
        mini.mask({
            el: document.body,
            cls: 'mini-mask-loading',
            html: '加载中...'
        });
        if ("year" == ctg) {
            typeValue = date.substring(0,4);
            reqData.TYPE = typeValue;
            var encode = mini.encode(reqData);
            ajaxRequest("/AjaxService?method=ReportEfficiencyYear", encode, function (res) {
                setTimeout(function () {
                    mini.unmask(document.body);
                }, 100);
                clickedFlag = false;
                makeReportYear(res,reqData);
            });
        } else {
            typeValue = date.substring(0,7);
            reqData.TYPE = typeValue;
            var encode = mini.encode(reqData);
            ajaxRequest("/AjaxService?method=ReportEfficiencyMonth", encode, function (res) {
                setTimeout(function () {
                    mini.unmask(document.body);
                }, 100);
                clickedFlag = false;
                makeReportMonth(res,reqData);
            });
        }
    }
</script>

<div id="container" style="width:80%; height:400px; margin: 0 auto"></div>
<table class="detail-table" style="width: 95%; margin: 0 auto;" id="table">
</table>
</body>
</html>
