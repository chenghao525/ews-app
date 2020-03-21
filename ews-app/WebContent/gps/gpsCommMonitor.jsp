<%@ page contentType="text/html; charset=UTF-8" import = "com.corp.oa.util.G" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://www.shanghaicorp.com/struts/extend" prefix="extend" %>
<%	
	String s=request.getParameter("search");
	if(s!=null && !"".equals(s) && !"null".equals(s)){s = new String(s.getBytes("ISO-8859-1"),"UTF-8"); }else{s="";} 
	String g=request.getParameter("gps");
	if(g==null || "null".equals(g))	g=""; 
	String t=request.getParameter("type");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>map</title>
</head>
<style type="text/css">
body, html{width: 100%;height: 100%;overflow: hidden;margin:0;}
#allmap {margin-right: 0px;height: 95%;width:100%;overflow: hidden;}
#result {border-left:1px dotted #999;height:100%;width:295px;position:absolute;top:0px;right:0px;font-size:12px;}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/DistanceTool/1.2/src/DistanceTool_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/MarkerTool/1.2/src/MarkerTool_min.js"></script>

<script language="JavaScript" src="/js/js.js?r=1221"></script>
<LINK href="/images/css.css" type="text/css" rel="STYLESHEET"> 
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
&nbsp;请输入搜索地址：<input type="text" size="40" name="addr" value="<%=s%>">&nbsp;<input type="submit" class="button-ui" value="搜 索" onclick="SearchAddress(document.all.addr.value);return false;" id="btnSearch">&nbsp;&nbsp;&nbsp;&nbsp;请在地图上右键进行操作
<div id="allmap" style="overflow:hidden;zoom:1;position:relative;"></div>
<div id="winInfo" class="mini-window" title="业务设置" style="width:250px;height:145px;z-index:200" showMaxButton="false" showCloseButton="false" showToolbar="false" showFooter="true" showModal="false" allowResize="false" allowDrag="true" >
    <div property="footer" style="text-align:right;padding:12px;padding-right:15px;">
        <input type='button' class="button-ui" value='确 定' onclick="onOk()"/>&nbsp;&nbsp;&nbsp;&nbsp;<input class="button-ui" type='button' value='取 消' onclick='onCancel();'/>
    </div>
	<table width="100%">
		<tr>
			<td nowrap width="*" align="right">业务地址：</td>
			<td width="99%"><input type="text" name="businessAddr" value="<%=s%>" size="26"></td>
		</tr>
		<tr>
			<td nowrap width="*" align="right">经 纬 度：</td>
			<td width="99%"><input type="text" name="addrGPS" readonly="true" value="<%=g%>" size="26"></td>
		</tr>
	</table>
</div>

</body>
</html>
<script type="text/javascript">
// global vars
var image_path="../images/";

var trackStart = image_path+"trackStart.png";
var trackEnd   = image_path+"trackEnd.png";
var imageDefault = image_path+"mapGps0.png";

var pinMarker=null;
var shpoint = "上海",bjpoint = "北京",curpoint = "上海";
var map= new BMap.Map('allmap');
map.centerAndZoom(curpoint)

var traffic = new BMap.TrafficLayer(); // 创建交通流量图层实例 
var myDis = new BMapLib.DistanceTool(map); //测距工具

//设置右键菜单
var contextMenu = new BMap.ContextMenu();

map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
map.addControl(new BMap.ScaleControl());                    // 添加默认比例尺控件
map.addControl(new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT}));                    // 左下

map.enableScrollWheelZoom(true);

var localsearch = new BMap.LocalSearch(map, {
	renderOptions:{map: map}
});

var txtMenuItem = [{text:'放大',callback:function(){map.zoomIn()}},
  {text:'缩小',callback:function(){map.zoomOut()}},
  {text:'放置到最大级',callback:function(){map.setZoom(18)}},
  {text:'查看全国',callback:function(){map.setZoom(4)}},
  {text:'显示交通流量',callback:function(){showTraffic()}},
  {text:'隐藏交通流量',callback:function(){hideTraffic()}},
  {text:'测距',callback:function(){disTool()}},
  {text:'在此添加标注',callback:function(p){
									var marker = new BMap.Marker(p), px = map.pointToPixel(p);
									map.addOverlay(marker);
									marker.enableDragging();
									getLocation(p.lng , p.lat);
									marker.addEventListener("dragend", function(e){
											getLocation(e.point.lng , e.point.lat);
										});
									if(pinMarker!=null){map.removeOverlay(pinMarker);}
									pinMarker=marker;
									pinMarker.disableMassClear();
								}}
];

for(var i=0; i < txtMenuItem.length; i++){
	contextMenu.addItem(new BMap.MenuItem(txtMenuItem[i].text,txtMenuItem[i].callback,100));
	if(i==1 || i==3) {
		contextMenu.addSeparator();
	}
}
map.addContextMenu(contextMenu);

function $(id){
	return document.getElementById(id);
}

function showTraffic(){
	map.addTileLayer(traffic);
}
function hideTraffic(){
	map.removeTileLayer(traffic);
}

function pinTool(){
	var mkrTool = new BMapLib.MarkerTool(map, {autoClose: true, followText: "点击鼠标添加标注"});
	mkrTool.open(); 
	return false;
}
function disTool(){
	myDis.open();
	return false;
}

function getLocation(lng,lat){
	setValue("addrGPS",lng+","+lat);
	var gc = new BMap.Geocoder(); 
	var pt=new BMap.Point(lng,lat);
	var l=gc.getLocation(pt, function(rs){
        var addComp = rs.addressComponents;
		var addr=addComp.city +addComp.district + addComp.street+ addComp.streetNumber;
		setValue("businessAddr",addr);
    }); 
}

function SearchAddress(addr){
	localsearch.clearResults();
	localsearch.setLocation(curpoint);
	localsearch.search(addr);
}

function draw_line(points,endHide){

	var data = '';
	try {
		data = eval(points);
	} catch (e) {
		alert('parse error789:' + e);
		return ;
	}
	if (data.length == undefined || data.length < 1) {
		alert('no data');
		return ;
	}

	var ptarr=new Array();
	for (var i=0; i<data.length; i++) {
		ptarr[i]= new BMap.Point(data[i][1], data[i][0]);
		if(i==0){
			var myIcon = new BMap.Icon(trackStart, new BMap.Size(36,36));
			var m = new BMap.Marker(ptarr[i],{icon:myIcon});
			map.addOverlay(m);
		}
		if(i==data.length-1){
			if(!(endHide!=null && endHide!="" && endHide=="true")){
				var myIcon = new BMap.Icon(trackEnd, new BMap.Size(36,36));
				var m = new BMap.Marker(ptarr[i],{icon:myIcon});
				map.addOverlay(m);
			}
		}
	}
	
	var polyline = new BMap.Polyline(ptarr, {strokeColor:"blue", strokeWeight:3, strokeOpacity:0.5});
	map.addOverlay(polyline);

	map.setViewport(ptarr);
	map.addControl(new BMap.OverviewMapControl());              //添加默认缩略地图控件
	map.addControl(new BMap.OverviewMapControl({isOpen:true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT}));   //右上角，打开
}
function addExistPoint(){
	if(nvl("<%=g%>")!=""){
		var g="<%=g%>".split(",");
		var lng=g[0];
		var lat=g[1];
		var point=new BMap.Point(lng,lat);
		var marker = new BMap.Marker(point);
		map.addOverlay(marker);
		marker.enableDragging();
		marker.addEventListener("dragend", function(e){
				getLocation(e.point.lng , e.point.lat);
			});
		if(pinMarker!=null){map.removeOverlay(pinMarker);}
		pinMarker=marker;
		pinMarker.disableMassClear();
		
		var ptarr=new Array(point);
		map.setViewport(ptarr);
	}
}

mini.parse();
function showBusinessInfo(type) {
	if("ywd"==type){
		var win = mini.get("winInfo");
		win.show("right","top");
	}
}

function check(){
	if(getValue("addrGPS")==""){alert("请选择业务的地址信息");return false;}else{return true;}
}
function getData(){
	var a = new Array();
	a[0]=getValue("businessAddr");
	a[1]=getValue("addrGPS");
	return a;
}

function CloseWindow(action) {if (window.CloseOwnerWindow) window.CloseOwnerWindow(action); else window.close();}
function onOk(){if(check()) CloseWindow("ok");}
function onCancel() {CloseWindow("cancel");}
showBusinessInfo("<%=t%>");
addExistPoint();
</script>
