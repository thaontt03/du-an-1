/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.duan1.services;

import edu.poly.duan1.model.TheLoai;
import java.util.List;

/**
 *
 * @author Nguyen Thi Thu Thao
 */
public interface TheLoaiService {
        public List<TheLoai> getAll();

    public Boolean saveOrUpdate(TheLoai s);

    public Boolean delete(TheLoai s);

    public TheLoai getObjbyMa(String ma);
    
    public List<TheLoai> search(String ten);
}
