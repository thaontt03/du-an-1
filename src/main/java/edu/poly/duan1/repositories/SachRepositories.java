/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.duan1.repositories;

import edu.poly.duan1.hibernateConfig.HibernateConfig;
import edu.poly.duan1.model.Sach;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Nguyen Thi Thu Thao
 */
public class SachRepositories {

    Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();

    public List<Sach> getAll() {
        List<Sach> list = new ArrayList<>();
        Query query = session.createQuery("SELECT c FROM Sach c");
        list = query.getResultList();
        return list;
    }

    public Boolean saveOrUpdate(Sach s) {
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

    public Boolean delete(Sach s) {
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

    public Sach getObjbyMa(String ma) {
        Sach s = null;
        try {
            Query query = session.createQuery("select c from Sach c where c.ma = :ma");
            query.setParameter("ma", ma);
            s = (Sach) query.getSingleResult();

        } catch (Exception e) {
        }
        return s;
    }

    public List<Sach> search(String ten) {
        List<Sach> list = new ArrayList();
        Query query = session.createQuery("SELECT c From Sach c where c.ten like :ten");
        query.setParameter("ten", "%" + ten + "%");
        list = query.getResultList();
        return list;
    }
}
