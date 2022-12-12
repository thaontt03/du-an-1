package edu.poly.duan1.swing.table;

import edu.poly.duan1.swing.scrollbar.ScrollBarCustomUI;
import edu.poly.duan1.swing.textarea.TextAreaCellRenderer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;

public class Table1 {
    
    public static void apply(JScrollPane scroll, TableType type) {
        JTable table = (JTable) scroll.getViewport().getComponent(0);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setDefaultRenderer(new TableHeaderCustomCellRender(table));
        table.setRowHeight(30);
        HoverIndex hoverRow = new HoverIndex();
        TableCellRenderer cellRender;
        if (type == TableType.DEFAULT) {
//            cellRender = new TableCustomCellRender(hoverRow);
        } else {
            cellRender = new TextAreaCellRenderer(hoverRow);
        }
//        table.setDefaultRenderer(Object.class, cellRender);
        table.setShowVerticalLines(true);
        table.setSelectionBackground(new Color(76, 204, 255)); //
        table.setGridColor(new Color(220, 220, 220));
        table.setForeground(new Color(51, 51, 51)); ///
        table.setSelectionForeground(new Color(51, 51, 51));
        scroll.setBorder(new LineBorder(new Color(220, 220, 220)));
        JPanel panel = new JPanel() {
            @Override
            public void paint(Graphics grphcs) {
                super.paint(grphcs);
                grphcs.setColor(new Color(211, 220, 220)); //
                grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
                grphcs.dispose();
            }
        };
        panel.setBackground(new Color(250, 250, 250));
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.getVerticalScrollBar().setUI(new ScrollBarCustomUI());
        scroll.getHorizontalScrollBar().setUI(new ScrollBarCustomUI());
        table.getTableHeader().setBackground(new Color(250, 250, 250)); // color header
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                hoverRow.setIndex(-1);
                table.repaint();
            }
            
        });
        table.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row != hoverRow.getIndex()) {
                    hoverRow.setIndex(row);
                    table.repaint();
                }
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row != hoverRow.getIndex()) {
                    hoverRow.setIndex(row);
                    table.repaint();
                }
            }
        });
    }
    
    public static enum TableType {
        MULTI_LINE, DEFAULT
    }
}
/*
int row = tb_sanpham.getSelectedRow();
        int rowHD = tb_hoadon.getSelectedRow();
        String inPutSL = null;
        Integer soLuongNhap;
        if (rowHD == -1) {
            tb_sanpham.clearSelection();
            NotificationMess panel = new NotificationMess(new FrmHome(), NotificationMess.Type.ERROR, NotificationMess.Location.TOP_CENTER, "Bạn chưa chọn hóa đơn!");
            panel.showNotification();
        } else {
            HoaDon hd = iHoaDonService.getObj((String) tb_hoadon.getValueAt(rowHD, 0));
            ChiTietDep ctd = iChiTietDepService.findByTT(0, txt_sp_timkiem.getText(), "DESC").get(row);
            if (hd.getTrangThai() == 1) {
                tb_sanpham.clearSelection();
                NotificationMess panel = new NotificationMess(new FrmHome(), NotificationMess.Type.ERROR, NotificationMess.Location.TOP_CENTER, "Hóa đơn đã được thanh toán!");
                panel.showNotification();
                return;
            }
            if (ctd.getSoLuong() == 0) {
                tb_sanpham.clearSelection();
                NotificationMess panel = new NotificationMess(new FrmHome(), NotificationMess.Type.ERROR, NotificationMess.Location.TOP_CENTER, "Sản phẩm này đã hết hàng");
                panel.showNotification();
                return;
            }
            inPutSL = helper.input(this, "Vui lòng nhập số lượng: ", "Số lượng");
            try {
                soLuongNhap = Integer.parseInt(inPutSL);
                if (soLuongNhap <= 0) {
                    NotificationMess panel = new NotificationMess(new FrmHome(), NotificationMess.Type.WARNING, NotificationMess.Location.TOP_CENTER, "Vui lòng nhập lại!");
                    panel.showNotification();
                    return;
                } else if (soLuongNhap > ctd.getSoLuong()) {
                    NotificationMess panel = new NotificationMess(new FrmHome(), NotificationMess.Type.WARNING, NotificationMess.Location.TOP_CENTER, "Quá số lượng cho phép !");
                    panel.showNotification();
                    return;
                }
            } catch (Exception e) {
                NotificationMess panel = new NotificationMess(new FrmHome(), NotificationMess.Type.WARNING, NotificationMess.Location.TOP_CENTER, "Vui lòng nhập lại !");
                panel.showNotification();
                return;
            }
            if (iHoaDonCTService.getobj(ctd.getId(), hd.getId()) == null) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setCtdep(ctd);
                hdct.setHoaDon(hd);
                hdct.setSoLuong(soLuongNhap);
                hdct.setDonGia(ctd.getGiaBan());
                hdct.setTrangThai(0);
                ctd.setSoLuong(ctd.getSoLuong() - soLuongNhap);
                iChiTietDepService.save(ctd);
                iHoaDonCTService.save(hdct);
            } else {
HoaDonChiTiet hdct = iHoaDonCTService.getobj(ctd.getId(), hd.getId());
                hdct.setSoLuong(hdct.getSoLuong() + soLuongNhap);
                ctd.setSoLuong(ctd.getSoLuong() - soLuongNhap);
                iChiTietDepService.save(ctd);
                iHoaDonCTService.save(hdct);
            }
            loadSP(iChiTietDepService.findByTT(0, txt_sp_timkiem.getText(), "DESC"));
            loadGioHang(hd.getMa());
        }
        tongTien();

 */
