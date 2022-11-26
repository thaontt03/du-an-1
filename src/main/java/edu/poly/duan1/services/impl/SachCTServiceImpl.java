package edu.poly.duan1.services.impl;

import edu.poly.duan1.model.SachCT;
import edu.poly.duan1.repositories.SachCTRepositories;
import edu.poly.duan1.services.SachCTService;
import java.util.List;

/**
 *
 * @author AnhTiTan
 */
public class SachCTServiceImpl implements SachCTService {

    private SachCTRepositories sachCTRep = new SachCTRepositories();

    @Override
    public List<SachCT> getAll() {
        return sachCTRep.getAll();
    }

    @Override
    public Boolean saveOrUpdate(SachCT stc) {
        return sachCTRep.saveOrUpdate(stc);
    }

    @Override
    public Boolean delete(SachCT stc) {
        return sachCTRep.delete(stc);
    }

    @Override
    public SachCT getObjbyID(int id) {
        return sachCTRep.getObjbyID(id);
    }
}
