package Controller;

import Beans.JWT;
import DAO.UserDAO;
import Model.User;
import io.jsonwebtoken.ExpiredJwtException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.Enumeration;


@WebServlet(name = "verifyAccount", value = "/verifyAccount")

public class VerifyAccountControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType( "text/html" );
        res.setCharacterEncoding("UTF-8");
        String body = "";
        try{
            body = JWT.getBodyJWT(req.getParameter("token"));
        }catch (Exception e){
            req.getRequestDispatcher("Component/VerifyAccount/VerifyAccountError.jsp").forward(req,res);
            return;
        }
        User user = (User) req.getSession().getAttribute("verifyAccount_" + body.trim());
        if(user == null){
            req.getRequestDispatcher("Component/VerifyAccount/VerifyAccountError.jsp").forward(req,res);
            return;
        }
        System.out.println();
        System.out.println("Timeout: " +req.getSession().getMaxInactiveInterval());
        System.out.println("TimeCreate: " +req.getSession().getCreationTime());
        System.out.println("" +req.getSession().getMaxInactiveInterval());
        int rs = 0;
        try {
            if(UserDAO.checkByUsername(user.getUserName())|| UserDAO.checkByEmail(user.getEmail())){
                req.getRequestDispatcher("Component/VerifyAccount/ErrorUserOrEmailExist.jsp").forward(req,res);
                req.getSession().removeAttribute("verifyAccount_" + body.trim());

                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {

            rs = UserDAO.insertUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if(rs ==1 ){
            req.getRequestDispatcher("Component/VerifyAccount/VerifyAccount.jsp").forward(req,res);
            req.getSession().removeAttribute("verifyAccount_" + body.trim());

        }else{
            req.getRequestDispatcher("Component/VerifyAccount/VerifyAccountError.jsp").forward(req,res);
            req.getSession().removeAttribute("verifyAccount_" + body.trim());

        }

    }
}
