<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Website Header</title>
  <style>

    header {
      background-color: #222222;
      padding: 10px;
    }

    nav {
      display: flex;
      justify-content: space-between;
    }

    nav ul {
      list-style-type: none;
      margin: 0;
      padding: 0;
    }

    nav ul li {
      display: inline;
      margin-right: 10px;
    }

    nav ul li:last-child {
      margin-right: 0;
    }

    nav ul li a {
      display: inline-block;
      padding: 10px 20px;
      color: #ffffff;
      text-decoration: none;
    }

    nav ul li:nth-child(1) a {
      color: #ffffff;
    }

    nav ul.bot li:nth-child(1) a{
      color: #778899;
    }
  </style>
</head>
<body>
<header>
  <nav>
    <ul>
      <li><a href="/map">Map</a></li>
      <li><a href="/createNewService">Create New Service</a></li>
      <li><a href="/watchService">Watch Service</a></li>
    </ul>
    <ul class="bot">
      <li><a href="/Bot">Bot</a></li>
    </ul>
  </nav>
</header>
</body>
</html>
