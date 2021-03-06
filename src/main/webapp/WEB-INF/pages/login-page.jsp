<%--
  Created by IntelliJ IDEA.
  User: oleg7
  Date: 14.12.2018
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>

<%@include file="../parts/header.jspf"%>
    <div class="form">
        <form id="main-form" class="login-form" method="post" action="${pageContext.request.contextPath}/cinema/guest/login">
            <input name="username" type="text" placeholder="<fmt:message key="user.username.query" bundle="${language}"/>" required pattern=".{4,}" title="Four or more characters"/>
            <input name="password" type="password" placeholder="<fmt:message key="user.password.query" bundle="${language}"/>" required pattern=".{4,}" title="Four or more characters"/>
            <button onclick="disableButton()"><fmt:message key="login.label" bundle="${language}"/></button>
            <p class="message"><fmt:message key="not.registered.question" bundle="${language}"/> <a href="${pageContext.request.contextPath}/cinema/register-page"><fmt:message key="create.account.label" bundle="${language}"/></a></p>
        </form>
    </div>
<%@include file="../parts/footer.jspf"%>