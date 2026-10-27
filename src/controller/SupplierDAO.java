package controller;

import config.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import model.Supplier;

public class SupplierDAO implements DAO<Supplier> {

    private Connection cnx = MyConnexion.getInstance().getConnection();

    @Override
    public boolean insert(Supplier s) {
        // 1. Génération de l'UUID
        if (s.getId() == null) s.setId(UUID.randomUUID());
        
        String sql = "INSERT INTO supplier (id, fullname, email, tel, VAT) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            // 2. Conversion UUID -> String
            ps.setString(1, s.getId().toString()); 
            ps.setString(2, s.getFullname());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getTel());
            ps.setString(5, s.getVAT());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Erreur Insert Supplier: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Supplier s) {
        String sql = "UPDATE supplier SET fullname = ?, email = ?, tel = ?, VAT = ? WHERE id = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, s.getFullname());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getTel());
            ps.setString(4, s.getVAT());
            // Conversion UUID -> String
            ps.setString(5, s.getId().toString()); 
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Erreur Update Supplier: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM supplier WHERE id = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Erreur Delete Supplier: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public Supplier findById(String id) {
        String sql = "SELECT * FROM supplier WHERE id = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Supplier(
                    // 3. Conversion String -> UUID
                    UUID.fromString(rs.getString("id")), 
                    rs.getString("fullname"),
                    rs.getString("email"),
                    rs.getString("tel"),
                    rs.getString("VAT")
                );
            }
        } catch (SQLException ex) {
            System.err.println("Erreur FindById Supplier: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Supplier> select() {
        ArrayList<Supplier> list = new ArrayList<>();
        String sql = "SELECT * FROM supplier";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Supplier(
                    // 4. Conversion String -> UUID
                    UUID.fromString(rs.getString("id")), 
                    rs.getString("fullname"),
                    rs.getString("email"),
                    rs.getString("tel"),
                    rs.getString("VAT")
                ));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur Select Supplier: " + ex.getMessage());
        }
        return list;
    }
}