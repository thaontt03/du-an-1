/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.duan1.repositories;

import edu.poly.duan1.hibernateConfig.HibernateConfig;
import edu.poly.duan1.model.KhachHang;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Nguyen Thi Thu Thao
 */
public class KhachHangRepositories {
     Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();

    public List<KhachHang> getAll() {
        List<KhachHang> list = new ArrayList<>();
        Query query = session.createQuery("SELECT c FROM KhachHang c");
        list = query.getResultList();
        return list;
    }
     public List<KhachHang> getAll2(int heso) {
        List<KhachHang> list = new ArrayList<>();
        Query query = session.createQuery("SELECT c FROM KhachHang c");
        list = query.setFirstResult(heso).getResultList();
        return list;
    }

    public Boolean saveOrUpdate(KhachHang s) {
        try {
            transaction.begin();
            session.saveOrUpdate(s);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete(KhachHang s) {
        try {
            transaction.begin();
            session.delete(s);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public KhachHang getObjbyMa(String ma) {
        KhachHang s = null;
        try {
            Query query = session.createQuery("select c from KhachHang c where c.ma = :ma");
            query.setParameter("ma", ma);
            s = (KhachHang) query.getSingleResult();

        } catch (Exception e) {
        }
        return s;
    }
    public List<KhachHang> search(String ten) {
        List<KhachHang> list = new ArrayList();
        Query query = session.createQuery("SELECT c From KhachHang c where c.ten like :ten");
        query.setParameter("ten", "%" + ten + "%");
        list = query.getResultList();
        return list;
    }
}
