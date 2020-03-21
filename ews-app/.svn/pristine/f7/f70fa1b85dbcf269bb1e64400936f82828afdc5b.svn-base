<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://www.shanghaicorp.com/struts/extend" prefix="extend" %>
<html>
<HEAD><title></title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type=text/css rel=stylesheet>
</HEAD>
<script type="text/javascript" src="/js/js.js?r=1221"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
<style type="text/css">
    html body .searchbox .mini-buttonedit-icon
    {
        background:url(/scripts/miniscript/themes/icons/search.gif) no-repeat 50% 50%;
    }
</style>
<body align="left" leftmargin="1" topmargin="1" marginwidth="0" marginheight="0">
	<div class="mini-splitter" style="width:100%;height:100%;border:0px;">
		<div size="260px" showCollapseButton="true" style="border:0px;">
			<div class="mini-splitter" vertical="true" showCollapseButton="false" allowResize="false" style="width:260px;height:100%;border:0px;">
				<div size="2px" style="border:0px;">
					<input style="width:100%" id="orgSearch" class="mini-buttonedit searchbox" emptyText="请输入..." showClose="true" oncloseclick="onCloseClick" onbuttonclick="provFilter" onenter="provFilter"/>
				</div>
				<div style="border:0px;">
					<ul id="leftTree" class="mini-tree" url="/AjaxService?method=GetOrgTree" style="width:100%;height:100%;" showTreeIcon="true" textField="DEPTNAME" idField="SID" iconField="ICONCLS" parentField="PID" resultAsTree="false" onnodeselect="onNodeSelect"></ul>
				</div>        
			</div>
		</div>
		<div showCollapseButton="false">
			<iframe id="right" src="" frameborder="0" name="right" style="width:100%;height:100%;" border="0" scrolling="auto"></iframe>
		</div>        
	</div>
</body>
</html>
<script type="text/javascript">
<!--
 mini.parse();
 function onNodeSelect(e) {
	var tree=mini.get("leftTree");
	var node = e.node;
	var sid=node.SID;
	var psid="";
	var pnode=tree.getParentNode(node);
	if(pnode!=null)	psid=pnode.SID;

	if (node.INFO_CLASS=="3") right.location.href="/system/user/userDetail.jsp?org_sid="+sid+"&org_psid="+psid+"&type=update";
	else if(node.INFO_CLASS=="2" || node.INFO_CLASS=="1")
	    right.location.href="/system/org.do?b_deptfind=true&dept_sid="+sid+"&org_sid="+sid+"&org_psid="+psid;
}

function refresh(sid,psid){
	var tree=mini.get("leftTree");
	tree.load("/AjaxService?method=GetOrgTree");
	var a=tree.getAllChildNodes(tree.getRootNode());
	for(var i=0;i<a.length;i++){if(a[i].SID==sid){tree.expandPath(a[i]);return;}}
	if(psid!=null && psid!=""){for(var i=0;i<a.length;i++){if(a[i].SID==psid){tree.expandPath(a[i]);return;}}}
}

 function onCloseClick(e) {
	 var ps = mini.get("orgSearch");
	 ps.setText("");ps.setValue("");
}

function provFilter(){
	var tree = mini.get("leftTree");
	var key = mini.get("orgSearch").getValue(); //查询内容
	if (key==null || key == "") {
		 tree.clearFilter();
	 } else {
		 tree.filter(function (node) {
			 var text = node[tree.textField];
			 var isleaf = tree.isLeaf(node);
			 if (text.indexOf(key) != -1) {
				 return true;
			 }
		 });
		 // 展开所有
		tree.expandAll();
	 }
}

//-->
</script>
