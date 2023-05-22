<%--                        Google Map API.                      --%>
<%@ page import="entity.sto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="constants.Constants" %>
<html>
<head>
    <title>Map</title>
    <link rel="stylesheet" type="text/css" href="map.css">
    <%String myCode = Constants.KEY_API;%>

    <script src="https://maps.googleapis.com/maps/api/js?key=<%=myCode%>"></script>
    <script type="text/javascript">
        let marker; //маркер, що буде центром діапазону пошуку
        let map; //карта
        let greenStoMarkers = [];//для СТО в діапазоні
        let stoMarkers = [];//Сочатку всі СТО, а потім СТО за діапазоном
        let selectedMarker = false; // Flag to track if a marker is selected
        function initialize() {
            <%--var center = new google.maps.LatLng(<%= request.getAttribute("lat") %>, <%= request.getAttribute("lng") %>);--%>
            let mapOptions = {
                zoom: 2,
                center: {lat: 45.15231, lng: 78.430},
            };
            map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

            // Add click event listener to the map
            map.addListener('click', function(event) {
                placeMarker(event.latLng);
            });

            //рисуєм точки (всі або за межами діапазану, якщо був викликаний метод пошуку)
            <%
            List<sto> res = (List<sto>) request.getAttribute("stos");
            if (res != null){
                for (int i = 0; i < res.size(); i++) {
                    sto sto = res.get(i);
                    if (sto.getGeo() != null) { %>
            var position = new google.maps.LatLng(<%= sto.getGeo().getY() %>, <%= sto.getGeo().getX() %>);
            var marker = new google.maps.Marker({
                position: position,
                map: map,
                title: "<%= sto.getName() %> \nAddress: <%= sto.getAddress() %> \nEvaluation: <%= sto.getEvaluation() %>",
                icon: {
                    path: google.maps.SymbolPath.CIRCLE,
                    fillColor: "yellow",
                    fillOpacity: 1,
                    strokeColor: "black",
                    strokeWeight: 1,
                    scale: 8
                }
            });

            stoMarkers.push(marker);
            <%
                    }
                }
            }
            %>
            // Add click event listener to each marker
            for (let i = 0; i < stoMarkers.length; i++){
                stoMarkers[i].addListener('click', function() {
                    zoomToMarker(stoMarkers[i]);
                });
            }

            //рисуємо точки в вибраному діапазоні (якщо вибрали)
            <%
            List<sto> stosInRange = (List<sto>) request.getAttribute("stosInRange");
            if (stosInRange != null){
                for (int i = 0; i < stosInRange.size(); i++) {
                    sto sto = stosInRange.get(i);
                    if (sto.getGeo() != null) { %>
            var position = new google.maps.LatLng(<%= sto.getGeo().getY() %>, <%= sto.getGeo().getX() %>);
            var marker = new google.maps.Marker({
                position: position,
                map: map,
                title: "<%= sto.getName() %> \nAddress: <%= sto.getAddress() %> \nEvaluation: <%= sto.getEvaluation() %>",
                icon: {
                    path: google.maps.SymbolPath.CIRCLE,
                    fillColor: "green",
                    fillOpacity: 1,
                    strokeColor: "black",
                    strokeWeight: 1,
                    scale: 8
                }
            });

            greenStoMarkers.push(marker);
            <%
                    }
                }
            }
            %>
            for (let i = 0; i < greenStoMarkers.length; i++){
                // Add click event listener to each marker
                greenStoMarkers[i].addListener('click', function() {
                    zoomToMarker(greenStoMarkers[i]);
                });
            }

        }

        // приближення до маркера при натисканні нанього
        function zoomToMarker(marker) {
            // Center the map on the marker's position
            map.setCenter(marker.getPosition());
            map.setZoom(10);
        }

        //Check if we have chosen marker (to search sto in radius) before searching (calling doPost method)
        function validateForm() {
            if (!selectedMarker) {
                alert("Please select a marker on the map.");
                return false; // Prevent form submission
            }
        }

        function placeMarker(location) {
            // Clear previous marker, if any
            selectedMarker = true;
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

        //При натисканні на СТО зі списку
        function zoomToMarkerFromList(index, element) {
            //1. Виділяємо даний елемент в списку
            // Remove the 'highlighted' class from all list elements
            const listElements = document.querySelectorAll('.sto-list');
            listElements.forEach(item => item.classList.remove('highlighted'));

            // Add the 'highlighted' class to the clicked element
            element.classList.add('highlighted');

            //2. Виділяємо даний елемент на карті кольором lime
            changeColorOfSelectedStoFromList(index);

            //3. zoom to the element
            let marker = greenStoMarkers[index];
            zoomToMarker(marker);
        }

        //Виділяємо елемент на карті кольором lime
        function changeColorOfSelectedStoFromList(index){
            //Всі кольори в діапазоні ставимо зеленими
            greenStoMarkers.forEach(greenEl => greenEl.setIcon({
                path: google.maps.SymbolPath.CIRCLE,
                fillColor: "green",
                fillOpacity: 1,
                strokeColor: "black",
                strokeWeight: 1,
                scale: 8
            }));

            //Вибране СТО позначаємо lime кольором
            var theMarker = greenStoMarkers[index];
            theMarker.setIcon({
                path: google.maps.SymbolPath.CIRCLE,
                fillColor: 'lime',
                fillOpacity: 1,
                strokeColor: 'black',
                strokeWeight: 1,
                scale: 8
            });
        }


    </script>

</head>
<body onload="initialize()">
<%--Форма для пошуку по діапазону--%>
<div id="map-canvas" style="height: 500px;"></div>
<form method="post" action="${pageContext.request.contextPath}/map" onsubmit="return validateForm()">
    <input type="hidden" id="centerLat" name="centerLat" value="">
    <input type="hidden" id="centerLon" name="centerLon" value="">
    <label for="range">Search Range (in meters):</label>
    <input type="number" id="range" name="range" required>
    <button type="submit">Search</button>
</form>

<%--список СТО в діапазоні--%>
<div class="sto-list-container">
    <% if (stosInRange !=null){
        for (int i = 0; i < stosInRange.size(); i++) {
            sto sto = stosInRange.get(i);
            if (sto.getGeo() != null) { %>
    <ul class="sto-list" onclick="zoomToMarkerFromList(<%= i %>, this)">
        <li class="sto-item">
            <p class="sto-name">Name: <span><%= sto.getName() %></span></p>
            <p class="sto-address">Address: <span><%= sto.getAddress() %></span></p>
            <p class="sto-evaluation">Evaluation: <span><%= sto.getEvaluation() %></span></p>
            <p class="sto-coordinates">Latitude: <span><%= sto.getGeo().getY() %></span></p>
            <p class="sto-coordinates">Longitude: <span><%= sto.getGeo().getX() %></span></p>
        </li>
    </ul>
    <%
            }
        }
    } %>
</div>

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
