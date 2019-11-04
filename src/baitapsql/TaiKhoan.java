/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitapsql;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class TaiKhoan {
    
    public int DangKetNoi(String tt, String un) {
        try {
            Statement cmd = ConnectUtils.getSQLServerConnection().createStatement();
            return cmd.executeUpdate("update TaiKhoan set DangKetNoi='"+tt+"' where TenDangNhap='"+un+"'");
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
     
    }
    public int LuuIp(String ip, String un){
        try {
            Statement cmd = ConnectUtils.getSQLServerConnection().createStatement();
            return cmd.executeUpdate("update TaiKhoan set Ip='"+ ip+"' where TenDangNhap='"+un+"'");//Luu Ip;
            
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
    public boolean ktDangNhap(String un,String pwd){
        try {
             Statement cmd = ConnectUtils.getSQLServerConnection().createStatement();
             String sql="select * from TaiKhoan where tendangnhap='"+un+"' and matkhau='"+ pwd+"'";
             ResultSet rs=cmd.executeQuery(sql);
             return rs.next() ;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    //Hàm check user đã tồn tại
    public boolean checkUser(String un){
        try {
             Statement cmd = ConnectUtils.getSQLServerConnection().createStatement();
             String sql="select * from TaiKhoan where tendangnhap='"+un+"'";
             ResultSet rs=cmd.executeQuery(sql);
             return rs.next() ;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    //Hàm trả về địa chỉ Ip của người đăng nhập là un
    public String LayIp(String un){
        try {
            Statement cmd = ConnectUtils.getSQLServerConnection().createStatement();
            String sql = "select * from TaiKhoan where tendangnhap='" + un + "'";
            ResultSet rs = cmd.executeQuery(sql);
            if (rs.next())//Nếu tồn tại
            {
                return rs.getString("Ip");//Trả về Ip
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    //Hàm trả về thông tin của tất cả các tài khoản đang đăng nhập vào hệ thống: DangKetNoi=true
    public ResultSet LayTaiKhoanDangKetNoi(String un){
        try {
            Statement cmd = ConnectUtils.getSQLServerConnection().createStatement();
            String sql="select * from TaiKhoan where DangKetNoi='true' and TenDangNhap not like '"+un+"'";
            ResultSet rs=cmd.executeQuery(sql);
            return rs;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    //Lấy về trạng thái đang kết nối của tài khoản có tên đăng nhập là: TenDangNhap
    public String LayTrangThaiKetNoi(String TenDangNhap){
        try {
            Statement cmd = ConnectUtils.getSQLServerConnection().createStatement();
            String sql="select * from TaiKhoan where TenDangNhap='"+TenDangNhap+"'";
            ResultSet rs=cmd.executeQuery(sql);
            rs.next();
            return rs.getString("DangKetNoi");
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    //Nhập vào bảng TaiKhoan 1 dòng dữ liệu
    public int TaoTaiKhoan(String tenDn, String matKhau, String hoten){
        try {
            Statement cmd = ConnectUtils.getSQLServerConnection().createStatement();
            int kt= cmd.executeUpdate("insert into TaiKhoan(TenDangNhap,MatKhau,HoTen) values('"+tenDn+"','"+matKhau+"','"+ hoten+"')" );
            return kt;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
    public int DoiMatKhau(String tenDn, String MatKhau){
        try {
            Statement cmd = ConnectUtils.getSQLServerConnection().createStatement();
            int kt= cmd.executeUpdate("update TaiKhoan set MatKhau='"+MatKhau+"' where TenDangNhap='"+tenDn+"'");
            return kt;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
    public int CheckMatKhau(String tenDn, String MatKhau){
        try {
            Statement cmd = ConnectUtils.getSQLServerConnection().createStatement();
            int kt= cmd.executeUpdate("select * from TaiKhoan where TenDangNhap = '"+tenDn+"' and MatKhau ='"+MatKhau+"'");
            return kt;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
}
