<%--
  Created by IntelliJ IDEA.
  User: oleg7
  Date: 27.12.2018
  Time: 0:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../parts/header.jspf"%>
<fmt:setLocale value="${sessionScope.lang}"/>
<div class="links">
    <c:forEach items="${days}" var="day">
        <a href="${pageContext.request.contextPath}/servlet/schedule/<fmt:formatDate value="${day}"  pattern="yyyy-MM-dd"/>"><fmt:formatDate value="${day}"  pattern="EEE"/></a>
    </c:forEach>

</div>

<div class="films">
    <c:forEach items="${seances}" var="seance">
        <div class="film">
            <div><fmt:message key="film.name.label" bundle="${language}"/> : ${seance.name}</div>
            <div><fmt:message key="film.genre.label" bundle="${language}"/> : ${seance.price}</div>
            <div><fmt:message key="film.director.label" bundle="${language}"/> : ${seance.duration}</div>
            <div><fmt:message key="film.rate.label" bundle="${language}"/> : ${seance.columns}</div>
            <div><fmt:message key="film.rate.label" bundle="${language}"/> : <fmt:formatDate value="${seance.startTime}"  pattern="HH:mm"/></div>
        </div>
    </c:forEach>
</div>
<%@include file="../parts/footer.jspf"%>
