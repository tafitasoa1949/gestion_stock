package data;

public class Compo extends DAO{
	private String produit;
	private String composition;
	private float quantite;
	private float price;
	public String getProduit() {
		return produit;
	}
	public void setProduit(String produit) {
		this.produit = produit;
	}
	public String getComposition() {
		return composition;
	}
	public void setComposition(String composition) {
		this.composition = composition;
	}
	public float getQuantite() {
		return quantite;
	}
	public void setQuantite(float quantite) {
		this.quantite = quantite;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Compo() {}
	public Compo(String pro,String comp,float quan,float prix) {
		this.setProduit(pro);
		this.setComposition(comp);
		this.setQuantite(quan);
		this.setPrice(prix);
	}
}
