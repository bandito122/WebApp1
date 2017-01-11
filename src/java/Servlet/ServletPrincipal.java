/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import beans.BeanBDSql;
import beans.ConnectionOptions;
import beans.DataBaseAccessFactory;
import beans.IDataBaseAccess;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bobmastrolilli
 */
public class ServletPrincipal extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    BeanBDSql beanSql;
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext sc = getServletContext();
        sc.log("-- démarrage de la servlet ServletPrincipal");
        beanSql = ConnectToBd();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        java.io.PrintWriter out = response.getWriter();
        ServletContext sc = getServletContext();
        sc.log("-- passage par la servlet ControleDataCenter");

        String action = request.getParameter("action");
        sc.log("-- Valeur du paramètre action : " + action);
        
        System.out.println("Connexion à la bd ");
            
        if (action.equals("Connexion")) 
        {
 
            
            boolean etatCheckBox = request.getParameter("cb_id") != null;
            sc.log(("etat = " + etatCheckBox));
            if (etatCheckBox) 
            {
                sc.log("nouveau client");
                beanSql.InsertClient((String) request.getParameter("Login"), (String) request.getParameter("Password"));
                response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/WebApplication/HomePage.jsp");
            }
            
            else if (beanSql.CheckClient((String) request.getParameter("Login"), (String) request.getParameter("Password"))) 
            {
                System.out.println("SendRedictect vers   : " + request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/WebApplication/HomePage.jsp");
                response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/WebApplication/HomePage.jsp");
            }
            else
            {
                System.out.println("Mauvais Identifiant ou password...");
                response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/WebApplication/newjsp.jsp");
            }

        }
        if (action.equals("Demande de reservation"))
        {
            response.setContentType("text/html;charset=UTF-8");
       
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Reservation</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Bienvenu sur la page de reservation</h3>");
            out.println("<form action=\"/WebApplication/ServletPrincipal\"  method=\"GET\">");
        Vector v = new Vector();
        
        v = beanSql.getAllDestination() ;
        //sc.log("v = " + v);
        //v.add("Rome");
        //v.add("Bxl");
        //v.add("Milan");
        String choix;
        out.println("Choisissez la destination: <select name=\"cars\"> ");
        
        for(int i=0; i<v.size() ; i++)
        {
            System.out.println("Nom= " + v.get(i));
            out.println("\"<option value=\" " + v.get(i) +"\">"+v.get(i)+"</option> <br />\"");
            
        }
       
            out.println("</select >");
           
            out.println("<input type=\"submit\" name=\"action\" value=\"Reserver\" />");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
     
        }
        if (action.equals("Reserver"))
        {
            Random r = new Random();
            int valeur1 = 0+ r.nextInt(10 - 0);
            int valeur2 = 0+ r.nextInt(10 - 0);
            String emplacement_random;
            sc.log("destination = " + request.getParameter("cars"));
            emplacement_random = String.valueOf(valeur1) +"," + String.valueOf(valeur2);
            boolean check;
            check=beanSql.Check_IF_PLACE_IS_FREE_OL(emplacement_random);
            if (check == true)
            {
                sc.log("Emplacement recu = " + emplacement_random);
                sc.log("Destination pour recherche = " + request.getParameter("cars") );
                sc.log("Emplacement pour recherche = "+ emplacement_random);
                int num = beanSql.InsertReservationOnLine(request.getParameter("cars"),emplacement_random);
                sc.log("redirect = " + request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/WebApplication/Resultat.jsp?rep="+"true" + "&emplacement="+emplacement_random + "&idRes=" + num);
                            response.sendRedirect (request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/WebApplication/Resultat.jsp?rep="+"true" + "&emplacement="+emplacement_random + "&idRes=" + num);
            }
            else
            {
                 response.sendRedirect (request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/WebApplication/Resultat.jsp?rep="+"false" );
                sc.log("Il n'y a plus d'emplacement libre désolé! ");
            }
            
        }
        
        System.out.println("fin...");
    }
    public BeanBDSql ConnectToBd()
     {
        DataBaseAccessFactory dbaf = DataBaseAccessFactory.getInstance();
        IDataBaseAccess beanSql = dbaf.getDataBaseAcces("MySQL");
        ConnectionOptions options = new ConnectionOptions();
        options.addOption("host", "127.0.0.1");
        options.addOption("port", "8889");
        options.addOption("database", "BD_TRAFIC");
        options.addOption("user", "root");
        options.addOption("passwd", "root"); 
        beanSql.Connect(options);
        return (BeanBDSql)beanSql;
     }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ServletPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ServletPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
