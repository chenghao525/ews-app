<form name="flowAction" action="flowPage.jsp" method="post">
<input type="hidden" name="flowId" value="<%=request.getParameter("flowId")%>">
<script language="javascript">
  flowAction.submit();
</script>
</form>