package Controller;

import DAO.CompanyDAO;
import DAO.ProductDAO;
import Upload.UploadImage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/postProduct")
public class PostProduct extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathRoot=(this.getServletContext().getRealPath("/"));
        resp.setContentType("application/json");
        try {
            String status=req.getParameter("status");
            int statusMain=1;
            if(status.equalsIgnoreCase("Sản phẩm đã qua sử dụng")){
                statusMain=0;
            }
            String title=URLDecoder.decode(req.getParameter("title"), "UTF-8");
            String content=URLDecoder.decode(req.getParameter("content"), "UTF-8");
            String images=req.getParameter("images");
            int idCompany=CompanyDAO.getIdByName(req.getParameter("nameCompany"));
            int year=Integer.parseInt(req.getParameter("yearofmanufacture"));
            ArrayList<String> listimgs=UploadImage.uploadAllFile(images,pathRoot,"post"+String.valueOf(idCompany),"Product");
            String rsImg="";
            for (String tmp : listimgs) {
                rsImg+=tmp+"||";
            }
            System.out.println(rsImg);
            int gear=Integer.valueOf(req.getParameter("gear"));
            String fuel= URLDecoder.decode(req.getParameter("fuel"), "UTF-8");
            Float price=Float.parseFloat(req.getParameter("price"));
            String body=req.getParameter("body");
            String made=URLDecoder.decode(req.getParameter("made"),"UTF-8");
            System.out.println(made);
            int quantity=Integer.valueOf(req.getParameter("quantity"));
            int rs=ProductDAO.insertProduct(title,content,body,made,rsImg,gear,idCompany,year,statusMain,fuel,price, quantity);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
