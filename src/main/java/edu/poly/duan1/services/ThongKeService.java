/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.duan1.services;

import edu.poly.duan1.model.thongke;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Nguyen Thi Thu Thao
 */
public interface ThongKeService {

    public List<thongke> getList();

    public List<thongke> getDoanhThu();

    public List<thongke> getSanPham();

    public List<thongke> getBieuDoSanPham();

    public List<thongke> getHoadon(Date ngay);

    public List<thongke> getExcel();
}
