<%--
Created by IntelliJ IDEA.
User: oleg7
Date: 10.12.2018
Time: 16:21
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="WEB-INF/parts/header.jspf"%>
<div ng-controller="filmCtrl">
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
<%@include file="WEB-INF/parts/footer.jspf"%>
