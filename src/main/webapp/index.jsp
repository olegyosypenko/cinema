<%--
Created by IntelliJ IDEA.
User: oleg7
Date: 10.12.2018
Time: 16:21
To change this template use File | Settings | File Templates.
--%>
<%@include file="WEB-INF/parts/header.jsp"%>
<form action="${pageContext.request.contextPath}/servlet/lang" class="form">
    <input type="hidden" name="lang" value="<fmt:message key="change.lang.code" bundle="${language}"/>">
    <fmt:message key="change.lang.question" bundle="${language}"/>
    <br>
    <button class="button"><fmt:message key="change.lang" bundle="${language}"/></button>
</form>
</body>
</html>