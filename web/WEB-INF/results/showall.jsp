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
<form action="StorePrefs">
    <input type="submit" name="dbpref" value="Memorizza preferiti">
</form>
<form action="LoadPrefs">
    <input type="submit" name="loaddb" value="Carica preferiti dal db">
</form>
<table class="title">
    <tr><th>Gestione clienti</th></tr>
</table>
<a href = "index.html"> Torna a index </a>
<p style="color:green"> I clienti in verde sono nei preferiti </p>
<%
    List<Integer> preferiti = (List<Integer>) session.getAttribute("preferiti");
    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
%>
<ul>
    <% for(Customer customer : customers){
        if (preferiti != null && preferiti.contains(customer.getId())){
    %>
    <li style="color:green"> <%=customer.getId()%>.  <%= customer.getFirstName()%> <%=customer.getLastName()%> <%= customer.getBalance()%>
            <% }else { %>
    <li> <%=customer.getId()%>. <%= customer.getFirstName()%> <%=customer.getLastName()%> <%= customer.getBalance()%>
        <% } %>
        <form action="UpdateCustomer">
            <input type="hidden" name="id" value="<%=customer.getId()%>">
            <input type="submit" value="Modifica">
            <input type="submit" name="aggPreferBut" value="Aggiungi ai preferiti">
            <input type="submit" name="togliPreferBut" value="Togli dai preferiti">
        </form>
    </li>
    <%}%>
</ul>
</body>
</html>
