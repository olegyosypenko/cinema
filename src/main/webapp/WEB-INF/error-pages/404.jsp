<%--
  Created by IntelliJ IDEA.
  User: oleg7
  Date: 04.01.2019
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<%@include file="../parts/header.jspf"%>



<div class='error-message'>404</div>
<div><fmt:message key="page.not.found.label" bundle="${language}"/></div>
<a href='${pageContext.request.contextPath}/servlet/free/home'><fmt:message key="home.page.label" bundle="${language}"/></a>

<%@include file="../parts/footer.jspf"%>