<%--
  Created by IntelliJ IDEA.
  User: oleg7
  Date: 14.12.2018
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cinema</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css">
</head>
<body>
<header>
    <a href="${pageContext.request.contextPath}/servlet/home" class="logo">Cinema</a>
    <nav class="main-menu right">
        <a class="menu-item" href="${pageContext.request.contextPath}/servlet/schedule">Расписание</a>
        <a class="menu-item" href="${pageContext.request.contextPath}/servlet/films">Все фильмы</a>
        <a class="menu-item" href="${pageContext.request.contextPath}/servlet/register-page">Зарегистрироваться</a>
        <a class="menu-item" href="${pageContext.request.contextPath}/servlet/login-page">Войти</a>
        <a class="menu-item" href="${pageContext.request.contextPath}/servlet/logout">Выйти</a>
    </nav>
</header>
Welcome, ${username}!
<script src="${pageContext.request.contextPath}/resources/script.js"></script>
</body>
</html>