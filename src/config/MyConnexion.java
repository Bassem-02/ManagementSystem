package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyConnexion {
    // Vérifie bien que le nom de ta base est "stockdb"
    private String url = "jdbc:mysql://localhost:3306/stockdb?autoReconnect=true&useSSL=false";
    private String login = "root";
    private String pwd = "";
    
    private static MyConnexion instance;  
    private static Connection connection;
    
    public static MyConnexion getInstance(){
        if(instance == null){
            instance = new MyConnexion();
        }
        return instance;
    }
    
    public static Connection getConnection() {
        return connection;
    }  
    
    private MyConnexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion à la base de données réussie !");
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("Erreur de connexion !");
            Logger.getLogger(MyConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}