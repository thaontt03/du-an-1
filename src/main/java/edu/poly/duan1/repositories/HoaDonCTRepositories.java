/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.repositories;

import edu.poly.duan1.hibernateConfig.HibernateConfig;
import edu.poly.duan1.model.HoaDonCT;
import java.util.ArrayList;
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
