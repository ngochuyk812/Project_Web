package Controller;

import DAO.UserDAO;
import Mail.SendEmail;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        try {
            User user= UserDAO.getUserByName(username);
            String email=user.getEmail();
            String pass=user.getPassWord();
            SendEmail.sendPass(email,pass);
            resp.sendRedirect("/Page/Login_Register.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
