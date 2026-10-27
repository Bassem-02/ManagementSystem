package model;

import java.util.UUID;

public class Supplier {
    private UUID id;
    private String fullname;
    private String email;
    private String tel;
    private String VAT;

    public Supplier() {}

    public Supplier(String fullname, String email, String tel, String VAT) {
        this.fullname = fullname;
        this.email = email;
        this.tel = tel;
        this.VAT = VAT;
    }

    public Supplier(UUID id, String fullname, String email, String tel, String VAT) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.tel = tel;
        this.VAT = VAT;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTel() { return tel; }
    public void setTel(String tel) { this.tel = tel; }
    public String getVAT() { return VAT; }
    public void setVAT(String VAT) { this.VAT = VAT; }

    @Override
    public String toString() {
        return "Supplier{id=" + id + ", fullname=" + fullname + "}";
    }
}