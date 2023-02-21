package Controller;

import DAO.CompanyDAO;
import DAO.OderDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import Model.Company;
import Model.Oder;
import Model.Post;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin")
public class FilterAdmin extends HttpServlet {
    protected void indexPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, SQLException {
        setShowProfile(req);
        int countUser=UserDAO.getCountUser();
        int countProduct= ProductDAO.getCountProduct();
        int countPOut= ProductDAO.getCountPOut();
        int countOrder= OderDAO.getCountOrder();
        int countOrderOut= OderDAO.getCountOrderOut();
        float getPriceRevenue= ProductDAO.getPriceRevenue();

        req.setAttribute("countUser",countUser);
        req.setAttribute("countProduct",countProduct);
        req.setAttribute("countPOut",countPOut);
        req.setAttribute("countOrder",countOrder);
        req.setAttribute("countOrderOut",countOrderOut);
        req.setAttribute("getPriceRevenue",getPriceRevenue);

        ArrayList<Post> products = ProductDAO.getProductOut();
        req.setAttribute("products", products);

        ArrayList<Oder> oders= OderDAO.getOrderOut();
            req.setAttribute("oders", oders);

        req.getRequestDispatcher("/Page/Admin/doc/quan-ly-bao-cao.jsp").forward(req,res);
        res.setStatus(200);
    }
    protected void postPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ArrayList<Company> list = null;
        try {
            list = CompanyDAO.getAllCompany();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.getSession().setAttribute("listCompany", list);
        req.getRequestDispatcher("/Page/Admin/doc/index.jsp").forward(req,res);

        res.setStatus(200);
    }
    protected void userPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        setShowProfile(req);
        try {
            List<User> list= UserDAO.getAllUser();
            req.setAttribute("listUser",list);
            req.getRequestDispatcher("/Page/Admin/doc/table-data-table.jsp").forward(req,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected void productPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ArrayList<Post> products = ProductDAO.getProduct();
        req.setAttribute("products", products);
        ArrayList<Company> list = null;
        try {
            list = CompanyDAO.getAllCompany();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.getSession().setAttribute("listCompany", list);
        req.getRequestDispatcher("/Page/Admin/doc/table-data-product.jsp").forward(req,res);
    }
    protected void oderPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("Oder");

        ArrayList<Oder> oders = null;
        try {
            oders = OderDAO.getOrder();
            req.setAttribute("oders", oders);
            req.getRequestDispatcher("/Page/Admin/doc/table-data-oder.jsp").forward(req,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    protected void setShowProfile(HttpServletRequest req){
        String username="";
        String img="";
        Cookie[] cookies = req.getCookies();
        for (Cookie tmp : cookies) {
            if (tmp.getName().equals("user")) {
                username = tmp.getValue();
            }else {
                if (tmp.getName().equals("imgUser")) {
                    img = tmp.getValue();
                }
            }
        }
        System.out.println(username);
        System.out.println(img);
        req.getSession().setAttribute("username",username);
        req.getSession().setAttribute("img",img);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        Cookie[] cookies = req.getCookies();
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        if(action!=null && action.equalsIgnoreCase("editproduct")){
            Post product = ProductDAO.getPostById(Integer.valueOf(id));
            req.setAttribute("product", product);
            ArrayList<Company> list = null;
            try {
                list = CompanyDAO.getAllCompany();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            req.getSession().setAttribute("listCompany", list);
            req.getRequestDispatcher("/Page/Admin/doc/form-edit-product.jsp").forward(req,res);

        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        Cookie[] cookies = req.getCookies();
        String name = "";
        for (Cookie tmp : cookies) {
            if (tmp.getName().equals("user")) {
                name = tmp.getValue();

            }

        }
        String page = req.getParameter("page");
        try {
            if (UserDAO.checkAdmin(name)) {
                User u = UserDAO.getUserByName(name);
                req.setAttribute("avatar", u.getAvatar());
                switch (page.toLowerCase().trim()) {
                    case "post" :
                        postPage(req,res);
                        break;
                    case  "usermanagement":
                        userPage(req,res);
                        break;
                    case  "productmanagement":
                        productPage(req,res);
                        break;
                    case  "odermanagement":
                        oderPage(req,res);
                        break;

                    default:
                        indexPage(req,res);
                }

            }else{
                req.getRequestDispatcher("Page/404.jsp").forward(req,res);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(name);

    }
}

