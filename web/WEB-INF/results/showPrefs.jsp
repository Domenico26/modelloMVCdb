<%@ page import="java.util.List" %>
<%@ page import="model.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mostra utenti</title>
    <link rel="stylesheet"
          href="./css/styles.css"
          type="text/css"/>
</head>
<body>

<table class="title">
    <tr><th>Gestione clienti</th></tr>
</table>
<a href = "index.html"> Torna a index </a>
<p style="color:green">Preferiti </p>
<%
    List<Integer> preferiti = (List<Integer>) session.getAttribute("preferiti");
    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
%>
<ul>
    <% for(Customer customer : customers){
        if (preferiti != null && preferiti.contains(customer.getId())){
    %>
    <li> <%=customer.getId()%>.  <%= customer.getFirstName()%> <%=customer.getLastName()%> <%= customer.getBalance()%>
            <% } %>

    <%}%>
</ul>
</body>
</html>