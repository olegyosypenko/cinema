<%--
  Created by IntelliJ IDEA.
  User: oleg7
  Date: 17.12.2018
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>

<%@include file="../parts/header.jspf"%>
<div class="form">
    <form class="login-form" method="post" id="seance-form" action="${pageContext.request.contextPath}/servlet/create-seance">
        <input name="start-time" type="datetime-local" placeholder="<fmt:message key="seance.start.time.query" bundle="${language}"/>"/>
        <input name="price" type="number" placeholder="<fmt:message key="seance.price.query" bundle="${language}"/>"/>
        <select name="film-id" form="seance-form">
            <c:forEach items="${films}" var="film">
                <option value="${film.id}">${film.name}</option>
            </c:forEach>
        </select>
        <button><fmt:message key="menu.create.seance" bundle="${language}"/></button>
    </form>
</div>
<%@include file="../parts/footer.jspf"%>