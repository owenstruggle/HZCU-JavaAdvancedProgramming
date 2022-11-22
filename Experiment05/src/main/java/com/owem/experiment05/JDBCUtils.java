package com.owem.experiment05;

import java.sql.*;

/**
 * @projectName: Experiment05
 * @package: com.owem.experiment05.utils
 * @className: JDBCUtils
 * @author: Owem
 * @description: TODO
 * @date: 2022/10/18 17:06
 * @version: 1.0
 */
public class JDBCUtils {
    public static void main(String[] args) throws Exception {
        System.out.println(getConnection());
    }

    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String passwd = "12345678";

        Class.forName("com.mysql.jdbc.Driver");

        return DriverManager.getConnection(url, user, passwd);
    }

    public static void closeResource(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
