package Model;

import java.sql.Date;
import java.util.ArrayList;

public class Product {
    private int id;
    private Company vendo;
    private String name;
    private String content;
    private String body;
    private int yearOfManuFacture;
    private String fuel;
    private double price;
    private Date createAt;
    private int status;
    private ArrayList<String> images ;

    public Product(int id, Company vendo, String name, String content, String body, int yearOfManuFacture, String fuel, double price, Date createAt, int status) {
        this.id = id;
        this.vendo = vendo;
        this.name = name;
        this.content = content;
        this.body = body;
        this.yearOfManuFacture = yearOfManuFacture;
        this.fuel = fuel;
        this.price = price;
        this.createAt = createAt;
        this.status = status;
        images = new ArrayList<>();
    }
    public Product(int id, Company vendo, String name, String content, String body, int yearOfManuFacture, String fuel, double price, Date createAt, int status, ArrayList<String> images) {
        this.id = id;
        this.vendo = vendo;
        this.name = name;
        this.content = content;
        this.body = body;
        this.yearOfManuFacture = yearOfManuFacture;
        this.fuel = fuel;
        this.price = price;
        this.createAt = createAt;
        this.status = status;
        this.images = images;
    }
    public int getId() {
        return id;
    }
    public ArrayList<String> getImages(){
        return  images;
    }
    public void addImage(String image){
        images.add(image);
    }
    public Company getVendo() {
        return vendo;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getBody() {
        return body;
    }

    public int getYearOfManuFacture() {
        return yearOfManuFacture;
    }

    public String getFuel() {
        return fuel;
    }

    public double getPrice() {
        return price;
    }


    public Date getCreateAt() {
        return createAt;
    }

    public int getStatus() {
        return status;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setVendo(Company vendo) {
        this.vendo = vendo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setYearOfManuFacture(int yearOfManuFacture) {
        this.yearOfManuFacture = yearOfManuFacture;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", vendo=" + vendo +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", body='" + body + '\'' +
                ", yearOfManuFacture=" + yearOfManuFacture +
                ", fuel='" + fuel + '\'' +
                ", price=" + price +
                ", createAt=" + createAt +
                ", status=" + status +
                ", images=" + images +
                '}';
    }
}
