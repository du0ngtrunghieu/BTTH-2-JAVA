/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitapsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class ConnectUtils {
    public static String TenDn=""; //Lưu lại tên người dùng đăng nhập vào hệ thống
    public static String IpGoiDen=""; //Lưu lại Ip của máy cần gửi đến
    public static int DoDaiGoiTinNhan=0;//Lưu lại độ dài của gói tin vừa nhận
    public static Connection conn;
    public static Connection getSQLServerConnection()
            throws SQLException, ClassNotFoundException {
        String hostName = "localhost";
        String sqlInstanceName = "SQLEXPRESS";
        String database = "Chat-PT";
        String userName = "sa";
        String password = "1234";

        return getSQLServerConnection(hostName, sqlInstanceName,
                database, userName, password);
    }

    // Trường hợp sử dụng SQLServer.
    // Và thư viện SQLJDBC.
    public static Connection getSQLServerConnection(String hostName,
            String sqlInstanceName, String database, String userName,
            String password) throws ClassNotFoundException, SQLException {
        // Khai báo class Driver cho DB SQLServer
        // Việc này cần thiết với Java 5
        // Java6 tự động tìm kiếm Driver thích hợp.
        // Nếu bạn dùng Java6, thì ko cần dòng này cũng được.
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        // Cấu trúc URL Connection dành cho SQLServer
        // Ví dụ:
        // jdbc:sqlserver://ServerIp:1433/SQLEXPRESS;databaseName=simplehr
        String connectionURL = "jdbc:sqlserver://" + hostName + ":1433"
                + ";instance=" + sqlInstanceName + ";databaseName=" + database;

        conn = DriverManager.getConnection(connectionURL, userName,
                password);
        return conn;
    }

    public static ResultSet getTable(String tb) {
        try {
            Statement cmd = getSQLServerConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            return cmd.executeQuery("select * from " + tb);
        } catch (Exception t2) {
            System.out.print(t2.getMessage());
            return null;
        }
       
    }

}
