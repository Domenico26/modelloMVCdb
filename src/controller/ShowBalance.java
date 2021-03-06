package controller;

import model.Customer;
import model.CustomerDAO;

import java.io.*;
import java.text.ParseException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;


/**
 * Servlet that reads a customer ID and displays
 * information on the account balance of the customer
 * who has that ID.
 * <p>
 * From <a href="http://courses.coreservlets.com/Course-Materials/">the
 * coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT,
 * Spring, Hibernate/JPA, and Java programming</a>.
 */

@WebServlet("/show-balance")
public class ShowBalance extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException , NumberFormatException {
        String address;
        // reading parameters from the request
        String cID=request.getParameter("customerId");

        // visto che il form index.html non permette l'inserimento di una stringa nel campo customerId (input type= "number), questo controllo sembrerebbe inutile
        // Bisogna per� tener conto che questa servlet potrebbe essere chiamata non tramite il mio form
        // ma da una url inserita nel browser : http://localhost:8080/modelloMVCdb_war_exploded/show-balance?customerId=ciao
        // si noti  che cusotmerId = ciao
        // In questo caso questo controllo � necessario: nel progetto va sempre fatto il doppio controllo (sia lato client che lato server) come fatto in questa applicazione
        int customerId=0;
        try {
            customerId = Integer.parseInt(cID);
        }
        catch (NumberFormatException e){
            address="/WEB-INF/results/unknown-customer.jsp";
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }




        // instantiating a Model class to query the data
        var service = new CustomerDAO();
        // creating the javabean "customer" to receive the Model response
        // and invocating the Model service by passing the request parameter "customerId"
        Customer customer = service.doRetrieveById(customerId);

        //storing the resulting javabean in the "request" object
        request.setAttribute("customer", customer);



        // depending on the Model response the  "address" of the proper View component (jsp) is set
        if (customer == null) {
            request.setAttribute("badId", customerId);
            address = "/WEB-INF/results/unknown-customer.jsp";
        } else if (customer.getBalance() < 0) {
            address = "/WEB-INF/results/negative-balance.jsp";
        } else if (customer.getBalance() < 10000) {
            address = "/WEB-INF/results/normal-balance.jsp";
        } else {
            address = "/WEB-INF/results/high-balance.jsp";
        }

        // The servlet dispatches the control to the chosen jsp (through its address)
        // and passes it both the reference to the javabean (stored in the "request") and
        // the response where the jsp will store the final page.
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
