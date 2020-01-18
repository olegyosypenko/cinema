<%--
  Created by IntelliJ IDEA.
  User: oleg7
  Date: 03.01.2019
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../parts/header.jspf"%>
<div class="form">
    <form id="main-form" method="post" action="${pageContext.request.contextPath}/cinema/user/add-money">
        <input name="money" type="number" placeholder="<fmt:message key="profile.money.query" bundle="${language}"/>"/>
        <button onclick="disableButton()"><fmt:message key="add.money" bundle="${language}"/></button>
    </form>
</div>
<%@include file="../parts/footer.jspf"%>
