/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edu.poly.duan1.view;

import edu.poly.duan1.model.ChucVu;
import edu.poly.duan1.model.NguoiDung;
import edu.poly.duan1.model.TheLoai;
import edu.poly.duan1.services.ChucVuService;
import edu.poly.duan1.services.NguoiDungService;
import edu.poly.duan1.services.impl.ChucVuServiceImpl;
import edu.poly.duan1.services.impl.NguoiDungServiceImpl;
import edu.poly.duan1.ultis.helper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin 88
 */
public class view_NguoiDung extends javax.swing.JFrame {

    /**
     * Creates new form view_NguoiDung
     */
    private NguoiDungService ndService = new NguoiDungServiceImpl();
    private ChucVuService CVService = new ChucVuServiceImpl();
    private DefaultComboBoxModel<ChucVu> dcbmCV;
    private DefaultTableModel tblModel;
    private helper helper = new helper();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    int check;

    public view_NguoiDung() {
        initComponents();
        setLocationRelativeTo(null);
        dcbmCV = new DefaultComboBoxModel<>();
        loadDataTable();
        loadCBChucVu();
        check = -1;
    }

    private void loadDataTable() {
        int i = 1;
        tblModel = (DefaultTableModel) tblND.getModel();
        tblModel.setColumnIdentifiers(new String[]{"STT", "Mã", "Họ Tên", "Giới Tính", "Ngày Sinh", "Địa Chỉ", "Sđt", "Mật Khẩu", "Chức Vụ", "Ngày Tạo", "Ngày Sửa", "Trạng Thái"});
        tblModel.setRowCount(0);
        for (NguoiDung x : ndService.getAll()) {
            tblModel.addRow(new Object[]{
                i++,
                x.getMa(),
                x.getHoTen(),
                x.getGioiTinh(),
                sdf.format(x.getNgaySinh()),
                x.getDiaChi(),
                x.getSdt(),
                x.getMatKhau(),
                x.getChucVu().getTen(),
                sdf.format(x.getNgayTao()),
                sdf.format(x.getNgaySua()),
                x.getTrangThai() == 1 ? "Đang làm việc" : "Nghỉ Làm"
            });
        }
    }

    private void loadDataTable(List<NguoiDung> list) {
        tblModel = (DefaultTableModel) tblND.getModel();
        tblModel.setColumnIdentifiers(new String[]{"STT", "Mã", "Họ Tên", "Giới Tính", "Ngày Sinh", "Địa Chỉ", "Sđt", "Mật Khẩu", "Chức Vụ", "Ngày Tạo", "Ngày Sửa", "Trạng Thái"});
        tblModel.setRowCount(0);
        int i = 1;
        for (edu.poly.duan1.model.NguoiDung x : list) {
            tblModel.addRow(new Object[]{
                i++,
                x.getMa(),
                x.getHoTen(),
                x.getGioiTinh(),
                sdf.format(x.getNgaySinh()),
                x.getDiaChi(),
                x.getSdt(),
                x.getMatKhau(),
                x.getChucVu().getTen(),
                sdf.format(x.getNgayTao()),
                sdf.format(x.getNgaySua()),
                x.getTrangThai() == 1 ? "Đang làm việc" : "Nghỉ Làm"}
            );
        }
    }

    public void search() {
        String ma = txtTimKiem.getText();
        loadDataTable(ndService.search(ma));
    }

    private void loadCBChucVu() {
        dcbmCV = (DefaultComboBoxModel) cboChucVu.getModel();
        dcbmCV.removeAllElements();
        for (ChucVu x : CVService.getAll()) {
            dcbmCV.addElement(x);
        }
    }

    private void loadText() {
        int choice = tblND.getSelectedRow();
        NguoiDung nd = ndService.getAll().get(choice);
        txtMa.setText(nd.getMa());
        txtHoTen.setText(nd.getHoTen());
        txtNS.setText(sdf.format(nd.getNgaySinh()));
        if (nd.getGioiTinh().equals("Nam")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        txtDiaChi.setText(nd.getDiaChi());
        txtSdt.setText(nd.getSdt());
        txtMK.setText(nd.getMatKhau());
        dcbmCV.setSelectedItem(nd.getChucVu());
        if (nd.getTrangThai() == 1) {
            rdoDDK.setSelected(true);
        } else {
            rdoCDK.setSelected(true);
        }
    }

    private boolean checkNull() {
        if (helper.checkNull(txtMa, "Mã") || helper.checkNull(txtHoTen, "Họ tên") || helper.checkNull(txtDiaChi, "Địa Chỉ")
                || helper.checkNull(txtSdt, "SĐT") || helper.checkNull(txtMK, "Mật Khẩu") || helper.checkNull(txtNS, "Ngày Sinh")) {
            return false;
        }
        return true;
    }

    private void checkDate() {
        helper.checkDate(txtNS.getText());
    }

    private boolean Validate() {
        if (!txtSdt.getText().matches("^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ");
            return false;
        }
        String ma = txtMa.getText();
        if (ndService.getObjbyMa(ma) != null) {
            helper.error(this, "Mã đã tồn tại vui lòng thử lại bằng mã khác");
            return false;
        } else {
            return true;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        txtNS = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        cboChucVu = new javax.swing.JComboBox<>();
        rdoCDK = new javax.swing.JRadioButton();
        rdoDDK = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblND = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtMK = new javax.swing.JTextField();
        txtTimKiem = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btnTK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Quản Lý Người Dùng");

        jLabel2.setText("Mã");

        jLabel3.setText("Họ tên");

        jLabel4.setText("Giới Tính");

        jLabel5.setText("Ngày Sinh");

        jLabel6.setText("Địa Chỉ");

        jLabel7.setText("Sđt");

        jLabel8.setText("Mật Khẩu");

        jLabel9.setText("Chức Vụ");

        jLabel12.setText("Trạng Thái");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        txtNS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNSActionPerformed(evt);
            }
        });

        cboChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonGroup2.add(rdoCDK);
        rdoCDK.setText("Nghỉ Làm");

        buttonGroup2.add(rdoDDK);
        rdoDDK.setText("Đang làm việc");

        tblND.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblND.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblND);

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jButton2.setText("Cập nhật");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Reset");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Xóa");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });

        jLabel13.setText("Search Mã");

        btnTK.setText("Tìm Kiếm");
        btnTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel2)
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rdoNam)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoNu))
                            .addComponent(txtMa, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txtHoTen)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(293, 293, 293)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(txtTimKiem)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel13)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel12)))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(rdoCDK)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(rdoDDK))
                                            .addComponent(txtNS, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTK)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel9)
                                            .addGap(18, 18, 18)
                                            .addComponent(cboChucVu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel8)
                                                .addComponent(jLabel7))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(txtSdt))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addGap(13, 13, 13)
                                                    .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(57, 57, 57)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnThem)
                                            .addComponent(jButton4))
                                        .addGap(46, 46, 46)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 836, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboChucVu, txtDiaChi, txtHoTen, txtMa, txtNS, txtSdt});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnThem, jButton2, jButton3, jButton4});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu)
                    .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(rdoCDK)
                    .addComponent(rdoDDK))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel13))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTK))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNSActionPerformed

    private void tblNDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNDMouseClicked
        // TODO add your handling code here:
        int index = 0;
        index = tblND.getSelectedRow();
        loadText();
        check = tblND.getSelectedRow();
    }//GEN-LAST:event_tblNDMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (checkNull()) {
            if (Validate()) {
                checkDate();
                add();
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
          if (check == -1) {
            JOptionPane.showMessageDialog(this, "Chọn người dùng cần cập nhật");
        } else {
            update();
        } 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
          if (check == -1) {
            JOptionPane.showMessageDialog(this, "Chọn người dùng cần xóa");
        } else {
            delete();
        } 
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void btnTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKActionPerformed
        // TODO add your handling code here:
          if (txtTimKiem.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào mã người dùng cần tìm!");
            txtTimKiem.requestFocus();
            return;
        } else {
            search();
        }
    }//GEN-LAST:event_btnTKActionPerformed

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
            java.util.logging.Logger.getLogger(view_NguoiDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(view_NguoiDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(view_NguoiDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(view_NguoiDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new view_NguoiDung().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTK;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboChucVu;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoCDK;
    private javax.swing.JRadioButton rdoDDK;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblND;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMK;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtNS;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

    private void add() {
        try {
            NguoiDung nd = new NguoiDung();
            nd.setMa(txtMa.getText());
            nd.setHoTen(txtHoTen.getText());
            nd.setNgaySinh(sdf.parse(txtNS.getText()));
            nd.setGioiTinh(rdoNam.isSelected() ? "Nam" : "Nữ");
            nd.setDiaChi(txtDiaChi.getText());
            nd.setSdt(txtSdt.getText());
            nd.setMatKhau(txtMK.getText());
            ChucVu cv = (ChucVu) cboChucVu.getSelectedItem();
            nd.setChucVu(cv);
            nd.setNgayTao(java.sql.Date.valueOf(LocalDate.now()));
            nd.setNgaySua(java.sql.Date.valueOf(LocalDate.now()));
            int a;
            if (rdoDDK.isSelected()) {
                a = 1;
            } else {
                a = 0;
            }
            nd.setTrangThai(a);
            if (ndService.saveOrUpdate(nd)) {
                helper.alert(this, "Thêm Thành Công");
                reset();
            } else {
                helper.error(this, "Thêm Thất Bại");
            }
            loadDataTable();
        } catch (ParseException ex) {
            Logger.getLogger(view_NguoiDung.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void update() {
        int row = tblND.getSelectedRow();
        NguoiDung nd = ndService.getObjbyMa((String) tblND.getValueAt(row, 1));
        if (row == -1) {
            helper.error(this, "Vui lòng chọn Người Dùng cần sửa");
        } else {
            nd.setMa(txtMa.getText());
            nd.setHoTen(txtHoTen.getText());
            try {
                nd.setNgaySinh(sdf.parse(txtNS.getText()));
            } catch (ParseException ex) {
                Logger.getLogger(view_NguoiDung.class.getName()).log(Level.SEVERE, null, ex);
            }
            nd.setGioiTinh(rdoNam.isSelected() ? "Nam" : "Nữ");
            nd.setDiaChi(txtDiaChi.getText());
            nd.setSdt(txtSdt.getText());
            nd.setMatKhau(txtMK.getText());
            ChucVu cv = (ChucVu) cboChucVu.getSelectedItem();
            nd.setChucVu(cv);
            nd.setNgayTao(java.sql.Date.valueOf(LocalDate.now()));
            nd.setNgaySua(java.sql.Date.valueOf(LocalDate.now()));
            int a;
            if (rdoDDK.isSelected()) {
                a = 1;
            } else {
                a = 0;
            }
            nd.setTrangThai(a);
            if (ndService.saveOrUpdate(nd)) {
                helper.alert(this, "Sửa thành công");
                reset();
            } else {
                helper.error(this, "Sửa thất bại");
            }
            loadDataTable();
        }
    }

    private void delete() {
        int index = tblND.getSelectedRow();
        if (index == -1) {
            helper.error(this, "vui lòng chọn dòng cần xóa");
        } else {
            NguoiDung nd = ndService.getAll().get(index);
            if (ndService.delete(nd)) {
                helper.alert(this, "xóa thành công");
            } else {
                helper.error(this, "xóa thất bại vui lòng kiểm tra lại");
            }
            reset();
        }
        loadDataTable();
    }

    private void reset() {
        txtMa.setText("");
        txtHoTen.setText("");
        txtNS.setText("");
        txtDiaChi.setText("");
        txtSdt.setText("");
        txtMK.setText("");
        check = -1;
    }

}
