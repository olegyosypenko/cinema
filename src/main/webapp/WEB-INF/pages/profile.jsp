<%--
  Created by IntelliJ IDEA.
  User: oleg7
  Date: 27.12.2018
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>

<%@include file="../parts/header.jspf"%>
<div class="user">
    <div><fmt:message key="user.firstname.label" bundle="${language}"/> : ${user.firstName}</div>
    <div><fmt:message key="user.lastname.label" bundle="${language}"/> : ${user.lastName}</div>
    <div><fmt:message key="user.username.label" bundle="${language}"/> : ${user.username}</div>
    <div><fmt:message key="user.money.label" bundle="${language}"/> : ${user.money} <fmt:message key="currency.label" bundle="${language}"/></div>
    <a href="${pageContext.request.contextPath}/cinema/user/add-money-page"><fmt:message key="add.money" bundle="${language}"/></a>
</div>
<%@include file="../parts/footer.jspf"%>