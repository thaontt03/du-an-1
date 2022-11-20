/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.duan1.services;

import edu.poly.duan1.model.Sach;
import java.util.List;

/**
 *
 * @author Nguyen Thi Thu Thao
 */
public interface SachServices {

    public List<Sach> getAll();

    public Boolean saveOrUpdate(Sach s);

    public Boolean delete(Sach s);

    public Sach getObjbyMa(String ma);

    public List<Sach> search(String ten);
}
