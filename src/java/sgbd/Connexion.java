package sgbd;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {
	public Connection getconnection() throws Exception{
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gestion_stock", "postgres", "admin");
        }catch (Exception c){
            c.printStackTrace();
            c.getMessage();
        }
        return con;
    }
}
