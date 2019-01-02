<%--
  Created by IntelliJ IDEA.
  User: oleg7
  Date: 17.12.2018
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>

<%@include file="../parts/header.jspf"%>

<div class="form">
    <form class="login-form" method="post" id="film" action="${pageContext.request.contextPath}/servlet/admin/create-film">
        <input name="name" type="text" placeholder="<fmt:message key="film.name.query" bundle="${language}"/>"/>
        <input name="name-en" type="text" placeholder="<fmt:message key="film.nameEN.query" bundle="${language}"/>"/>
        <input name="genre" type="text" placeholder="<fmt:message key="film.genre.query" bundle="${language}"/>"/>
        <input name="genre-en" type="text" placeholder="<fmt:message key="film.genreEN.query" bundle="${language}"/>"/>
        <input name="director" type="text" placeholder="<fmt:message key="film.director.query" bundle="${language}"/>"/>
        <input name="director-en" type="text" placeholder="<fmt:message key="film.directorEN.query" bundle="${language}"/>"/>
        <input name="image-link" type="text" placeholder="<fmt:message key="film.imagelink.query" bundle="${language}"/>"/>
        <input name="image-link-en" type="text" placeholder="<fmt:message key="film.imagelinkEN.query" bundle="${language}"/>"/>
        <input name="rate" type="number" step="0.01" placeholder="<fmt:message key="film.rate.query" bundle="${language}"/>"/>
        <textarea rows="4" cols="50" name="description"><fmt:message key="film.description.query" bundle="${language}"/></textarea>
        <textarea rows="4" cols="50" name="description-en"><fmt:message key="film.descriptionEN.query" bundle="${language}"/></textarea>
        <button><fmt:message key="menu.create.film" bundle="${language}"/></button>
    </form>
</div>
<%@include file="../parts/footer.jspf"%>