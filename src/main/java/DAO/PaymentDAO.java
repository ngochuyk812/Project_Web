package DAO;

        import Connect.ConnectDB;
        import Model.Comment;
        import Model.Payment;

        import java.io.UnsupportedEncodingException;
        import java.sql.*;
        import java.util.ArrayList;
        import java.util.List;

public class PaymentDAO {
    public static int insertPayment(Payment payment) throws SQLException {
        String sqlUpdate = "INSERT INTO payment ( id_order,type_payment,amount,payment_date,status) values(?,?,?,?, ?)";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
        pstmt.setLong(1, payment.getIdOrder());
        pstmt.setInt(2, payment.getType());
        pstmt.setDouble(3, payment.getAmount());
        pstmt.setDate(4, payment.getPaymentDate());
        pstmt.setInt(5, payment.getStatus());
        return pstmt.executeUpdate();
    }


}
