package edu.poly.duan1.view;

import edu.poly.duan1.model.HoaDon;
import edu.poly.duan1.model.HoaDonCT;
import edu.poly.duan1.model.Sach;
import edu.poly.duan1.model.SachCT;
import edu.poly.duan1.services.HoaDonCTService;
import edu.poly.duan1.services.HoaDonService;
import edu.poly.duan1.services.SachCTService;
import edu.poly.duan1.services.SachServices;
import edu.poly.duan1.services.impl.HoaDonCTServiceImpl;
import edu.poly.duan1.services.impl.HoaDonServiceImpl;
import edu.poly.duan1.services.impl.SachCTServiceImpl;
import edu.poly.duan1.services.impl.SachServicesImpl;
import edu.poly.duan1.ultis.helper;
import edu.poly.main.Main;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AnhTiTan
 */
public class view_Ban_Hang extends javax.swing.JFrame {

    /**
     * Creates new form view_Ban_Hang
     */
    private DefaultTableModel tblModel;
    private SachCTService sachCTService = new SachCTServiceImpl();
    private HoaDonService hoaDonService = new HoaDonServiceImpl();
    private HoaDonCTService hoaDonCTService = new HoaDonCTServiceImpl();
    private helper helper = new helper();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public view_Ban_Hang() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        tblHD.fixTable(jScrollPane3);
        tblGioHang.fixTable(jScrollPane5);
        tblSanPham.fixTable(jScrollPane6);
        LoadData();
        List<HoaDonCT> listGH = new ArrayList<>();
        GroupTT();
        rdTatCa.setSelected(true);
    }

    private void loadDataTaSach(List<SachCT> list) {
        tblModel = (DefaultTableModel) tblSanPham.getModel();
        tblModel.setColumnIdentifiers(new String[]{"STT", "Mã", "Tên Sách", "SLT", "Giá bán", "Mô tả"});
        tblModel.setRowCount(0);
        int i = 1;
        for (SachCT x : list) {
            tblModel.addRow(new Object[]{
                i++,
                x.getSach().getMa(),
                x.getSach().getTen(),
                x.getSoLuongTon(),
                x.getGiaBan(),
                x.getMoTa()
            });
        }
    }

    private void loadDataHoaDon(List<HoaDon> list) {
        tblModel = (DefaultTableModel) tblHD.getModel();
        tblModel.setColumnIdentifiers(new String[]{"STT", "Mã HD", "Ngày tạo", "Trạng thái"});
        tblModel.setRowCount(0);
        int i = 1;
        for (HoaDon x : list) {
            tblModel.addRow(new Object[]{
                i++,
                x.getMa(),
                //                x.getNguoiDung().getHoTen(),
                sdf.format(x.getNgayTao()),
                x.getTrangThai() == 1 ? "Đã thanh toán" : "Chưa thanh toán"
            });
        }
    }

    private void loadDataGH(List<HoaDonCT> listGH) {
        tblModel = (DefaultTableModel) tblGioHang.getModel();
        tblModel.setColumnIdentifiers(new String[]{"STT", "Mã HD", "Tên sách", "Số lượng", "Giá bán", "Thành tiền"});
        tblModel.setRowCount(0);
        int i = 1;
        for (HoaDonCT x : listGH) {
            tblModel.addRow(new Object[]{
                i++,
                x.getHoaDon().getMa(),
                x.getSachCT().getSach().getTen(),
                x.getSoLuong(),
                x.getDonGia(),
                x.getSoLuong() * x.getDonGia(),
                x.ThanhTien(x.getSoLuong(), x.getDonGia())});
        }
    }

    private void loadDataGH() {
        tblModel = (DefaultTableModel) tblGioHang.getModel();
        tblModel.setColumnIdentifiers(new String[]{"STT", "Mã HD", "Tên sách", "Số lượng", "Giá bán", "Thành tiền"});
        tblModel.setRowCount(0);

    }

    private void LoadData() {
        loadDataTaSach(sachCTService.getAll());
        loadDataHoaDon(hoaDonService.getAll());
        loadDataGH();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radioButtonCustom1 = new edu.poly.duan1.swing.radio_button.RadioButtonCustom();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        textField7 = new edu.poly.duan1.swing.textfield.TextField();
        roundPanel3 = new edu.poly.duan1.swing.scrollbar.roundpanel.RoundPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblSanPham = new edu.poly.duan1.swing.table.Table();
        jPanel3 = new javax.swing.JPanel();
        roundPanel2 = new edu.poly.duan1.swing.scrollbar.roundpanel.RoundPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblGioHang = new edu.poly.duan1.swing.table.Table();
        jPanel2 = new javax.swing.JPanel();
        btnTaoHD = new edu.poly.swing.Button();
        rdChuaTT = new edu.poly.duan1.swing.radio_button.RadioButtonCustom();
        rdDaTT = new edu.poly.duan1.swing.radio_button.RadioButtonCustom();
        rdTatCa = new edu.poly.duan1.swing.radio_button.RadioButtonCustom();
        roundPanel1 = new edu.poly.duan1.swing.scrollbar.roundpanel.RoundPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHD = new edu.poly.duan1.swing.table.Table();
        jPanel4 = new javax.swing.JPanel();
        btnThanhToan = new edu.poly.swing.Button();
        txtMaHD = new edu.poly.duan1.swing.textfield.TextField();
        txtNgayTao = new edu.poly.duan1.swing.textfield.TextField();
        txtNhanVien = new edu.poly.duan1.swing.textfield.TextField();
        txtTongTien = new edu.poly.duan1.swing.textfield.TextField();
        txtTienKhachDua = new edu.poly.duan1.swing.textfield.TextField();
        txtTienThua = new edu.poly.duan1.swing.textfield.TextField();
        button3 = new edu.poly.duan1.swing.button.Button();

        radioButtonCustom1.setText("radioButtonCustom1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản phẩm"));

        textField7.setLabelText("Tìm Kiếm");

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblSanPham);

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(textField7, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(textField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng"));

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
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
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblGioHang);

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn"));

        btnTaoHD.setBackground(new java.awt.Color(204, 204, 204));
        btnTaoHD.setText("Tạo Hóa Đơn");
        btnTaoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHDActionPerformed(evt);
            }
        });

        rdChuaTT.setText("Chưa thanh toán");
        rdChuaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdChuaTTActionPerformed(evt);
            }
        });

        rdDaTT.setText("Đã thanh toán");
        rdDaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdDaTTActionPerformed(evt);
            }
        });

        rdTatCa.setText("Tất cả");
        rdTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdTatCaActionPerformed(evt);
            }
        });

        tblHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHD.setSelectionBackground(new java.awt.Color(153, 204, 255));
        tblHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHD);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnTaoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(rdChuaTT, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(rdDaTT, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdDaTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdChuaTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaoHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn"));

        btnThanhToan.setBackground(new java.awt.Color(204, 204, 204));
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        txtMaHD.setLabelText("Mã HD");

        txtNgayTao.setLabelText("Ngày tạo");

        txtNhanVien.setLabelText("Tên NV");

        txtTongTien.setLabelText("Tổng tiền");

        txtTienKhachDua.setLabelText("Tiền khách đưa");
        txtTienKhachDua.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienKhachDuaCaretUpdate(evt);
            }
        });

        txtTienThua.setLabelText("Tiền thừa");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(174, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtMaHD, txtNgayTao, txtNhanVien, txtTienKhachDua, txtTienThua, txtTongTien});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtMaHD, txtNgayTao, txtNhanVien, txtTienKhachDua, txtTienThua, txtTongTien});

        button3.setBackground(new java.awt.Color(204, 204, 204));
        button3.setText("Trở về màn hình chính");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void taoHoaDon() {
        HoaDon hd = new HoaDon();
        String result;
        hd.setNgayTao(java.sql.Date.valueOf(LocalDate.now()));
        for (int i = 0; i < hoaDonService.getAll().size() + 1; i++) {
            result = "HD" + i;
            if (hoaDonService.getObjbyMa(result) == null) {
                hd.setMa(result);
                break;
            } else {
                continue;
            }
        }
        if (hoaDonService.saveOrUpdate(hd)) {
            helper.alert(this, "Thêm Hoá Đơn Thành Công");
        } else {
            helper.error(this, "Thêm hoá đơn mới thất bại");

        }
        loadDataHoaDon(hoaDonService.getAll());
    }
    private void btnTaoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHDActionPerformed
        taoHoaDon();
    }//GEN-LAST:event_btnTaoHDActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked

        try {

            int row = tblHD.getSelectedRow();
            int rowSP = tblSanPham.getSelectedRow();
            HoaDon hd = hoaDonService.getObjbyMa((String) tblHD.getValueAt(row, 1));
            SachCT sctt = sachCTService.getAll().get(rowSP);
            SachCT sct = new SachCT();
            sct = sachCTService.getObjbyID(sctt.getId());
            HoaDonCT hdct = hoaDonCTService.getObj(hd.getId(), sct.getId());

            if (sctt.getSoLuongTon() == 0) {
                helper.error(this, "da het hang");
                return;
            }
            String input = JOptionPane.showInputDialog("Nhập số lượng");
            Integer soLuong = Integer.parseInt(input);
            if (hdct != null) {

                hdct.setSoLuong(hdct.getSoLuong() + soLuong);
                sct.setSoLuongTon(sct.getSoLuongTon() - soLuong);
            } else {
                hdct = new HoaDonCT();
                hdct.setHoaDon(hd);
                hdct.setSachCT(sct);
                hdct.setDonGia(sct.getGiaBan().doubleValue());
                hdct.setSoLuong(hdct.getSoLuong() + soLuong);
                sct.setSoLuongTon(sct.getSoLuongTon() - soLuong);
            }
            sachCTService.saveOrUpdate(sct);
            hoaDonCTService.saveOrUpdate(hdct);
            loadDataTaSach(sachCTService.getAll());
            loadDataGH((List<HoaDonCT>) hoaDonCTService.getObjbyMa((String) tblHD.getValueAt(row, 1)));

        } catch (Exception e) {
        }
//        BigDecimal tong = null;
        Double tongTien = 0.0;
        // Tổng tiền
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            try {
                tongTien = tongTien + Double.parseDouble(tblGioHang.getValueAt(i, 5).toString());
            } catch (Exception e) {
            }
        }
        txtTongTien.setText(tongTien.toString());
        //  BigDecimal tienKhach = BigDecimal.valueOf(Double.parseDouble(txtTienKhachDua_hd.getText()));
        // BigDecimal tongTien = (BigDecimal) tbHoaDonChiTiet.getValueAt(row, 4);
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void tblHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDMouseClicked
        int row = tblHD.getSelectedRow();
        HoaDon hd = hoaDonService.getObjbyMa((String) tblHD.getValueAt(row, 1));
        loadDataGH((List<HoaDonCT>) hoaDonCTService.getObjbyMa((String) tblHD.getValueAt(row, 1)));
        txtMaHD.setText(hd.getMa());
        txtNgayTao.setText(sdf.format(hd.getNgayTao()) + "");
        Double tongTien = 0.0;
        // Tổng tiền
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {

            try {
                tongTien = tongTien + Double.parseDouble(tblGioHang.getValueAt(i, 5).toString());
            } catch (Exception e) {
            }

        }
        txtTongTien.setText(tongTien.toString());
    }//GEN-LAST:event_tblHDMouseClicked

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        new Main().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_button3ActionPerformed

    private void txtTienKhachDuaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienKhachDuaCaretUpdate
        Double tongTien = 0.0;
        Double tienKhachDua = 0.0;
        Double tienThua = 0.0;
        try {
            tongTien = Double.parseDouble(txtTongTien.getText().toString());
            tienKhachDua = Double.parseDouble(txtTienKhachDua.getText());
//            tienThua = tienThua - tongTien;
        } catch (Exception e) {
        }
        if (tienKhachDua - tongTien <= 0.0) {
            txtTienThua.setText(0 + "");
        } else {
            txtTienThua.setText(tienKhachDua - tongTien + "");
        }
    }//GEN-LAST:event_txtTienKhachDuaCaretUpdate

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        Double tongTien = 0.0;
        Double tienKhachDua = 0.0;
        Double tienThua = 0.0;
        try {
            tongTien = Double.parseDouble(txtTongTien.getText().toString());
            tienKhachDua = Double.parseDouble(txtTienKhachDua.getText());
//            tienThua = tienThua - tongTien;
        } catch (Exception e) {
        }
        if (tienKhachDua - tongTien <= 0.0) {
            txtTienThua.setText(0 + "");
        } else {
            txtTienThua.setText(tienKhachDua - tongTien + "");
        }
        int row = tblHD.getSelectedRow();

        HoaDon hd = hoaDonService.getObjbyMa((String) tblHD.getValueAt(row, 1));
        if (hd.getTrangThai() == 1) {
            helper.error(this, "Đơn hàng này đã được thanh toán");
            return;
        }
        if (tienKhachDua - tongTien >= 0) {
            hd.setTrangThai(1);
            if (hoaDonService.saveOrUpdate(hd)) {
                helper.alert(this, "thanh toan thanh cong");
                tblModel = (DefaultTableModel) tblGioHang.getModel();
                tblModel.setRowCount(0);
            } else {
                helper.error(this, "thanh toan that bai");
            }

        }
        LoadData();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        String input = JOptionPane.showInputDialog("Nhập số lượng");
        Integer soLuong = Integer.parseInt(input);
        try {
            int row = tblGioHang.getSelectedRow();
            HoaDonCT hdct = hoaDonCTService.getAll().get(row);
            hdct.setSoLuong(soLuong);
            hoaDonCTService.saveOrUpdate(hdct);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblGioHangMouseClicked

    private void rdChuaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdChuaTTActionPerformed
        loadDataHoaDon(hoaDonService.search(0));
    }//GEN-LAST:event_rdChuaTTActionPerformed

    private void rdDaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdDaTTActionPerformed
        loadDataHoaDon(hoaDonService.search(1));
    }//GEN-LAST:event_rdDaTTActionPerformed

    private void rdTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdTatCaActionPerformed
        loadDataHoaDon(hoaDonService.getAll());
    }//GEN-LAST:event_rdTatCaActionPerformed

    ButtonGroup buttonGroup1 = new ButtonGroup();

    private void GroupTT() {
        buttonGroup1.add(rdChuaTT);
        buttonGroup1.add(rdDaTT);
        buttonGroup1.add(rdTatCa);
    }

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
            java.util.logging.Logger.getLogger(view_Ban_Hang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(view_Ban_Hang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(view_Ban_Hang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(view_Ban_Hang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new view_Ban_Hang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private edu.poly.swing.Button btnTaoHD;
    private edu.poly.swing.Button btnThanhToan;
    private edu.poly.duan1.swing.button.Button button3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private edu.poly.duan1.swing.radio_button.RadioButtonCustom radioButtonCustom1;
    private edu.poly.duan1.swing.radio_button.RadioButtonCustom rdChuaTT;
    private edu.poly.duan1.swing.radio_button.RadioButtonCustom rdDaTT;
    private edu.poly.duan1.swing.radio_button.RadioButtonCustom rdTatCa;
    private edu.poly.duan1.swing.scrollbar.roundpanel.RoundPanel roundPanel1;
    private edu.poly.duan1.swing.scrollbar.roundpanel.RoundPanel roundPanel2;
    private edu.poly.duan1.swing.scrollbar.roundpanel.RoundPanel roundPanel3;
    private edu.poly.duan1.swing.table.Table tblGioHang;
    private edu.poly.duan1.swing.table.Table tblHD;
    private edu.poly.duan1.swing.table.Table tblSanPham;
    private edu.poly.duan1.swing.textfield.TextField textField7;
    private edu.poly.duan1.swing.textfield.TextField txtMaHD;
    private edu.poly.duan1.swing.textfield.TextField txtNgayTao;
    private edu.poly.duan1.swing.textfield.TextField txtNhanVien;
    private edu.poly.duan1.swing.textfield.TextField txtTienKhachDua;
    private edu.poly.duan1.swing.textfield.TextField txtTienThua;
    private edu.poly.duan1.swing.textfield.TextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
