
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //    if (!G.isOwnRight("", request)) { out.print("会话已失效或无权限"); return; }
%>
<html>
<head>
    <title>新增技工</title>
    <META http-equiv=Content-Type content="text/html; charset=UTF-8">
    <LINK href="/images/css.css" type=text/css rel=stylesheet>
    <script type="text/javascript" src="/js/js.js?r=1221"></script>
    <link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
    <script src="/scripts/boot.js" type="text/javascript"></script>
    <style type="text/css">
        html, body{	margin:0;padding:0;border:0;width:100%;height:100%;overflow-x:hidden;}
    </style>
</head>
<body>
<div id="win" class="mini-window" iconCls="icon-edit" title="新增/编辑技师" style="width: 700px;display: none; height: 300px"
     showModal="true" allowResize="true" allowDrag="true">
    <form id="mechanicFm" style="margin-bottom:0">
        <table width="96%" class="detail-table" cellspacing="0">
            <tr style="display: none">
                <td width="100px" align="right" bgcolor="f7f7f7">
                    <input class="mini-textbox" name="SID" style="width: 100%" required="true"/>
                </td>
            </tr>
            <tr height="40px">
                <td width="100px" align="right" bgcolor="f7f7f7">姓名：</td>
                <td>
                    <input class="mini-textbox" name="MECHANIC_NAME" style="width: 100%" required="true"/>
                </td>
                <td width="100px" align="right" bgcolor="f7f7f7">性别：</td>
                <td>
                    <input class="mini-combobox" name="SEX" value="13"
                           url='/AjaxService?method=GetDicList4MU&data={"infotype":"2"}' style="width: 100%;"/>
                </td>
            </tr>
            <tr height="40px">
                <td width="100px" align="right" bgcolor="f7f7f7">电话：</td>
                <td>
                    <input class="mini-textbox" name="TEL" onvalidation="validateTel" style="width: 100%;"/>
                </td>
                <td width="100px" align="right" bgcolor="f7f7f7">工号：</td>
                <td>
                    <input class="mini-textbox" name="WORK_NUMBER"  style="width: 100%;"/>
                </td>
            </tr>
            <tr height="40px">
                <td width="100px" align="right" bgcolor="f7f7f7">所属门店：</td>
                <td>
                    <input class="mini-combobox" name="PROV_SID" url='/AjaxService?method=GetProviders' style="width:100%" required="true"/>
                </td>
                <td width="100px" align="right" bgcolor="f7f7f7">技师等级：</td>
                <td>
                    <input class="mini-combobox" name="GRADE" required="true"
                           url='/AjaxService?method=GetDicList4MU&data={"infotype":"52"}' style="width: 100%"/>
                </td>
            </tr>
            <tr height="40px">
                <td width="100px" align="right" bgcolor="f7f7f7">技师岗位：</td>
                <td>
                    <input class="mini-combobox" name="MECHANIC_WORK" value="900" required="true"
                           url='/AjaxService?method=GetDicList4MU&data={"infotype":"50"}' style="width: 100%;"/>
                </td>
                <td width="100px" align="right" bgcolor="f7f7f7">用友ID：</td>
                <td>
                    <input class="mini-textbox" name="U8_MECHAINC_CODE" required="true" style="width: 100%"/>
                </td>
            </tr>
        </table>
    </form>
    <div align="center" style="margin-bottom: 10px">
        <%--<a class="mini-button"  onclick="hide('win')">取 消</a>--%>
        <a class="mini-button"  onclick="saveMechanic()">保 存</a>
    </div>
</div>
<script type="text/javascript">
    mini.parse();
    var fm = new mini.Form("#mechanicFm");
    function saveMechanic() {
        fm.validate();
        if (!fm.isValid()) { return; }
        var json = fm.getData();
        if (json.SID && json.SID.length > 0) {
            json.type = 'edit';
        } else {
            json.type = 'add';
        }
        var encode = mini.encode(json);
        ajaxRequest("/AjaxService?method=MechanicAdjustment", encode, function (res) {
           if (res && res.message) {
               if (res.code == "0") {
                   _formReset("mechanicFm");
               }
               mini.alert(res.message);
           }
        });
    }
    function hide(w) {
        var wd = mini.get(w);
        wd.hide();
    }
    function validateTel(e) {
        if (e.isValid) {
            var pattern = /^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(16[0-9])|(18[0,5-9]))\\d{8}$/;
            if (e.value.length != 11) {
                e.errorText = "必须输入正确的电话号码";
                e.isValid = false;
            }
        }
    }

</script>

</body>
</html>
