<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Evaluation</title>
</head>
<body>
<h1>Add Service Station</h1>
<form action="/estimation" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>

    <label for="latitude">Latitude:</label>
    <input type="text" id="latitude" name="lat" required><br>

    <label for="longitude">Longitude:</label>
    <input type="text" id="longitude" name="lon" required><br>

    <!-- Add other input fields for the required attributes -->

    <input type="submit" value="Add">
</form>
</body>
</html>
