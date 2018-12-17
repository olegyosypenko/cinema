<%--
  Created by IntelliJ IDEA.
  User: oleg7
  Date: 17.12.2018
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>

<html>
<head>
    <title>Cinema</title>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="labels" var="language"/>
    <fmt:requestEncoding value="UTF-8" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css">
</head>
<body>
<header>
    <a href="${pageContext.request.contextPath}/servlet/home" class="logo">Cinema</a>
    <nav class="main-menu right">
        <a class="menu-item" href="${pageContext.request.contextPath}/servlet/create-film-page"><fmt:message key="menu.create" bundle="${language}"/></a>
        <a class="menu-item" href="${pageContext.request.contextPath}/servlet/schedule"><fmt:message key="menu.schedule" bundle="${language}"/></a>
        <a class="menu-item" href="${pageContext.request.contextPath}/servlet/films"><fmt:message key="menu.all.films" bundle="${language}"/></a>
        <a class="menu-item" href="${pageContext.request.contextPath}/servlet/register-page"><fmt:message key="menu.signin.label" bundle="${language}"/></a>
        <a class="menu-item" href="${pageContext.request.contextPath}/servlet/login-page"><fmt:message key="menu.login.label" bundle="${language}"/></a>
        <a class="menu-item" href="${pageContext.request.contextPath}/servlet/logout"><fmt:message key="menu.logout.label" bundle="${language}"/></a>
    </nav>
</header>
