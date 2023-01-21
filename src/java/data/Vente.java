/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author Tafitasoa-P15B-140
 */
public class Vente {
   String produit;
   int quantite;
   String daty;
   public String getProduit() {
       return produit;
   }
   public int getQuantite() {
       return quantite;
   }
   public String getDaty() {
       return daty;
   }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setDaty(String daty) {
        this.daty = daty;
    }
    public Vector<Vector<Object>> getStock_production(Connection co)throws Exception{
        Vector<Vector<Object>> list = new Vector<Vector<Object>>();
        Statement stmt = co.createStatement();
        String sql = "select * from stock_production";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            Vector<Object> indice = new Vector<Object>();
            String produit = rs.getString(1);
            int isany = rs.getInt(2);
            indice.add(produit);
            indice.add(isany);
            list.add(indice);
        }
        rs.close();
        stmt.close();
        return list;
    }
    public void update_stock_production(Connection co,String produit,float nouv_quantite) throws Exception{
        try {
            Statement stmt = co.createStatement();
            String sql = " update stock_production set quantite ="+nouv_quantite+" where produit='"+produit+"'";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public boolean hamarotra(Connection co)throws Exception{
        boolean decision = false;
        String sql = "select quantite from stock_production where produit = '"+this.getProduit()+"'";
        Statement stmt = co.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        float last_quantite = 0;
        while(rs.next()){
            last_quantite = rs.getInt(1);
        }
        float valeur = last_quantite - this.getQuantite();
        if( valeur > 0 || valeur == 0){
            decision = true;
            float nouv = last_quantite - this.getQuantite();
            update_stock_production(co, this.getProduit(), nouv);
        }else{
            decision = false;
        }
        return decision;
    }
}
