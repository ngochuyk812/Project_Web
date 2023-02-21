package Model;

import java.io.Serializable;
import java.util.regex.Pattern;

public class Post implements Serializable {
    private int idPost;
    private String title;
    private String content;
    private String body ;
    private String made;
    private String images;
    private int gear;
    private int idCompany;
    private int yearOfManuFacture;
    private int status;
    private String fuel;
    private float price;
    private int quantity;





    public Post(int idPost, String title, String content, String body, String made, String images, int gear, int idCompany, int yearOfManuFacture, int status,  String fuel, float price, int quantity) {
        this.idPost = idPost;
        this.title = title;
        this.content = content;
        this.body = body;
        this.made = made;
        this.images = images;
        this.gear = gear;
        this.idCompany = idCompany;
        this.yearOfManuFacture = yearOfManuFacture;
        this.status = status;
        this.fuel = fuel;
        this.price = price;
        this.quantity = quantity;
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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getGear() {
        return gear;
    }

    public void setGear(int gear) {
        this.gear = gear;
    }

    public int getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    }

    public int getYearOfManuFacture() {
        return yearOfManuFacture;
    }

    public void setYearOfManuFacture(int yearOfManuFacture) {
        this.yearOfManuFacture = yearOfManuFacture;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String[] arrayImg(){
        System.out.println(this.getImages());
        String[] rs = this.getImages().split(Pattern.quote("||"));
        return rs;
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
    public String getSrcFirst() {
        return images.split("||")[0];

    }

    @Override
    public String toString() {
        return "Post{" +
                "idPost=" + idPost +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", body='" + body + '\'' +
                ", made='" + made + '\'' +
                ", images='" + images + '\'' +
                ", gear=" + gear +
                ", idCompany=" + idCompany +
                ", yearOfManuFacture=" + yearOfManuFacture +
                ", status=" + status +
                ", fuel='" + fuel + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
