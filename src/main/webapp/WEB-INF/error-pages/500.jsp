<%--
  Created by IntelliJ IDEA.
  User: oleg7
  Date: 04.01.2019
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<%@include file="../parts/header.jspf"%>


<div class='error-message'>500</div>
<div>
    <fmt:message key="internal.server.error.label" bundle="${language}"/>
</div>


<a href='${pageContext.request.contextPath}/cinema/free/home'><fmt:message key="home.page.label" bundle="${language}"/></a>

<%@include file="../parts/footer.jspf"%>