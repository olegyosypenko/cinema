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
        </div>
    </c:forEach>
</div>
<%@include file="../parts/footer.jspf"%>