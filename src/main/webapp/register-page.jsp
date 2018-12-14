<%--
  Created by IntelliJ IDEA.
  User: oleg7
  Date: 14.12.2018
  Time: 16:03
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
<div class="form">
    <form class="register-form" method="post" action="${pageContext.request.contextPath}/servlet/register">
        <input name="first-name" type="text" placeholder="first name"/>
        <input name="last-name" type="text" placeholder="last name"/>
        <input name="username" type="text" placeholder="username" required pattern=".{6,}" title="Six or more characters"/>
        <input name="password" type="password" placeholder="password" required pattern=".{6,}" title="Six or more characters"/>
        <button>create</button>
        <p class="message">Already registered? <a href="${pageContext.request.contextPath}/servlet/login-page">Sign In</a></p>
    </form>
</div>
</body>
</html>
