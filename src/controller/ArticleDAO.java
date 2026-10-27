package controller;

import config.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import model.Article;
import model.Category;

public class ArticleDAO implements DAO<Article> {

    private Connection cnx = MyConnexion.getInstance().getConnection();

    @Override
    public boolean insert(Article a) {
        if (a.getId() == null) a.setId(UUID.randomUUID());
        String sql = "INSERT INTO article (id, label, description, qty, prixAchat, prixVente, category_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, a.getId().toString());
            ps.setString(2, a.getLabel());
            ps.setString(3, a.getDescription());
            ps.setDouble(4, a.getQty());
            ps.setDouble(5, a.getPrixAchat());
            ps.setDouble(6, a.getPrixVente());
            ps.setString(7, a.getCategory() != null ? a.getCategory().getId().toString() : null);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Erreur Insert Article: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Article a) {
        String sql = "UPDATE article SET label = ?, description = ?, qty = ?, prixAchat = ?, prixVente = ?, category_id = ? WHERE id = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, a.getLabel());
            ps.setString(2, a.getDescription());
            ps.setDouble(3, a.getQty());
            ps.setDouble(4, a.getPrixAchat());
            ps.setDouble(5, a.getPrixVente());
            ps.setString(6, a.getCategory() != null ? a.getCategory().getId().toString() : null);
            ps.setString(7, a.getId().toString());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Erreur Update Article: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM article WHERE id = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Erreur Delete Article: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public Article findById(String id) {
        String sql = "SELECT * FROM article WHERE id = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Category cat = new Category();
                if (rs.getString("category_id") != null) {
                    cat.setId(UUID.fromString(rs.getString("category_id")));
                } else {
                    cat = null;
                }
                return new Article(
                    UUID.fromString(rs.getString("id")),
                    rs.getString("label"),
                    rs.getString("description"),
                    rs.getDouble("qty"),
                    rs.getDouble("prixAchat"),
                    rs.getDouble("prixVente"),
                    cat
                );
            }
        } catch (SQLException ex) {
            System.err.println("Erreur FindById Article: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Article> select() {
        ArrayList<Article> list = new ArrayList<>();
        String sql = "SELECT * FROM article";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category cat = new Category();
                if (rs.getString("category_id") != null) {
                    cat.setId(UUID.fromString(rs.getString("category_id")));
                } else {
                    cat = null;
                }
                list.add(new Article(
                    UUID.fromString(rs.getString("id")),
                    rs.getString("label"),
                    rs.getString("description"),
                    rs.getDouble("qty"),
                    rs.getDouble("prixAchat"),
                    rs.getDouble("prixVente"),
                    cat
                ));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur Select Article: " + ex.getMessage());
        }
        return list;
    }
}