/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.duan1.model;

import java.util.Date;

/**
 *
 * @author Nguyen Thi Thu Thao
 */
public class thongke {
    private int soLuong;
    private Date ngay;

    public thongke(int soLuong, Date ngay) {
        this.soLuong = soLuong;
        this.ngay = ngay;
    }

    public thongke() {
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }
}

