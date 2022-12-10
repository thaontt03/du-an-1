/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
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
@Table(name = "SachCT")
public class SachCT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idSach")
    private Sach sach;

    @ManyToOne
    @JoinColumn(name = "idTheLoai")
    private TheLoai theLoai;

    @ManyToOne
    @JoinColumn(name = "idNXB")
    private NXB NXB;

//    @ManyToOne
//    @JoinColumn(name = "idNCC")
//    private NCC NCC;

    @Column
    private String moTa;

    @Column
    private int soLuongTon;

    @Column
    private BigDecimal giaNhap;

    @Column
    private BigDecimal giaBan;

    @Column
    private Date ngayTao;

    @Column
    private Date ngaySua;

    @Column
    private int trangThai;

    @Column
    private String tacGia;

    @OneToMany(mappedBy = "sachCT", fetch = FetchType.LAZY)
    List<HoaDonCT> listHoaDonCT;
}
/*
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
IdSach UNIQUEIDENTIFIER,
IdTheLoai UNIQUEIDENTIFIER,
IdNXB UNIQUEIDENTIFIER,
IdNCC UNIQUEIDENTIFIER,
MoTa NVARCHAR(50) DEFAULT NULL,
SoLuongTon INT,
GiaNhap DECIMAL(20,0) DEFAULT 0,
GiaBan DECIMAL(20,0) DEFAULT 0,
NgayTao DATE DEFAULT NULL,
NgaySua DATE DEFAULT NULL,
TrangThai INT DEFAULT 0
 */
