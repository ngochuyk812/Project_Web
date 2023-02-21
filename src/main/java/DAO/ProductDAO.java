package DAO;

import Connect.ConnectDB;
import Model.Cart;
import Model.Company;
import Model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class ProductDAO {
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public static ArrayList<Post> getProduct() {
        ArrayList<Post> posts = new ArrayList<>();
        String query = "SELECT * FROM product ";
        try {
            Statement statement = ConnectDB.getConnect().createStatement();
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                posts.add(new Post(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getInt(9),
                        resultSet.getInt(10),
                        resultSet.getString(11),
                        resultSet.getFloat(12),
                        resultSet.getInt(13)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return posts;
    }

    public static ArrayList<Post> getProductOut() {
        ArrayList<Post> posts = new ArrayList<>();
        String query = "SELECT * FROM product where quantity<=0";
        try {
            Statement statement = ConnectDB.getConnect().createStatement();
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                posts.add(new Post(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getInt(9),
                        resultSet.getInt(10),
                        resultSet.getString(11),
                        resultSet.getFloat(12),
                        resultSet.getInt(13)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return posts;
    }




    public static Post getPostById(int id){
        System.out.println("ID Post ================ " + id);
        Post post =null;
        String query = "SELECT * FROM product where idpost = ?";

        try {
            Statement statement = ConnectDB.getConnect().createStatement();
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                post = new Post(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getInt(9),
                        resultSet.getInt(10),
                        resultSet.getString(11),
                        resultSet.getFloat(12),
                        resultSet.getInt(13)

                );
                System.out.println(post.toString());

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(post);
        return post;
    }
    public static int deleteProduct(int idPost) throws SQLException {
        Statement statement = ConnectDB.getConnect().createStatement();
        String q1 = "SET FOREIGN_KEY_CHECKS=0";
        PreparedStatement preparedStatement = statement.getConnection().prepareStatement(q1);
        preparedStatement.executeUpdate();
        String query = "DELETE FROM `product` WHERE idPost=? ";
        preparedStatement = statement.getConnection().prepareStatement(query);
        preparedStatement.setInt(1,idPost);
        int resultSet = preparedStatement.executeUpdate();
        String q2 = "SET FOREIGN_KEY_CHECKS=1";
        preparedStatement = statement.getConnection().prepareStatement(q1);
        preparedStatement.executeUpdate();
        return resultSet;
    }
    public static float getPriceRevenue() throws SQLException {
        float rsSet=0;
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("select * from  `product` where quantity=0");
        ResultSet rs=stmt.executeQuery();
        while (rs.next()){
            rsSet+=rs.getFloat("price");

        }
        return rsSet;
    }
    public static int getCountPOut() throws SQLException {
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("select count(*) from  `product` where quantity=0");
        ResultSet rs=stmt.executeQuery();
        rs.next();
        int countRow=rs.getInt(1);
        System.out.println(countRow);
        return countRow;
    }

    public ArrayList<Post> getPostUnComfirm() {
        ArrayList<Post> posts = new ArrayList<>();
        String query = "SELECT * FROM post where Comfirm=0";
        try {
            statement = ConnectDB.getConnect().createStatement();
            preparedStatement = statement.getConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                posts.add(new Post(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getInt(9),
                        resultSet.getInt(10),
                        resultSet.getString(11),
                        resultSet.getFloat(12),
                        resultSet.getInt(13)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return posts;
    }
    public static int insertProduct(String title, String content,String body,String made, String images,int gear,int idCompany, int year, int status,String fuel,float price, int quantity) {
        String query = "INSERT INTO product(title,content,body,made,images,gear ,idCompany,yearofmanufacture,status,fuel,price,quantity) VALUES (?,?,?,?,?,?,?,?,?,?,?,?); ";
        try {
            PreparedStatement stmt = ConnectDB.getConnect().prepareStatement(query);
            stmt.setString(1,title);
            stmt.setString(2,content);
            stmt.setString(3,body);
            stmt.setString(4,made);
            stmt.setString(5,images);
            stmt.setInt(6,gear);
            stmt.setInt(7,idCompany);
            stmt.setInt(8,year);
            stmt.setInt(9,status);
            stmt.setString(10,fuel);
            stmt.setFloat(11,price);
            stmt.setInt(12, quantity);
            int resultSet = stmt.executeUpdate();
            return resultSet;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static int getCountProduct() throws SQLException {

        return  getProduct().size();
    }
    public static int updateProduct(int idpost,String title, String content,String body,String made,int gear,int idCompany, int year, int status,String fuel,float price, int quantity) throws SQLException {
        String q1 = "SET FOREIGN_KEY_CHECKS=0";
            PreparedStatement stmt = ConnectDB.getConnect().prepareStatement(q1);
        stmt.executeUpdate();
        String query = "UPDATE product set title =?,content=?,body=?,made=?,gear =?,idCompany=?,yearofmanufacture=?,status=?,fuel=?,price=?,quantity=? where idpost = ? ";
        try {
             stmt = ConnectDB.getConnect().prepareStatement(query);
            stmt.setString(1,title);
            stmt.setString(2,content);
            stmt.setString(3,body);
            stmt.setString(4,made);
            stmt.setInt(5,gear);
            stmt.setInt(6,idCompany);
            stmt.setInt(7,year);
            stmt.setInt(8,status);
            stmt.setString(9,fuel);
            stmt.setFloat(10,price);
            stmt.setInt(11, quantity);
            stmt.setInt(12, idpost);

            int resultSet = stmt.executeUpdate();
            String q2 = "SET FOREIGN_KEY_CHECKS=1";
            stmt = ConnectDB.getConnect().prepareStatement(q2);
            stmt.executeUpdate();
            return resultSet;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static ArrayList<Post> getAllProduct(){
        ArrayList<Post> posts = new ArrayList<>();
        String query = "select * from product ";
        try {
            PreparedStatement stmt = ConnectDB.getConnect().prepareStatement(query);
            ResultSet rs= stmt.executeQuery();

            while (rs.next()){
                posts.add(new Post(rs.getInt("idPost"),rs.getString("title"),rs.getString("content"),rs.getString("body"),rs.getString("made"),rs.getString("images"),rs.getInt("gear"),rs.getInt("idcompany"),rs.getInt("yearofmanufacture"),rs.getInt("status"),rs.getString("fuel"),rs.getFloat("price"), rs.getInt("quantity")));
            }

            return posts;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
//    public static int updateBasic(int idPost, String title, int quantity, float price) throws SQLException{
//        String sqlUpdate = "UPDATE prooduct "
//                + "SET idpost = ?, title = ?,  quantity = ? ,price = ?  "
//                + "WHERE idpost = ?";
//        Connection conn = ConnectDB.getConnect();
//        PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
//        pstmt.setInt(1, idPost);
//        pstmt.setString(2, title);
//        pstmt.setInt(3, quantity);
//        pstmt.setFloat(4, price);
//
//        int rowAffected = pstmt.executeUpdate();
//        return rowAffected;
//    }

    public static void main(String[] args) throws SQLException {
        ProductDAO.getPostById(208);
    }
}


