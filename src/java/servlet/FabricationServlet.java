package servlet;

import data.DAO;
import data.Fabrication;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sgbd.Connexion;

/**
 *
 * @author Tafitasoa-P15B-140
 */
public class FabricationServlet extends HttpServlet {


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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection co;
        PrintWriter out = response.getWriter();
        try {
            Connexion con = new Connexion();
            co = con.getconnection();
            String nom = request.getParameter("nom");
            int quantite = Integer.parseInt(request.getParameter("quantite"));
            String daty = request.getParameter("daty");
            Fabrication nouv_fab = new Fabrication();
            nouv_fab.setProduit(nom);
            nouv_fab.setQuantite(quantite);
            nouv_fab.setDaty(daty);
            RequestDispatcher dispat;
            boolean result;
            try {
                DAO da = new DAO();
                result = da.check_rest_produit_Stock(co, nouv_fab);
                out.print(result);
                if(result == false ){
                    request.setAttribute("resultat", result);
                    dispat = request.getRequestDispatcher("Fabrication.jsp");
                    dispat.forward(request, response);
                }else{
                    dispat = request.getRequestDispatcher("ResultFabrication.jsp");
                    dispat.forward(request, response);
                }
            } catch (Exception e) {
                String fonctiondiso = e.getMessage();
                request.setAttribute("fonctiondiso", fonctiondiso);
                this.getServletContext().getRequestDispatcher("/Fabrication.jsp").forward(request, response);
            }
            
            
        } catch (Exception e) {
            String message = e.getMessage();
            request.setAttribute("message", message);
            this.getServletContext().getRequestDispatcher("/Fabrication.jsp").forward(request, response);
        }
    }
}
