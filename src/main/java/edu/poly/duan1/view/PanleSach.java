/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package edu.poly.duan1.view;

import edu.poly.duan1.model.Sach;
import edu.poly.duan1.services.SachServices;
import edu.poly.duan1.services.impl.SachServicesImpl;
import edu.poly.duan1.ultis.helper;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AnhTiTan
 */
public class PanleSach extends javax.swing.JPanel {

    /**
     * Creates new form PanleSach
     */
        private SachServices sachServices = new SachServicesImpl();
    private DefaultTableModel tblModel;
    private helper helper = new helper();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    public PanleSach() {
        initComponents();
        tblSach.fixTable(jScrollPane1);
//        setLocationRelativeTo(null);
        loadDataTable();
        tblSach.getTableHeader().setReorderingAllowed(false);
    }
        private void loadDataTable() {
        tblModel = (DefaultTableModel) tblSach.getModel();
        tblModel.setColumnIdentifiers(new String[]{"Id", "Mã", "Tên", "Ngày tạo", "Ngày sửa"});
        tblModel.setRowCount(0);

        for (edu.poly.duan1.model.Sach x : sachServices.getAll()) {
            tblModel.addRow(new Object[]{
                x.getId(),
                x.getMa(),
                x.getTen(),
                sdf.format(x.getNgayTao()),
                sdf.format(x.getNgaySua())}
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
        if (sachServices.getObjbyMa(ma) != null) {
            helper.error(this, "mã đã tồn tại vui lòng thử lại bằng mã khác");
            return false;
        } else {
            return true;
        }
    }

    private void add() {
        edu.poly.duan1.model.Sach s = new edu.poly.duan1.model.Sach();
        s.setMa(txtMa.getText());
        s.setTen(txtTen.getText());
        s.setNgayTao(java.sql.Date.valueOf(LocalDate.now()));
        s.setNgaySua(java.sql.Date.valueOf(LocalDate.now()));
        if (sachServices.saveOrUpdate(s)) {
            helper.alert(this, "add thanh cong");
        } else {
            helper.error(this, "add loi");
        }
        loadDataTable();
    }

    private void delete() {
        int index = tblSach.getSelectedRow();
        if (index == -1) {
            helper.error(this, "vui lòng chọn dòng cần xóa");
        } else {
            Sach s = sachServices.getAll().get(index);
            if (sachServices.delete(s)) {
                helper.alert(this, "xóa thành công");
            } else {
                helper.error(this, "xóa thất bại vui lòng kiểm tra lại");
            }
            reset();
        }
        loadDataTable();
    }

    private void update() {
        int row = tblSach.getSelectedRow();
        Sach s = sachServices.getObjbyMa((String) tblSach.getValueAt(row, 1));
        if (row == -1) {
            helper.error(this, "vui lòng chọn dòng cần sửa trước khi sửa");
        } else {
            s.setMa(txtMa.getText());
            s.setTen(txtTen.getText());
            s.setNgaySua(java.sql.Date.valueOf(LocalDate.now()));
            if (sachServices.saveOrUpdate(s)) {
                helper.alert(this, "sửa thành công");
            } else {
                helper.error(this, "sửa thất bại");
            }
            loadDataTable();
        }
    }

    private void reset() {
        txtMa.setText("");
        txtTen.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtTimKiem = new edu.poly.duan1.swing.textfield.TextField();
        roundPanel1 = new edu.poly.duan1.swing.scrollbar.roundpanel.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSach = new edu.poly.duan1.swing.table.Table();
        btnThem = new edu.poly.duan1.swing.button.Button();
        btnSua = new edu.poly.duan1.swing.button.Button();
        btnXoa = new edu.poly.duan1.swing.button.Button();
        btnReset = new edu.poly.duan1.swing.button.Button();
        txtTen = new edu.poly.duan1.swing.textfield.TextField();
        txtMa = new edu.poly.duan1.swing.textfield.TextField();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtTimKiem.setLabelText("Tìm kiếm");
        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });

        tblSach.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSach);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1086, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnThem.setBackground(new java.awt.Color(204, 204, 204));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(204, 204, 204));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(204, 204, 204));
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        txtTen.setLabelText("Tên sách");

        txtMa.setLabelText("Mã Sách");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(239, 239, 239)
                                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(262, 262, 262)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtTen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                                    .addComponent(txtMa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(159, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSachMouseClicked
               int index = tblSach.getSelectedRow();
        Sach s = sachServices.getAll().get(index);
        txtMa.setText(s.getMa());
        txtTen.setText(s.getTen());
    }//GEN-LAST:event_tblSachMouseClicked

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
            if (checkNull()) {
            if (Validate()) {
                add();
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
            update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
       delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
       reset();
    }//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private edu.poly.duan1.swing.button.Button btnReset;
    private edu.poly.duan1.swing.button.Button btnSua;
    private edu.poly.duan1.swing.button.Button btnThem;
    private edu.poly.duan1.swing.button.Button btnXoa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private edu.poly.duan1.swing.scrollbar.roundpanel.RoundPanel roundPanel1;
    private edu.poly.duan1.swing.table.Table tblSach;
    private edu.poly.duan1.swing.textfield.TextField txtMa;
    private edu.poly.duan1.swing.textfield.TextField txtTen;
    private edu.poly.duan1.swing.textfield.TextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}