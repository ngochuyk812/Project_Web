package Controller;

import DAO.CommentDAO;
import Model.Comment;
import Model.RespJsonServlet;
import Model.User;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/postComment")
public class CommentControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        ArrayList<Comment> cmts = new ArrayList<>();
        if (id != null) {
            try {
                cmts = (ArrayList<Comment>) CommentDAO.getListComment(Integer.valueOf(id));
                resp.getWriter().write(new Gson().toJson(cmts));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String content = URLDecoder.decode(req.getParameter("content"), "UTF-8");
        resp.setContentType("application/json");
        User user = (User) req.getSession().getAttribute("user");
        long miliSeconds = System.currentTimeMillis();
        int idPost = Integer.valueOf(req.getParameter("idPost"));
        int star = Integer.valueOf(req.getParameter("star"));
        // cach 2
        if(user!=null){
            Comment comment=new Comment(idPost,user.getIdUser(),star, 1);
            try {
                if ((CommentDAO.insertComment(comment) != 0)) {
                    resp.getWriter().println(new RespJsonServlet("ok").json());
                    resp.setStatus(200);
                } else {
                    resp.getWriter().println(new RespJsonServlet("not ok").json());
                    resp.setStatus(200);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            System.out.println(123);
            resp.getWriter().println(new RespJsonServlet("no user").json());
            resp.setStatus(200);
        }

    }
}
