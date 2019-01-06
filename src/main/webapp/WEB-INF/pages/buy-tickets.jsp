<%--
  Created by IntelliJ IDEA.
  User: oleg7
  Date: 28.12.2018
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../parts/header.jspf"%>
<fmt:setLocale value="${sessionScope.lang}"/>

<div>
    <div><fmt:message key="film.name.label" bundle="${language}"/> : ${seance.name}</div>
    <div><fmt:message key="seance.start.label" bundle="${language}"/> : <fmt:formatDate value="${seance.startTime}"  pattern="HH:mm"/></div>
    <div><fmt:message key="seance.price.label" bundle="${language}"/> : ${seance.price}</div>
    <div><fmt:message key="seance.duration.label" bundle="${language}"/> : ${seance.duration}</div>
</div>
<h3>
    <fmt:message key="choose.seats.label" bundle="${language}"/>
</h3>
<div id="hall">

    <c:forEach items="${parameters.taken}" var="row" varStatus="outerLoop">
        <div>
            <c:forEach items="${row}" var="seat" varStatus="innerLoop">
                <c:choose>

                    <c:when test="${seat == 0}">
                        <div class='free seat hint hint--top'
                             data-hint='<fmt:message key="row.label" bundle="${language}"/>: ${outerLoop.index + 1} <fmt:message key="seat.label" bundle="${language}"/>: ${innerLoop.index + 1} <fmt:message key="seance.price.label" bundle="${language}"/>: ${seance.price} <fmt:message key="currency.label" bundle="${language}"/>'
                             data-seat='${innerLoop.index + 1}' data-row='${outerLoop.index + 1}' data-price='${seance.price}'></div>
                    </c:when>
                    <c:otherwise>
                        <div class='taken seat hint hint--top' data-hint='<fmt:message key="taken.label" bundle="${language}"/>'></div>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:forEach>
</div>
<h3>
    <fmt:message key="whole.price.label" bundle="${language}"/> <span id="whole-price">0</span> <fmt:message key="currency.label" bundle="${language}"/>
</h3>
<form action="${pageContext.request.contextPath}/servlet/user/buy-tickets" id="buy-tickets-form">
    <input type="hidden" name="seance-id" value="${seance.id}">
    <input type="hidden" name="price" value="${seance.price}">
    <input type="hidden" name="user-id" value="${user.id}">
    <c:if test="${user.role == USER}">
        <button class="button"><fmt:message key="buy.tickets.label" bundle="${language}"/></button>
    </c:if>
    <c:if test="${user.role == ADMIN}">
        <a href="${pageContext.request.contextPath}/servlet/admin/delete-seance?seance-id=${seance.id}"><fmt:message key="delete.seance.label" bundle="${language}"/></a>
    </c:if>
</form>

<%@include file="../parts/footer.jspf"%>