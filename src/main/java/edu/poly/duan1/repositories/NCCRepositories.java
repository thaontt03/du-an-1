package edu.poly.duan1.repositories;

import edu.poly.duan1.hibernateConfig.HibernateConfig;
import edu.poly.duan1.model.NCC;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author AnhTiTan
 */
public class NCCRepositories {

    Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();

    public List<NCC> getAll() {
        List<NCC> list = new ArrayList<>();
        Query query = session.createQuery("SELECT c FROM NCC c");
        list = query.getResultList();
        return list;
    }
    public List<NCC> search(String ten) {
        List<NCC> list = new ArrayList();
        Query query = session.createQuery("SELECT c From NCC c where c.ten like :ten");
        query.setParameter("ten", "%" + ten + "%");
        list = query.getResultList();
        return list;
    }

    public Boolean saveOrUpdate(NCC ncc) {
        try {
            transaction.begin();
            session.saveOrUpdate(ncc);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete(NCC ncc) {
        try {
            transaction.begin();
            session.delete(ncc);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public NCC getObjbyMa(String ma) {
        NCC ncc = null;
        try {
            Query query = session.createQuery("select c from NCC c where c.ma = :ma");
            query.setParameter("ma", ma);
            ncc = (NCC) query.getSingleResult();

        } catch (Exception e) {
        }
        return ncc;
    }
}
