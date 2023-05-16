package org.example.h2.util;

import org.h2.util.JdbcUtils;

import javax.sql.rowset.serial.SerialClob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 */
public class JdbcUtil {

    public static final String DRIVER = "org.h2.Driver";

    public static final String URL = "jdbc:h2:tcp://127.0.0.1:3330/F:/workspace/danmuk-repo/database/danmuku";

    public static final String USER_NAME = "sa";

    public static final String PASSWORD = "";

    public static Connection getConn() throws SQLException {
        return JdbcUtils.getConnection(DRIVER, URL, USER_NAME, PASSWORD);
    }

    /**
     * Clob 转 String
     */
    public static String getString(Clob c) {
        try {
            return c.getSubString(1, (int) c.length());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * String 转 Clob
     */
    public static Clob getClob(String s) {
        try {
            return new SerialClob(s.toCharArray());
        } catch (Exception e) {
            return null;
        }
    }
}