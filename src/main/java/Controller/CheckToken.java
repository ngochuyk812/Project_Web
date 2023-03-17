package Controller;


import Beans.JWT;
import DAO.CartDAO;
import Model.Cart;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet("/checkToken")
public class CheckToken extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter pw= response.getWriter();
        String token=request.getParameter("token");
        try {
            Jws<Claims> claims =JWT.getBodyJWTLogin(token);
            String username = (String) claims.getBody().get("username");
            int isAdmin = (int) claims.getBody().get("isAdmin");
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("userName",username);
            jsonObject.put("isAdmin",isAdmin);
            pw.println(jsonObject);
            response.setStatus(200);
        }catch (Exception e){
            pw.println("not ok");
            response.setStatus(500);
        }
    }
}
