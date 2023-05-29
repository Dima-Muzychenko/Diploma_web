<%@ page import="entity.sto" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="change.css">
    <title>Change STO</title>
</head>
<body>
<jsp:include page="header.jsp" />
<h1>Change STO</h1>
<%
    sto sto1 = (entity.sto) session.getAttribute("sto1");    // You can now access all the properties of the sto object and use them in your form

%>
<form action="ChangeServlet" method="post">
    <!-- Add input fields for the sto properties you want to modify -->
    <label for="quality">Quality:</label>
    <input type="text" id="quality" name="quality" value="<%= sto1.getQuality() %>">

    <label for="speed">Speed:</label>
    <input type="text" id="speed" name="speed" value="<%= sto1.getSpeed() %>">

    <label for="price">Price:</label>
    <input type="text" id="price" name="price" value="<%= sto1.getPrice() %>">

    <label for="serviceRange">Service Range:</label>
    <input type="text" id="serviceRange" name="service_range" value="<%= sto1.getServiceRange() %>">

    <input type="submit" value="Update STO">
</form>
</body>
</html>
