package DAO;

import Beans.AbBean;
import Connect.ConnectDB;

import java.sql.SQLException;

public class LogDAO {
    public static boolean insert(AbBean bean) throws SQLException {
        return bean.insert(ConnectDB.getConnect());
    }



}
