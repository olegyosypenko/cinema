<%--
  Created by IntelliJ IDEA.
  User: oleg7
  Date: 01.01.2019
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../parts/header.jspf"%>
<fmt:setLocale value="${sessionScope.lang}"/>

<c:forEach items="${tickets}" var="ticket">
    <div class="item">
        <div><fmt:message key="film.name.label" bundle="${language}"/> : ${ticket.seance.film.name}</div>
        <div><fmt:message key="row.label" bundle="${language}"/> : ${ticket.row}</div>
        <div><fmt:message key="seat.label" bundle="${language}"/> : ${ticket.seat}</div>
        <div><fmt:message key="film.genre.label" bundle="${language}"/> : ${ticket.seance.startTime}</div>
    </div>
</c:forEach>


<%@include file="../parts/footer.jspf"%>