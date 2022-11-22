/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package edu.poly.duan1.view;

import edu.poly.duan1.model.NCC;
import edu.poly.duan1.model.NXB;
import edu.poly.duan1.model.Sach;
import edu.poly.duan1.model.SachCT;
import edu.poly.duan1.model.TheLoai;
import edu.poly.duan1.services.NCCService;
import edu.poly.duan1.services.NXBService;
import edu.poly.duan1.services.SachCTService;
import edu.poly.duan1.services.SachServices;
import edu.poly.duan1.services.TheLoaiService;
import edu.poly.duan1.services.impl.NCCServiceImpl;
import edu.poly.duan1.services.impl.NXBServiceImpl;
import edu.poly.duan1.services.impl.SachCTServiceImpl;
import edu.poly.duan1.services.impl.SachServicesImpl;
import edu.poly.duan1.services.impl.TheLoaiServiceImpl;
import edu.poly.duan1.ultis.helper;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AnhTiTan
 */
public class Panel_SachCT extends javax.swing.JPanel {

    /**
     * Creates new form Panel_SachCT
     */
    private SachCTService sachCTService = new SachCTServiceImpl();
    private SachServices sachServices = new SachServicesImpl();
    private TheLoaiService theLoaiService = new TheLoaiServiceImpl();
    private NXBService nXBService = new NXBServiceImpl();
    private NCCService nCCService = new NCCServiceImpl();
    private DefaultComboBoxModel<Sach> dcbmSach;
    private DefaultComboBoxModel<TheLoai> dcbmTheLoai;
    private DefaultComboBoxModel<NXB> dcbmNXB;
    private DefaultComboBoxModel<NCC> dcbmNCC;
    private DefaultTableModel tblModel;
    private helper helper = new helper();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public Panel_SachCT() {
        initComponents();
        dcbmSach = new DefaultComboBoxModel<>();
        dcbmTheLoai = new DefaultComboBoxModel<>();
        dcbmNXB = new DefaultComboBoxModel<>();
        dcbmNCC = new DefaultComboBoxModel<>();
        loadDataTable();
        loadCbMaSach();
        loadCbMaTheLoai();
        loadCbMaNXB();
        loadCbMaMCC();
//        setLocationRelativeTo(null);
        tblSachCT.setRowSelectionInterval(0, 0);
        loadText();
    }

    private void loadDataTable() {
        int i = 1;
        tblModel = (DefaultTableModel) tblSachCT.getModel();
        tblModel.setColumnIdentifiers(new String[]{"STT", "Tên Sách", "Thể Loại", "NXB", "NCC", "Tác Giả", "Mô Tả", "SLT", "Giá Nhập", "Giá Bán", "Ngày Tạo", "Ngày Sửa", "Trạng Thái"});
        tblModel.setRowCount(0);
        for (SachCT x : sachCTService.getAll()) {
            tblModel.addRow(new Object[]{
                i++,
                x.getSach().getTen(),
                x.getTheLoai().getTen(),
                x.getNXB().getTen(),
                x.getNCC().getTen(),
                x.getTacGia(),
                x.getMoTa(),
                x.getSoLuongTon(),
                x.getGiaNhap(),
                x.getGiaBan(),
                sdf.format(x.getNgayTao()),
                sdf.format(x.getNgaySua()),
                x.getTrangThai() == 1 ? "Đang Kinh Doanh" : "Ngừng Kinh Doanh"
            });
        }
    }

    // load data Cb
    private void loadCbMaSach() {
        dcbmSach = (DefaultComboBoxModel) cbSach.getModel();
        dcbmSach.removeAllElements();
        for (Sach x : sachServices.getAll()) {
            dcbmSach.addElement(x);
        }
    }

    private void loadCbMaTheLoai() {
        dcbmTheLoai = (DefaultComboBoxModel) cbTheLoai.getModel();
        dcbmTheLoai.removeAllElements();
        for (TheLoai x : theLoaiService.getAll()) {
            dcbmTheLoai.addElement(x);
        }
    }

    private void loadCbMaNXB() {
        dcbmNXB = (DefaultComboBoxModel) cbNXB.getModel();
        dcbmNXB.removeAllElements();
        for (NXB x : nXBService.getAll()) {
            dcbmNXB.addElement(x);
        }
    }

    private void loadCbMaMCC() {
        dcbmNCC = (DefaultComboBoxModel) cbNCC.getModel();
        dcbmNCC.removeAllElements();
        for (NCC x : nCCService.getAll()) {
            dcbmNCC.addElement(x);
        }
    }

    private void add() {
        SachCT sct = new SachCT();
        Sach sach = (Sach) cbSach.getSelectedItem();
        TheLoai tl = (TheLoai) cbTheLoai.getSelectedItem();
        NXB nxb = (NXB) cbNXB.getSelectedItem();
        NCC ncc = (NCC) cbNCC.getSelectedItem();
        sct.setSach(sach);
        sct.setTheLoai(tl);
        sct.setNXB(nxb);
        sct.setNCC(ncc);
        sct.setMoTa(txtMoTa.getText());
        sct.setTacGia(txtTacGia.getText());
        sct.setSoLuongTon((int) spSLT.getValue());
        sct.setGiaNhap(helper.convertToDecimal(txtGiaNhap, "Giá nhập không hợp lệ"));
        sct.setGiaBan(helper.convertToDecimal(txtGiaBan, "Giá nhập không hợp lệ"));
        sct.setNgayTao(java.sql.Date.valueOf(LocalDate.now()));
        sct.setNgaySua(java.sql.Date.valueOf(LocalDate.now()));
        if (sachCTService.saveOrUpdate(sct)) {
            helper.alert(this, "thêm oke");
        } else {
            helper.error(this, "thêm thất bại");
        }
        loadDataTable();
    }

    private void update() {
        int index = tblSachCT.getSelectedRow();
        SachCT sct = sachCTService.getAll().get(index);
        Sach sach = (Sach) cbSach.getSelectedItem();
        TheLoai tl = (TheLoai) cbTheLoai.getSelectedItem();
        NXB nxb = (NXB) cbNXB.getSelectedItem();
        NCC ncc = (NCC) cbNCC.getSelectedItem();
        sct.setSach(sach);
        sct.setTheLoai(tl);
        sct.setNXB(nxb);
        sct.setNCC(ncc);
        sct.setMoTa(txtMoTa.getText());
        sct.setTacGia(txtTacGia.getText());
        sct.setSoLuongTon((int) spSLT.getValue());
        sct.setGiaNhap(helper.convertToDecimal(txtGiaNhap, "Giá nhập không hợp lệ"));
        sct.setGiaBan(helper.convertToDecimal(txtGiaBan, "Giá nhập không hợp lệ"));
        sct.setNgaySua(java.sql.Date.valueOf(LocalDate.now()));
        if (sachCTService.saveOrUpdate(sct)) {
            helper.alert(this, "sửa oke");
        } else {
            helper.error(this, "sửa thất bại thất bại");
        }
        loadDataTable();
    }

    private void delete() {
        int index = tblSachCT.getSelectedRow();
        SachCT sct = sachCTService.getAll().get(index);
        if (index == -1) {
            helper.error(this, "Vui Lòng chọn đòng cần xóa");

        } else {
            if (sachCTService.delete(sct)) {
                helper.alert(this, "xóa thành công");
            } else {
                helper.error(this, "xóa thất bại");
            }
        }
        loadDataTable();
    }

    private void loadText() {
        int choice = tblSachCT.getSelectedRow();
        SachCT sct = sachCTService.getAll().get(choice);
        dcbmSach.setSelectedItem(sct.getSach());
        dcbmNCC.setSelectedItem(sct.getNCC());
        dcbmTheLoai.setSelectedItem(sct.getTheLoai());
        dcbmNXB.setSelectedItem(sct.getNXB());
        txtMoTa.setText(sct.getMoTa());
        txtGiaNhap.setText(sct.getGiaNhap() + "");
        txtGiaBan.setText(sct.getGiaBan() + "");
        spSLT.setValue(sct.getSoLuongTon());
        txtTacGia.setText(sct.getTacGia());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        JLabel5 = new javax.swing.JLabel();
        JLabel6 = new javax.swing.JLabel();
        JLabel7 = new javax.swing.JLabel();
        JLabel8 = new javax.swing.JLabel();
        JLabel9 = new javax.swing.JLabel();
        JLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSachCT = new javax.swing.JTable();
        txtTacGia = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        txtGiaNhap = new javax.swing.JTextField();
        spSLT = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        cbNCC = new javax.swing.JComboBox<>();
        cbNXB = new javax.swing.JComboBox<>();
        cbTheLoai = new javax.swing.JComboBox<>();
        cbSach = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();

        jLabel1.setText("Chi Tiết Sách");

        jLabel2.setText("Sách");

        jLabel3.setText("Thể Loại");

        jLabel4.setText("NXB");

        JLabel5.setText("NCC");

        JLabel6.setText("Mô tả");

        JLabel7.setText("Số lượng tồn");

        JLabel8.setText("Giá Nhập");

        JLabel9.setText("Giá Bán");

        JLabel10.setText("Tác Giả");

        tblSachCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblSachCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSachCTMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSachCT);

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JLabel10)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JLabel7)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JLabel8)
                                    .addComponent(JLabel9))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbSach, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(spSLT, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSua)
                            .addComponent(btnThem)
                            .addComponent(btnXoa))
                        .addGap(0, 454, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnThem))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbNXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JLabel6)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spSLT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLabel8)
                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLabel10)
                            .addComponent(txtTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnSua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoa)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSachCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSachCTMouseClicked
        loadText();
    }//GEN-LAST:event_tblSachCTMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        add();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabel10;
    private javax.swing.JLabel JLabel5;
    private javax.swing.JLabel JLabel6;
    private javax.swing.JLabel JLabel7;
    private javax.swing.JLabel JLabel8;
    private javax.swing.JLabel JLabel9;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbNCC;
    private javax.swing.JComboBox<String> cbNXB;
    private javax.swing.JComboBox<String> cbSach;
    private javax.swing.JComboBox<String> cbTheLoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner spSLT;
    private javax.swing.JTable tblSachCT;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtTacGia;
    // End of variables declaration//GEN-END:variables
}
