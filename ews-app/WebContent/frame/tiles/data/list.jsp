<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<tiles:insert page="/frame/tiles/template/template.jsp" flush="true">
  <tiles:put name="title"  value="??" />
  <tiles:put name="header" value="/frame/tiles/template/header.jsp" />
  <tiles:put name="left"   value="/frame/tiles/template/left.jsp" />
  <tiles:put name="menu"   value="/frame/tiles/template/menu.jsp" />
  <tiles:put name="body"   value="/frame/system/maintenance.jsp?domain_name=XT_MENU" />
  <tiles:put name="footer" value="/frame/tiles/template/footer.jsp" />
</tiles:insert>

