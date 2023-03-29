package Controller;


import Model.User;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/check/UserIsExist")
public class CheckUserIsExist extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter pw= response.getWriter();
        User user= (User) request.getSession().getAttribute("user");
        if (user != null) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userName", user.getUserName());
                jsonObject.put("statusLogin",user.getStatusLogin());
                jsonObject.put("pass",user.getPassWord());
                pw.println(jsonObject);
                response.setStatus(200);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } else {
            pw.println("not ok");
            response.setStatus(200);

        }
    }
}
