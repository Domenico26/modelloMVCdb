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

@WebServlet("/UpdateServlet")

public class UpdateServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NumberFormatException {

        String id = request.getParameter("id");
        String first = request.getParameter("firstname");
        String last = request.getParameter("lastname");
        String bil = request.getParameter("balance");

        Customer custom = new Customer();

        custom.setId(Integer.parseInt(id));
        custom.setFirstName(first);
        custom.setLastName(last);
        custom.setBalance(Double.parseDouble(bil));

        var service = new CustomerDAO();

        String result;
        String address;

        try{
            service.doUpdate(custom);
            result = "Operazione riuscita";
        }
        catch(RuntimeException e){
            result="Operazione non riuscita";
        }

        request.setAttribute("risultato",result);


        address="/WEB-INF/results/risultato.jsp";








        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

}
