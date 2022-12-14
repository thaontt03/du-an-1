/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package edu.poly.duan1.view;

import edu.poly.duan1.model.TheLoai;
import edu.poly.duan1.services.TheLoaiService;
import edu.poly.duan1.services.impl.TheLoaiServiceImpl;
import edu.poly.duan1.swing.table.Table1;
import edu.poly.duan1.ultis.helper;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class PanelTheLoaiOke extends javax.swing.JPanel {

    /**
     * Creates new form PanelNXBoke
     */
    private TheLoaiService theLoaiServices = new TheLoaiServiceImpl();
    private DefaultTableModel tblModel;
    private helper helper = new helper();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    int check;

    public PanelTheLoaiOke() {
        initComponents();
        Table1.apply(jScrollPane1, Table1.TableType.DEFAULT);
        loadDataTable();
        check = -1;
    }

    private void loadDataTable(List<TheLoai> list) {
        tblModel = (DefaultTableModel) tblTheLoai.getModel();
        tblModel.setRowCount(0);

        for (edu.poly.duan1.model.TheLoai x : list) {
            tblModel.addRow(new Object[]{
                x.getId(),
                x.getMa(),
                x.getTen(),
                sdf.format(x.getNgayTao()),
                sdf.format(x.getNgaySua()),}
            );
        }
    }

    private void loadDataTable() {
        tblModel = (DefaultTableModel) tblTheLoai.getModel();
        tblModel.setRowCount(0);

        for (edu.poly.duan1.model.TheLoai x : theLoaiServices.getAll()) {
            tblModel.addRow(new Object[]{
                x.getId(),
                x.getMa(),
                x.getTen(),
                sdf.format(x.getNgayTao()),
                sdf.format(x.getNgaySua())});
        }

    }

    private boolean checkNull() {
        if (helper.checkNull(txtMa, "M??") || helper.checkNull(txtTen, "T??n")) {
            return false;
        }
        return true;
    }

    private boolean Validate() {
        String ma = txtMa.getText();
        if (theLoaiServices.getObjbyMa(ma) != null) {
            helper.error(this, "M?? ???? t???n t???i vui l??ng th??? l???i b???ng m?? kh??c");
            return false;
        } else {
            return true;
        }
    }

    private void add() {
        edu.poly.duan1.model.TheLoai s = new edu.poly.duan1.model.TheLoai();
        s.setMa(txtMa.getText());
        s.setTen(txtTen.getText());
        s.setNgayTao(java.sql.Date.valueOf(LocalDate.now()));
        s.setNgaySua(java.sql.Date.valueOf(LocalDate.now()));
        if (theLoaiServices.saveOrUpdate(s)) {
            helper.alert(this, "Th??m th??nh c??ng");
            reset();
        } else {
            helper.error(this, "Th??m kh??ng th??nh c??ng");
        }
        loadDataTable();
    }

    private void reset() {
        txtMa.setText("");
        txtTen.setText("");
        check = -1;
    }

    private void delete() {
        int index = tblTheLoai.getSelectedRow();
        if (index == -1) {
            helper.error(this, "vui l??ng ch???n d??ng c???n x??a");
        } else {
            TheLoai s = theLoaiServices.getAll().get(index);
            if (theLoaiServices.delete(s)) {
                helper.alert(this, "x??a th??nh c??ng");
            } else {
                helper.error(this, "x??a th???t b???i vui l??ng ki???m tra l???i");
            }
            reset();
        }
        loadDataTable();
    }

    private void update() {
        int row = tblTheLoai.getSelectedRow();
        TheLoai s = theLoaiServices.getObjbyMa((String) tblTheLoai.getValueAt(row, 1));
        if (row == -1) {
            helper.error(this, "Vui l??ng ch???n th??? lo???i c???n s???a");
        } else {
            s.setMa(txtMa.getText());
            s.setTen(txtTen.getText());
            s.setNgaySua(java.sql.Date.valueOf(LocalDate.now()));

            if (theLoaiServices.saveOrUpdate(s)) {
                helper.alert(this, "S???a th??nh c??ng");
                reset();
            } else {
                helper.error(this, "S???a th???t b???i");
            }
            loadDataTable();
        }
    }

    public void search() {
        String ten = txtTimKiem.getText();
        loadDataTable(theLoaiServices.search(ten));
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
        tblTheLoai = new javax.swing.JTable();
        txtMa = new edu.poly.duan1.swing.textfield.TextField();
        txtTen = new edu.poly.duan1.swing.textfield.TextField();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new edu.poly.duan1.swing.textfield.TextField();
        btnThem = new edu.poly.duan1.swing.button.Button();
        btnSua = new edu.poly.duan1.swing.button.Button();
        btnXoa = new edu.poly.duan1.swing.button.Button();
        btnLamMoi = new edu.poly.duan1.swing.button.Button();

        setBackground(new java.awt.Color(255, 255, 255));

        tblTheLoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "M??", "T??n", "Ng??y t???o", "Ng??y s???a"
            }
        ));
        tblTheLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTheLoaiMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblTheLoai);

        tableScrollButton1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        txtMa.setForeground(new java.awt.Color(3, 155, 216));
        txtMa.setLabelText("M??");

        txtTen.setLabelText("T??n");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(3, 155, 216));
        jLabel1.setText("Qu???n l?? th??? lo???i");

        txtTimKiem.setLabelText("T??m Ki???m");
        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(3, 155, 216));
        btnThem.setText("Th??m");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(3, 155, 216));
        btnSua.setText("C???p Nh???t");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(3, 155, 216));
        btnXoa.setText("X??a");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(3, 155, 216));
        btnLamMoi.setText("L??m M???i");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(82, 82, 82))
            .addGroup(layout.createSequentialGroup()
                .addGap(244, 244, 244)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtMa, txtTen});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLamMoi, btnSua, btnThem, btnXoa});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtMa, txtTen});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnLamMoi, btnSua, btnThem, btnXoa});

    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        search();
    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void tblTheLoaiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTheLoaiMousePressed
        int index = tblTheLoai.getSelectedRow();
        TheLoai s = theLoaiServices.getAll().get(index);
        txtMa.setText(s.getMa());
        txtTen.setText(s.getTen());
        check = tblTheLoai.getSelectedRow();    }//GEN-LAST:event_tblTheLoaiMousePressed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (checkNull()) {
            if (Validate()) {
                add();
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (check == -1) {
            JOptionPane.showMessageDialog(this, "Ch???n th??? lo???i c???n c???p nh???t");
        } else {
            update();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (check == -1) {
            JOptionPane.showMessageDialog(this, "Ch???n th??? lo???i c???n x??a");
        } else {
            delete();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        reset();
    }//GEN-LAST:event_btnLamMoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private edu.poly.duan1.swing.button.Button btnLamMoi;
    private edu.poly.duan1.swing.button.Button btnSua;
    private edu.poly.duan1.swing.button.Button btnThem;
    private edu.poly.duan1.swing.button.Button btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private edu.poly.duan1.swing.table.TableScrollButton tableScrollButton1;
    private javax.swing.JTable tblTheLoai;
    private edu.poly.duan1.swing.textfield.TextField txtMa;
    private edu.poly.duan1.swing.textfield.TextField txtTen;
    private edu.poly.duan1.swing.textfield.TextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
