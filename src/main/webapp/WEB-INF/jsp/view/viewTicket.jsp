<html>
<head>
    <title>View Ticket# <c:out value="${ticketID}"/></title>
</head>
<body>
    <a href="<c:url value='/login'>
        <c:param name='logout'/>
    </c:url>">Logout</a>
    <h2>Ticket Information</h2>
    <h3>Ticket#<c:out value="${ticketID}"/>: <c:out value="${ticket.subject}"/></h3>
    <h3>Thank you <c:out value="${ticket.customerName}"/> for submitting a ticket.</h3>
    <p>Subject: <c:out value="${ticket.subject}"/></p>
    <p>Issue: <c:out value="${ticket.body}"/></p>
    <c:if test="${ticket.hasAttachment()}">
        <a href="<c:url value="/ticket">
            <c:param name='action' value='download' />
            <c:param name='ticketID' value='${ticketID}'/>
            <c:param name="attachment" value='${ticket.attachment.name}'/>
        </c:url>"><c:out value="${ticket.attachment.name}"></c:out></a>
    </c:if>
    <br><a href="ticket"> Return to ticket list</a>
</body>
</html>
