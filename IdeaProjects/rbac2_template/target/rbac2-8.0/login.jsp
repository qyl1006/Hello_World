<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	session.invalidate();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>叩丁狼WMS（演示版）</title>
<link href="/style/login_css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
</head>
<body>
	<div id="login_center">
		<div id="login_area">
			<div id="login_box">
				<div id="login_form">
					<form id="loginForm" action="/login.do" method="post">
						<div id="login_tip">
							<span id="login_err" class="sty_txt2">${msg}</span>
						</div>
						<div>
							用户名：<input type="text" name="username" class="username" id="name"
								value="admin">
						</div>
						<div>
							密&nbsp;&nbsp;码：<input type="password" name="password"
								class="pwd" id="pwd" value="1">
						</div>
						<div id="btn_area">
							<input type="submit" class="login_btn" value="登  录"> 
							<input type="reset" class="login_btn" value="重 置">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>