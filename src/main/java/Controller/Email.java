package Controller;

import Mail.SendEmail;
import Model.RespJsonServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/sendMail")
public class Email extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String email = req.getParameter("email");
        System.out.println(email);
        String code = SendEmail.sendCodeMail(email);
        if (code != null) {
            resp.getWriter().println(new RespJsonServlet(code).json());
            HttpSession session = req.getSession();
            session.setAttribute("code", code);
            resp.setStatus(200);
        } else {
            resp.sendError(500);

        }


    }
}
