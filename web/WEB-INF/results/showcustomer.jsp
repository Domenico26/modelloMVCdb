<html>
<head>
    <title>Immetti dati</title>
    <link href="./css/styles.css"
          rel="stylesheet" type="text/css"/>
</head>
<body>
<fieldset>
    <legend>Modifica account</legend>

    <!-- the html page invokes the "show-balance" servlet, i.e., the ShowBalance class -->

    <!--target="_blank" nuova scheda-->
    <form action="UpdateServlet" method="post">
        <input type="hidden" name="id" value="${customer.id}">
        <input type="text" name="firstname" value="${customer.firstName}">
        <input type="text" name="lastname" value="${customer.lastName}">
        <input type="text" name="balance" value="${customer.balance}">
        <input type="submit" value="Salva Modifica">
    </form>

</fieldset>

</body>
</html>