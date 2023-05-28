package Servlets;

import entity.sto;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ChangeServlet", value = "/ChangeServlet")
public class ChangeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the updated values from request parameters
        String name = request.getParameter("name");
        // Retrieve other updated fields as needed

        // Retrieve the sto object from the session
        sto sto1 = (sto) request.getSession().getAttribute("sto1");

        if (sto1 != null) {
            // Update the sto object with the new values
            sto1.setName(name);
            // Update other fields as needed

            // Perform the necessary logic to save the changes
            // This may involve interacting with your data storage (e.g., a database) to update the corresponding sto object

            // Redirect to info.jsp to display the updated information
            response.sendRedirect("info.jsp");
        } else {
            // Handle the case when sto1 is null
            // Redirect to an appropriate page or display an error message
        }
    }
}
