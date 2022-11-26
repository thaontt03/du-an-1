/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.poly.duan1.services;

import edu.poly.duan1.model.HoaDonCT;
import java.util.List;

/**
 *
 * @author AnhTiTan
 */
public interface HoaDonCTService {

    public List<HoaDonCT> getAll();

    public Boolean saveOrUpdate(HoaDonCT hdct);

    public Boolean delete(HoaDonCT hdct);

    public List<HoaDonCT> getObjbyMa(String ma);

    public HoaDonCT getObj(int idhd, int idcts);

}
