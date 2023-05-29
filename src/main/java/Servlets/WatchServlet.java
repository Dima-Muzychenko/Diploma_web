package Servlets;

import Check.InputDataCheck;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "WatchServlet", value = "/watch")
public class WatchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Set the proper encoding
        response.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String lat = request.getParameter("lat");
        String lon = request.getParameter("lon");
        String password = request.getParameter("password");

        InputDataCheck check = new InputDataCheck();
        // Validate latitude and longitude values
        boolean isValidLat = check.isValidNumber(lat, -90, 90);
        boolean isValidLon = check.isValidNumber(lon, -180, 180);

        if (!isValidLat || !isValidLon) {
            // Set the error message in the request attribute
            request.setAttribute("errorMessage", "Invalid latitude or longitude value.");

            // Forward the request back to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/watchService.jsp");
            dispatcher.forward(request, response);
            return;
        }
        // Perform any necessary processing or validation of the form input

        String encodedName = URLEncoder.encode(name, "UTF-8");
        String encodedPass = URLEncoder.encode(password, "UTF-8");

        String redirectURL = "/info?name=" + encodedName + "&lat=" + lat + "&lon=" + lon + "&pass=" + encodedPass;
        response.sendRedirect(redirectURL);
    }

}
