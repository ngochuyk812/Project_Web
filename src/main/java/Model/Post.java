package Model;

import java.io.Serializable;
import java.sql.Date;
import java.util.regex.Pattern;

public class Post implements Serializable {
    private int idPost;
    private int idVendor;
    private String name;
    private String title;
    private String content ;
    private String body;
    private int made;
    private int yearOfManuFacture;
    private int fuel;
    private float price;
    private Date createAt;
    private int status;
    private int quantity;



    public Post(int idPost, String title, int idVendor, String name, String content, String body, int made, int yearOfManuFacture, int fuel, float price, Date createAt, int status, int quantity) {
        this.idPost = idPost;
        this.title=title;
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

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
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

    public int getMade() {
        return made;
    }

    public void setMade(int made) {
        this.made = made;
    }

    public int getYearOfManuFacture() {
        return yearOfManuFacture;
    }

    public void setYearOfManuFacture(int yearOfManuFacture) {
        this.yearOfManuFacture = yearOfManuFacture;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
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
                "idPost=" + idPost +
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
