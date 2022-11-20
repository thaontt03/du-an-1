package edu.poly.duan1.repositories;

import edu.poly.duan1.hibernateConfig.HibernateConfig;
import edu.poly.duan1.model.NXB;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author AnhTiTan
 */
public class NXBRepositories {

    Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();

    public List<NXB> getAll() {
        List<NXB> list = new ArrayList<>();
        Query query = session.createQuery("SELECT c FROM NXB c");
        list = query.getResultList();
        return list;
    }

    public List<NXB> search(String ten) {
        List<NXB> list = new ArrayList();
        Query query = session.createQuery("SELECT c From NXB c where c.ten like :ten");
        query.setParameter("ten", "%" + ten + "%");
        list = query.getResultList();
        return list;
    }

    public Boolean saveOrUpdate(NXB nxb) {
        try {
            transaction.begin();
            session.saveOrUpdate(nxb);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete(NXB nxb) {
        try {
            transaction.begin();
            session.delete(nxb);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public NXB getObjbyMa(String ma) {
        NXB nxb = null;
        try {
            Query query = session.createQuery("select c from NXB c where c.ma = :ma");
            query.setParameter("ma", ma);
            nxb = (NXB) query.getSingleResult();

        } catch (Exception e) {
        }
        return nxb;
    }
}
