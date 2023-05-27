package Servlets;

import FuzzyLogic.ServiceStationAttractiveness;
import entity.sto;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreateNewServiceServlet", value = "/createNewService")
public class CreateNewServiceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/createNewService.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
        //для оновлення даних
//        sto stoUpdate = manager.find(sto.class, request.getParameter("info_id"));
//        stoUpdate.setQuality(Integer.valueOf(request.getParameter("quality")));
//        stoUpdate.setSpeed(Integer.valueOf(request.getParameter("speed")));
//        stoUpdate.setPrice(Integer.valueOf(request.getParameter("price")));
//        stoUpdate.setServiceRange(Integer.valueOf(request.getParameter("service_range")));
//        stoUpdate.setEvaluation(Double.valueOf(request.getParameter("evaluation")));

        sto stoInsert = new sto();
        stoInsert.setName(request.getParameter("name"));//id змінювати нельзя в Hibernate
        stoInsert.setOwner(request.getParameter("owner"));
        try { //перевірка, чи у нас число в тих строках, де воно повинно бути
            stoInsert.setQuality(Double.valueOf(request.getParameter("quality")));
            stoInsert.setSpeed(Double.valueOf(request.getParameter("speed")));
            stoInsert.setPrice(Double.valueOf(request.getParameter("price")));
            stoInsert.setServiceRange(Double.valueOf(request.getParameter("service_range")));


            ServiceStationAttractiveness attractiveness = new ServiceStationAttractiveness();
            double evaluation = attractiveness.CountEvaluation(stoInsert.getQuality(), stoInsert.getSpeed(), stoInsert.getPrice(), stoInsert.getServiceRange());
            stoInsert.setEvaluation(evaluation);

            stoInsert.setAddress(request.getParameter("address"));
            stoInsert.setLat(Double.valueOf(request.getParameter("lat")));
            stoInsert.setLon(Double.valueOf(request.getParameter("lon")));
            stoInsert.setPassword(request.getParameter("password"));
            stoInsert.setComments(request.getParameter("comments"));
            stoInsert.setWorking(request.getParameter("working"));
        } catch (NumberFormatException e) {
            System.out.println("Expected double type: " + e);
            manager.close();
            factory.close();
        }


        GeometryFactory gf = new GeometryFactory(new PrecisionModel(), 4326);
        Point pgGeo = gf.createPoint(new Coordinate(stoInsert.getLat(), stoInsert.getLon()));
        stoInsert.setGeo(pgGeo);


//        manager.createNativeQuery("INSERT INTO sto (name, owner, quality, speed, price, service_range, evaluation, address, lat, lon, geo, password, comments, working) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        String query = "INSERT INTO sto (name, owner, quality, speed, price, service_range, evaluation, address, lat, lon, geo, password, comments, working) " +
                "VALUES ('" + stoInsert.getName() + "', '" + stoInsert.getOwner() + "', " +
                stoInsert.getQuality() + ", " + stoInsert.getSpeed() + ", " + stoInsert.getPrice() + ", " +
                stoInsert.getServiceRange() + ", " + stoInsert.getEvaluation() + ", '" + stoInsert.getAddress() + "', " +
                stoInsert.getLat() + ", " + stoInsert.getLon() + ", '" + stoInsert.getGeo() + "', '" + stoInsert.getPassword() + "', '" +
                stoInsert.getComments() + "', '" + stoInsert.getWorking() + "')";

        manager.getTransaction().begin();
        int rowsAffected = manager.createNativeQuery(query).executeUpdate();
        manager.getTransaction().commit();

        if (rowsAffected > 0) {
            System.out.println("Works: "+rowsAffected);
        } else {
            System.out.println("Does not work");
        }

        manager.close();
        factory.close();
    }
}
