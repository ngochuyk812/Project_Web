package Model;

import java.sql.Date;

public class Product {
    private int id;
    private int idVendo;
    private String name;
    private String content;
    private String body;
    private int yearOfManuFacture;
    private int fuel;
    private double price;
    private Date createAt;
    private int status;

    public Product(int id, int idVendo, String name, String content, String body, int yearOfManuFacture, int fuel, double price, Date createAt, int status) {
        this.id = id;
        this.idVendo = idVendo;
        this.name = name;
        this.content = content;
        this.body = body;
        this.yearOfManuFacture = yearOfManuFacture;
        this.fuel = fuel;
        this.price = price;
        this.createAt = createAt;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getIdVendo() {
        return idVendo;
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

    public int getFuel() {
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

    public void setIdVendo(int idVendo) {
        this.idVendo = idVendo;
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

    public void setFuel(int fuel) {
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

}
