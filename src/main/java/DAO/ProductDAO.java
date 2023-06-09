package DAO;

import Connect.ConnectDB;
import Model.Company;
import Model.ImgProduct;
import Model.ImportProduct;
import Model.Product;
import com.google.gson.Gson;
import org.springframework.ui.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class ProductDAO {
//    int quantity;
    public static ArrayList<Product> getProduct() {
        ArrayList<Product> products = new ArrayList<>();
        String query = "SELECT product.*, sum(quantity) as quantity FROM importproduct join product on importproduct.idProduct = product.id GROUP by product.id HAVING quantity >0;\n";
        try {
            Statement statement = ConnectDB.getConnect().createStatement();
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                int quantity = getQuantityByID(resultSet.getInt(1));
                Company vendo = CompanyDAO.getVendoById(resultSet.getInt(2));
                Product prod = new Product(resultSet.getInt(1),
                        vendo,
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),

                        resultSet.getInt(6),
                        resultSet.getString(7),
                        resultSet.getDouble(8),
                        resultSet.getDate(9),
                        resultSet.getInt(10),
                        getImagesByID(resultSet.getInt(1))
                );
                products.add(prod);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
    public static int getQuantityProductById(int id) {
        String query = "SELECT product.*, sum(quantity) as quantity FROM importproduct join product on importproduct.idProduct = product.id GROUP by product.id HAVING quantity > 0 AND id = ?";
        try {
            Statement statement = ConnectDB.getConnect().createStatement();
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return  resultSet.getInt("quantity");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  -1;
    }
    public static ArrayList<Product> getNewProducts() {
        ArrayList<Product> newProducts = new ArrayList<>();
        String query = "SELECT * FROM product JOIN importproduct ON product.id = importproduct.idProduct ORDER BY product.yearOfManuFacture DESC LIMIT 12;";
        try {
            Statement statement = ConnectDB.getConnect().createStatement();
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Company vendo = CompanyDAO.getVendoById(resultSet.getInt(2));
                Product prod = new Product(resultSet.getInt(1),
                        vendo,
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),

                        resultSet.getInt(6),
                        resultSet.getString(7),
                        resultSet.getDouble(8),
                        resultSet.getDate(9),
                        resultSet.getInt(10),
                        getImagesByID(resultSet.getInt(1))
                );
                newProducts.add(prod);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return newProducts;
    }

    public static ArrayList<Product> getTrendProducts() {
        ArrayList<Product> trendProducts = new ArrayList<>();
        String query = "SELECT * FROM product JOIN importproduct ON product.id = importproduct.idProduct ORDER BY importproduct.quantity AND importproduct.createAt DESC LIMIT 12;";
        try {
            Statement statement = ConnectDB.getConnect().createStatement();
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Company vendo = CompanyDAO.getVendoById(resultSet.getInt(2));
                Product prod = new Product(resultSet.getInt(1),
                        vendo,
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),

                        resultSet.getInt(6),
                        resultSet.getString(7),
                        resultSet.getDouble(8),
                        resultSet.getDate(9),
                        resultSet.getInt(10),
                        getImagesByID(resultSet.getInt(1))
                );
                trendProducts.add(prod);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trendProducts;
    }

    public static ArrayList<String> getImagesByID(int idProduct) {
        ArrayList<String> images = new ArrayList<>();
        String query = "SELECT srcImg FROM imgproduct where idProduct = ?";

        try {
            Statement statement = ConnectDB.getConnect().createStatement();
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, idProduct);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                images.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return images;
    }

//    public static ArrayList<String> getImportByID(int idProduct) {
//        ArrayList<String> imports = new ArrayList<>();
//        String query = "SELECT importproduct.quantity FROM importproduct where idProduct = ?";
//
//        try {
//            Statement statement = ConnectDB.getConnect().createStatement();
//            PreparedStatement preparedStatement = statement.getConnection().prepareStatement(query);
//            preparedStatement.setInt(1, idProduct);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                imports.add(resultSet.getInt(1));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return imports;
//    }


    public static ArrayList<Product> getProductOut() {
        ArrayList<Product> posts = new ArrayList<>();
        String query = "SELECT p.*, COALESCE(ip.quantity, 0) - COALESCE(od.quantity, 0) AS quantity_on_hand FROM product p LEFT JOIN orderdetail od ON p.id = od.idProduct LEFT JOIN importproduct ip ON p.id = ip.idProduct WHERE COALESCE(ip.quantity, 0) - COALESCE(od.quantity, 0) <= 0;;";
        try {
            Statement statement = ConnectDB.getConnect().createStatement();
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Company vendo = CompanyDAO.getVendoById(resultSet.getInt(2));
                posts.add(new Product(resultSet.getInt(1),
                        vendo,
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getString(7),
                        resultSet.getDouble(8),
                        resultSet.getDate(9),
                        resultSet.getInt(10),
                        getImagesByID(resultSet.getInt(1))
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return posts;
    }

    public static int newImgProduct(ImgProduct tmp) {
        int resultSet = 0;
        for (String img : tmp.getArr()) {
            String query = "INSERT INTO imgproduct(idproduct,srcImg, status) VALUES (?,?,?); ";
            try {
                PreparedStatement stmt = ConnectDB.getConnect().prepareStatement(query);
                stmt.setInt(1, tmp.getIdProduct());
                stmt.setString(2, img);
                stmt.setInt(3, tmp.getStatus());
                resultSet = stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return resultSet;
    }


    public static Product getProductById(int id) {
        System.out.println("ID Post ================ " + id);
        Product product = null;
            String query = "SELECT p.*, COALESCE(ip.quantity, 0) - COALESCE(od.quantity, 0) AS quantity_on_hand FROM product p LEFT JOIN orderdetail od ON p.id = od.idProduct LEFT JOIN importproduct ip ON p.id = ip.idProduct WHERE p.id=?";
        try {
            Statement statement = ConnectDB.getConnect().createStatement();
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Company vendo = CompanyDAO.getVendoById(resultSet.getInt(2));
                product = new Product(resultSet.getInt(1),
                        vendo,
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getString(7),
                        resultSet.getDouble(8),
                        resultSet.getDate(9),
                        resultSet.getInt(14),
                        getImagesByID(resultSet.getInt(1))
                );
                product.setHeight(resultSet.getInt("height"));
                product.setWidth(resultSet.getInt("width"));
                product.setLength(resultSet.getInt("length"));
                product.setWeight(resultSet.getInt("weight"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public void getProductByID(int id){

    }
    public static int getQuantityProduct(int idProduct) throws SQLException {
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("SELECT (SELECT  SUM(orderdetail.quantity) FROM (`order` JOIN orderdetail on `order`.id = orderdetail.idOrder) WHERE orderdetail.idProduct = ? ) as SLB, (SELECT  SUM(importproduct.quantity) FROM importproduct WHERE importproduct.idProduct = ?) AS SLN");
        stmt.setInt(1, idProduct);
        stmt.setInt(2, idProduct);
        ResultSet resultSet = stmt.executeQuery();
        int rs = 0;
        while (resultSet.next()) {
            rs = resultSet.getInt(2) - resultSet.getInt(1);

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
        preparedStatement.setInt(1, idPost);
        int resultSet = preparedStatement.executeUpdate();
        String q2 = "SET FOREIGN_KEY_CHECKS=1";
        preparedStatement = statement.getConnection().prepareStatement(q1);
        preparedStatement.executeUpdate();
        return resultSet;
    }

    public static float getPriceRevenue() throws SQLException {
        float count = 0;
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("SELECT DISTINCT idProduct FROM importproduct; ");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("idProduct");
            int quantity = getQuantityProduct(id);
            if (quantity == 0) {
                count++;
            }

        }
        return count;
    }

    public static int getCountPOut() throws SQLException {
        int count = 0;
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("SELECT DISTINCT idProduct FROM importproduct;");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("idProduct");
            int quantity = getQuantityProduct(id);
            if (quantity == 0) {
                count++;
            }
        }
        return count;
    }

    public static int insertProduct(int idUser, Product pro, int quantity) {
        String query = "INSERT INTO product(idVendo,name,content,body,yearOfManuFacture,fuel,price,height, length, width, weight,status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?); ";
        int productId = -1;
        int resultSet = 0;
        try {
            PreparedStatement stmt = ConnectDB.getConnect().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, pro.getVendo().getId());
            stmt.setString(2, pro.getName());
            stmt.setString(3, pro.getContent());
            stmt.setString(4, pro.getBody());
            stmt.setInt(5, pro.getYearOfManuFacture());
            stmt.setString(6, pro.getFuel());
            stmt.setDouble(7, pro.getPrice());
            stmt.setInt(8, pro.getHeight());
            stmt.setInt(9, pro.getLength());
            stmt.setInt(10, pro.getWidth());
            stmt.setInt(11, pro.getWeight());

            stmt.setInt(12, 1);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    productId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ImgProduct imgProduct = new ImgProduct(productId, pro.getImages(), 1);
        newImgProduct(imgProduct);
        newImportProduct(new ImportProduct(idUser, productId, quantity, 1));
        return resultSet;
    }

    public static void newImportProduct(ImportProduct importProduct) {
        String query = "INSERT INTO importproduct(idUser,idProduct,quantity,status) VALUES (?,?,?,?); ";
        try {
            PreparedStatement stmt = ConnectDB.getConnect().prepareStatement(query);
            stmt.setInt(1, importProduct.getIdUser());
            stmt.setInt(2, importProduct.getIdProduct());
            stmt.setInt(3, importProduct.getQuantity());
            stmt.setInt(4, importProduct.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getCountProduct() throws SQLException {
        return getProduct().size();
    }

    public static int updateProduct(int idpost, String title, String content, String body, String made, int gear, int idCompany, int year, int status, String fuel, float price, int quantity) throws SQLException {
        String q1 = "SET FOREIGN_KEY_CHECKS=0";
        PreparedStatement stmt = ConnectDB.getConnect().prepareStatement(q1);
        stmt.executeUpdate();
        String query = "UPDATE product set title =?,content=?,body=?,made=?,gear =?,idCompany=?,yearofmanufacture=?,status=?,fuel=?,price=?,quantity=? where idpost = ? ";
        try {
            stmt = ConnectDB.getConnect().prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, content);
            stmt.setString(3, body);
            stmt.setString(4, made);
            stmt.setInt(5, gear);
            stmt.setInt(6, idCompany);
            stmt.setInt(7, year);
            stmt.setInt(8, status);
            stmt.setString(9, fuel);
            stmt.setFloat(10, price);
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

    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        productDAO.getNewProducts();
    }
}




