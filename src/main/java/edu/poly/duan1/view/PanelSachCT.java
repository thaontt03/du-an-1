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
import edu.poly.duan1.swing.table.Table1;
import edu.poly.duan1.ultis.helper;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AnhTiTan
 */
public class PanelSachCT extends javax.swing.JPanel {

    /**
     * Creates new form PanelSachCT
     */
        private SachCTService sachCTService = new SachCTServiceImpl();
    private SachServices sachServices = new SachServicesImpl();
    private TheLoaiService theLoaiService = new TheLoaiServiceImpl();
    private NXBService nXBService = new NXBServiceImpl();
    private NCCService nCCService = new NCCServiceImpl();
    private DefaultComboBoxModel<Sach> dcbmSach;
    private DefaultComboBoxModel<TheLoai> dcbmTheLoai;
    private DefaultComboBoxModel<NXB> dcbmNXB;
    private DefaultTableModel tblModel;
    private helper helper = new helper();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    int check;
    public PanelSachCT() {
        initComponents();
        Table1.apply(jScrollPane1, Table1.TableType.DEFAULT);
                dcbmSach = new DefaultComboBoxModel<>();
        dcbmTheLoai = new DefaultComboBoxModel<>();
        dcbmNXB = new DefaultComboBoxModel<>();
        loadDataTable();
        loadCbMaSach();
        loadCbMaTheLoai();
        loadCbMaNXB();
//        tblSachCT.setRowSelectionInterval(0, 0);
//        loadText();
        check = -1;
    }
  private void loadDataTable() {
        int i = 1;
        tblModel = (DefaultTableModel) tblSachCT.getModel();
        tblModel.setColumnIdentifiers(new String[]{"STT", "Tên Sách", "Thể Loại", "NXB", "Tác Giả", "Mô Tả", "SLT", "Giá Nhập", "Giá Bán", "Ngày Tạo", "Ngày Sửa", "Trạng Thái"});
        tblModel.setRowCount(0);
        for (SachCT x : sachCTService.getAll()) {
            tblModel.addRow(new Object[]{
                i++,
                x.getSach().getTen(),
                x.getTheLoai().getTen(),
                x.getNXB().getTen(),               
                x.getTacGia(),
                x.getMoTa(),
                x.getSoLuongTon(),
                x.getGiaNhap(),
                x.getGiaBan(),
                sdf.format(x.getNgayTao()),
                sdf.format(x.getNgaySua()),
                x.getTrangThai() == 1 ? "Hết" : "Còn"
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

 

    private void add() {
        SachCT sct = new SachCT();
        Sach sach = (Sach) cbSach.getSelectedItem();
        TheLoai tl = (TheLoai) cbTheLoai.getSelectedItem();
        NXB nxb = (NXB) cbNXB.getSelectedItem();
        sct.setSach(sach);
        sct.setTheLoai(tl);
        sct.setNXB(nxb);
//        sct.setNCC(ncc);
        sct.setMoTa(txtMoTa.getText());
        sct.setTacGia(txtTacGia.getText());
        sct.setSoLuongTon((int) spSLT.getValue());
        BigDecimal giaNhap = helper.convertToDecimal(txtGiaNhap, "Giá nhập không hợp lệ");
        if (giaNhap == null) {
            return;
        }
        BigDecimal giaBan = helper.convertToDecimal(txtGiaBan, "Giá nhập không hợp lệ");
        if (giaNhap == null) {
            return;
        }
        sct.setGiaNhap(giaNhap);
        sct.setGiaBan(giaBan);
        sct.setNgayTao(java.sql.Date.valueOf(LocalDate.now()));
        sct.setNgaySua(java.sql.Date.valueOf(LocalDate.now()));
        if (sachCTService.saveOrUpdate(sct)) {
            helper.alert(this, "Thêm thành công");
            reset();
        } else {
            helper.error(this, "Thêm thất bại");
        }
        loadDataTable();
    }

    void reset() {
        txtGiaBan.setText("");
        txtGiaNhap.setText("");
        txtMoTa.setText("");
        txtTacGia.setText("");
        check = -1;
    }

    private void update() {
        int index = tblSachCT.getSelectedRow();
        SachCT sct = sachCTService.getAll().get(index);
        Sach sach = (Sach) cbSach.getSelectedItem();
        TheLoai tl = (TheLoai) cbTheLoai.getSelectedItem();
        NXB nxb = (NXB) cbNXB.getSelectedItem();
        sct.setSach(sach);
        sct.setTheLoai(tl);
        sct.setNXB(nxb);
        sct.setMoTa(txtMoTa.getText());
        sct.setTacGia(txtTacGia.getText());

        int soluongton = (int) spSLT.getValue();
        sct.setSoLuongTon(soluongton);
        sct.setGiaNhap(helper.convertToDecimal(txtGiaNhap, "Giá nhập không hợp lệ"));
        sct.setGiaBan(helper.convertToDecimal(txtGiaBan, "Giá nhập không hợp lệ"));
        sct.setNgaySua(java.sql.Date.valueOf(LocalDate.now()));
        if (soluongton > 0) {
            sct.setTrangThai(0);
        } else if (soluongton <= 0) {
            sct.setTrangThai(1);
        }
        if (sachCTService.saveOrUpdate(sct)) {
            helper.alert(this, "Đã cập nhật");
            reset();
        } else {
            helper.error(this, "Cập nhật thất bại");
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
                reset();
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
//        dcbmNCC.setSelectedItem(sct.getNCC());
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

        tableScrollButton2 = new edu.poly.duan1.swing.table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSachCT = new javax.swing.JTable();
        cbSach = new edu.poly.duan1.swing.combobox.Combobox();
        cbTheLoai = new edu.poly.duan1.swing.combobox.Combobox();
        cbNXB = new edu.poly.duan1.swing.combobox.Combobox();
        textAreaScroll1 = new edu.poly.duan1.swing.textarea.TextAreaScroll();
        txtMoTa = new edu.poly.duan1.swing.textarea.TextArea();
        jLabel1 = new javax.swing.JLabel();
        spSLT = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        txtGiaNhap = new edu.poly.duan1.swing.textfield.TextField();
        txtGiaBan = new edu.poly.duan1.swing.textfield.TextField();
        txtTacGia = new edu.poly.duan1.swing.textfield.TextField();
        btnThem = new edu.poly.duan1.swing.button.Button();
        btnSua = new edu.poly.duan1.swing.button.Button();
        btnXoa = new edu.poly.duan1.swing.button.Button();

        setBackground(new java.awt.Color(255, 255, 255));

        tblSachCT.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSachCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSachCTMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblSachCT);

        tableScrollButton2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        cbSach.setLabeText("Sách");

        cbTheLoai.setLabeText("Thể Loại");

        cbNXB.setLabeText("NXB");

        textAreaScroll1.setLabelText("Mô Tả");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        textAreaScroll1.setViewportView(txtMoTa);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(3, 155, 216));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Chi Tiết Sách");

        jLabel2.setText("Số lượng tồn");

        txtGiaNhap.setLabelText("Giá Nhập");

        txtGiaBan.setLabelText("Giá Bán");

        txtTacGia.setLabelText("Tác giả");

        btnThem.setBackground(new java.awt.Color(3, 155, 216));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(3, 155, 216));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(3, 155, 216));
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
            .addComponent(tableScrollButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 968, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbTheLoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbNXB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textAreaScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
                        .addGap(83, 83, 83)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTacGia, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spSLT, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(110, 110, 110))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSua, btnThem, btnXoa});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbNXB, cbSach, cbTheLoai, textAreaScroll1, txtGiaBan, txtGiaNhap, txtTacGia});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spSLT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(cbSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(cbNXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textAreaScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)))
                .addComponent(tableScrollButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbNXB, cbSach, cbTheLoai, textAreaScroll1, txtGiaBan, txtGiaNhap, txtTacGia});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnSua, btnThem, btnXoa});

    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
              add();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
                if (check == -1) {
            JOptionPane.showMessageDialog(this, "Chọn sách cần cập nhật");
        } else {
            update();
        } 
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
            if (check == -1) {
            JOptionPane.showMessageDialog(this, "Chọn sách cần xóa");
        } else {
            delete();
        } 
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblSachCTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSachCTMousePressed
       loadText();
        check = tblSachCT.getSelectedRow();
    }//GEN-LAST:event_tblSachCTMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private edu.poly.duan1.swing.button.Button btnSua;
    private edu.poly.duan1.swing.button.Button btnThem;
    private edu.poly.duan1.swing.button.Button btnXoa;
    private edu.poly.duan1.swing.combobox.Combobox cbNXB;
    private edu.poly.duan1.swing.combobox.Combobox cbSach;
    private edu.poly.duan1.swing.combobox.Combobox cbTheLoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spSLT;
    private edu.poly.duan1.swing.table.TableScrollButton tableScrollButton2;
    private javax.swing.JTable tblSachCT;
    private edu.poly.duan1.swing.textarea.TextAreaScroll textAreaScroll1;
    private edu.poly.duan1.swing.textfield.TextField txtGiaBan;
    private edu.poly.duan1.swing.textfield.TextField txtGiaNhap;
    private edu.poly.duan1.swing.textarea.TextArea txtMoTa;
    private edu.poly.duan1.swing.textfield.TextField txtTacGia;
    // End of variables declaration//GEN-END:variables
}
