package edu.poly.duan1.repositories;

import edu.poly.duan1.hibernateConfig.HibernateConfig;
import edu.poly.duan1.model.SachCT;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author AnhTiTan
 */
public class SachCTRepositories {

    Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();

    public List<SachCT> getAll() {
        List<SachCT> list = new ArrayList<>();
        Query query = session.createQuery("SELECT c FROM SachCT c");
        list = query.getResultList();
        return list;
    }

    public Boolean saveOrUpdate(SachCT sct) {
        try {
            transaction.begin();
            session.saveOrUpdate(sct);
            transaction.commit();
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete(SachCT sct) {
        try {
            transaction.begin();
            session.delete(sct);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public SachCT getObjbyID(int id) {
        SachCT sct = null;
        try {
            Query query = session.createQuery("select c from SachCT c where c.id = :id");
            query.setParameter("id", id);
            sct = (SachCT) query.getSingleResult();

        } catch (Exception e) {
        }
        return sct;
    }

    public List findByTen(String ten) {
        List<SachCT> list = new ArrayList<>();
        try {
            Query query = session.createQuery("SELECT c FROM SachCT c WHERE c.sach.ten LIKE :ten ORDER BY c.soLuongTon DESC");
            query.setParameter("ten", "%" + ten + "%");
            list = query.getResultList();
        } catch (Exception e) {
        }
        return list;
    }

}
