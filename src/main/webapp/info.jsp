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

        table {
            width: 1200px;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            border: 1px solid #ddd;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f5f5f5;
            font-weight: bold;
        }
    </style>
    <title>STO Information</title>
</head>
<body>
<jsp:include page="header.jsp" />
<h1>STO Information</h1>
<%
    sto sto1 = (entity.sto) request.getAttribute("sto");
    if (sto1 != null) {
%>
<table>
    <tr>
        <th>Name:</th>
        <td><%= sto1.getName() %></td>
    </tr>
    <tr>
        <th>Result:</th>
        <td><%= sto1.getEvaluation()+". "+sto1.getResultValue() %></td>
    </tr>
    <tr>
        <th>Address:</th>
        <td><%= sto1.getAddress() %></td>
    </tr>
    <tr>
        <th>Latitude:</th>
        <td><%= sto1.getLat() %></td>
    </tr>
    <tr>
        <th>Longitude:</th>
        <td><%= sto1.getLon() %></td>
    </tr>
</table>
<form action="change.jsp" method="post">
    <%-- Store the sto object in the session --%>
    <% session.setAttribute("sto1", sto1); %>
    <input type="submit" value="Change STO">
</form>
<%
} else {
%>
<table>
    <tr>
        <td>No such sto:</td>
    </tr>
</table>
<%
    }
%>
</body>
</html>
