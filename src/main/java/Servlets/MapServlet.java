package Servlets;


import DAO.StoDAO;
import entity.sto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "MapServlet", value = "/map")
public class MapServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
        StoDAO st = new StoDAO();
        List<sto> res = st.getAllStos();
        request.setAttribute("res", res);
        getServletContext().getRequestDispatcher("/map.jsp").forward(request,response);
        manager.close();
        factory.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double centerLat = Double.parseDouble(request.getParameter("centerLat"));
        double centerLon = Double.parseDouble(request.getParameter("centerLon"));
        double rangeInMeters = Double.parseDouble(request.getParameter("range"));

        StoDAO stoDAO = new StoDAO();
        List<sto> res = stoDAO.getStosInRange(centerLon, centerLat, rangeInMeters);
        request.setAttribute("res", res);
        request.getRequestDispatcher("/map.jsp").forward(request, response);
    }
}
