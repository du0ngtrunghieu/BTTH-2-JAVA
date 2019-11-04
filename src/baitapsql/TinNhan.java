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
public class TinNhan {
    public int TaoTinNhan(String NoiDung, String TenDangNhap){
        try {
            Statement cmd = ConnectUtils.getSQLServerConnection().createStatement();
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            int kt= cmd.executeUpdate("insert into TinNhan(NoiDung,TenDangNhap,NgayNhan) values('"+NoiDung+"','"+TenDangNhap+"','"+ sqlDate+"')" );
            return kt;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
    public ResultSet getDate(String username){
         try {
            Statement cmd = ConnectUtils.getSQLServerConnection().createStatement();
            String sql = "select Count(Id),NgayNhan from TinNhan where TenDangNhap ='"+username+"' GROUP BY NgayNhan";
            ResultSet rs = cmd.executeQuery(sql);
            return rs;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public ResultSet getTinNhan(String username, String ngaynhan){
         try {
            Statement cmd = ConnectUtils.getSQLServerConnection().createStatement();
            String sql = "select NoiDung from TinNhan where NgayNhan =N'"+ngaynhan+"' and TenDangNhap ='"+username+"'";
            ResultSet rs = cmd.executeQuery(sql);
            return rs;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
