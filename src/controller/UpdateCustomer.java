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

@WebServlet("/UpdateCustomer")
public class UpdateCustomer extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // instantiating a Model class to query the data
        var service = new CustomerDAO();
        String customerId = request.getParameter("id");
        int id=Integer.parseInt(customerId);


        String address;

        if(request.getParameter("aggPreferBut")!=null)
        {
            //aggiungi ai preferiti
            HttpSession session=request.getSession();
            List<Integer> preferiti;
            if((preferiti=(List<Integer>)session.getAttribute("preferiti"))==null)
            {
                preferiti=new ArrayList<Integer>();
            }
            else
            {
                session.removeAttribute("preferiti");
            }
            Integer ID=id;
            //se non lo contiene gia lo aggiunge
            if(!preferiti.contains(ID))
                preferiti.add(id);
            session.setAttribute("preferiti",preferiti);
            request.setAttribute("customers",service.doRetrieveAll());
            address="/WEB-INF/results/showall.jsp";


        }
        else
        if(request.getParameter("togliPreferBut")!=null)
        {
            //rimuovi dai preferiti
            HttpSession session=request.getSession();
            List<Integer> preferiti;
            if((preferiti=(List<Integer>)session.getAttribute("preferiti"))==null || preferiti.size()==0)
            {

            }
            else
            {
                Integer ID=id;
                session.removeAttribute("preferiti");
                preferiti.remove(ID);
                session.setAttribute("preferiti",preferiti);
            }
            request.setAttribute("customers",service.doRetrieveAll());
            address="/WEB-INF/results/showall.jsp";

        }
        else
        {
            //modifica customer
            try{
                service.doRetrieveById(id);
                Customer customer = service.doRetrieveById(id);
                //storing the resulting javabean in the "request" object
                request.setAttribute("customer", customer);
                address="/WEB-INF/results/showcustomer.jsp";
            }
            catch (RuntimeException e)
            {
                address = "/WEB-INF/results/errore.jsp";
            }
        }






        // The servlet dispatches the control to the chosen jsp (through its address)
        // and passes it both the reference to the javabean (stored in the "request") and
        // the response where the jsp will store the final page.
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

}

