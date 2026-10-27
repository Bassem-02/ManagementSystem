package controller;

import config.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import model.Category;

public class CategoryDAO implements DAO<Category> {

    private Connection cnx = MyConnexion.getInstance().getConnection();

    @Override
    public boolean insert(Category c) {
        if (c.getId() == null) {
            c.setId(UUID.randomUUID());
        }
        
        String sql = "INSERT INTO category (id, name, description) VALUES (?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, c.getId().toString()); 
            ps.setString(2, c.getName());
            ps.setString(3, c.getDescription());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Erreur Insert Category: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Category c) {
        String sql = "UPDATE category SET name = ?, description = ? WHERE id = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, c.getName());
            ps.setString(2, c.getDescription());
            // ✅ Conversion UUID -> String pour MySQL
            ps.setString(3, c.getId().toString()); 
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Erreur Update Category: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM category WHERE id = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Erreur Delete Category: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public Category findById(String id) {
        String sql = "SELECT * FROM category WHERE id = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Category(
                    // ✅ Conversion String (SQL) -> UUID (Java)
                    UUID.fromString(rs.getString("id")), 
                    rs.getString("name"),
                    rs.getString("description")
                );
            }
        } catch (SQLException ex) {
            System.err.println("Erreur FindById Category: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Category> select() {
        ArrayList<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM category";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(
                    // ✅ Conversion String (SQL) -> UUID (Java)
                    UUID.fromString(rs.getString("id")), 
                    rs.getString("name"),
                    rs.getString("description")
                ));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur Select Category: " + ex.getMessage());
        }
        return list;
    }
}