<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.corp.oa.util.G, com.corp.ews.EWSConstant" %>
<%
    if (!G.isOwnRight("13202000", request)) { out.print("会话已失效或无权限"); return; }
%>
<html>
<head>
    <title>采购入库</title>
    <META http-equiv=Content-Type content="text/html; charset=UTF-8">
    <LINK href="/images/css.css" type=text/css rel=stylesheet>
    <script type="text/javascript" src="/js/js.js?r=1221"></script>
    <link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
    <script src="/scripts/boot.js" type="text/javascript"></script>
    <style type="text/css">
        html, body{	margin:0;padding:0;border:0;width:100%;height:100%;overflow-x:hidden;}
    </style>
</head>
<body align="center" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div class="mini-fit">
    <iframe id="nodeIframe" style="width: 100%; height: 100%; border: none;"></iframe>
</div>

<script type="text/javascript">
    function init() {
        var json = {
            funcNode: "<%=EWSConstant.PURCHASE_CODE%>",
            billId: ''
        }
        var data = mini.encode(json);
        ajaxRequest('/AjaxService?method=LoginFuncNode', data, function (rs) {
            if (rs && rs.code == '0') {
                $("#nodeIframe").attr('src', rs.message);
            } else if (rs) {
                mini.alert(rs.message);
            }
        });
    }
    init();

</script>
</body>
</html>
