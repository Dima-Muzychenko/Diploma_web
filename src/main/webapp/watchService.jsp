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

<%--        param is an implicit object that allows you to access request parameters--%>
<%--        For example, if you have an input field with the name "name" in your form, you can access its value using param.name in your JSP code--%>
<%--        By using param, you can access the submitted form data in your JSP code and use it--%>
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required value="${param.name}"><%--Якщо ми ввели неправильні дані,
        то наше введені дані залишаються в формі, а не стираються (щоб заново всі оля не заповнювати)--%>


        <label for="latitude">Latitude:</label>
        <input type="text" id="latitude" name="lat" required value="${param.lat}">


        <label for="longitude">Longitude:</label>
        <input type="text" id="longitude" name="lon" required value="${param.lon}">

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <%String error = (String) request.getAttribute("errorMessage");
        if(error!=null) {
        %>
        <span style="color: red;"><%= request.getAttribute("errorMessage") %></span>
        <br>
        <%
        }
        %>


        <input type="submit" value="Watch">
    </form>
</div>

</body>
</html>