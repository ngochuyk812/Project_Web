package Controller;

import DAO.UserDAO;
import Beans.SendEmail;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/forgotPass")
public class ForGotPass extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
       req.getRequestDispatcher("Page/ForgotPass.jsp").forward(req,res);
    }
    protected void doPost (HttpServletRequest req, HttpServletResponse res){
        String username=req.getParameter("username");
        try {
            User user= UserDAO.getUserByName(username);
            String email=user.getEmail();
            String pass=user.getPassWord();
            SendEmail.getInstance().sendText(email,"Quên pass",pass);
            res.sendRedirect("/Page/Login.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
