/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edu.poly.duan1.view;

import edu.poly.duan1.model.NXB;
import edu.poly.duan1.services.NXBService;
import edu.poly.duan1.services.impl.NXBServiceImpl;
import edu.poly.duan1.ultis.helper;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin 88
 */
public class View_NXB extends javax.swing.JFrame {

    /**
     * Creates new form View_NXB
     */
    private NXBService nxbService = new NXBServiceImpl();
    private DefaultTableModel tblModel;
    private helper helper = new helper();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private List<NXB> nxb;
    private List<NXB> nxb2;
    int sotrang = 1;
    int check;

    public View_NXB() {
        initComponents();
        setLocationRelativeTo(null);
        nxb = nxbService.getAll();
        int heso = (sotrang * 5) - 5;
        nxb2 = nxbService.getAll2(heso);
        loadDataTable(nxb2);
//        fillToTable();
        uppanel();
        check = -1;

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
                x.getTrangThai() == 1 ? "Đang Hợp Tác" : "Ngừng Hợp Tác"}
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

        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNXB = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        lblTrangThai = new javax.swing.JLabel();
        rdbCon = new javax.swing.JRadioButton();
        rdbHet = new javax.swing.JRadioButton();
        btnback = new javax.swing.JButton();
        btnnext = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLammoi = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblsotrang = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Quản Lý Nhà Xuất Bản");

        jLabel2.setText("Tìm Kiếm");

        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        tblNXB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã", "Tên NXB", "Ngày Tạo", "Ngày Sửa", "Trạng Thái"
            }
        ));
        tblNXB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNXBMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNXB);

        jLabel3.setText("Mã");

        jLabel4.setText("Tên");

        jLabel5.setText("Trạng Thái");

        buttonGroup2.add(rdbCon);
        rdbCon.setText("Đang Hợp Tác");

        buttonGroup2.add(rdbHet);
        rdbHet.setText("Ngừng Hợp Tác");

        btnback.setText("|<");
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        btnnext.setText(">|");
        btnnext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnextActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Cập nhật");
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

        btnLammoi.setText("Làm mới");
        btnLammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLammoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem)
                .addGap(45, 45, 45)
                .addComponent(btnSua)
                .addGap(43, 43, 43)
                .addComponent(btnXoa)
                .addGap(22, 22, 22)
                .addComponent(btnLammoi)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLammoi, btnSua, btnThem, btnXoa});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnLammoi, btnSua, btnThem, btnXoa});

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
                .addComponent(lblsotrang, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(106, 106, 106)
                                                .addComponent(lblTrangThai))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(rdbCon)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdbHet))))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel2)
                        .addGap(30, 30, 30)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiem)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnnext, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 22, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnback, btnnext});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(btnnext)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(rdbCon)
                    .addComponent(rdbHet))
                .addGap(5, 5, 5)
                .addComponent(lblTrangThai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnback, btnnext});

        pack();
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

    private void btnLammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLammoiActionPerformed
        reset();
    }//GEN-LAST:event_btnLammoiActionPerformed

    private void tblNXBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNXBMouseClicked
        int index = tblNXB.getSelectedRow();
        tblModel = (DefaultTableModel) tblNXB.getModel();
        String ma = tblModel.getValueAt(index, 1).toString();
        NXB nxb = nxbService.getObjbyMa(ma);
        txtMa.setText(nxb.getMa());
        txtTen.setText(nxb.getTen());
        if (nxb.getTrangThai() == 0) {
            rdbHet.setSelected(true);
        } else {
            rdbCon.setSelected(true);
        }
        check = tblNXB.getSelectedRow();
    }//GEN-LAST:event_tblNXBMouseClicked

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
       if (txtTimKiem.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào tên nhà xuất bản cần tìm!");
            txtTimKiem.requestFocus();
            return;
        } else {
            search();
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnnextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnextActionPerformed
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
    }//GEN-LAST:event_btnnextActionPerformed

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
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
    }//GEN-LAST:event_btnbackActionPerformed

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemCaretUpdate

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View_NXB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View_NXB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View_NXB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View_NXB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View_NXB().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLammoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnback;
    private javax.swing.JButton btnnext;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JLabel lblsotrang;
    private javax.swing.JRadioButton rdbCon;
    private javax.swing.JRadioButton rdbHet;
    private javax.swing.JTable tblNXB;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

    private void uppanel() {
        int tongsotrang = nxb.size() / 5;
        if (nxb.size() % 5 != 0) {
            tongsotrang += 1;

        }
        lblsotrang.setText(String.valueOf(sotrang) + "/" + tongsotrang);
    }
}
