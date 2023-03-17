package Controller;

import Beans.JWT;
import DAO.UserDAO;
import Model.User;
import Model.UserGoogleDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Form;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/login/loginWithGg")
public class LoginWithGg extends HttpServlet {
    public static String GOOGLE_CLIENT_ID = "108677386257-pqkllmloi7d9f60ipgvb053sfg9eosrr.apps.googleusercontent.com";

    public static String GOOGLE_CLIENT_SECRET = "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";

    public static String GOOGLE_REDIRECT_URI = "http://localhost:3000/login/loginWithGg";

    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

    public static String GOOGLE_GRANT_TYPE = "authorization_code";
    public static String getToken(String code) throws ClientProtocolException, IOException {
        String response = Request.Post(GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", GOOGLE_CLIENT_ID)
                        .add("client_secret",GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri",GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", GOOGLE_GRANT_TYPE).build()).execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        System.out.println(response);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserGoogleDto getUserInfo(String accessToken) throws ClientProtocolException, IOException {
        String link = GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        UserGoogleDto googlePojo = new Gson().fromJson(response, UserGoogleDto.class);
        return googlePojo;
    }
    public static void  addCookiesToken(User user, HttpServletResponse resp){
        String token= JWT.createJWTLogin(user,24);
        Cookie cookie=new Cookie("token", token);
        cookie.setMaxAge(30*24*60*60); //for 30 days
        cookie.setPath("/");
        resp.addCookie(cookie);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String code=req.getParameter("code");
        String accessTokens=getToken(code);
        UserGoogleDto userGoogleDto = getUserInfo(accessTokens);
        User user=new User(userGoogleDto.getId(),null, userGoogleDto.getName(),null,null,null,null);
        try {
            if(UserDAO.getUserByName(userGoogleDto.getId())!=null){
                addCookiesToken(user,resp);
            }else {
                UserDAO.insertUser(user);
                addCookiesToken(user,resp);
            }
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
