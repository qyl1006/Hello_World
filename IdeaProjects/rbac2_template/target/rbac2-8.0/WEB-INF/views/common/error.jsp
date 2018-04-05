﻿<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>叩丁狼PSS（演示版）</title>
<link href="/style/error_css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<s:debug></s:debug>
	<div id="login_center">
		<div id="login_area">
			<div id="login_box">
				<div id="login_form">
					<H2>出错啦！</H2>
					<span>执行过程中发生了异常：</span>
					<span class="error">${ex.message}</span>
					<span>请联系管理员解决！</span>
					<span>联系电话：020-XXXXXXXX</span>
					<span>联系邮件：xxx@xxx.com</span>
					<span>&copy;广州狼码教育科技有限公司</span>
				</div>
			</div>
		</div>
	</div>
</body>
</html>