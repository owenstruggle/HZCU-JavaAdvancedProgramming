package com.owem.login.utils;

import org.apache.commons.dbutils.DbUtils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @description: 操作数据库的工具类
 * @author: Owemshu
 * @create: 2022-06-24 22:35
 */
public class JDBCUtils {
    public static Connection getConnection() throws Exception {
        Driver driver = new com.mysql.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/loginlib";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "12345678");
        return driver.connect(url, info);
    }

    public static void closeResource(Connection conn, PreparedStatement ps, ResultSet rs) {
        DbUtils.closeQuietly(rs);
        DbUtils.closeQuietly(ps);
        DbUtils.closeQuietly(conn);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getConnection());
    }
}