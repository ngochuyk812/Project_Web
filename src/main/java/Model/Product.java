package Model;

import java.io.Serializable;
import java.sql.Date;

public class Product implements Serializable {
    private int id;
    private int idVendor;
    private String name;
    private String title;
    private String content ;
    private String body;
    private String made;
    private int yearOfManuFacture;
    private String fuel;
    private float price;
    private Date createAt;
    private int status;
    private int quantity;



    public Product(int idPost, String title, int idVendor, String content, String body, String made, int yearOfManuFacture, String fuel, float price, Date createAt, int status, int quantity) {
        this.id = idPost;
        this.title= title;
        this.idVendor = idVendor;
        this.name = name;
        this.content = content;
        this.body = body;
        this.made = made;
        this.yearOfManuFacture = yearOfManuFacture;
        this.fuel = fuel;
        this.price = price;
        this.createAt = createAt;
        this.status = status;
        this.quantity=quantity;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int idPost) {
        this.id = idPost;
    }

    public int getIdVendor() {
        return idVendor;
    }

    public void setIdVendor(int idVendor) {
        this.idVendor = idVendor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMade() {
        return made;
    }

    public void setMade(String made) {
        this.made = made;
    }

    public int getYearOfManuFacture() {
        return yearOfManuFacture;
    }

    public void setYearOfManuFacture(int yearOfManuFacture) {
        this.yearOfManuFacture = yearOfManuFacture;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Post{" +
                "idPost=" + id +
                ", idVendor=" + idVendor +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", body='" + body + '\'' +
                ", made=" + made +
                ", yearOfManuFacture=" + yearOfManuFacture +
                ", fuel=" + fuel +
                ", price=" + price +
                ", createAt=" + createAt +
                ", status=" + status +
                '}';
    }
}
