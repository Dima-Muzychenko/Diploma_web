package Servlets;

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

        // Perform any necessary processing or validation of the form input

        String encodedName = URLEncoder.encode(name, "UTF-8");
        String encodedPass = URLEncoder.encode(password, "UTF-8");

        String redirectURL = "/info?name=" + encodedName + "&lat=" + lat + "&lon=" + lon + "&pass=" + encodedPass;
        response.sendRedirect(redirectURL);
    }
}
