<%-- 
    Document   : newjsp
    Created on : 31 déc. 2015, 01:57:07
    Author     : bobmastrolilli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion d'un client</title>
    </head>
    
    <body>
    <center><h1> Bienvenue sur Web Applic Reservation !</h1></center>
   <br/><br/><br/><br/> 
    <center>   
    <div>
            <form action="/WebApplication/ServletPrincipal"  method="GET">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Login :<input type= "text" name="Login" value="guest" /></br>
                Password :<input type="password" name="Password" value="guest" /></br>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" name="action" value="Connexion" />
                <input type="submit" value="Connecter" />
                <input type="checkbox" name="cb_id" value="cb_id_valeur" checked ="checked" />
            </form>
        </div>
        </center> 

    </body>
</html>