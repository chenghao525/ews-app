//通过控件名字得到object
function MM_findObj_(n, d){
	var p,i,x;

	if(!d) d=document;

	if((p=n.indexOf("?"))>0&&parent.frames.length){
		d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);
	}
	if( !(x=d[n]) && d.all ) x=d.all[n];

	for (i=0;!x&&i<d.forms.length;i++)	x=d.forms[i][n];

	for(i=0;!x&&d.layers&&i<d.layers.length;i++)
		x=MM_findObj_(n,d.layers[i].document);

	return x;
}

function getObject(name){
	return MM_findObj_(name);
}

function setValue(name,value){
	var v=setValueByJq(name,value);
	if(v==null || v==""){
		object = MM_findObj_(name);
		if(object){ object.value=value;}
	}
}

function setValueByJq(name,value){
	$("input[name='"+name+"']").val(value);
	return getValueByJq(name);
	
}

function getRadioValue(RadioName){
	var obj;    
	obj=document.getElementsByName(RadioName);
	if(obj!=null){
		var i;
		for(i=0;i<obj.length;i++){
			if(obj[i].checked){return obj[i].value;}
		}
	}
	return "";
}

function changeTwoDecimal(x){
    var f_x = parseFloat(x);
    if (isNaN(f_x)){
		alert('function:changeTwoDecimal->parameter error');
		return false;
    }
    var f_x = Math.round(x*100)/100;
    return f_x;
}


function setFocus(obj){
	try{if(obj!=null) obj.select();}catch(e){};
	try{if(obj!=null) obj.focus();}catch(e){}
}

function getParent(el, pTagName) {
	if (el == null) return null;
	else if (el.nodeType == 1 && el.tagName.toLowerCase() == pTagName.toLowerCase())	// Gecko bug, supposed to be uppercase
		return el;
	else
		return getParent(el.parentNode, pTagName);
}

function getValue(name){
	var v=getValueByJq(name);
	if(v==null || v==""){
		object = MM_findObj_(name);
		if(object)  return object.value;
	}else return v;
}

function getValueByJq(name){
	return $("input[name='"+name+"']").val();
}

function strTrim(str){
	if(str==null) return null;
	else return str.replace(/^\s+|\s+$/g,"");
}

function nvl(str){
	if(str==null || str =="null" || str=="undefined"){return ""}else{return str;}
}

function nvlRep(str,rep){
	if(nvl(str)==""){return rep}else{return str;}
}

function subLength(str,len,repl){
	if(nvl(str)=="") return "";
	var l=str.length;
	if(l>len){
		return (str.substr(0,len)+nvlRep(repl,""));
	}else return str;
}

function getTrimValue(name){
	var tmp=getValue(name);
	tmp=strTrim(tmp);
	return tmp;
}

function ajaxRequest(url,para,func,async) {
	var ran = Math.random().toString();
	url+="&ran="+ran;
	var asc=true;
	if(nvl(async)=="false") asc=false;
	$.ajax({
		url: url,
		async: asc,
		dataType:"json", 
		data: {"data":para},
		type: "post",
		success:func,
		error: function (jqXHR, textStatus, errorThrown) {
			try{grid.unmask();}catch(e){}
			try{grid1.unmask();}catch(e){}
			try{grid2.unmask();}catch(e){}
			try{grid3.unmask();}catch(e){}
			var code=jqXHR.status;
			var info=jqXHR.responseText;
			//alert(mini);
			if(code==600){
				try{mini.alert("您没有操作权限");}catch(e){alert("您没有操作权限");}
			}else if(code==601){
				try{mini.alert("操作出现错误："+info);}catch(e){alert("操作出现错误："+info);}
			}else if(code!=0){
				if(info!=null&&info!=""){
					try{mini.alert(info+"("+code+")");}catch(e){alert(info+"("+code+")");}
				}else{
					try{mini.alert("其他错误("+code+")");}catch(e){alert("其他错误("+code+")");}
				}
			}
		}
	});

}

function ajaxRequestAPPWS(methodName,paras,func){
	$.ajax({ 
		contentType:"text/xml", 
		type: "POST",
		url: "/daappgate/GateService?wsdl",
		data: getPostData(methodName,paras), 
		dataType: "xml", 
		success: func, 
		error: function(x, e){ 
			alert("会话失效或网络中断");} 
		}); 
}

function getPostData(funcName,paras){ 
	var postdata="<?xml version=\"1.0\" encoding=\"utf-8\"?>"; 
	postdata +="<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\">";
	postdata +="<env:Header /><env:Body><call xmlns=\"http://webservice.corp.com/\">";
	postdata +="<arg0 xmlns=\"\">"+funcName+"</arg0>";
	postdata +="<arg1 xmlns=\"\">"+paras+"</arg1>";
	postdata +="</call></env:Body></env:Envelope>";
	return postdata; 
} 

/** 
* json对象转字符串形式 
*/ 
function json2str(o) { 
	var arr = []; 
	var fmt = function(s) { 
		if (typeof s == 'object' && s != null) return json2str(s); 
		return /^(string|number)$/.test(typeof s) ? "'" + s + "'" : s; 
	} 
	for (var i in o) arr.push("'" + i + "':" + fmt(o[i])); 
	return '{' + arr.join(',') + '}'; 
} 

function extend(destination, source) {
    for (var property in source)
      destination[property] = source[property];
    return destination;
}

function findDataExport(action){
	var data=$("form").serialize();

	var form = $("<form>");
	form.attr('style','display:none');
	form.attr('target','');
	form.attr('method','post');
	form.attr('name','myForm');
	form.attr('action',action+'&'+data);

	var input1 = $('<input>');
	input1.attr('type','submit');
	input1.attr('name','button');
	input1.attr('value','value');

	$('body').append(form);
	form.append(input1);

	form.submit();
	form.remove();
}

function exportPageContent2Xsl(eleId){
	try{
		var curTbl = document.getElementById(eleId); 
		var oXL = new ActiveXObject("Excel.Application");
		var oWB = oXL.Workbooks.Add();
		var oSheet = oWB.ActiveSheet;
		var sel=document.body.createTextRange();
		sel.moveToElementText(curTbl);
		sel.select();
		sel.execCommand("Copy");
		oSheet.Paste();
		oXL.Visible = true;
	}catch(e){
		alert("无法启动Excel！\n\n如果您确定您的电脑中已经安装了Excel，请调整IE的安全级别。\n\n具体操作：\n\n" + "工具 → Internet选项 → 安全 → 自定义级别 → 对没有标记为安全的ActiveX进行初始化和脚本运行 → 启用/提示");
	}
}

function setCutPageButton(name,buttonName){
    object = MM_findObj_(name);
    if(object){
      object.innerHTML = '<input type=hidden name=' + buttonName + ' value=true>';
    }
}
function printPage() { print(document); }
function redirect(URLStr) { location = URLStr; }
function MM_goToURL() { //v3.0
  var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
  for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
}

//获得帮助系统代码
function setCodeValue( cname ){
	var myCodeValue = document.valueForm.codeValue.value;
	for( var i = 0 ;i < window.opener.document.forms.length; i ++){
		for( var j = 0 ; j < window.opener.document.forms[i].elements.length ; j++ ){
			if( window.opener.document.forms[i].elements[j].name == cname){

				window.opener.document.forms[i].elements[j].value = myCodeValue;
				if ( window.opener.document.forms[i].elements[j].value == '' )
					window.opener.document.forms[i].elements[j].value = '#';
				break;
			}
		}
	}
}
function ifdel(){
	return ( confirm('确认删除选择的项目？') );
}

// 更改页面的Ｅｎｔｅｒ顺序
function pf_ChangeFocus(){
   key = window.event.keyCode;
   if ( key==0xD && event.srcElement.tagName!='TEXTAREA') /*0xD*/{
	window.event.keyCode=9;
   }
}
function pf_return(form,element)
{
	document.forms[form].elements[element].focus();
	return false;
}
function switchoutlookBar(objid){
 object = MM_findObj_('menuIndex');
 if(object != null){
   object.value=objid;
 }
 rowlen=document.all.table_mk.rows.length;
 rowobj=document.all.table_mk.rows;
 //objid = 'outlooktitle'+objid;
 for(i=0;i<rowlen;i++)
 {
  colobj=rowobj(i).cells;
  collen=rowobj(i).cells.length;
  for(j=0;j<collen;j++) {
    if(colobj(j).id != ''){
      if(document.all("outlookdivin"+ colobj(j).id.substring(12)) != null){
      if(('outlooktitle'+objid) == colobj(j).id){
        colobj(j).className="content2";
        document.all("outlookdivin"+ colobj(j).id.substring(12)).style.display="";
      }else{
        document.all("outlookdivin"+ colobj(j).id.substring(12)).style.display="none";
        colobj(j).className="content0";
      }
      }
    }
  }
 }
}
// 选择这一类的所有 use in system/grant/rolepermission.jsp
function selectAll(selected){
  trNum = selected.parentElement.parentElement.rowIndex;
  rows = selected.parentElement.rowSpan;
  rowsobj = selected.parentElement.parentElement.parentElement.rows;
  //alert(trNum);
  //alert(rows);
  for(var i=trNum;i<trNum+rows;i++){
    //alert(i);
    rowobj = rowsobj(i);
    colsobj = rowobj.cells;
    for(var j=0;j<colsobj.length;j++){
      element = colsobj(j).children(0);
      //alert(element);
      if(element != null){
        if(element.type == 'checkbox'){
          element.checked = selected.checked;
        }
      }
    }
  }

}


//清除form里面的控件值
function clearAll(){
	var formId = '';
	var id;
	var args = clearAll.arguments;
        var prefix = '';
	for( var i = 0 ; i < document.forms.length ; i++ )
		formId = formId + i +',';
        if(args.length > 0) {
		prefix = args[0];
        }

	while( formId.indexOf(',') > 0 ){
		id = formId.substring(0,formId.indexOf(','));
		formId = formId.substring(formId.indexOf(',')+1);
		for( i = 0 ;i < document.forms[id].elements.length ; i ++ ){
			var name = document.forms[id].elements[i].name;
			//alert(name);
			//alert(prefix);
			//alert(name.indexOf(prefix));
			if(name!=null){
				if(name.indexOf(prefix) == 0){
					if( document.forms[id].elements[i].type=='text' && document.forms[id].elements[i].disabled==false )
						document.forms[id].elements[i].value = '';
					else if( document.forms[id].elements[i].type=='textarea' && document.forms[id].elements[i].disabled==false )
						document.forms[id].elements[i].value = '';
					else if(document.forms[id].elements[i].type=='select-one')
						document.forms[id].elements[i].value = document.forms[id].elements[i].options[0].value;
				}
			}
		}
	}
	if(typeof(_customSearchResetFunc) == "function") _customSearchResetFunc();
}


function flowShow(divid){ 
	var x,y

	x = (document.layers) ? e.pageX : document.body.scrollLeft+event.clientX;
	y = (document.layers) ? e.pageY : document.body.scrollTop+event.clientY;
	y=y+20;
	x=x-10;
	divid.style.posLeft=x;
	divid.style.posTop=y;

	divid.filters.revealTrans.apply(); 
	divid.style.visibility = "visible"; 
	divid.filters.revealTrans.play(); 
} 
function flowHide(divid){ 
	divid.filters.revealTrans.apply(); 
	divid.style.visibility = "hidden"; 
	divid.filters.revealTrans.play(); 
} 

<!-- 测试静态页面提交用 -->
function submitAction(btnObj){
	if(btnObj == null){
		return false;
	}
	var name = btnObj.name;
	var el = getParentAction(btnObj, "FORM");
	name = '..' + el.action + '@' + name + '=true';
	//alert(name);
	window.location.href=name;
	return false;
}

function getParentAction(el, pTagName) {
	if (el == null) return null;
	else if (el.nodeType == 1 && el.tagName.toLowerCase() == pTagName.toLowerCase())
		return el;
	else
		return getParent(el.parentNode, pTagName);
}

// 得到本对象所在的Form 
function getParentElement(el, pTagName) {
	if (el == null) return null;
	else if (el.nodeType == 1 && el.tagName.toLowerCase() == pTagName.toLowerCase())
			return el;
	else
			return getParentElement(el.parentNode, pTagName);
}

function getObjectValue(thisObj,objName){
	var form = getParentElement(thisObj,'FORM');
	if(form != null){
		//alert(form);
		var obj = form[objName];
		if(obj != null){
			//alert(obj.type);
			return obj.value;
		}
	}
}

var _bFirstFocus=true;
function firstElementFocus() { 
	if(!_bFirstFocus) return;
	try{
		var element;
		var temp;
		if(document.forms.length==0){
			for(var i=0;i<document.all.length;i++){
				element=document.all[i];
				temp=element.type+"";
				temp=temp.toLowerCase();
				if((temp == 'text' || temp == 'radio' || temp == 'checkbox' 
					|| temp == 'submit' || temp=='select'
					 || temp == 'password') && !element.disabled && !element.readOnly){
					if(temp == 'text' || temp == 'password')
						element.select();
					element.focus();
					return;
				}
			}
		}else{
			for(var i=0;i<document.forms.length;i++){
				for(var j=0;j<document.forms[i].elements.length;j++){
					element=document.forms[i].elements[j];
					temp=element.type+"";
					temp=temp.toLowerCase();
					if((temp == 'text' || temp == 'radio' || temp == 'checkbox' 
						|| temp == 'submit' || temp=='select'
						 || temp == 'password') && !element.disabled && !element.readOnly){
						if(temp == 'text' || temp == 'password')
							element.select();
						element.focus();
						return;
					}
				}
			}
		}
	}catch(e){}
}
setTimeout(firstElementFocus,300);


/**
* 隐藏一个<div id='strObj'>
*/
function hiddenObject(strObj){
  var obj = MM_findObj_(strObj);
  if(obj != null){
    obj.style.display = 'none';
    //alert('hidden['+strObj+']');
  }
}
function showObject(strObj){
  var obj = MM_findObj_(strObj);
  if(obj != null){
    obj.style.display = '';
  }
}


function sendSMS(phone){
	var w=window.open('/sms/sms.do?b__add=true&phone='+phone,'sms','width=650px,height=380px,menubar=0,toolbar=0,directories=0,location=0,status=0,scrollbars=1');	
	w.focus();
	return false;
}

function setTitlePath(path){
	var h='<TABLE align=left border=0 cellPadding=0 cellSpacing=0 width="100%" class="nprint" background="/images/top_pathbg1.jpg"><TR><TD height="28" valign="middle" width="70%" nowrap>&nbsp;<IMG SRC="/images/h_ar1.gif" WIDTH="20" HEIGHT="20" BORDER=0  align="absmiddle">';
	
	h+=path;

	h+='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[&nbsp;<font color="red"><b><span id="_time_">00:00:00</span></b></font>&nbsp;前刷新&nbsp;]</td></tr></TABLE>';

	document.getElementById('_title_path_').innerHTML=h;
	_startTimer();
}

var timer;
var nSeconds = 0;

function _refreshTime(nSeconds) {
	var hh = Math.floor(nSeconds/3600);
	if(hh < 10) hh = '0' + hh;
	var mm = Math.floor((nSeconds%3600)/60);
	if(mm < 10) mm = '0' + mm;
	var ss = Math.floor(nSeconds%60);
	if(ss < 10) ss = '0' + ss;
	document.getElementById('_time_').innerHTML = hh + ':' + mm + ':' + ss;
}

 //开始
function _startTimer(){
	_refreshTime(nSeconds++);
	timer = setInterval(function() {
		_refreshTime(nSeconds++);
	}, 1000);
}

function replaceAllSpace(str){
	if(str==null) return null;
	else return str.replace(/\s+/g,"");
}

function isMobiePhoneNumber(phone){
	var patrn = /(^0{0,1}1[3|4|5|8][0-9]{9}$)/; 
	return (patrn.exec(phone));
}

function guid() {
    return 'xxxxxxxx-xxxx-6xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
        return v.toString(16);
    });
}

/**
     * 时间格式化 返回格式化的时间
     * @param date {object}  可选参数，要格式化的data对象，没有则为当前时间
     * @param fomat {string} 格式化字符串，例如：'YYYY年MM月DD日 hh时mm分ss秒 星期' 'YYYY/MM/DD week' (中文为星期，英文为week)
     * @return {string} 返回格式化的字符串
     * 
     * 例子:
     * formatDate(new Date("january 01,2012"));
     * formatDate(new Date());
     * formatDate('YYYY年MM月DD日 hh时mm分ss秒 星期 YYYY-MM-DD week');
     * formatDate(new Date("january 01,2012"),'YYYY年MM月DD日 hh时mm分ss秒 星期 YYYY/MM/DD week');
     * 
     * 格式：   
     *    YYYY：4位年,如1993
　　 *　　YY：2位年,如93
　　 *　　MM：月份
　　 *　　DD：日期
　　 *　　hh：小时
　　 *　　mm：分钟
　　 *　　ss：秒钟
　　 *　　星期：星期，返回如 星期二
　　 *　　周：返回如 周二
　　 *　　week：英文星期全称，返回如 Saturday
　　 *　　www：三位英文星期，返回如 Sat
     */
function formatDate(date, format) {
	if (arguments.length < 2 && !date.getTime) {
		format = date;
		date = new Date();
	}
	typeof format != 'string' && (format = 'YYYY年MM月DD日 hh时mm分ss秒');
	var week = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', '日', '一', '二', '三', '四', '五', '六'];
	return format.replace(/YYYY|YY|MM|DD|hh|mm|ss|星期|周|www|week/g, function(a) {
		switch (a) {
		case "YYYY": return date.getFullYear();
		case "YY": return (date.getFullYear()+"").slice(2);

		case "MM": {if(((date.getMonth()+1)+"").length==1) return "0"+(date.getMonth()+1); else return (date.getMonth()+1);}
		case "DD":{if((date.getDate()+"").length==1) return "0"+date.getDate(); else return date.getDate();} 
		case "hh": {if((date.getHours()+"").length==1) return "0"+date.getHours(); else return date.getHours();}
		case "mm":{if((date.getMinutes()+"").length==1) return "0"+date.getMinutes(); else return date.getMinutes();}
		case "ss": {if((date.getSeconds()+"").length==1) return "0"+date.getSeconds(); else return date.getSeconds();}
		case "星期": return "星期" + week[date.getDay() + 7];
		case "周": return "周" +  week[date.getDay() + 7];
		case "week": return week[date.getDay()];
		case "www": return week[date.getDay()].slice(0,3);
		}
	});
}

function getCityArea(infotype,parent,ctrlName,defaultSel){
	var para='{"infotype":"'+infotype+'","parent":"'+parent+'"}';
	ajaxRequest("/AjaxService?method=GetDicList",para,
		function(r){
			$('select[name="'+ctrlName+'"]').empty();
			var data;
			try {data = eval(r);}catch (e){return ;}
			if (data==null || data.length == undefined || data.length < 1) {return;}
			
			for(var i=0;i<data.length;i++){
				$('select[name="'+ctrlName+'"]').append("<option value='"+data[i].SID+"'>"+data[i].CONTENT+"</option>");
			}
			$('select[name="'+ctrlName+'"]').val(defaultSel);
			$('select[name="'+ctrlName+'"]').change();
		}	
	);
}

function getCityAreaNoChange(infotype,parent,ctrlName,defaultSel){
	var para='{"infotype":"'+infotype+'","parent":"'+parent+'"}';
	ajaxRequest("/AjaxService?method=GetDicList",para,
		function(r){
			$('select[name="'+ctrlName+'"]').empty();
			var data;
			try {data = eval(r);}catch (e){return ;}
			if (data==null || data.length == undefined || data.length < 1) {return;}
			
			for(var i=0;i<data.length;i++){
				$('select[name="'+ctrlName+'"]').append("<option value='"+data[i].SID+"'>"+data[i].CONTENT+"</option>");  
			}
			$('select[name="'+ctrlName+'"]').val(defaultSel);
		}	
	);
}

function _tinyBoxShow(imgSrc){TINY.box.show({image:imgSrc,boxid:'frameless',animate:true,width:700,height:500});}

var _rptYDataFlag="@_@";
function _reportGridPrint(cont,xData,yData){
	var x=xData.replace(/'/g,'').split(",");
	var yList=yData.split(_rptYDataFlag);
	var html='<TABLE class="detail-table" cellpadding="3" cellspacing="0" align="center" style="margin: 0 auto;">';
	
	//打印x轴
	html+='<tr bgcolor="#e0e3e8" height=25>';
	for(var i=0;i<x.length;i++){
		html+='<td nowrap>'+x[i]+'</td>';
	}
	html+='</tr>';

	for(var i=0;i<yList.length;i++){
		//打印Y轴
		html+='<tr height=25>';
		var y=yList[i].split(",");
		for(var j=0;j<y.length;j++){
			html+='<td style="white-space: nowrap;">'+y[j]+'</td>';
		}
		html+='</tr>';
	}
	html+='</table>';
	$('#'+cont).html(html);
}

function _showLog(r){
	var data;
	try {data = eval(r);} catch (e) {return;}
	if (data.length == undefined || data.length < 1) {return;}

	var tab='<table class="detail-table" style="margin-left: 0em;margin-top: 0em;margin-bottom:0.5em;" cellpadding="3" cellspacing="3"><tr height="30" bgcolor="f7f7f7"><td>No.</td><td>行数</td><td>错误日志</td></tr>';
	for(var i=0;i<data.length;i++){tab+='<tr height="30"><td>'+(i+1)+'</td><td>'+data[i].item+'</td><td>'+data[i].log+'</td></tr>';}
	tab+='</table>';

	var html='<div style="padding-left:10px;"><div style="float:left;height:99%px;overflow:hidden;max-height:250px;overflow-y:scroll;border:0px solid #ccc;width:100%">'+tab+'</div></div>';

	mini.showMessageBox({width: 400,title: "导入日志",buttons: ["cancel"],message: "导入日志",html: html,showModal: true,callback: function(action){}});
}

function _getGridSearch(formName,dateField) {
	var form = new mini.Form("#"+formName);
	form.validate();
	if (!form.isValid()){var errors = form.getErrors();errors[0].focus();return false;}

	var fields = form.getFields();
	var ui,v,bs,df,obj;
	var json={};
	bs=false;
	df=!(dateField==null||dateField==""||strTrim(dateField)=="");
	var f="";
	if(df){f=","+dateField+",";}

	for(var i=0;i<fields.length;i++){
		ui = fields[i];
		v=ui.value;
		if(nvl(v)!=""&&ui.id.indexOf("mini-")<0){
			bs=true;
			if(df&&f.indexOf(","+ui.id+",")>=0){
				json[ui.id]=ui.getFormValue();
			}else{json[ui.id]=v;}
		}
	}
	if(bs) return json;
	else return null;
}
function _formReset(formName){
	var form = new mini.Form("#"+formName);
	form.reset();
}

function _makeVerticalStriArr(arr){
	var newArr = new Array(); //newArr是用来放置处理后的数组
	for(var i = 0; i < arr.length;i++){ //arr是处理前的数组
		var deptname = arr[i]+"";
		var str = deptname.charAt(0);
		for( var j = 1; j < deptname.length; j++){
			str = str + '<br/>' + deptname.charAt(j);
		}
		newArr.push(str);
	}
	return newArr;
}

/**
 * 播放声音
 * @param src
 * @private
 */
function _audioPlay(src) {
	if (!src || src.length == 0) {
		return;
	}
    var audio = new Audio(src);
    if (audio) {
        audio.play();
    }
}
// 计算当前月往前的12个月
function _getPrevious12MonthsOfTheCurrent() {

	var currentDt = new Date();
	var month = currentDt.getMonth() + 1, year = currentDt.getFullYear();
	var dt  = year + "-" + (month < 10 ? '0' + month : month);
	var dtArray = [{id: dt, text: dt}];

	for (var i = 0; i < 11; i++) {
		currentDt.setMonth(month - 2);
        month = currentDt.getMonth() + 1;
        year = currentDt.getFullYear();

        dt  = year + "-" + (month < 10 ? '0' + month : month);
        dtArray.push({id: dt, text: dt});
	}
	return dtArray;
}

/**
 * 将table 转成excel
 * @param tableid
 * @param hrefId
 * @param sheetName
 * @private
 */
function _tableToExcel(tableid, hrefId, sheetName) {

    var base64 = function (s) {
        return window.btoa(unescape(encodeURIComponent(s)));
    }
    var format = function (s, c) {
        return s.replace(/{(\w+)}/g, function (m, p) {
            return c[p];
        });
    }

    // xls格式   xlsx格式应为"data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64,";
    var uri = "data:application/vnd.ms-excel;base64,";
    var template = '<html xmlns:o="urn:schemas-microsoft-com:office:office"' +
        ' xmlns:x="urn:schemas-microsoft-com:office:excel"' +
        'xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet>' +
        '<x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets>' +
        '</x:ExcelWorkbook></xml><![endif]--><style type="text/css">' +
        'table td {' +
        'width: 200px;' +
        'height: 30px;' +
        'text-align: center;' +
        'background-color: white;}</style>' +
        '</head><body ><table class="excelTable">{table}</table></body></html>';
    if (!tableid.nodeType) tableid = document.getElementById(tableid);
    var ctx = {worksheet: sheetName || 'Worksheet', table: tableid.innerHTML};
    document.getElementById(hrefId).href = uri + base64(format(template, ctx));
}