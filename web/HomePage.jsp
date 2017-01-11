<%-- 
    Document   : HomePage
    Created on : 31 déc. 2015, 02:37:03
    Author     : bobmastrolilli
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page d'accueil</title>
    </head>
    <body>
    <center>
        <h1>Menu Principal</h1>

        <p>Bonjour et bienvenue sur WebApplic Reservation: </p>

        RESERVATION<br/>
        ------------------------------------------------
        <form action="/WebApplication/ServletPrincipal" method="POST">
            <input type="submit" name="action" value="Demande de reservation" />
        </form>
        
    </center>
    </body>
</html>
