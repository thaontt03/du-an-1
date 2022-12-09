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
    private String nam;
    private String thang;
    private double doanhThu;
    private double loinhuan;
    private String ma;
    private String ten;
    private double dongia;
    private int soluonton;

    public thongke(int soLuong, String ma, String ten, int soluonton) {
        this.soLuong = soLuong;
        this.ma = ma;
        this.ten = ten;
        this.soluonton = soluonton;
    }

    public int getSoluonton() {
        return soluonton;
    }

    public void setSoluonton(int soluonton) {
        this.soluonton = soluonton;
    }
    
    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }

    public thongke(int soLuong, String ten) {
        this.soLuong = soLuong;
        this.ten = ten;
    }

    public thongke(int soLuong, double doanhThu, String ten, double dongia) {
        this.soLuong = soLuong;
        this.doanhThu = doanhThu;
        this.ten = ten;
        this.dongia = dongia;
    }

    public thongke(int soLuong, String ma, String ten) {
        this.soLuong = soLuong;
        this.ma = ma;
        this.ten = ten;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public thongke(int soLuong, String nam, String thang, double doanhThu, double loinhuan) {
        this.soLuong = soLuong;
        this.nam = nam;
        this.thang = thang;
        this.doanhThu = doanhThu;
        this.loinhuan = loinhuan;
    }

    public thongke(String nam, double doanhThu) {
        this.nam = nam;
        this.doanhThu = doanhThu;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public double getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(double doanhThu) {
        this.doanhThu = doanhThu;
    }

    public double getLoinhuan() {
        return loinhuan;
    }

    public void setLoinhuan(double loinhuan) {
        this.loinhuan = loinhuan;
    }

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
