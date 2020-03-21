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

function getDate(param){
	var date="";
	if(param.ORDER_TYPE=='415'){
		
		if(param.CREATE_TIME&& typeof param.CREATE_TIME =="object"&&param.CREATE_TIME.getDate()){
			date=param.CREATE_TIME.getDate()+"号";
		}
	}else{
		date=param.PAY_APPLY_NO.substring(param.PAY_APPLY_NO.length-8,param.PAY_APPLY_NO.length-6)+"号";
	}
	return date;
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

function designRo(param){
	
	LODOP.NewPage();
    // for(var i=0;i<2;i++){
        designRowsRo(param);
    // }

//	LODOP.PRINT_DESIGN();
}

function designRowsRo(rows){
	if(!LODOP) LODOP=getLodop(); 
	if(!LODOP) return;
	LODOP.PRINT_INIT("print1");
    LODOP.SET_PRINT_COPIES(1);
	for(var i=0;i<rows.length;i++){
    	//rows[i].index="页码："+(i+1)+"/"+rows.length;
    	//designA5(rows[i]);
    	designbj(rows[i]);
    }
	//LODOP.SET_PRINT_PAGESIZE(2,1480,2100,'');
	LODOP.SET_PRINT_PAGESIZE(2,800,500,'');
	// LODOP.ADD_PRINT_HTM();
    LODOP.PRINT();
}
function designbj(param){
LODOP.PRINT_INITA(0,1,793,1123,"");
LODOP.SET_PRINT_MODE("PRINT_NOCOLLATE",1);
LODOP.ADD_PRINT_RECT(7,8,165,280,0,1);

LODOP.ADD_PRINT_SHAPE(4,48,46,88,20,0,1,"#000000");
LODOP.ADD_PRINT_TEXT(54,46,88,20,param.VEHICLE_LICENSE);
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
LODOP.SET_PRINT_STYLEA(0,"FontColor","#FFFFFF");
LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
LODOP.SET_PRINT_STYLEA(0,"Bold",1);
LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",1);
LODOP.ADD_PRINT_IMAGE(14,14,150,25,"<img src='../../images/ro.png'>");
LODOP.SET_PRINT_STYLEA(0,"Stretch",2);
LODOP.ADD_PRINT_LINE(43,8,43,174,0,1);
LODOP.ADD_PRINT_TEXT(83,14,60,18,"工单号：");
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",1);
LODOP.ADD_PRINT_TEXT(83,64,105,18,param.RO_NUMBER);
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",1);
LODOP.ADD_PRINT_TEXT(106,14,70,18,"油漆面数：");
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",1);
LODOP.ADD_PRINT_TEXT(106,79,90,18,param.SHEET_METAL_COUNT);
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.ADD_PRINT_TEXT(129,14,70,18,"车身颜色：");
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",1);
LODOP.ADD_PRINT_TEXT(129,79,90,18,param.COLOR_NAME);
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",1);
LODOP.ADD_PRINT_TEXT(152,14,70,18,"色母消耗：");
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",1);
LODOP.ADD_PRINT_TEXT(152,79,90,18,param.WEIGHT_TOTAL);
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",1);
LODOP.ADD_PRINT_TEXT(175,14,45,18,"技师：");
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",1);
LODOP.ADD_PRINT_TEXT(175,54,115,18,param.MECHANIC_NAME);
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",1);
LODOP.ADD_PRINT_TEXT(198,14,70,18,"调漆技师：");
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",1);
LODOP.ADD_PRINT_TEXT(198,79,90,18,param.PAINT_MAKER);
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
LODOP.SET_PRINT_STYLEA(0,"ReadOnly",0);
LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",1);
LODOP.ADD_PRINT_TEXT(273,12,158,14,"打印时间："+new Date().pattern("yyyy-MM-dd hh:mm:ss"));
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",1);
LODOP.ADD_PRINT_LINE(263,11,263,170,2,0);
LODOP.ADD_PRINT_TEXT(245,14,85,18,"签字：");
LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
LODOP.SET_PRINT_STYLEA(0,"FontSize",8);

}
