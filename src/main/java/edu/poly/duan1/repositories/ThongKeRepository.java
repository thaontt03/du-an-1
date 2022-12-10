/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.duan1.repositories;

import edu.poly.duan1.model.thongke;
import edu.poly.duan1.ultis.JDBC_Helper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Nguyen Thi Thu Thao
 */
public class ThongKeRepository {

    public List<thongke> getList() {
        List<thongke> list = new ArrayList<>();
        ResultSet rs;
        String sql = "select  year(ngaythanhtoan) as nam,"
                + "month(ngaythanhtoan) as thang, "
                + "sum(soluong) as soluong, \n"
                + "sum(dongia*soluong) as doanhthu,\n"
                + "sum((dongia*soluong)-(gianhap*soluong)) as loinhuan\n"
                + "from hoadon join hoadonct on hoadon.id = hoadonct.idhd \n"
                + "join sachct on hoadonct.idsachct = sachct.id\n"
                + "group by month(ngaythanhtoan),year(ngaythanhtoan)\n"
                + "order by nam desc";

        rs = JDBC_Helper.selectTongQuat(sql);
        try {
            while (rs.next()) {
                int soluong = rs.getInt("soluong");
                String nam = rs.getString("nam");
                String thang = rs.getString("thang");
                double doanhthu = rs.getDouble("doanhthu");
                double loinhuan = rs.getDouble("loinhuan");
                thongke mh = new thongke(soluong, nam, thang, doanhthu, loinhuan);
                list.add(mh);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
            //Logger.getLogger(GiangVien_Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<thongke> getDoanhThu() {
        List<thongke> list = new ArrayList<>();
        ResultSet rs;
        String sql = "select  year(ngaythanhtoan) as nam,\n"
                + "sum(dongia*soluong) as doanhthu\n"
                + "from hoadon join hoadonct on hoadon.id = hoadonct.idhd \n"
                + "group by year(ngaythanhtoan)\n"
                + "order by nam desc";

        rs = JDBC_Helper.selectTongQuat(sql);
        try {
            while (rs.next()) {
                String nam = rs.getString("nam");
                double doanhThu = rs.getDouble("doanhthu");
                thongke mh = new thongke(nam, doanhThu);
                list.add(mh);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
            //Logger.getLogger(GiangVien_Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        // lay ra tong so luong sach da ban va ngay thanh toan
//        Query query = session.createQuery("SELECT h.hoaDon.ngayThanhToan,COUNT(h.soLuong) FROM HoaDonCT h  GROUP BY h.hoaDon.ngayThanhToan");
//        list = query.getResultList();
//        return list;
    }

    public List<thongke> getBieuDoSanPham() {
        List<thongke> list = new ArrayList<>();
        ResultSet rs;
        String sql = "select sach.ten as ten, sum(soluong) as soluong from \n"
                + "hoadonct join sachct on hoadonct.idsachct = sachct.id \n"
                + "join sach on sachct.idsach = sach.id\n"
                + "group by ten";

        rs = JDBC_Helper.selectTongQuat(sql);
        try {
            while (rs.next()) {
                String ten = rs.getString("ten");
                int soluong = rs.getInt("soluong");
                thongke mh = new thongke(soluong, ten);
                list.add(mh);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
            //Logger.getLogger(GiangVien_Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<thongke> getSanPham() {
        List<thongke> list = new ArrayList<>();
        ResultSet rs;
        String sql = "select sach.ma as ma, sach.ten as ten, sum(soluong)as soluong\n"
                + "from hoadonct join sachct on hoadonct.idsachct = sachct.id \n"
                + "join sach on sach.id = sachct.idsach\n"
                + "group by sach.ma, sach.ten";

        rs = JDBC_Helper.selectTongQuat(sql);
        try {
            while (rs.next()) {
                String ma = rs.getString("ma");
                String ten = rs.getString("ten");
                int soluong = rs.getInt("soluong");
                thongke mh = new thongke(soluong, ma, ten);
                list.add(mh);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
            //Logger.getLogger(GiangVien_Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<thongke> getExcel() {
        List<thongke> list = new ArrayList<>();
        ResultSet rs;
        String sql = "select sach.ma as ma, sach.ten as ten, sum(soluong)as soluong,  soluongton\n"
                + "                from hoadonct join sachct on hoadonct.idsachct = sachct.id\n"
                + "                join sach on sach.id = sachct.idsach\n"
                + "                group by sach.ma, sach.ten,soluongton";

        rs = JDBC_Helper.selectTongQuat(sql);
        try {
            while (rs.next()) {
                String ma = rs.getString("ma");
                String ten = rs.getString("ten");
                int soluong = rs.getInt("soluong");
                int soluongton = rs.getInt("soluongton");
                thongke mh = new thongke(soluong, ma, ten, soluongton);
                list.add(mh);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
            //Logger.getLogger(GiangVien_Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<thongke> getHoadon(Date ngay) {
        List<thongke> list = new ArrayList<>();
        ResultSet rs;
        String sql = "select sach.ten as ten, sum(soluong)as soluong, "
                + "dongia, sum(dongia*soluong) as tongtien\n"
                + "from hoadon join hoadonct on hoadon.id = hoadonct.idhd "
                + "join sachct on hoadonct.idsachct = sachct.id \n"
                + "join sach on sach.id = sachct.idsach "
                + "where ngaythanhtoan = ?\n"
                + "group by sach.ma, sach.ten,dongia";

        rs = JDBC_Helper.selectTongQuat(sql, ngay);
        try {
            while (rs.next()) {
                String ten = rs.getString("ten");
                int soluong = rs.getInt("soluong");
                double dongia = rs.getDouble("dongia");
                double tongtien = rs.getDouble("tongtien");
                thongke mh = new thongke(soluong, tongtien, ten, dongia);
                list.add(mh);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
            //Logger.getLogger(GiangVien_Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
