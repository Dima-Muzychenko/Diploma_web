<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Evaluation</title>
    <style>
        /* General Styles */
        body {
            font-family: Arial, sans-serif;
            margin: 8px;
        }

        .container {
            max-width: 830px;
            margin: 0 auto;
            text-align: center;
        }

        h1 {
            margin-top: 20px;
        }

        h2 {
            margin-top: 40px;
            margin-bottom: 20px;
            font-size: 24px;
            line-height: 1.5;
            position: relative;
        }

        h2::before,
        h2::after {
            content: "";
            position: absolute;
            top: 50%;
            width: 610px;
            height: 2px;
            background-color: #ccc;
        }

        h2::before {
            left: -330px;
            /*width: 30%;*/
            transform: translateY(-50%);
        }

        h2::after {
            right: -330px;
            /*width: 30%;*/
            transform: translateY(-50%);
        }

        label {
            font-weight: bold;
            display: block;
            margin-top: 10px;
        }

        input[type="text"],
        input[type="password"],
        textarea {
            width: 320px;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 3px;
            margin-bottom: 10px;
        }
        textarea {
            height: 120px;
            resize: none;
        }

        input[type="submit"] {
            width: 350px;
            padding: 10px 20px;
            margin-top: 15px;
            background-color: #337ab7;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        /* Info Circle Styles */
        .info-circle {
            position: relative;
            display: inline-block;
            width: 20px;
            height: 20px;
            background-color: #0a5a0a;
            border-radius: 50%;
            margin-left: 5px;
            cursor: pointer;
        }

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

        <input type="submit" value="Add">
    </form>
</div>

</body>
</html>