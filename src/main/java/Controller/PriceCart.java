package Controller;


import Model.Cart;
import Model.User;
import DAO.CartDAO;
import DAO.OderDAO;
import DAO.UserDAO;
import Model.VerifyRecaptcha;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.Region;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;


@WebServlet(name = "pricecar", value = "/pricecar")
public class PriceCart extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String gRecaptchaResponse = req
                .getParameter("g-recaptcha-response");
        VerifyRecaptcha.verify(gRecaptchaResponse);
       req.getRequestDispatcher("Component/Captcha/Captcha.jsp").forward(req,res);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String gRecaptchaResponse = req
                .getParameter("g-recaptcha-response");
        System.out.println(gRecaptchaResponse);
        VerifyRecaptcha.verify(gRecaptchaResponse);

    }

    }
