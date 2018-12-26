<%--
  Created by IntelliJ IDEA.
  User: oleg7
  Date: 17.12.2018
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>

<%@include file="../parts/header.jspf"%>
<div class="form">
    <form class="login-form" method="post" id="film" action="${pageContext.request.contextPath}/servlet/create-seance">
        <input name="start-time" type="datetime-local" placeholder="<fmt:message key="seance.start.time.query" bundle="${language}"/>"/>
        <input name="end-time" type="datetime-local" placeholder="<fmt:message key="seance.end.time.query" bundle="${language}"/>"/>
        <input name="price" type="number" placeholder="<fmt:message key="seance.price.query" bundle="${language}"/>"/>
        <input name="film-id" type="number" placeholder="<fmt:message key="seance.film.id.query" bundle="${language}"/>"/>
        <button><fmt:message key="menu.create.seance" bundle="${language}"/></button>
    </form>
</div>
<%@include file="../parts/footer.jspf"%>