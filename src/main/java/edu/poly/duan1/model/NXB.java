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
@Table(name = "NXB")
public class NXB {

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

    @OneToMany(mappedBy = "NXB", fetch = FetchType.LAZY)
    List<SachCT> listSach;

}
/*
Id INT IDENTITY(1,1) PRIMARY KEY,
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(50) DEFAULT NULL,
NgayTao DATE DEFAULT NULL,
NgaySua DATE DEFAULT NULL,
TrangThai INT DEFAULT 0
 */
