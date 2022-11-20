/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.duan1.repositories;

import edu.poly.duan1.hibernateConfig.HibernateConfig;
import edu.poly.duan1.model.TheLoai;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Nguyen Thi Thu Thao
 */
public class TheLoaiRepositories {

    Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();

    public List<TheLoai> getAll() {
        List<TheLoai> list = new ArrayList<>();
        Query query = session.createQuery("SELECT c FROM TheLoai c");
        list = query.getResultList();
        return list;
    }

    public List<TheLoai> search(String ten) {
        List<TheLoai> list = new ArrayList();
        Query query = session.createQuery("SELECT c From TheLoai c where c.ten like :ten");
        query.setParameter("ten", "%" + ten + "%");
        list = query.getResultList();
        return list;
    }

    public Boolean saveOrUpdate(TheLoai s) {
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

    public Boolean delete(TheLoai s) {
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

    public TheLoai getObjbyMa(String ma) {
        TheLoai s = null;
        try {
            Query query = session.createQuery("select c from TheLoai c where c.ma = :ma");
            query.setParameter("ma", ma);
            s = (TheLoai) query.getSingleResult();

        } catch (Exception e) {
        }
        return s;
    }
}
