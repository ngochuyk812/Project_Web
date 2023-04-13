package Model;

import java.sql.Date;

public class ImportProduct {
    private int idUser;
    private int idProduct;
    private int quantity;
    private int Status;


    public ImportProduct(int idUser, int idProduct, int quantity, int status) {
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.quantity = quantity;
        Status = status;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

}
