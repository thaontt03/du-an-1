/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.duan1.services.impl;

import edu.poly.duan1.model.thongke;
import edu.poly.duan1.repositories.ThongKeRepository;
import edu.poly.duan1.services.ThongKeService;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Nguyen Thi Thu Thao
 */
public class ThongKeServiceImpl implements ThongKeService{
   private ThongKeRepository thongkeRepositories = new ThongKeRepository();
    @Override
    public List<thongke> getList() {
        return thongkeRepositories.getList();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<thongke> getDoanhThu() {
        return thongkeRepositories.getDoanhThu();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<thongke> getSanPham() {
        return thongkeRepositories.getSanPham();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<thongke> getHoadon(Date ngay) {
        return thongkeRepositories.getHoadon(ngay);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<thongke> getBieuDoSanPham() {
        return thongkeRepositories.getBieuDoSanPham();
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<thongke> getExcel() {
        return thongkeRepositories.getExcel();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
