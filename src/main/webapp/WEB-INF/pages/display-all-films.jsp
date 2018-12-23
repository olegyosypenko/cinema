<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  Film: oleg7
  Date: 18.12.2018
  Time: 0:12
  To change this template use File | Settings | File Templates.
--%>


<%@include file="../parts/header.jsp"%>
<div class="films">
        <c:forEach items="${films}" var="film">
    <div class="film">
            <c:choose>
                <c:when test="${sessionScope.lang=='ua'}">
                    <div><fmt:message key="film.name.label" bundle="${language}"/> : ${film.name}</div>
                    <div><fmt:message key="film.genre.label" bundle="${language}"/> : ${film.genre}</div>
                    <div><fmt:message key="film.director.label" bundle="${language}"/> : ${film.director}</div>
                    <div><fmt:message key="film.rate.label" bundle="${language}"/> : ${film.rate}</div>
                </c:when>
                <c:otherwise>
                    <div><fmt:message key="film.name.label" bundle="${language}"/> : ${film.nameEN}</div>
                    <div><fmt:message key="film.genre.label" bundle="${language}"/> : ${film.genreEN}</div>
                    <div><fmt:message key="film.director.label" bundle="${language}"/> : ${film.directorEN}</div>
                    <div><fmt:message key="film.rate.label" bundle="${language}"/> : ${film.rate}</div>
                </c:otherwise>
            </c:choose>

    </div>
        </c:forEach>
</div>
</body>
</html>
