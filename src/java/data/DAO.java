package data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Vector;

public class DAO {
    //gettAll
    public Vector<Stock> get_AllStock(Connection co) throws Exception{
        Vector<Stock> list = new Vector<Stock>();
        try {
            Statement stmt = co.createStatement();
            String sql = "select stock.produit,stock.quantite,produit.price from stock join produit on produit.name=stock.produit";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Stock stock = new Stock();
                stock.setProduit(rs.getString(1));
                stock.setQuantite(rs.getFloat(2));
                stock.setPrix(rs.getFloat(3));
                list.add(stock);
            }
        } catch (Exception e) {
                throw new Exception(e.getMessage());
        }finally {
                if(co != null) co.close();
        }
        return list;
    }
    //
    public Vector<Achat> get_AllAchat(Connection co)throws Exception{
        Vector<Achat> list = new Vector<Achat>();
        try {
            Statement stmt = co.createStatement();
            String sql = "select * from achat order by daty desc";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Achat indice = new Achat();
                indice.setProduit(rs.getString(1));
                indice.setPrice(rs.getFloat(2));
                indice.setQuantite(rs.getInt(3));
                indice.setDaty(rs.getString(4));
                list.add(indice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public Vector<Fabrication> get_Allfabrication(Connection co)throws Exception{
        Vector<Fabrication> list = new Vector<Fabrication>();
        try {
            Statement stmt = co.createStatement();
            String sql = "select * from fabrication order by daty desc";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Fabrication indice = new Fabrication();
                indice.setProduit(rs.getString(1));
                indice.setQuantite(rs.getInt(2));
                indice.setDaty(rs.getString(3));
                indice.setPrice(rs.getFloat(4));
                list.add(indice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    /////////////////////////////////////////
    /*public Vector<Compo> get_list(Connection co,String produit) throws Exception{
        Vector<Compo> list = new Vector<Compo>();
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
        System.out.println("getlist");
        return list;
        
    }
    ////////////////////////////////
    public Vector<Vector<Object>> effectif_par_produit(Connection co,Fabrication fab_produit_fini) throws Exception{
        Vector<Vector<Object>> list = new  Vector<Vector<Object>>();
        Vector<Compo> list_compo = get_list(co, fab_produit_fini.getProduit());
        for(int i=0 ; i < list_compo.size() ; i++) {
            Vector<Object> indice = new Vector<Object>();
            float quantite = list_compo.get(i).getQuantite() * fab_produit_fini.getQuantite();
            indice.add(list_compo.get(i).getComposition());
            indice.add(quantite);
            list.add(indice);
        }
        return list;
    }
    public Stock getStock(Connection co,Object produit)throws Exception{
        Stock indice = new Stock();
        try {
                Statement stmt = co.createStatement();
                String sql = "select * from stock where produit='"+produit+"'";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()) { 
                    indice.setProduit(rs.getString(1));
                    indice.setQuantite(rs.getFloat(2));
                }
        } catch (Exception e) {
                throw new Exception(e.getMessage());
        }
        return indice;
    }
    //fonction fabrication
    public void insertfab(Connection co,Fabrication fab) throws Exception{
        Statement stmt = co.createStatement();
        String sql = "insert fabrication into values('"+fab.getProduit()+"',"+fab.getQuantite()+",'"+fab.getDaty()+"')";
        stmt.executeUpdate(sql);
        stmt.close();
    }
    //
    public void update_stock(Connection co,String produit,float quantite)throws Exception{
        try {
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        Statement stmt = co.createStatement();
        String sql = "update stock set quantite="+quantite+" where produit='"+produit+"'";
        System.out.println(sql);
        stmt.executeUpdate(sql);
        stmt.close();
    }
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean check_restStock_Insert_fabrication(Connection co,Fabrication fab_produit_fini) throws Exception{
        boolean result = true;
        //composition sy quantite ilainy
        Vector<Vector<Object>> compo_quantite = effectif_par_produit(co, fab_produit_fini);
        Vector<Float> insert_rest_quantite = new Vector<Float>();
        for(int i=0 ; i< compo_quantite.size() ; i++) { 
            Stock stock = getStock(co, compo_quantite.get(i).get(0));
            float valeur = stock.getQuantite() - ( (float) compo_quantite.get(i).get(1) );
            insert_rest_quantite.add(valeur);
            if( valeur < 0) {
                    result = false;
                    System.out.println(compo_quantite.get(i).get(0));
                    System.out.println(valeur);
            }else{
                    System.out.println("mety  : "+compo_quantite.get(i).get(0));
                    System.out.println(valeur);
            }
        }
        if(result == true) {
            for(int i=0 ; i< compo_quantite.size() ; i++) { 
               float nouv_quantite = insert_rest_quantite.get(i);
               String nom_produit_premiere = (String) compo_quantite.get(i).get(0);
               update_stock(co, nom_produit_premiere, nouv_quantite);
               insertfab(co, fab_produit_fini);
            }
        }
        return result;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //pmp
    */
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
    public Vector<Vector<Object>> effectif_par_produit(Connection co,Fabrication fab_produit_fini) throws Exception{
        Vector<Vector<Object>> list = new  Vector<Vector<Object>>();
        Vector<Compo> list_compo = get_list(co, fab_produit_fini.getProduit());
        for(int i=0 ; i < list_compo.size() ; i++) {
            Vector<Object> indice = new Vector<Object>();
            float quantite = list_compo.get(i).getQuantite() * fab_produit_fini.getQuantite();
            indice.add(list_compo.get(i).getComposition());
            indice.add(quantite);
            list.add(indice);
        }
        return list;
    }
    public void change_stock_production(Connection co,String produitfini,float quantite)throws Exception{
        float last_quantite = 0;
        float nouv_quantite = 0;
        try {
            Statement stmt = co.createStatement();
            String sql = "select quantite from stock_production where produit='"+produitfini+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                last_quantite = rs.getFloat(1);
            }
            nouv_quantite = last_quantite + quantite;
            String sql1 = "update stock_production set quantite="+nouv_quantite+" where produit='"+produitfini+"'";
            stmt.executeUpdate(sql1);
            rs.close();
            stmt.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public void update_stock(Connection co,String produit,float quantite)throws Exception{
        try {
            Statement stmt = co.createStatement();
            String sql = "update stock set quantite="+quantite+" where produit='"+produit+"'";
            //System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public Stock getStock(Connection co,Object produit)throws Exception{
        Stock indice = new Stock();
        try {
            Statement stmt = co.createStatement();
            String sql = "select * from stock where produit='"+produit+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) { 
               indice.setProduit(rs.getString(1));
               indice.setQuantite(rs.getFloat(2));
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return indice;
    }
    public float prix_Revient(Connection co,String produitfini)throws Exception{
        Vector<Compo> composition = get_list(co, produitfini);
        float val = 0;
        for(int i= 0 ; i < composition.size() ; i++){
            val = val + (composition.get(i).getPrice() * composition.get(i).getQuantite());
        }
        return val;
    }
    public void insertfab(Connection co,Fabrication fab,float price) throws Exception{
        Statement stmt = co.createStatement();
        String sql = "insert into fabrication values('"+fab.getProduit()+"',"+fab.getQuantite()+",'"+fab.getDaty()+"',"+price+")";
        stmt.executeUpdate(sql);
        stmt.close();
    }
    public boolean check_rest_produit_Stock(Connection co,Fabrication fab_produit_fini) throws Exception{
        boolean result = true;
        //composition sy quantite ilainy
        Vector<Vector<Object>> compo_quantite = effectif_par_produit(co, fab_produit_fini);
        Vector<Float> insert_rest_quantite = new Vector<Float>();
        for(int i=0 ; i< compo_quantite.size() ; i++) { 
            Stock stock = getStock(co, compo_quantite.get(i).get(0));
            float valeur = stock.getQuantite() - ( (float) compo_quantite.get(i).get(1) );
            insert_rest_quantite.add(valeur);
            if( valeur < 0) {
                result = false;
                System.out.println(compo_quantite.get(i).get(0));
                System.out.println(valeur);
            }else{
                System.out.println("mety  : "+compo_quantite.get(i).get(0));
                System.out.println(valeur);
            }
        }
        if(result == true) {
            for(int i=0 ; i< compo_quantite.size() ; i++) { 
               float nouv_quantite = insert_rest_quantite.get(i);
               String nom_produit_premiere = compo_quantite.get(i).get(0).toString();
               update_stock(co, nom_produit_premiere, nouv_quantite);
            }
            change_stock_production(co, fab_produit_fini.getProduit(), fab_produit_fini.getQuantite());
            float revient = prix_Revient(co, fab_produit_fini.getProduit());
            insertfab(co, fab_produit_fini,revient);
        }
        return result;
    }
    public float getPrix_M_P(Connection co,String produit)throws Exception{
        float result = 0 ;
        try {
            Statement stmt = co.createStatement();
            String sql = "select price from produit where name='"+produit+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                result = rs.getFloat(1);
            }
        } catch (Exception e) {
            throw  new Exception(e.getMessage());
        }finally{
            if(co != null) co.close();
        }
        return result;
    }
}
