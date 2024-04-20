<html>
<head>
    <title>Ticket List</title>
</head>
    <a href="<c:url value='/login'>
        <c:param name='logout'/>
    </c:url>">Logout</a>
<body>
    <h2>Ticket List</h2>
    <a href="<c:url value='/ticket'>
        <c:param name='action' value='createTicket'/>
    </c:url>">Create Post </a><br><br>
    <c:choose>
        <c:when test="${ticketDatabase.size()==0}">
            <p>There are no tickets yet...</p>
        </c:when>
        <c:otherwise>
            <c:forEach var="ticket" items="${ticketDatabase}">
                Ticket#: <c:out value="${ticket.key} - "></c:out>
                <a href="<c:url value='/ticket'>
                <c:param name='action' value='view' />
                <c:param name='ticketID' value='${ticket.key}'/>
            </c:url>"><c:out value="${ticket.value.subject}"></c:out></a><br>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</body>
</html>
