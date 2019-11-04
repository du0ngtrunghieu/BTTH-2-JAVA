/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitapsql;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Admin
 */
public class GoiNhan {

    DatagramSocket Sserver;

    public void MoCong(int cong)//Mở ra 1 cổng
    {
        try {
            Sserver = new DatagramSocket(cong);//Tạo ra 1 DatagramSocket và mở cổng
        } catch (Exception tb1) {
            System.out.print("Khong mo cong duoc");
        }
    }
    //Gửi đến máy có địa chỉ Ip 1 chuỗi tb, gửi vào cổng: cong

    public void Goi(String tb, String Ip, int cong) {
        try {
            DatagramSocket Sclient = new DatagramSocket();//Tạo ra 1 DatagramSocket để nhận gói tin
            InetAddress ip = InetAddress.getByName(Ip);//Lấy về thông tin của máy có địa chỉ Ip là: ip
//Tạo ra 1 DatagramPacket để chứa gói tin và địa chỉ đích
            DatagramPacket dr = new DatagramPacket(tb.getBytes(), tb.length(), ip, cong);
            Sclient.send(dr);//Gửi gói tin đi
            Sclient.close();
            System.out.print("da goi");
        } catch (Exception tb2) {
            System.out.print("Khong goi duoc");
        }
    }
//Nhận về 1 gói tin theo dạng chuỗi

    public String Nhan() {
        try {
            byte[] buff = new byte[512];//Tạo ra 1 bộ đệm
//Tạo ra 1 DatagramPacket để chứa gói tin nhận về
            DatagramPacket dr = new DatagramPacket(buff, buff.length);
            Sserver.receive(dr);//Nhận về 1 gói tin
//Lây dữ liệu về và đổi ra chuỗi
            String data = new String(dr.getData(), 0, dr.getLength());
            System.out.print("da nhan");
            return data;//Trả về chuỗi nhận được
        } catch (Exception tb3) {
            System.out.print("Khong nhan duoc");
            return null;
        }
    }
//Tương tự như phương thức Goi nhưng gửi đi 1 dãy các byte, phương thức này để gửi file
//Gửi đến máy có địa chỉ Ip là Ip 1 dãy các byte có độ dài là: dodai qua cổng: cong

    public void GoiByte(byte tb[], int dodai, String Ip, int cong) {
        try {
            DatagramSocket Sclient = new DatagramSocket();
            InetAddress ip = InetAddress.getByName(Ip);
            DatagramPacket dr = new DatagramPacket(tb, dodai, ip, cong);
            Sclient.send(dr);
            Sclient.close();
            System.out.print("da goi");
        } catch (Exception tb2) {
            System.out.print("Khong goi duoc");
        }
    }
//Nhập về 1 dãy các byte, phương thức này dùng để nhận file về

    public byte[] NhanByte() {
        try {
            byte[] buff = new byte[512];
            DatagramPacket dr = new DatagramPacket(buff, buff.length);
            Sserver.receive(dr);
            byte[] DuLieuNhan = new byte[dr.getLength()];
            DuLieuNhan = dr.getData();
            ConnectUtils.DoDaiGoiTinNhan = dr.getLength();//Lưu lại độ dài của gói tin vừa nhận
            return DuLieuNhan;//Trả về dãy các byte vừa nhận
        } catch (Exception tb3) {
            System.out.print("Khong nhan duoc");
            return null;
        }
    }


}
