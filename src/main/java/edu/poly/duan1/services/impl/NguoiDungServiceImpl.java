/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.services.impl;

import edu.poly.duan1.model.NguoiDung;
import edu.poly.duan1.repositories.NguoiDungRepositories;
import edu.poly.duan1.services.NguoiDungService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AnhTiTan
 */
public class NguoiDungServiceImpl implements NguoiDungService {

    private NguoiDungRepositories nguoiDungRepositories = new NguoiDungRepositories();

    @Override
    public List<NguoiDung> getAll() {
        return nguoiDungRepositories.getAll();
    }

    @Override
    public Boolean saveOrUpdate(NguoiDung nd) {
        return nguoiDungRepositories.saveOrUpdate(nd);
    }

    @Override
    public Boolean delete(NguoiDung nd) {
        return nguoiDungRepositories.delete(nd);
    }

    @Override
    public NguoiDung getObjbyMa(String ma) {
        return nguoiDungRepositories.getObjbyMa(ma);
    }

    @Override
    public List<NguoiDung> search(String ma) {
        return nguoiDungRepositories.search(ma);
    }

    @Override
    public List<NguoiDung> getAll2(int heso) {
        List<NguoiDung> list = nguoiDungRepositories.getAll2(heso);
        List<NguoiDung> list2 = new ArrayList<>();
        int index = 0;
        for (NguoiDung x : list) {
            list2.add(x);
            index++;
            if(index ==5)
            break;
        }
       return list2;
    }

  
}
