package Controller;

import Beans.JWT;
import Model.User;
import DAO.UserDAO;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/profile")
public class Profile extends HttpServlet {

    private void changleUser(HttpServletRequest req, HttpServletResponse res, String user) throws IOException, SQLException, ClassNotFoundException {
        res.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        Map<String, String[]> params = req.getParameterMap();
        String pass = null;
        try {
            pass = params.get("oldpass")[0];

        }catch (Exception e){

        }
        if(pass != null){
            if(!UserDAO.checkLogin(user,pass)){
                res.getWriter().write(new Gson().toJson("Wrong Password"));
                return;
            }
            if(UserDAO.updateUser(user,params.get("passnew")[0], URLDecoder.decode(params.get("fullName")[0], "UTF-8"),params.get("email")[0],params.get("phone")[0], URLDecoder.decode(params.get("address")[0], "UTF-8")) == 1){
                res.getWriter().write(new Gson().toJson(1));
                return;
            }
            res.getWriter().write(new Gson().toJson("Error"));

        }else{

            if(UserDAO.updateUser(user,URLDecoder.decode(params.get("fullName")[0], "UTF-8"),params.get("email")[0],params.get("phone")[0],URLDecoder.decode(params.get("address")[0], "UTF-8")) == 1){
                res.getWriter().write(new Gson().toJson(1));
                return;
            }

        }

    }
    protected void changeAvatar(HttpServletRequest req, HttpServletResponse res, String user) throws ServletException, IOException {
        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
        try {
            List<FileItem> fileItems = upload.parseRequest(req);
            for (FileItem fileItem : fileItems) {
                if (!fileItem.isFormField()) {
                    String nameimg = fileItem.getName();
                    if (!nameimg.equals("")) {
                        String dirUrl = req.getServletContext()
                                .getRealPath("") + File.separator + "Img/User";
                        System.out.println(req.getServletContext()
                                .getRealPath(""));
                        File dir = new File(dirUrl);
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        String fileImg = dirUrl + File.separator + user + ".jpg";
                        File file = new File(fileImg);
                        try {
                            fileItem.write(file);
                            System.out.println("UPLOAD THÀNH CÔNG...!");
                            System.out.println("ĐƯỜNG DẪN KIỂM TRA UPLOAD HÌNH ẢNH : \n"+dirUrl);
                            UserDAO.uploadAvatar(user,"Img/User/"+user + ".jpg");
                            Cookie img=new Cookie("imgUser","Img/User/"+user + ".jpg");
                            res.addCookie(img);
                        } catch (Exception e) {
                            System.out.println("CÓ LỖ TRONG QUÁ TRÌNH UPLOAD!");
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        res.sendRedirect("/profile");

    }
    public String getUserNameByToken(String token){
        try {
            Jws<Claims> claims = JWT.getBodyJWTLogin(token);
            String username = (String) claims.getBody().get("username");
            return username;
        }catch (Exception e){
            return null;
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String userName = null;
        String token=null;
        for (int i = 0; i < req.getCookies().length; i++) {
            if (req.getCookies()[i].getName().equals("token")) {
                token = req.getCookies()[i].getValue();
                break;
            }
        }
        if(token != null){
            User u;
            userName=getUserNameByToken(token);
            try {
                u = UserDAO.getUserByName(userName);
                req.setAttribute("userInfo", u);
                req.getRequestDispatcher("Page/Profile.jsp").forward(req, res);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String user = null;
        String action ;

        for (int i = 0; i < req.getCookies().length; i++) {
            if (req.getCookies()[i].getName().equals("user")) {
                user = req.getCookies()[i].getValue();
                break;
            }
        }
        action = req.getParameter("action");
        if(action!= null && action.equals("changeProfile")){
            try {
                changleUser(req,res, user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        if(action!= null && action.equals("changeAvatar")){
            changeAvatar(req,res,user);
        }
    }
}
