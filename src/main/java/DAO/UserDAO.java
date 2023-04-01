package DAO;

import Beans.JWT;
import Connect.ConnectDB;
import Model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static boolean checkLogin(String username, String pass) throws SQLException {
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("select * from user where username=? and password=?");
        stmt.setString(1, username);
        stmt.setString(2, pass);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }

    public static boolean checkByEmail(String email) throws SQLException {
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("select email from user where email=?");
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }

    public static boolean checkByUsername(String username) throws SQLException {
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("select username from user where username=?");
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }

    public static int getCountUser() throws SQLException {
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("select count(*) from user where role=0 ");
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int countRow = rs.getInt(1);
        System.out.println(countRow);
        return countRow;
    }

    public static User getUserByName(String name) throws SQLException {
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("select * from user where username=?");
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int idUser = rs.getInt("id");
            String userName = rs.getString("username");
            String fullName = rs.getString("fullname");
            String email = rs.getString("email");
            String pass = rs.getString("password");
            String phone = rs.getString("phone");
            String address = rs.getString("address");
            String avatar = rs.getString("avatar");
            int role = Integer.valueOf(rs.getString("role"));
            int status = Integer.valueOf(rs.getString("status"));
            int statusLogin = rs.getInt("statusLogin");
            User user = new User(userName, pass, fullName, email, phone, avatar, address, role, status, statusLogin);
            user.setIdUser(idUser);
            return user;
        }
        ;
        return null;

    }

    public static boolean checkUser(String username, String email) throws SQLException, ClassNotFoundException {
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("select * from user where username=? or email=?");
        stmt.setString(1, username);
        stmt.setString(2, email);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }

    public static int insertUser(User user) throws SQLException, ClassNotFoundException {
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("insert into user (username,password,fullname,email,phone,address,role,status,statusLogin) value(?,?,?,?,?,?,?,?,?)");
        stmt.setString(1, user.getUserName());
        stmt.setString(2, user.getPassWord());
        stmt.setString(3, user.getFullName());
        stmt.setString(4, user.getEmail());
        stmt.setString(5, user.getPhone());
        stmt.setString(6, user.getAddress());
        stmt.setInt(7, 0);
        stmt.setInt(8, 1);
        stmt.setInt(9, user.getStatusLogin());
        int rs = stmt.executeUpdate();
        return rs;
    }

    public static int updateUser(String username, String fullname, String email, String phone, String address) throws SQLException, ClassNotFoundException {
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("UPDATE user\n" +
                "SET  fullname = ?,email = ?, phone = ?, address = ?\n" +
                "WHERE username = ?");
        if (fullname.equals("null")) {
            stmt.setNull(1, Types.INTEGER);
        } else {
            stmt.setString(1, fullname);
        }

        if (email.equals("null")) {
            stmt.setNull(2, Types.INTEGER);
        } else {
            stmt.setString(2, email);
        }

        if (phone.equals("null")) {
            stmt.setNull(3, Types.INTEGER);
        } else {
            stmt.setString(3, phone);
        }
        if (address.equals("null")) {
            stmt.setNull(4, Types.INTEGER);
        } else {
            stmt.setString(4, address);
        }
        stmt.setString(5, username);
        int rs = stmt.executeUpdate();
        return rs;
    }

    public static int uploadAvatar(String user, String linkAvatar) throws SQLException, ClassNotFoundException {
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("UPDATE `user`" +
                "SET avatar = ? WHERE username = ?");
        stmt.setString(1, linkAvatar);
        stmt.setString(2, user);

        int rs = stmt.executeUpdate();
        return rs;
    }

    public static int updateUser(String username, String pass, String fullname, String email, String phone, String address) throws SQLException, ClassNotFoundException {
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("UPDATE user\n" +
                "SET password = ?, fullname = ?,email = ?, phone = ?, address = ?\n" +
                "WHERE username = ?");
        stmt.setString(1, pass);
        if (fullname.equals("null")) {
            stmt.setNull(2, Types.INTEGER);
        } else {
            stmt.setString(2, fullname);
        }

        if (email.equals("null")) {
            stmt.setNull(3, Types.INTEGER);
        } else {
            stmt.setString(3, email);
        }

        if (phone.equals("null")) {
            stmt.setNull(4, Types.INTEGER);
        } else {
            stmt.setString(4, phone);
        }
        if (address.equals("null")) {
            stmt.setNull(5, Types.INTEGER);
        } else {
            stmt.setString(5, address);
        }
        stmt.setString(6, username);
        int rs = stmt.executeUpdate();
        return rs;
    }

    public static boolean checkAdmin(String name) throws SQLException {
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("select * from user where username=?");
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        if (rs.next() && rs.getInt("role") == 1) {
            return true;
        }
        return false;

    }

    public static List<User> getAllUser() throws SQLException {
        List<User> list = new ArrayList<>();
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("select * from user where role=0");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            list.add(new User(rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("email"), rs.getString("phone"), rs.getString("avatar"), rs.getString("address"), rs.getInt("role"), rs.getInt("status"), rs.getInt("statusLogin")));
        }
        return list;
    }

}
