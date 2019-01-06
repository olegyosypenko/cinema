<%--
  Created by IntelliJ IDEA.
  User: oleg7
  Date: 14.12.2018
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>

<%@include file="../parts/header.jspf"%>
<fmt:setLocale value="${sessionScope.lang}"/>
<div class="form">
    <form id="main-form" class="register-form" method="post" action="${pageContext.request.contextPath}/servlet/guest/register">
        <input name="first-name" type="text" placeholder="<fmt:message key="user.firstname.query" bundle="${language}"/>"/>
        <input name="first-name-en" type="text" placeholder="<fmt:message key="user.firstnameEN.query" bundle="${language}"/>"/>
        <input name="last-name" type="text" placeholder="<fmt:message key="user.lastname.query" bundle="${language}"/>"/>
        <input name="last-name-en" type="text" placeholder="<fmt:message key="user.lastnameEN.query" bundle="${language}"/>"/>
        <input name="username" type="text" placeholder="<fmt:message key="user.username.query" bundle="${language}"/>"/>
        <input name="password" type="password" placeholder="<fmt:message key="user.password.query" bundle="${language}"/>"/>
        <button onclick="disableButton()"><fmt:message key="create.label" bundle="${language}"/></button>
        <p class="message"><fmt:message key="already.registered.question" bundle="${language}"/> <a href="${pageContext.request.contextPath}/servlet/login-page"><fmt:message key="menu.login.label" bundle="${language}"/></a></p>
    </form>
</div>
<%@include file="../parts/footer.jspf"%>