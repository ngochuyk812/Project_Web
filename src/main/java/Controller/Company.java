package Controller;

import DAO.CompanyDAO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/company")
public class Company extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            res.getWriter().write(new Gson().toJson(CompanyDAO.getAllCompany()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
