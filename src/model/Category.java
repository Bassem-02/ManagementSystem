package model;

import java.util.ArrayList;
import java.util.UUID;

public class Category {
    private UUID id;
    private String name;
    private String description;
    
   
    private Category parent;             
    private ArrayList<Category> subCategories; 

    public Category() {
        this.subCategories = new ArrayList<>();
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
        this.subCategories = new ArrayList<>();
    }

    public Category(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.subCategories = new ArrayList<>();
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Category getParent() { return parent; }
    public void setParent(Category parent) { 
        this.parent = parent; 
        if (parent != null && !parent.getSubCategories().contains(this)) {
            parent.getSubCategories().add(this);
        }
    }

    public ArrayList<Category> getSubCategories() { return subCategories; }
    public void setSubCategories(ArrayList<Category> subCategories) { this.subCategories = subCategories; }
    
    public void addSubCategory(Category child) {
        if (!this.subCategories.contains(child)) {
            this.subCategories.add(child);
            child.setParent(this); // Le enfant pointe vers le père
        }
    }

    @Override
    public String toString() {
        return "Category{id=" + id + ", name=" + name + "}";
    }
}