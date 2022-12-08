/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.repositories;

import edu.poly.duan1.hibernateConfig.HibernateConfig;
import edu.poly.duan1.model.HoaDonCT;
import edu.poly.duan1.model.thongke;
import edu.poly.duan1.ultis.JDBC_Helper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author AnhTiTan
 */
public class HoaDonCTRepositories {

    Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();

    public List<HoaDonCT> getAll() {
        List<HoaDonCT> list = new ArrayList<>();
        Query query = session.createQuery("SELECT h FROM HoaDonCT h");
        list = query.getResultList();
        return list;
    }

    public Boolean saveOrUpdate(HoaDonCT hdct) {
        try {
            transaction.begin();
            session.saveOrUpdate(hdct);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public Boolean delete(HoaDonCT hdct) {
        try {
            transaction.begin();
            session.delete(hdct);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<HoaDonCT> getObjbyMa(String ma) {
        Query query = session.createQuery("SELECT h FROM HoaDonCT h WHERE h.hoaDon.ma = :ma");
        query.setParameter("ma", ma);
        return query.getResultList();
    }

    public List<HoaDonCT> getObjbyDate(Date ngaythanhtoan) {
        Query query = session.createQuery("SELECT tac FROM HoaDonCT tac where tac.hoaDon.ngayThanhToan = :ngaythanhtoan");
        query.setParameter("ngaythanhtoan", ngaythanhtoan);
        return query.getResultList();
    }

    public List<thongke> getList() {
        List<thongke> list = new ArrayList<>();
        ResultSet rs;
        String sql = "SELECT ngayThanhToan as ngay, sum(soluong) as so_luong FROM hoadonct join hoadon on hoadonct.idhd = hoadon.id\n"
                + "GROUP BY ngaythanhtoan";

        rs = JDBC_Helper.selectTongQuat(sql);
        try {
            while (rs.next()) {
                int soluong = rs.getInt("so_luong");
                Date ngay = rs.getDate("ngay");
                thongke mh = new thongke(soluong, ngay);
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

    public HoaDonCT getObjbyID(int id) {
        HoaDonCT hdct = null;
        try {
            Query query = session.createQuery("SELECT h FROM HoaDonCT h WHERE h.id = :id ");
            query.setParameter("id", id);
            hdct = (HoaDonCT) query.getSingleResult();
        } catch (Exception e) {
        }
        return hdct;
    }

    public HoaDonCT getObj(int idhd, int idcts) {
        HoaDonCT hdct = null;
        try {
            Query query = session.createQuery("SELECT h FROM HoaDonCT h WHERE h.hoaDon.id = :idhd AND h.sachCT.id = :idcts");
            query.setParameter("idhd", idhd);
            query.setParameter("idcts", idcts);
            hdct = (HoaDonCT) query.getSingleResult();
        } catch (Exception e) {
        }
        return hdct;
    }

}
