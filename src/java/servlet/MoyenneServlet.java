
package servlet;

import data.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sgbd.Connexion;

public class MoyenneServlet extends HttpServlet {
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
        String produit = (String) request.getParameter("produit");
        DAO da = new DAO();
        Connection co;
        try {
            Connexion con = new Connexion(); 
            co = con.getconnection();
            float pmp = da.getPrix_M_P(co, produit);
            request.setAttribute("produit", produit);
            request.setAttribute("prix", pmp);
            RequestDispatcher dispat = request.getRequestDispatcher("./affichage.jsp");
            dispat.forward(request, response);
        }catch(Exception e){
            e.printStackTrace();
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
