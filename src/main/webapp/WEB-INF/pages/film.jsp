<%--
  Created by IntelliJ IDEA.
  User: oleg7
  Date: 01.01.2019
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../parts/header.jspf"%>
<div class="item">
    <div><span><fmt:message key="film.name.label" bundle="${language}"/></span> : ${film.name}</div>
    <div><span><fmt:message key="film.genre.label" bundle="${language}"/></span> : ${film.genre}</div>
    <div><span><fmt:message key="film.director.label" bundle="${language}"/></span> : ${film.director}</div>
    <div><span><fmt:message key="film.rate.label" bundle="${language}"/></span> : ${film.rate}</div>
    <div><span><fmt:message key="film.description.label" bundle="${language}"/></span> : ${film.description}</div>

    <img src="${film.imageLink}" alt="">
</div>
<fmt:message key="seances.label" bundle="${language}"/>
<c:forEach items="${seances}" var="seance">
    <div><a href="${pageContext.request.contextPath}/servlet/free/buy-tickets-page/${seance.id}"><fmt:formatDate value="${seance.startTime}"  pattern="dd-MM-yyyy HH:mm"/></a></div>
</c:forEach>
<c:if test="${user.role == 'ADMIN'}">
    <div>
        <a href="${pageContext.request.contextPath}/servlet/admin/delete-film/${film.id}"><fmt:message key="delete.film.label" bundle="${language}"/></a>
    </div>
</c:if>

<%@include file="../parts/footer.jspf"%>
