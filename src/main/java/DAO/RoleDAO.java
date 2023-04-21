package DAO;

import Connect.ConnectDB;
import Model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO {
    public static List<Role> getAllRole() throws SQLException {
        List<Role> list=new ArrayList<>();
        Connection c = ConnectDB.getConnect();
        PreparedStatement stmt = c.prepareStatement("select * from role where status=1");
        ResultSet rs = stmt.executeQuery();
        while  (rs.next()) {
            list.add(new Role(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }
        return list;
    }
}
