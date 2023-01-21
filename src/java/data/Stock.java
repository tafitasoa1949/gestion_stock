package data;

public class Stock {
	String produit;
	float quantite;
        float prix;
	public String getProduit() {
		return produit;
	}
	public void setProduit(String produit) {
		this.produit = produit;
	}
	public float getQuantite() {
		return quantite;
	}
	public void setQuantite(float quantite) {
		this.quantite = quantite;
	}
        public float getPrix() {
            return prix;
        }
        public void setPrix(float prix) {
            this.prix = prix;
        }
        
}
