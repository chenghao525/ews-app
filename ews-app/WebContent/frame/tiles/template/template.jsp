<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%-- Layout component
  parameters : title, header, left, body, footer
--%>
<HTML>

<HEAD>
<TITLE><tiles:getAsString name="title"/></TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<STYLE type=text/css>.side {
	FONT-SIZE: 80%; MARGIN-LEFT: 1em; LINE-HEIGHT: 165%; FONT-FAMILY: Arial, sans-serif
}
.menu {
	FONT-SIZE: 80%; LINE-HEIGHT: 165%; FONT-FAMILY: Arial, sans-serif
}.indent {
	MARGIN-LEFT: 1em; MARGIN-RIGHT: 1em
}
</STYLE>
<link href="/frame/css/stylesheet.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="/frame/css/js.js"></script>

<META content="MSHTML 6.00.2737.800" name=GENERATOR></HEAD>
<BODY text=#000000 vLink=#551a8b aLink=#ff0000 link=#0000cc bgColor=#ffffff
topMargin=2>
<tiles:insert attribute="header" />
<TABLE cellSpacing=0 cellPadding=3 width="100%" align=center border=0>
  <TBODY>
  <TR>
    <TD vAlign=top noWrap width=145>
     <tiles:insert attribute="left" />
    </TD>
    <TD width=4>&nbsp;</TD>
    <TD width=4 vAlign=bottom background="/frame/css/image/dot2.gif"></TD>
    <TD width=4>&nbsp;</TD>
    <TD vAlign=top>
       <tiles:insert attribute="menu" />
	   <tiles:insert attribute="body" />
    </TD></TR></TBODY></TABLE>
<tiles:insert attribute="footer" />
</BODY></HTML>