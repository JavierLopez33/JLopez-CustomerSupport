
<html>
<head>
    <title>Ticket Login</title>
</head>
<body>
    <h2>Login</h2>
    <h3>Enter username and password to login to ticket system.</h3>
    <c:if test="${loginFailed == true}">
        <b><c:out value="The username or password entered is not correct."></c:out> </b>
    </c:if>
    <form method="POST" action="<c:url value="/login"/>">
        Username: <input type="text" name="username"><br><br>
        Password: <input type="text" name="password"><br><br>
        <input type="submit" value="Log In">
    </form>
</body>
</html>