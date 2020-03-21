<%@ page contentType="text/html; charset=UTF-8" import="org.apache.struts.util.RequestUtils" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://www.shanghaicorp.com/struts/extend" prefix="extend" %>

<%
	String o=(String)RequestUtils.lookup(pageContext, "providerForm", "opt", null);

	String tby=(String)RequestUtils.lookup(pageContext, "providerForm", "t_INFO_PROVIDER.string(TB_BY_PDU)", null);
%>
<html:html>
<HEAD><title>商户信息管理</title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="/images/css.css" type=text/css rel=stylesheet>
</HEAD>

<script type="text/javascript" src="/js/js.js?r=1221"></script>
<link href="/scripts/demo.css" rel="stylesheet" type="text/css" />
<script src="/scripts/boot.js" type="text/javascript"></script>
<link rel="stylesheet" href="/viewer/dist/viewer.css">
<link rel="stylesheet" href="/viewer/css/main.css">
<body align="center" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div style="height:5px"></div>
<div id="dataTab" class="mini-tabs" style="width:100%;height:98%" onactivechanged="onActivechanged">
	<div title="商户基础信息" iconCls="icon-node" id="tab1" name="grid">
		<html:form action="/provider/provider" style="margin-bottom:0" styleId="form00">
		<html:hidden name="providerForm" property="sid"/>
		<html:hidden name="providerForm" property="current"/>
		<html:hidden name="providerForm" property="t_INFO_PROVIDER.string(SID)"/>
		<html:hidden name="providerForm" property="t_INFO_PROVIDER.string(BRAND_ID_LIST)"/>
		<html:hidden name="providerForm" property="t_INFO_PROVIDER.string(AUTH_BRAND)"/>
		<html:hidden name="providerForm" property="t_INFO_PROVIDER.string(BRAND_ID_LIST_EX)"/>
		<html:hidden name="providerForm" property="t_INFO_PROVIDER.string(MATCH_ALL)"/>
		<html:hidden name="providerForm" property="t_INFO_PROVIDER.string(PROV_PROMISE)"/>
		<html:hidden name="providerForm" property="t_INFO_PROVIDER.string(SRV_ITEMS)"/>
		<html:hidden name="providerForm" property="t_INFO_PROVIDER.string(PROV_TYPE)"/>
		<html:hidden name="providerForm" property="t_INFO_PROVIDER.string(VIDEO_VALID)"/>
		<html:hidden name="providerForm" property="t_INFO_PROVIDER.string(QM_M_LMT)"/>
		<html:hidden name="providerForm" property="t_INFO_PROVIDER.string(TOP_POS)"/>
		<html:hidden name="providerForm" property="photoPath"/>
		<html:hidden name="providerForm" property="photoPathOld"/>
		<html:hidden name="providerForm" property="maList"/>
		<html:hidden name="providerForm" property="attrList"/>

		<input type="hidden" name="PROVINCE" value="<bean:write name="providerForm" property="t_INFO_PROVIDER.string(PROVINCE)"/>">
		<input type="hidden" name="CITY" value="<bean:write name="providerForm" property="t_INFO_PROVIDER.string(CITY)"/>">
		<input type="hidden" name="AREA" value="<bean:write name="providerForm" property="t_INFO_PROVIDER.string(AREA)"/>">
		<br>
		<TABLE width="95%" class="detail-table" cellpadding="5" cellspacing="0" align="center" style=" margin:0 auto">
			<tr align="center" bgcolor="#e0e3e8" height=25><td colspan="4"><b>商户详细信息&nbsp;<bean:write name="providerForm" property="t_INFO_PROVIDER.string(SID)"/></b></td></tr>
			<tr height=25>
				<td align="right" bgcolor="f7f7f7">点评分数：</td>
				<td>&nbsp;<bean:write name="providerForm" property="t_INFO_PROVIDER.string(REVIEW_POINT)"/>&nbsp;分</td>
				<td align="right" bgcolor="f7f7f7">总销售量：</td>
				<td>&nbsp;<bean:write name="providerForm" property="t_INFO_PROVIDER.string(SOLD_TOTAL)"/>&nbsp;单</td>
			</tr>
			<tr height=25>
				<td width="12%" align="right" bgcolor="f7f7f7">商户编号：</td>
				<td width="30%"><html:text property="t_INFO_PROVIDER.string(PROV_ID)" size="40" maxlength="30"/>&nbsp;<font color="red">*</font></td>
				<td width="12%" align="right" bgcolor="f7f7f7">所在城市：</td>
				<td width="46">
					<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="161" n2="1"/>
					<html:select property="t_INFO_PROVIDER.string(PROVINCE)" size="1" onchange="getCityArea('16',this.value,'t_INFO_PROVIDER.string(CITY)',getValue('CITY'));">
					<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
					</html:select>
					<html:select property="t_INFO_PROVIDER.string(CITY)" size="1" onchange="getCityArea('18',this.value,'t_INFO_PROVIDER.string(AREA)',getValue('AREA'));">
					<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
					</html:select>
					<html:select property="t_INFO_PROVIDER.string(AREA)" size="1">
					<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
					</html:select>&nbsp;<font color="red">*</font>
				</td>
			</tr>
			<tr height=25>
				<td align="right" bgcolor="f7f7f7">商户名称：</td>
				<td><html:text property="t_INFO_PROVIDER.string(PROV_NAME)" size="40" maxlength="100"/>&nbsp;<font color="red">*</font></td>
				<td align="right" bgcolor="f7f7f7">店名/简称：</td>
				<td><html:text property="t_INFO_PROVIDER.string(PROV_NAME_SUM)" size="40" maxlength="50"/>&nbsp;<font color="red">*</font></td>
			</tr>
			<tr height=25>
				<td align="right" bgcolor="f7f7f7">分店名：</td>
				<td><html:text property="t_INFO_PROVIDER.string(BRANCH_NAME)" size="40" maxlength="50"/></td>
				<td align="right" bgcolor="f7f7f7">公司网站：</td>
				<td><html:text property="t_INFO_PROVIDER.string(WEB_ADDR)" size="40" maxlength="100"/></td>
			</tr>
			<tr height=25>
				<td align="right" bgcolor="f7f7f7">热线电话：</td>
				<td><html:text property="t_INFO_PROVIDER.string(TEL)" size="40" maxlength="25"/></td>
				<td align="right" bgcolor="f7f7f7">服务时间：</td>
				<td><html:text property="t_INFO_PROVIDER.string(SRV_TIME)" size="40" maxlength="100"/>&nbsp;<font color="red">*</font></td>
			</tr>
			<tr height=25>
				<td align="right" bgcolor="f7f7f7">商户类别：</td>
				<td>
					<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="17" n2="1"/>
					<html:select property="t_INFO_PROVIDER.string(SRV_TYPE)" onchange="changeBrandSel();" size="1">
					<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
					</html:select>&nbsp;<font color="red">*</font>
			<%
				//	&nbsp;&nbsp;&nbsp;&nbsp;
				//	<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="19" n2="1"/>
				//	<html:select property="t_INFO_PROVIDER.string(BRAND_ID)" size="1">
				//	<html:option value=""></html:option>
				//	<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
				//	</html:select>(保留属性)
			%>
				</td>
				<td align="right" bgcolor="f7f7f7">服务汽车品牌：</td>
				<td><html:text property="t_INFO_PROVIDER.string(BRAND_DESC)" size="40" maxlength="100"/>（文字描述）</td>
			</tr>
			<tr height=25>
				<td align="right" bgcolor="f7f7f7"><font color="red"><b>授权</b></font>服务品牌：</td>
				<td>
					<input id="AUTH_BRAND" style="width:260px;" class="mini-treeselect" multiSelect="true" textField="TEXT" valueField="ID"  parentField="PID" checkRecursive="true" showFolderCheckBox="true" expandOnLoad="false" allowInput="false" url=""  onfocus="loadCarList3"/>
				</td>
				<td align="right" bgcolor="f7f7f7">服务汽车品牌：</td>
				<td>
					<input type="radio" name="MATCH_ALL" value="yes" checked onclick="$('#brandidlist').hide();"><font color="blue">所有</font>&nbsp;&nbsp;
					<input type="radio" name="MATCH_ALL" value="no" onclick="$('#brandidlist').show();"><font color="red">部分</font>
					<span id="brandidlist" style="display:none">
						<input id="BRAND_ID_LIST" style="width:300px;" class="mini-treeselect" multiSelect="true" textField="TEXT" valueField="ID"  parentField="PID" checkRecursive="true" showFolderCheckBox="true" expandOnLoad="false" popupWidth="400" allowInput="false" url=""  onfocus="loadCarList"/>
					</span>
				</td>
			</tr>
			<tr height=25>
				<td align="right" bgcolor="f7f7f7">地址：</td>
				<td><html:text property="t_INFO_PROVIDER.string(ADDR)" size="40" maxlength="100"/></td>
				<td align="right" bgcolor="f7f7f7">GPS经纬度：</td>
				<td>经度：<html:text property="t_INFO_PROVIDER.string(LONGITUDE)" size="10" maxlength="20"/>&nbsp;&nbsp;纬度：<html:text property="t_INFO_PROVIDER.string(LATITUDE)" size="10" maxlength="15"/>（百度GPS <a style='cursor:hand;' onclick="gpsShow()">查看</a>）</td>
			</tr>
			<tr height=25>
				<td align="right" bgcolor="f7f7f7">联系人一：</td>
				<td><html:text property="t_INFO_PROVIDER.string(PEOPLE1)" size="40" maxlength="20"/></td>
				<td align="right" bgcolor="f7f7f7">联系人一电话：</td>
				<td><html:text property="t_INFO_PROVIDER.string(TEL1)" size="40" maxlength="15"/></td>
			</tr>
			<tr height=25>
				<td align="right" bgcolor="f7f7f7">联系人二：</td>
				<td><html:text property="t_INFO_PROVIDER.string(PEOPLE2)" size="40" maxlength="20"/></td>
				<td align="right" bgcolor="f7f7f7">联系人二电话：</td>
				<td><html:text property="t_INFO_PROVIDER.string(TEL2)" size="40" maxlength="15"/></td>
			</tr>
			<tr height=25>
				<td align="right" bgcolor="f7f7f7">合作状态：</td>
				<td>
					<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="29" n2="1"/>
					<html:select property="t_INFO_PROVIDER.string(COOP_STATE)" size="1">
					<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
					</html:select>
				</td>
				<td align="right" bgcolor="f7f7f7">商户结算：</td>
				<td>
					<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="9" n2="1"/>
					<html:select property="t_INFO_PROVIDER.string(BILL_TYPE)" size="1">
					<html:option value=""></html:option>
					<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
					</html:select>&nbsp;<font color="red">*</font>
				</td>
			</tr>
			<tr height=25>
				<td align="right" bgcolor="f7f7f7">服务内容（服务项）：</td>
				<td>
					<div id="SRV_ITEMS" name="SRV_ITEMS" class="mini-combobox" style="width:260px;" popupWidth="260" textField="text" valueField="id" url='/AjaxService?method=GetDicList4MU&data={"infotype":"7"}' value="" multiSelect="true" showClose="true" oncloseclick="onCloseClick" >
						<div property="columns">
							<div header="编号" field="id" width="30"></div>
							<div header="项目" field="text"></div>
						</div>
					</div>
				</td>
				<td align="right" bgcolor="f7f7f7">服务承诺：</td>
				<td>
					<div id="PROV_PROMISE" name="PROV_PROMISE" class="mini-combobox" style="width:260px;" popupWidth="260" textField="text" valueField="id" url='/AjaxService?method=GetDicList4MU&data={"infotype":"34"}' value="" multiSelect="true" showClose="true" oncloseclick="onCloseClick" >
						<div property="columns">
							<div header="编号" field="id" width="20"></div>
							<div header="项目" field="text"></div>
						</div>
					</div>
				</td>
			</tr>

			<tr height=25>
				<td align="right" bgcolor="f7f7f7">服务内容（维保项）：</td>
				<td>
					<input id="MA_LIST" name="MA_LIST" class="mini-treeselect" multiSelect="true" showClose="true" expandOnLoad="true" style="width:260px;" textField="TEXT" valueField="ID" parentField="PID" checkRecursive="false" popupWidth="260" value="<bean:write name="providerForm" property="maList"/>" onbeforenodeselect="beforenodeselect" url="/AjaxService?method=GetMaTree4MU"/>
				</td>
				<td align="right" bgcolor="f7f7f7">服务属性：</td>
				<td>
					<input id="ATTR_LIST" name="ATTR_LIST" class="mini-treeselect" multiSelect="true" showClose="true" expandOnLoad="true" style="width:260px;" textField="TEXT" valueField="ID" parentField="PID" checkRecursive="false" popupWidth="260" value="<bean:write name="providerForm" property="attrList"/>" onbeforenodeselect="beforenodeselect" url="/AjaxService?method=GetProvAttrList"/>
				</td>
			</tr>
			<tr height="25">
				<td nowrap align="right" bgcolor="f7f7f7">商户介绍（Web端）：</td>
				<td nowrap valign="top"><html:textarea property="t_INFO_PROVIDER.string(PROV_DESC)" rows="4" cols="40" maxlength="1000"/></td>
				<td nowrap align="right" bgcolor="f7f7f7">商户介绍（移动端）：</td>
				<td nowrap valign="top"><html:textarea property="t_INFO_PROVIDER.string(PROV_DESC_M)" rows="4" cols="40" maxlength="500"/></td>
			</tr>
			<tr height="25">
				<td nowrap align="right" bgcolor="f7f7f7">有视频监控：</td>
				<td nowrap valign="top">
					<input name="VIDEO_VALID" id="VIDEO_VALID" class="mini-combobox" style="width:100px;" value="no" required="true"  url="/data/yesno.txt"/>
				</td>
				<td nowrap align="right" bgcolor="f7f7f7">漆面单月限量：</td>
				<td nowrap valign="top"><input id="QM_M_LMT" name="QM_M_LMT" class="mini-spinner" required="true" value="<bean:write name="providerForm" property="t_INFO_PROVIDER.string(QM_M_LMT)"/>" minValue="0" maxValue="500" decimalPlaces="0" style="width:100px"/>(0表示不限制)</td>
			</tr>
			<tr height="25">
				<td nowrap align="right" bgcolor="f7f7f7">门店置顶：</td>
				<td nowrap valign="top">
					<input id="TOP_POS" name="TOP_POS" class="mini-spinner" required="true" value="<bean:write name="providerForm" property="t_INFO_PROVIDER.string(TOP_POS)"/>" minValue="0" maxValue="100" decimalPlaces="0" style="width:100px"/>( >0表示置顶)
				</td>
				<td nowrap align="right" bgcolor="f7f7f7">商户标签：</td>
				<td nowrap valign="top"><font color="blue"><span id="provlabel"><bean:write name="providerForm" property="t_INFO_PROVIDER.string(LBL_LIST)"/></span></font><INPUT TYPE="button" class="button-ui" value="维护" onclick="editLabel();"></td>
				<script type="text/javascript">
				<!--
				function editLabel(){
					mini.open({
						url: "/sortTable/provLabel.jsp?sid=<bean:write name="providerForm" property="sid"/>",
						title: "标签管理",
						width: 900,
						height: 600,
						allowResize:false,
						ondestroy: function (action) {
							var iframe = this.getIFrameEl();
							var data = iframe.contentWindow.getData();
							data = mini.clone(data);    //必须
							if(data) $('#provlabel').html(data);
						}
					});
				}
				//-->
				</script>
			</tr>
			<tr height=25>
				<td align="right" bgcolor="f7f7f7"><b><font color="red">排除服务品牌</font></b>：</td>
				<td>			
					<input id="BRAND_ID_LIST_EX" style="width:260px;" class="mini-treeselect" multiSelect="true" textField="TEXT" valueField="ID"  parentField="PID" checkRecursive="true" showFolderCheckBox="true" expandOnLoad="false" popupWidth="500" allowInput="false" url=""  onfocus="loadCarList2"/>
				</td>
				<td nowrap align="right" bgcolor="f7f7f7">备注：</td>
				<td nowrap valign="top"><html:textarea property="t_INFO_PROVIDER.string(MEMO)" rows="1" cols="50" maxlength="1000"/></td>
			</tr>
			<tr height="25">
				<td align="right" bgcolor="f7f7f7">主图片：</td>
				<td>
					<ul class="docs-pictures main-image"><li style="width:160px"><img height="160px" id="empPhoto" data-original="<bean:write name="providerForm" property="photoPath"/>" src="<bean:write name="providerForm" property="photoPath"/>"></li></ul>
					<br><INPUT TYPE="button" class="button-ui" value="上传图片" onclick="openPhotoUpload();">
				</td>
				<td nowrap align="right" bgcolor="f7f7f7">其他图片：<br><br><% if("edit".equals(o)){out.print("<a style='cursor:hand;' onclick='managePicture()'>管理</a>&nbsp;");} %><a style='cursor:hand;' onclick="loadPictures();">刷新</a></td>
				<td nowrap id="pic"></td>
			</tr>
			<tr bgcolor="#FFFF99" height=25><td colspan="4">以下是商户联系信息</td></tr>
			<tr height=25>
				<td align="right" bgcolor="f7f7f7">服务SA姓名：</td>
				<td><html:text property="t_INFO_PROVIDER.string(SRV_MAN_NAME)" size="40" maxlength="50"/></td>
				<td align="right" bgcolor="f7f7f7">电话：</td>
				<td><html:text property="t_INFO_PROVIDER.string(SRV_MAN_TEL)" size="40" maxlength="50"/>（英文逗号隔开）</td>
			</tr>
			<tr height=25>
				<td align="right" bgcolor="f7f7f7">物流配送联系信息：</td>
				<td><html:text property="t_INFO_PROVIDER.string(DLV_INFO)" size="40" maxlength="20"/>&nbsp;</td>
				<td align="right" bgcolor="f7f7f7">&nbsp;</td>
				<td></td>
			</tr>
			<tr bgcolor="#FFFF99" height=25><td colspan="4">以下是用友财务信息</td></tr>
			<tr height=25>
				<td align="right" bgcolor="f7f7f7">用友编码：</td>
				<td><html:text property="t_INFO_PROVIDER.string(YY_CODE)" size="40" maxlength="10"/></td>
				<td align="right" bgcolor="f7f7f7">商户税率：</td>
				<td><html:text property="t_INFO_PROVIDER.string(YY_TAX)" size="40" maxlength="10"/>（17或者6）</td>
			</tr>
			<tr bgcolor="#FFFF99" height=25><td colspan="4">以下是商户渠道属性</td></tr>
			<tr height=25>
				<td align="right" bgcolor="f7f7f7">商户渠道来源：</td>
				<td>
					<extend:importCode domainName="T_LIST_STATUS" codeName="list" n1="31" n2="1"/>
					<html:select style="width:260px" property="t_INFO_PROVIDER.string(PROV_CHANNEL)" size="1">
					<html:options collection="list" property="string(CODENO)" labelProperty="string(CODENAME)"/>
					</html:select>&nbsp;<font color="red">*</font>
				</td>
				<td align="right" bgcolor="f7f7f7">商户分类：</td>
				<td>
					<input name="PROV_TYPE" id="PROV_TYPE" class="mini-combobox" value="" style="width:300px;" multiSelect="true" url="/data/provtype.txt"/>
				</td>
			</tr>
			<tr height=25>
				<td align="right" bgcolor="f7f7f7">商户渠道编码：</td>
				<td><html:text property="t_INFO_PROVIDER.string(TB_ID)" size="40" maxlength="50"/></td>
				<td align="right" bgcolor="f7f7f7">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr bgcolor="f7f7f7" height="50">
				<td align="center" colspan="4"><br>
					<logic:notEqual name="providerForm" property="opt" value="see">
						<logic:equal name="providerForm" property="bPop" value="false">
							<extend:submit StyleClass="button-ui" property="b_save" onclick="return checkData(false);">保 存</extend:submit>
							&nbsp;&nbsp;
						</logic:equal>
						
						<logic:equal name="providerForm" property="bPop" value="true">
							<extend:submit StyleClass="button-ui" property="b_savepop" onclick="return checkData(true);">保 存</extend:submit>
							&nbsp;&nbsp;
						</logic:equal>
					</logic:notEqual>
					<logic:equal name="providerForm" property="t_INFO_PROVIDER.string(ACCOUNT_OPEN)" value="yes">
						<input type="button" class="button-ui" value="账户信息" onclick="provView()"/>
						<script type="text/javascript">
						<!--
						function provView(){
							mini.open({
								url: "/prov/account.do?b_view=true&provSid=<bean:write name="providerForm" property="t_INFO_PROVIDER.string(SID)"/>&opt=view",
								title: "商户账户",
								iconCls: "icon-user", 
								width: 1000,
								height: 600,
								allowResize:false
							});
						}
						//-->
						</script>
						&nbsp;&nbsp;<input type="button" class="button-ui" value="查看账户流水" onclick="openAccount()"/>
					</logic:equal>
					<logic:equal name="providerForm" property="t_INFO_PROVIDER.string(ACCOUNT_OPEN)" value="no">
						<font size="+1" color="red">商户结算账户还未开通</font>
					</logic:equal>
				<br><br></td>
			</tr>
		</table>
	</div>
<%if("edit".equals(o)||"see".equals(o)){%>
	<div title="登录账户" iconCls="icon-user" id="tab2" name="grid2">
		<div class="mini-fit">
			<div id="datagrid2" class="mini-datagrid" style="width:100%;height:100%; margin:0 auto" idField="SID" allowResize="false" showPager="false"  allowCellEdit="false" allowCellSelect="false" sortMode="client">
				<div property="columns">
					<div type="indexcolumn" width="20"></div>
					<div field="SID" width="30" headerAlign="center" allowSort="false" visible="false">序号</div>
					<div field="USER_ID" width="100" allowSort="true" vtype="required">账号	</div>
					<div field="USER_NAME" width="100" allowSort="true" vtype="required">名称</div>
					<div field="USER_PWD" width="100" allowSort="true" vtype="required">密码</div>
					<div field="USER_MB" width="100" allowSort="true">手机</div>
					<div type="comboboxcolumn" field="USER_ACTIVE" width="60" allowSort="true" vtype="required" autoShowPopup="true">状态
						<input property="editor" class="mini-combobox" style="width:100%;" url='/AjaxService?method=GetDicList4MU&data={"infotype":"3"}'/>  
					</div>
					<div type="comboboxcolumn" field="CHANNEL_SRC" width="100" allowSort="true" autoShowPopup="true">配件分销商
						<input property="editor" class="mini-combobox" style="width:100%;" showClose="true"  oncloseclick="onCloseClick" url='/AjaxService?method=GetDicList4MU&data={"infotype":"32"}'/> 
					</div>
					<div field="MEMO" width="150" allowSort="true">备注</div>
				</div>
			</div>
		</div>
	</div>
	<div title="工位信息" iconCls="icon-ma" id="tab3" name="grid1">
		<div class="mini-fit">
			<div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;margin:0 auto" idField="SID" allowResize="false" showPager="false" sortMode="client">
				<div property="columns">
					<div type="indexcolumn" width="20"></div>
					<div field="SID" width="60" headerAlign="center" allowSort="false" visible="false">序号</div>
					<div field="WP_ID" width="80" allowSort="true" vtype="required">工位编号(不能重复)</div>
					<div field="WP_NAME" width="100" allowSort="true" vtype="required">工位简称</div>
					<div field="NODE_ORDER" width="30" allowSort="true" vtype="required;int">排序</div>
					<div type="comboboxcolumn" field="WP_ACTIVE" width="60" allowSort="true" vtype="required" autoShowPopup="true">当前有效
						<input property="editor" class="mini-combobox" style="width:100%;" url='/AjaxService?method=GetDicList4MU&data={"infotype":"3"}'/>  
					</div>
					<div type="comboboxcolumn" field="SRV_ITEMS" width="80" allowSort="true" vtype="required" autoShowPopup="true">业务属性
						<input property="editor" class="mini-combobox" style="width:100%;" url='/AjaxService?method=GetDicList4MU&data={"infotype":"7"}'/>
					</div>
					<div field="OWNER_MB" width="70" allowSort="true">负责人手机</div>
					<div field="TIME_START" width="60" allowSort="true" dateFormat="H:mm">预约开始时间</div>
					<div field="TIME_END" width="60" allowSort="true" dateFormat="H:mm">预约结束时间</div>
					<div type="comboboxcolumn" field="ELEVATOR_SIDE_EXIST" width="60" allowSort="true" vtype="required" autoShowPopup="true">两侧升降机
						<input property="editor" class="mini-combobox" style="width:100%;" data="yesno"/>  
					</div>
					<div type="comboboxcolumn" field="ELEVATOR_FOOT_EXIST" width="60" allowSort="true" vtype="required" autoShowPopup="true">
						底部升降机<input property="editor" class="mini-combobox" style="width:100%;" data="yesno"/>  
					</div>
					<div type="comboboxcolumn" field="CAMERA_EXIST" width="60" allowSort="true" vtype="required" autoShowPopup="true">摄像头
						<input property="editor" class="mini-combobox" style="width:100%;" data="yesno"/>  
					</div>
					<div field="CAMERA_URL" width="60" allowSort="true">摄像头url</div>
					<div field="WP_DEFAULT" width="50" allowSort="true">默认工位</div>
				</div>
			</div>
		</div>
	</div>
	<div title="订单信息" iconCls="icon-new" id="tab4" name="grid3">
		<div class="mini-fit">
			<div id="datagrid3" class="mini-datagrid" style="width:100%;height:100%;margin:0 auto" idField="SID" allowResize="false" sizeList="[50,100]"  pageSize="50" allowCellEdit="false" allowCellSelect="false" multiSelect="false" allowCellValid="false" sortMode="server" url="" onrowdblclick="rowdblclick">
				<div property="columns">
					<div type="indexcolumn" width="20"></div>
					<div field="TASK_ID" width="80" allowSort="true">任务单号</div>
					<div field="CUST_ID" width="80" allowSort="true">客户手机号</div>
					<div field="CUST_NAME" width="60" allowSort="true">客户名称</div>
					<div field="SRV_TYPE_NAME" width="60" vtype="required" allowSort="true">服务项</div>
					<div field="CRT_DATE" width="100" dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">下单时间</div>
					<div field="ASK_SRV_TIME_START" width="100" vtype="required" dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">预约时间</div>
					<div field="BOOK_CODE_USED_TIME" width="100" dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">服务时间</div>
					<div field="SRV_FINISHED_TIME" width="100" dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">服务完成时间</div>
					<div type="comboboxcolumn" field="TASK_STATE" width="60" allowSort="true">订单状态
						<input property="editor" class="mini-combobox" style="width:100%;" url='/AjaxService?method=GetDicList4MU&data={"infotype":"5","order":"optorder"}'/>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div title="客户点评" iconCls="icon-custom" id="tab5" name="grid5">
		<div class="mini-fit">
			<div id="datagrid5" class="mini-datagrid" style="width:100%;height:100%;" idField="SID" allowResize="false" sizeList="[50,100]" pageSize="50" allowCellEdit="true"  allowCellValid="false" sortMode="server" url="" oncellclick="gridcellclick">
				<div property="columns">
					<div type="indexcolumn" width="20"></div>
					<div field="IMAGES_COUNT" width="40">图/点我</div>
					<div field="UUID" width="0" visible="false"></div>
					<div field="RV_POINT" width="40" dataType='currency' currencyUnit='' align='right' allowSort="true">评分</div>
					<div field="CUST_ID" width="100" allowSort="true">手机号</div>
					<div field="CUST_NAME" width="60" allowSort="true">姓名</div>
					<div field="RV_TIME" width="120" dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">点评时间</div>
					<div field="RV_CONTENT" width="400" allowSort="true">点评</div>
					<div field="CHECKED" width="50" allowSort="true">已审核</div>
					<div field="RV_LABEL_NAME" width="100">点评标签</div>
					<div field="BU_REPLY" width="150" allowSort="true">商户回复</div>
					<div field="RV_MORE" width="100">追评</div>
					<div field="RV_MORE_TIME" width="80" dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">追评时间</div>
				</div>
			</div>
		</div>
	</div>
<%}%>
</div>
	
<SCRIPT LANGUAGE="JavaScript">
<!--
mini.parse();
var myJson=mini.decode(top._myData1Lev);
<%if("edit".equals(o)){%>
	var bEdit=true;
<%}else{%>
	var bEdit=false;
<%}%>

function iniArea(p,c,a){
	getCityAreaNoChange('161',null,'t_INFO_DELIVERY.string(PROVINCE)',p,false);
	getCityAreaNoChange('16',p,'t_INFO_DELIVERY.string(CITY)',c,false);
	getCityAreaNoChange('18',c,'t_INFO_DELIVERY.string(AREA)',a,false);
}

function checkData(pop){
	if(getTrimValue("t_INFO_PROVIDER.string(PROV_ID)")==""){
		mini.alert("商户编号不能为空！");
		setFocus(document.getElementsByName("t_INFO_PROVIDER.string(PROV_ID)")[0]); 
		return false;
	}
	if(getTrimValue("t_INFO_PROVIDER.string(PROV_NAME)")==""){
		mini.alert("商户名称不能为空！");
		setFocus(document.getElementsByName("t_INFO_PROVIDER.string(PROV_NAME)")[0]); 
		return false;
	}
	if(getTrimValue("t_INFO_PROVIDER.string(PROV_NAME_SUM)")==""){
		mini.alert("商户简称不能为空！");
		setFocus(document.getElementsByName("t_INFO_PROVIDER.string(PROV_NAME_SUM)")[0]); 
		return false;
	}
	if(getTrimValue("t_INFO_PROVIDER.string(SRV_TIME)")==""){
		mini.alert("服务时间不能为空！");
		setFocus(document.getElementsByName("t_INFO_PROVIDER.string(SRV_TIME)")[0]); 
		return false;
	}
	if(getTrimValue("t_INFO_PROVIDER.string(BILL_TYPE)")==""){
		mini.alert("商户结算方式不能为空！");
		setFocus(document.getElementsByName("t_INFO_PROVIDER.string(BILL_TYPE)")[0]); 
		return false;
	}
	
	var obj,v,ct1,ct2;
	if($('input[name=MATCH_ALL]:eq(0)').is(':checked')){
		setValue("t_INFO_PROVIDER.string(BRAND_ID_LIST)","");
		setValue("t_INFO_PROVIDER.string(MATCH_ALL)","yes");
	}else{
		obj=mini.get("BRAND_ID_LIST");
		v=obj.getValue();
		setValue("t_INFO_PROVIDER.string(BRAND_ID_LIST)",v);
		setValue("t_INFO_PROVIDER.string(MATCH_ALL)","no");
	}

	obj=mini.get("BRAND_ID_LIST_EX");
	v=obj.getValue();
	setValue("t_INFO_PROVIDER.string(BRAND_ID_LIST_EX)",v);

	obj=mini.get("AUTH_BRAND");
	v=obj.getValue();
	setValue("t_INFO_PROVIDER.string(AUTH_BRAND)",v);

	obj=mini.get("PROV_PROMISE");
	v=obj.getValue();
	setValue("t_INFO_PROVIDER.string(PROV_PROMISE)",v);
	
	obj=mini.get("SRV_ITEMS");
	v=obj.getValue();
	setValue("t_INFO_PROVIDER.string(SRV_ITEMS)",v);
	
	obj=mini.get("PROV_TYPE");
	v=obj.getValue();
	setValue("t_INFO_PROVIDER.string(PROV_TYPE)",v);
	
	obj=mini.get("VIDEO_VALID");
	v=obj.getValue();
	setValue("t_INFO_PROVIDER.string(VIDEO_VALID)",v);

	obj=mini.get("QM_M_LMT");
	v=obj.getValue();
	setValue("t_INFO_PROVIDER.string(QM_M_LMT)",v);
	
	obj=mini.get("TOP_POS");
	v=obj.getValue();
	setValue("t_INFO_PROVIDER.string(TOP_POS)",v);

	obj=mini.get("MA_LIST");
	v=obj.getValue();
	setValue("maList",v);

	obj=mini.get("ATTR_LIST");
	v=obj.getValue();
	setValue("attrList",v);

	mini.confirm("您确认保存信息？", "请确认",
		function(action){
			if(action == "ok"){
				mini.loading("正在提交数据并进行商户与配件的智能匹配，请稍后 ...", "提交");
				var form=document.forms["form00"];
				if(pop) form.action=form.action+"?b_savepop=true";
				else form.action=form.action+"?b_save=true";
				form.submit();
				return false;
			}
		}
	);
	return false;
}
function openPhotoUpload(){
	var w=window.open("/file/imageUpload/upload.jsp","upload",'width=500px,height=280px,menubar=0,toolbar=0,directories=0,location=0,status=0,scrollbars=1');
	w.focus();
}
function setPhoto(path){
	setValue("photoPath",path);
	document.all.empPhoto.src=path;
}
function loadPictures(){

	var json={};
	json.id="<bean:write name="providerForm" property="sid"/>";
	json.type="provider";

	$('#pic').html('正在加载...');
	ajaxRequest("/AjaxService?method=ImGetPictures",json2str(json),setPictures);
}
function setPictures(r){
	$('#pic').html("无图片");

	var data;
	try {data = eval(r);} catch (e) {return;}
	if (data.length == undefined || data.length < 1) {return;}
	
	var tab='<div><ul class="docs-pictures images">';
	for(var i=0;i<data.length;i++){
		tab+='<li><img data-original="'+data[i].PATH+'" src="'+data[i].PATH+'" height="84"></li>';
	}
	tab+="</ul></div>";
	$('#pic').html(tab);
	$('.images').viewer();
}
function managePicture(){
	mini.open({
		url: "/share/pictureManager.jsp?id=<bean:write name="providerForm" property="sid"/>&type=provider",
		title: "图片管理",
		width: 1000,
		height: 500,
		allowResize:false,
		ondestroy: function (action){}
	});
}
loadPictures();

function changeBrandSel(){
	var v=$('select[name="t_INFO_PROVIDER.string(SRV_TYPE)"]').val();
	if(v=="97" || v=="95") {
		//$('#brandlist').show();$('#brand4s').hide();
	}else{
		//$('#brandlist').hide();$('#brand4s').show();
	}
}
changeBrandSel();

function gpsShow(){
	var lng=getValue("t_INFO_PROVIDER.string(LONGITUDE)");
	var lat=getValue("t_INFO_PROVIDER.string(LATITUDE)");
	var w=window.open("/gps/gpsPointMonitor.jsp?LATITUDE="+lng+"&LONGITUDE="+lat,"gps","width=800px,height=600px,menubar=0,toolbar=0,directories=0,location=0,status=0,scrollbars=1");
	w.focus();
}

var bil=mini.get("BRAND_ID_LIST");
if("<bean:write name="providerForm" property="t_INFO_PROVIDER.string(MATCH_ALL)" />"=="yes"){
	$("input[name=MATCH_ALL]:eq(0)").attr("checked",'checked');$('#brandidlist').hide();
	bil.setValue("");bil.setText("");
}else{
	$("input[name=MATCH_ALL]:eq(1)").attr("checked",'checked');$('#brandidlist').show();
	bil.setValue("<bean:write name="providerForm" property="t_INFO_PROVIDER.string(BRAND_ID_LIST)" />");
	bil.setText("<bean:write name="providerForm" property="t_INFO_PROVIDER.string(BRAND_ID_LIST_NAME)" />");
}

bil=mini.get("BRAND_ID_LIST_EX");
bil.setValue("<bean:write name="providerForm" property="t_INFO_PROVIDER.string(BRAND_ID_LIST_EX)" />");
bil.setText("<bean:write name="providerForm" property="t_INFO_PROVIDER.string(BRAND_ID_LIST_EX_NAME)" />");

bil=mini.get("AUTH_BRAND");
bil.setValue("<bean:write name="providerForm" property="t_INFO_PROVIDER.string(AUTH_BRAND)" />");
bil.setText("<bean:write name="providerForm" property="t_INFO_PROVIDER.string(AUTH_BRAND_NAME)" />");

bil=mini.get("PROV_PROMISE");
bil.setValue("<bean:write name="providerForm" property="t_INFO_PROVIDER.string(PROV_PROMISE)" />");

bil=mini.get("SRV_ITEMS");
bil.setValue("<bean:write name="providerForm" property="t_INFO_PROVIDER.string(SRV_ITEMS)" />");
bil=mini.get("PROV_TYPE");
bil.setValue("<bean:write name="providerForm" property="t_INFO_PROVIDER.string(PROV_TYPE)" />");
bil=mini.get("VIDEO_VALID");
bil.setValue("<bean:write name="providerForm" property="t_INFO_PROVIDER.string(VIDEO_VALID)" />");

bil=mini.get("QM_M_LMT");
bil.setValue("<bean:write name="providerForm" property="t_INFO_PROVIDER.string(QM_M_LMT)" />");
bil=mini.get("TOP_POS");
bil.setValue("<bean:write name="providerForm" property="t_INFO_PROVIDER.string(TOP_POS)" />");

function onCloseClick(e) {var obj = e.sender;obj.setText("");obj.setValue("");}

var loaded=false,loaded2=false,loaded3=false;
function loadCarList(){
	if(loaded)return;
	loaded=true;
	var obj=mini.get("BRAND_ID_LIST");
	obj.loadList(myJson,"ID","PID");
}
function loadCarList2(){
	if(loaded2)return;
	loaded2=true;
	var obj=mini.get("BRAND_ID_LIST_EX");
	obj.loadList(myJson,"ID","PID");
}
function loadCarList3(){
	if(loaded3)return;
	loaded3=true;
	var obj=mini.get("AUTH_BRAND");
	obj.loadList(myJson,"ID","PID");
}

if(bEdit) iniArea(getValue("PROVINCE"),getValue("CITY"),getValue("AREA"));
$('select[name="t_INFO_PROVIDER.string(PROVINCE)"]').change();

function tbyCheckChange(){
	var obj=getObject("t_INFO_PROVIDER.string(TB_BY_PDU)");
	if(obj.checked){$('#tbystock').show();}
	else{$('#tbystock').hide();}
}
function viewTbyStock(){
	mini.open({
		url: "/provider/oilstock.do?b_query=true&sid="+getValue("sid"),
		title: "库存管理",
		width: 1000,
		height: 500,
		allowResize:false,
		ondestroy: function (action){}
	});
}
function beforenodeselect(e) {if (e.isLeaf == false) e.cancel = true;}
var yesno=[{ id: "yes", text: '有的' }, { id: "no", text: '没有'}];

function onActivechanged(e){
	var tab=e.tab;
	var name=tab.name;

	var grid=eval(name);
	if(!grid) return;
<%if("edit".equals(o)||"see".equals(o)){%>
	if(grid.getUrl()==""){
		if(name=="grid2"){
			var json={};
			json.SRC="bySid";json.ID="<bean:write name="providerForm" property="sid" />";
			grid.url="/AjaxService?method=GetBuUserList&data="+json2str(json);
		}else if(name=="grid1"){
			var json={};
			json.SRC="bySid";json.ID="<bean:write name="providerForm" property="sid" />";
			grid.url="/AjaxService?method=GetWpList&data="+json2str(json);
		}else if(name=="grid3"){
			grid.url="/AjaxService?method=GetCommonList&code=OrderListByProv&sid=<bean:write name="providerForm" property="sid"/>";
		}else if(name=="grid5"){
			grid.url="/AjaxService?method=GetCommonList&code=GetReviewList&TYPE=all&provSid=<bean:write name="providerForm" property="sid"/>";
		}
		grid.load();
	}
<%}%>
}

<%if("edit".equals(o)||"see".equals(o)){%>
var grid2 = mini.get("datagrid2");
var grid1 = mini.get("datagrid1");
var grid3=mini.get("datagrid3");
var grid5=mini.get("datagrid5");

function gridcellclick(e){
	var sender=e.sender;
	var f=e.field;
	var row=grid5.getSelected();

	if(f=="IMAGES_COUNT"){
		mini.open({
			url: "/share/pictureManager.jsp?id="+row.UUID+"&type=review&read=true",
			title: "图片管理",
			width: 1000,
			height: 600,
			allowResize:false
		});
	}
	grid.accept();
}
<%}%>

function openAccount(){
	mini.open({
		url: "/prov/accountlist.do?b_query=true&sid=<bean:write name="providerForm" property="sid" />",
		title: "账户流水",
		iconCls: "icon-cash", 
		width: "100%",
		height: "100%",
		allowResize:false,
		allowDrag:false});
}
function rowdblclick(e){
	var row=grid3.getSelected();
	var sid=row.SID;
	mini.open({
		url: '/delivery/list.do?b_see=true&sid='+sid,                          
		title: "订单信息",
		iconCls: "icon-node", 
		width: '100%',
		height: '100%',
		allowResize:false,
		allowDrag:false});
}
//-->
</SCRIPT>
<script src="/viewer/dist/viewer.js"></script>
<script src="/viewer/js/main.js"></script>
</html:form>	
</body>
</html:html>