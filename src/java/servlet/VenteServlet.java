/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import data.Vente;
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
public class VenteServlet extends HttpServlet {



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
        PrintWriter out = response.getWriter();
        Connection co;
        try {
            Connexion con = new Connexion();
            co = con.getconnection();
            String produit = (String)request.getParameter("nom");
            int quantite = Integer.parseInt(request.getParameter("quantite"));
            String daty = (String)request.getParameter("daty");
            Vente nouv_vente = new Vente();
            nouv_vente.setProduit(produit);
            nouv_vente.setQuantite(quantite);
            nouv_vente.setDaty(daty);
            RequestDispatcher dispat;
            boolean result = nouv_vente.hamarotra(co);
            if(result == false ){
                request.setAttribute("validation", result);
                dispat = request.getRequestDispatcher("Vente.jsp");
                dispat.forward(request, response);
            }else{
               dispat = request.getRequestDispatcher("ResultVente.jsp");
               dispat.forward(request, response); 
            }
            
        } catch (Exception e) {
            String message = e.getMessage();
            request.setAttribute("message", message);
            this.getServletContext().getRequestDispatcher("/Vente.jsp").forward(request, response);
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
