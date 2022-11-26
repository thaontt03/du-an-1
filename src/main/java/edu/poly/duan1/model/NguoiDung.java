package edu.poly.duan1.model;

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
@Table(name = "NguoiDung")
public class NguoiDung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String ma;

    @Column
    private String hoTen;

    @Column
    private String gioiTinh;

    @Column
    private Date ngaySinh;

    @Column
    private String diaChi;

    @Column
    private String sdt;

    @Column
    private String matKhau;

    @ManyToOne
    @JoinColumn(name = "idCV")
    private ChucVu chucVu;

    @Column
    private Date ngayTao;

    @Column
    private Date ngaySua;

    @Column
    private int trangThai;

    @OneToMany(mappedBy = "nguoiDung", fetch = FetchType.LAZY)
    List<HoaDon> listHD;

}
/*
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
HoTen NVARCHAR(30) DEFAULT NULL,
GioiTinh NVARCHAR(10) DEFAULT NULL,
NgaySinh DATE DEFAULT NULL,
DiaChi NVARCHAR(100) DEFAULT NULL,
Sdt VARCHAR(30) DEFAULT NULL,
MatKhau VARCHAR(MAX) DEFAULT NULL,
IdCV UNIQUEIDENTIFIER,
NgayTao DATE DEFAULT NULL,
NgaySua DATE DEFAULT NULL,
TrangThai INT DEFAULT 0
 */
