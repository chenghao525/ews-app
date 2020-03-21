<%@ page
import="java.util.*"
import="org.apache.struts.util.RequestUtils"
import="org.apache.struts.Globals"
import="java.util.Locale"
import="org.apache.struts.taglib.html.Constants"
import="org.apache.commons.beanutils.PropertyUtils"

import="com.corp.frame.core.dao.DataObject"
import="com.corp.frame.core.dao.DataManager"
import="com.corp.frame.core.code.Code"
import="com.corp.frame.core.code.CodeManager"
import="com.corp.frame.core.util.DateFormat"
import="com.corp.frame.core.base.UserInfo"
import="com.corp.frame.core.metadata.DatabaseObject"
import="com.corp.frame.core.metadata.FieldProperty"
import="com.corp.frame.core.metadata.QueryFieldProperty"
import="com.corp.frame.core.action.PrefixClass"
import="com.corp.frame.core.util.Validate"
import="com.corp.frame.core.util.StringUtil"
import="com.corp.frame.bean.MetaData"
import="com.corp.frame.bean.CustomConstants"%>

<%-- 
接口说明:
类型           变量名      说明
String        |beanName         : BEAN名字
String        |propertyPrefix   : 属性中 "fieldName" 之前的部分,如:"XXX.string("
String        |propertySuffix   : 属性中 "fieldName" 后面的那一部分,如:")"
Collection    |fieldList        : 字段列表
UserInfo      |user             : 用户对象
DataManager   |dm               : 数据库管理对象
int           |operatorType     : 操作类型 @see CustomConstants.OPERATOR_TYPE_
boolean       |isShowDefaultValue: 是否显示默认值
--%>

<%
  // 对于日期类型，需要引用一个JS文件，而这个JS文件只能引用一次，所以增加一个标志，
  // 用来判断该JS是否已经引用过。
  boolean isDataJSImport = false;
  
  pageContext.setAttribute("fields",fieldList);
%>
<extend:iterateGroup id="element" name="fields" scope="page" tdCount="2" column="3">
<%
  FieldProperty field=(FieldProperty)pageContext.getAttribute("element");
  // 字段名
  final String fieldName = field.getFieldName();
  // 属性名
  final String propertyName = propertyPrefix+fieldName+propertySuffix;
  // 得到 Form 对象
  final Object formObject = RequestUtils.lookup(pageContext,beanName,null,null);
  // bean中的值
  final Object currentValue = PropertyUtils.getProperty(formObject, propertyName);
  // 如果要设置默认值
  if(isShowDefaultValue){
    // 如果是新增
    if(operatorType == CustomConstants.OPERATOR_TYPE_INSERT || currentValue == null || StringUtil.isNull(String.valueOf(currentValue))){
       String defaultValue;
         // 且字段的属性为当前时间,则设置默认值
         if(field.isCurrentTime()){
           defaultValue = DateFormat.getCurrent();
         }
         // 如果是当前用户
         else if(field.isCurrentUser()){
           defaultValue = user.getUserId();
         }
         // 指定的默认值
         else{
           defaultValue = field.getTextArea().getDefaultValue();
         }
      PropertyUtils.setProperty(formObject,propertyName,defaultValue);
    }
    
  }
  pageContext.removeAttribute("colspan");
  // 初始合并数为 1
  int tempColspan= 1;
  // 如果没有名则不显示字段名
  if(field.isIncorporate()){
    tempColspan += 1;
  }
  else{
    out.print("<td align='right' class='tableLeft' nowrap>");
    if(field.getFieldCNName() == null || "".equals(field.getFieldCNName())){
    }
    else{
		Locale loc=(Locale) session.getAttribute(Globals.LOCALE_KEY);
		String lang=loc.toString();
		lang=lang.toLowerCase();

		if(lang==null || "".equals(lang))
		{//默认为中文
			out.print(field.getFieldCNName());
		}else if("zh_cn".equals(lang))
		{//中文
			out.print(field.getFieldCNName());
		}else if("en".equals(lang))
		{//英文
			out.print(field.getFieldENName());
		}else if("ja".equals(lang))
		{//日文
			out.print(field.getFieldJPName());
		}
      //out.print(":");
    }
    out.print("</td>");
  }
  pageContext.setAttribute("colspan",String.valueOf(tempColspan));
  if(field.isNewLine()){
    tempColspan += 2;
%>
  <extend:iterateNext colspanName="colspan" colspan="<%=tempColspan%>" />
<% } %>
  <td align="left" nowrap class="tableRight" colspan="<bean:write name="colspan" ignore="true"/>">
<%
      String help = "";
      Code code = null;
      boolean isAddHidden = false;
      boolean isGetCodeFromPrefix = false;
      if(field.isHasPrefix()){
        // 如果有前置类
        PrefixClass prefix = Validate.getPrefixClassObject(field.getPrefixClassName());
        // 要显示的代码是从前置类中取得的
        if(field.isGetCodeFromPrefix()){
          // 前置类返回代码
          code = prefix.doExecute(null,user,dm,null);
          isGetCodeFromPrefix = true;
        }
        else{
          prefix.doExecute(null,user,dm,null);
        }
//System.out.println(code.getValue());
      }
      // 该字段是否是只读的
      boolean readonly = field.isReadonly();
      // 如果是新增记录则设置的只读属性失效(即只读属性只对修改起作用)
      if(operatorType == CustomConstants.OPERATOR_TYPE_INSERT){
        readonly = false;
      }
      String onChange = "";
      if(field.isSubmitOnChange()){
        onChange="target='_self';submitForm('#');";
      }
      String style = field.getTextArea().getStyle();
      if(style == null){
        style = "";
      }
      String styleClass = field.getTextArea().getStyleClass();
      if(styleClass == null){
        styleClass = "";
      }
      String prefix = field.getTextArea().getPrefix();
      if(prefix != null){
         out.print(prefix);
      }

//------------------------------------- 显示类型 BEGIN ---------------------//
//----------如果是日期 -----------------------------------------------------//
      if(field.isDate()){
          if(!isDataJSImport){
            isDataJSImport = true;
%>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
<%
          }
        // 如果是日期类型,显示风格为 ... 至 ... 形式
        // 供 Struts 的 Property 属性使用
        if(operatorType == CustomConstants.OPERATOR_TYPE_QUERY){
          String stratPropertyName = propertyPrefix + fieldName + CustomConstants.FIELD_APPEND_START+propertySuffix;
          String endPropertyName = propertyPrefix + fieldName + CustomConstants.FIELD_APPEND_END+propertySuffix;
%>
<html:text name="<%=beanName%>" styleId="<%=stratPropertyName%>" property="<%=stratPropertyName%>" styleClass="mini-datepicker" style="width:120px;vertical-align:bottom;"/>&nbsp;～&nbsp;<html:text name="<%=beanName%>" styleId="<%=endPropertyName%>" property="<%=endPropertyName%>" styleClass="mini-datepicker" style="width:120px;vertical-align:bottom;"/>
<%
       }
       else{
%>
<html:text name="<%=beanName%>" styleId="<%=propertyName%>" property="<%=propertyName%>" styleClass="mini-datepicker" style="width:120px;vertical-align:bottom;"/>
<%
       }
       
     }else{
        // 非日期类型(字符串,大字符串,单选框,复选框,下列框,密码类型)
 //----------如果是代码 -----------------------------------------------------//
        if(field.isCode()){
          if(!isGetCodeFromPrefix){
            // 如果该代码没有从前置类中取得
            code = CodeManager.getCode(field.getCodeCondition(),dm);
          }
          if(code == null){
            out.println("the code ["+field.getCodeCondition()+"] is null.fromCode["+isGetCodeFromPrefix+"]");
            continue;
          }
          String codeName = ("string("+code.getCodeName()+")");
          String codeCNName = ("string("+code.getCodeCNName()+")");
//----------如果用列表显示 ---------------------------------------------------//
          if(field.isList()){
            isAddHidden = true;
            pageContext.setAttribute("list",code.getValue());
            // 如果用列表显示
%>
<html:select name="<%=beanName%>" property="<%=propertyName%>" size="1"
disabled="<%=readonly%>"
onchange="<%=onChange%>" style="<%=style%>" styleClass="<%=styleClass%>">
<html:option value=""></html:option>
<html:options collection="list" property="<%=codeName%>"
             labelProperty="<%=codeCNName%>"/>
</html:select>
<%
          }
//----------如果用复选按钮显示 -----------------------------------------------//
          else if(field.isCheckbox()){
            isAddHidden = true;
            // 复选按钮
            Iterator iter = code.getValue().iterator();
            while(iter.hasNext()){
               DataObject do1 = (DataObject)iter.next();
%>
<html:checkbox name="<%=beanName%>" property="<%=propertyName%>"
value="<%=do1.getString(code.getCodeName())%>" disabled="<%=readonly%>"
onchange="<%=onChange%>" style="<%=style%>" styleClass="<%=styleClass%>"/>
<%=do1.getString(code.getCodeCNName())%>
<%
            }
          }
//----------如果用单选按钮显示 -----------------------------------------------//
          else if(field.isRadio()){
            // 单选按钮
            isAddHidden = true;
            Iterator iter = code.getValue().iterator();
            while(iter.hasNext()){
               DataObject do1 = (DataObject)iter.next();
%>
<html:radio name="<%=beanName%>" property="<%=propertyName%>"
value="<%=do1.getString(code.getCodeName())%>" disabled="<%=readonly%>"
onchange="<%=onChange%>" style="<%=style%>" styleClass="<%=styleClass%>"/>
<%=do1.getString(code.getCodeCNName())%>
<%
            }
          }
//----------如果用普通文本显示 ---------------------------------------------//
          else{
          // 普通文本
          help = StringUtil.replace("getHelp('%s',this,'%s');",new String[]{
              field.getExplain(),String.valueOf(field.isCheckCodePermission())});
%>
   <html:text name="<%=beanName%>" property="<%=propertyName%>"
 readonly="<%=readonly%>" onkeydown="<%=help%>"
onchange="<%=onChange%>" style="<%=style%>" styleClass="<%=styleClass%>"/>
<%
           }
        }// end if(field.isCode())
//----------如果用密码类型显示 ---------------------------------------------//
        else if(field.isPassword()){
// 密码类型
%>
<html:password name="<%=beanName%>" property="<%=propertyName%>" readonly="<%=readonly%>"
onchange="<%=onChange%>" style="<%=style%>" styleClass="<%=styleClass%>"/>
<%
        }
//----------如果长的文本字符串 ---------------------------------------------//
        else if(field.isLargeString()){
// 长的文本字符串
%>
<html:textarea name="<%=beanName%>" property="<%=propertyName%>"  readonly="<%=readonly%>"
cols="<%=field.getTextArea().getCols()%>" rows="<%=field.getTextArea().getRows()%>"
onchange="<%=onChange%>" style="<%=style%>" styleClass="<%=styleClass%>"/>
<%
        }
//----------如果只显示不能输入 ---------------------------------------------//
        else if(field.isDisplay()){
%>
            <bean:write name="<%=beanName%>" property="<%=propertyName%>" />
<%
        }
//----------如果以上情况都不符合,默认用文件本框来显示 ----------------------//
        else{
          //System.out.println(propertyName);
%>
   <html:text name="<%=beanName%>" property="<%=propertyName%>" readonly="<%=readonly%>"
size="<%=field.getTextArea().getLength()%>" maxlength="<%=field.getTextArea().getMaxLength()%>"
onchange="<%=onChange%>" style="<%=style%>" styleClass="<%=styleClass%>"/>
<%
        }
//------------------------------------ 显示类型 END ------------------------------
        }
        // 如果在显示完字段后有一句话
        if(field.getTextArea().getSuffix() != null){
           out.print(field.getTextArea().getSuffix());
        }
        if(field.isRequired()){
          // 必输项
          out.print("<font color='red'>*</font>");
        }
        // 如果只读的话,保存时此列表的值 http 协议不会提交,
//------------------- 需增加一隐藏域保存其值 ------------------------------------
        if(readonly && isAddHidden){
%>
<html:hidden name="<%=beanName%>" property="<%=propertyName%>" />
<%
        }
//----------如果该输入框后面有一个按钮 ----------------------//
       if(field.getTextArea().getSuperfluityClassName() != null){
         String buttonName = field.getTextArea().getSuperfluityName();
         if(buttonName == null){
           buttonName = " 确定 ";
         }
%>
<INPUT TYPE="button" class="button-ui" value="<%=buttonName%>" class="button1"
 onClick="superfluityClassName.value='<%=field.getTextArea().getSuperfluityClassName()%>';submitForm('superfluity');">
<%
       }
%>
</td>
</extend:iterateGroup>
