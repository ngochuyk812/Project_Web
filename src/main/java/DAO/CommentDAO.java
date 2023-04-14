package DAO;

import Connect.ConnectDB;
import Model.Comment;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    public static int insertComment(Comment comment) throws SQLException {
        String sqlUpdate = "INSERT INTO comment (idUser, star, idProduct,status) values(?,?,?,?)";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
        pstmt.setInt(1, comment.getIdUser());
        pstmt.setInt(2, comment.getStar());
        pstmt.setInt(3, comment.getIdProduct());
        pstmt.setInt(4, comment.getStatus());
        return pstmt.executeUpdate();
    }

    public static List<Comment> getListComment(int idpost) throws SQLException, UnsupportedEncodingException {
        List<Comment> list = new ArrayList<Comment>();
        String sqlUpdate = "select * from comment where idProduct=?";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
        pstmt.setInt(1, idpost);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            list.add(new Comment(
                    UserDAO.getUserById(rs.getInt(3)).getUserName(),
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getDate(5),
                    rs.getInt(6)
            ));

        }
        return list;
    }
}
