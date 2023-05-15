package Servlets;

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
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "EstimationServlet", value = "/estimation")
public class EstimationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        stoInsert.setQuality(Integer.valueOf(request.getParameter("quality")));
        stoInsert.setSpeed(Integer.valueOf(request.getParameter("speed")));
        stoInsert.setPrice(Integer.valueOf(request.getParameter("price")));
        stoInsert.setServiceRange(Integer.valueOf(request.getParameter("service_range")));
        stoInsert.setEvaluation(Double.valueOf(request.getParameter("evaluation")));
        stoInsert.setAddress(request.getParameter("address"));
        stoInsert.setLat(Double.valueOf(request.getParameter("lat")));
        stoInsert.setLon(Double.valueOf(request.getParameter("lon")));

        GeometryFactory gf = new GeometryFactory(new PrecisionModel(), 4326);
        Point pgGeo = gf.createPoint(new Coordinate(stoInsert.getLon(), stoInsert.getLat()));
        stoInsert.setGeo(pgGeo);


        manager.getTransaction().begin();
        manager.merge(stoInsert);//додаємо/оновлюємо дані
        manager.getTransaction().commit();

        manager.close();
        factory.close();
    }
}
