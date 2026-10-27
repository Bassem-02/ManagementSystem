package controller;

import config.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;
import model.Mvt;

public class MvtDAO implements DAO<Mvt> {

    private Connection cnx = MyConnexion.getInstance().getConnection();

    @Override
    public boolean insert(Mvt m) {
        if (m.getId() == null) m.setId(UUID.randomUUID());
        String sql = "INSERT INTO mvt (id, dateTime, note, type, qty, article_id, supplier_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, m.getId().toString());
            ps.setTimestamp(2, Timestamp.valueOf(m.getDateTime())); 
            ps.setString(3, m.getNote());
            ps.setString(4, m.getType().name());
            ps.setDouble(5, m.getQty());
            ps.setString(6, m.getArticleId() != null ? m.getArticleId().toString() : null);
            ps.setString(7, m.getSupplierId() != null ? m.getSupplierId().toString() : null);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Erreur Insert Mvt: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Mvt m) {
        String sql = "UPDATE mvt SET dateTime = ?, note = ?, type = ?, qty = ?, article_id = ?, supplier_id = ? WHERE id = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(m.getDateTime()));
            ps.setString(2, m.getNote());
            ps.setString(3, m.getType().name());
            ps.setDouble(4, m.getQty());
            ps.setString(5, m.getArticleId() != null ? m.getArticleId().toString() : null);
            ps.setString(6, m.getSupplierId() != null ? m.getSupplierId().toString() : null);
            ps.setString(7, m.getId().toString());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Erreur Update Mvt: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM mvt WHERE id = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Erreur Delete Mvt: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public Mvt findById(String id) {
        String sql = "SELECT * FROM mvt WHERE id = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Timestamp ts = rs.getTimestamp("dateTime");
                String dateStr = (ts != null) ? ts.toString() : "";
                String artIdStr = rs.getString("article_id");
                UUID artId = (artIdStr != null) ? UUID.fromString(artIdStr) : null;
                String supIdStr = rs.getString("supplier_id");
                UUID supId = (supIdStr != null) ? UUID.fromString(supIdStr) : null;

                return new Mvt(
                    UUID.fromString(rs.getString("id")),
                    dateStr,
                    rs.getString("note"),
                    Mvt.TypeMvt.valueOf(rs.getString("type")),
                    rs.getDouble("qty"),
                    artId,
                    supId
                );
            }
        } catch (SQLException ex) {
            System.err.println("Erreur FindById Mvt: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Mvt> select() {
        ArrayList<Mvt> list = new ArrayList<>();
        String sql = "SELECT * FROM mvt";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Timestamp ts = rs.getTimestamp("dateTime");
                String dateStr = (ts != null) ? ts.toString() : "";
                String artIdStr = rs.getString("article_id");
                UUID artId = (artIdStr != null) ? UUID.fromString(artIdStr) : null;
                String supIdStr = rs.getString("supplier_id");
                UUID supId = (supIdStr != null) ? UUID.fromString(supIdStr) : null;

                list.add(new Mvt(
                    UUID.fromString(rs.getString("id")),
                    dateStr,
                    rs.getString("note"),
                    Mvt.TypeMvt.valueOf(rs.getString("type")),
                    rs.getDouble("qty"),
                    artId,
                    supId
                ));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur Select Mvt: " + ex.getMessage());
        }
        return list;
    }

    public ArrayList<Mvt> findByArticle(String articleId) {
        ArrayList<Mvt> list = new ArrayList<>();
        String sql = "SELECT * FROM mvt WHERE article_id = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, articleId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Timestamp ts = rs.getTimestamp("dateTime");
                String dateStr = (ts != null) ? ts.toString() : "";
                String artIdStr = rs.getString("article_id");
                UUID artId = (artIdStr != null) ? UUID.fromString(artIdStr) : null;
                String supIdStr = rs.getString("supplier_id");
                UUID supId = (supIdStr != null) ? UUID.fromString(supIdStr) : null;

                list.add(new Mvt(
                    UUID.fromString(rs.getString("id")),
                    dateStr,
                    rs.getString("note"),
                    Mvt.TypeMvt.valueOf(rs.getString("type")),
                    rs.getDouble("qty"),
                    artId,
                    supId
                ));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur FindByArticle: " + ex.getMessage());
        }
        return list;
    }

    public ArrayList<Mvt> findBySupplier(String supplierId) {
        ArrayList<Mvt> list = new ArrayList<>();
        String sql = "SELECT * FROM mvt WHERE supplier_id = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, supplierId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Timestamp ts = rs.getTimestamp("dateTime");
                String dateStr = (ts != null) ? ts.toString() : "";
                String artIdStr = rs.getString("article_id");
                UUID artId = (artIdStr != null) ? UUID.fromString(artIdStr) : null;
                String supIdStr = rs.getString("supplier_id");
                UUID supId = (supIdStr != null) ? UUID.fromString(supIdStr) : null;

                list.add(new Mvt(
                    UUID.fromString(rs.getString("id")),
                    dateStr,
                    rs.getString("note"),
                    Mvt.TypeMvt.valueOf(rs.getString("type")),
                    rs.getDouble("qty"),
                    artId,
                    supId
                ));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur FindBySupplier: " + ex.getMessage());
        }
        return list;
    }

    public ArrayList<Mvt> findByType(Mvt.TypeMvt type) {
        ArrayList<Mvt> list = new ArrayList<>();
        String sql = "SELECT * FROM mvt WHERE type = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, type.name());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Timestamp ts = rs.getTimestamp("dateTime");
                String dateStr = (ts != null) ? ts.toString() : "";
                String artIdStr = rs.getString("article_id");
                UUID artId = (artIdStr != null) ? UUID.fromString(artIdStr) : null;
                String supIdStr = rs.getString("supplier_id");
                UUID supId = (supIdStr != null) ? UUID.fromString(supIdStr) : null;

                list.add(new Mvt(
                    UUID.fromString(rs.getString("id")),
                    dateStr,
                    rs.getString("note"),
                    Mvt.TypeMvt.valueOf(rs.getString("type")),
                    rs.getDouble("qty"),
                    artId,
                    supId
                ));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur FindByType: " + ex.getMessage());
        }
        return list;
    }
}