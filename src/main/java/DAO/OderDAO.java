package DAO;

import Connect.ConnectDB;
import Model.Cart;
import Model.Oder;

import java.sql.*;
import java.util.ArrayList;

public class OderDAO {
    static Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    public static int addOrder(Oder oder) throws SQLException {
        ArrayList<Cart> carts = new ArrayList<>();
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("INSERT INTO `order` (`id`, `fullName`, `phone`, `address`, `productName`, `quantity`, `total`, `status`, `username`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?,?)");
        stmt.setString(1, oder.getFullName());
        stmt.setString(2, oder.getPhone());
        stmt.setString(3, oder.getAddress());
        stmt.setString(4, oder.getProductName());
        stmt.setInt(5, oder.getQuantity());
        stmt.setDouble(6, oder.getTotal());
        stmt.setInt(7, oder.getStatus());
        stmt.setString(8, oder.getUsername());


        int resultSet = stmt.executeUpdate();

        return resultSet;
    }
    public static ArrayList<Oder> getOrder() throws SQLException {
        ArrayList<Oder> oders = new ArrayList<>();
        Connection c= ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("select * from `order`");
        ResultSet rs= stmt.executeQuery();
        while (rs.next()){
            oders.add(new Oder(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getInt(7), rs.getDouble(8), rs.getInt(9)));


        }

        return oders;
    }

    public static ArrayList<Oder> getOrderOut() throws SQLException {
        ArrayList<Oder> oders = new ArrayList<>();
        Connection c= ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("select * from `order` where status=2");
        ResultSet rs= stmt.executeQuery();
        while (rs.next()){
            oders.add(new Oder(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getInt(7), rs.getDouble(8), rs.getInt(9)));


        }

        return oders;
    }
    public static int deleteOderById(int id) throws SQLException {
        Connection c= ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("DELETE from `order` where id=?");
        stmt.setInt(1,id);
        int rs= stmt.executeUpdate();
        return rs;
    }
    public static int updateById(int id, String fullName, int quantity, String adrress, String phone, int status) throws SQLException {
        Connection c= ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("UPDATE `order` set fullName = ?, quantity = ?, address = ?, phone = ?, status =? where id=?");
        stmt.setString(1,fullName);
        stmt.setInt(2,quantity);
        stmt.setString(3,adrress);
        stmt.setString(4,phone);
        stmt.setInt(5,status);
        stmt.setInt(6,id);
        int rs= stmt.executeUpdate();
        return rs;
    }
    public static int getCountOrder() throws SQLException {
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("select count(*) from `order`");
        ResultSet rs=stmt.executeQuery();
        rs.next();
        int countRow=rs.getInt(1);
        System.out.println(countRow);
        return countRow;
    }
    public static int getCountOrderOut() throws SQLException {
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("select count(*) from  `order` where status=2");
        ResultSet rs=stmt.executeQuery();
        rs.next();
        int countRow=rs.getInt(1);
        System.out.println(countRow);
        return countRow;
    }
    public static ArrayList<Oder> getOrderByUser(String user) throws SQLException {
        ArrayList<Oder> oders = new ArrayList<>();
        Connection c= ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("select * from `order` where username=?");
        stmt.setString(1,user);
        ResultSet rs= stmt.executeQuery();

        while (rs.next()){
            oders.add(new Oder(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getInt(7), rs.getDouble(8), rs.getInt(9)));


        }

        return oders;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(OderDAO.getOrder().size());
    }
}
