package DAO;

import Connect.ConnectDB;
import Model.Post;
import Model.Product;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAO {
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public static ArrayList<Post> getProduct() {
        ArrayList<Post> posts = new ArrayList<>();
        String query = "SELECT p.idProduct, pr.title, pr.idVendo, pr.name,pr.content, pr.body,pr.made,pr.yearOfManuFacture, pr.fuel,pr.price,pr.createAt, pr.status, SUM(o.quantity) as orderquantity, SUM(p.quantity) as productquantity, SUM(o.quantity) + SUM(p.quantity) as totalquantity FROM importproduct as p JOIN orderdetail as o ON o.idProduct = p.idProduct JOIN product as pr ON pr.id = p.idProduct GROUP BY p.idProduct, pr.title, pr.idVendo, pr.name,pr.content, pr.body,pr.made,pr.yearOfManuFacture, pr.fuel,pr.price,pr.createAt, pr.status";
        try {
            Statement statement = ConnectDB.getConnect().createStatement();
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                posts.add(new Post(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getInt(9),
                        resultSet.getFloat(10),
                        resultSet.getDate(11),
                        resultSet.getInt(12),
                        resultSet.getInt(15)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return posts;
    }

    public static ArrayList<Post> getProductOut() {
        ArrayList<Post> posts = new ArrayList<>();
        String query = "SELECT p.idProduct, pr.title, pr.idVendo, pr.name,pr.content, pr.body,pr.made,pr.yearOfManuFacture, pr.fuel,pr.price,pr.createAt, pr.status, SUM(o.quantity) as orderquantity, SUM(p.quantity) as productquantity, SUM(o.quantity) + SUM(p.quantity) as totalquantity FROM importproduct as p JOIN orderdetail as o ON o.idProduct = p.idProduct JOIN product as pr ON pr.id = p.idProduct GROUP BY p.idProduct, pr.title, pr.idVendo, pr.name,pr.content, pr.body,pr.made,pr.yearOfManuFacture, pr.fuel,pr.price,pr.createAt, pr.status HAVING SUM(o.quantity) + SUM(p.quantity) <=0;";
        try {
            Statement statement = ConnectDB.getConnect().createStatement();
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                    posts.add(new Post(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getInt(7),
                            resultSet.getInt(8),
                            resultSet.getInt(9),
                            resultSet.getFloat(10),
                            resultSet.getDate(11),
                            resultSet.getInt(12),
                            resultSet.getInt(15)
                    ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return posts;
    }




    public static Product getProductById(int id){
        System.out.println("ID Post ================ " + id);
        Product post =null;
        String query = "SELECT * FROM product where id = ?";

        try {
            Statement statement = ConnectDB.getConnect().createStatement();
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                post = new Product(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getInt(7),
                        resultSet.getDouble(8),
                        resultSet.getDate(9),
                        resultSet.getInt(10)
                );
                System.out.println(post.toString());

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(post);
        return post;
    }
    public static int getQuantityProduct(int idProduct)throws SQLException {
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("SELECT (SELECT  SUM(orderdetail.quantity) FROM (`order` JOIN orderdetail on `order`.id = orderdetail.idOrder) WHERE orderdetail.idProduct = ? ) as SLB, (SELECT  SUM(importproduct.quantity) FROM importproduct WHERE importproduct.idProduct = ?) AS SLN");
        stmt.setInt(1,idProduct);
        stmt.setInt(2,idProduct);
        ResultSet resultSet = stmt.executeQuery();
        int rs = 0;
        while (resultSet.next()) {
            rs= resultSet.getInt(2) - resultSet.getInt(1);

        }
        return rs;
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
        float count=0;
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("SELECT DISTINCT idProduct FROM importproduct; ");
        ResultSet rs=stmt.executeQuery();
        while (rs.next()){
            int id=rs.getInt("idProduct");
            int quantity=getQuantityProduct(id);
            if(quantity==0){
                count++;
            }

        }
        return count;
    }

    public static int getCountPOut() throws SQLException {
        int count=0;
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("SELECT DISTINCT idProduct FROM importproduct; ");
        ResultSet rs=stmt.executeQuery();
        while (rs.next()){
            int id=rs.getInt("idProduct");
            int quantity=getQuantityProduct(id);
            if(quantity==0){
                count++;
            }
        }
        return count;
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
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getInt(9),
                        resultSet.getFloat(10),
                        resultSet.getDate(11),
                        resultSet.getInt(12),
                        resultSet.getInt(12)
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
                posts.add(new Post(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getFloat(10),
                        rs.getDate(11),
                        rs.getInt(12),
                        rs.getInt(12)));
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
        ProductDAO.getProductById(208);
    }
}


