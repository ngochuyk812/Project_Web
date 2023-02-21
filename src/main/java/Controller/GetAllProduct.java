package Controller;

import DAO.ProductDAO;
import Model.RespJsonServlet;
import Model.User;
import DAO.UserDAO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.SQLException;

@WebServlet(urlPatterns = {""})
public class GetAllProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("listProduct", ProductDAO.getAllProduct());
        System.out.println(ProductDAO.getAllProduct());
        req.getRequestDispatcher("/index.jsp").forward(req, res);
    }
}
