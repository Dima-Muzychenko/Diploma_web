<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Evaluation</title>
    <style>
        /*кружок підказки*/
        .info-circle {
            position: relative;
            display: inline-block;
            width: 20px;
            height: 20px;
            background-color: #FFCCCB;
            border-radius: 50%;
            margin-left: 5px;
            cursor: pointer;
        }
        /*інформація в кружку підказки*/
        .info-tooltip {
            position: absolute;
            display: none;
            background-color: rgba(0, 0, 0, 0.8);
            color: #fff;
            padding: 5px;
            border-radius: 4px;
            top: -30px;
            left: 30px;
            width: 200px;
        }

        .info-circle:hover .info-tooltip {
            display: block;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp" />
<h1>Add Service Station</h1>
<form action="EstimationServlet" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required>
    <div class="info-circle">
        <div class="info-tooltip">
            Enter the name of the service station.
        </div>
    </div>
    <br>

    <label for="latitude">Latitude:</label>
    <input type="text" id="latitude" name="lat" required>
    <div class="info-circle">
        <div class="info-tooltip">
            Enter the latitude coordinate of the service station.
        </div>
    </div>
    <br>

    <label for="longitude">Longitude:</label>
    <input type="text" id="longitude" name="lon" required>
    <div class="info-circle">
        <div class="info-tooltip">
            Enter the longitude coordinate of the service station.
        </div>
    </div>
    <br>

    <!-- Add other input fields with circles and tooltips -->
    <label for="owner">Owner:</label>
    <input type="text" id="owner" name="owner" required><br>

    <label for="quality">Quality:</label>
    <input type="text" id="quality" name="quality" required><br>

    <label for="speed">Speed:</label>
    <input type="text" id="speed" name="speed" required><br>

    <label for="price">Price:</label>
    <input type="text" id="price" name="price" required><br>

    <label for="serviceRange">Service Range:</label>
    <input type="text" id="serviceRange" name="service_range" required><br>

    <label for="evaluation">Evaluation:</label>
    <input type="text" id="evaluation" name="evaluation" required><br>

    <label for="address">Address:</label>
    <input type="text" id="address" name="address" required><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>

    <label for="comments">Comments:</label>
    <textarea id="comments" name="comments" required></textarea><br>

    <label for="working">Working Hours:</label>
    <input type="text" id="working" name="working" required><br>

    <input type="submit" value="Add">
</form>

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