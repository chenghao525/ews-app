<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.corp.oa.util.G,com.corp.ews.EWSConstant" %>
<%
    if (!G.isOwnRight("13401000", request)) { out.print("会话已失效或无权限"); return; }
    String empType = (String)request.getSession().getAttribute("EMP_TYPE");
%>
<html>
<head>
    <title>技术管理</title>
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
        <% if (G.isOwnRight("13401001", request)) {%>
        <a class="mini-button" iconCls="icon-new" plain="true" onclick="newMechanic()">新建</a>
        <span class="separator"></span>
        <a class="mini-node" plain="true"  href="/data/mechanic.xlsx" download="mechanic.xlsx">模板下载</a>
        <a class="mini-button" iconCls="icon-eps-import" plain="true" onclick="exportMechanic()">导入</a>
        <span class="separator"></span>
        <%}%>
        <% if (G.isOwnRight("13401002", request)) {%>
        <a class="mini-button" iconCls="icon-edit" plain="true" onclick="eidtMechanic()">编辑</a>
        <span class="separator"></span>
        <%}%>
        <a class="mini-button" iconCls="icon-refresh" plain="true" onclick="search()">刷新</a>
        <span class="separator"></span>
        <a class="mini-button" iconCls="icon-delete" plain="true" onclick="delMechanic()">删除</a>
    </div>
    <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
        <form id="formsearch" style="margin-bottom:0">
            <table>
                <tr><td>
                    <%if(EWSConstant.MANAGER.equals(empType)) {%>
                    门店：<input id="S_PROV_SID" class="mini-combobox" showNullItem="true" nullItemText="请选择"
                              url='/AjaxService?method=GetProviders' style="width:150px;" onenter="search"/>
                    <span class="separator"></span>
                    <%}%>
                    技师岗位：<input id="S_MECHANIC_WORK" class="mini-combobox" showNullItem="true" nullItemText="请选择" url='/AjaxService?method=GetDicList4MU&data={"infotype":"50"}'  style="width:100px;" onenter="search"/>
                    <span class="separator"></span>
                    技师姓名：<input id="S_MECHANIC_NAME" class="mini-textbox" style="width:100px;" onenter="search"/>
                    <span class="separator"></span>
                    <a class="mini-button" onclick="reset()">重置</a>&nbsp;&nbsp;<a class="mini-button" onclick="search()">查询</a>
                </td></tr>
            </table>
        </form>
    </div>
    <div class="mini-fit">
        <div id="datagrid" style="width: 100%; height: 100%" class="mini-datagrid" sizeList="[20,30,50,100]" allowCellSelect="true" pageSize="50" onrowdblclick="eidtMechanic">
            <div property="columns">
                <div type="indexcolumn" width="20"></div>
                <div type="checkcolumn" width="20"></div>
                <div field="MECHANIC_NAME" width="40">姓名</div>
                <div type="comboboxcolumn" field="PROV_SID" width="60" readonly="true">门店
                    <input property="editor" class="mini-combobox" style="width:100%;" allowInput="true" url='/AjaxService?method=GetProviders'/>
                </div>
                <div type="comboboxcolumn" field="MECHANIC_WORK" width="60" readonly="true">技师岗位
                    <input property="editor" class="mini-combobox" style="width:100%;" allowInput="true" url='/AjaxService?method=GetDicList4MU&data={"infotype":"50"}'/>
                </div>
                <div field="TEL" width="40">电话</div>
            </div>
        </div>
    </div>
    <% if (G.isOwnRight("13401001", request)) {%>
    <%@include file="addMechanic.jsp"%>
    <%}%>

<script type="text/javascript">
    mini.parse();
    var upWin = null;
    var sForm = new mini.Form("formsearch");
    var grid = mini.get("datagrid");

    function loadGrid(json) {
        grid.url = "/AjaxService?method=GetCommonList&code=GetMechanicList";
        grid.load(json);

    }
    function search() {
        var json = _getGridSearch("formsearch");
        if (!json) {
            mini.alert("请选择筛选条件")
            return;
        }
        loadGrid(json);
    }
    function newMechanic() {
        var fm = new mini.Form("#mechanicFm");
        fm.setData("");
        show("win");
    }
    function eidtMechanic() {
        <%if(G.isOwnRight("13401002", request)) {%>
        var row = grid.getSelected();
        if (!row) {
            mini.alert("请选择编辑的技师");
            return;
        }
        show('win');
        var fm = new mini.Form("mechanicFm");
        fm.setData(row);
        <%}%>
    }
    function delMechanic() {
        var row = grid.getSelected();
        if (!row) {
            mini.alert("请选择需要删除的数据");
            return;
        }
        row.type = 'del';type = 'del';
        var encode = mini.encode(row);
        ajaxRequest("/AjaxService?method=MechanicAdjustment", encode, function (res) {
            if (res && res.message) {
                if (res.code == 0) {
                    search();
                }
                mini.alert(res.message);
            }
        });
    }
    function exportMechanic() {
        upWin = window.open("/file/imageUpload/fileUpload.jsp", "fileupload",
            'width=500px,height=280px,menubar=0,toolbar=0,directories=0,location=0,status=0,scrollbars=1');
        upWin.focus();
    }
    function setPhoto(path){
        upWin.close();
        if(path==null || path==""){mini.alert("请正确上传需要的文件");return;}
        var mbid=mini.loading("文件上传成功！<br><br>正在导入...", "数据");

        var data = mini.encode({path: path});

        ajaxRequest("/AjaxService?method=ImportMechanics", data, function(r){
            if(mbid!=null){mini.hideMessageBox(mbid);mbid=null;}
            if (r && r.code == '0') {
                _showLog(r.details);
            } else if (r) {
                mini.alert(r.message);
            }
        });
    }
    function reset() {
        _formReset("formsearch");
    }

    function hide(win) {
        var windows = mini.get(win);
        windows.hide();
    }
    function show(win) {
        var mForm = new mini.Form("mechanicFm");
        mForm.setData("");
        var windows = mini.get(win);
        windows.show();
    }

</script>
</body>
</html>
