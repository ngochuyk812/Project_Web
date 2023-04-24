package Model;

import java.sql.Date;

public class Oder {
    private long id;
    private int idUser;
    private String address;
    private String idTransport ;
    private Date createAt;
    private String note;
    private int status;
    private double total_price;

    public Oder(long id, int idUser,String address, String idTransport, Date createAt, String note, int status, double total_price) {
        this.id = id;
        this.idUser = idUser;
        this.idTransport = idTransport;
        this.address = address;
        this.createAt = createAt;
        this.note = note;
        this.status = status;
        this.total_price = total_price;
    }
    public Oder() {

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getIdTransport() {
        return idTransport;
    }

    public void setIdTransport(String idTransport) {
        this.idTransport = idTransport;
    }



    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Oder{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", address='" + address + '\'' +
                ", idTransport='" + idTransport + '\'' +
                ", createAt=" + createAt +
                ", note='" + note + '\'' +
                ", status=" + status +
                ", total_price=" + total_price +
                '}';
    }
}
