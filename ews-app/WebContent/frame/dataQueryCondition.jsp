<%@ page
import="java.util.*"
import="org.apache.struts.util.RequestUtils"
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
import="com.corp.frame.core.metadata.QueryObject"
import="com.corp.frame.core.action.PrefixClass"
import="com.corp.frame.core.util.Validate"
import="com.corp.frame.core.util.StringUtil"
import="com.corp.frame.bean.MetaData"
import="com.corp.frame.bean.CustomConstants"%>

<% /*
///////////////////////////////////////////////////////////////
接口说明:
类型           变量名      说明
String         beanName   :Form 的名称 
String         property   :Form 中QueryView的名称;
String         domainName :域名;
*/ %>

<%
  DataManager dm  = null;
  String propertyPrefix = property+".queryObject(";
  String propertySuffix = ").value";
  int operatorType = CustomConstants.OPERATOR_TYPE_QUERY;
  boolean isShowDefaultValue = true;
  Collection fieldList = DatabaseObject.getDatabaseObject(domainName).getQueryFieldList();
  {
    Iterator iter = fieldList.iterator();
    StringBuffer buffer = new StringBuffer();
    while(iter.hasNext()){
      QueryFieldProperty field  = (QueryFieldProperty)iter.next();
      getHidden(propertyPrefix,field,buffer);
    }
    out.print(buffer.toString());
  }
try{
  UserInfo user = (UserInfo)session.getAttribute("userView");
  dm = DataManager.getInstance();
  dm.setUser(user);
  dm.setCheckPermission(true);
%>
<%@include file="/frame/dataForm.jsp" %>
<%
 }
 finally{
  if(dm != null){
    dm.close();
  }
 }
%>
<%!
  
  public void getHidden(String propertyPrefix, QueryFieldProperty fieldProperty,
                        StringBuffer buffer) {
    String fieldName = fieldProperty.getFieldName();
    if (fieldProperty.isDate()) {
      getHidden(propertyPrefix, fieldName + CustomConstants.FIELD_APPEND_START,
                "substr(" + fieldName + ",1,10)", ">=",
                 String.valueOf(QueryObject.TYPE_STRING), buffer);
      getHidden(propertyPrefix, fieldName + CustomConstants.FIELD_APPEND_END,
                "substr(" + fieldName + ",1,10)", "<=",
                 String.valueOf(QueryObject.TYPE_STRING), buffer);
    }
    else if (fieldProperty.isNumber()) {
      getHidden(propertyPrefix, fieldName + 
        CustomConstants.FIELD_APPEND_START, 
        fieldName, ">=", 
        String.valueOf(QueryObject.TYPE_NUMBER), buffer);
      getHidden(propertyPrefix, fieldName + 
        CustomConstants.FIELD_APPEND_END, 
        fieldName, "<=", 
        String.valueOf(QueryObject.TYPE_NUMBER), buffer);
    }
    else {
      getHidden(propertyPrefix,
                fieldName,
                fieldProperty.getQueryFieldName(),
                fieldProperty.getQueryOperator(),
                fieldProperty.getQueryType(),
                buffer);

    }
  }

  public void getHidden(String propertyPrefix, String fieldName,
                        String queryFieldName,
                        String queryOperator, String queryType,
                        StringBuffer buffer) {
    getHidden(propertyPrefix, fieldName,
              ").field", queryFieldName, buffer);
    getHidden(propertyPrefix, fieldName,
              ").operator", queryOperator, buffer);
    getHidden(propertyPrefix, fieldName,
              ").type", queryType, buffer);

  }
  public void getHidden(String propertyPrefix, String fieldName,
                        String propertySuffix,
                        String value, StringBuffer buffer) {
    if (value != null) {
      buffer.append("<input type='hidden' name='");
      buffer.append(propertyPrefix).append(fieldName).append(propertySuffix);
      buffer.append("' value='");
      buffer.append(value);
      buffer.append("'>\n");
    }
  }

%>