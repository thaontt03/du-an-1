/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edu.poly.duan1.ViewNV;

import edu.poly.duan1.model.KhachHang;
import edu.poly.duan1.services.KhachHangService;
import edu.poly.duan1.services.impl.KhachHangServiceImpl;
import edu.poly.duan1.ultis.helper;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tran Tien
 */
public class ViewNV_KH extends javax.swing.JFrame {

    private KhachHangService khachHangServices = new KhachHangServiceImpl();
    private DefaultTableModel tblModel;
    private helper helper = new helper();
    private List<KhachHang> list;
    private List<KhachHang> listkh;
    private int sotrang = 1;

    /**
     * Creates new form ViewNV_KH
     */
    public ViewNV_KH() {
        initComponents();
        setLocationRelativeTo(null);
        list = khachHangServices.getAll();
        int heso = (sotrang * 5) - 5;
        listkh = khachHangServices.getAll2(heso);

        loadDataTable(listkh);
        uppanel();
    }

    private void loadDataTable() {
        tblModel = (DefaultTableModel) tblKhachHang1.getModel();
        tblModel.setRowCount(0);

        for (edu.poly.duan1.model.KhachHang x : khachHangServices.getAll()) {
            tblModel.addRow(new Object[]{x.getId(), x.getMa(), x.getTen(), x.getDiaChi(), x.getSdt(), x.getNgayTao(), x.getNgaySua(), x.getTrangThai() == 1 ? "Còn" : "Hết"});
        }
    }

    private void loadDataTable(List<KhachHang> list) {
        tblModel = (DefaultTableModel) tblKhachHang1.getModel();
        tblModel.setRowCount(0);

        for (edu.poly.duan1.model.KhachHang x : list) {
            tblModel.addRow(new Object[]{x.getId(), x.getMa(), x.getTen(), x.getDiaChi(), x.getSdt(), x.getNgayTao(), x.getNgaySua(), x.getTrangThai() == 1 ? "Còn" : "Hết"});
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

        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhachHang1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtsearch1 = new edu.poly.duan1.swing.textfield.TextField();
        btnfirst = new javax.swing.JButton();
        btnback = new javax.swing.JButton();
        btnnext = new javax.swing.JButton();
        btnlast = new javax.swing.JButton();
        lblsotrang = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblKhachHang1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblKhachHang1);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Thông Tin Khách Hàng");

        txtsearch1.setBackground(java.awt.SystemColor.control);
        txtsearch1.setActionCommand("<Not Set>");
        txtsearch1.setCaretColor(new java.awt.Color(153, 153, 153));
        txtsearch1.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        txtsearch1.setLabelText("Tìm Kiếm");
        txtsearch1.setSelectedTextColor(new java.awt.Color(76, 204, 255));
        txtsearch1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtsearch1CaretUpdate(evt);
            }
        });
        txtsearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsearch1ActionPerformed(evt);
            }
        });

        btnfirst.setText("Fisrt");
        btnfirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfirstActionPerformed(evt);
            }
        });

        btnback.setText("Back");
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        btnnext.setText("Next");
        btnnext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnextActionPerformed(evt);
            }
        });

        btnlast.setText("Last");
        btnlast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlastActionPerformed(evt);
            }
        });

        lblsotrang.setText("label");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnfirst)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(lblsotrang, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(btnnext)
                                .addGap(71, 71, 71)
                                .addComponent(btnlast)))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(txtsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnfirst)
                    .addComponent(btnback)
                    .addComponent(btnnext)
                    .addComponent(btnlast)
                    .addComponent(lblsotrang))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtsearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsearch1ActionPerformed
        search();
    }//GEN-LAST:event_txtsearch1ActionPerformed

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        int tongSoTrang = list.size() / 5;
        if (list.size() % 5 != 0) {
            tongSoTrang += 1;
        }
        if (sotrang == 1) {
            loadDataTable(listkh);
        } else {
            listkh.removeAll(listkh);
            sotrang -= 1;
            int heso = (sotrang * 5) - 5;
            listkh = khachHangServices.getAll2(heso);
            loadDataTable(listkh);
            lblsotrang.setText(String.valueOf(sotrang) + "/" + tongSoTrang);
        }
    }//GEN-LAST:event_btnbackActionPerformed

    private void btnfirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfirstActionPerformed
        listkh.removeAll(listkh);
        sotrang = 1;
        int heso = (sotrang * 5) - 5;
        listkh = khachHangServices.getAll2(heso);
        loadDataTable(listkh);
        uppanel();
    }//GEN-LAST:event_btnfirstActionPerformed

    private void btnnextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnextActionPerformed
        int tongSoTrang = list.size() / 5;
        if (list.size() % 5 != 0) {
            tongSoTrang += 1;
        }
        if (sotrang == tongSoTrang) {
            loadDataTable(listkh);
        } else {
            listkh.removeAll(listkh);
            sotrang += 1;
            int heso = (sotrang * 5) - 5;
            listkh = khachHangServices.getAll2(heso);
            loadDataTable(listkh);
            lblsotrang.setText(String.valueOf(sotrang) + "/" + tongSoTrang);
        }
    }//GEN-LAST:event_btnnextActionPerformed

    private void btnlastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlastActionPerformed
        int tongSoTrang = list.size() / 5;
        if (list.size() % 5 != 0) {
            tongSoTrang += 1;
        }
        listkh.removeAll(listkh);
        sotrang = tongSoTrang;
        int heso = (sotrang * 5) - 5;
        listkh = khachHangServices.getAll2(heso);
        loadDataTable(listkh);
        lblsotrang.setText(String.valueOf(sotrang) + "/" + tongSoTrang);
    }//GEN-LAST:event_btnlastActionPerformed

    private void txtsearch1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtsearch1CaretUpdate
        search();
    }//GEN-LAST:event_txtsearch1CaretUpdate

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
            java.util.logging.Logger.getLogger(ViewNV_KH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewNV_KH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewNV_KH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewNV_KH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewNV_KH().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnback;
    private javax.swing.JButton btnfirst;
    private javax.swing.JButton btnlast;
    private javax.swing.JButton btnnext;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblsotrang;
    private javax.swing.JTable tblKhachHang1;
    private edu.poly.duan1.swing.textfield.TextField txtsearch1;
    // End of variables declaration//GEN-END:variables

    private void search() {
        String ten = txtsearch1.getText();
        if (ten.isEmpty()) {
            loadDataTable(list);
        } else {
            loadDataTable(khachHangServices.search(ten));
        }
    }

    private void uppanel() {
        int tongsotrang = list.size() / 5;
        if (list.size() % 5 != 0) {
            tongsotrang += 1;

        }
        lblsotrang.setText(String.valueOf(sotrang) + "/" + tongsotrang);
    }

}
