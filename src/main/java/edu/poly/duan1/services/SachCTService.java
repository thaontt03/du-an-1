/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.poly.duan1.services;

import edu.poly.duan1.model.SachCT;
import java.util.List;

/**
 *
 * @author AnhTiTan
 */
public interface SachCTService {

    public List<SachCT> getAll();

    public Boolean saveOrUpdate(SachCT stc);

    public Boolean delete(SachCT stc);

    public SachCT getObjbyID(int id);
     public List getObjbyTen(String ten);
}
