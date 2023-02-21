package Controller;


import Model.Cart;
import DAO.CartDAO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.System;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(name = "CartControl", value = "/cart")
public class CartControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String user = null;
        System.out.println("cx");
        String param = request.getParameter("action");

        for (int i = 0; i < request.getCookies().length; i++) {
            if (request.getCookies()[i].getName().equals("user")) {
                user = request.getCookies()[i].getValue();
                break;
            }
        }

        if (user != null) {
            if (param != null) {
                if (param.equals("listcart")) {
                    getList(request, response, user);
                    return;
                }
            }

            request.getRequestDispatcher("Page/Cart.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/Page/Login_Register.jsp").forward(request, response);

        }
    }

    private void getList(HttpServletRequest request, HttpServletResponse response, String user) throws IOException {
        ArrayList<Cart> carts = new ArrayList<>();
        try {
            CartDAO cs = new CartDAO();
            carts = cs.getAllCartByUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.getWriter().write(new Gson().toJson(carts));
    }

    protected void setQuantity(HttpServletRequest request, HttpServletResponse response, String action) throws ServletException, IOException, SQLException {
        String id = request.getParameter("idpost");
        Cookie[] arrCookie = request.getCookies();
        String user = null;
        for (Cookie tmp : arrCookie) {
            if (tmp.getName().equals("user")) {
                user = tmp.getValue();
                break;
            }
        }
        Cart cart = CartDAO.getCart(user, Integer.parseInt(id));

        try {
            if(action.equals("decrease"))
                cart = CartDAO.updateQuantityCart(cart.getUsername(), cart.getPost().getIdPost(), cart.getAmount() - 1);
            else
                cart = CartDAO.updateQuantityCart(cart.getUsername(), cart.getPost().getIdPost(), cart.getAmount() + 1);
            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(cart));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected void removeCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String id = request.getParameter("idpost");
        System.out.println(id);
        Cookie[] arrCookie = request.getCookies();
        String user = null;
        for (Cookie tmp : arrCookie) {
            if (tmp.getName().equals("user")) {
                user = tmp.getValue();
                break;
            }
        }

        try {
            int rs = CartDAO.removeCart(user, Integer.parseInt(id));
            System.out.println(id);

            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){

        }else{
            if(action.equals("decrease") || action.equals("increase")){
                try {
                    setQuantity(request,response, action);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(action.equals("addtocart")){
                try {
                    addToCart(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(action.equals("remove")){
                try {
                    removeCart(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }

    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String user = null;
        for (int i = 0; i < request.getCookies().length; i++) {
            if (request.getCookies()[i].getName().equals("user")) {
                user = request.getCookies()[i].getValue();
                break;
            }
        }

        String id = request.getParameter("idpost");
        if(id != null && user != null){
            Cart cart = CartDAO.getCart(user,Integer.valueOf(id));
            if(cart != null){
                setQuantity(request,response, "increase");
                response.getWriter().write(new Gson().toJson(1));
                return;

            }else{
                CartDAO.addToCart(user, Integer.valueOf(id));
                response.getWriter().write(new Gson().toJson(1));
                return;

            }
        }else{
            response.getWriter().write(new Gson().toJson(0));
            return;
        }
    }
}
