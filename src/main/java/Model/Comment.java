package Model;


import java.sql.Date;

public class Comment {
    private int id;
    private int idProduct;
    private int idUser;
    private String userName;
    private int star;
    private int status;
    private Date createAt;

    public Comment(int idProduct, int idUser, int star, int status) {
        this.idProduct = idProduct;
        this.idUser = idUser;
        this.star = star;
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Comment(String userName, int id, int idProduct, int idUser, int star, Date createAt, int status) {
        this.id = id;
        this.idProduct = idProduct;
        this.idUser = idUser;
        this.star = star;
        this.userName=userName;
        this.createAt = createAt;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
