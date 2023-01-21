
package servlet;

import data.Achat;
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

public class AchatServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection co;
        try {
            Connexion con = new Connexion(); 
            co = con.getconnection();
            String nom = (String) request.getParameter("nom");
            float price = Float.parseFloat(request.getParameter("prix"));
            float quantite = Float.parseFloat(request.getParameter("quantite"));
            String daty = (String) request.getParameter("daty");
            Achat nouv = new Achat();
            nouv.setProduit(nom);
            nouv.setPrice(price);
            nouv.setQuantite(quantite);
            nouv.setDaty(daty);
            nouv.insert(co);
            RequestDispatcher dispat = request.getRequestDispatcher("index.jsp");
            dispat.forward(request, response);
        } catch (Exception ex) {
            String message = ex.getMessage();
            request.setAttribute("message", message);
            this.getServletContext().getRequestDispatcher("/Achat.jsp").forward(request, response);
        }
        
    }

    /**
     *
     * @return
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
