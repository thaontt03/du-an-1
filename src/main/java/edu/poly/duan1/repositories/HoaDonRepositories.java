package edu.poly.duan1.repositories;

import edu.poly.duan1.hibernateConfig.HibernateConfig;
import edu.poly.duan1.model.HoaDon;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author AnhTiTan
 */
public class HoaDonRepositories {

    Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();

    public List<HoaDon> getAll() {
        List<HoaDon> list = new ArrayList<>();
        Query query = session.createQuery("SELECT h FROM HoaDon h");
        list = query.getResultList();
        return list;
    }

    public Boolean saveOrUpdate(HoaDon hd) {
        try {
            transaction.begin();
            session.saveOrUpdate(hd);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public Boolean delete(HoaDon hd) {
        try {
            transaction.begin();
            session.delete(hd);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<HoaDon> search(int tt) {
        List<HoaDon> list = new ArrayList<>();
        Query query = session.createQuery("SELECT h FROM HoaDon h where h.trangThai = :tt");
        query.setParameter("tt", tt);
        list = query.getResultList();
        return list;
    }

    public HoaDon getObjbyMa(String ma) {
        HoaDon hd = null;
        try {
            Query query = session.createQuery("SELECT h FROM HoaDon h WHERE h.ma = :ma");
            query.setParameter("ma", ma);
            hd = (HoaDon) query.getSingleResult();
        } catch (Exception e) {
        }
        return hd;
    }
}
