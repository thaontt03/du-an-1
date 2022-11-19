/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author AnhTiTan
 */
@Data

@Entity
@Table(name = "NCC")
public class NCC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String ma;

    @Column
    private String ten;

    @Column
    private Date ngayTao;

    @Column
    private Date ngaySua;

    @Column
    private int trangThai;

    @OneToMany(mappedBy = "NCC", fetch = FetchType.LAZY)
    List<SachCT> listSach;

    @Override
    public String toString() {
        return ten;
    }
}
