<%@ page contentType="text/html; charset=UTF-8" import="com.corp.ews.EWSConstant,com.corp.oa.util.G,java.net.*" %>
<% 
String sid=(String)request.getParameter("org_sid");
String type=(String)request.getParameter("type");
String empid=(String)request.getParameter("empid");
String org_psid=(String)request.getParameter("org_psid");
String emptype = G.getEmployeeType(request);
if (!G.isOwnRight("19902000,19902001,19902002", request)) { out.println("没有权限或会话已失效"); return; }
 %>

<html>
<HEAD><title></title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type=text/css rel=stylesheet>
</HEAD>

<script type="text/javascript" src="/js/js.js?r=1221"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
<LINK href="/images/css.css" type="text/css" rel="STYLESHEET">
<body align="center" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

	<div class="mini-fit" showHeader="false" style="height:100%;border-bottom:0;padding:0px;">
        <h5>账号信息</h5>
        <form id="form" style="width:100%;">
            <table width="96%" class="detail-table">
                <tr height="25">
                    <td align="right" width="18%">登录名 :</td>
                    <td width="35%">
                        <input class="mini-textbox" name="EMPID" id="EMPID" style="width: 100px" required="true"/>
                    </td>
                    <td align="right" width="18%">姓名 :</td>
                    <td width="35%">
                        <input class="mini-textbox" name="EMPNAME" id="EMPNAME" style="width: 100px" required="true"/>
                    </td>
                    
                </tr>
                <tr height="25">
                    <td align="right" width="18%">U8C账号 :</td>
                    <td width="35%">
                        <input id="U8C_CODE" class="mini-textbox" name="U8C_CODE"
                               style="width: 100px" required="true"/>
                    </td>
                    <td align="right" width="18%">门店调漆技师 :</td>
                    <td width="35%">
                        <input id="PAINT_MAKER_ID" class="mini-combobox" name="PAINT_MAKER_ID" url='/AjaxService?method=GetMechanics&data={"work":"<%=EWSConstant.PAINT_MAKER%>"}'
                               style="width: 100px" required="true"/>
                    </td>
                </tr>
                <tr height="25">
                    <td align="right" width="18%">性别 :</td>
                    <td width="35%">
                        <input id="SEX" class="mini-combobox" name="SEX" data="sexs"  value="13"
                               style="width: 100px" required="false"/>
                    </td>
                    <td align="right" width="18%">登录状态 :</td>
                    <td width="35%">
                        <input id="EMP_ACTIVE" class="mini-combobox" name="EMP_ACTIVE" data="states" value="正在使用"
                               style="width: 100px" required="false"/>
                    </td>
                </tr>
                <tr height="25">
                    <td align="right" width="18%">单位电话 :</td>
                    <td width="35%">
                        <input class="mini-textbox" name="COPHONE" id="COPHONE" style="width: 100px" required="false"/>
                    </td>
                    <td align="right" width="18%">手机 :</td>
                    <td width="35%">
                        <input class="mini-textbox" name="MP" style="width: 100px" required="false"/>
                    </td>

                </tr>
                <tr height="25">
                    <td align="right" width="18%">E_mail :</td>
                    <td width="35%">
                        <input class="mini-textbox" name="EMAIL" style="width: 100px" required="false"/>
                    </td>
                    <td align="right" width="18%">身份证号 :</td>
                    <td width="35%">
                        <input class="mini-textbox" name="ID_CARD" style="width: 100px" required="false"/>
                    </td>
                </tr>
                
                <tr height="25">
                    <td align="right" width="18%">备 注 :</td>
                    <td width="35%">
                        <input class="mini-textbox" name="MEMO" style="width: 200px" require="false"/>
                    </td>
                    <td align="right" width="18%">人员类别 :</td>
                    <td width="35%">
                        <input id="EMP_TYPE" class="mini-combobox" name="EMP_TYPE" url='/AjaxService?method=GetDicList4MU&data={"infotype":"10"}'
                               style="width: 100px" required="false"/>
                    </td>
                </tr>
                <tr height="25">
                    <td align="right" width="18%">账号所属门店 :</td>
                    <td width="35%">
                        <input id="PROV_SID" class="mini-combobox" name="PROV_SID"
                               url='/AjaxService?method=GetProviders'
                               style="width: 150px" required="true"/>
                    </td>
                    <td align="right" width="18%"></td>
                    <td width="35%">
                        <%--<input id="PAINT_MAKER_ID" class="mini-combobox" name="PAINT_MAKER_ID" url='/AjaxService?method=GetMechanics&data={"work":"<%=EWSConstant.PAINT_MAKER%>"}'--%>
                               <%--style="width: 100px" required="true"/>--%>
                    </td>
                </tr>
            </table>
                
        </form>
        <div style="width: 100%" align="center">
        	<%if(!G.isNvl(type)&&!type.equals("update")){%>
            	<span class="mini-button mini-button-primary" style="width: 100px;margin-left:80px;" onclick="reset">清空</span>
            <%}%>
            	<span class="mini-button mini-button-primary" style="width: 100px;margin-left:80px;" onclick="save(0)">保存</span>
            <%if(!G.isNvl(type)&&type.equals("update")&&G.isOwnRight("19902003", request)){%>
            	<span class="mini-button mini-button-primary" style="width: 100px;margin-left:80px;" onclick="save(1)">删除</span>
            <%}%>
            <%if(!G.isNvl(type)&&type.equals("update")&&G.isOwnRight("19902004", request)){%>
            	<span class="mini-button mini-button-primary" style="width: 100px;margin-left:80px;" onclick="save(2)">重置密码</span>
            <%}%>
            
        </div>
    </div>
    


<SCRIPT LANGUAGE="JavaScript">
<!--
var sexs = [{id: "13", text: "先生"}, {id: "14", text: "女士"}];
var states = [{id: "16", text: "正在使用"}, {id: "17", text: "暂时冻结"}];
mini.parse();

var edtform = new mini.Form("form");
var sid='<%=sid%>';
var type='<%=type%>';
var empid='<%=empid%>';
var org_psid='<%=org_psid%>';

function init(){
	if(sid && sid.length > 0){
		var data = {
		    SID: sid,
            TYPE: 'dtl'
        };
		var request = mini.encode(data);
		ajaxRequest("/AjaxService?method=EWSUserAction",request,function(r){
			var rs = r && mini.decode(r);
			if(rs && rs.code == '0'){
				edtform.setData(rs.details);
				edtform.unmask();
				mini.get("last");
			} else if (rs && rs.code != '0') {
                mini.alert(rs.message);
            } else{
				mini.alert("没有数据！");
			}
		});
	}
}
init();

function reset(){
	edtform.reset();
}

function save(i){
	
	edtform.validate();
    if (!edtform.isValid()&&i!=1) {
        return false;
    }
    var data = edtform.getData();
    data.TYPE = type;
    data.SID = sid;
    data.DUTYSID = org_psid;

    var title="保存";
    if(i==1){
    	data.STATE = 0;
    	title="删除";
    }else if(i==2){
    	data.RESET_PWD="yes";
    	title="置空该用户的密码";
    }
    var request = mini.encode(data);

	mini.confirm("您确认"+title+"？","请确认",function(action){
		if (action == "ok"){
			ajaxRequest("/AjaxService?method=EWSUserAction",request,function(r){
				var result = mini.decode(r);
                if (result && result.code == '0') {
                    if(type == 'add'){
						edtform.reset();
					}
                    mini.alert(result.message);
                }else{
					mini.alert("操作失败："+result.message);
				}
			});
		}
	});
}


//-->
</SCRIPT>

</body>
</html>