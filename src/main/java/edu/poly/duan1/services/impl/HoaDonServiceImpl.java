/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.services.impl;

import edu.poly.duan1.model.HoaDon;
import edu.poly.duan1.repositories.HoaDonRepositories;
import edu.poly.duan1.services.HoaDonService;
import java.util.List;

/**
 *
 * @author AnhTiTan
 */
public class HoaDonServiceImpl implements HoaDonService {

    private HoaDonRepositories hoaDonRepositories = new HoaDonRepositories();

    @Override
    public List<HoaDon> getAll() {
        return hoaDonRepositories.getAll();
    }

    @Override
    public Boolean saveOrUpdate(HoaDon hd) {
        return hoaDonRepositories.saveOrUpdate(hd);
    }

    @Override
    public Boolean delete(HoaDon hd) {
        return hoaDonRepositories.delete(hd);
    }

    @Override
    public HoaDon getObjbyMa(String ma) {
        return hoaDonRepositories.getObjbyMa(ma);
    }

    @Override
    public List<HoaDon> search(int tt) {
        return hoaDonRepositories.search(tt);
    }

}
