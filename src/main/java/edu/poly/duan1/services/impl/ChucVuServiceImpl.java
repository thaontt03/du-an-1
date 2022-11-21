/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.services.impl;

import edu.poly.duan1.model.ChucVu;
import edu.poly.duan1.repositories.ChucVuRepository;
import edu.poly.duan1.services.ChucVuService;
import java.util.List;

/**
 *
 * @author Tran Tien
 */
public class ChucVuServiceImpl implements ChucVuService{
    private ChucVuRepository chucvuRepositoryy = new ChucVuRepository();
    @Override
    public List<ChucVu> getAll() {
       return chucvuRepositoryy.getAll();
    }

    @Override
    public Boolean saveOrUpdate(ChucVu s) {
        return chucvuRepositoryy.saveOrUpdate(s);
    }

    @Override
    public Boolean delete(ChucVu s) {
       return chucvuRepositoryy.delete(s);
    }

    @Override
    public ChucVu getObjbyMa(String ma) {
        return chucvuRepositoryy.getObjbyMa(ma);
    }

    @Override
    public List<ChucVu> search(String ten) {
       return chucvuRepositoryy.search(ten);
    }
    
}
