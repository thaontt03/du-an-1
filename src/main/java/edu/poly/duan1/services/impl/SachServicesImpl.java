/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.duan1.services.impl;

import edu.poly.duan1.model.Sach;
import edu.poly.duan1.repositories.SachRepositories;
import edu.poly.duan1.services.SachServices;
import java.util.List;

/**
 *
 * @author Nguyen Thi Thu Thao
 */
public class SachServicesImpl implements SachServices {

    private SachRepositories sachRepositories = new SachRepositories();

    @Override
    public List<Sach> getAll() {
        return sachRepositories.getAll();
    }

    @Override
    public Boolean saveOrUpdate(Sach s) {
        return sachRepositories.saveOrUpdate(s);
    }

    @Override
    public Boolean delete(Sach s) {
        return sachRepositories.delete(s);
    }

    @Override
    public Sach getObjbyMa(String ma) {
        return sachRepositories.getObjbyMa(ma);
    }

    @Override
    public List<Sach> search(String ten) {
        return sachRepositories.search(ten);
    }

}
