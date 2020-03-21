<%@ page contentType="text/html; charset=UTF-8" import = "com.corp.oa.util.G" %>

<%	
	String s=request.getParameter("s");
	if(!G.isNvl(s)) s = new String(s.getBytes("ISO-8859-1"),"UTF-8"); 
	else s="";

	String e=request.getParameter("e");
	if(!G.isNvl(e)) e = new String(e.getBytes("ISO-8859-1"),"UTF-8"); 
	else e="";

	String c=request.getParameter("c");
%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<LINK href="/images/css.css" type="text/css" rel="STYLESHEET"> 
<script language="JavaScript" src="/js/js.js?r=1221"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
<style type="text/css">
body, html{width: 100%;height: 100%;overflow: hidden;margin:0;}
#l-map{height:650px;width:78%;float:left;border-right:2px solid #bcbcbc;}
#r-result{height:650px;width:21.8%;float:left;overflow-y:auto;overflow-x:hidden}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=HySLOoFI4waSiEp2SAV6zILv"></script>
<title>驾车导航</title>
</head>
<body>
<table border="0" cellpadding="3" cellspacing="0" width="100%">
<tr height="25">
	<td width="10">&nbsp;</td>
	<td>起点：<input type="text" id="start" value="<%=s%>">&nbsp;&nbsp;&nbsp;&nbsp;终点：<input type="text" id="end" value="<%=e%>"><input type="button" value="查询" class="button-ui" onclick="search();"></td>
</tr>
</table>
<div id="l-map"></div>
<div id="r-result"></div>
</body>
</html>
<script type="text/javascript">

// 百度地图API功能
var map = new BMap.Map("l-map");
map.centerAndZoom("上海",11); 
map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
map.addControl(new BMap.ScaleControl());                    // 添加默认比例尺控件
map.addControl(new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT}));                    // 左下
map.enableScrollWheelZoom(true);

var transit = new BMap.DrivingRoute(map, {
	renderOptions: {
		map: map,
		panel: "r-result",
		enableDragging : true //起终点可进行拖拽
	}});
transit.search("<%=s%>","<%=e%>");
function search(){
	var s=$("#start").val();
	var e=$("#end").val();
	transit.search(s,e);
}
</script>

