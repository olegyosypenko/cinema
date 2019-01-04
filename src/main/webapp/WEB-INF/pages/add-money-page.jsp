<%--
  Created by IntelliJ IDEA.
  User: oleg7
  Date: 03.01.2019
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../parts/header.jspf"%>
<fmt:message key="${request.error}" bundle="${language}"/>
${request.error}

<div class="form">
    <form method="post" action="${pageContext.request.contextPath}/servlet/user/add-money">
        <input name="money" type="number" placeholder="<fmt:message key="profile.money.query" bundle="${language}"/>"/>
        <button><fmt:message key="add.money" bundle="${language}"/></button>
    </form>
</div>
<%@include file="../parts/footer.jspf"%>
