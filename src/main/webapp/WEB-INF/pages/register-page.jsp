<%--
  Created by IntelliJ IDEA.
  User: oleg7
  Date: 14.12.2018
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>

<%@include file="../parts/header.jsp"%>
<div class="form">
    <form class="register-form" method="post" action="${pageContext.request.contextPath}/servlet/register">
        <input name="first-name" type="text" placeholder="first name"/>
        <input name="last-name" type="text" placeholder="last name"/>
        <input name="username" type="text" placeholder="username" required pattern=".{4,}" title="Four or more characters"/>
        <input name="password" type="password" placeholder="password" required pattern=".{4,}" title="Four or more characters"/>
        <button>create</button>
        <p class="message">Already registered? <a href="${pageContext.request.contextPath}/servlet/login-page">Sign In</a></p>
    </form>
</div>
</body>
</html>
