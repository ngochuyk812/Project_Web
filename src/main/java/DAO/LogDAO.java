package DAO;

import Beans.AbBean;
import Connect.ConnectDB;
import Model.Log;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LogDAO {
    public static boolean insert(AbBean bean) throws SQLException {
        return bean.insert(ConnectDB.getConnect());
    }



}
