<%--
  Created by IntelliJ IDEA.
  User: oleg7
  Date: 27.12.2018
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>

<%@include file="../parts/header.jspf"%>
<div class="form">
    <form method="post" action="${pageContext.request.contextPath}/servlet/add-money">
        <input name="money" type="number" placeholder="<fmt:message key="profile.money.query" bundle="${language}"/>"/>
        <button><fmt:message key="add.money" bundle="${language}"/></button>
    </form>
</div>
<%@include file="../parts/footer.jspf"%>