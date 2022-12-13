/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package edu.poly.duan1.view;

import edu.poly.duan1.model.NXB;
import edu.poly.duan1.services.NXBService;
import edu.poly.duan1.services.impl.NXBServiceImpl;
import edu.poly.duan1.swing.table.Table1;
import edu.poly.duan1.ultis.helper;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class PanelNXBoke extends javax.swing.JPanel {

    /**
     * Creates new form PanelNXBoke
     */
    private NXBService nxbService = new NXBServiceImpl();
    private DefaultTableModel tblModel;
    private helper helper = new helper();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private List<NXB> nxb;
    private List<NXB> nxb2;
    int sotrang = 1;
    int check;

    public PanelNXBoke() {
        initComponents();
        Table1.apply(jScrollPane1, Table1.TableType.DEFAULT);
        nxb = nxbService.getAll();
        int heso = (sotrang * 5) - 5;
        nxb2 = nxbService.getAll2(heso);
        loadDataTable(nxb2);
//        fillToTable();
        uppanel();
        check = -1;
        tt();
    }

    private void uppanel() {
        int tongsotrang = nxb.size() / 5;
        if (nxb.size() % 5 != 0) {
            tongsotrang += 1;

        }
        lblsotrang.setText(String.valueOf(sotrang) + "/" + tongsotrang);
    }

    private void loadDataTable() {
        tblModel = (DefaultTableModel) tblNXB.getModel();
        tblModel.setRowCount(0);

        for (edu.poly.duan1.model.NXB x : nxbService.getAll()) {
            tblModel.addRow(new Object[]{
                x.getId(),
                x.getMa(),
                x.getTen(),
                sdf.format(x.getNgayTao()),
                sdf.format(x.getNgaySua()),
                x.getTrangThai() == 1 ? "Đang Hợp Tác" : "Ngừng Hợp Tác"
            }
            );
        }
    }

    private boolean checkNull() {
        if (helper.checkNull(txtMa, "Mã") || helper.checkNull(txtTen, "Tên")) {
            return false;
        }
        return true;
    }

    private boolean Validate() {
        String ma = txtMa.getText();
        if (nxbService.getObjbyMa(ma) != null) {
            helper.error(this, "Mã đã tồn tại vui lòng thử lại bằng mã khác");
            return false;
        } else {
            return true;
        }
    }

    private void add() {
        edu.poly.duan1.model.NXB s = new edu.poly.duan1.model.NXB();
        s.setMa(txtMa.getText());
        s.setTen(txtTen.getText());
        s.setNgayTao(java.sql.Date.valueOf(LocalDate.now()));
        s.setNgaySua(java.sql.Date.valueOf(LocalDate.now()));
        if (rdbCon.isSelected()) {
            s.setTrangThai(1);
        } else {
            s.setTrangThai(0);
        }
        if (nxbService.saveOrUpdate(s)) {
            helper.alert(this, "Thêm thành công");
        } else {
            helper.error(this, "Thêm thất bại");
        }
        int heso = (sotrang * 5) - 5;
        nxb2 = nxbService.getAll2(heso);
        loadDataTable(nxb2);
    }

    private void delete() {
        int index = tblNXB.getSelectedRow();
        if (index == -1) {
            helper.error(this, "Vui lòng chọn dòng cần xóa");
        } else {
            NXB nxb = nxbService.getAll().get(index);
            if (nxbService.delete(nxb)) {
                helper.alert(this, "Xóa thành công");
            } else {
                helper.error(this, "Xóa thất bại vui lòng kiểm tra lại");
            }
            reset();
        }
        int heso = (sotrang * 5) - 5;
        nxb2 = nxbService.getAll2(heso);
        loadDataTable(nxb2);
    }
    ButtonGroup buttonGroup2 = new ButtonGroup();

    private void tt() {
        buttonGroup2.add(rdbCon);
        buttonGroup2.add(rdbHet);
    }

    private void update() {
        int row = tblNXB.getSelectedRow();
        NXB s = nxbService.getObjbyMa((String) tblNXB.getValueAt(row, 1));
        if (row == -1) {
            helper.error(this, "Vui lòng chọn dòng cần sửa trước khi sửa");
        } else {
            s.setMa(txtMa.getText());
            s.setTen(txtTen.getText());
            s.setNgaySua(java.sql.Date.valueOf(LocalDate.now()));
            int a;
            if (rdbCon.isSelected()) {
                a = 1;
            } else {
                a = 0;
            }
            s.setTrangThai(a);
            if (nxbService.saveOrUpdate(s)) {
                helper.alert(this, "Sửa thành công");
            } else {
                helper.error(this, "Sửa thất bại");
            }
            int heso = (sotrang * 5) - 5;
            nxb2 = nxbService.getAll2(heso);
            loadDataTable(nxb2);
        }
    }

    private void reset() {
        txtMa.setText("");
        txtTen.setText("");
        buttonGroup2.clearSelection();
        check = -1;
    }

    private void loadDataTable(List<NXB> list) {
        tblModel = (DefaultTableModel) tblNXB.getModel();
        tblModel.setRowCount(0);

        for (edu.poly.duan1.model.NXB x : list) {
            tblModel.addRow(new Object[]{
                x.getId(),
                x.getMa(),
                x.getTen(),
                sdf.format(x.getNgayTao()),
                sdf.format(x.getNgaySua()),
                x.getTrangThai() == 1 ? "Ngừng hợp tác" : "Đang hợp tác"}
            );
        }
    }

    public void search() {
        String ten = txtTimKiem.getText();
        if (ten.isEmpty()) {
            int heso = (sotrang * 5) - 5;
            nxb2 = nxbService.getAll2(heso);
            loadDataTable(nxb2);
        } else {
            loadDataTable(nxbService.search(ten));
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tableScrollButton1 = new edu.poly.duan1.swing.table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNXB = new javax.swing.JTable();
        btnPre = new edu.poly.duan1.swing.button.Button();
        btnNext = new edu.poly.duan1.swing.button.Button();
        jPanel2 = new javax.swing.JPanel();
        lblsotrang = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMa = new edu.poly.duan1.swing.textfield.TextField();
        txtTen = new edu.poly.duan1.swing.textfield.TextField();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new edu.poly.duan1.swing.textfield.TextField();
        btnTimKiem = new edu.poly.duan1.swing.button.Button();
        btnThem = new edu.poly.duan1.swing.button.Button();
        btnSua = new edu.poly.duan1.swing.button.Button();
        btnXoa = new edu.poly.duan1.swing.button.Button();
        btnLamMoi = new edu.poly.duan1.swing.button.Button();
        rdbHet = new edu.poly.duan1.swing.radio_button.RadioButtonCustom();
        rdbCon = new edu.poly.duan1.swing.radio_button.RadioButtonCustom();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        tblNXB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã", "Tên", "Ngày Tạo", "Ngày Sửa", "Trạng thái"
            }
        ));
        tblNXB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblNXBMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblNXB);

        tableScrollButton1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        btnPre.setBackground(new java.awt.Color(3, 155, 216));
        btnPre.setText("pre");
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(3, 155, 216));
        btnNext.setText("next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblsotrang.setText("Label");

        jLabel6.setText("number of pages:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblsotrang)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblsotrang, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtMa.setForeground(new java.awt.Color(3, 155, 216));
        txtMa.setLabelText("Mã");

        txtTen.setLabelText("Tên");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(3, 155, 216));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản Lý Nhà Xuất Bản");

        txtTimKiem.setLabelText("Tìm Kiếm");

        btnTimKiem.setBackground(new java.awt.Color(3, 155, 216));
        btnTimKiem.setForeground(new java.awt.Color(51, 51, 51));
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(3, 155, 216));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(3, 155, 216));
        btnSua.setText("Cập Nhật");
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

        btnLamMoi.setBackground(new java.awt.Color(3, 155, 216));
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        rdbHet.setBackground(new java.awt.Color(3, 155, 216));
        rdbHet.setText("Ngừng hợp tác");

        rdbCon.setBackground(new java.awt.Color(3, 155, 216));
        rdbCon.setText("Đang hợp tác");

        jLabel2.setText("Trạng thái");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(36, 36, 36)
                                .addComponent(rdbCon, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdbHet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnThem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(89, 89, 89))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(185, 185, 185)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtMa, txtTen});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLamMoi, btnSua, btnThem, btnXoa});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdbHet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdbCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tableScrollButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtMa, txtTen});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnLamMoi, btnSua, btnThem, btnXoa});

    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (checkNull()) {
            if (Validate()) {
                add();
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (check == -1) {
            JOptionPane.showMessageDialog(this, "Chọn nhà xuất bản cần cập nhật");
        } else {
            update();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (check == -1) {
            JOptionPane.showMessageDialog(this, "Chọn nhà xuất bản cần xóa");
        } else {
            delete();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        reset();    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        if (txtTimKiem.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào tên nhà xuất bản cần tìm!");
            txtTimKiem.requestFocus();
            return;
        } else {
            search();
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        int tongSoTrang = nxb.size() / 5;
        if (sotrang <= 1) {
            JOptionPane.showMessageDialog(this, "Không Thể Back Nữa !!");
        }
        if (nxb.size() % 5 != 0) {
            tongSoTrang += 1;
        }
        if (sotrang == 1) {
            loadDataTable(nxb2);
        } else {
            nxb2.removeAll(nxb2);
            sotrang -= 1;
            int heso = (sotrang * 5) - 5;
            nxb2 = nxbService.getAll2(heso);
            loadDataTable(nxb2);
            lblsotrang.setText(String.valueOf(sotrang) + "/" + tongSoTrang);
        }
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        int tongSoTrang = nxb.size() / 5;
        if (sotrang > tongSoTrang) {
            JOptionPane.showMessageDialog(this, "Không Thể next Nữa !!");
        }
        if (nxb.size() % 5 != 0) {
            tongSoTrang += 1;
        }
        if (sotrang == tongSoTrang) {
            loadDataTable(nxb2);
        } else {
            nxb2.removeAll(nxb2);
            sotrang += 1;
            int heso = (sotrang * 5) - 5;
            nxb2 = nxbService.getAll2(heso);
            loadDataTable(nxb2);
            lblsotrang.setText(String.valueOf(sotrang) + "/" + tongSoTrang);

        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void tblNXBMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNXBMousePressed
        int index = tblNXB.getSelectedRow();
        tblModel = (DefaultTableModel) tblNXB.getModel();
        String ma = tblModel.getValueAt(index, 1).toString();
        NXB nxb = nxbService.getObjbyMa(ma);
        txtMa.setText(nxb.getMa());
        txtTen.setText(nxb.getTen());
        if (nxb.getTrangThai() == 1) {
            rdbHet.setSelected(true);
        } else {
            rdbCon.setSelected(true);
        }
        check = tblNXB.getSelectedRow();
    }//GEN-LAST:event_tblNXBMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private edu.poly.duan1.swing.button.Button btnLamMoi;
    private edu.poly.duan1.swing.button.Button btnNext;
    private edu.poly.duan1.swing.button.Button btnPre;
    private edu.poly.duan1.swing.button.Button btnSua;
    private edu.poly.duan1.swing.button.Button btnThem;
    private edu.poly.duan1.swing.button.Button btnTimKiem;
    private edu.poly.duan1.swing.button.Button btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblsotrang;
    private edu.poly.duan1.swing.radio_button.RadioButtonCustom rdbCon;
    private edu.poly.duan1.swing.radio_button.RadioButtonCustom rdbHet;
    private edu.poly.duan1.swing.table.TableScrollButton tableScrollButton1;
    private javax.swing.JTable tblNXB;
    private edu.poly.duan1.swing.textfield.TextField txtMa;
    private edu.poly.duan1.swing.textfield.TextField txtTen;
    private edu.poly.duan1.swing.textfield.TextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
