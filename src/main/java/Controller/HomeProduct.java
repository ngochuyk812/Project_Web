package Controller;

import DAO.ProductDAO;
import Model.Product;
import com.google.gson.Gson;
import org.springframework.ui.Model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/homeProduct")
public class HomeProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        ProductDAO productDAO = new ProductDAO();
//        ArrayList<Product> newProducts = productDAO.getNewProducts();
//        try {
//            getListNewProduct(req, res);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        req.setAttribute("newList", newProducts);
//
//        req.getRequestDispatcher("index.jsp").forward(req, res);
    }
    protected void getListNewProduct(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, SQLException {
        ArrayList<Product> newProducts = ProductDAO.getNewProducts();
        for(Product tmp :newProducts ){
            System.out.println(tmp.toString());

//            tmp.setContent(URLEncoder.encode(tmp.getContent(), StandardCharsets.UTF_8));
//            tmp.setName(tmp.getName());
//            tmp.setFuel(tmp.getFuel());
        }
        res.getWriter().write(new Gson().toJson(newProducts));
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
