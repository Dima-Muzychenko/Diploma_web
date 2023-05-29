<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Evaluation</title>
    <link rel="stylesheet" type="text/css" href="WatchService.css">

</head>
<body>
<jsp:include page="header.jsp" />
<div class="container">
    <h1>Watch car service</h1>
    <form action="/watch" method="post">

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>


        <label for="latitude">Latitude:</label>
        <input type="text" id="latitude" name="lat" required>


        <label for="longitude">Longitude:</label>
        <input type="text" id="longitude" name="lon" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <input type="submit" value="Watch">
    </form>
</div>

</body>
</html>