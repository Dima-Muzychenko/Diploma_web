package Servlets;

import entity.Sto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.util.List;

//import org.postgis.Geometry;
//import org.postgis.Point;

@WebServlet("/map")
public class MapServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //Відправка даних до jsp
//        //1. Set the data as an attribute of the request object in the Servlet
//        String message = "Hello World!";
//        request.setAttribute("message", message);
//
//        //<h1>${message}</h1>
//        //2. send data to a JSP page as URL parameters
//        String message1 = "Hello World!";
//        String url = "result.jsp?message=" + URLEncoder.encode(message1, "UTF-8");
//        response.sendRedirect(url);
//        //<h1><%= request.getParameter("message") %></h1>
//        //3. використовуючи session
        // Open a new Hibernate session



        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();

        manager.getTransaction().begin();

//        CriteriaBuilder builder = manager.getCriteriaBuilder();
//        CriteriaQuery<Tuple> query = builder.createTupleQuery();
//        Root<Sto> root = query.from(Sto.class);
//
//        query.multiselect(root.get("name"), root.get("evaluation"));
        CriteriaBuilder builder  = manager.getCriteriaBuilder();
        CriteriaQuery<Sto> query = builder.createQuery(Sto.class);
        Root<Sto> root = query.from(Sto.class);
        query.multiselect(root.get("name"), root.get("evaluation"));
        List<Sto> results = manager.createQuery(query).getResultList();
        request.setAttribute("results", results);//Pass the location data to the JSP page

//        for (Tuple result : results) {
//            String name = result.get(0, String.class);
//            Point geolocation = result.get(1, Point.class);
//            System.out.println("Name: " + name + ", Geolocation: " + geolocation);
//        }


        manager.getTransaction().commit();
        manager.close();
        factory.close();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/map.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
