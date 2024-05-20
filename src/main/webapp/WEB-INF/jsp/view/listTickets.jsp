<html>
<head>
    <title>Ticket List</title>
</head>
<body>
    <a href="<c:url value='/logout'/>">Logout</a>
    <h2>Ticket List</h2>
    <a href="<c:url value='/ticket/create'/>">Create Post </a><br><br>
    <c:choose>
        <c:when test="${ticketDatabase.size()==0}">
            <p>There are no tickets yet...</p>
        </c:when>
        <c:otherwise>
            <c:forEach var="ticket" items="${ticketDatabase}">
                Ticket#: <c:out value="${ticket.id} - "></c:out>
                <a href="<c:url value='/ticket/view/${ticket.id}'/>">
                    <c:out value="${ticket.subject}"></c:out></a><br>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</body>
</html>
