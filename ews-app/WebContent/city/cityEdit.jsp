<%@ page contentType="text/html; charset=UTF-8" import="org.apache.struts.util.RequestUtils,com.corp.basic.Constant,com.corp.oa.util.G" %>
<%
	String sid=(String)request.getParameter("sid");
	if(!G.isOwnRight("10235000",request)){out.println("没有权限或会话已失效");return;}
%>

<html>

<HEAD><title></title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type=text/css rel=stylesheet>
</HEAD>

<script type="text/javascript" src="/js/js.js?r=1221"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
<body align="center" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<form id="form1" style="margin-bottom:0;float:left;">
<TABLE width="98%" class="detail-table" cellpadding="5" cellspacing="0" align=center>
	<tr align="center" bgcolor="#e0e3e8" height=25><td colspan="4"><b><font id='cityname'></font>&nbsp;城市信息</b></td></tr>

	<tr height=25>
		<td align="right" width="15%" bgcolor="f7f7f7">是否开通：</td>
		<td width="35%">
			<input id="BU_OPEN" name="BU_OPEN" class="mini-combobox" style="width:100px;" textField="text" valueField="id" url="/data/yesno.txt" required="true"
<%
	if(!G.isOwnRight("10235002",request)){
		out.print(" readonly='true' ");
	}
%>
			/>
		</td>
	</tr>
	<tr height=25>
		<td align="right" bgcolor="f7f7f7">常用城市：</td>
		<td>
			<input id="BU_COMM" name="BU_COMM" class="mini-combobox" style="width:100px;" textField="text" valueField="id" url="/data/yesno.txt" required="true"/>
		</td>
	</tr>
	<tr height=25>
		<td align="right" bgcolor="f7f7f7"><font size="+1" color="red">未</font>开通服务：</td>
		<td>
			<input id="NO_SERVICE" name="NO_SERVICE" class="mini-combobox" style="width:400px;" textField="text" valueField="id" url="/data/servicelist.txt" multiSelect="true" />
		</td>
	</tr>
	<tr height=25>
		<td align="right"  bgcolor="f7f7f7">城市拼音：</td>
		<td>
			首字母：<input name="HEAD_PY" class="mini-textbox" style="width:50px" required="true" vtype="rangeLength:1,1"/>&nbsp;&nbsp;首字母简拼：<input name="HEADALL_PY" required="true" class="mini-textbox" style="width:100px"  vtype="rangeLength:2,20"/>&nbsp;&nbsp;全拼：<input name="ALL_PY" class="mini-textbox" required="true" style="width:150px" vtype="rangeLength:2,50"/>
		</td>
	</tr>
	<tr height=25>
		<td align="right"  bgcolor="f7f7f7">城市车牌：</td>
		<td>
			<input name="CARID_HEAD" class="mini-textbox" style="width:100px" required="true" vtype="rangeLength:1,2"/>
		</td>
	</tr>
	<tr align="center" bgcolor="#e0e3e8" height=15><td colspan="4"></td></tr>
	<tr height=25>
		<td align="right" bgcolor="f7f7f7">快速定损限额：</td>
		<td>
			<input class="mini-spinner" name="ASSESS_CEIL" value="" minValue="0" maxValue="10000" decimalPlaces="0" style="width:100px" required="true"/>元
		</td>
	</tr>
<%if(G.isOwnRight("10203001",request)){%>
	<tr height=40>
		<td align="center" colspan="2" >
			<INPUT TYPE="button" class="button-ui" value="保 存" onclick="saveCity();">
			<script type="text/javascript">
			<!--
			function saveCity(){
				var form = new mini.Form("#form1");
				form.validate();
				if (!form.isValid()){var errors = form.getErrors();errors[0].focus();return false;}
				var data = form.getData();
				data.SID="<%=sid%>";
				
				var json = mini.encode(data);
				mini.confirm("您确认保存？", "请确认",
					function(action) {
						if (action == "ok"){
							var mbid=mini.loading("正在保存数据...", "保存");
							ajaxRequest("/AjaxService?method=SaveCityInfo",json,function(r){
								if(mbid!=null){mini.hideMessageBox(mbid);mbid=null;}
								mini.alert("保存成功");return;
							});
						}
					}
				);
			}
			//-->
			</script>
		</td>
	</tr>
<%}%>
</table>
</form>

<SCRIPT LANGUAGE="JavaScript">
<!--
mini.parse();

function setData(){
	ajaxRequest("/AjaxService?method=GetCityInfo",'<%=sid%>',function(r){
		var data = mini.decode(r);
		var form = new mini.Form("#form1");
		form.setData(data);
	});
}
setData();

//-->
</SCRIPT>

</body>
</html>