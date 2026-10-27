package config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CRUD {
    
    MyConnexion mc = MyConnexion.getInstance();
    
    // INSERT
    public boolean executeInsert(String sql){ 
        try {
            Statement statement = mc.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Insertion effectuée avec succès");
            return true;
        } catch (SQLException ex) {
            System.err.println("Erreur d'Insertion");
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    // UPDATE
    public boolean executeUpdate(String sql) {
        try {
            Statement statement = MyConnexion.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Modification effectuée avec succès");
            return true;
        } catch (SQLException ex) {
            System.err.println("Erreur de Modification");
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    // DELETE
    public boolean executeDelete(String sql) {
        try {
            Statement statement = MyConnexion.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Suppression effectuée avec succès");
            return true;
        } catch (SQLException ex) {
            System.err.println("Erreur de Suppression");
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public ResultSet executeSelect(String sql) {
        try {
            Statement statement = MyConnexion.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("Sélection effectuée !");
            return rs;
        } catch (SQLException ex) {
            System.err.println("Erreur de Sélection !");
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ResultSet executeSearch(String sql) {
        return executeSelect(sql);
    }
}