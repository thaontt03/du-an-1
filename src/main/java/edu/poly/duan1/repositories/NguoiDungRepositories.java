/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.repositories;

import edu.poly.duan1.hibernateConfig.HibernateConfig;
import edu.poly.duan1.model.NguoiDung;
import edu.poly.duan1.model.TheLoai;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author AnhTiTan
 */
public class NguoiDungRepositories {

    private final Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();

    public List<NguoiDung> getAll() {
        List<NguoiDung> list = new ArrayList<>();
        Query query = session.createQuery("SELECT c FROM NguoiDung c");
        list = query.getResultList();
        return list;
    }
    public List<NguoiDung> search(String ma) {
        List<NguoiDung> list = new ArrayList();
        Query query = session.createQuery("SELECT c From NguoiDung c where c.ma like :ma");
        query.setParameter("ma", "%" + ma + "%");
        list = query.getResultList();
        return list;
    }

    public Boolean saveOrUpdate(NguoiDung nd) {
        try {
            transaction.begin();
            session.saveOrUpdate(nd);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public Boolean delete(NguoiDung nd) {
        try {
            transaction.begin();
            session.delete(nd);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public NguoiDung getObjbyMa(String ma) {
        NguoiDung nd = null;
        try {
            Query query = session.createQuery("select c from NguoiDung c where c.ma = :ma");
            query.setParameter("ma", ma);
            nd = (NguoiDung) query.getSingleResult();

        } catch (Exception e) {
        }
        return nd;
    }
}
