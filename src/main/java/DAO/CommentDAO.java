package DAO;

import Connect.ConnectDB;
import Model.Comment;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    public static int insertComment(Comment comment, String content, List<String> listImg, List<String> listVideo) throws SQLException {
        int cmtId = -1;
        String sqlUpdate = "INSERT INTO comment (idUser, star, idProduct,status) values(?,?,?,?)";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement pstmt = conn.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, comment.getIdUser());
        pstmt.setInt(2, comment.getStar());
        pstmt.setInt(3, comment.getIdProduct());
        pstmt.setInt(4, comment.getStatus());
        int affectedRows = pstmt.executeUpdate();
        if (affectedRows > 0) {
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                cmtId = generatedKeys.getInt(1);
            }
        }
        if (!content.equals("")) {
            insertTextContent(content, cmtId, 0);
        }
        if (listImg != null) {
            insertNewImgOVideo(listImg, cmtId, 1);
        }
        if (listVideo != null) {
            insertNewImgOVideo(listVideo, cmtId, 2);
        }
        return affectedRows;
    }

    public static void insertTextContent(String content, int idComment, int type) throws SQLException {
        String sqlUpdate = "INSERT INTO contentcomment (idComment, content, type) values(?,?,?)";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
        pstmt.setInt(1, idComment);
        pstmt.setString(2, content);
        pstmt.setInt(3, type);
        pstmt.executeUpdate();
        return;
    }

    public static void insertNewImgOVideo(List<String> content, int idComment, int type) throws SQLException {
        String sqlUpdate = "INSERT INTO contentcomment (idComment, content, type) values(?,?,?)";
        Connection conn = ConnectDB.getConnect();
        try (PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {
            for (String tmp : content) {
                System.out.println(12345);
                pstmt.setInt(1, idComment);
                pstmt.setString(2, tmp);
                pstmt.setInt(3, type);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;
    }

    public static List<Comment> getListComment(int idpost) throws SQLException, UnsupportedEncodingException {
        List<Comment> list = new ArrayList<Comment>();
        String sqlUpdate = "select cc.*,c.createAt,c.idUser,c.star from contentcomment as cc join comment as c on cc.idComment=c.id where c.idProduct=?";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
        pstmt.setInt(1, idpost);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            String content = rs.getString(3);
            int idCmt = rs.getInt(2);
            int type = rs.getInt(4);
            boolean check = false;
            Comment cmt = null;
            for (Comment tmp : list) {
                if (tmp.getId() == idCmt) {
                    check = true;
                    cmt = tmp;
                    break;
                }
            }
            if (!check) {
                Comment comment = new Comment();
                if (type == 1) {
                    comment.getListImg().add(content);
                } else {
                    if (type == 0) {
                        comment.setContent(content);
                    } else {
                        if (type == 2) {
                            comment.getListVideo().add(content);
                        }
                    }
                }
                comment.setId(rs.getInt(2));
                comment.setIdProduct(idpost);
                comment.setUserName(UserDAO.getUserById(rs.getInt(6)).getUserName());
                comment.setStar(rs.getInt(7));
                comment.setCreateAt(rs.getDate(5));
                comment.setAvatar(UserDAO.getUserById(rs.getInt(6)).getAvatar());
                list.add(comment);
            } else {
                if (type == 1) {
                    cmt.getListImg().add(content);
                } else {
                    if (type == 0) {
                        cmt.setContent(content);
                    } else {
                        if (type == 2) {
                            cmt.getListVideo().add(content);
                        }
                    }
                }
            }
        }
        return list;
    }
}
