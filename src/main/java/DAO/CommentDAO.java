package DAO;

import Connect.ConnectDB;
import Model.Comment;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    public static int insertComment(String username, Date time, float star,String comment,int idpost) throws SQLException {
        String sqlUpdate = "INSERT INTO comment (username, time, star,comment,idpost ) values(?,?,?,?,?)";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
        pstmt.setString(1,username);
        pstmt.setDate(2,time);
        pstmt.setFloat(3,star);
        pstmt.setString(4,comment);
        pstmt.setInt(5,idpost);
        return pstmt.executeUpdate();

    }
    public static List<Comment> getListComment(int idpost) throws SQLException, UnsupportedEncodingException {
        List<Comment> list=new ArrayList<Comment>();

        String sqlUpdate = "select * from comment where idpost=?";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
        pstmt.setInt(1,idpost);
        ResultSet rs=pstmt.executeQuery();
        while (rs.next()){
            list.add(new Comment(rs.getString("username"),rs.getDate("time"),rs.getFloat("star"), URLEncoder.encode(rs.getString("comment"), StandardCharsets.UTF_8).replace("+","%20"),rs.getInt("idpost")));

        }
        return list;
    }
}
