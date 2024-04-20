
<html>
<head>
    <title>Create a new Ticket</title>
</head>
<body>
    <a href="<c:url value='/login'>
        <c:param name='logout'/>
    </c:url>">Logout</a>
    <h1>Ticket Submission Form</h1>
    <form method="POST" action="ticket"  enctype="multipart/form-data">
        <input type="hidden" name="action" value="create">
        Name:<br>
        <input type="text" name="customerName"><br><br>
        Subject:<br>
        <input type="text" name="subject" ><br><br>
        Ticket Information:<br>
        <textarea name="body" rows="10" cols="100"></textarea><br><br>
        Attachments:<br>
        <input type="file" name="attachment1" multiple><br><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
