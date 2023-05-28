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

    // Example: Display the sto name
    String stoName = sto1.getName();
%>
<form action="updateSto" method="post">
    <!-- Add input fields for the sto properties you want to modify -->
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="<%= stoName %>">

    <!-- Add more fields for other STO properties that you want to modify -->

    <input type="submit" value="Update STO">
</form>
</body>
</html>
