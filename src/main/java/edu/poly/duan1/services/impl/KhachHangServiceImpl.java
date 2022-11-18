/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.duan1.services.impl;

import edu.poly.duan1.model.KhachHang;
import edu.poly.duan1.repositories.KhachHangRepositories;
import edu.poly.duan1.services.KhachHangService;
import java.util.List;

/**
 *
 * @author Nguyen Thi Thu Thao
 */
public class KhachHangServiceImpl implements KhachHangService {

    private KhachHangRepositories khachHangRepositories = new KhachHangRepositories();

    @Override
    public List<KhachHang> getAll() {
        return khachHangRepositories.getAll();
    }

    @Override
    public Boolean saveOrUpdate(KhachHang s) {
        return khachHangRepositories.saveOrUpdate(s);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean delete(KhachHang s) {
        return khachHangRepositories.delete(s);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public KhachHang getObjbyMa(String ma) {
        return khachHangRepositories.getObjbyMa(ma);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
