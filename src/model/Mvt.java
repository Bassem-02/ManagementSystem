package model;

import java.util.UUID;

public class Mvt {
    
    public enum TypeMvt {
        INPUT,
        OUTPUT
    }

    private UUID id;
    private String dateTime;
    private String note;
    private TypeMvt type;
    private double qty;
    private UUID articleId;  
    private UUID supplierId; 

    public Mvt() {}

    public Mvt(String dateTime, String note, TypeMvt type, double qty, UUID articleId, UUID supplierId) {
        this.dateTime = dateTime;
        this.note = note;
        this.type = type;
        this.qty = qty;
        this.articleId = articleId;
        this.supplierId = supplierId;
    }

    public Mvt(UUID id, String dateTime, String note, TypeMvt type, double qty, UUID articleId, UUID supplierId) {
        this.id = id;
        this.dateTime = dateTime;
        this.note = note;
        this.type = type;
        this.qty = qty;
        this.articleId = articleId;
        this.supplierId = supplierId;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getDateTime() { return dateTime; }
    public void setDateTime(String dateTime) { this.dateTime = dateTime; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public TypeMvt getType() { return type; }
    public void setType(TypeMvt type) { this.type = type; }
    public double getQty() { return qty; }
    public void setQty(double qty) { this.qty = qty; }
    public UUID getArticleId() { return articleId; }
    public void setArticleId(UUID articleId) { this.articleId = articleId; }
    public UUID getSupplierId() { return supplierId; }
    public void setSupplierId(UUID supplierId) { this.supplierId = supplierId; }

    @Override
    public String toString() {
        return "Mvt{id=" + id + ", type=" + type + ", qty=" + qty + ", articleId=" + articleId + "}";
    }
}