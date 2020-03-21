//通过控件名字得到object
function MM_findObj_(n, d){
	var p,i,x;

	if(!d)
		d=document;

	if((p=n.indexOf("?"))>0&&parent.frames.length)
	{
		d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);
	}
	if( !(x=d[n]) && d.all )
		x=d.all[n];

	for (i=0;!x&&i<d.forms.length;i++)
		x=d.forms[i][n];

	for(i=0;!x&&d.layers&&i<d.layers.length;i++)
		x=MM_findObj_(n,d.layers[i].document);

	return x;
}
function setValue(name,value)
{
    object = MM_findObj_(name);
    if(object){
      object.value=value;
    }
}
function printPage() { print(document); }
function redirect(URLStr) { location = URLStr; }
function MM_goToURL() { //v3.0
  var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
  for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
}
//打开帮助系统
function openHelpWindow(codeType,val,checkCodePermission)
{
	if( val.type=='text' )
		window.open('/frame/getHelp.jsp?controlName='+val.name+'&codeType='+codeType+'&codeValue='+val.value+'&checkCodePermission='+checkCodePermission+'',
'help','toolbar=no,status=yes,location=no,directories=no,menubar=no,scrollbars=yes,resizable=true,width=500');
	//else if( val.type=='select-one' )
	//	window.open('/xtwh/help/CodeSelectHelp.jsp?controlName='+val.name+'&codeType='+codeType+'','help','toolbar=no,status=yes,location=no,directories=no,menubar=no,scrollbars=yes,resizable=true,width=500,height=530');
}

/**************/
//获得帮助系统
function getHelp( codeType , control ,fieldType)
{
 if(event.keyCode==120)
      openHelpWindow(codeType , control,fieldType);
/*
   if(fieldType == '5'){
     openHelpWindow(codeType , control,fieldType);
   }
   else{
	if(event.keyCode==113)
		getCode(codeType,control);
	else if(event.keyCode==120)
		openHelp(codeType,control);
	else if(event.keyCode==123)
		getCnCode(codeType,control);
   }
  */
}
//获得帮助系统代码
function setCodeValue( cname )
{
	var myCodeValue = document.valueForm.codeValue.value;
	for( var i = 0 ;i < window.opener.document.forms.length; i ++)
	{
		for( var j = 0 ; j < window.opener.document.forms[i].elements.length ; j++ )
		{
			if( window.opener.document.forms[i].elements[j].name == cname)
			{

				window.opener.document.forms[i].elements[j].value = myCodeValue;
				if ( window.opener.document.forms[i].elements[j].value == '' )
					window.opener.document.forms[i].elements[j].value = '#';
				break;
			}
		}
	}
}
function ifdel()
{
	return ( confirm('确认删除选择的项目？') );
}
// 更改页面的Ｅｎｔｅｒ顺序
function pf_ChangeFocus()
{
   key = window.event.keyCode;
   if ( key==0xD && event.srcElement.tagName!='TEXTAREA') /*0xD*/
   {
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
  for(j=0;j<collen;j++)
  {
    if(colobj(j).id != ''){
      if(document.all("outlookdivin"+ colobj(j).id.substring(12)) != null){
      if(('outlooktitle'+objid) == colobj(j).id){
        colobj(j).className="content2";
        document.all("outlookdivin"+ colobj(j).id.substring(12)).style.display="";
      }
      else{
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
/*


  //alert(selected.id);
  var id;
  var formId = '';
  var element;
//  for( var i = 0 ; i < document.forms.length ; i++ )
//  formId = formId + i +',';

//  while( formId.indexOf(',') > 0 )
//  {
//    id = formId.substring(0,formId.indexOf(','));
//    formId = formId.substring(formId.indexOf(',')+1);
    for( i = 0 ;i < document.forms[0].elements.length ; i ++ )
    {
      element =  document.forms[0].elements[i];
      if(element.type=='checkbox')
      {
        elementName = element.name;
        if(type == 'PermissionToUser' || type == 'PermissionToRole')
        {
          // selectedList.string(020304);
          //alert(elementName.substring(20,24));
          if(elementName.substring(20,24) == selected.id){
            element.checked = selected.checked;
          }
        }
        else if(type == 'UserToRole'){
          //if(elementName.substring(22,26) == selected.id.substring(0,4)){
          //  element.checked = selected.checked;
          //}
        }
      }
    }
//  }
*/
}


//清除form里面的控件值
function clearAll()
{
	var formId = '';
	var id;
	var args = clearAll.arguments;
        var prefix = '';
	for( var i = 0 ; i < document.forms.length ; i++ )
		formId = formId + i +',';
        if(args.length > 0) {
		prefix = args[0];
        }

	while( formId.indexOf(',') > 0 )
	{
		id = formId.substring(0,formId.indexOf(','));
		formId = formId.substring(formId.indexOf(',')+1);
		for( i = 0 ;i < document.forms[id].elements.length ; i ++ )
		{
var name = document.forms[id].elements[i].name;
//alert(name);
//alert(prefix);
//alert(name.indexOf(prefix));
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
