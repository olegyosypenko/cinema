<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 21.07.2019
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>

<html ng-app="cinema">
<head>
    <title>Cinema</title>
    <fmt:setLocale value="uk"/>
    <fmt:setBundle basename="labels" var="language"/>
    <fmt:requestEncoding value="UTF-8" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css">
    <script src="${pageContext.request.contextPath}/resources/angular.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app.js"></script>
</head>
<body>
    <%@include file="WEB-INF/parts/guest-navbar.jspf"%>
    <div ng-controller="filmCtrl" class="main-content">
        <h1><fmt:message key="home.page.label" bundle="${language}"/></h1>
        <h2><fmt:message key="most.popular.films" bundle="${language}"/></h2>
        <div ng-repeat="film in films">
            <div><span><fmt:message key="film.name.label" bundle="${language}"/></span> : {{film.name}}</div>
            <div><span><fmt:message key="film.genre.label" bundle="${language}"/></span> : {{film.genre}}</div>
            <div><span><fmt:message key="film.director.label" bundle="${language}"/></span> : {{film.director}}</div>
            <div><span><fmt:message key="film.rate.label" bundle="${language}"/></span> : {{film.rate}}</div>
            <a href="${pageContext.request.contextPath}/cinema/free/film/{{film.id}}"><fmt:message key="show.more.label" bundle="${language}"/></a>
        </div>
    </div>
</body>
</html>
