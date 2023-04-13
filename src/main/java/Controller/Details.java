package Controller;

import DAO.CommentDAO;
import DAO.ProductDAO;
import Model.Product;
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
import java.sql.SQLException;

@WebServlet("/details")
public class Details extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("id",id);
        if(id == null){
            req.getRequestDispatcher("Page/404.jsp").forward(req, res);
            return;
        }
        try {
            req.setAttribute("listComment", CommentDAO.getListComment(Integer.valueOf(id)));
            Product product = ProductDAO.getProductById(Integer.valueOf(id));
            req.setAttribute("product", product);
            req.getRequestDispatcher("Page/Detail.jsp").forward(req, res);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
