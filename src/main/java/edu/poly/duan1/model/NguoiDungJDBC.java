/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.model;

/**
 *
 * @author User
 */
public class NguoiDungJDBC {
    
    
    private String id;
    private String ma;
    private String hoten;
    private int gioitinh;
    private String ngaysinh;
    private String diachi;
    private String sdt;
    private String matkhau;
    private String idCV;
    private String ngaytao;
    private String ngaysua;
    private String trangthai;
    private String tongtienmuahang;

    public NguoiDungJDBC() {
    }

    public NguoiDungJDBC(String id, String ma, String hoten, int gioitinh, String ngaysinh, String diachi, String sdt, String matkhau, String idCV, String ngaytao, String ngaysua, String trangthai, String tongtienmuahang) {
        this.id = id;
        this.ma = ma;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.sdt = sdt;
        this.matkhau = matkhau;
        this.idCV = idCV;
        this.ngaytao = ngaytao;
        this.ngaysua = ngaysua;
        this.trangthai = trangthai;
        this.tongtienmuahang = tongtienmuahang;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public int getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getIdCV() {
        return idCV;
    }

    public void setIdCV(String idCV) {
        this.idCV = idCV;
    }

    public String getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(String ngaytao) {
        this.ngaytao = ngaytao;
    }

    public String getNgaysua() {
        return ngaysua;
    }

    public void setNgaysua(String ngaysua) {
        this.ngaysua = ngaysua;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getTongtienmuahang() {
        return tongtienmuahang;
    }

    public void setTongtienmuahang(String tongtienmuahang) {
        this.tongtienmuahang = tongtienmuahang;
    }
    
    
    
    
}
