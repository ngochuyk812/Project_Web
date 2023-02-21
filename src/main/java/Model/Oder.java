package Model;

public class Oder {
    private int id;
    private String fullName;
    private String username;
    private String phone;
    private String address;
    private String productName;
    private int quantity;
    private  double total;
    private int status;

    public Oder(int id, String username, String fullName, String phone, String address, String productName, int quantity, double total, int status) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.productName = productName;
        this.quantity = quantity;
        this.total = total;
        this.status = status;
    }
    public Oder(String username, String fullName, String phone, String address, String productName, int quantity, double total, int status) {
        this.username = username;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.productName = productName;
        this.quantity = quantity;
        this.total = total;
        this.status = status;
    }
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
