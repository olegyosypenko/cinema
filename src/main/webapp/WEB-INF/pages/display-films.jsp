<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  FilmDto: oleg7
  Date: 18.12.2018
  Time: 0:12
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../parts/header.jspf"%>
<div class="films">
    <c:forEach items="${films}" var="film">
        <div class="film">
            <div><fmt:message key="film.name.label" bundle="${language}"/> : ${film.name}</div>
            <div><fmt:message key="film.genre.label" bundle="${language}"/> : ${film.genre}</div>
            <div><fmt:message key="film.director.label" bundle="${language}"/> : ${film.director}</div>
            <div><fmt:message key="film.rate.label" bundle="${language}"/> : ${film.rate}</div>
            <a href="${pageContext.request.contextPath}/servlet/free/film/${film.id}"><fmt:message key="show.more.label" bundle="${language}"/></a>
        </div>
    </c:forEach>
    <div class="pagination">

        <c:choose>
            <c:when test="${previousIndex != currentIndex}">
                <a class="active" href="${pageContext.request.contextPath}/servlet/free/films/${previousIndex}"><<</a>
            </c:when>
            <c:otherwise>
                <span class="disabled"><<</span>
            </c:otherwise>
        </c:choose>

        <c:forEach items="${indexes}" var="index">
            <c:choose>
                <c:when test="${index != currentIndex}">
                    <a class="active" href="${pageContext.request.contextPath}/servlet/free/films/${index}">${index}</a>
                </c:when>
                <c:otherwise>
                    <span class="current">${index}</span>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:choose>
            <c:when test="${nextIndex != currentIndex}">
                <a class="active" href="${pageContext.request.contextPath}/servlet/free/films/${nextIndex}">>></a>
            </c:when>
            <c:otherwise>
                <span class="disabled">>></span>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<%@include file="../parts/footer.jspf"%>