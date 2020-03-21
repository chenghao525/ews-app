<%@ page contentType="text/html; charset=UTF-8" import="com.corp.oa.util.G,com.corp.servlet.common.CommonUtils"%>
<html>
<head>
<title></title>
<LINK href="/images/css.css" type="text/css" rel="STYLESHEET">
<script language="JavaScript" src="/js/js.js?r=1221"></script>
<script type="text/javascript" src="/js/sort.js"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>

</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<%
	if(!G.isOwnRight("19903000",request)){out.println("没有权限或会话已失效");return;}
%>
<div id="dataTab" class="mini-tabs" style="width:100%;height:100%" onactivechanged="onActivechanged">
	<div title="按角色赋权" iconCls="icon-lock" name="1">
		<div class="mini-toolbar" style="border-bottom:0;padding:0px;">
			<table>
				<tr style="text-align:left;float:left;"><td>
					角色：<input id="S_ROLEID" class="mini-combobox" style="width:300px" allowInput="true" multiSelect="false" url="/AjaxService?method=GetRoleDicList" onvaluechanged="roleChange"/>
					<span class="separator"></span>
					&nbsp;<a class="mini-button" onclick="saveByRole()">保存</a>
					<script type="text/javascript">
					<!--
					function roleChange(){
						var obj=mini.get("S_ROLEID");
						var v=obj.getValue();
						if(v&&v!=""){
							ajaxRequest("/AjaxService?method=GetRightIdList&type=role&id="+v,null,function(r){
								var data=eval(r);
								var rows=grid1.getData();
								var valid=data.right;
								if (rows.length > 0){
									var sid="";
									for(var i=0;i<rows.length;i++){
										sid=rows[i].SID;
										if(valid.indexOf(","+sid+",")>=0){
											grid1.setSelected(rows[i]);
										}else{
											grid1.deselect(rows[i]);
										}
									}
								}
							});
						}
					}
					function saveByRole(){
						var obj=mini.get("S_ROLEID");
						var v=obj.getValue();
						if(v&&v!=""){
							var rows=grid1.getSelecteds();
							var sid="";
							for(var i=0;i<rows.length;i++){sid+=rows[i].SID+",";}
							json={};json.sid=sid;json.type="role";json.roleid=v;
							grid1.loading("保存中，请稍后......");
							ajaxRequest("/AjaxService?method=SetRightIdList",json2str(json),function(r){
								mini.alert("保存成功");
								grid1.unmask();
							});
						}
					}
					//-->
					</script>
				</td></tr>
			</table>
		</div>
		<div class="mini-fit">
			<div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" idField="SID" allowResize="false" sizeList="[500,1000]" pageSize="500" allowCellEdit="true"  allowCellSelect="true" multiSelect="true" allowCellValid="false" sortMode="server" url="" oncellclick="gridcellclick">
				<div property="columns">
					<div type="indexcolumn" width="20"></div>
					<div type="checkcolumn" width="20"></div>
					<div field="EMPID" width="80">登录账号</div>
					<div field="EMPNAME" width="100" allowSort="true">姓名</div>
					<div field="MP" width="100" allowSort="true">手机</div>
				</div>
			</div>
		</div>
	</div>
	<div title="按人员赋权" iconCls="icon-user" name="2" style="display:none;">
		<div class="mini-toolbar" style="border-bottom:0;padding:0px;">
			<table>
				<tr style="text-align:left;float:left;"><td>
					人员：<input id="S_EMPID" class="mini-combobox" style="width:300px" allowInput="true" multiSelect="false" url="/AjaxService?method=GetEmpAllList&data=valid&inc-self=false" onvaluechanged="empChange"/>
					<span class="separator"></span>
					&nbsp;<a class="mini-button" onclick="saveByEmp()">保存</a>
					<script type="text/javascript">
					<!--
					function empChange(){
						var obj=mini.get("S_EMPID");
						var v=obj.getValue();
						if(v&&v!=""){
							ajaxRequest("/AjaxService?method=GetRightIdList&type=employee&id="+v,null,function(r){
								var data=eval(r);
								var valid=data.right;
								var rows=grid2.getData();
								if (rows.length > 0){
									var sid="";
									for(var i=0;i<rows.length;i++){
										sid=rows[i].ID;
										if(valid.indexOf(","+sid+",")>=0){
											grid2.setSelected(rows[i]);
										}else{
											grid2.deselect(rows[i]);
										}
									}
								}
							});
						}
					}
					function saveByEmp(){
						var obj=mini.get("S_EMPID");
						var v=obj.getValue();
						if(v&&v!=""){
							var rows=grid2.getSelecteds();
							var sid="";
							for(var i=0;i<rows.length;i++){sid+=rows[i].ID+",";}
							json={};json.sid=sid;json.type="employee";json.empid=v;
							grid2.loading("保存中，请稍后......");
							ajaxRequest("/AjaxService?method=SetRightIdList",json2str(json),function(r){
								mini.alert("保存成功");
								grid2.unmask();
							});
						}
					}
					//-->
					</script>
				</td></tr>
			</table>
		</div>
		<div class="mini-fit">
			<div id="datagrid2" class="mini-datagrid" style="width:100%;height:100%;" idField="ID" allowResize="false" sizeList="[500,1000]"  pageSize="500" allowCellEdit="false" allowCellSelect="true" multiSelect="true" allowCellValid="false" sortMode="server" url="" oncellclick="gridcellclick">
				<div property="columns">
					<div type="indexcolumn" width="20"></div>
					<div type="checkcolumn" width="20"></div>
					<div field="TEXT" width="180">角色名称</div>
				</div>
			</div>
		</div>
	</div>
</div>


<SCRIPT LANGUAGE="JavaScript">
<!--
mini.parse();
var grid1=mini.get("datagrid1");
var grid2=mini.get("datagrid2");

var _idx=1;
function onActivechanged(e){
	var tab=e.tab;
	var name=tab.name;

	var grid=eval("grid"+name);
	if(!grid) return;
	
	_idx=name;

	if(grid.getUrl()==""){
		if(name=="2") grid.setUrl("/AjaxService?method=GetCommonList&code=GetRoleDicList");
		grid.load();
	}
}
grid1.url="/AjaxService?method=GetCommonList&code=GetEmployeeList";
grid1.load();

//-->
</script>
</body>
</html>
