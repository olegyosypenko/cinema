<%--
  Created by IntelliJ IDEA.
  User: oleg7
  Date: 01.01.2019
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../parts/header.jspf"%>
<div class="film">
    <div><fmt:message key="film.name.label" bundle="${language}"/> : ${film.name}</div>
    <div><fmt:message key="film.genre.label" bundle="${language}"/> : ${film.genre}</div>
    <div><fmt:message key="film.director.label" bundle="${language}"/> : ${film.director}</div>
    <div><fmt:message key="film.rate.label" bundle="${language}"/> : ${film.rate}</div>
    <div><fmt:message key="film.rate.label" bundle="${language}"/> : ${film.description}</div>
    <img src="${film.imageLink}" alt="">
</div>
<fmt:message key="seances.label" bundle="${language}"/>
<c:forEach items="${seances}" var="seance">
    <div><a href="${pageContext.request.contextPath}/servlet/free/buy-tickets-page/${seance.id}">${seance.start-time}</a></div>
</c:forEach>
<%@include file="../parts/footer.jspf"%>
