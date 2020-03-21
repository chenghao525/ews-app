<%@ page contentType="text/html; charset=UTF-8" import="com.corp.oa.util.G" %>

<head>
<title></title>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type="text/css" rel="STYLESHEET"> 
<script language="JavaScript" src="/js/js.js?r=1221"></script>
<script type="text/javascript" src="/js/sort.js"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
</head>
<%
	if(!G.isOwnRight("19908000",request)){out.println("没有权限或会话已失效");return;}
%>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div class="mini-toolbar" style="border-bottom:0;padding:0px;">
	<table style="width:100%;"><tr><td style="width:100%;text-align:left;">
		<a class="mini-button" iconCls="icon-reload" onclick="reset();gridIniLoad()" plain="true">刷新</a>
	</td></tr></table>
</div>
<div class="mini-toolbar" style="border-bottom:0;padding:0px;">
	<form id="formsearch" style="margin-bottom:0">
	<table style="width:100%;"><tr><td style="width:100%;text-align:left;">
		手机号：<input id="S_REC_PHONE" emptyText="13...形式,快" class="mini-textbox" maxLength="50" style="width:80px;" onenter="search" onvaluechanged="resetPhone"/> 
		<span class="separator"></span>
		手机号：<input id="S_REC_PHONE_S" emptyText="xxx形式,慢" class="mini-textbox" maxLength="50" style="width:80px;" onenter="search" onvaluechanged="resetPhone"/> 
		<span class="separator"></span>
		发送内容：<input id="S_SMS_CONTENT" class="mini-textbox" maxLength="50" style="width:80px;" onenter="search"/>
		<span class="separator"></span>
		返回值：<input id="S_RETURN_STATE" class="mini-textbox" maxLength="50" style="width:80px;" onenter="search"/>
		<span class="separator"></span>
		发送时间：<input id="S_CRT_TIME_START" class="mini-datepicker" style="width:100px;" value=""/>~<input id="S_CRT_TIME_END" class="mini-datepicker" style="width:100px;" value=""/> 
		<span class="separator"></span>
		<a class="mini-button" onclick="reset()">重置</a>&nbsp;&nbsp;<a class="mini-button" onclick="search()">查询</a><font color="red">&nbsp;仅30天内短信</font>
	</td></tr></table>
	</form>
</div>
<div class="mini-fit">
	<div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" idField="SID" allowResize="false" sizeList="[50,100]"  pageSize="100" allowCellEdit="false" allowCellSelect="false" multiSelect="false" allowCellValid="false" sortMode="server" url="">
		<div property="columns">
			<div type="indexcolumn" width="20"></div>
			<div field="REC_PHONE" width="60">接收号码</div>
			<div field="SMS_CONTENT" width="600">发送内容</div>
			<div field="RETURN_STATE" width="60">返回值</div>
			<div field="CRT_TIME" width="100" dateFormat="yyyy-MM-dd HH:mm:ss">发送时间</div>
		</div>
	</div>
</div>

<SCRIPT LANGUAGE="JavaScript">
<!--
mini.parse();
var grid = mini.get("datagrid1");

function gridIniLoad(json){
	grid.url="/AjaxService?method=GetCommonList&code=GetSmsSendList&his=no";
	grid.load(json);
}
gridIniLoad();

function reset(){var form = new mini.Form("#formsearch");form.reset();}
function search() {gridIniLoad(getSearchJson());}
function getSearchJson(){
	var form = new mini.Form("#formsearch");
	var fields = form.getFields();
	var ui,v;
	var json={};
	var bs=false;
	for(var i=0;i<fields.length;i++){
		 ui = fields[i];
		 v=ui.value;
		 if(nvl(v)!=""){
			 bs=true;
			 if(ui.id=="S_CRT_TIME_START"||ui.id=="S_CRT_TIME_END"){json[ui.id]=ui.getFormValue();}
			 else{json[ui.id]=v;}
		}
	}
	if(bs) return json;
	else return null;
}

//-->
</SCRIPT>
</body>
</html>
