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

<h2>
    <fmt:message key="your.tickets" bundle="${language}"/>:
</h2>

<div class="clearfix">
    <c:forEach items="${tickets}" var="ticket">
        <div class="ticket">
            <div><span><fmt:message key="film.name.label" bundle="${language}"/></span> : ${ticket.seance.film.name}</div>
            <div><span><fmt:message key="row.label" bundle="${language}"/></span> : ${ticket.row}</div>
            <div><span><fmt:message key="seat.label" bundle="${language}"/></span> : ${ticket.seat}</div>
            <div><span><fmt:message key="seance.start.label" bundle="${language}"/></span> : <fmt:formatDate value="${ticket.seance.startTime}"  pattern="dd-MM-yyyy HH:mm"/></div>
        </div>
    </c:forEach>
</div>


<%@include file="../parts/footer.jspf"%>