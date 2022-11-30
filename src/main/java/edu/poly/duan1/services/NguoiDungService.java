/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.poly.duan1.services;

import edu.poly.duan1.model.NguoiDung;
import java.util.List;

/**
 *
 * @author AnhTiTan
 */
public interface NguoiDungService {

    public List<NguoiDung> getAll();
    
    public List<NguoiDung> search(String ma);

    public Boolean saveOrUpdate(NguoiDung nd);

    public Boolean delete(NguoiDung nd);

    public NguoiDung getObjbyMa(String ma);
}
