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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author AnhTiTan
 */
@Data

@Entity
@Table(name = "HoaDon")

public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String ma;

    @Column
    private Date ngayTao;

    @Column
    private Date ngayThanhToan;

    @Column
    private Date ngaySua;

    @Column
    private int trangThai;

    @ManyToOne()
    @JoinColumn(name = "idNguoiTao")
    private NguoiDung nguoiDungTao;
    
    @ManyToOne()
    @JoinColumn(name = "idNguoiThanhToan")
     private NguoiDung nguoiDungThanhToan;

    @OneToMany(mappedBy = "hoaDon", fetch = FetchType.LAZY)
    List<HoaDonCT> listHoaDonCT;

}
/*
    Id INT IDENTITY(1,1) PRIMARY KEY,
    IdKH INT,
    IdND INT,
    Ma VARCHAR(20) UNIQUE,
    NgayTao DATE DEFAULT NULL,
    NgayThanhToan DATE DEFAULT NULL,
    NgaySua DATE DEFAULT NULL,
    TrangThai INT DEFAULT 0,
 */
