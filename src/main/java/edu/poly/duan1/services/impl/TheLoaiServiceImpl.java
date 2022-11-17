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
public class TheLoaiServiceImpl implements TheLoaiService{
private TheLoaiRepositories sachRepositories = new TheLoaiRepositories();
    @Override
    public List<TheLoai> getAll() {
        return sachRepositories.getAll();
    }

    @Override
    public Boolean saveOrUpdate(TheLoai s) {
        return sachRepositories.saveOrUpdate(s);
    }

    @Override
    public Boolean delete(TheLoai s) {
        return sachRepositories.delete(s);
    }

    @Override
    public TheLoai getObjbyMa(String ma) {
       return sachRepositories.getObjbyMa(ma);
    }
    
}
