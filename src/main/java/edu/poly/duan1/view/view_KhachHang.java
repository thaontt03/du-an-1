/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.duan1.view;

import edu.poly.duan1.model.KhachHang;
import edu.poly.duan1.services.KhachHangService;
import edu.poly.duan1.services.impl.KhachHangServiceImpl;
import edu.poly.duan1.ultis.helper;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nguyen Thi Thu Thao
 */
public class view_KhachHang extends javax.swing.JPanel {

    private KhachHangService khachHangServices = new KhachHangServiceImpl();
    private DefaultTableModel tblModel;
    private helper helper = new helper();
    int check;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Creates new form view_KhachHang
     */
    public view_KhachHang() {
        initComponents();
        loadDataTable();
        check = -1;
    }

    private void loadDataTable() {
        tblModel = (DefaultTableModel) tblKhachHang.getModel();
        tblModel.setRowCount(0);

        for (edu.poly.duan1.model.KhachHang x : khachHangServices.getAll()) {
            tblModel.addRow(new Object[]{x.getId(), x.getMa(), x.getTen(), x.getDiaChi(), x.getSdt(), x.getNgayTao(), x.getNgaySua(), x.getTrangThai() == 1 ? "Enable" : "Disable"});
        }
    }

    private boolean checkNull() {
        if (helper.checkNull(txtMa, "Mã") || helper.checkNull(txtTen, "Tên") || helper.checkNull(txtDiaChi, "Địa chỉ") || helper.checkNull(txtSdt, "Số điện thoại")) {
            return false;
        }
        if (!txtSdt.getText().matches("^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ");
            return false;
        }
        return true;
    }

    private boolean Validate() {
        String ma = txtMa.getText();
        if (khachHangServices.getObjbyMa(ma) != null) {
            helper.error(this, "Mã đã tồn tại vui lòng thử lại bằng mã khác");
            return false;
        } else {
            return true;
        }
    }

    private void add() {
        edu.poly.duan1.model.KhachHang s = new edu.poly.duan1.model.KhachHang();
        s.setMa(txtMa.getText());
        s.setTen(txtTen.getText());
        s.setSdt(txtSdt.getText());
        s.setDiaChi(txtDiaChi.getText());
        int a;
        if (rdbEnable.isSelected()) {
            a = 1;
        } else {
            a = 0;
        }
        s.setTrangThai(a);
        s.setNgayTao(java.sql.Date.valueOf(LocalDate.now()));
        s.setNgaySua(java.sql.Date.valueOf(LocalDate.now()));
        if (khachHangServices.saveOrUpdate(s)) {
            helper.alert(this, "Thêm thành công");
            reset();
        } else {
            helper.error(this, "Thêm thất bại");
        }
        loadDataTable();
    }

    private void loadDataTable(List<KhachHang> list) {
        tblModel = (DefaultTableModel) tblKhachHang.getModel();
        tblModel.setRowCount(0);

        for (KhachHang x : list) {
            tblModel.addRow(new Object[]{
                x.getId(),
                x.getMa(),
                x.getTen(),
                x.getDiaChi(),
                x.getSdt(),
                sdf.format(x.getNgayTao()),
                sdf.format(x.getNgaySua()),
                x.getTrangThai() == 1 ? "Enable" : "Disable"
            }
            );
        }
    }

    private void update() {
        int row = tblKhachHang.getSelectedRow();
        KhachHang s = khachHangServices.getObjbyMa((String) tblKhachHang.getValueAt(row, 1));
        if (row == -1) {
            helper.error(this, "Vui lòng chọn dòng khách hàng cần cập nhật");
        } else {
            s.setMa(txtMa.getText());
            s.setTen(txtTen.getText());
            s.setSdt(txtSdt.getText());
            s.setDiaChi(txtDiaChi.getText());
            s.setNgaySua(java.sql.Date.valueOf(LocalDate.now()));
            int a;
            if (rdbEnable.isSelected()) {
                a = 1;
            } else {
                a = 0;
            }
            s.setTrangThai(a);
            if (khachHangServices.saveOrUpdate(s)) {
                helper.alert(this, "Cập nhật thành công");
                reset();
            } else {
                helper.error(this, "Cập nhật thất bại");
            }
            loadDataTable();
        }
    }

    private void delete() {
        int index = tblKhachHang.getSelectedRow();
        if (index == -1) {
            helper.error(this, "Vui lòng khách hàng cần xóa");
        } else {
            KhachHang s = khachHangServices.getAll().get(index);
            if (khachHangServices.delete(s)) {
                helper.alert(this, "Xóa thành công");
                reset();
            } else {
                helper.error(this, "Xóa thất bại vui lòng kiểm tra lại");
            }
            reset();
        }
        loadDataTable();
    }

    private void reset() {
        txtMa.setText("");
        txtTen.setText("");
        buttonGroup1.clearSelection();
        txtDiaChi.setText("");
        txtSdt.setText("");
        check = -1;
    }

    public void search() {
        String ten = txtTimKiem.getText();
        loadDataTable(khachHangServices.search(ten));
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        txtMa = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        rdbEnable = new javax.swing.JRadioButton();
        rdbdisable = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();

        jLabel1.setText("Mã:");

        jLabel2.setText("Họ Tên:");

        jLabel3.setText("Địa Chỉ:");

        jLabel4.setText("Số điện thoại:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Thông Tin Khách Hàng");

        jLabel6.setText("Trạng thái:");

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã", "Họ Tên", "Địa chỉ", "SĐT", "Ngày thêm", "Ngày sửa", "Trạng Thái"
            }
        ));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);

        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });

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

        btnReset.setText("Làm mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdbEnable);
        rdbEnable.setText("Enable");

        buttonGroup1.add(rdbdisable);
        rdbdisable.setText("Disable");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTimKiem))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addGap(0, 4, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(rdbEnable)
                                .addGap(36, 36, 36)
                                .addComponent(rdbdisable)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(190, 190, 190))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(btnSua)
                                .addGap(61, 61, 61)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(244, 244, 244))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(4, 4, 4)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(rdbEnable)
                    .addComponent(rdbdisable)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnReset)
                    .addComponent(btnThem))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed

    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (checkNull()) {
            if (Validate()) {
                add();
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (check == -1) {
            JOptionPane.showMessageDialog(this, "Chọn khách hàng cần xóa");
        } else {
            delete();
        }      // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (check == -1) {
            JOptionPane.showMessageDialog(this, "Chọn khách hàng cần cập nhật");
        } else {
            update();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        int index = tblKhachHang.getSelectedRow();
        KhachHang s = khachHangServices.getAll().get(index);
        txtMa.setText(s.getMa());
        txtTen.setText(s.getTen());
        txtDiaChi.setText(s.getDiaChi());
        txtSdt.setText(s.getSdt());
        if (s.getTrangThai() == 0) {
            rdbdisable.setSelected(true);
        } else {
            rdbEnable.setSelected(true);
        }
        check = tblKhachHang.getSelectedRow();
    }//GEN-LAST:event_tblKhachHangMouseClicked
//    public boolean checktim() {
//         if (txtTimKiem.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào mã sinh viên cần tìm!");
//            txtTimKiem.requestFocus();
//        }
//        return true;
//    }
    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        if (txtTimKiem.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào tên khách hàng cần tìm!");
            txtTimKiem.requestFocus();
            return;
        } else {
            search();
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        search();
    }//GEN-LAST:event_txtTimKiemCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton rdbEnable;
    private javax.swing.JRadioButton rdbdisable;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
