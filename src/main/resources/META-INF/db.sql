    CREATE DATABASE DUAN_1_GROUP1
GO
    USE DUAN_1_GROUP1
GO
--Sách
CREATE TABLE Sach(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Ma VARCHAR(20) UNIQUE,
    Ten NVARCHAR(50) DEFAULT NULL,
    NgayTao DATE DEFAULT NULL,
    NgaySua DATE DEFAULT NULL,
    TrangThai INT DEFAULT 0

)
-- Chi tiết sách
CREATE TABLE SachCT(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    IdSach INT,
    IdTheLoai INT,
    IdNXB INT,
    IdNCC INT,
    MoTa NVARCHAR(500) DEFAULT NULL,
    SoLuongTon INT,
    GiaNhap DECIMAL(20,0) DEFAULT 0,
    GiaBan DECIMAL(20,0) DEFAULT 0,
    NgayTao DATE DEFAULT NULL,
    NgaySua DATE DEFAULT NULL,
    TrangThai INT DEFAULT 0,
    TacGia nvarchar(50)
)


--Thể Loại
CREATE TABLE TheLoai(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Ma VARCHAR(20) UNIQUE,
    Ten NVARCHAR(50) DEFAULT NULL,
    NgayTao DATE DEFAULT NULL,
    NgaySua DATE DEFAULT NULL,
    TrangThai INT DEFAULT 0
)
--Nhà xuất bản
CREATE TABLE NXB(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Ma VARCHAR(20) UNIQUE,
    Ten NVARCHAR(50) DEFAULT NULL,
    NgayTao DATE DEFAULT NULL,
    NgaySua DATE DEFAULT NULL,
    TrangThai INT DEFAULT 0
)
--Khách hàng
CREATE TABLE KhachHang(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Ma VARCHAR(20) UNIQUE,
    HoTen NVARCHAR(50),
    Sdt VARCHAR(30) DEFAULT NULL,
    DiaChi NVARCHAR(100) DEFAULT NULL,
    NgayTao DATE DEFAULT NULL,
    NgaySua DATE DEFAULT NULL,
    TrangThai INT DEFAULT 0
)
--Hóa đơn
IF OBJECT_ID('HoaDon') IS NOT NULL
    DROP TABLE HoaDon
GO
CREATE TABLE HoaDon(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    IdKH INT,
    IdND INT,
    Ma VARCHAR(20) UNIQUE,
    NgayTao DATE DEFAULT NULL,
    NgayThanhToan DATE DEFAULT NULL,
    NgaySua DATE DEFAULT NULL,
    TrangThai INT DEFAULT 0,
)
--Hóa đơn chi tiết
IF OBJECT_ID('HoaDonCT') IS NOT NULL
    DROP TABLE HoaDonCT 
GO
CREATE TABLE HoaDonCT(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    IdSachCT INT,
    IdHD INT,
    Ma VARCHAR(20) UNIQUE,
    DonGia DECIMAL(20,0) DEFAULT 0,
    SoLuong INT,
    NgayTao DATE DEFAULT NULL,
    NgaySua DATE DEFAULT NULL,
    TrangThai INT DEFAULT 0,
)
--Chức vụ
CREATE TABLE ChucVu(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Ma VARCHAR(20) UNIQUE,
    Ten NVARCHAR(30),
    NgayTao DATE DEFAULT NULL,
    NgaySua DATE DEFAULT NULL,
    TrangThai INT DEFAULT 0,
)
--Nhà cung cấp
CREATE TABLE NCC(
    Id INT IDENTITY(1,1) PRIMARY KEY ,
    Ma VARCHAR(20) UNIQUE,
    Ten NVARCHAR(30),
    Sdt VARCHAR(30) DEFAULT NULL,
    Email VARCHAR(100) DEFAULT NULL,
    DiaChi NVARCHAR(100) DEFAULT NULL,
    NgayTao DATE DEFAULT NULL,
    NgaySua DATE DEFAULT NULL,
    TrangThai INT DEFAULT 0,
)
--Người dùng
IF OBJECT_ID('NguoiDung') IS NOT NULL
    DROP TABLE NguoiDung 
GO
CREATE TABLE NguoiDung(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Ma VARCHAR(20) UNIQUE,
    HoTen NVARCHAR(30) DEFAULT NULL,
    GioiTinh NVARCHAR(10) DEFAULT NULL,
    NgaySinh DATE DEFAULT NULL,
    DiaChi NVARCHAR(100) DEFAULT NULL,
    Sdt VARCHAR(30) DEFAULT NULL,
    MatKhau VARCHAR(MAX) DEFAULT NULL,
    IdCV INT,
    NgayTao DATE DEFAULT NULL,
    NgaySua DATE DEFAULT NULL,
    TrangThai INT DEFAULT 0,
--     TongTienMuaHang float default 0
)
--Hóa đơn trả
CREATE TABLE HoaDonTra(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    IdHD INT,
    IdND INT,
    NgayTao DATE DEFAULT NULL,
    NgaySua DATE DEFAULT NULL,
    TrangThai INT DEFAULT 0,
)

-- hoa don tra chi tiet
create table HoaDonTraCT(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    IdHDT INT,
    IDSachCT INT,
    SoLuongTra int,
    DonGia float
)
--Hóa đơn nhập
CREATE TABLE HoaDonNhap(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Ma VARCHAR(20) UNIQUE,
    IdNCC INT,
    IdND INT,
    NgayTao DATE DEFAULT NULL,
    NgaySua DATE DEFAULT NULL,
    TrangThai INT DEFAULT 0,
)
--Hóa đơn nhập chi tiết
CREATE TABLE HoaDonNhapCT(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    IdHDN INT,
    IdSachCT INT,
    DonGia DECIMAL(20,0) DEFAULT 0,
    SoLuong INT,
)
--TẠO QUAN HỆ GIỮA CÁC BẢNG
--SachChiTiet - Sach
ALTER TABLE SachCT ADD FOREIGN KEY (IdSach) REFERENCES Sach(Id)
--SachChiTiet - TheLoai
ALTER TABLE SachCT ADD FOREIGN KEY (IdTheLoai) REFERENCES TheLoai(Id)
--SachChiTiet - NhaXuatBan
ALTER TABLE SachCT ADD FOREIGN KEY (IdNXB) REFERENCES NXB(Id)
--SachChiTiet - NhaCungCap
ALTER TABLE SachCT ADD FOREIGN KEY (IdNCC) REFERENCES NCC(Id)
-- HoaDonNhap - NhaCungCap
ALTER TABLE HoaDonNhap ADD FOREIGN KEY (IdNCC) REFERENCES NCC(Id)
-- HoaDonNhap - nguoidung
ALTER TABLE HoaDonNhap ADD FOREIGN KEY (IdND) REFERENCES NguoiDung(Id)
-- HoaDonNhapChiTiet - HoaDonNhap
ALTER TABLE HoaDonNhapCT ADD FOREIGN KEY(IdHDN) REFERENCES HoaDonNhap(Id)
-- HoaDonNhapChiTiet - sachChiTiet
ALTER TABLE HoaDonNhapCT ADD FOREIGN KEY(IdSachCT) REFERENCES SachCT(Id)
-- NguoiDung - ChucVu
ALTER TABLE NguoiDung ADD FOREIGN KEY(IdCV) REFERENCES ChucVu(Id)
-- ChiTietHoaDon - SachChiTiet
ALTER TABLE HoaDonCT ADD FOREIGN KEY(IdSachCT) REFERENCES SachCT(Id)
-- ChiTietHoaDon - HoaDonBan
ALTER TABLE HoaDonCT ADD FOREIGN KEY(IdHD) REFERENCES HoaDon(Id)

-- HoaDonBan - NguoiDung
ALTER TABLE HoaDon ADD FOREIGN KEY(IDND) REFERENCES NguoiDung(Id)
-- HoaDonBan - KhachHang
ALTER TABLE HoaDon ADD FOREIGN KEY(IdKH) REFERENCES KhachHang(Id)
-- HoaDonTra - HoaDon
ALTER TABLE HoaDonTra ADD FOREIGN KEY(IdHD) REFERENCES HoaDon(Id)
-- HoaDonTra - Nguoidung
ALTER TABLE HoaDonTra ADD FOREIGN KEY(IdND) REFERENCES NguoiDung(Id)
-- HoaDonTraCT - hoadontra
alter table HoaDonTraCT add foreign key(IdHDT) references HoaDonTra(Id)

--Thêm dữ liệu cho Sách
insert into Sach(ma,ten,ngaytao,ngaysua) values 
		('S1',N'Tôi yêu em','2002-11-11','2002-11-11'),
		('S2',N'Bứt phá hóa học','2002-11-11','2002-11-11'),
		('S3',N'Đồ thị hàm số','2002-11-11','2002-11-11'),
		('S4',N'Nàng thơ','2002-11-11','2002-11-11'),
		('S5',N'Toán cao cấp','2002-11-11','2002-11-11')
-- Thêm dữ liệu cho Thẻ Loại
insert into TheLoai(ma,ten,ngaytao,ngaysua) values 
		('TL1',N'Văn Học','2002-11-11','2002-11-11'),
		('TL2',N'Hóa Học','2002-11-11','2002-11-11'),
		('TL3',N'Toán','2002-11-11','2002-11-11'),
		('TL4',N'Vật lý','2002-11-11','2002-11-11'),
		('TL5',N'Địa lý','2002-11-11','2002-11-11')
-- Thêm dữ liệu cho NCC
insert into NCC(ma,ten,ngaytao,ngaysua) values 
		('NCC1',N'Nhà cung 1','2002-11-11','2002-11-11'),
		('NCC2',N'Nhà cung 2','2002-11-11','2002-11-11'),
		('NCC3',N'Nhà cung 3','2002-11-11','2002-11-11'),
		('NCC4',N'Nhà cung 4','2002-11-11','2002-11-11'),
		('NCC5',N'Nhà cung 5','2002-11-11','2002-11-11')
-- Thêm dữ liệu cho NXB
insert into NXB(ma,ten,ngaytao,ngaysua) values 
		('NXB1',N'Đại học Bách Khoa HN','2002-11-11','2002-11-11'),
		('NXB2',N'Đại học Sư Phạm HN','2002-11-11','2002-11-11'),
		('NXB3',N'Báo tuổi trẻ','2002-11-11','2002-11-11'),
		('NXB4',N'Báo lá cải','2002-11-11','2002-11-11'),
		('NXB5',N'Báo tiền phong','2002-11-11','2002-11-11')

