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

@WebServlet("/insert-servlet")
public class InsertServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");


        // qui manca il controllo sull'input balance, se non è un double bisogna rispondere al client con pagina di errore
        // come fatto per customerId in ShowBalance
        double balance=Double.parseDouble(req.getParameter("balance"));



        var service= new CustomerDAO();
        service.doSave(new Customer(firstName,lastName,balance));

        String address="/index.html";

        RequestDispatcher dispatcher =
                req.getRequestDispatcher(address);
        dispatcher.forward(req, resp);
    }
}
