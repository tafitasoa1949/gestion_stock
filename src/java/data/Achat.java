
package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Achat{
    String produit;
    float price;
    float quantite;
    String daty;

    public String getProduit() {
        return produit;
    }

    public float getPrice() {
        return price;
    }

    public float getQuantite() {
        return quantite;
    }

    public String getDaty() {
        return daty;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    public void setDaty(String daty) {
        this.daty = daty;
    }
    public Achat(){}
    public Achat(String pro,float pric,int qua,String da){
        this.setProduit(pro);
        this.setPrice(pric);
        this.setQuantite(qua);
        this.setDaty(da);
    }
    public Vector<Compo> get_list(Connection co,String produit) throws Exception{
		Vector<Compo> list = new Vector<Compo>();
		try {
			Statement stmt = co.createStatement();
			String sql = "select * from compo where produitmere='"+produit+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Compo indice = new Compo();
				indice.setProduit(rs.getString(1));
				indice.setComposition(rs.getString(2));
				indice.setQuantite(rs.getFloat(3));
				indice.setPrice(rs.getFloat(4));
				list.add(indice);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return list;
    }
    public float[] histo_achat(Connection co,String produit) throws Exception{
        float[] last_prix_quant = new float[2];
        float price = 0;
        float quantite = 0;
        try {
            Statement stmt = co.createStatement();
            String sql = "select produit.price,stock.quantite from stock join produit on produit.name=stock.produit where stock.produit='"+produit+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                    price = rs.getFloat(1);
                    quantite = rs.getFloat(2);
                    last_prix_quant[0] = price;
                    last_prix_quant[1] = quantite;
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
                throw new Exception(e.getMessage());
        }
        return last_prix_quant;
    }
    public float Prix_M_Pondere(Connection co) throws Exception{
        float result = 0;
        float numerateur = 0;
        float denominateur = 0;
        float[] last_prix_quanti = histo_achat(co, this.getProduit());
        numerateur = ( last_prix_quanti[0] * last_prix_quanti[1] ) + ( this.getPrice() * this.getQuantite() );
        denominateur =  last_prix_quanti[1] + this.getQuantite() ;
        result = numerateur / denominateur;
        return result;
    }
    public void update_prix_unti(Connection co,float result_PMP) throws Exception{
            Statement stmt = co.createStatement();
            String sql = "update produit set price="+result_PMP+" where produit.name='"+this.getProduit()+"'";
            stmt.executeUpdate(sql);
    }
    //matiere premiere ilaina am fabrication
    
    public void addStock(Connection co,String produit,float stock_news)throws Exception{
        float last_stock = 0;
        float new_stock = 0;
        try {
            Statement stmt = co.createStatement();
            String sql = "select quantite from stock where produit='"+produit+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                last_stock = rs.getFloat(1);
            }
            rs.close();
            new_stock = last_stock + stock_news;
            String sql2 = "update stock set quantite="+new_stock+" where produit='"+produit+"'";
            stmt.executeUpdate(sql2);
            stmt.close();
        } catch (Exception e) {
            throw  new Exception(e.getMessage());
        }
    }
    
    public void insert (Connection co) throws Exception{
        try {
            Statement stmt = co.createStatement();
            float pmp = Prix_M_Pondere(co);
            System.out.println(pmp);
            update_prix_unti(co, pmp);
            System.err.println(pmp);
            addStock( co, this.getProduit(), this.getQuantite());
            float[] last_prix_quantite = histo_achat(co, this.getProduit());
            String sql ="insert into achat values('"+this.getProduit()+"',"+pmp+","+last_prix_quantite[1]+",'"+this.getDaty()+"')";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            throw  new Exception(e.getMessage());
        }finally{
            if (co != null ) co.close();
        }
    }
}
