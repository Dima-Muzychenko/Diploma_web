package Servlets;

import Check.InputDataCheck;
import FuzzyLogic.ServiceStationAttractiveness;
import entity.sto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "ChangeServlet", value = "/ChangeServlet")
public class ChangeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Set the proper encoding
        response.setCharacterEncoding("UTF-8");
        // Retrieve the updated values from request parameters


        InputDataCheck check = new InputDataCheck();
        // Validate latitude and longitude values
        boolean isValidQuality = check.isValidNumber(request.getParameter("quality"), 0, 10);
        boolean isValidSpeed = check.isValidNumber(request.getParameter("speed"), 0, 10);
        boolean isValidPrice = check.isValidNumber(request.getParameter("price"), 0, 10);
        boolean isValidServiceRange = check.isValidNumber(request.getParameter("service_range"), 0, 10);

        if (!isValidQuality || !isValidSpeed || !isValidPrice || !isValidServiceRange) {
            // Set the error message in the request attribute
            request.setAttribute("errorMessage", "Invalid quality, speed, price or service range value. It Should be in range from 0 to 10");

            // Forward the request back to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/change.jsp");
            dispatcher.forward(request, response);
            return;
        }

        double quality = Double.parseDouble(request.getParameter("quality"));
        double speed = Double.parseDouble(request.getParameter("speed"));
        double price = Double.parseDouble(request.getParameter("price"));
        double service_range = Double.parseDouble(request.getParameter("service_range"));


        // Retrieve the sto object from the session
        sto sto1 = (sto) request.getSession().getAttribute("sto1");
        sto1.setQuality(quality);
        sto1.setSpeed(speed);
        sto1.setPrice(price);
        sto1.setServiceRange(service_range);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();

        ServiceStationAttractiveness attractiveness = new ServiceStationAttractiveness();
        double evaluation = attractiveness.CountEvaluation(sto1.getQuality(), sto1.getSpeed(), sto1.getPrice(), sto1.getServiceRange());
        sto1.setEvaluation(evaluation);


        String query = "UPDATE sto SET quality = " + quality + ", speed = " + speed + ", price = " + price +
                ", service_range = " + service_range + ", evaluation = " + evaluation + " WHERE info_id = " + sto1.getInfoId() ;

        manager.getTransaction().begin();
        manager.createNativeQuery(query).executeUpdate();
        manager.getTransaction().commit();

        manager.close();
        factory.close();

        String encodedName = URLEncoder.encode(sto1.getName(), "UTF-8");
        String encodedPass = URLEncoder.encode(sto1.getPassword(), "UTF-8");
        String redirectURL = "/info?name=" + encodedName + "&lat=" + sto1.getLat() + "&lon=" + sto1.getLon() + "&pass=" + encodedPass;
        response.sendRedirect(redirectURL);
    }
}
