<%--
  Created by IntelliJ IDEA.
  User: oleg7
  Date: 17.12.2018
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>

<%@include file="../parts/header.jsp"%>
<div class="form">
    <form class="login-form" method="post" id="film" action="${pageContext.request.contextPath}/servlet/login">
        <input name="name" type="text" placeholder="<fmt:message key="film.name.query" bundle="${language}"/>"/>
        <input name="nameEN" type="text" placeholder="<fmt:message key="film.nameEN.query" bundle="${language}"/>"/>
        <input name="genre" type="text" placeholder="<fmt:message key="film.genre.query" bundle="${language}"/>"/>
        <input name="genreEN" type="text" placeholder="<fmt:message key="film.genreEN.query" bundle="${language}"/>"/>
        <input name="director" type="text" placeholder="<fmt:message key="film.director.query" bundle="${language}"/>"/>
        <input name="directorEN" type="text" placeholder="<fmt:message key="film.directorEN.query" bundle="${language}"/>"/>
        <input name="rate" type="number" step="0.01" placeholder="<fmt:message key="film.rate.query" bundle="${language}"/>"/>
        <textarea rows="4" cols="50" name="description"><fmt:message key="film.description.query" bundle="${language}"/></textarea>
        <textarea rows="4" cols="50" name="descriptionEN"><fmt:message key="film.descriptionEN.query" bundle="${language}"/></textarea>
        <button>login</button>
        <p class="message">Not registered? <a href="${pageContext.request.contextPath}/servlet/register-page">Create an account</a></p>
    </form>
</div>
</body>
</html>
