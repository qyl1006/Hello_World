<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Qyuelin
  Date: 2018/4/29
  Time: 1:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试Shiro</title>
</head>
<body>
<shiro:notAuthenticated>
    <a>亲,请登陆!</a>
    <a href="/login.jsp">登陆</a>
</shiro:notAuthenticated>
<shiro:authenticated>
    <span>你好!欢迎回来!</span>
    <shiro:principal property="name"/>
</shiro:authenticated>
</body>
</html>
