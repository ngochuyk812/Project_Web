package DAO;

import Beans.AbBean;
import Connect.ConnectDB;
import Model.Log;
import org.springframework.security.access.prepost.PreAuthorize;

import java.sql.SQLException;

public class LogDAO {
    public static boolean insert(AbBean bean) throws SQLException {
        return bean.insert(ConnectDB.getConnect());
    }
    @PreAuthorize("hasPermission('USER', 'READ')")
    public static int getTest(){
        return 1;
    }

    public static void main(String[] args) throws SQLException {
        getTest();
    }
}
