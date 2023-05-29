<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Evaluation</title>
    <link rel="stylesheet" type="text/css" href="CreateNewService.css">
</head>
<body>
<jsp:include page="header.jsp" />
<div class="container">
    <h1>Create car service</h1>
    <form action="/createNewService" method="post">
        <h2>Public information</h2>
        <%--param is an implicit object that allows you to access request parameters--%>
        <%--For example, if you have an input field with the name "name" in your form,
        you can access its value using param.name in your JSP code--%>
        <%--By using param, you can access the submitted form data in your JSP code and use it--%>
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required value="${param.name}"> <%--Якщо ми ввели неправильні дані,
        то наше введені дані залишаються в формі, а не стираються (щоб заново всі оля не заповнювати)--%>
        <div class="info-circle">
            <div class="info-tooltip">
                Car service name.
            </div>
        </div>

        <label for="latitude">Latitude:</label>
        <input type="text" id="latitude" name="lat" required value="${param.lat}">
        <div class="info-circle">
            <div class="info-tooltip">
                Latitude coordinate of the car service. It should be in range from 90 to -90. For example 34.2343
            </div>
        </div>

        <label for="longitude">Longitude:</label>
        <input type="text" id="longitude" name="lon" required value="${param.lon}">
        <div class="info-circle">
            <div class="info-tooltip">
                Longitude coordinate of the service station. It should be in range from 180 to -180. For example -114.2343
            </div>
        </div>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" required value="${param.address}">
        <div class="info-circle">
            <div class="info-tooltip">
                Address of car service. For example: "Gershuny, 323, Kozelets, Chernihiv region, 17000, Ukraine"
            </div>
        </div>
        <br>

        <label for="comments">Description:</label>
        <textarea id="comments" name="comments" required>${param.comments}</textarea>
        <div class="info-circle">
            <div class="info-tooltip">
                Detailed description of what the service do.
            </div>
        </div>

        <label for="working">Working Hours:</label>
        <textarea id="working" name="working" required>${param.working}</textarea>
        <div class="info-circle">
            <div class="info-tooltip">
                List the working days and hours of your car service. For example:
                Mon, 09:00-18:00
                Tue, 09:00–18:00
                Wed, 09:00-18:00
                Thu, 09:00 - 18:00
                Fri, 09:00-15:00
                Sat, Sun - weekend
            </div>
        </div>
        <br>


        <h2>Private information</h2>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <div class="info-circle">
            <div class="info-tooltip">
                Password for car service. It can be used for further changes of car service.
            </div>
        </div>

        <label for="owner">Owner:</label>
        <input type="text" id="owner" name="owner" required value="${param.owner}">
        <div class="info-circle">
            <div class="info-tooltip">
                Full name of the owner of the car service.
            </div>
        </div>

        <h2>Evaluation parameters</h2>
        <label for="quality">Quality:</label>
        <input type="text" id="quality" name="quality" required value="${param.quality}">
        <div class="info-circle">
            <div class="info-tooltip">
                Average quality evaluation in range from 0 to 10.
            </div>
        </div>

        <label for="speed">Speed:</label>
        <input type="text" id="speed" name="speed" required value="${param.speed}">
        <div class="info-circle">
            <div class="info-tooltip">
                Average speed evaluation in range from 0 to 10.
            </div>
        </div>
        <br>

        <label for="price">Price:</label>
        <input type="text" id="price" name="price" required value="${param.price}">
        <div class="info-circle">
            <div class="info-tooltip">
                Average price evaluation in range from 0 to 10.
            </div>
        </div>

        <label for="serviceRange">Service Range:</label>
        <input type="text" id="serviceRange" name="service_range" required value="${param.service_range}">
        <div class="info-circle">
            <div class="info-tooltip">
                Average service range evaluation in range from 0 to 10.
            </div>
        </div>
        <br>

        <br>
        <%String error = (String) request.getAttribute("errorMessage");
            if(error!=null) {
        %>
        <span style="color: red;"><%= request.getAttribute("errorMessage") %></span>
        <br>
        <%
            }
        %>
        <input type="submit" value="Add">
    </form>
</div>

<%--дії ри наведенні на кружочки--%>
<script>
    const infoCircles = document.querySelectorAll('.info-circle');
    infoCircles.forEach(circle => {
        const tooltip = circle.querySelector('.info-tooltip');

        circle.addEventListener('mouseover', () => {
            circle.classList.add('active');
        });
        circle.addEventListener('mouseout', () => {
            circle.classList.remove('active');
        });

    });
</script>
</body>
</html>