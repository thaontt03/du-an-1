/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.poly.duan1.services;

import edu.poly.duan1.model.HoaDon;
import java.util.List;

/**
 *
 * @author AnhTiTan
 */
public interface HoaDonService {

    public List<HoaDon> getAll();

    public List<HoaDon> getObjbyDate(String ngaythanhtoan);

    public Boolean saveOrUpdate(HoaDon hd);

    public Boolean delete(HoaDon hd);

    public HoaDon getObjbyMa(String ma);

    public List<HoaDon> search(int tt);
}
