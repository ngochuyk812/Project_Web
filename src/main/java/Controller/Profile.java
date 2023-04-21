package Controller;

import Beans.HashSHA216;
import DAO.UserDAO;
import Model.User;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
        String userName = user;
        Map<String, String[]> params = req.getParameterMap();
        String pass = null;
        try {
            pass = params.get("oldpass")[0];
        } catch (Exception e) {
        }
        if (pass != null) {
            String oldPass = HashSHA216.hash(pass);
            if (!UserDAO.checkLogin(userName, oldPass)) {
                res.getWriter().write(new Gson().toJson("Wrong Password"));
                return;
            }
            if (UserDAO.updateUser(userName, HashSHA216.hash(params.get("passnew")[0]), URLDecoder.decode(params.get("fullName")[0], "UTF-8"), params.get("email")[0], params.get("phone")[0], URLDecoder.decode(params.get("address")[0], "UTF-8")) == 1) {
                User userMain = UserDAO.getUserByName(user);
                saveSession(userMain, req);
                res.getWriter().write(new Gson().toJson(1));
                return;
            }
            res.getWriter().write(new Gson().toJson("Error"));
        } else {
            if (UserDAO.updateUser(userName, HashSHA216.hash(params.get("passnew")[0]), URLDecoder.decode(params.get("fullName")[0], "UTF-8"), params.get("email")[0], params.get("phone")[0], URLDecoder.decode(params.get("address")[0], "UTF-8")) == 1) {
                User userMain = UserDAO.getUserByName(user);
                saveSession(userMain, req);
                res.getWriter().write(new Gson().toJson(1));
                return;
            }
            res.getWriter().write(new Gson().toJson("Error"));
        }
    }
    protected void changeAvatar(HttpServletRequest req, HttpServletResponse res, String user) throws ServletException, IOException, SQLException {
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
                        File dir = new File(dirUrl);
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        String fileImg = dirUrl + File.separator + user + ".jpg";
                        File file = new File(fileImg);
                        try {
                            fileItem.write(file);
                            UserDAO.uploadAvatar(user, "Img/User/" + user + ".jpg");
                            saveSession(UserDAO.getUserByName(user), req);
                        } catch (Exception e) {
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            req.setAttribute("userInfo", user);
            req.setAttribute("statusLogin", user);
            req.getRequestDispatcher("/Page/Profile.jsp").forward(req, res);
        } else {
            res.setStatus(200);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String action = req.getParameter("action");
        if (action != null && action.equals("changeProfile")) {
            try {
                changleUser(req, res, user.getUserName());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        if (action != null && action.equals("changeAvatar")) {
            System.out.println(user);
            try {
                changeAvatar(req, res, user.getUserName());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void saveSession(User user, HttpServletRequest req) {
        req.getSession().setAttribute("user", user);
    }
}
