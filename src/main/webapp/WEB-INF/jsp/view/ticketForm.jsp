
<html>
<head>
    <title>Create a new Ticket</title>
</head>
<body>
    <h1>Ticket Submission Form</h1>
    <form method="POST" action="ticket"  enctype="multipart/form-data">
        <input type="hidden" name="action" value="create">
        <label for="customerName">Name:</label><br>
        <input type="text" name="customerName" required><br><br>
        <label for="subject">Subject:</label><br>
        <input type="text" name="subject" required><br><br>
        <label for="body">Ticket Information:</label><br>
        <textarea id="body" name="body" rows="10" cols="100" required></textarea><br><br>
        <label for="attachment">Attachments:</label><br>
        <input type="file" name="attachment1" multiple><br><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
