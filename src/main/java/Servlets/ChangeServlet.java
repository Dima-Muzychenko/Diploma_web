package Servlets;

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
