  CREATE DATABASE DUAN_1_GROUP1_
GO
    USE DUAN_1_GROUP1_
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
    --IdNCC INT,
    MoTa NVARCHAR(500) DEFAULT NULL,
    SoLuongTon INT,
    GiaNhap DECIMAL(20,0) DEFAULT 0,
    GiaBan DECIMAL(20,0) DEFAULT 0,
    NgayTao DATE DEFAULT NULL,
    NgaySua DATE DEFAULT NULL,
    TrangThai INT DEFAULT 0,
    TacGia nvarchar(50)
    --soluongdaban ra
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
    IdNguoiTao INT,
	IdNguoiThanhToan INT,
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
--ALTER TABLE SachCT ADD FOREIGN KEY (IdNCC) REFERENCES NCC(Id)
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

-- HoaDonBan - NguoiDung(NhanVien tao)
ALTER TABLE HoaDon ADD FOREIGN KEY(IDNguoiTao) REFERENCES NguoiDung(Id)
--HoaDonBan - NguoiThanhToan (NV thanh toan)
ALTER TABLE HoaDon ADD FOREIGN KEY(IDNguoiThanhToan) REFERENCES NguoiDung(Id)
-- HoaDonBan - KhachHang
ALTER TABLE HoaDon ADD FOREIGN KEY(IdKH) REFERENCES KhachHang(Id)
-- HoaDonTra - HoaDon
ALTER TABLE HoaDonTra ADD FOREIGN KEY(IdHD) REFERENCES HoaDon(Id)
-- HoaDonTra - Nguoidung
ALTER TABLE HoaDonTra ADD FOREIGN KEY(IdND) REFERENCES NguoiDung(Id)
-- HoaDonTraCT - hoadontra
alter table HoaDonTraCT add foreign key(IdHDT) references HoaDonTra(Id)

--Thêm dữ liệu cho Sách
INSERT INTO SACH(MA,TEN,NGAYTAO,NGAYSUA) VALUES 
		('S1',N'Tiệm sách cơn mưa','2013-11-11','2013-11-11'),
		('S2',N'Đáp án của thanh xuân','2002-11-11','2002-11-11'),
		('S3',N'Kẻ truy sát','2002-11-11','2002-11-11'),
		('S4',N'Bán hàng bằng trái tim','2014-12-11','2014-12-11'),
		('S5',N'Họ đã khởi nghiệp thế nào','2002-11-11','2002-11-11'),
		('S6',N'Nang đoạn kim cương','2002-11-11','2002-11-11'),
		('S7',N'Những chú chó bán hàng','2002-11-11','2002-11-11'),
		('S8',N'Biến tầm nhìn thành hành động','2002-11-11','2002-11-11'),
		('S9',N'Sống tối giản','2002-11-11','2002-11-11'),
		('S10',N'Việt Nam thời dựng nước','2007-11-11','2007-11-11'),
		('S11',N'Việt Nam sử lược','2002-11-11','2002-11-11'),
		('S12',N'Việt sử và những câu chuyện thú vị','2012-11-11','2012-11-11'),
		('S13',N'Bác Hồ ở Tân Trào','2002-11-11','2002-11-11'),
		('S14',N'25 tướng lĩnh Việt Nam','2002-11-11','2002-11-11'),
		('S15',N'Bách khoa lịch sử thế giới','2002-11-11','2002-11-11'),
		('S16',N'Ung thư hiểu để chữa lành','2002-11-11','2002-11-11'),
		('S17',N'Đừng chết bởi canxi','2002-11-11','2002-11-11'),
		('S18',N'Ma Y Thần tướng','2020-11-11','2020-11-11'),
		('S19',N'Hệ miễn dịch-Kiệt tác của sự sống','2015-11-11','2015-11-11')

-- Thêm dữ liệu cho Thể Loại
insert into TheLoai(ma,ten,ngaytao,ngaysua) values 
		('TL1',N'Văn học','2017-11-11','2017-11-11'),
		('TL2',N'Kinh tế','2002-11-11','2002-11-11'),
		('TL3',N'Kỹ năng sống','2002-11-11','2002-11-11'),
		('TL4',N'Lịch sử','2002-11-11','2020-11-11'),
		('TL5',N'Y học','2002-11-11','2002-11-11')
-- Thêm dữ liệu cho NCC
--insert into NCC(ma,ten,ngaytao,ngaysua) values 
--		('NCC1',N'Công Ty Cổ Phần Dịch Vụ Xuất Bản Giáo Dục Hà Nội','2002-11-11','2002-11-11'),
--		('NCC2',N'Công Ty TNHH Đăng Nguyên','2002-11-11','2002-11-11'),
--		('NCC4',N'Nhà cung 4','2002-11-11','2002-11-11'),
--		('NCC5',N'Nhà cung 5','2002-11-11','2002-11-11')
-- Thêm dữ liệu cho NXB
insert into nxb(ma,ten,ngaytao,ngaysua) values 
		('NXB1',N'Nhà xuất bản Lao Động','2002-11-11','2002-11-11'),
		('NXB2',N'Nhà xuất bản Tổng hợp TP Hồ Chí Minh','2002-11-11','2002-11-11'),
		('NXB3',N'Nhà xuất bản Hồng Đức','2002-11-11','2002-11-11'),
		('NXB4',N'Nhà xuất bản Phụ Nữ','2002-11-11','2002-11-11'),
		('NXB5',N'Nhà xuất bản Trẻ','2002-11-11','2002-11-11'),
		('NXB6',N'Nhà xuất bản Hà Nội','2002-11-11','2002-11-11'),
		('NXB7',N'Nhà xuất bản Công Thương','2009-11-11','2002-11-11')

insert into ChucVu(ma, ten) values ('NV', N'Nhân Viên'),
                                   ('QL', N'Quản Lý')