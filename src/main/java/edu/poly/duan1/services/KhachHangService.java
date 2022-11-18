/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.duan1.services;

import edu.poly.duan1.model.KhachHang;
import java.util.List;

/**
 *
 * @author Nguyen Thi Thu Thao
 */
public interface KhachHangService {

    public List<KhachHang> getAll();

    public Boolean saveOrUpdate(KhachHang s);

    public Boolean delete(KhachHang s);

    public KhachHang getObjbyMa(String ma);
}
