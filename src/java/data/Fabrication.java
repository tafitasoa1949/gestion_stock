package data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Fabrication extends DAO{
	String produit;
	int quantite;
	String daty;
        float price;
	public String getProduit() {
		return produit;
	}
	public void setProduit(String produit) {
		this.produit = produit;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public String getDaty() {
		return daty;
	}
	public void setDaty(String daty) {
		this.daty = daty;
	}

        public void setPrice(float price) {
            this.price = price;
        }
        
        public float getPrice() {
            return price;
        }
        
        public Fabrication(){}
	public Fabrication(String pro,int qua,String da,float prix) {
		this.setProduit(pro);
		this.setQuantite(qua);
		this.setDaty(da);
                this.setPrice(prix);
	}      
}
