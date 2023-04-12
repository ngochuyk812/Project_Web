package Model;

import java.util.ArrayList;

public class ImgProduct {
    private int idProduct;
    private ArrayList<String> arr;
    private int status;

    public ImgProduct(int idProduct, ArrayList<String> arr, int status) {
        this.idProduct = idProduct;
        this.arr = arr;
        this.status = status;
    }


    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public ArrayList<String> getArr() {
        return arr;
    }

    public void setArr(ArrayList<String> arr) {
        this.arr = arr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
