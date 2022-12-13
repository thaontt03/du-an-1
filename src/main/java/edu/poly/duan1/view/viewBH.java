/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edu.poly.duan1.view;

import edu.poly.duan1.model.HoaDon;
import edu.poly.duan1.model.HoaDonCT;
import edu.poly.duan1.model.NguoiDung;
import edu.poly.duan1.model.SachCT;
import edu.poly.duan1.services.HoaDonCTService;
import edu.poly.duan1.services.HoaDonService;
import edu.poly.duan1.services.SachCTService;
import edu.poly.duan1.services.impl.HoaDonCTServiceImpl;
import edu.poly.duan1.services.impl.HoaDonServiceImpl;
import edu.poly.duan1.services.impl.SachCTServiceImpl;
import edu.poly.duan1.swing.table.Table1;
import edu.poly.duan1.ultis.helper;
import edu.poly.main.Main;
import java.awt.Image;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AnhTiTan
 */
public class viewBH extends javax.swing.JFrame {

    /**
     * Creates new form viewBH
     */
    private DefaultTableModel tblModel;
    private SachCTService sachCTService = new SachCTServiceImpl();
    private HoaDonService hoaDonService = new HoaDonServiceImpl();
    private HoaDonCTService hoaDonCTService = new HoaDonCTServiceImpl();
    private helper helper = new helper();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    private NguoiDung nguoiDung = new NguoiDung();

    public viewBH(NguoiDung nd) {
        initComponents();
        Table1.apply(jScrollPane1, Table1.TableType.DEFAULT);
        Table1.apply(jScrollPane2, Table1.TableType.DEFAULT);
        Table1.apply(jScrollPane3, Table1.TableType.DEFAULT);
        setExtendedState(MAXIMIZED_BOTH);
        LoadData();
        List<HoaDonCT> listGH = new ArrayList<>();
        GroupTT();
        rdTatCa.setSelected(true);
        setTitle("Bán Hàng");
        this.nguoiDung = nd;
        Image icon = Toolkit.getDefaultToolkit().getImage("images/logo.png");
        this.setIconImage(icon);

    }

    private void loadDataTaSach(List<SachCT> list) {
        tblModel = (DefaultTableModel) tblSanPham.getModel();
        tblModel.setColumnIdentifiers(new String[]{"STT", "Mã", "Tên sách", "SLT", "Giá bán", "Mô tả"});
        tblModel.setRowCount(0);
        int i = 1;
        for (SachCT x : list) {
            tblModel.addRow(new Object[]{
                i++,
                x.getSach().getMa(),
                x.getSach().getTen(),
                x.getSoLuongTon(),
                x.getGiaBan(),
                x.getMoTa()
            });
        }
    }

    private void loadDataHoaDon(List<HoaDon> list) {
        tblModel = (DefaultTableModel) tblHD.getModel();
        tblModel.setColumnIdentifiers(new String[]{"STT", "Mã HD", "Người Tạo", "Ngày tạo", "Trạng thái"});
        tblModel.setRowCount(0);
        int i = 1;
        for (HoaDon x : list) {
            tblModel.addRow(new Object[]{
                i++,
                x.getMa(),
                x.getNguoiDungTao().getHoTen(),
                sdf.format(x.getNgayTao()),
                x.getTrangThai() == 1 ? "Đã thanh toán" : "Chưa thanh toán"
            });
        }
    }

    private void loadDataGH(List<HoaDonCT> listGH) {
        try {
            tblModel = (DefaultTableModel) tblGioHang.getModel();
            tblModel.setColumnIdentifiers(new String[]{"STT", "Mã HD", "Tên sách", "Số lượng", "Giá bán", "Thành tiền"});
            tblModel.setRowCount(0);
            int i = 1;
            for (HoaDonCT x : listGH) {
                tblModel.addRow(new Object[]{
                    i++,
                    x.getHoaDon().getMa(),
                    x.getSachCT().getSach().getTen(),
                    x.getSoLuong(),
                    x.getDonGia(),
                    x.getSoLuong() * x.getDonGia(),
                    x.ThanhTien(x.getSoLuong(), x.getDonGia())});
            }
        } catch (Exception e) {
        }
    }
    ButtonGroup buttonGroup1 = new ButtonGroup();

    private void GroupTT() {
        buttonGroup1.add(rdChuaTT);
        buttonGroup1.add(rdDaTT);
        buttonGroup1.add(rdTatCa);
    }

    private void loadDataGH() {
        tblModel = (DefaultTableModel) tblGioHang.getModel();
        tblModel.setColumnIdentifiers(new String[]{"STT", "Mã HD", "Tên sách", "Số lượng", "Giá bán", "Thành tiền"});
        tblModel.setRowCount(0);

    }

    private void LoadData() {
        loadDataTaSach(sachCTService.getAll());
        loadDataHoaDon(hoaDonService.getAll());
        loadDataGH();
    }

    private void tongTien() {
        DecimalFormat x = new DecimalFormat("###,###,###");
        Double tongTien = 0.0;
        // Tổng tiền
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            try {
                tongTien = tongTien + Double.parseDouble(tblGioHang.getValueAt(i, 5).toString());
            } catch (Exception e) {
            }
        }
//        txtTongTien.setText(x.format(tongTien));
        txtTongTien.setText(tongTien + "");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtTimKiem = new edu.poly.duan1.swing.textfield.TextField();
        tableScrollButton3 = new edu.poly.duan1.swing.table.TableScrollButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        tableScrollButton2 = new edu.poly.duan1.swing.table.TableScrollButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnGoHome = new edu.poly.duan1.swing.button.Button();
        jPanel2 = new javax.swing.JPanel();
        rdChuaTT = new edu.poly.duan1.swing.radio_button.RadioButtonCustom();
        rdDaTT = new edu.poly.duan1.swing.radio_button.RadioButtonCustom();
        rdTatCa = new edu.poly.duan1.swing.radio_button.RadioButtonCustom();
        btnTaoHoaDon = new edu.poly.duan1.swing.button.Button();
        tableScrollButton1 = new edu.poly.duan1.swing.table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHD = new javax.swing.JTable();
        pnHDTT = new javax.swing.JPanel();
        txtMaHD = new edu.poly.duan1.swing.textfield.TextField();
        txtNgayTao = new edu.poly.duan1.swing.textfield.TextField();
        txtTongTien = new edu.poly.duan1.swing.textfield.TextField();
        txtTienKhachDua = new edu.poly.duan1.swing.textfield.TextField();
        txtTienThua = new edu.poly.duan1.swing.textfield.TextField();
        btnThanhToan = new edu.poly.duan1.swing.button.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản phẩm"));

        txtTimKiem.setLabelText("Tìm Kiếm");
        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSanPhamMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPham);

        tableScrollButton3.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(tableScrollButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tableScrollButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng"));

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblGioHangMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblGioHang);

        tableScrollButton2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tableScrollButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tableScrollButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnGoHome.setBackground(new java.awt.Color(204, 204, 204));
        btnGoHome.setText("Trở về màn hình chính");
        btnGoHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoHomeActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn"));

        rdChuaTT.setText("Chưa thanh toán");
        rdChuaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdChuaTTActionPerformed(evt);
            }
        });

        rdDaTT.setText("Đã thanh toán");
        rdDaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdDaTTActionPerformed(evt);
            }
        });

        rdTatCa.setText("Tất cả");
        rdTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdTatCaActionPerformed(evt);
            }
        });

        btnTaoHoaDon.setBackground(new java.awt.Color(204, 204, 204));
        btnTaoHoaDon.setText("Tạo Hóa Đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        tblHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblHDMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblHD);

        tableScrollButton1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdChuaTT, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(rdDaTT, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(rdTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdDaTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdChuaTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnHDTT.setBackground(new java.awt.Color(255, 255, 255));
        pnHDTT.setBorder(javax.swing.BorderFactory.createTitledBorder("Thanh Toán"));

        txtMaHD.setLabelText("Mã HD");

        txtNgayTao.setLabelText("Ngày tạo");

        txtTongTien.setLabelText("Tổng tiền");

        txtTienKhachDua.setLabelText("Tiền khách đưa");
        txtTienKhachDua.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienKhachDuaCaretUpdate(evt);
            }
        });

        txtTienThua.setLabelText("Tiền thừa");

        btnThanhToan.setBackground(new java.awt.Color(204, 204, 204));
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnHDTTLayout = new javax.swing.GroupLayout(pnHDTT);
        pnHDTT.setLayout(pnHDTTLayout);
        pnHDTTLayout.setHorizontalGroup(
            pnHDTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHDTTLayout.createSequentialGroup()
                .addGroup(pnHDTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                    .addGroup(pnHDTTLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnHDTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTienKhachDua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnHDTTLayout.setVerticalGroup(
            pnHDTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHDTTLayout.createSequentialGroup()
                .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnGoHome, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnHDTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnHDTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(btnGoHome, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGoHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoHomeActionPerformed
        new Main(nguoiDung).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnGoHomeActionPerformed

    private void rdChuaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdChuaTTActionPerformed
        loadDataHoaDon(hoaDonService.search(0));
        loadDataGH();
        tongTien();
    }//GEN-LAST:event_rdChuaTTActionPerformed

    private void rdDaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdDaTTActionPerformed
        loadDataHoaDon(hoaDonService.search(1));
        loadDataGH();
        tongTien();
    }//GEN-LAST:event_rdDaTTActionPerformed

    private void rdTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdTatCaActionPerformed
        loadDataHoaDon(hoaDonService.getAll());
        loadDataGH();
        tongTien();
    }//GEN-LAST:event_rdTatCaActionPerformed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        taoHoaDon();
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void txtTienKhachDuaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienKhachDuaCaretUpdate
        Double tongTien = 0.0;
        Double tienKhachDua = 0.0;
        Double tienThua = 0.0;
        DecimalFormat x = new DecimalFormat("###,###,###");
        try {
//            tongTien = Double.parseDouble(x.format(txtTongTien.getText()));
            tongTien = Double.parseDouble(txtTongTien.getText().toString());
//            tienKhachDua = Double.parseDouble(x.format(txtTienKhachDua.getText()));
            tienKhachDua = Double.parseDouble(txtTienKhachDua.getText());
            tienThua = tienKhachDua - tongTien;
        } catch (Exception e) {
        }
        if (tienKhachDua - tongTien <= 0.0) {
            //            txtTienThua.setText(0 + "");
            txtTienThua.setText(x.format(tienThua) + " VNĐ");
        } else {
            txtTienThua.setText(x.format(tienThua) + " VNĐ");
        }
    }//GEN-LAST:event_txtTienKhachDuaCaretUpdate
    private void thanhToan() {
        DecimalFormat x = new DecimalFormat("###,###,###");
        Double tongTien = 0.0;
        Double tienKhachDua = 0.0;
        Double tienThua = 0.0;
        try {
            tongTien = Double.parseDouble(txtTongTien.getText().toString());
            tienKhachDua = Double.parseDouble(txtTienKhachDua.getText());
            tienThua = tienKhachDua - tongTien;
        } catch (Exception e) {
        }
        if (tienKhachDua - tongTien <= 0.0) {
//            txtTienThua.setText(0 + "");
            txtTienThua.setText(x.format(tienThua));
        } else {
            txtTienThua.setText(x.format(tienThua));
        }
        int row = tblHD.getSelectedRow();

        HoaDon hd = hoaDonService.getObjbyMa((String) tblHD.getValueAt(row, 1));
        if (hd.getTrangThai() == 1) {
            helper.error(this, "Đơn hàng này đã được thanh toán");
            return;
        }

        if (tienKhachDua - tongTien >= 0) {
            if (helper.confirm(this, "Bạn có muốn thanh toán hóa đơn: ''" + hd.getMa() + "''\n"
                    + "Tổng số tiền là: " + x.format(tongTien) + " VNĐ không?")) {
                hd.setTrangThai(1);
                hd.setNgayThanhToan(java.sql.Date.valueOf(LocalDate.now()));
                hd.setNguoiDungThanhToan(nguoiDung);
                if (hoaDonService.saveOrUpdate(hd)) {

                    helper.alert(this, "Thanh toán thành công");
                    tblModel = (DefaultTableModel) tblGioHang.getModel();
                    tblModel.setRowCount(0);
                    loadDataHoaDon(hoaDonService.getAll());

                } else {
                    helper.error(this, "Thanh toán thất bại");
                }
            }
        } else {
            helper.error(this, "Tiền khách đưa không đủ vui lòng nhập lại số tiền");
        }
    }

    private void taoHoaDon() {
        HoaDon hd = new HoaDon();

        String result;
        hd.setNgayTao(java.sql.Date.valueOf(LocalDate.now()));
        for (int i = 0; i < hoaDonService.getAll().size() + 1; i++) {
            result = "HD" + i;
            if (hoaDonService.getObjbyMa(result) == null) {
                hd.setMa(result);
                hd.setNguoiDungTao(nguoiDung);
                break;
            } else {
                continue;
            }
        }
        if (hoaDonService.saveOrUpdate(hd)) {
            helper.alert(this, "Thêm hoá đơn Thành Công");

            loadDataHoaDon(hoaDonService.getAll());
            tblHD.setRowSelectionInterval(0, 0);
            int row = tblHD.getSelectedRow();
            loadDataGH((List<HoaDonCT>) hoaDonCTService.findNByMa((String) tblHD.getValueAt(row, 1)));
            txtMaHD.setText(hd.getMa());
            txtNgayTao.setText(sdf.format(hd.getNgayTao()) + "");
            tongTien();
        } else {
            helper.error(this, "Thêm hoá đơn mới thất bại");

        }

    }
    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        if (txtMaHD.getText().isEmpty()) {
            helper.error(this, "Vui lòng chọn hóa đơn cần thanh toán");
            return;
        } else {
            thanhToan();
        }

    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void tblHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDMouseClicked


    }//GEN-LAST:event_tblHDMouseClicked

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked

    }//GEN-LAST:event_tblSanPhamMouseClicked
    private void sanphamMousePressed() {

        int row = tblHD.getSelectedRow();
        int rowSP = tblSanPham.getSelectedRow();
        if (row == -1) {
            helper.error(this, "Vui lòng chọn hóa đơn cần chỉnh sửa");
            tblSanPham.clearSelection();
        } else {
            HoaDon hd = hoaDonService.getObjbyMa((String) tblHD.getValueAt(row, 1));
            SachCT sctt = sachCTService.getAll().get(rowSP);
            SachCT sct = new SachCT();
            sct = sachCTService.getObjbyID(sctt.getId());
            HoaDonCT hdct = hoaDonCTService.getObj(hd.getId(), sct.getId());
            if (hd.getTrangThai() == 1) {
                helper.error(this, "Hóa đơn này đã được thanh toán");
                tblSanPham.clearSelection();
                return;
            }
            if (sctt.getSoLuongTon() <= 0) {
                helper.error(this, "Đã hết hàng");
                return;
            }
            String input = JOptionPane.showInputDialog("Vui lòng nhập số lượng");
            try {

                Integer soLuong = Integer.parseInt(input);
                if (soLuong > sctt.getSoLuongTon()) {
                    helper.error(this, "Đã vượt quá số lượng tối đa \n" + "(max:" + sct.getSoLuongTon() + ")");
                    tblSanPham.clearSelection();
                    return;
                } else if (soLuong <= 0) {
                    helper.error(this, "Số lượng phải >0");
                    tblSanPham.clearSelection();
                    return;
                }
                if (hdct != null) {

                    hdct.setSoLuong(hdct.getSoLuong() + soLuong);
                    sct.setSoLuongTon(sct.getSoLuongTon() - soLuong);
                    hdct.setNgayTao(java.sql.Date.valueOf(LocalDate.now()));
                    hdct.setNgaySua(java.sql.Date.valueOf(LocalDate.now()));
                } else {
                    hdct = new HoaDonCT();
                    hdct.setNgaySua(java.sql.Date.valueOf(LocalDate.now()));
                    hdct.setHoaDon(hd);
                    hdct.setSachCT(sct);
                    hdct.setDonGia(sct.getGiaBan().doubleValue());
                    hdct.setSoLuong(hdct.getSoLuong() + soLuong);
                    sct.setSoLuongTon(sct.getSoLuongTon() - soLuong);
                }
                sachCTService.saveOrUpdate(sct);
                hoaDonCTService.saveOrUpdate(hdct);
            } catch (Exception e) {
                helper.error(this, "vui lòng nhập lại");
            }
            loadDataTaSach(sachCTService.getAll());
            loadDataGH((List<HoaDonCT>) hoaDonCTService.findNByMa((String) tblHD.getValueAt(row, 1)));
            tongTien();
        }
    }

    private void tblGioHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMousePressed
        giohang();

    }//GEN-LAST:event_tblGioHangMousePressed

    private void tblHDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDMousePressed
        int row = tblHD.getSelectedRow();
        HoaDon hd = hoaDonService.getObjbyMa((String) tblHD.getValueAt(row, 1));
        loadDataGH((List<HoaDonCT>) hoaDonCTService.findNByMa((String) tblHD.getValueAt(row, 1)));
        txtMaHD.setText(hd.getMa());
        txtNgayTao.setText(sdf.format(hd.getNgayTao()) + "");
        tongTien();
    }//GEN-LAST:event_tblHDMousePressed

    private void tblSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMousePressed
        sanphamMousePressed();
    }//GEN-LAST:event_tblSanPhamMousePressed

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        String ten = txtTimKiem.getText();
        loadDataTaSach(sachCTService.findByTen(ten));
    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void giohang() {
        int rowGH = tblGioHang.getSelectedRow();
        int rowHD = tblHD.getSelectedRow();
        Integer soLuongNhap;
        if (rowHD == -1) {
            helper.error(this, "không được thực hiện");
            return;
        } else {

            HoaDon hd = hoaDonService.getObjbyMa((String) tblHD.getValueAt(rowHD, 1));

            if (hd.getTrangThai() == 1) {
                helper.error(this, "Hóa đơn này đã được thanh toán");
                tblGioHang.clearSelection();
                return;
            } else {
                HoaDonCT hdct = hoaDonCTService.findNByMa(txtMaHD.getText()).get(rowGH);
                SachCT sct = sachCTService.getObjbyID(hdct.getSachCT().getId());
                Integer soLuongCu = hdct.getSoLuong();
                String input = JOptionPane.showInputDialog("Vui lòng nhập số lượng");
                try {

                    soLuongNhap = Integer.parseInt(input);
                    int check = 0;
                    check = (soLuongCu + sct.getSoLuongTon()) - soLuongNhap;
                    if (soLuongNhap < 0) {
                        helper.error(this, "Số lượng phải >=0");
                        tblGioHang.clearSelection();
                        return;
                    }
                    if (soLuongNhap > sct.getSoLuongTon() && check<0) {
                        helper.error(this, "Quá số lượng cho phép");
                        tblGioHang.clearSelection();
                        return;
                    }

                } catch (Exception e) {
                    helper.error(this, "Vui lòng nhập lại");
                    tblGioHang.clearSelection();
                    return;
                }
                if (soLuongNhap == 0) {
                    if (helper.confirm(this, "Bạn có muốn xóa: ''" + hdct.getSachCT().getSach().getTen() + "'' ra khỏi giỏ hàng không?")) {
                        sct.setSoLuongTon(sct.getSoLuongTon() + soLuongCu - soLuongNhap);
                        sachCTService.saveOrUpdate(sct);
                        hoaDonCTService.delete(hdct);
                    }
                } else {

                    sct.setSoLuongTon(sct.getSoLuongTon() + soLuongCu - soLuongNhap);
                    hdct.setNgaySua(java.sql.Date.valueOf(LocalDate.now()));
                    hdct.setSoLuong(soLuongNhap);
                    sachCTService.saveOrUpdate(sct);
                    hoaDonCTService.saveOrUpdate(hdct);

                }
//            tongTien();

            }
            loadDataGH((List<HoaDonCT>) hoaDonCTService.findNByMa(hd.getMa()));
            loadDataTaSach(sachCTService.getAll());
            tongTien();
        }
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(viewBH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(viewBH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(viewBH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(viewBH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new viewBH().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private edu.poly.duan1.swing.button.Button btnGoHome;
    private edu.poly.duan1.swing.button.Button btnTaoHoaDon;
    private edu.poly.duan1.swing.button.Button btnThanhToan;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pnHDTT;
    private edu.poly.duan1.swing.radio_button.RadioButtonCustom rdChuaTT;
    private edu.poly.duan1.swing.radio_button.RadioButtonCustom rdDaTT;
    private edu.poly.duan1.swing.radio_button.RadioButtonCustom rdTatCa;
    private edu.poly.duan1.swing.table.TableScrollButton tableScrollButton1;
    private edu.poly.duan1.swing.table.TableScrollButton tableScrollButton2;
    private edu.poly.duan1.swing.table.TableScrollButton tableScrollButton3;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHD;
    private javax.swing.JTable tblSanPham;
    private edu.poly.duan1.swing.textfield.TextField txtMaHD;
    private edu.poly.duan1.swing.textfield.TextField txtNgayTao;
    private edu.poly.duan1.swing.textfield.TextField txtTienKhachDua;
    private edu.poly.duan1.swing.textfield.TextField txtTienThua;
    private edu.poly.duan1.swing.textfield.TextField txtTimKiem;
    private edu.poly.duan1.swing.textfield.TextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
