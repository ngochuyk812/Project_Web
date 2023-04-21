package Controller;


import DAO.CartDAO;
import DAO.ProductDAO;
import Model.Cart;
import Model.User;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.System;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(name = "CartControl", value = "/cart")
public class CartControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        System.out.println("cx");
        String param = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userName = user.getId();
        if (param != null) {
            if (param.equals("listcart")) {
                getList(request, response, userName);
                return;
            }
        }

        request.getRequestDispatcher("Page/Cart.jsp").forward(request, response);
    }

    private void getList(HttpServletRequest request, HttpServletResponse response, int user) throws IOException {
        ArrayList<Cart> carts = new ArrayList<>();

        try {
            CartDAO cs = new CartDAO();
            carts = cs.getAllCartByUser(user);
            for (int i = 0; i < carts.size(); i++) {
                Cart cart = carts.get(i);
                int quantityProduct = ProductDAO.getQuantityProduct(cart.getProduct().getId());
                System.out.println(quantityProduct + "-----" );
                if(quantityProduct == 0){
                    cart.setStatus(1);
                }else{
                    if(cart.getQuantity() > quantityProduct){
                        cart.setStatus(2);
                    }else if(cart.getQuantity() == quantityProduct){
                        cart.setStatus(4);
                    }else if(cart.getQuantity() <= 1){
                        cart.setStatus(3);
                    }else{
                        cart.setStatus(0);
                    }
                }
            CartDAO.updateCartById(cart);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.getWriter().write(new Gson().toJson(carts));
    }

    protected void setQuantity(HttpServletRequest request, HttpServletResponse response, String action) throws ServletException, IOException, SQLException {
        int idCart = Integer.valueOf(request.getParameter("idCart"));
        Cookie[] arrCookie = request.getCookies();
        String user = null;
        for (Cookie tmp : arrCookie) {
            if (tmp.getName().equals("user")) {
                user = tmp.getValue();
                break;
            }
        }
        Cart cart = CartDAO.getCartById(idCart);

        try {
            if(action.equals("decrease")){
                cart.setQuantity(cart.getQuantity()  - 1);
                int quantityProduct = ProductDAO.getQuantityProduct(cart.getProduct().getId());
                System.out.println(quantityProduct + "-----" );
                if(quantityProduct == 0){
                    cart.setStatus(1);
                }else{
                    if(cart.getQuantity() > quantityProduct){
                        cart.setStatus(2);
                    }else if(cart.getQuantity() == quantityProduct){
                        cart.setStatus(4);
                    }else if(cart.getQuantity() < 1){
                        cart.setQuantity( cart.getQuantity() + 1);
                        cart.setStatus(3);
                    }else{
                        cart.setStatus(0);

                    }


                }

                CartDAO.updateCartById(cart);

            }
            else{
                cart.setQuantity(cart.getQuantity()  + 1);
                int quantityProduct = ProductDAO.getQuantityProduct(cart.getProduct().getId());
                System.out.println(quantityProduct + "-----" );
                if(quantityProduct == 0){
                    cart.setStatus(1);
                }else{
                    if(cart.getQuantity() > quantityProduct){

                        cart.setStatus(2);
                    }else if(cart.getQuantity() == quantityProduct){
                        cart.setStatus(4);
                    }else if(cart.getQuantity() < 1){
                        cart.setQuantity( cart.getQuantity() + 1);
                        cart.setStatus(3);
                    }else{
                        cart.setStatus(0);

                    }


                }

                CartDAO.updateCartById(cart);
            }

            cart = CartDAO.updateQuantityCartById(cart.getId(), cart.getQuantity());

            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(cart));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected void removeCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int idCart = Integer.valueOf(request.getParameter("idCart"));

        System.out.println(idCart);


        try {
            int rs = CartDAO.removeCartById(idCart);
            System.out.println(rs);
            Gson gson = new Gson();

            response.getWriter().write(gson.toJson(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");

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
