<%--                        Google Map API.                      --%>
<%@ page import="entity.sto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Map</title>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBHRKe3PbCrcqxerxPGfkkbcHnriCnHmOw"></script>
    <script type="text/javascript">
        var marker; //маркер, що буде центром діапазону пошуку
        var map
        function initialize() {
            <%--var center = new google.maps.LatLng(<%= request.getAttribute("lat") %>, <%= request.getAttribute("lng") %>);--%>
            var mapOptions = {
                zoom: 2,
                center: {lat: 45.15231, lng: 78.430},
            };
            map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

            // Add click event listener to the map
            map.addListener('click', function(event) {
                placeMarker(event.latLng);
            });

            var stoMarkers = [];
            <%
            List<sto> res = (List<sto>) request.getAttribute("res");
            for (int i = 0; i < res.size(); i++) {
                sto sto = res.get(i);
                if (sto.getGeo() != null) { %>
            var position = new google.maps.LatLng(<%= sto.getGeo().getY() %>, <%= sto.getGeo().getX() %>);
            var marker = new google.maps.Marker({
                position: position,
                map: map,
                title: "<%= sto.getName() %>",
                description: "<%= sto.getName() %>",
                icon: {
                    path: google.maps.SymbolPath.CIRCLE,
                    fillColor: "yellow",
                    fillOpacity: 1,
                    strokeColor: "black",
                    strokeWeight: 1,
                    scale: 8
                }
            });

            // Add click event listener to each marker
            marker.addListener('click', function() {
                zoomToMarker(marker);
            });

            stoMarkers.push(marker);
            <%
                }
            }
            %>

            function zoomToMarker(marker) {
                // Center the map on the marker's position
                map.setCenter(marker.getPosition());

                // Zoom in on the marker
                map.setZoom(8);
            }
        }

        function placeMarker(location) {
            // Clear previous marker, if any
            if (marker) {
                marker.setMap(null);
            }

            // Create a new marker at the clicked location
            marker = new google.maps.Marker({
                position: location,
                map: map,
                title: "Location",
            });

            // Set the value of hidden input fields with the latitude and longitude
            document.getElementById("centerLat").value = location.lat();
            document.getElementById("centerLon").value = location.lng();
        }

    </script>
</head>
<body onload="initialize()">
<div id="map-canvas" style="height: 500px;"></div>
<form method="post" action="${pageContext.request.contextPath}/map">
    <input type="hidden" id="centerLat" name="centerLat" value="">
    <input type="hidden" id="centerLon" name="centerLon" value="">
    <label for="range">Search Range (in meters):</label>
    <input type="number" id="range" name="range" required>
    <button type="submit">Search</button>
</form>
</body>
</html>



<%--                                Leaflet                       --%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Map</title>--%>
<%--    <script src="https://cdn.jsdelivr.net/npm/leaflet@1.7.1/dist/leaflet.js"></script>--%>
<%--    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/leaflet@1.7.1/dist/leaflet.css" />--%>
<%--    <style>--%>
<%--        #mapid { height: 600px; }--%>
<%--    </style>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div id="mapid"></div>--%>
<%--<script>--%>
<%--    var mymap = L.map('mapid').setView([55.75, 37.62], 10);--%>

<%--    L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {--%>
<%--        attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +--%>
<%--            '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +--%>
<%--            'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',--%>
<%--        maxZoom: 18,--%>
<%--        id: 'mapbox/streets-v11',--%>
<%--        tileSize: 512,--%>
<%--        zoomOffset: -1,--%>
<%--        accessToken: 'your.mapbox.access.token'--%>
<%--    }).addTo(mymap);--%>

<%--    <c:forEach items="${res}" var="s">--%>
<%--    L.marker([${s.getLat()}, ${s.getLon()}]).addTo(mymap);--%>
<%--    </c:forEach>--%>
<%--</script>--%>
<%--</body>--%>
<%--</html>--%>
