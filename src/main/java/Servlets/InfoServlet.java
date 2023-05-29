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

@WebServlet(name = "InfoServlet", value = "/info")
public class InfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");//Ставимо нормальне кодування
        response.setCharacterEncoding("UTF-8");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
        StoDAO st = new StoDAO();
        sto sto = st.getSTOByNameLatLonPass(request.getParameter("name"), Double.parseDouble(request.getParameter("lat")),
                Double.parseDouble(request.getParameter("lon")), request.getParameter("pass"));
        request.setAttribute("sto", sto);
        manager.close();
        factory.close();
        getServletContext().getRequestDispatcher("/info.jsp").forward(request,response);

//        request.getRequestDispatcher("/info.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");//Ставимо нормальне кодування
        response.setCharacterEncoding("UTF-8");

    }
}
