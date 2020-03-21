<%@ page contentType="text/html; charset=UTF-8" import = "com.corp.oa.util.G" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://www.shanghaicorp.com/struts/extend" prefix="extend" %>
<%	
	String s=request.getParameter("search");
	if(s!=null && !"".equals(s) && !"null".equals(s)) s = new String(s.getBytes("ISO-8859-1"),"UTF-8"); 
	else s="";
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
li{line-height:28px;}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.4/src/SearchInfoWindow_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/DistanceTool/1.2/src/DistanceTool_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/RectangleZoom/1.2/src/RectangleZoom_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/MarkerTool/1.2/src/MarkerTool_min.js"></script>

<script language="JavaScript" src="/js/js.js?r=1221"></script>
<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.4/src/SearchInfoWindow_min.css" />
<LINK href="/images/css.css" type="text/css" rel="STYLESHEET"> 
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%">
<tr>
	<td width="*">&nbsp;请输入搜索地址：<input type="text" size="40" name="addr" value="<%=s%>">&nbsp;<input type="submit" class="button-ui" value="搜 索" onclick="SearchAddress(document.all.addr.value);return false;" id="btnSearch">&nbsp;&nbsp;&nbsp;&nbsp;请在地图上右键进行操作</td>
</tr>
</table>
<div id="allmap" style="overflow:hidden;zoom:1;position:relative;"></div>

<script type="text/javascript">
// global vars
var image_path="../images/";

var trackStart = image_path+"gpsStart.png";
var trackEnd   = image_path+"gpsEnd.png";
var imageDefault = image_path+"mapGps0.png";

var marker = new Array(),markerLine = null,searchInfoWindow = new Array(),pinMarker=null;

var map= new BMap.Map('allmap');

var traffic = new BMap.TrafficLayer(); // 创建交通流量图层实例 
var myDis = new BMapLib.DistanceTool(map); //测距工具
//var myDrag = new BMapLib.RectangleZoom(map, {followText: "拖拽鼠标进行操作"});//区域缩放工具

var shpoint = "上海",bjpoint = "北京",curpoint = "";
var markPoints;

//设置右键菜单
var contextMenu = new BMap.ContextMenu();

map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
map.addControl(new BMap.ScaleControl());                    // 添加默认比例尺控件
map.addControl(new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT}));                    // 左下

//map.addControl(new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}));  //右上角，仅包含平移和缩放按钮
//map.addControl(new BMap.NavigationControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT, type: BMAP_NAVIGATION_CONTROL_PAN}));  //左下角，仅包含平移按钮
//map.addControl(new BMap.NavigationControl({anchor: BMAP_ANCHOR_BOTTOM_RIGHT, type: BMAP_NAVIGATION_CONTROL_ZOOM}));  //右下角，仅包含缩放按钮

map.enableScrollWheelZoom(true);
//map.addControl(new BMap.MapTypeControl());

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

function addExistPoint(){
	if(nvl("<%=g%>")!=""){
		var g="<%=g%>".split(",");
		var lng=g[0];
		var lat=g[1];
		var marker = new BMap.Marker(new BMap.Point(lng,lat));
		map.addOverlay(marker);
		marker.enableDragging();
		marker.addEventListener("dragend", function(e){
				//e.setTop(true);
				getLocation(e.point.lng , e.point.lat);
			});
		if(pinMarker!=null){map.removeOverlay(pinMarker);}
		pinMarker=marker;
		pinMarker.disableMassClear();
	}
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
	//return false;
}

function init_markers(x,city) {
	
	map.clearOverlays();
	if(city == "210"){curpoint=shpoint;}
	if(city == "211"){curpoint=bjpoint;}

	if(x==null) {
		map.setCenter(curpoint);
		return;
	}
	var data = '';
	//alert(json2str(x));
	try {
		data = eval(x);
	} catch (e) {
		map.clearOverlays();
		alert('parse error:456' + e);
		return ;
	}
	if (data.length == undefined || data.length < 1) {
		alert('no data');
		return ;
	}
	markPoints=data;
	
	show_map_markes();
}

function show_map_markes(){
	
	var pt="",photo="",myIcon="";

	var pointArr=new Array();

	for (var i = 0; i < markPoints.length; i ++) {
		pt = new BMap.Point(markPoints[i].longitude,markPoints[i].latitude);

		pointArr[i]=pt;
		
		if(markPoints[i].icon==null || markPoints[i].icon=="")
			photo=imageDefault;
		else
			photo=image_path+markPoints[i].icon;

		myIcon = new BMap.Icon(photo, new BMap.Size(48,48));
		
		marker[i] = new BMap.Marker(pt,{icon:myIcon});
		//marker[i].setTop(true);
		setWindow(marker[i],i);
		map.addOverlay(marker[i]);
	}
	map.setViewport(pointArr);
	map.addControl(new BMap.OverviewMapControl());              //添加默认缩略地图控件
	map.addControl(new BMap.OverviewMapControl({isOpen:false, anchor: BMAP_ANCHOR_BOTTOM_RIGHT}));   //右上角，打开

	if("<%=t%>"=="da"){//显示已存在的点和业务设置对话框
		addExistPoint();
		showBusinessInfo();
	}
}

function setBusiness(i,m,e,en){
	setValue("businessEmp",en);
	setValue("empInfo_mobile",m);
	setValue("empInfo_sid",e);
	setValue("empInfo_name",en);
	searchInfoWindow[i].hide(); 
}

function getLocation(lng,lat){
	//alert(lng);
	setValue("addrGPS",lng+","+lat);
	var gc = new BMap.Geocoder(); 
	var pt=new BMap.Point(lng,lat);
	var l=gc.getLocation(pt, function(rs){
        var addComp = rs.addressComponents;
		//var addr=addComp.province+addComp.city +addComp.district + addComp.street+ addComp.streetNumber;
		var addr=addComp.city +addComp.district + addComp.street+ addComp.streetNumber;
		//alert(addr);
		setValue("businessAddr",addr);
    }); 
}

function showEmployee(sid){
	var w=window.open('/expressemp/manage.do?b_expressedit=true&emp_sid='+sid,'emp','width=1000px,height=600px,menubar=0,toolbar=0,directories=0,location=0,status=0,scrollbars=1');	
	w.focus();
	return false;
}

function setWindow(m,i){
	if(markPoints[i].employee!=null && markPoints[i].employee!=""){
		var content = '<table><tr><td><table><tr><td width="188" nowrap>'+markPoints[i].msg+'<br><hr></td></tr><tr><td nowrap>&nbsp;●&nbsp;<a href="#0" onclick="showEmployee(\''+markPoints[i].employee+'\')">查看&nbsp;'+markPoints[i].empname+'&nbsp;的个人信息</a></td></tr><tr><td nowrap>&nbsp;●&nbsp;<a href="#0" onclick="sendSMS(\''+markPoints[i].phone+'\')">给&nbsp;'+markPoints[i].empname+'&nbsp;发短信</a></td></tr><tr><td nowrap>&nbsp;●&nbsp;<a href="#0" onclick="alert(\'[ '+markPoints[i].phone+' ],inProgress...\')">给&nbsp;'+markPoints[i].empname+'&nbsp;打电话</a></td></tr><tr><td nowrap>&nbsp;●&nbsp;<a href="#0" onclick="setBusiness('+i+',\''+markPoints[i].mobileid+'\',\''+markPoints[i].employee+'\',\''+markPoints[i].empname+'\')">给&nbsp;'+markPoints[i].empname+'&nbsp;派发业务</a></td></tr></table></td><td valign="top"><a href="#0" onclick="showEmployee(\''+markPoints[i].employee+'\')"><img src="'+markPoints[i].photo+'" width="96" height="128" border="1" alt="个人信息" style="cursor:hand;"></a><br>积分：'+markPoints[i].point+'<br>星级：'+markPoints[i].star+'&nbsp;星</td></tr></table>';

		//创建检索信息窗口对象
		searchInfoWindow[i] = new BMapLib.SearchInfoWindow(map, content, {
			title  : "信息框",      //标题
			width  : 300,             //宽度
			height : 175,              //高度
			panel  : "panel",         //检索结果面板
			enableAutoPan : true,     //自动平移
			searchTypes   :[
				BMAPLIB_TAB_SEARCH,   //周边检索
				BMAPLIB_TAB_TO_HERE,  //到这里去
				BMAPLIB_TAB_FROM_HERE //从这里出发
			]
		});

		//m.enableDragging(); //marker可拖拽
		m.addEventListener("click", function(e){
			searchInfoWindow[i].open(m);
		});
	}
}

function SearchAddress(addr){
	localsearch.clearResults();
	localsearch.setLocation(curpoint);
	localsearch.search(addr);
}

function draw_line(points,endHide){

	var data = '';
	try {data = eval(points);} catch (e) {alert('parse error123:' + e);return ;}
	if (data.length == undefined || data.length < 1) {alert('no data');return ;}

	markerLine=new Array();
	for (var i=0; i<data.length; i++) {
		markerLine[i]= new BMap.Point(data[i][1], data[i][0]);
		if(i==0){
			var myIcon = new BMap.Icon(trackStart, new BMap.Size(36,36));
			var m = new BMap.Marker(markerLine[i],{icon:myIcon});
			map.addOverlay(m);
		}
		if(i==data.length-1){
			if(!(endHide!=null && endHide!="" && endHide=="true")){
				var myIcon = new BMap.Icon(trackEnd, new BMap.Size(36,36));
				var m = new BMap.Marker(markerLine[i],{icon:myIcon});
				map.addOverlay(m);
			}
		}
	}
	
	var polyline = new BMap.Polyline(markerLine, {strokeColor:"blue", strokeWeight:3, strokeOpacity:0.5});
	map.addOverlay(polyline);

	map.setViewport(markerLine);
	map.addControl(new BMap.OverviewMapControl());              //添加默认缩略地图控件
	map.addControl(new BMap.OverviewMapControl({isOpen:true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT}));   //右上角，打开
}

function markerClick(idx) {
	map.setCenter(new BMap.Point(markPoints[idx].longitude,markPoints[idx].latitude));
	for(var i=0;i<marker.length;i++)
		marker[i].setTop(false);
	marker[idx].setTop(true);
	marker[idx].setAnimation(BMAP_ANIMATION_BOUNCE);
	setTimeout(function(){marker[idx].setAnimation(null);},3000);
}

function markCustGPS(lng,lat) {
	var marker = new BMap.Marker(new BMap.Point(lng,lat));
	map.addOverlay(marker);
	marker.disableDragging();
	marker.setTop(true);
	marker.setTitle("客户所在地址");
	marker.setAnimation(BMAP_ANIMATION_BOUNCE);
}

function markDriverStartingGPS(r) {
	var data;
	try {data = eval(r);}catch (e){return;}
	if (data==null || data == undefined ) return;

	var lng=data.LONGITUDE;
	var lat=data.LATITUDE;
	var pt= new BMap.Point(lng,lat);
	var myIcon = new BMap.Icon(image_path+"mapGpsTasking.png", new BMap.Size(48,48));
	var marker = new BMap.Marker(pt,{icon:myIcon});

	map.addOverlay(marker);
	marker.disableDragging();
	marker.setTop(true);
	marker.setTitle("任务开始时间："+data.ACT_SRV_TIME_START+"\nGPS定位时间："+data.GPS_TIME+"\n时间间隔："+data.MIN_TIME+"分钟");
}

try{
	if(parent.left!=null) parent.setAddr();
}catch(e){}
</script>

<div id="winInfo" class="mini-window" title="业务设置" style="width:300px;height:160px;z-index:200" showMaxButton="false" showCloseButton="false" showToolbar="false" showFooter="true" showModal="false" allowResize="false" allowDrag="true" >
    <div property="footer" style="text-align:right;padding:12px;padding-right:15px;">
        <input type='button' class="button-ui" value='确 定' onclick="onOk()"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' class="button-ui" value='取 消' onclick='onCancel();'/>
    </div>
	<table width="100%">
		<tr>
			<td nowrap width="*" align="right">业务地址：</td>
			<td><input type="text" name="businessAddr" id="businessAddr" value="<%=s%>" size="25" class="mini-text">&nbsp;<font color="red">*</font><input type="hidden" name="addrGPS" value="<%=g%>"></td>
		</tr>
		<tr>
			<td nowrap width="*" align="right">业 务 员：</td>
			<td><input type="text" name="businessEmp" id="businessEmp" readonly="true" size="25"><input type="hidden" name="empInfo_mobile" id="empInfo_mobile"><input type="hidden" name="empInfo_sid"><input type="hidden" name="empInfo_name"></td>
		</tr>
	</table>
</div>

</body>
</html>

<script type="text/javascript">
function showBusinessInfo() {
	var win = mini.get("winInfo");
	win.show("right","top");
}

function check(){
	if(getValue("addrGPS")==""){alert("请选择业务的地址信息");return false;}
	//if(getValue("empInfo_sid")==""){alert("请选择业务员");return false;}
	return true;
}
function getData(){
	var ar = new Array();
	ar[0]=getValue("empInfo_mobile")+"";
	ar[1]=getValue("empInfo_sid")+"";
	ar[2]=getValue("empInfo_name")+"";
	ar[3]=getValue("businessAddr")+"";
	ar[4]=getValue("addrGPS");
	return ar;
}

function CloseWindow(action) {if (parent.window.CloseOwnerWindow) parent.window.CloseOwnerWindow(action); else window.close();}
function onOk(){if(check()) CloseWindow("ok");}
function onCancel() {CloseWindow("cancel");}
</script>