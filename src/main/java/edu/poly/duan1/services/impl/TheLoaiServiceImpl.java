/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.duan1.services.impl;

import edu.poly.duan1.model.TheLoai;
import edu.poly.duan1.repositories.TheLoaiRepositories;
import edu.poly.duan1.services.TheLoaiService;
import java.util.List;

/**
 *
 * @author Nguyen Thi Thu Thao
 */
public class TheLoaiServiceImpl implements TheLoaiService {

    private TheLoaiRepositories theLoaiRepositories = new TheLoaiRepositories();

    @Override
    public List<TheLoai> getAll() {
        return theLoaiRepositories.getAll();
    }

    @Override
    public Boolean saveOrUpdate(TheLoai s) {
        return theLoaiRepositories.saveOrUpdate(s);
    }

    @Override
    public Boolean delete(TheLoai s) {
        return theLoaiRepositories.delete(s);
    }

    @Override
    public TheLoai getObjbyMa(String ma) {
        return theLoaiRepositories.getObjbyMa(ma);
    }

    @Override
    public List<TheLoai> search(String ten) {
        return theLoaiRepositories.search(ten);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
