package model;

import java.util.UUID;

public class Article {
    private UUID id;
    private String label;
    private String description;
    private double qty;
    private double prixAchat;
    private double prixVente;
    private Category category; 

    public Article() {}

    public Article(String label, String description, double qty, double prixAchat, double prixVente, Category category) {
        this.label = label;
        this.description = description;
        this.qty = qty;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.category = category;
    }

    public Article(UUID id, String label, String description, double qty, double prixAchat, double prixVente, Category category) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.qty = qty;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.category = category;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getQty() { return qty; }
    public void setQty(double qty) { this.qty = qty; }
    public double getPrixAchat() { return prixAchat; }
    public void setPrixAchat(double prixAchat) { this.prixAchat = prixAchat; }
    public double getPrixVente() { return prixVente; }
    public void setPrixVente(double prixVente) { this.prixVente = prixVente; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    @Override
    public String toString() {
        String catName = (category != null) ? category.getName() : "Aucune";
        return "Article{id=" + id + ", label=" + label + ", qty=" + qty + ", category=" + catName + "}";
    }
}