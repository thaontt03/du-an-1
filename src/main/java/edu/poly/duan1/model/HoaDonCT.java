/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author AnhTiTan
 */
@Data

@Entity
@Table(name = "HoaDonCT")

public class HoaDonCT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idSachCT")
    private SachCT sachCT;

    @ManyToOne
    @JoinColumn(name = "idHD")
    private HoaDon hoaDon;
//
//    @Column
//    private String ma;

    @Column
    private int soLuong;

    @Column
    private Double donGia;

    @Column
    private Date ngayTao;

    @Column
    Date ngaySua;

    @Column
    private int trangThai;

    public Double ThanhTien(int soLuong, double donGia) {
        return donGia * soLuong;
    }

}
/*
    Id INT IDENTITY(1,1) PRIMARY KEY,
    IdSachCT INT,
    IdHD INT,
    Ma VARCHAR(20) UNIQUE,
    DonGia DECIMAL(20,0) DEFAULT 0,
    SoLuong INT,
    NgayTao DATE DEFAULT NULL,
    NgaySua DATE DEFAULT NULL,
    TrangThai INT DEFAULT 0,
 */
