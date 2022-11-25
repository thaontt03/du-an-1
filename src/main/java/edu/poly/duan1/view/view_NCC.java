/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package edu.poly.duan1.view;

import edu.poly.duan1.model.NCC;
import edu.poly.duan1.services.NCCService;
import edu.poly.duan1.services.impl.NCCServiceImpl;
import edu.poly.duan1.ultis.helper;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AnhTiTan
 */
public class view_NCC extends javax.swing.JPanel {

    /**
     * Creates new form viewNCC
     */
    private NCCService nccService = new NCCServiceImpl();
    private DefaultTableModel tblModel;
    private helper helper = new helper();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public view_NCC() {
        initComponents();
        loadDataTable();
        tblNCC.getTableHeader().setReorderingAllowed(false);
        GroupTT();
    }

    private void loadDataTable() {
        tblModel = (DefaultTableModel) tblNCC.getModel();
        tblModel.setRowCount(0);
        int i = 1;
        for (edu.poly.duan1.model.NCC x : nccService.getAll()) {
            tblModel.addRow(new Object[]{
                i++,
                x.getMa(),
                x.getTen(),
                sdf.format(x.getNgayTao()),
                sdf.format(x.getNgaySua()),
                x.getTrangThai()}
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
        if (nccService.getObjbyMa(ma) != null) {
            helper.error(this, "mã đã tồn tại vui lòng thử lại bằng mã khác");
            return false;
        } else {
            return true;
        }
    }

    private void add() {
        edu.poly.duan1.model.NCC s = new edu.poly.duan1.model.NCC();
        s.setMa(txtMa.getText());
        s.setTen(txtTen.getText());
        s.setNgayTao(java.sql.Date.valueOf(LocalDate.now()));
        s.setNgaySua(java.sql.Date.valueOf(LocalDate.now()));

        if (nccService.saveOrUpdate(s)) {
            helper.alert(this, "add thanh cong");
        } else {
            helper.error(this, "add loi");
        }
        loadDataTable();
    }

    private void delete() {
        int index = tblNCC.getSelectedRow();
        if (index == -1) {
            helper.error(this, "vui lòng chọn dòng cần xóa");
        } else {
            NCC s = nccService.getAll().get(index);
            if (nccService.delete(s)) {
                helper.alert(this, "xóa thành công");
            } else {
                helper.error(this, "xóa thất bại vui lòng kiểm tra lại");
            }
            reset();
        }
        loadDataTable();
    }

    private void update() {
        int row = tblNCC.getSelectedRow();
        NCC s = nccService.getObjbyMa((String) tblNCC.getValueAt(row, 1));
        if (row == -1) {
            helper.error(this, "vui lòng chọn dòng cần sửa trước khi sửa");
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
            if (nccService.saveOrUpdate(s)) {
                helper.alert(this, "sửa thành công");
            } else {
                helper.error(this, "sửa thất bại");
            }
            loadDataTable();
        }
    }
    ButtonGroup buttonGroup1 = new ButtonGroup();

    private void GroupTT() {
        buttonGroup1.add(rdbCon);
        buttonGroup1.add(rdbHet);
    }

    private void reset() {
        txtMa.setText("");
        txtTen.setText("");
        buttonGroup1.clearSelection();

    }

    private void loadDataTable(List<NCC> list) {
        tblModel = (DefaultTableModel) tblNCC.getModel();
        tblModel.setRowCount(0);
        int i = 1;
        for (edu.poly.duan1.model.NCC x : list) {
            tblModel.addRow(new Object[]{
                i++,
                x.getMa(),
                x.getTen(),
                sdf.format(x.getNgayTao()),
                sdf.format(x.getNgaySua()),
                x.getTrangThai()}
            );
        }
    }

    public void search() {
        String ten = txtTimKiem.getText();
        loadDataTable(nccService.search(ten));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtTimKiem = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNCC = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        rdbCon = new javax.swing.JRadioButton();
        rdbHet = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ NCC");

        jButton1.setText("Tìm kiếm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tblNCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã", "Tên", "Ngày nhập", "Ngày sửa", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNCCMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNCC);

        jLabel3.setText("Mã");

        jLabel4.setText("Tên");

        jLabel5.setText("Trạng thái");

        rdbCon.setText("Đang kinh doanh");

        rdbHet.setText("Ngừng kinh doanh");

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

        btnReset.setText("Làm mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnReset)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(65, 65, 65))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(79, 79, 79)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(44, 44, 44)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rdbCon)
                                .addGap(18, 18, 18)
                                .addComponent(rdbHet))
                            .addComponent(txtTen, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                            .addComponent(txtMa))))
                .addGap(45, 45, 45))
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jButton1)))
                    .addGap(38, 38, 38)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(rdbCon)
                    .addComponent(rdbHet))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnReset))
                .addGap(15, 15, 15))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(169, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNCCMouseClicked
        int index = tblNCC.getSelectedRow();
        NCC s = nccService.getAll().get(index);
        txtMa.setText(s.getMa());
        txtTen.setText(s.getTen());
        if (s.getTrangThai() == 0) {
            rdbHet.setSelected(true);
        } else {
            rdbCon.setSelected(true);
        }
    }//GEN-LAST:event_tblNCCMouseClicked

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        search();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        search();
    }//GEN-LAST:event_txtTimKiemCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdbCon;
    private javax.swing.JRadioButton rdbHet;
    private javax.swing.JTable tblNCC;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
