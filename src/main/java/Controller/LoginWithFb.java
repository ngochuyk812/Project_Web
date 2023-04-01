package Controller;

import Beans.HashSHA216;
import Beans.JWT;
import DAO.UserDAO;
import Model.User;
import io.jsonwebtoken.Jws;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login/LoginWithFb")
public class LoginWithFb extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("userName");
        try {
            User user=null;
            if(UserDAO.getUserByName(username)!=null){
                user=UserDAO.getUserByName(username);
                req.getSession().setAttribute("user",user);

            }else {
                user=new User(username, null,null,null,null,null,null,0,1,1);
                UserDAO.insertUser(user);
                req.getSession().setAttribute("user",user);

            }
            String contextPath = req.getContextPath();

            saveSession(user,req);
            resp.sendRedirect(contextPath+"/");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveSession(User user, HttpServletRequest req){
        req.getSession().setAttribute("user",user);
    }
}
