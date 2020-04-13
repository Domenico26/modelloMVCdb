package controller;

import model.Customer;
import model.CustomerDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/LoadPrefs")

public class LoadPrefs extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NumberFormatException {





        var service = new CustomerDAO();


        String address;
        HttpSession session = request.getSession();
        List<Integer> preferiti = (List<Integer>) service.doRetrievePrefs();








        //storing the resulting javabean in the "request" object
        try {


            session.setAttribute("preferiti", preferiti);
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