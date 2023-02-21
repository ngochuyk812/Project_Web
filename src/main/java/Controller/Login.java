package Controller;

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

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter pw;
        String name= req.getParameter("username");
        String pass= req.getParameter("password");
        Gson gson=new Gson();
        try {
            if(UserDAO.checkLogin(name,pass)){
                User user= UserDAO.getUserByName(name);
                String userResp=gson.toJson(user);
                System.out.println(userResp);
                Cookie cookie=new Cookie("user", name);
                Cookie isAdmin=new Cookie("isAdmin",user.getIsAdmin() + "");
                Cookie img=new Cookie("imgUser",user.getAvatar());
                resp.addCookie(cookie);
                resp.addCookie(img);
                resp.addCookie(isAdmin);
                pw=resp.getWriter();
                pw.println(new RespJsonServlet("ok").json());
                pw.close();
            }else {
                resp.getWriter().println(new RespJsonServlet("not ok").json());
                resp.setStatus(200);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
