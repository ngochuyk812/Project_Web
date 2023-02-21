package Controller;

import Mail.SendEmail;
import Model.RespJsonServlet;
import DAO.UserDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter("/register")
public class FilterRegister implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest rq=(HttpServletRequest)request;
        HttpServletResponse resp=(HttpServletResponse)response;
        response.setContentType("application/json");
        String email=request.getParameter("email");
        HttpSession session= rq.getSession();
        String codeSs= (String) session.getAttribute("code");
        String codeCl= request.getParameter("code");
        System.out.println(codeSs);
        System.out.println(codeCl);
        try {
            if(!(UserDAO.checkUser(request.getParameter("name"), email))){
                if(codeSs.equalsIgnoreCase(codeCl)){
                    chain.doFilter(request,response);
                }else {
                    resp.getWriter().println(new RespJsonServlet("code sai").json());
                }
            }else {
                RespJsonServlet ex=new RespJsonServlet("username or email is exist");
                resp.getWriter().println(ex.json());
                resp.setStatus(200);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void destroy() {

    }
}
