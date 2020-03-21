var head = document.head || document.getElementsByTagName("head")[0] || document.documentElement;
var oscript = document.createElement("script");
oscript.src ="/js/LodopFuncs.js";
head.insertBefore( oscript,head.firstChild );

function getDueTime(param){
	try {
		if(param.DUE_TIME&&param.DUE_TIME.length==22){
			return param.DUE_TIME;
		}else{
			if(param.DUE_PAY_START_TIME&&param.DUE_PAY_START_TIME!=""&&param.DUE_PAY_END_TIME&&param.DUE_PAY_END_TIME!=""){
				return (param.DELIVERY_DATE.substring(0,10)+" "+param.DUE_PAY_START_TIME+"-"+param.DUE_PAY_END_TIME);
			}else{
				return "--";
			}
		}	
	} catch (e) {
		return "--";
	}
	
	
}
function getStars(length){
	var stars="";
	for(var i=0;i<length;i++){
		stars+="*";
	}
	return stars;
}

function getAddr(addr){
	var newAddr=addr;
	//隐藏门牌号
//	if(addr.indexOf("号")>=0&&addr.indexOf("号")==addr.lastIndexOf("号")){
//		
//		if(addr.indexOf("室")>=0){
//			newAddr=addr.substring(0,addr.indexOf("号")+1)+"***"+addr.substring(addr.indexOf("室"),addr.length);
//		}else{
//			//newAddr=addr.substring(0,addr.indexOf("号")+1)+getStars(addr.length-addr.indexOf("号")-1);
//			
//			//if(addr.endsWith("号")){
////				newAddr=addr.substring(0,addr.indexOf("号")+1);
////			}else{
//				newAddr=addr.substring(0,addr.indexOf("号")+1)+"***";
////			}
//		}
//	}else{
//		if(addr.indexOf("弄")>=0){
//			if(addr.indexOf("室")>=0){
//				newAddr=addr.substring(0,addr.indexOf("弄")+1)+"***"+addr.substring(addr.indexOf("室"),addr.length);
//			}else{
//				//newAddr=addr.substring(0,addr.indexOf("弄")+1)+getStars(addr.length-addr.indexOf("弄")-1);
//				
////				if(addr.endsWith("弄")){
////					newAddr=addr.substring(0,addr.indexOf("弄")+1);
////				}else{
//					newAddr=addr.substring(0,addr.indexOf("弄")+1)+"***";
////				}
//			}
//		}
//		
//	}
	//隐藏手机号
	var reg = /\d+/g;
	var phones = newAddr.match(reg);
	if(phones&&null!=phones){
		for(var i=0;i<phones.length;i++){
			if(phones[i].length==11){
				var rep=phones[i].substring(0,3)+"****"+phones[i].substring(7,11);
				newAddr=newAddr.replace(phones[i],rep);
			}
		}
	}
	if(newAddr.length>60){
		newAddr=newAddr.substring(0,58)+"***";
	}
	return newAddr;
	
}

Date.prototype.pattern=function(fmt) {         
    var o = {         
    "M+" : this.getMonth()+1, //月份         
    "d+" : this.getDate(), //日         
    "h+" : this.getHours(), //小时         
    "H+" : this.getHours(), //小时         
    "m+" : this.getMinutes(), //分         
    "s+" : this.getSeconds(), //秒         
    "q+" : Math.floor((this.getMonth()+3)/3), //季度         
    "S" : this.getMilliseconds() //毫秒         
    };         
    var week = {         
    "0" : "/u65e5",         
    "1" : "/u4e00",         
    "2" : "/u4e8c",         
    "3" : "/u4e09",         
    "4" : "/u56db",         
    "5" : "/u4e94",         
    "6" : "/u516d"        
    };         
    if(/(y+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));         
    }         
    if(/(E+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);         
    }         
    for(var k in o){         
        if(new RegExp("("+ k +")").test(fmt)){         
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));         
        }         
    }         
    return fmt;         
} 

function SelectAsDefaultPrinter(res) {
		if(!LODOP) LODOP=getLodop(); 
		if(!LODOP) return; 
		if (LODOP.CVERSION) {
			LODOP.On_Return=res;
			LODOP.SET_PRINT_COPIES(1);
			LODOP.SELECT_PRINTER();
			
			return;
		};
        	//if (LODOP.SELECT_PRINTER()>=0) 
//        	alert("选择成功!"); else alert("选择失败！");
}

function design(param){
	
	LODOP.NewPage();
	if(param.ORDER_TYPE=='410'){
		designA(param);//保单(垫付)
	}else if(param.ORDER_TYPE=='411'){
		designE(param);//保费
	}else if(param.ORDER_TYPE=='412'){
		designC(param);//发票
	}else if(param.ORDER_TYPE=='413'){
		designB(param);//非车
	}else if(param.ORDER_TYPE=='414'){
		designD(param);//加急
	}else if(param.ORDER_TYPE=='415'){
		designA(param);//保单(非垫付)
	}else{
		return;
	}
	
		
//	LODOP.PRINT_DESIGN();
}

function designRows(rows){
	if(!LODOP) LODOP=getLodop(); 
	if(!LODOP) return;
	LODOP.PRINT_INIT("print");
	for(var i=0;i<rows.length;i++){
    	//rows[i].index="页码："+(i+1)+"/"+rows.length;
    	design(rows[i]);
    }
    LODOP.PRINT();
}
function designA(param){


//LODOP.PRINT_INITA(0,1,793,1123,"");
//LODOP.SET_PRINT_MODE("PRINT_NOCOLLATE",1);
//LODOP.ADD_PRINT_TEXT(65,235,127,37,"太平洋电销");
//LODOP.SET_PRINT_STYLEA(0,"FontSize",17);
//LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
LODOP.ADD_PRINT_TEXT(26,68,150,54,"太平洋保险\r\nCPIC");
LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
LODOP.SET_PRINT_STYLEA(0,"Bold",1);
//LODOP.ADD_PRINT_TEXT(63,320,58,36,"保单");
//LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
//LODOP.SET_PRINT_STYLEA(0,"FontSize",17);
//LODOP.SET_PRINT_STYLEA(0,"Bold",1);
LODOP.ADD_PRINT_TEXT(65,295,180,36,"保单配送单");
LODOP.SET_PRINT_STYLEA(0,"FontSize",17);
LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
LODOP.ADD_PRINT_TEXT(92,245,280,20,"（为保护个人隐私，部分信息打码隐藏）");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.SET_PRINT_STYLEA(0,"FontColor","#808080");
LODOP.SET_PRINT_STYLEA(0,"Alignment",2);

LODOP.ADD_PRINT_TEXT(135,185,62,21,"车牌号：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(135,241,155,21,param.VEHICLE_LICENSE.substring(0,1)+getStars(param.VEHICLE_LICENSE.length-4)+param.VEHICLE_LICENSE.substring(param.VEHICLE_LICENSE.length-3)+"\r\n");
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.ADD_PRINT_TEXT(159,185,100,21,"交强险保单号：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);

if(param.TCP_POLICY_NO&&param.TCP_POLICY_NO!=""){
LODOP.ADD_PRINT_TEXT(159,279,180,21,getStars(param.TCP_POLICY_NO.length-6)+param.TCP_POLICY_NO.substring(param.TCP_POLICY_NO.length-6));
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
}
LODOP.ADD_PRINT_TEXT(183,185,100,21,"商业险保单号：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);


if(param.ACI_POLICY_NO&&param.ACI_POLICY_NO!=""){
LODOP.ADD_PRINT_TEXT(183,279,180,21,getStars(param.ACI_POLICY_NO.length-6)+param.ACI_POLICY_NO.substring(param.ACI_POLICY_NO.length-6));
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
}
LODOP.ADD_PRINT_TEXT(207,185,62,21,"收件人：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(207,241,155,21,param.ADDRESSEE);
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.SET_PRINT_STYLEA(0,"Bold",1);
LODOP.ADD_PRINT_TEXT(231,185,75,21,"预约时间：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(231,254,195,21,getDueTime(param));
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.ADD_PRINT_TEXT(255,185,51,21,"区域：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(255,230,195,21,param.REGION_NAME);
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.SET_PRINT_STYLEA(0,"Bold",1);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(279,185,51,21,"地址：");
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(279,230,480,42,getAddr(param.DELIVERY_ADSTREET));
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);

//LODOP.ADD_PRINT_TEXT(135,19,130,20,param.ORDER_NUM);
//LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
//LODOP.SET_PRINT_STYLEA(0,"AlignJustify",1);
//LODOP.SET_PRINT_STYLEA(0,"Horient",3);
LODOP.ADD_PRINT_TEXT(135,17,130,20,param.ORDER_NUM);
LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",2);

LODOP.ADD_PRINT_BARCODE(145,19,125,125,"QRCode",param.PAY_APPLY_NO);
LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
LODOP.SET_PRINT_STYLEA(0,"QRCodeVersion",3);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
var pos="";
if(param.PAY_WAY&&param.PAY_WAY=="快钱"){
	pos="快钱";
}else if(param.PAY_WAY&&param.PAY_WAY=="alipay"){
	pos="蚂蚁";
}
LODOP.ADD_PRINT_TEXT(270,17,120,22,pos+"支付号");
LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
LODOP.SET_PRINT_STYLEA(0,"FontColor","#000040");
LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
LODOP.SET_PRINT_STYLEA(0,"Bold",1);
LODOP.ADD_PRINT_TEXT(290,17,120,25,param.PAY_APPLY_NO.substring(param.PAY_APPLY_NO.length-10,param.PAY_APPLY_NO.length));
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.SET_PRINT_STYLEA(0,"FontColor","#400040");
LODOP.SET_PRINT_STYLEA(0,"Alignment",2);

//if(param.PAY_FEE&&param.PAY_FEE!=""&&param.PAY_FEE!="0"){
LODOP.ADD_PRINT_TEXT(183,561,190,30,"应收:"+(param.PAY_FEE&&param.PAY_FEE!=""?param.PAY_FEE:"0")+"元");
LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
//}
LODOP.ADD_PRINT_LINE(335,19,336,739,2,0);
//LODOP.ADD_PRINT_HTM(307,19,730,20,"<p>------------------------------------------------------------------------------------------------------------------------</p>");
//LODOP.ADD_PRINT_LINE(304,163,135,164,0,1);
LODOP.ADD_PRINT_TEXT(345,19,732,60,"温馨提示：\r\n投保交强险请验收：①交强险保单正本、副本 ②交强险标志 ③交强险发票（电子发票）。\r\n投保商业险请验收：①神行车保系列产品保险单 ②商业险发票（电子发票）。\r\n电子发票：可通过短信、邮箱或太保官网进行查询及下载。 快递服务监督电话:66779900-2515");
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
LODOP.SET_PRINT_STYLEA(0,"FontColor","#5C5C5C");
LODOP.SET_PRINT_STYLEA(0,"LineSpacing",2);
if(param.PRESENT_CODE&&param.PRESENT_CODE!=""){
	LODOP.ADD_PRINT_TEXT(424,19,53,18,"备注：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(424,71,680,43,param.PRESENT_CODE+"\r\n");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	
}else{
	if(param.GIFT_LIST&&param.GIFT_LIST!=""){
		LODOP.ADD_PRINT_TEXT(424,19,53,18,"备注：");
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
		LODOP.ADD_PRINT_TEXT(424,71,680,43,param.GIFT_LIST+"\r\n");
		LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	
	}
} 
LODOP.ADD_PRINT_TEXT(470,19,96,25,"配送公司：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.ADD_PRINT_TEXT(470,104,92,23,param.EXPRESS_COMPANY_NAME_SUM);
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
LODOP.ADD_PRINT_TEXT(470,300,110,29,"收件人签名：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.ADD_PRINT_IMAGE(24,19,48,48,"<img src='../images/cpic.png'>");
//LODOP.ADD_PRINT_IMAGE(29,692,56,55,"<img src='../images/A.png'>");
LODOP.ADD_PRINT_TEXT(29,692,43,40,"A");
LODOP.SET_PRINT_STYLEA(0,"FontSize",26);
LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
LODOP.ADD_PRINT_TEXT(15,550,120,21,param.index);
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
if(param.ORDER_TYPE=='415'){
	
	if(param.CREATE_TIME&& typeof param.CREATE_TIME =="object"&&param.CREATE_TIME.getDate()){
		LODOP.ADD_PRINT_TEXT(135,693,52,21,param.CREATE_TIME.getDate()+"号");
	}
}else{
	LODOP.ADD_PRINT_TEXT(135,693,52,21,param.PAY_APPLY_NO.substring(param.PAY_APPLY_NO.length-8,param.PAY_APPLY_NO.length-6)+"号");
}

LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
LODOP.SET_PRINT_STYLEA(0,"Bold",1);
LODOP.ADD_PRINT_TEXT(512,19,64,20,"录单工号：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.ADD_PRINT_TEXT(511,80,100,20,param.SEAT_NO);
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.ADD_PRINT_TEXT(512,467,53,20,"经办人：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.ADD_PRINT_TEXT(511,518,65,20,param.AGENTER);
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.ADD_PRINT_TEXT(512,578,55,25,"打印时间：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
LODOP.ADD_PRINT_TEXT(512,632,119,25,new Date().pattern("yyyy-MM-dd hh:mm:ss"));
LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
LODOP.SET_PRINT_STYLEA(0,"Horient",1);
	LODOP.ADD_PRINT_LINE(585,19,586,739,1,0);
	LODOP.SET_PRINT_STYLEA(0,"Horient",2);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",2);
	
	
	
	//LODOP.ADD_PRINT_IMAGE(525,717,44,20,"<img src='../images/scissors.png'>");
	//LODOP.ADD_PRINT_TEXT(625,235,127,37,"太平洋电销");
	//LODOP.SET_PRINT_STYLEA(0,"FontSize",17);
	//LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(590,68,150,54,"太平洋保险\r\nCPIC");
	LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.ADD_PRINT_TEXT(625,295,180,36,"保单配送单");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",17);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(652,245,280,20,"（为保护个人隐私，部分信息打码隐藏）");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#808080");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(695,185,62,21,"车牌号：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(695,241,155,21,param.VEHICLE_LICENSE.substring(0,1)+getStars(param.VEHICLE_LICENSE.length-4)+param.VEHICLE_LICENSE.substring(param.VEHICLE_LICENSE.length-3)+"\r\n\r\n");
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.ADD_PRINT_TEXT(719,185,100,22,"交强险保单号：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	if(param.TCP_POLICY_NO&&param.TCP_POLICY_NO!=""){
	LODOP.ADD_PRINT_TEXT(719,279,180,21,getStars(param.TCP_POLICY_NO.length-6)+param.TCP_POLICY_NO.substring(param.TCP_POLICY_NO.length-6));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	}
	LODOP.ADD_PRINT_TEXT(743,185,100,21,"商业险保单号：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	if(param.ACI_POLICY_NO&&param.ACI_POLICY_NO!=""){
	LODOP.ADD_PRINT_TEXT(743,279,180,21,getStars(param.ACI_POLICY_NO.length-6)+param.ACI_POLICY_NO.substring(param.ACI_POLICY_NO.length-6));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	}
	LODOP.ADD_PRINT_TEXT(767,185,62,21,"收件人：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(767,241,155,21,param.ADDRESSEE);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.ADD_PRINT_TEXT(791,185,75,21,"预约时间：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(791,254,195,21,getDueTime(param));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.ADD_PRINT_TEXT(815,185,51,21,"区域：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(815,230,195,21,param.REGION_NAME);
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(839,185,51,21,"地址：");
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(839,230,480,42,getAddr(param.DELIVERY_ADSTREET));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	
	//LODOP.ADD_PRINT_TEXT(695,19,130,20,param.ORDER_NUM+"\r\n");
	//LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	//LODOP.SET_PRINT_STYLEA(0,"AlignJustify",1);
	//LODOP.SET_PRINT_STYLEA(0,"Horient",3);
	LODOP.ADD_PRINT_TEXT(695,17,130,20,param.ORDER_NUM);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",2);
	
	LODOP.ADD_PRINT_BARCODE(705,19,125,125,"QRCode",param.PAY_APPLY_NO);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	LODOP.SET_PRINT_STYLEA(0,"QRCodeVersion",3);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	var pos="";
	if(param.PAY_WAY&&param.PAY_WAY=="快钱"){
		pos="快钱";
	}else if(param.PAY_WAY&&param.PAY_WAY=="alipay"){
		pos="蚂蚁";
	}
	LODOP.ADD_PRINT_TEXT(830,17,120,22,pos+"支付号");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#000040");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.ADD_PRINT_TEXT(850,17,120,25,param.PAY_APPLY_NO.substring(param.PAY_APPLY_NO.length-10,param.PAY_APPLY_NO.length));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#400040");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	
	//if(param.PAY_FEE&&param.PAY_FEE!=""&&param.PAY_FEE!="0"){
	LODOP.ADD_PRINT_TEXT(743,561,190,30,"应收:"+(param.PAY_FEE&&param.PAY_FEE!=""?param.PAY_FEE:"0")+"元");
	LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	//}
	//LODOP.ADD_PRINT_HTM(867,19,730,20,"<p>------------------------------------------------------------------------------------------------------------------------</p>");
	//LODOP.ADD_PRINT_LINE(864,163,695,164,0,1);
	LODOP.ADD_PRINT_LINE(895,19,896,739,2,0);
	LODOP.ADD_PRINT_TEXT(905,19,732,60,"温馨提示：\r\n投保交强险请验收：①交强险保单正本、副本 ②交强险标志 ③交强险发票（电子发票）。\r\n投保商业险请验收：①神行车保系列产品保险单 ②商业险发票（电子发票）。\r\n电子发票：可通过短信、邮箱或太保官网进行查询及下载。 快递服务监督电话:66779900-2515");
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#5C5C5C");
	LODOP.SET_PRINT_STYLEA(0,"LineSpacing",2);
	if(param.PRESENT_CODE&&param.PRESENT_CODE!=""){
		LODOP.ADD_PRINT_TEXT(984,19,53,18,"备注：");
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
		LODOP.ADD_PRINT_TEXT(984,72,680,43,param.PRESENT_CODE);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
		
	}else{
		if(param.GIFT_LIST&&param.GIFT_LIST!=""){
			LODOP.ADD_PRINT_TEXT(984,19,53,18,"备注：");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
			LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
			LODOP.ADD_PRINT_TEXT(984,72,680,43,param.GIFT_LIST);
			LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
		}
	} 
	LODOP.ADD_PRINT_TEXT(1030,19,96,25,"配送公司：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.ADD_PRINT_TEXT(1030,104,92,23,param.EXPRESS_COMPANY_NAME_SUM);
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
	LODOP.ADD_PRINT_TEXT(1030,300,110,29,"收件人签名：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.ADD_PRINT_IMAGE(584,19,48,48,"<img src='../images/cpic.png'>");
	//LODOP.ADD_PRINT_IMAGE(589,692,56,55,"<img src='../images/A.png'>");
	LODOP.ADD_PRINT_TEXT(589,692,43,40,"A");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",26);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	if(param.ORDER_TYPE=='415'){
		if(param.CREATE_TIME&& typeof param.CREATE_TIME =="object"&&param.CREATE_TIME.getDate()){
			LODOP.ADD_PRINT_TEXT(695,693,52,21,param.CREATE_TIME.getDate()+"号");
		}
	}else{
		LODOP.ADD_PRINT_TEXT(695,693,52,21,param.PAY_APPLY_NO.substring(param.PAY_APPLY_NO.length-8,param.PAY_APPLY_NO.length-6)+"号");
	}
	
	LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.ADD_PRINT_TEXT(1072,19,64,20,"录单工号：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
	LODOP.ADD_PRINT_TEXT(1071,80,100,20,param.SEAT_NO);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.ADD_PRINT_TEXT(1072,467,53,20,"经办人：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
	LODOP.ADD_PRINT_TEXT(1071,518,65,20,param.AGENTER);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	
	LODOP.ADD_PRINT_TEXT(1072,578,55,25,"打印时间：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.ADD_PRINT_TEXT(1072,632,119,25,new Date().pattern("yyyy-MM-dd hh:mm:ss")+"\r\n");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"Horient",1);



	}
	
function designC(param){


//LODOP.PRINT_INITA(0,1,793,1123,"");
//LODOP.SET_PRINT_MODE("PRINT_NOCOLLATE",1);
//LODOP.ADD_PRINT_TEXT(65,235,127,37,"太平洋电销");
//LODOP.SET_PRINT_STYLEA(0,"FontSize",17);
//LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
LODOP.ADD_PRINT_TEXT(26,68,150,54,"太平洋保险\r\nCPIC");
LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
LODOP.SET_PRINT_STYLEA(0,"Bold",1);
LODOP.ADD_PRINT_TEXT(65,295,180,36,"发票配送单");
LODOP.SET_PRINT_STYLEA(0,"FontSize",17);
LODOP.SET_PRINT_STYLEA(0,"Alignment",2);

LODOP.ADD_PRINT_TEXT(92,245,280,20,"（为保护个人隐私，部分信息打码隐藏）");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.SET_PRINT_STYLEA(0,"FontColor","#808080");
LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
LODOP.ADD_PRINT_TEXT(165,571,160,50,param.INVOICE_TYPE?param.INVOICE_TYPE.replace("专票","专票两联").replace("电子发票","电票"):"");
LODOP.SET_PRINT_STYLEA(0,"FontSize",22);
LODOP.SET_PRINT_STYLEA(0,"Alignment",3);

LODOP.ADD_PRINT_TEXT(135,185,62,21,"订单号：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(135,241,155,21,param.ORDER_NUM);
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
//LODOP.ADD_PRINT_TEXT(135,185,62,21,"车牌号：");
//LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
//LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
//LODOP.ADD_PRINT_TEXT(135,241,155,21,param.VEHICLE_LICENSE.substring(0,1)+getStars(param.VEHICLE_LICENSE.length-4)+param.VEHICLE_LICENSE.substring(param.VEHICLE_LICENSE.length-3)+"\r\n");
//LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
//LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.ADD_PRINT_TEXT(159,185,100,21,"交强险保单号：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);

if(param.TCP_POLICY_NO&&param.TCP_POLICY_NO!=""){
LODOP.ADD_PRINT_TEXT(159,279,180,21,getStars(param.TCP_POLICY_NO.length-6)+param.TCP_POLICY_NO.substring(param.TCP_POLICY_NO.length-6));
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
}
LODOP.ADD_PRINT_TEXT(183,185,100,21,"商业险保单号：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);


if(param.ACI_POLICY_NO&&param.ACI_POLICY_NO!=""){
LODOP.ADD_PRINT_TEXT(183,279,180,21,getStars(param.ACI_POLICY_NO.length-6)+param.ACI_POLICY_NO.substring(param.ACI_POLICY_NO.length-6));
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
}

//LODOP.ADD_PRINT_TEXT(159,185,62,21,"保单号：");
//LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
//LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
//if(param.ACI_POLICY_NO&&param.ACI_POLICY_NO!=""){
//LODOP.ADD_PRINT_TEXT(159,241,180,21,getStars(param.ACI_POLICY_NO.length-6)+param.ACI_POLICY_NO.substring(param.ACI_POLICY_NO.length-6));
//LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
//}
LODOP.ADD_PRINT_TEXT(207,185,62,21,"收件人：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(207,241,154,21,param.ADDRESSEE);
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.SET_PRINT_STYLEA(0,"Bold",1);
LODOP.ADD_PRINT_TEXT(231,185,75,21,"预约时间：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(231,254,195,21,getDueTime(param));
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.ADD_PRINT_TEXT(255,185,51,21,"区域：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(255,230,195,21,param.REGION_NAME);
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.SET_PRINT_STYLEA(0,"Bold",1);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(279,185,51,21,"地址：");
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(279,230,480,63,getAddr(param.DELIVERY_ADSTREET));
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
//LODOP.ADD_PRINT_BARCODE(135,566,216,49,"Code39",param.ORDER_NUM);
//LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
//LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_BARCODE(135,19,125,125,"QRCode",param.ORDER_NUM);
LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
LODOP.SET_PRINT_STYLEA(0,"QRCodeVersion",3);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(269,24,127,32,"     "+param.VEHICLE_LICENSE.substring(0,1)+getStars(param.VEHICLE_LICENSE.length-4)+param.VEHICLE_LICENSE.substring(param.VEHICLE_LICENSE.length-3));
LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
//LODOP.ADD_PRINT_HTM(307,19,730,20,"<p>------------------------------------------------------------------------------------------------------------------------</p>");
//LODOP.ADD_PRINT_LINE(304,163,135,164,0,1);
LODOP.ADD_PRINT_LINE(335,19,336,739,2,0);
LODOP.ADD_PRINT_TEXT(345,19,732,60,"温馨提示：\r\n增值税专用发票请验收：两联①抵扣联 ②发票联\r\n增值税普通发票请验收：一联①发票联\r\n快递服务监督电话:66779900-2515");
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
LODOP.SET_PRINT_STYLEA(0,"FontColor","#5C5C5C");
LODOP.SET_PRINT_STYLEA(0,"LineSpacing",2);
LODOP.ADD_PRINT_TEXT(441,19,96,25,"配送公司：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.ADD_PRINT_TEXT(441,107,92,23,param.EXPRESS_COMPANY_NAME_SUM);
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
LODOP.ADD_PRINT_TEXT(441,296,110,29,"收件人签名：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.ADD_PRINT_IMAGE(24,19,48,48,"<img src='../images/cpic.png'>");
//LODOP.ADD_PRINT_IMAGE(29,692,56,55,"<img src='../images/C.png'>");
LODOP.ADD_PRINT_TEXT(29,692,43,40,"C");
LODOP.SET_PRINT_STYLEA(0,"FontSize",26);
LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
LODOP.ADD_PRINT_TEXT(15,550,120,21,param.index);
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.ADD_PRINT_TEXT(486,19,64,20,"录单工号：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.ADD_PRINT_TEXT(486,80,100,20,param.SEAT_NO);
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.ADD_PRINT_TEXT(486,467,53,20,"经办人：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.ADD_PRINT_TEXT(485,518,65,20,param.AGENTER);
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);

LODOP.ADD_PRINT_TEXT(486,578,55,25,"打印时间：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
LODOP.ADD_PRINT_TEXT(486,632,119,25,new Date().pattern("yyyy-MM-dd hh:mm:ss"));
LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
LODOP.SET_PRINT_STYLEA(0,"Horient",1);
	LODOP.ADD_PRINT_LINE(585,19,586,739,1,0);
	LODOP.SET_PRINT_STYLEA(0,"Horient",2);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",2);
	
	
	
	//LODOP.ADD_PRINT_IMAGE(525,717,44,20,"<img src='../images/scissors.png'>");
	//LODOP.ADD_PRINT_TEXT(625,235,127,37,"太平洋电销");
	//LODOP.SET_PRINT_STYLEA(0,"FontSize",17);
	//LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(590,68,150,54,"太平洋保险\r\nCPIC");
	LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.ADD_PRINT_TEXT(625,295,180,36,"发票配送单");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",17);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	
	LODOP.ADD_PRINT_TEXT(652,245,280,20,"（为保护个人隐私，部分信息打码隐藏）");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#808080");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(725,571,160,50,param.INVOICE_TYPE?param.INVOICE_TYPE.replace("专票","专票两联").replace("电子发票","电票"):"");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",22);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	
	LODOP.ADD_PRINT_TEXT(695,185,62,21,"订单号：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(695,241,155,21,param.ORDER_NUM);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	//LODOP.ADD_PRINT_TEXT(695,185,62,21,"车牌号：");
	//LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	//LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	//LODOP.ADD_PRINT_TEXT(695,241,155,21,param.VEHICLE_LICENSE.substring(0,1)+getStars(param.VEHICLE_LICENSE.length-4)+param.VEHICLE_LICENSE.substring(param.VEHICLE_LICENSE.length-3)+"\r\n\r\n");
	//LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	//LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.ADD_PRINT_TEXT(719,185,100,22,"交强险保单号：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	if(param.TCP_POLICY_NO&&param.TCP_POLICY_NO!=""){
	LODOP.ADD_PRINT_TEXT(719,279,180,21,getStars(param.TCP_POLICY_NO.length-6)+param.TCP_POLICY_NO.substring(param.TCP_POLICY_NO.length-6));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	}
	LODOP.ADD_PRINT_TEXT(743,185,100,21,"商业险保单号：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	if(param.ACI_POLICY_NO&&param.ACI_POLICY_NO!=""){
	LODOP.ADD_PRINT_TEXT(743,279,180,21,getStars(param.ACI_POLICY_NO.length-6)+param.ACI_POLICY_NO.substring(param.ACI_POLICY_NO.length-6));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	}
	//LODOP.ADD_PRINT_TEXT(719,185,62,21,"保单号：");
	//LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	//LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	//if(param.ACI_POLICY_NO&&param.ACI_POLICY_NO!=""){
	//LODOP.ADD_PRINT_TEXT(719,241,180,21,getStars(param.ACI_POLICY_NO.length-6)+param.ACI_POLICY_NO.substring(param.ACI_POLICY_NO.length-6));
	//LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	//}
	LODOP.ADD_PRINT_TEXT(767,185,62,21,"收件人：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(767,241,154,21,param.ADDRESSEE);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.ADD_PRINT_TEXT(791,185,75,21,"预约时间：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(791,254,195,21,getDueTime(param));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.ADD_PRINT_TEXT(815,185,51,21,"区域：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(815,230,195,21,param.REGION_NAME);
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(839,185,51,21,"地址：");
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(839,230,480,63,getAddr(param.DELIVERY_ADSTREET)+"\r\n");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	//LODOP.ADD_PRINT_BARCODE(695,566,216,49,"Code39",param.ORDER_NUM);
	//LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
	//LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_BARCODE(695,19,125,125,"QRCode",param.ORDER_NUM);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	LODOP.SET_PRINT_STYLEA(0,"QRCodeVersion",3);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(829,24,127,32,"     "+param.VEHICLE_LICENSE.substring(0,1)+getStars(param.VEHICLE_LICENSE.length-4)+param.VEHICLE_LICENSE.substring(param.VEHICLE_LICENSE.length-3));
	LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	//LODOP.ADD_PRINT_HTM(867,19,730,20,"<p>------------------------------------------------------------------------------------------------------------------------</p>");
	//LODOP.ADD_PRINT_LINE(864,163,695,164,0,1);
	LODOP.ADD_PRINT_LINE(895,19,896,739,2,0);
	LODOP.ADD_PRINT_TEXT(905,19,732,84,"温馨提示：\r\n增值税专用发票请验收：两联①抵扣联 ②发票联\r\n增值税普通发票请验收：一联①发票联\r\n快递服务监督电话:66779900-2515");
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#5C5C5C");
	LODOP.SET_PRINT_STYLEA(0,"LineSpacing",2);
	LODOP.ADD_PRINT_TEXT(1001,19,96,25,"配送公司：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.ADD_PRINT_TEXT(1001,107,92,23,param.EXPRESS_COMPANY_NAME_SUM);
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
	LODOP.ADD_PRINT_TEXT(1001,296,110,29,"收件人签名：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.ADD_PRINT_IMAGE(584,19,48,48,"<img src='../images/cpic.png'>");
	//LODOP.ADD_PRINT_IMAGE(589,692,56,55,"<img src='../images/C.png'>");
	
	LODOP.ADD_PRINT_TEXT(589,692,43,40,"C");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",26);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(1046,19,64,20,"录单工号：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
	LODOP.ADD_PRINT_TEXT(1045,80,100,20,param.SEAT_NO);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.ADD_PRINT_TEXT(1046,467,53,20,"经办人：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
	LODOP.ADD_PRINT_TEXT(1045,518,65,20,param.AGENTER);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	
	LODOP.ADD_PRINT_TEXT(1046,578,55,25,"打印时间：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.ADD_PRINT_TEXT(1046,632,119,25,new Date().pattern("yyyy-MM-dd hh:mm:ss"));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"Horient",1);     
	}
	
function designD(param){


//LODOP.PRINT_INITA(0,1,793,1123,"");
//LODOP.SET_PRINT_MODE("PRINT_NOCOLLATE",1);
//LODOP.ADD_PRINT_TEXT(65,235,127,37,"太平洋电销");
//LODOP.SET_PRINT_STYLEA(0,"FontSize",17);
//LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
LODOP.ADD_PRINT_TEXT(26,68,150,54,"太平洋保险\r\nCPIC");
LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
LODOP.SET_PRINT_STYLEA(0,"Bold",1);
LODOP.ADD_PRINT_TEXT(65,295,180,36,"加急配送单");
LODOP.SET_PRINT_STYLEA(0,"FontSize",17);
LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
LODOP.ADD_PRINT_TEXT(92,245,280,20,"（为保护个人隐私，部分信息打码隐藏）");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.SET_PRINT_STYLEA(0,"FontColor","#808080");
LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
LODOP.ADD_PRINT_TEXT(135,185,62,21,"订单号：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(135,241,155,21,param.ORDER_NUM);
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);

LODOP.ADD_PRINT_TEXT(159,185,62,21,"收件人：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(159,241,155,21,param.ADDRESSEE);
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.SET_PRINT_STYLEA(0,"Bold",1);

LODOP.ADD_PRINT_TEXT(183,185,75,21,"预约时间：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(183,254,195,21,getDueTime(param));
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.ADD_PRINT_TEXT(207,185,51,21,"区域：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(207,230,195,21,param.REGION_NAME);
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.SET_PRINT_STYLEA(0,"Bold",1);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(231,185,51,21,"地址：");
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(231,230,480,63,getAddr(param.DELIVERY_ADSTREET));
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
//LODOP.ADD_PRINT_BARCODE(135,566,216,49,"Code39",param.ORDER_NUM);
//LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
//LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_BARCODE(135,19,125,125,"QRCode",param.ORDER_NUM);
LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
LODOP.SET_PRINT_STYLEA(0,"QRCodeVersion",3);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(269,24,127,32,"     "+param.VEHICLE_LICENSE.substring(0,1)+getStars(param.VEHICLE_LICENSE.length-4)+param.VEHICLE_LICENSE.substring(param.VEHICLE_LICENSE.length-3));
LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
//if(param.PAY_FEE&&param.PAY_FEE!=""&&param.PAY_FEE!="0"){
LODOP.ADD_PRINT_TEXT(183,561,190,30,"应收:"+(param.PAY_FEE&&param.PAY_FEE!=""?param.PAY_FEE:"0")+"元");
LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
//}
//LODOP.ADD_PRINT_HTM(307,19,730,20,"<p>------------------------------------------------------------------------------------------------------------------------</p>");
//LODOP.ADD_PRINT_LINE(304,163,135,164,0,1);
LODOP.ADD_PRINT_LINE(335,19,336,739,2,0);
LODOP.ADD_PRINT_TEXT(345,19,732,60,"温馨提示：\r\n您有任何问题，欢迎拨打快递服务监督电话:66779900-2515");
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
LODOP.SET_PRINT_STYLEA(0,"FontColor","#5C5C5C");
LODOP.SET_PRINT_STYLEA(0,"LineSpacing",2);
LODOP.ADD_PRINT_TEXT(441,19,96,25,"配送公司：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.ADD_PRINT_TEXT(441,107,92,23,param.EXPRESS_COMPANY_NAME_SUM);
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
LODOP.ADD_PRINT_TEXT(441,296,110,29,"收件人签名：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.ADD_PRINT_IMAGE(24,19,48,48,"<img src='../images/cpic.png'>");
//LODOP.ADD_PRINT_IMAGE(29,692,56,55,"<img src='../images/D.png'>");
LODOP.ADD_PRINT_TEXT(29,692,43,40,"D");
LODOP.SET_PRINT_STYLEA(0,"FontSize",26);
LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
LODOP.ADD_PRINT_TEXT(15,550,120,21,param.index);
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.ADD_PRINT_TEXT(486,19,64,20,"录单工号：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.ADD_PRINT_TEXT(485,80,100,20,param.SEAT_NO);
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.ADD_PRINT_TEXT(486,467,53,20,"经办人：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.ADD_PRINT_TEXT(485,518,65,20,param.AGENTER);
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);

LODOP.ADD_PRINT_TEXT(486,578,55,25,"打印时间：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
LODOP.ADD_PRINT_TEXT(486,632,119,25,new Date().pattern("yyyy-MM-dd hh:mm:ss"));
LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
LODOP.SET_PRINT_STYLEA(0,"Horient",1);
	LODOP.ADD_PRINT_LINE(585,19,586,739,1,0);
	LODOP.SET_PRINT_STYLEA(0,"Horient",2);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",2);
	
	
	
	//LODOP.ADD_PRINT_IMAGE(525,717,44,20,"<img src='../images/scissors.png'>");
	//LODOP.ADD_PRINT_TEXT(625,235,127,37,"太平洋电销");
	//LODOP.SET_PRINT_STYLEA(0,"FontSize",17);
	//LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(590,68,150,54,"太平洋保险\r\nCPIC");
	LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.ADD_PRINT_TEXT(625,295,180,36,"加急配送单");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",17);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(652,245,280,20,"（为保护个人隐私，部分信息打码隐藏）");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#808080");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(695,185,62,21,"订单号：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(695,241,155,21,param.ORDER_NUM);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	
	LODOP.ADD_PRINT_TEXT(719,185,62,21,"收件人：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(719,241,154,21,param.ADDRESSEE);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	
	LODOP.ADD_PRINT_TEXT(743,185,75,21,"预约时间：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(743,254,195,21,getDueTime(param));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.ADD_PRINT_TEXT(767,185,51,21,"区域：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(767,230,195,21,param.REGION_NAME);
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(791,185,51,21,"地址：");
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(791,230,480,63,getAddr(param.DELIVERY_ADSTREET)+"\r\n");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	//LODOP.ADD_PRINT_BARCODE(695,566,216,49,"Code39",param.ORDER_NUM);
	//LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
	//LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_BARCODE(695,19,125,125,"QRCode",param.ORDER_NUM);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	LODOP.SET_PRINT_STYLEA(0,"QRCodeVersion",3);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(829,24,127,32,"     "+param.VEHICLE_LICENSE.substring(0,1)+getStars(param.VEHICLE_LICENSE.length-4)+param.VEHICLE_LICENSE.substring(param.VEHICLE_LICENSE.length-3));
	LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	//if(param.PAY_FEE&&param.PAY_FEE!=""&&param.PAY_FEE!="0"){
	LODOP.ADD_PRINT_TEXT(743,561,190,30,"应收:"+(param.PAY_FEE&&param.PAY_FEE!=""?param.PAY_FEE:"0")+"元");
	LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	//}
	//LODOP.ADD_PRINT_HTM(867,19,730,20,"<p>------------------------------------------------------------------------------------------------------------------------</p>");
	//LODOP.ADD_PRINT_LINE(864,165,695,166,0,1);
	LODOP.ADD_PRINT_LINE(895,19,896,739,2,0);
	LODOP.ADD_PRINT_TEXT(905,19,732,60,"温馨提示：\r\n您有任何问题，欢迎拨打快递服务监督电话:66779900-2515");
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#5C5C5C");
	LODOP.SET_PRINT_STYLEA(0,"LineSpacing",2);
	LODOP.ADD_PRINT_TEXT(1001,19,96,25,"配送公司：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.ADD_PRINT_TEXT(1001,107,92,23,param.EXPRESS_COMPANY_NAME_SUM);
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
	LODOP.ADD_PRINT_TEXT(1001,296,110,29,"收件人签名：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.ADD_PRINT_IMAGE(584,19,48,48,"<img src='../images/cpic.png'>");
	//LODOP.ADD_PRINT_IMAGE(589,692,56,55,"<img src='../images/D.png'>");
	
	LODOP.ADD_PRINT_TEXT(589,692,43,40,"D");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",26);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(1046,19,64,20,"录单工号：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
	LODOP.ADD_PRINT_TEXT(1045,80,100,20,param.SEAT_NO);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.ADD_PRINT_TEXT(1046,467,53,20,"经办人：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
	LODOP.ADD_PRINT_TEXT(1045,518,65,20,param.AGENTER);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	
	LODOP.ADD_PRINT_TEXT(1046,578,55,25,"打印时间：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.ADD_PRINT_TEXT(1046,632,119,25,new Date().pattern("yyyy-MM-dd hh:mm:ss"));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"Horient",1);  

	}
	
function designB(param){


//LODOP.PRINT_INITA(0,1,793,1123,"");
//LODOP.SET_PRINT_MODE("PRINT_NOCOLLATE",1);
//LODOP.ADD_PRINT_TEXT(65,215,127,37,"太平洋电销");
//LODOP.SET_PRINT_STYLEA(0,"FontSize",17);
//LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
LODOP.ADD_PRINT_TEXT(26,68,150,54,"太平洋保险\r\nCPIC");
LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
LODOP.SET_PRINT_STYLEA(0,"Bold",1);
LODOP.ADD_PRINT_TEXT(65,295,180,36,"非车险配送单");
LODOP.SET_PRINT_STYLEA(0,"FontSize",17);
LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
LODOP.ADD_PRINT_TEXT(92,245,280,20,"（为保护个人隐私，部分信息打码隐藏）");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.SET_PRINT_STYLEA(0,"FontColor","#808080");
LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
LODOP.ADD_PRINT_TEXT(135,185,62,21,"订单号：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(135,241,155,21,param.ORDER_NUM);
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);

//LODOP.ADD_PRINT_TEXT(135,185,62,21,"车牌号：");
//LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
//LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
//LODOP.ADD_PRINT_TEXT(135,241,155,21,param.VEHICLE_LICENSE.substring(0,1)+getStars(param.VEHICLE_LICENSE.length-4)+param.VEHICLE_LICENSE.substring(param.VEHICLE_LICENSE.length-3)+"\r\n");
//LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
//LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.ADD_PRINT_TEXT(159,185,62,21,"保单号：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
if(param.ACI_POLICY_NO&&param.ACI_POLICY_NO!=""){
LODOP.ADD_PRINT_TEXT(159,241,180,21,getStars(param.ACI_POLICY_NO.length-6)+param.ACI_POLICY_NO.substring(param.ACI_POLICY_NO.length-6));
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
}
LODOP.ADD_PRINT_TEXT(183,185,62,21,"收件人：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(183,241,155,21,param.ADDRESSEE);
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"Bold",1);

LODOP.ADD_PRINT_TEXT(207,185,75,21,"预约时间：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(207,254,195,21,getDueTime(param));
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.ADD_PRINT_TEXT(231,185,51,21,"区域：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(231,230,195,21,param.REGION_NAME);
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.SET_PRINT_STYLEA(0,"Bold",1);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(255,185,51,21,"地址：");
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(255,230,480,63,getAddr(param.DELIVERY_ADSTREET));
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
//LODOP.ADD_PRINT_BARCODE(135,566,216,49,"Code39",param.ORDER_NUM);
//LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
//LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_BARCODE(135,19,125,125,"QRCode",param.ORDER_NUM);
LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
LODOP.SET_PRINT_STYLEA(0,"QRCodeVersion",3);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(269,24,127,32,"     "+param.VEHICLE_LICENSE.substring(0,1)+getStars(param.VEHICLE_LICENSE.length-4)+param.VEHICLE_LICENSE.substring(param.VEHICLE_LICENSE.length-3));
LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
//if(param.PAY_FEE&&param.PAY_FEE!=""&&param.PAY_FEE!="0"){
LODOP.ADD_PRINT_TEXT(183,561,190,30,"应收:"+(param.PAY_FEE&&param.PAY_FEE!=""?param.PAY_FEE:"0")+"元");
LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
//}
//LODOP.ADD_PRINT_HTM(307,19,730,20,"<p>------------------------------------------------------------------------------------------------------------------------</p>");
//LODOP.ADD_PRINT_LINE(304,163,135,164,0,1);
LODOP.ADD_PRINT_LINE(335,19,336,739,2,0);
LODOP.ADD_PRINT_TEXT(345,19,732,60,"温馨提示：\r\n请验收：①保单正本 ②发票（电子发票）\r\n电子发票：可通过短信、邮箱或太保官网进行查询及下载。\r\n快递服务监督电话:66779900-2515");
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
LODOP.SET_PRINT_STYLEA(0,"FontColor","#5C5C5C");
LODOP.SET_PRINT_STYLEA(0,"LineSpacing",2);
LODOP.ADD_PRINT_TEXT(441,19,96,25,"配送公司：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.ADD_PRINT_TEXT(441,107,92,23,param.EXPRESS_COMPANY_NAME_SUM);
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
LODOP.ADD_PRINT_TEXT(441,296,110,29,"收件人签名：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.ADD_PRINT_IMAGE(24,19,48,48,"<img src='../images/cpic.png'>");
//LODOP.ADD_PRINT_IMAGE(29,692,56,55,"<img src='../images/B.png'>");
LODOP.ADD_PRINT_TEXT(29,692,43,40,"B");
LODOP.SET_PRINT_STYLEA(0,"FontSize",26);
LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
LODOP.ADD_PRINT_TEXT(15,550,120,21,param.index);
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.ADD_PRINT_TEXT(486,19,64,20,"录单工号：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.ADD_PRINT_TEXT(485,80,100,20,param.SEAT_NO);
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.ADD_PRINT_TEXT(486,467,53,20,"经办人：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.ADD_PRINT_TEXT(485,518,65,20,param.AGENTER);
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);

LODOP.ADD_PRINT_TEXT(486,578,55,25,"打印时间：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
LODOP.ADD_PRINT_TEXT(486,632,119,25,new Date().pattern("yyyy-MM-dd hh:mm:ss"));
LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
LODOP.SET_PRINT_STYLEA(0,"Horient",1);
	LODOP.ADD_PRINT_LINE(585,19,586,739,1,0);
	LODOP.SET_PRINT_STYLEA(0,"Horient",2);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",2);
	
	
	
	//LODOP.ADD_PRINT_IMAGE(525,717,44,20,"<img src='../images/scissors.png'>");
	//LODOP.ADD_PRINT_TEXT(625,215,127,37,"太平洋电销");
	//LODOP.SET_PRINT_STYLEA(0,"FontSize",17);
	//LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(590,68,150,54,"太平洋保险\r\nCPIC");
	LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.ADD_PRINT_TEXT(625,295,180,36,"非车险配送单");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",17);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(652,245,280,20,"（为保护个人隐私，部分信息打码隐藏）");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#808080");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(695,185,62,21,"订单号：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(695,241,155,21,param.ORDER_NUM);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	//LODOP.ADD_PRINT_TEXT(695,185,62,21,"车牌号：");
	//LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	//LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	//LODOP.ADD_PRINT_TEXT(695,241,155,21,param.VEHICLE_LICENSE.substring(0,1)+getStars(param.VEHICLE_LICENSE.length-4)+param.VEHICLE_LICENSE.substring(param.VEHICLE_LICENSE.length-3)+"\r\n\r\n");
	//LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	//LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.ADD_PRINT_TEXT(719,185,62,21,"保单号：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	if(param.ACI_POLICY_NO&&param.ACI_POLICY_NO!=""){
	LODOP.ADD_PRINT_TEXT(719,241,180,21,getStars(param.ACI_POLICY_NO.length-6)+param.ACI_POLICY_NO.substring(param.ACI_POLICY_NO.length-6));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	}
	LODOP.ADD_PRINT_TEXT(743,185,62,21,"收件人：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(743,241,154,21,param.ADDRESSEE);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.ADD_PRINT_TEXT(767,185,75,21,"预约时间：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(767,254,195,21,getDueTime(param));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.ADD_PRINT_TEXT(791,185,51,21,"区域：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(791,230,195,21,param.REGION_NAME);
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(815,185,51,21,"地址：");
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(815,230,480,63,getAddr(param.DELIVERY_ADSTREET)+"\r\n");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	//LODOP.ADD_PRINT_BARCODE(695,566,216,49,"Code39",param.ORDER_NUM);
	//LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
	//LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_BARCODE(695,19,125,125,"QRCode",param.ORDER_NUM);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	LODOP.SET_PRINT_STYLEA(0,"QRCodeVersion",3);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(829,24,127,32,"     "+param.VEHICLE_LICENSE.substring(0,1)+getStars(param.VEHICLE_LICENSE.length-4)+param.VEHICLE_LICENSE.substring(param.VEHICLE_LICENSE.length-3));
	LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	//if(param.PAY_FEE&&param.PAY_FEE!=""&&param.PAY_FEE!="0"){
	LODOP.ADD_PRINT_TEXT(743,561,190,30,"应收:"+(param.PAY_FEE&&param.PAY_FEE!=""?param.PAY_FEE:"0")+"元");
	LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	//}
	//LODOP.ADD_PRINT_HTM(867,19,730,20,"<p>------------------------------------------------------------------------------------------------------------------------</p>");
	//LODOP.ADD_PRINT_LINE(864,165,695,166,0,1);
	LODOP.ADD_PRINT_LINE(895,19,896,739,2,0);
	LODOP.ADD_PRINT_TEXT(905,19,732,60,"温馨提示：\r\n请验收：①保单正本 ②发票（电子发票）\r\n电子发票：可通过短信、邮箱或太保官网进行查询及下载。\r\n快递服务监督电话:66779900-2515");
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#5C5C5C");
	LODOP.SET_PRINT_STYLEA(0,"LineSpacing",2);
	
	LODOP.ADD_PRINT_TEXT(1001,19,96,25,"配送公司：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.ADD_PRINT_TEXT(1001,107,92,23,param.EXPRESS_COMPANY_NAME_SUM);
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
	LODOP.ADD_PRINT_TEXT(1001,296,110,29,"收件人签名：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.ADD_PRINT_IMAGE(584,19,48,48,"<img src='../images/cpic.png'>");
	//LODOP.ADD_PRINT_IMAGE(589,692,56,55,"<img src='../images/B.png'>");
	
	LODOP.ADD_PRINT_TEXT(589,692,43,40,"B");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",26);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(1046,19,64,20,"录单工号：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
	LODOP.ADD_PRINT_TEXT(1045,80,100,20,param.SEAT_NO);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.ADD_PRINT_TEXT(1046,467,53,20,"经办人：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
	LODOP.ADD_PRINT_TEXT(1045,518,65,20,param.AGENTER);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	
	LODOP.ADD_PRINT_TEXT(1046,578,55,25,"打印时间：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.ADD_PRINT_TEXT(1046,632,119,25,new Date().pattern("yyyy-MM-dd hh:mm:ss"));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"Horient",1);  

	}
	
function designE(param){


//LODOP.PRINT_INITA(0,1,793,1123,"");
//LODOP.SET_PRINT_MODE("PRINT_NOCOLLATE",1);
//LODOP.ADD_PRINT_TEXT(65,230,127,37,"太平洋电销");
//LODOP.SET_PRINT_STYLEA(0,"FontSize",17);
//LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
LODOP.ADD_PRINT_TEXT(26,68,150,54,"太平洋保险\r\nCPIC");
LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
LODOP.SET_PRINT_STYLEA(0,"Bold",1);
LODOP.ADD_PRINT_TEXT(65,295,180,36,"保费配送单");
LODOP.SET_PRINT_STYLEA(0,"FontSize",17);
LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
LODOP.ADD_PRINT_TEXT(92,245,280,20,"（为保护个人隐私，部分信息打码隐藏）");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.SET_PRINT_STYLEA(0,"FontColor","#808080");
LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
LODOP.ADD_PRINT_TEXT(135,185,62,21,"订单号：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(135,241,155,21,param.ORDER_NUM);
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);

LODOP.ADD_PRINT_TEXT(159,185,62,21,"收件人：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(159,241,155,21,param.ADDRESSEE);
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.SET_PRINT_STYLEA(0,"Bold",1);

LODOP.ADD_PRINT_TEXT(183,185,75,21,"预约时间：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(183,254,195,21,getDueTime(param));
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.ADD_PRINT_TEXT(207,185,51,21,"区域：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(207,230,195,21,param.REGION_NAME);
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.SET_PRINT_STYLEA(0,"Bold",1);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(231,185,51,21,"地址：");
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(231,230,480,63,getAddr(param.DELIVERY_ADSTREET));
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
//LODOP.ADD_PRINT_BARCODE(135,566,216,49,"Code39",param.ORDER_NUM);
//LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
//LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_BARCODE(135,19,125,125,"QRCode",param.ORDER_NUM);
LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
LODOP.SET_PRINT_STYLEA(0,"QRCodeVersion",3);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.ADD_PRINT_TEXT(269,24,127,32,"     "+param.VEHICLE_LICENSE.substring(0,1)+getStars(param.VEHICLE_LICENSE.length-4)+param.VEHICLE_LICENSE.substring(param.VEHICLE_LICENSE.length-3));
LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
//if(param.PAY_FEE&&param.PAY_FEE!=""&&param.PAY_FEE!="0"){
LODOP.ADD_PRINT_TEXT(183,561,190,30,"应收:"+(param.PAY_FEE&&param.PAY_FEE!=""?param.PAY_FEE:"0")+"元");
LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
//}
//LODOP.ADD_PRINT_HTM(307,19,730,20,"<p>------------------------------------------------------------------------------------------------------------------------</p>");
//LODOP.ADD_PRINT_LINE(304,163,135,164,0,1);
LODOP.ADD_PRINT_LINE(335,19,336,739,2,0);
LODOP.ADD_PRINT_TEXT(345,19,732,60,"温馨提示：\r\n您有任何问题，欢迎拨打电销专线：10108...(省略)");
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
LODOP.SET_PRINT_STYLEA(0,"FontColor","#5C5C5C");
LODOP.SET_PRINT_STYLEA(0,"LineSpacing",2);
LODOP.ADD_PRINT_TEXT(441,19,96,25,"配送公司：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.ADD_PRINT_TEXT(441,107,92,23,param.EXPRESS_COMPANY_NAME_SUM);
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
LODOP.ADD_PRINT_TEXT(441,296,110,29,"收件人签名：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
LODOP.ADD_PRINT_IMAGE(24,19,48,48,"<img src='../images/cpic.png'>");
//LODOP.ADD_PRINT_IMAGE(29,692,56,55,"<img src='../images/E.png'>");
LODOP.ADD_PRINT_TEXT(29,692,43,40,"E");
LODOP.SET_PRINT_STYLEA(0,"FontSize",26);
LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
LODOP.ADD_PRINT_TEXT(15,550,120,21,param.index);
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.ADD_PRINT_TEXT(486,19,64,20,"录单工号：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.ADD_PRINT_TEXT(485,80,100,20,param.SEAT_NO);
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.ADD_PRINT_TEXT(486,467,53,20,"经办人：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.ADD_PRINT_TEXT(485,518,65,20,param.AGENTER);
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);

LODOP.ADD_PRINT_TEXT(486,578,55,25,"打印时间：");
LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
LODOP.ADD_PRINT_TEXT(486,632,119,25,new Date().pattern("yyyy-MM-dd hh:mm:ss"));
LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
LODOP.SET_PRINT_STYLEA(0,"Horient",1);
	LODOP.ADD_PRINT_LINE(585,19,586,739,1,0);
	LODOP.SET_PRINT_STYLEA(0,"Horient",2);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",2);
	
	
	
	//LODOP.ADD_PRINT_IMAGE(525,717,44,20,"<img src='../images/scissors.png'>");
	//LODOP.ADD_PRINT_TEXT(625,230,127,37,"太平洋电销");
	//LODOP.SET_PRINT_STYLEA(0,"FontSize",17);
	//LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(590,68,150,54,"太平洋保险\r\nCPIC");
	LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.ADD_PRINT_TEXT(625,295,180,36,"保费配送单");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",17);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(652,245,280,20,"（为保护个人隐私，部分信息打码隐藏）");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#808080");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(695,185,62,21,"订单号：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(695,241,155,21,param.ORDER_NUM);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	
	LODOP.ADD_PRINT_TEXT(719,185,62,21,"收件人：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(719,241,154,21,param.ADDRESSEE);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	
	LODOP.ADD_PRINT_TEXT(743,185,75,21,"预约时间：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(743,254,195,21,getDueTime(param));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.ADD_PRINT_TEXT(767,185,51,21,"区域：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(767,230,195,21,param.REGION_NAME);
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(791,185,51,21,"地址：");
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(791,230,480,63,getAddr(param.DELIVERY_ADSTREET)+"\r\n");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	//LODOP.ADD_PRINT_BARCODE(695,566,216,49,"Code39",param.ORDER_NUM);
	//LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
	//LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_BARCODE(695,19,125,125,"QRCode",param.ORDER_NUM);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	LODOP.SET_PRINT_STYLEA(0,"QRCodeVersion",3);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	LODOP.ADD_PRINT_TEXT(829,24,127,32,"     "+param.VEHICLE_LICENSE.substring(0,1)+getStars(param.VEHICLE_LICENSE.length-4)+param.VEHICLE_LICENSE.substring(param.VEHICLE_LICENSE.length-3));
	LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
	//if(param.PAY_FEE&&param.PAY_FEE!=""&&param.PAY_FEE!="0"){
	LODOP.ADD_PRINT_TEXT(743,561,190,30,"应收:"+(param.PAY_FEE&&param.PAY_FEE!=""?param.PAY_FEE:"0")+"元");
	LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	//}
	//LODOP.ADD_PRINT_HTM(867,19,730,20,"<p>------------------------------------------------------------------------------------------------------------------------</p>");
	//LODOP.ADD_PRINT_LINE(866,165,698,166,0,1);
	LODOP.ADD_PRINT_LINE(895,19,896,739,2,0);
	LODOP.ADD_PRINT_TEXT(905,21,732,60,"温馨提示：\r\n您有任何问题，欢迎拨打电销专线：10108...(省略)");
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#5C5C5C");
	LODOP.SET_PRINT_STYLEA(0,"LineSpacing",2);
	LODOP.ADD_PRINT_TEXT(1001,19,96,25,"配送公司：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.ADD_PRINT_TEXT(1001,107,92,23,param.EXPRESS_COMPANY_NAME_SUM);
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
	LODOP.ADD_PRINT_TEXT(1001,296,110,29,"收件人签名：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.ADD_PRINT_IMAGE(584,19,48,48,"<img src='../images/cpic.png'>");
	//LODOP.ADD_PRINT_IMAGE(589,692,56,55,"<img src='../images/E.png'>");
	
	LODOP.ADD_PRINT_TEXT(589,692,43,40,"E");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",26);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(1046,19,64,20,"录单工号：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
	LODOP.ADD_PRINT_TEXT(1045,80,100,20,param.SEAT_NO);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.ADD_PRINT_TEXT(1046,467,53,20,"经办人：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
	LODOP.ADD_PRINT_TEXT(1045,518,65,20,param.AGENTER);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	
	LODOP.ADD_PRINT_TEXT(1046,578,55,25,"打印时间：");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.ADD_PRINT_TEXT(1046,632,119,25,new Date().pattern("yyyy-MM-dd hh:mm:ss"));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"Horient",1);  

	}