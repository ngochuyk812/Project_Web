package DAO;

import Connect.ConnectDB;
import Model.Cart;
import Model.Post;

import java.sql.*;
import java.util.ArrayList;

public class CartDAO {
    static Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    public static  ArrayList<Cart> getAllCartByUser(String username) throws SQLException {
        ArrayList<Cart> carts = new ArrayList<>();
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("SELECT cart.* FROM cart where cart.username = ? ");
        stmt.setString(1,username);
        ResultSet resultSet = stmt.executeQuery();
        ProductDAO ps = new ProductDAO();
        while (resultSet.next()) {
            Cart cart = new Cart();
            cart.setUsername(resultSet.getString(1));
            Post post = ps.getPostById(resultSet.getInt(2));
            cart.setPost(post);
            cart.setAmount( resultSet.getInt(3));
            carts.add(cart);
        }
        return carts;
    }
    public static Cart getCart(String username, int idPost) throws SQLException {

        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("SELECT * FROM cart where cart.username = ? and cart.idpost = ? ");
        stmt.setString(1,username);
        stmt.setInt(2,idPost);

        ResultSet resultSet = stmt.executeQuery();
        ProductDAO ps = new ProductDAO();
        Cart cart = null;
        while (resultSet.next()) {
            cart = new Cart();
            cart.setUsername(resultSet.getString(1));
            Post post = ps.getPostById(resultSet.getInt(2));
            cart.setPost(post);
            cart.setAmount( resultSet.getInt(3));

        }
        return cart;
    }
    public static Cart updateQuantityCart(String username , int idPost, int amount) throws SQLException{
        String sqlUpdate = "UPDATE cart "
                + "SET amount = ? "
                + "WHERE username = ?"
                + "AND idpost = ?";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
        pstmt.setInt(1, amount);
        pstmt.setString(2, username);
        pstmt.setInt(3, idPost);
        int rowAffected = pstmt.executeUpdate();
        return getCart(username, idPost);
    }
    public static int removeCart(String username , int idPost) throws SQLException {
        String sqlUpdate = "DELETE FROM cart "
                + "WHERE username = ?"
                + "AND idpost = ?";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
        pstmt.setString(1, username);
        pstmt.setInt(2, idPost);
        int rowAffected = pstmt.executeUpdate();
        return rowAffected;
    }
    public static int removeCart( int idPost) throws SQLException {
        String sqlUpdate = "DELETE FROM cart "
                + "WHERE username = ?";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
        pstmt.setInt(1, idPost);
        int rowAffected = pstmt.executeUpdate();
        return rowAffected;
    }

    public static int addToCart(String username, int idPost) throws SQLException {
        String sqlUpdate = "INSERT INTO cart (username, idpost, amount) values(?,?, 1)";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
        pstmt.setString(1, username);
        pstmt.setInt(2, idPost);
        int rowAffected = pstmt.executeUpdate();
        return rowAffected;

    }

}
