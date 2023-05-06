package Controller;

import Beans.HashSHA216;
import Beans.JWT;
import Beans.SendEmail;
import DAO.UserDAO;
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
        req.getRequestDispatcher("Page/ForgotPass.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("type").equals("confirmAccount")) {
            verifyAccount(req, resp);
        } else {
            if (req.getParameter("type").equals("forgotPassword")) {
                ChangePass(req, resp);
            }
        }
    }

    public void verifyAccount(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        try {
            User user = UserDAO.getUserByName(username);
            if (user != null) {
                String email = user.getEmail();
                String token = JWT.createJWT(req.getRemoteAddr() + "|" + user.getId(), JWT.TIMEOUT);
                SendEmail.getInstance().sendTokenVerifyForgot(email, "http://" + req.getHeader("host") + "/verifyForgotAccount?token=" + token);
                resp.sendRedirect("Page/Login.jsp");
            } else {
                resp.setStatus(401);
                resp.getWriter().write("tài khoản không tồn tại");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ChangePass(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("idUser"));
        String password = req.getParameter("password");
        if (!req.getParameter("idUser").equals("")) {
            try {
                if (UserDAO.changePassword(HashSHA216.hash(password), id) > 0) {
                    resp.setStatus(200);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
