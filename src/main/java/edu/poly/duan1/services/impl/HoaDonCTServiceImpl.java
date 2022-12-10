/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.services.impl;

import edu.poly.duan1.model.HoaDonCT;
import edu.poly.duan1.model.thongke;
import edu.poly.duan1.repositories.HoaDonCTRepositories;
import edu.poly.duan1.services.HoaDonCTService;
import java.util.Date;
import java.util.List;

/**
 *
 * @author AnhTiTan
 */
public class HoaDonCTServiceImpl implements HoaDonCTService {

    private HoaDonCTRepositories hoaDonRep = new HoaDonCTRepositories();

    @Override
    public List<HoaDonCT> getAll() {
        return hoaDonRep.getAll();
    }

    @Override
    public Boolean saveOrUpdate(HoaDonCT hdct) {
        return hoaDonRep.saveOrUpdate(hdct);
    }

    @Override
    public Boolean delete(HoaDonCT hdct) {
        return hoaDonRep.delete(hdct);
    }

    @Override
    public List<HoaDonCT> getObjbyMa(String ma) {
        return hoaDonRep.getObjbyMa(ma);
    }

    @Override
    public HoaDonCT getObj(int idhd, int idcts) {
        return hoaDonRep.getObj(idhd, idcts);
    }

    @Override
    public HoaDonCT getObjbyID(int id) {
        return hoaDonRep.getObjbyID(id);
    }

    @Override
    public List<HoaDonCT> getObjbyDate(Date date) {
        return hoaDonRep.getObjbyDate(date);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<thongke> getList() {
        return hoaDonRep.getList();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
