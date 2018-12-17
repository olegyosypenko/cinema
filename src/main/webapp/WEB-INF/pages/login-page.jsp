<%--
  Created by IntelliJ IDEA.
  User: oleg7
  Date: 14.12.2018
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>

<%@include file="../parts/header.jsp"%>
    <div class="form">
        <form class="login-form" method="post" action="${pageContext.request.contextPath}/servlet/login">
            <input name="username" type="text" placeholder="username" required pattern=".{6,}" title="Six or more characters"/>
            <input name="password" type="password" placeholder="password" required pattern=".{6,}" title="Six or more characters"/>
            <button>login</button>
            <p class="message">Not registered? <a href="${pageContext.request.contextPath}/servlet/register-page">Create an account</a></p>
        </form>
    </div>
</body>
</html>
