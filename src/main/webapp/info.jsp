<%@ page import="entity.sto" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="info.css">
    <title>Car service information</title>
</head>
<body>
<jsp:include page="header.jsp" />
<h1>Car service information</h1>
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
    <div class="center-button">
        <input type="submit" value="Change car service">
    </div>
</form>
<%
} else {
%>
<table>
    <tr>
        <td>No such car service:</td>
    </tr>
</table>
<%
    }
%>
</body>
</html>
