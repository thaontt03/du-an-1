/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.repositories;

import edu.poly.duan1.hibernateConfig.HibernateConfig;
import edu.poly.duan1.model.ChucVu;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Tran Tien
 */
public class ChucVuRepository {
     Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();

    public List<ChucVu> getAll() {
        List<ChucVu> list = new ArrayList<>();
        Query query = session.createQuery("SELECT c FROM ChucVu c");
        list = query.getResultList();
        return list;
    }

    public Boolean saveOrUpdate(ChucVu s) {
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

    public Boolean delete(ChucVu s) {
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

    public ChucVu getObjbyMa(String ma) {
        ChucVu s = null;
        try {
            Query query = session.createQuery("select c from ChucVu c where c.ma = :ma");
            query.setParameter("ma", ma);
            s = (ChucVu) query.getSingleResult();

        } catch (Exception e) {
        }
        return s;
    }
}
