
<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.corp.oa.util.G" %>
<%
//    if (!G.isOwnRight("", request)) { out.println("没有权限或会话已失效");return; }
%>
<html>
<head>
    <title>首页</title>
    <script type="text/javascript" src="/js/js.js?r=1222"></script>
    <link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
    <script src="/scripts/boot.js" type="text/javascript"></script>
    <LINK href="/images/css.css" type="text/css" rel="STYLESHEET">
</head>
<script type="text/javascript">
    ajaxRequest("/AjaxService?method=CheckUserPass", null, checkPass);
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
    .login-bar {
        width: 100%;
        height: 30px;
        background-color: #4f93e3;
        font-size: 15px;
        padding-bottom: 5px;
    }

</style>
<body width="100%" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
    <%--<div class="login-bar">上次登录时间</div>--%>
    <div class="mini-tabs" style="width:100%;height:100%" onactivechanged="onActivechanged">
        <%--<div iconCls="icon-new" title="现金订单入账">--%>
            <%--<div class="mini-fit">--%>
                <%--<div class="mini-toolbar" style="border-bottom:0;padding:0px;">--%>
                    <%--<form id="searchForm">--%>
                        <%--<table style="width:100%;">--%>
                            <%--<tr>--%>
                                <%--<td>--%>
                                    <%--<span class="mini-button" iconCls="icon-reload" plain="true" onclick="searchClick">刷新</span>--%>
                                    <%--<span class="separator"></span>--%>
                                    <%--<span class="mini-button" style="background-color: gainsboro" plain="true" onclick="noEnterAccount">未入账</span>--%>
                                    <%--<span class="separator"></span>--%>
                                    <%--<span class="mini-button" style="background-color: blueviolet" plain="true" onclick="enterAccount">已入账</span>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                            <%--<tr>--%>
                                <%--<td>--%>
                                    <%--<span>快递公司</span>--%>
                                    <%--<input class="mini-textbox" id="EXPRESS_COMPANY_ID" name="EXPRESS_COMPANY_ID" style="width: 100px" onenter="searchClick"/>--%>
                                    <%--<span class="separator"></span>--%>
                                    <%--<span>派送成功时间</span>--%>
                                    <%--<input class="mini-datepicker" id="EXPRESS_COMPANY_ID" name="EXPRESS_COMPANY_ID" format="yyyy-MM-dd" style="width: 100px" onenter="searchClick"/>--%>
                                    <%--<span>~</span>--%>
                                    <%--<input class="mini-datepicker" id="EXPRESS_COMPANY_ID" name="EXPRESS_COMPANY_ID" format="yyyy-MM-dd" style="width: 100px" onenter="searchClick"/>--%>
                                    <%--<span class="separator"></span>--%>
                                    <%--<span>车牌号</span>--%>
                                    <%--<input class="mini-textbox" id="EXPRESS_COMPANY_ID" name="EXPRESS_COMPANY_ID" style="width: 100px" onenter="searchClick"/>--%>
                                    <%--<span class="separator"></span>--%>
                                    <%--<span>支付号</span>--%>
                                    <%--<input class="mini-textbox" id="EXPRESS_COMPANY_ID" name="EXPRESS_COMPANY_ID" style="width: 100px" onenter="searchClick"/>--%>
                                    <%--<span class="separator"></span>--%>
                                    <%--<a class="mini-button" iconCls="icon-search" onclick="searchClick">查询</a>--%>
                                    <%--<a class="mini-button" iconCls="icon-redo" onclick="_formReset('searchForm')">重置</a>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                        <%--</table>--%>
                    <%--</form>--%>
                <%--</div>--%>
                <%--<div id="grid2" class="mini-datagrid" style="width: 100%; height: 100%" sizeList="[50,100]"  pageSize="50" multiSelect="true" allowCellSelect="true">--%>
                    <%--<div property="columns">--%>
                        <%--<div type="indexcolumn" width="20"></div>--%>
                        <%--<div field="" width="50" headerAlign="center">快递公司</div>--%>
                        <%--<div field="" width="50" headerAlign="center" allowSort="true">车牌号</div>--%>
                        <%--<div field="" width="50" headerAlign="center" allowSort="true">条码</div>--%>
                        <%--<div field="" width="50" headerAlign="center" allowSort="true">派送成功时间</div>--%>
                        <%--<div field="" width="50" headerAlign="center" allowSort="true">保费总额</div>--%>
                        <%--<div field="" width="50" headerAlign="center">支付方式</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid1 = mini.get("grid1");
        var grid2 = mini.get("grid2");
        var searchf = new mini.Form("searchForm");

        function onActivechanged(e) {
            var tab = e.tab;
            var name = tab.name;

            var myGrid=eval(name);
            if(!myGrid) return;

            if(myGrid.getUrl()==""){
                if(name=="grid") refreshGrid();
                if(name=="grid2") refreshGrid2();
            }

        }
        function searchClick() {
            var json = _getGridSearch("searchForm")
            refreshGrid2(json);

        }
        function refreshGrid() {
            grid1.url = "";
            grid1.load();
        }
        function refreshGrid2(json) {
            grid2.url = "";
            grid2.load(json);
        }
        function noEnterAccount() {
            //未入账
            var rows = grid2.getSelecteds();
            if (rows) {

            }

        }
        function enterAccount() {
            //已入账
            var rows = grid1.getSelecteds();
            if (rows) {


            }

        }
        
    </script>
</body>
</html>
