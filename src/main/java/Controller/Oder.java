package Controller;


import Model.Cart;
import Model.User;
import DAO.CartDAO;
import DAO.OderDAO;
import DAO.UserDAO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;


@WebServlet(name = "Oder", value = "/oder")
public class Oder extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action != null && action.equals("listorder")){
            try {
                getOrder(request,response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        sendBill(request,response, null,false);
    }

    protected void getOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {


        String username = null;
        boolean checkRs = false;
        ArrayList<Model.Oder> oders = new ArrayList<>();
        for (int i = 0; i < request.getCookies().length; i++) {
            if (request.getCookies()[i].getName().equals("user")) {
                username = request.getCookies()[i].getValue();
                break;
            }
        }
        if(username == null){
            sendBill(request,response,null, false);
            return;
        }
        oders = OderDAO.getOrderByUser(username);

        response.getWriter().write(new Gson().toJson(oders));

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delete = request.getParameter("delete");
        if(delete != null){
            int rs = 0;
            try {
                rs = OderDAO.deleteOderById(Integer.valueOf(delete));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.getWriter().write(new Gson().toJson(rs));
            return;
        }
        String update = request.getParameter("update");
        if(update != null){
            String fullName = URLDecoder.decode(request.getParameter("fullName"), "UTF-8");
            int id = Integer.valueOf(update);
            String address = URLDecoder.decode(request.getParameter("address"), "UTF-8");
            int quantity = Integer.valueOf(request.getParameter("quantity"));
            String phone = request.getParameter("phone");
            int status = Integer.valueOf(request.getParameter("status"));


            int rs = 0;
            try {
                rs = OderDAO.updateById(id,fullName,quantity,address,phone,status );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.getWriter().write(new Gson().toJson(rs));
            return;
        }
        String username = null;
        boolean checkRs = false;
        ArrayList<Model.Oder> oders = new ArrayList<>();
        for (int i = 0; i < request.getCookies().length; i++) {
            if (request.getCookies()[i].getName().equals("user")) {
                username = request.getCookies()[i].getValue();
                break;
            }
        }
        if(username != null){
            ArrayList<Cart> carts = new ArrayList<>();
            User user = null;
            try {
                 user = UserDAO.getUserByName(username);
                 CartDAO cs = new CartDAO();
                carts = cs.getAllCartByUser(username);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            for(Cart tmp : carts){
                Model.Oder oder = new Model.Oder(username,user.getFullName(), user.getPhone(), user.getAddress(), tmp.getPost().getTitle(), tmp.getAmount(), (double) (tmp.getAmount() * tmp.getPost().getPrice()), 0);
                try {
                    if(OderDAO.addOrder(oder) == 1){
                        oders.add(oder);
                        CartDAO.removeCart(username, tmp.getPost().getIdPost());
                        checkRs = true;
                    }else{
                        checkRs = false;
                        break;
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        sendBill(request,response, oders, checkRs);

    }

    private void sendBill(HttpServletRequest req, HttpServletResponse res, ArrayList<Model.Oder> oders, boolean check) throws IOException, ServletException {
        res.setCharacterEncoding("UTF-8");
        PrintWriter out = res.getWriter();
        if(!check){
            req.getRequestDispatcher("Page/404.jsp").forward(req, res);
            return;
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String fullName = oders.get(0).getFullName();
        String date = dtf.format(now).toString();
        String phone = oders.get(0).getPhone();
        String address = oders.get(0).getAddress();
        double total = 0;
        for(Model.Oder tmp : oders){
            total += tmp.getTotal();
        }
        Locale locale = new Locale("en", "UK");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        String sTotal = fmt.format(total).substring(1, fmt.format(total).length());

        out.println("\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css\" integrity=\"sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==\" crossorigin=\"anonymous\" referrerpolicy=\"no-referrer\" />\n" +
                "    <title>Thành công</title>\n" +
                "    <style>\n" +
                "        .widget-steps{\n" +
                "            display: flex;\n" +
                "            justify-content: center;\n" +
                "        }\n" +
                "        .progress{\n" +
                "            background-color: rgb(30, 142, 30);\n" +
                "            height: 2px;\n" +
                "        }\n" +
                "     \n" +
                "       \n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div id=\"orderpage\" style=\"background-color: #f6f7f8; margin-top: 1px;\">\n" +
                "        <br>\n" +
                "        <br>\n" +
                "        <div class=\"container\">\n" +
                "          <div class=\"\" >\n" +
                "            <div class=\"col-md-11 mx-auto\"> \n" +
                "    \n" +
                "              <div class=\"row widget-steps\">\n" +
                "                <div class=\"col-3 step complete susscess-box\">\n" +
                "                  <div class=\"step-name\">Đặt hàng</div>\n" +
                "                  <div class=\"progress\">\n" +
                "                    <div class=\"progress-bar\"></div>\n" +
                "                  </div>\n" +
                "                  <a href=\"#\" class=\"step-dot\"></a> </div>\n" +
                "                <div class=\"col-3 step complete susscess-box\">\n" +
                "                  <div class=\"step-name\">Hóa đơn</div>\n" +
                "                  <div class=\"progress\">\n" +
                "                    <div class=\"progress-bar\"></div>\n" +
                "                  </div>\n" +
                "                  <a href=\"#\" class=\"step-dot\"></a> </div>\n" +
                "                \n" +
                "                <div class=\"col-3 step complete susscess-box\">\n" +
                "                  <div class=\"step-name\">Hoàn thành</div>\n" +
                "                  <div class=\"progress\">\n" +
                "                    <div class=\"progress-bar\"></div>\n" +
                "                  </div>\n" +
                "                  <a href=\"#\" class=\"step-dot\"></a> </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "           <hr/>\n" +
                "            <div class=\"col-lg-12 text-center mt-5 \">\n" +
                "              <p class=\"text-success text-16 lh-1\"><i style=\"font-size: 40px;\" class=\"fas fa-check-circle\"></i></p>\n" +
                "              <h2 class=\"text-8\">Đặt Hàng Thành Công</h2>\n" +
                "              <p class=\"lead\">Hóa đơn của bạn</p>\n" +
                "            </div>\n" +
                "            \n" +
                "            <div class=\"col-md-8 col-lg-6 col-xl-5 mx-auto\">\n" +
                "              <div class=\"bg-white shadow-sm rounded p-4 p-lg-5 mb-4\">\n" +
                "                <div class=\"row\">\n" +
                "                  <div class=\"col-sm text-muted\">Họ và tên</div>\n" +
                "                  <div class=\"col-sm text-sm-end fw-600\">"+fullName+"</div>\n" +
                "                </div>\n" +
                "                <hr>\n" +
                "                <div class=\"row\">\n" +
                "                  <div class=\"col-sm text-muted\">Ngày</div>\n" +
                "                  <div class=\"col-sm text-sm-end fw-600\">"+date+"</div>\n" +
                "                </div>\n" +
                "                <hr>\n" +
                "                <div class=\"row\">\n" +
                "                  <div class=\"col-sm text-muted\">Thanh toán</div>\n" +
                "                  <div class=\"col-sm text-sm-end fw-600\">Thanh toán khi nhận hàng</div>\n" +
                "                </div>\n" +
                "                <hr>\n" +
                "               \n" +
                "                <div class=\"row\">\n" +
                "                  <div class=\"col-sm text-muted\">Số điện thoại</div>\n" +
                "                  <div class=\"col-sm text-sm-end fw-600\">"+phone+"</div>\n" +
                "                </div>\n" +
                "                <hr>\n" +
                "                <div class=\"row\">\n" +
                "                  <div class=\"col-sm text-muted\">Địa chỉ</div>\n" +
                "                  <div class=\"col-sm text-sm-end fw-600\">"+address+"</div>\n" +
                "                </div>\n" +
                "                <hr>\n" +
                "                <div class=\"row\">\n" +
                "                  <div class=\"col-sm text-muted\">Tổng tiền</div>\n" +
                "                  <div class=\"col-sm text-sm-end text-6 fw-500\">$"+sTotal+"</div>\n" +
                "                </div>\n" +
                "                <hr>\n" +
                "                <div class=\"row\">\n" +
                "                    <button style=\"background-color: rgb(43, 169, 43); border: none;  padding: 10px 15px; margin: 0 auto;\">\n" +
                "                        <a href=\"/profile\" style=\"color: white; text-decoration: none;\">TIẾP TỤC MUA HÀNG</a>\n" +
                "                      </button>\n" +
                "                </div>\n" +
                "                \n" +
                "              </div>\n" +
                "              \n" +
                "              \n" +
                "            </div>\n" +
                "          </div>\n" +
                "        </div>\n" +
                "       \n" +
                "        \n" +
                "      </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>");
    }
}
