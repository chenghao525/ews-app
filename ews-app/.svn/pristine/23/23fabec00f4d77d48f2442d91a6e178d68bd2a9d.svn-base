<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%
	String errorInfo="";
	String vn=(String)request.getSession().getAttribute(com.corp.frame.action.Constant.EXCEPTION);

	if(vn!=null && !"".equals(vn)){
		errorInfo=vn;
	}else{
		errorInfo=request.getParameter("error");
		if(errorInfo==null)errorInfo="";
		if(errorInfo.equals("1")){
			errorInfo="用户名或密码错误";
		}else if(errorInfo.equals("100")){
			errorInfo="尝试登录超过3次，锁定10分钟";
		}
	}
	session.invalidate();
%>
<html:html>
<head>
<link rel="shortcut icon" href="/favicon.ico" />
<link rel="bookmark" href="/favicon.ico" />
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
	<link href="/login/index.css" rel="stylesheet" type="text/css" />
	<title>EWSmis</title>
	<script type="text/javascript">
        var funPlaceholder = function(element) {
            var placeholder = '';
            if (element && !("placeholder" in document.createElement("input")) && (placeholder = element.getAttribute("placeholder"))) {
                var idLabel = element.id ;
                if (!idLabel) {
                    idLabel = "placeholder_" + new Date().getTime();
                    element.id = idLabel;
                }

                var eleLabel = document.createElement("label");
                eleLabel.htmlFor = idLabel;
                eleLabel.style.position = "absolute";
                eleLabel.style.bottom = "10px";
                eleLabel.style.left = "5px";
                eleLabel.style.color = "#fff";
                eleLabel.style.cursor = "text";
                element.parentNode.insertBefore(eleLabel, element);
                element.onfocus = function() {
                    eleLabel.innerHTML = "";
                };
                element.onblur = function() {
                    if (this.value === "") {
                        eleLabel.innerHTML = placeholder;
                    }
                };
                if (element.value === "") {
                    eleLabel.innerHTML = placeholder;
                }
            }
        };
        funPlaceholder(document.getElementById("empid"));
        funPlaceholder(document.getElementById("emppwd"));
        funPlaceholder(document.getElementById("validatenum"));

	</script>
</head>
<body style="overflow:hidden">
<html:form action="/login/checklogin" method="post" style="height: 100%;">
	<div class="content">
		<div class="top log w100">
			<img src="/images/ews-logo.png"/>
		</div>
		<div class="cont w100 dis_cont">
			<div class="mar_lf">
				<div class="cont_top">
					<span class="cor_ed784c ft_32 ft_wei">用户登录</span>
					<span class="cor_ff ft_22 ft_wei">login</span>
				</div>
				<%--<div style="margin-bottom:5px; color:#F00; text-align: center">&nbsp;<%=errorInfo%></div>--%>
				<div class="form">
					<div class="pos_re">
						<input type="text" id="empid" name="empid" class="input_bor w100 user_id" placeholder="请输入您的用户名" />
					</div>
					<div class="pos_re">
						<input type="password" id="emppwd" name="emppwd" class="input_bor w100 user_pass" placeholder="密码6~18位" />
					</div>
					<div class="input_ym pos_re clearfix">
						<input type="text" id="validatenum" name="validatenum" class="input_bor btn_ym fl_lf" placeholder="请输入验证码" />
						<!--<div class="input_bor btn_ym">验证码</div>-->
						<div class="bor_btn fl_lf">
							<img id="valnum" name="valnum" src="/images/ym.jpg" style="cursor:hand;vertical-align:bottom" onclick="show_validate_num()" title="点击我刷新"/>
						</div>
						<div class="btn_text fl_rt" onclick="show_validate_num()">换一张</div>
					</div>
					<div class="login clearfix" style="position: relative">
						<%--<div class="btn_login fl_lf">登录</div>--%>
						<html:submit StyleClass="btn_login fl_lf" style="color: #fff;font-size: 15px; border: none" property="submit" value="登 录" onclick="return check();"/>
						<div class="check fl_lf" onclick="recordPass()">
							<div class="clearfix">
								<div class="img_1 fl_lf">
									<img id="duigou" src="/images/dui.png" alt="" />
								</div>
								<span class="fl_lf">记住密码</span>
							</div>
						</div>
						<div style="color:red;position:  absolute;top: 0;left: 0;"><%=errorInfo%></div>
					</div>
				</div>
			</div>
		</div>
		<div class="fooder">
			<p>推荐使用   谷歌浏览器</p>
			<p class="p">服务供应商：<a href="http://www.wedrive.com.cn" target="_blank">上海聚车信息有限公司</a></p>
		</div>
	</div>
</html:form> 
</body>
</html:html>
<SCRIPT LANGUAGE="JavaScript">
	function init(){
		var user = localStorage.getItem("user");
		var pass = localStorage.getItem("pass");
		var flag = localStorage.getItem("record_flag");
		var gouNode = document.getElementById("duigou");
		if (flag == 'yes') {
			var userNode = document.getElementsByClassName("user_id");
			var passNode = document.getElementsByClassName("user_pass");

			gouNode.src = '/images/dui.png';
			userNode[0].value = user;
			passNode[0].value = pass;
		} else {
            gouNode.src = '/images/dui_1.png';
		}
		show_validate_num();
	}
	function show_validate_num() {
		document.getElementById("valnum").src = '/validatenumber.jpg?' + (new Date()).getTime();
	}
	function check() {
		var valnum = document.all.validatenum;
		if (valnum.value == '') {
			alert('请输入验证码！');
			valnum.select();
			valnum.focus();
			return false;
		}
		addLocalStorage();
		return true;
	}
	init();

	function addLocalStorage() {
        var gouNode = document.getElementById("duigou");
        var src = gouNode.src;
        if (src.indexOf("dui.png") >= 0) {
            var userNode = document.getElementsByClassName("user_id");
            var passNode = document.getElementsByClassName("user_pass");
            var user = userNode[0].value;
            var pass = passNode[0].value;
            if (user && user.length > 0 &&
                pass && pass.length > 0) {
                localStorage.setItem("record_flag", "yes");
                localStorage.setItem("user", user);
                localStorage.setItem("pass", pass);
            }
        } else {
            localStorage.setItem("record_flag", "no");
            localStorage.setItem("user", "");
            localStorage.setItem("pass", "");
        }
    }

	//记住密码
	function recordPass() {
		var gouNode = document.getElementById("duigou");
		var src = gouNode.src;
		if (src.indexOf("dui_1.png") >= 0) {
            gouNode.src = '/images/dui.png';
		} else {
            gouNode.src = '/images/dui_1.png';
		}
	}
</SCRIPT>
