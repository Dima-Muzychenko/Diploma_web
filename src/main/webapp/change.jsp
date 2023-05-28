<%@ page import="entity.sto" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<html>
<head>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }

        h1 {
            color: #333;
            text-align: center;
        }

        form {
            width: 400px;
            margin: 20px auto;
            background-color: #fff;
            border: 1px solid #ddd;
            padding: 20px;
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input[type="text"], textarea {
            width: 100%;
            padding: 5px;
            margin-bottom: 10px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: #fff;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
        }
    </style>
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
    <label for="quality">Name:</label>
    <input type="text" id="quality" name="quality" value="<%= sto1.getQuality() %>">

    <label for="speed">Name:</label>
    <input type="text" id="speed" name="speed" value="<%= sto1.getSpeed() %>">

    <label for="price">Name:</label>
    <input type="text" id="price" name="price" value="<%= sto1.getPrice() %>">

    <label for="serviceRange">Name:</label>
    <input type="text" id="serviceRange" name="service_range" value="<%= sto1.getServiceRange() %>">

    <input type="submit" value="Update STO">
</form>
</body>
</html>
