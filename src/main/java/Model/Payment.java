package Model;

import java.sql.Date;

public class Payment {
    private int id;
    private long idOrder;
    private int type;
    private double amount;
    private Date paymentDate;
    private int status;


    public Payment() {
    }

    public Payment(int id, long idOrder, int type, float amount, Date paymentDate, int status) {
        this.id = id;
        this.idOrder = idOrder;
        this.type = type;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }
    public Payment( long idOrder, int type, float amount, Date paymentDate, int status) {
        this.idOrder = idOrder;
        this.type = type;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public Payment(long id, int i, Double valueOf, Date date) {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
