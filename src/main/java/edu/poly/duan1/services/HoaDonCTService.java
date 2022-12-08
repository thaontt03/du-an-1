/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.poly.duan1.services;

import edu.poly.duan1.model.HoaDonCT;
import edu.poly.duan1.model.thongke;
import java.util.Date;
import java.util.List;

/**
 *
 * @author AnhTiTan
 */
public interface HoaDonCTService {

    public List<HoaDonCT> getAll();

    public List<thongke> getList();

    public Boolean saveOrUpdate(HoaDonCT hdct);

    public Boolean delete(HoaDonCT hdct);

    public List<HoaDonCT> getObjbyMa(String ma);

    public HoaDonCT getObj(int idhd, int idcts);

    public HoaDonCT getObjbyID(int id);

    public List<HoaDonCT> getObjbyDate(Date date);
}
