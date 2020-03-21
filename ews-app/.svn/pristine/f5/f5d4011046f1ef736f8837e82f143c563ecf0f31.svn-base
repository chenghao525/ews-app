<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>待完善工单</title>
    <script src="/js/printRo.js?r=6" type="text/javascript"></script>
    <style>
        .shf_box {
            width: 30%;
            margin-top: 20px;
            margin-right: 20px;
        }
        .custom-box {
            margin-left: 2%;
            width: 60%;
        }
        .custom-it {
            float: left;
            /*width: 30%;*/
            padding: 5px;
        }
    </style>
</head>
<body>
<div id="win" class="mini-window" iconCls="icon-edit" title="完善工单"
     style="width: 80%;display: none; height: 70%"
     showModal="true" allowResize="true" allowDrag="true" onbuttonclick="resetVar">
    <div class="recodeShf" style="display: none"></div>
    <div class="clearfix">
    <div class="fl_lf" style="width: 66%;">
    <form id="roEditFm" style="margin-bottom:0">
        <table width="100%" class="detail-table" cellspacing="0">
            <tr height="40px" style="display: none">
                <td width="100px" align="right" bgcolor="f7f7f7">EWS工单号：</td>
                <td>
                    <input class="mini-textbox asLabel" name="EWS_RO_CODE" readonly="true" style="width: 100%"/>
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
            <tr height="40px">
                <td width="100px" align="right" bgcolor="f7f7f7">车牌号：</td>
                <td>
                    <input class="mini-textbox" id="license" name="VEHICLE_LICENSE" onvaluechanged="licenseChanged"
                           required="true" style="width: 100%;"/>
                </td>
                <td width="100px" align="right" bgcolor="f7f7f7">车品牌：</td>
                <td>
                    <input class="mini-combobox" name="CAR_BRAND" required="true"
                           allowInput="true" url='/AjaxService?method=GetCarBrandList'  style="width: 100%;"/>
                </td>
            </tr>
            <tr height="40px">
                <td width="100px" align="right" bgcolor="f7f7f7">色号：</td>
                <td>
                    <input class="mini-textbox" name="COLOR_CODE" required="true" style="width: 100%;"/>
                </td>
                <td width="100px" align="right" bgcolor="f7f7f7">车身颜色：</td>
                <td>
                    <input class="mini-textbox" name="COLOR_NAME" emptyText="珍珠红....." required="true"  style="width: 100%;"/>
                </td>
            </tr>
            <tr height="40px">
                <td width="100px" align="right" bgcolor="f7f7f7">技师：</td>
                <td>
                    <input class="mini-combobox" id="mechanic" name="MECHANIC_ID" required="true"
                           url='/AjaxService?method=GetMechanics' style="width: 100%;"/>
                </td>
                <td width="100px" align="right" bgcolor="f7f7f7">调漆技师：</td>
                <td>
                    <input class="mini-combobox" id="pMaker" name="PAINT_MAKER_ID" required="true"
                           url='/AjaxService?method=GetMechanics&data={"work": "<%=EWSConstant.PAINT_MAKER%>"}' style="width: 100%;"/>
                </td>
            </tr>
            <tr height="40px">

                <td width="100px" align="right" bgcolor="f7f7f7">工单是否废弃：</td>
                <td>
                    <input class="mini-combobox" name="DISCARD" value="no" required="true" data="discardArray" style="width: 100%;"/>
                </td>
                <td width="100px" align="right" bgcolor="f7f7f7">色母总消耗(g)：</td>
                <td>
                    <input class="mini-textbox asLabel" name="WEIGHT_TOTAL" readonly="true" style="width: 100%"/>
                </td>
            </tr>
            <tr height="40px">

                <td width="100px" align="right" bgcolor="f7f7f7">工单状态：</td>
                <td>
                    <input class="mini-combobox" name="REWORK_TYPE" value="910" onvaluechanged="valueChanged" required="true"
                           url='/AjaxService?method=GetDicList4MU&data={"infotype":"51"}' style="width: 100%;"/>
                </td>
                <td width="100px" align="right" bgcolor="f7f7f7"><span class="originalRO"></span></td>
                <td>
                    <input class="mini-combobox ogl-ro" id="oglRo" name="ORIGINAL_RO_NUMBER" required="true" style="width: 100%; display: none"/>
                </td>
            </tr>
            <tr>
                <td width="100px" align="right" bgcolor="f7f7f7">钣面：</td>
                <td colspan="3">
                    <input class="mini-textArea" id="shf" name="SHEET_METAL_SURFACE" required="true" style="width: 100%;"/>
                </td>
            </tr>
        </table>
    </form>
        <a class="mini-button" style="margin-left: 2%;" onclick="addCustom">添加钣面自定义项</a>
        <div class="custom-box">
            <form id="customForm" style="margin-bottom:0">

            </form>
        </div>
    </div>
    <div class="shf_box fl_rt" style="display: block">
        <div class="boxcar" style="padding: 5px;">
            <div class="box-top clearfix">
                <div class="fl_lf w48">
                    <img src="/images/2_lf.png" id="<%=EWSConstant.LEFT_FRONT_BUMPER%>" onclick="shfClicked('<%=EWSConstant.LEFT_FRONT_BUMPER%>')">
                </div>
                <div class="fl_lf w50">
                    <img src="/images/2_rt.png" id="<%=EWSConstant.RIGHT_FRONT_BUMPER%>"onclick="shfClicked('<%=EWSConstant.RIGHT_FRONT_BUMPER%>')">
                </div>
            </div>
            <div class="clearfix">
                <div class="left">
                    <img src="/images/6.jpg" id="<%=EWSConstant.LEFT_FRONT_FENDER%>" onclick="shfClicked('<%=EWSConstant.LEFT_FRONT_FENDER%>')">
                    <img src="/images/7.jpg" id="<%=EWSConstant.LEFT_FRONT_DOOR%>" onclick="shfClicked('<%=EWSConstant.LEFT_FRONT_DOOR%>')">
                    <img src="/images/8.jpg" id="<%=EWSConstant.LEFT_REAR_DOOR%>" onclick="shfClicked('<%=EWSConstant.LEFT_REAR_DOOR%>')">
                    <img src="/images/9.jpg" id="<%=EWSConstant.LEFT_REAR_FENDER%>" onclick="shfClicked('<%=EWSConstant.LEFT_REAR_FENDER%>')">
                </div>
                <div class="mid">
                    <img src="/images/2.jpg" id="<%=EWSConstant.HOOD%>" onclick="shfClicked('<%=EWSConstant.HOOD%>')">
                    <img src="/images/3.jpg" id="<%=EWSConstant.ROOF%>" onclick="shfClicked('<%=EWSConstant.ROOF%>')">
                    <img src="/images/4.jpg" id="<%=EWSConstant.TRUNK%>" onclick="shfClicked('<%=EWSConstant.TRUNK%>')">

                </div>
                <div class="right">
                    <img src="/images/10.jpg" id="<%=EWSConstant.RIGHT_FRONT_FENDER%>" onclick="shfClicked('<%=EWSConstant.RIGHT_FRONT_FENDER%>')">
                    <img src="/images/12.jpg" id="<%=EWSConstant.RIGHT_FRONT_DOOR%>"  onclick="shfClicked('<%=EWSConstant.RIGHT_FRONT_DOOR%>')">
                    <img src="/images/13.jpg" id="<%=EWSConstant.RIGHT_REAR_DOOR%>" onclick="shfClicked('<%=EWSConstant.RIGHT_REAR_DOOR%>')">
                    <img src="/images/14.jpg" id="<%=EWSConstant.RIGHT_REAR_FENDER%>" onclick="shfClicked('<%=EWSConstant.RIGHT_REAR_FENDER%>')">
                </div>
            </div>
            <div class="bottom clearfix">
                <div class="fl_lf w48">
                    <img src="/images/3_lf.png" id="<%=EWSConstant.LEFT_REAR_BUMPER%>" onclick="shfClicked('<%=EWSConstant.LEFT_REAR_BUMPER%>')">
                </div>
                <div class="fl_lf w50">
                    <img src="/images/3_rt.png" id="<%=EWSConstant.RIGHT_REAR_BUMPER%>" onclick="shfClicked('<%=EWSConstant.RIGHT_REAR_BUMPER%>')">
                </div>
            </div>
        </div>
    </div>
    </div>
    <div align="center">
        <a class="mini-button" onclick="hideWindow('win')">取 消</a>
        <%--<a class="mini-button" style="margin-left: 20px;" onclick="saveRo()">保 存</a>--%>
        <a class="mini-button" style="margin-left: 20px;" onclick="saveRo(true)">保存并打印</a>
    </div>
</div>

<script type="text/javascript">

    var discardArray = [{id: 'no', text: '正常'}, {id: 'yes', text: '废弃'}]
    var shfCountArray = [{id: '0.25', text: '0.25'}, {id: '0.5', text: '0.5'}, {id: '1', text: '1'}];
    mini.parse();

    var pfForm = new mini.Form("#roEditFm");
    var oglShf = [];
    var shf_count = 0;
    var shf = [];

    function saveRo(print) {

        pfForm.validate();
        if (!pfForm.isValid()) {
            return;
        }
        var json = pfForm.getData();
        if (!json) {
            return;
        }
        //获取自定义项
        dealCustomSHF();
        json.SHF = oglShf;
        json.SHEET_METAL_COUNT = shf_count;
        json.MECHANIC_NAME = getName("mechanic", json.MECHANIC_ID);
        json.PAINT_MAKER = getName("pMaker", json.PAINT_MAKER_ID);

        var encode = mini.encode(json);
        ajaxRequest("/AjaxService?method=ROPerfectAction", encode, function (res) {
            res = mini.decode(res);
            if (res.code == '0') {
                var val = $(".recodeShf").attr("data-ro");
                if (val && val.length > 0) {
                    var arr = val.split("-");
                    var idx = arr[0];
                    $(".ro-box").eq(idx).remove();
                }
                if(print){
                    LODOP=getLodop();
                    if(!LODOP) {
                        mini.confirm("需安装CLodop云打印插件后刷新页面才能使用打印功能,确定安装吗?", "确定", function (action) {
                            if (action == "ok") {
                                window.location.href='../../data/CLodop_Setup_for_Win32NT.exe';
                            }
                        });
                        return;
                    }
                    printOk(json);
                }else{

                    hideWindow('win');
                }
                mini.alert(res.message);

            }

        });
    }

    function printOk(json) {
        var rows=[];
        rows.push(json);
        designRo(rows);
        hideWindow('win');

    }

    function getName(formId, id) {
        var list = mini.get(formId).getData();
        for (var i = 0; i < list.length; i++) {
            if (list[i].id == id) {
                return list[i].text;
            }
        }
        return ""
    }

    function shfClicked(id) {
        var isSl = clickedState(id);
        var shfName = getSHFName(id);
        if (!isSl) {
            for (var i = 0 ; i < shf.length; i++) {
                if (shf[i].indexOf(shfName) >= 0) {
                    shf_count -= getSHFCount(id);
                    shf.splice(i, 1);
                    break;
                }
            }
            removeShf(id);
        } else {
            var count = getSHFCount(id);
            shf_count += count
            shf.push(shfName  + "面" + count);
            oglShf.push({"SHF_ID": id, "SHF_NAME": shfName, "SHF_NUMBER": count, "SHF_DESCRIPTION": ""})
        }
        var showShf = "";
        if (shf_count > 0) {
            showShf = shf_count + '\n(' + shf.join(";") + ')'
        }
        mini.get("shf").setValue(showShf);
    }
    function removeShf(id) {
        var t = setInterval(function () {
            clearInterval(t);
            for (var i = 0; i < oglShf.length; i++) {
                if (oglShf[i].SHF_ID == id) {
                    oglShf.splice(i, 1);
                    break;
                }
            }
        }, 0);

    }
    function getSHFCount(id) {
        if (id == "<%=EWSConstant.LEFT_REAR_BUMPER%>"
            || id == "<%=EWSConstant.RIGHT_REAR_BUMPER%>"
            || id == "<%=EWSConstant.LEFT_FRONT_BUMPER%>"
            || id == "<%=EWSConstant.RIGHT_FRONT_BUMPER%>") {
            return 0.5;
        } else {
            return 1;
        }
    }
    //添加自定义项
    var cIdx = 1;
    function addCustom() {

        var html = '<div class="custom-it">\
                    <span>自定义项'+ cIdx +'：</span>\
                    <input class="mini-textbox" name="SHF_DESCRIPTION' + cIdx + '" emptyText="描述" style="width: 250px;"/>\
                    <input class="mini-combobox" name="SHF_NUMBER' + cIdx + '" emptyText="数量" data="shfCountArray" style="width: 60px;"/>\
                    <a class="mini-button" plain="true" onclick="delCutomIt('+ cIdx +')">-</a>\
                    </div>'
        cIdx++;
        $(".custom-box").find('#customForm').append(html);
        mini.parse();
    }
    function clickedState(id) {
        var isSelected = false;
        var src = $("#" + id).attr('src');
        if (src.indexOf("rt") >= 0 || src.indexOf("lf") >= 0) {
            if (src.indexOf('3') >= 0) {
                var src = $("#" + id).attr('src', src.replace('3', '4'));
                isSelected = true;
            } else if (src.indexOf('2') >= 0) {
                var src = $("#" + id).attr('src', src.replace('2', '6'));
                isSelected = true;
            } else if (src.indexOf('6') >= 0) {
                var src = $("#" + id).attr('src', src.replace('6', '2'));
                isSelected = false;
            } else if (src.indexOf('4') >= 0) {
                var src = $("#" + id).attr('src', src.replace('4', '3'));
                isSelected = false;
            }

        } else {
            var imgName = src.split("/")[2].split(".")[0];
            if (imgName <= 14) {
                $("#" + id).attr('src', '/images/' + imgName + imgName + ".jpg");
                isSelected = true;
            } else {
                imgName = imgName.substr(0, imgName.length / 2);
                $("#" + id).attr('src', '/images/' + imgName + ".jpg");
                isSelected = false;
            }
        }

        return isSelected;
    }
    function dealCustomSHF() {
        var custom = new mini.Form("customForm");
        var customJson = custom.getData();
        if (!customJson) {
            return;
        }

        for (var key in customJson) {
            //只遍历一个类型
            if (key.indexOf("SHF_DESCRIPTION") >= 0) {
                var idx = key.substr("SHF_DESCRIPTION".length, 1);
                var id = "custom" + idx;
                var name = "自定义项" + idx;
                var des = "SHF_DESCRIPTION" + idx;
                var num = "SHF_NUMBER" + idx;
                var desVal = customJson[des];
                var numVal = customJson[num];
                if (desVal && desVal.length > 0 && numVal && numVal.length > 0) {
                    shf_count += parseFloat(numVal);
                    oglShf.push({"SHF_ID": id, "SHF_NAME": name, "SHF_NUMBER": numVal, "SHF_DESCRIPTION": desVal})
                }
            }
        }
    }
    //删除自定义项
    function delCutomIt(index) {
        $(".custom-box").find('#customForm')
            .find(".custom-it").eq(index).remove();
    }

    function getSHFName(positionId) {

        if (positionId == "<%=EWSConstant.LEFT_REAR_BUMPER%>") {
            return "左后保险杠";
        } else if (positionId == "<%=EWSConstant.RIGHT_REAR_BUMPER%>") {
            return "右后保险杠";
        } else if (positionId == "<%=EWSConstant.LEFT_FRONT_BUMPER%>") {
            return "左前保险杠";
        } else if (positionId == "<%=EWSConstant.RIGHT_FRONT_BUMPER%>") {
            return "右前保险杠";
        } else if (positionId == "<%=EWSConstant.LEFT_FRONT_FENDER%>") {
            return "左前叶子板";
        } else if (positionId == "<%=EWSConstant.RIGHT_FRONT_FENDER%>") {
            return "右前叶子板";
        } else if (positionId == "<%=EWSConstant.LEFT_REAR_FENDER%>") {
            return "左后叶子板";
        } else if (positionId == "<%=EWSConstant.RIGHT_REAR_FENDER%>") {
            return "右后叶子板";
        } else if (positionId == "<%=EWSConstant.LEFT_FRONT_DOOR%>") {
            return "左前门";
        } else if (positionId == "<%=EWSConstant.RIGHT_FRONT_DOOR%>") {
            return "右前门";
        } else if (positionId == "<%=EWSConstant.LEFT_REAR_DOOR%>") {
            return "左后门";
        } else if (positionId == "<%=EWSConstant.RIGHT_REAR_DOOR%>") {
            return "右后门";
        } else if (positionId == "<%=EWSConstant.ROOF%>") {
            return "车顶";
        } else if (positionId == "<%=EWSConstant.TRUNK%>") {
            return "后备箱盖";
        } else if (positionId == "<%=EWSConstant.HOOD%>") {
            return "引擎盖";
        } else {
            return null;
        }
    }
    function hideWindow(win) {
        var wd = mini.get(win);
        wd.hide();
        resetVar();
    }
    function showCenter(win) {var win = mini.get(win);win.show();}

    function valueChanged(e) {
        var val = e.value;
        if (val == "<%=EWSConstant.REPEAT_NORMAL%>") {
            $(".originalRO").html("");
            $(".ogl-ro").hide();
        } else {
            $(".originalRO").html("原来的工单号：");
            $(".ogl-ro").show();
        }
    }
    function resetVar() {
        $(".custom-box").find('#customForm').html("");
        resetSHF();
        oglShf = [];
        shf_count = 0;
        shf = [];
        cIdx = 1;
    }

    function resetSHF() {
        var imgBox = $("img");
        for (var i = 0; i < imgBox.length; i++) {
            var src = $(imgBox[i]).attr('src');
            if (src.indexOf("rt") >= 0 || src.indexOf("lf") >= 0) {
                if (src.indexOf('6') >= 0) {
                    var src = $(imgBox[i]).attr('src', src.replace('6', '2'));
                } else if (src.indexOf('4') >= 0) {
                    var src = $(imgBox[i]).attr('src', src.replace('4', '3'));
                }
            } else {
                var imgName = src.split("/")[2].split(".")[0];
                if (imgName > 14) {
                    imgName = imgName.substr(0, imgName.length / 2);
                    $(imgBox[i]).attr('src', '/images/' + imgName + ".jpg");
                }
            }
        }

    }
    function licenseChanged() {
        var licenseInput = mini.get("license");
        var val = licenseInput.getValue();
        if (val && val.length >= 7) {
            var oglRo = mini.get("oglRo");
            ajaxRequest("/AjaxService?method=GetOriginalRoCodes", val, function (res) {
                if (res) {
                    oglRo.setData(mini.decode(res));
                }
            });
        }
    }
</script>

</body>
</html>
