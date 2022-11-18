/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Nguyen Thi Thu Thao
 */
@Data

@Entity
@Table(name = "Sach")
public class Sach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column
    private String ma;

    @Column
    private String ten;

    @Column
    private int trangThai;

    @Column
    private Date ngayTao;

    @Column
    private Date ngaySua;

    @OneToMany(mappedBy = "sach", fetch = FetchType.LAZY)
    List<SachCT> list_SachCT;
}
