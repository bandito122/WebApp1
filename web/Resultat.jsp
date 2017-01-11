<%-- 
    Document   : Resultat
    Created on : 31 déc. 2015, 07:00:11
    Author     : bobmastrolilli
--%>

<% ServletContext sc = getServletContext(); 
//response.sendRedirect (request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/WebApplication/Resultat.jsp?rep="+"true" + "?emplacement="+emplacement_random + "?idRes=" + num);



%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RESULTAT</title>
    </head>
    <body>
        <% 
//response.sendRedirect (request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/WebApplication/Resultat.jsp?rep="+"true" + "?emplacement="+emplacement_random + "?idRes=" + num);

String res = request.getParameter("rep");
sc.log("res = " + res + "\n");
if(res.trim().equals("true".trim())) 
{

    String emplacement = request.getParameter("emplacement");
    String id = request.getParameter("idRes");
        sc.log("emplacement = "+ emplacement  + "\n");
    sc.log("id" + id + "\n");%> <td> C'est OK, le numéro de réservation est :<%=id%> </td>
    <td> L'emplacement est : <%=emplacement%> </td>
<%}
else
{
    %>Désolé plus d'emplacement dispo... <%
}
%>

    </body>
</html>
