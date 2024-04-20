<c:set var="now" value="<%=System.currentTimeMillis()%>"/>

<html>
<head>
    <title>Ticket Sessions Admin View</title>
</head>
<body>
  <a href="<c:url value='login'>
  <c:param name="logout"/>
  </c:url>"> Logout </a>
<h2>Sessions Information</h2>
<p>There are a total of <c:out value="${numSessions}"/> active sessions going on.</p>
<ul>
  <c:forEach items="${sessionList}" var="s">
    <li><c:out value="${s.id} - ${s.getAttribute('username')} -
    last active ${(now-s.getLastAccessedTime())/1000} seconds ago"/></li>
  </c:forEach>
</ul>
</body>
</html>
