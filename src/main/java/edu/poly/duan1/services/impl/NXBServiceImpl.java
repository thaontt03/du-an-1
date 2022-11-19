/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.services.impl;

import edu.poly.duan1.model.NXB;
import edu.poly.duan1.repositories.NXBRepositories;
import edu.poly.duan1.services.NXBService;
import java.util.List;

/**
 *
 * @author AnhTiTan
 */
public class NXBServiceImpl implements NXBService {

    private NXBRepositories nxbRep = new NXBRepositories();

    @Override
    public List<NXB> getAll() {
        return nxbRep.getAll();
    }

    @Override
    public Boolean saveOrUpdate(NXB nxb) {
        return nxbRep.saveOrUpdate(nxb);
    }

    @Override
    public Boolean delete(NXB nxb) {
        return nxbRep.delete(nxb);
    }

    @Override
    public NXB getObjbyMa(String ma) {
        return nxbRep.getObjbyMa(ma);
    }

}
