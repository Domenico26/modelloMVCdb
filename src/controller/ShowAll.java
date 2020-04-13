package controller;

import model.Customer;
import model.CustomerDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/show-all")

public class ShowAll extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NumberFormatException {





        // instantiating a Model class to query the data
        var service = new CustomerDAO();

        String address;

        //storing the resulting javabean in the "request" object
        try {
            request.setAttribute("customers", service.doRetrieveAll());


            // depending on the Model response the  "address" of the proper View component (jsp) is set

            address = "/WEB-INF/results/showall.jsp";
        }
        catch (RuntimeException e)
        {
            address = "/WEB-INF/results/errore.jsp";
        }

        // The servlet dispatches the control to the chosen jsp (through its address)
        // and passes it both the reference to the javabean (stored in the "request") and
        // the response where the jsp will store the final page.
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }


}
