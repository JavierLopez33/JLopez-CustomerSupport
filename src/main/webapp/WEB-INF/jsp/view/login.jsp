
<html>
<head>
    <title>Ticket Login</title>
</head>
<body>
    <h2>Login</h2>
    <h3>Enter username and password to login to ticket system.</h3>
    <c:if test="${loginFailed == true}">
        <b><c:out value="The username or password you enteres are not correct, Please try again."></c:out></b><br>
    </c:if>
    <form:form method="POST" action="login" modelAttribute="loginForm">
        <form:label path="username">Username:&nbsp;</form:label>
        <form:input path="username"/><br><br>
        <form:label path="password">Password:&nbsp;</form:label>
        <form:password path="password"/><br><br>
        <input type="submit" value="Log In">
    </form:form>

    </body>
</html>