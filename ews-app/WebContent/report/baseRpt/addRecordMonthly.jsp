<html>
<head>
    <title>按月添加成本记录</title>
    <style>

    </style>
</head>
<body>
<div id="win" class="mini-window" iconCls="icon-edit" title="添加单钣面月成本"
     style="width: 80%;display: none; height: 70%" showModal="true" allowResize="true" allowDrag="true" onbuttonclick="resetVar">
    <div class="clearfix">
        <div class="fl_lf" style="width: 66%;">
            <form id="roEditFm" style="margin-bottom:0">
                <table width="100%" class="detail-table" cellspacing="0">
                    <tr height="40px" style="display: none">
                        <td width="100px" align="right" bgcolor="f7f7f7">记录时间：</td>
                        <td>
                            <input class="mini-combobox" name="EWS_RO_CODE" data="monthsArray" required="true" style="width: 100%"/>
                        </td>
                        <td width="100px" align="right" bgcolor="f7f7f7">SID：</td>
                        <td>
                            <input class="mini-textbox" name="SID" required="true" style="width: 100%;"/>
                        </td>
                    </tr>
                    <tr height="40px">
                        <td width="100px" align="right" bgcolor="f7f7f7">工单号：</td>
                        <td>
                            <input class="mini-textbox asLabel"  name="RO_NUMBER" readonly="true" style="width: 100%"/>
                        </td>
                        <td width="100px" align="right" bgcolor="f7f7f7">PPG颜色编号：</td>
                        <td>
                            <input class="mini-textbox asLabel" name="PPG_BRAND_CODE" readonly="true" style="width: 100%;"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div align="center">
        <a class="mini-button" onclick="hideWindow('win')">取 消</a>
        <%--<a class="mini-button" style="margin-left: 20px;" onclick="saveRo()">保 存</a>--%>
        <a class="mini-button" style="margin-left: 20px;" onclick="saveRo(true)">保存并打印</a>
    </div>
</div>

<script type="text/javascript">
    var monthsArray = _getPrevious12MonthsOfTheCurrent();
    mini.parse();

</script>

</body>
</html>

