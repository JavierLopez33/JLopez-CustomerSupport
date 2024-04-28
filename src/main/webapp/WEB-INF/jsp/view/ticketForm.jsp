
<html>
<head>
    <title>Create a new Ticket</title>
</head>
<body>
    <a href="<c:url value='/logout'/>">Logout</a>
    <h1>Ticket Submission Form</h1>

    <form:form method="POST" action="create" modelAttribute="ticket" enctype="multipart/form-data">
        <form:label path="customerName">Name:</form:label><br>
        <form:input path="customerName"/><br><br>
        <form:label path="subject">Subject:</form:label><br>
        <form:input path="subject"/><br><br>
        <form:label path="body">Ticket Information:</form:label><br>
        <form:textarea path="body" rows="10" cols="100"/><br><br>
        <b>Attachment</b><br><br>
        <form:input path="attachment" type="file"/><br><br>
        <input type="submit" value="Submit">
    </form:form>
</body>
</html>
