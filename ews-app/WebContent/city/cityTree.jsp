<%@ page contentType="text/html; charset=UTF-8" import="com.corp.oa.util.G" %>
<html>
<HEAD><title></title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type=text/css rel=stylesheet>
</HEAD>
<script type="text/javascript" src="/js/js.js?r=1221"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
<style type="text/css">
html body .searchbox .mini-buttonedit-icon{
	background:url(/scripts/miniscript/themes/icons/search.gif) no-repeat 50% 50%;
}
</style>
<%
	if(!G.isOwnRight("10235000",request)){out.println("没有权限或会话已失效");return;}
%>
<body align="left" leftmargin="1" topmargin="1" marginwidth="0" marginheight="0">
	<div class="mini-splitter" style="width:100%;height:100%;border:0px;">
		<div size="260px" showCollapseButton="true" style="border:0px;">
			<div class="mini-splitter" vertical="true" showCollapseButton="false" allowResize="false" style="width:260px;height:100%;border:0px;">
				<div size="1px" style="border:0px;">
					<input style="width:99%" id="maSearch" class="mini-buttonedit searchbox" emptyText="请输入城市名..." onbuttonclick="provFilter" showClose="true" oncloseclick="onCloseClick" onenter="provFilter"/>
				</div>
				<div style="border:0px;">
					<ul id="leftTree" class="mini-tree" style="width:100%;height:100%;" showTreeIcon="true"  textField="TEXT" idField="ID" iconField="ICONCLS" parentField="PID" resultAsTree="false" onnodeselect="onNodeSelect" onbeforenodeselect="beforenodeselect"></ul>
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
 var cityJson=mini.decode(top._cityData);
function showCity(id){var obj=mini.get(id);obj.loadList(cityJson,"ID","PID");};

showCity('leftTree');
function beforenodeselect(e){if (e.isLeaf == false) e.cancel = true;}
 function onNodeSelect(e) {
	var tree=mini.get("leftTree");
	var node = e.node;
	var sid=node.ID;


	var psid="";
	var level=node._level;
	var pnode=tree.getParentNode(node);
	if(pnode!=null)	psid=pnode.SID;
	right.location.href="/city/cityEdit.jsp?sid="+sid;
}

 function onCloseClick(e) {
	var ps = mini.get("maSearch");
	ps.setText("");ps.setValue("");
}

function provFilter(){
	var tree = mini.get("leftTree");
	var key = mini.get("maSearch").getValue(); //查询内容
	if (key==null || key == "") {
		 tree.clearFilter();
		 tree.collapseAll();
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
