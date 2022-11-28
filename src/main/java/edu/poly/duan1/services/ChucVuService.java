/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.poly.duan1.services;

import edu.poly.duan1.model.ChucVu;
import java.util.List;

/**
 *
 * @author Tran Tien
 */
public interface ChucVuService {
    
    public List<ChucVu> getAll2(int heso);

    public List<ChucVu> getAll();

    public Boolean saveOrUpdate(ChucVu s);

    public Boolean delete(ChucVu s);

    public ChucVu getObjbyMa(String ma);
    
    public List<ChucVu> search(String ten);
}
