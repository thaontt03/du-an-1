/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.services.impl;

import edu.poly.duan1.model.NCC;
import edu.poly.duan1.repositories.NCCRepositories;
import edu.poly.duan1.services.NCCService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AnhTiTan
 */
public class NCCServiceImpl implements NCCService {

    private NCCRepositories nccRep = new NCCRepositories();

    @Override
    public List<NCC> getAll() {
        return nccRep.getAll();
    }

    @Override
    public Boolean saveOrUpdate(NCC ncc) {
        return nccRep.saveOrUpdate(ncc);
    }

    @Override
    public Boolean delete(NCC ncc) {
        return nccRep.delete(ncc);
    }

    @Override
    public NCC getObjbyMa(String ma) {
        return nccRep.getObjbyMa(ma);
    }

    @Override
    public List<NCC> search(String ten) {
        return nccRep.search(ten);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<NCC> getAll2(int heso) {
        List<NCC> list = nccRep.getAll2(heso);
        List<NCC> list2 = new ArrayList<>();
        int index = 0;
        for (NCC x : list) {
            list2.add(x);
            index++;
            if(index ==5)
            break;
        }
       return list2;
    }

}
