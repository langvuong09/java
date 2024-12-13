package QuanLyThuVien.GUI;

import QuanLyThuVien.BUS.CTPhieuMuonBUS;
import QuanLyThuVien.BUS.PhanSachBUS;
import QuanLyThuVien.BUS.PhieuMuonBUS;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;

public class XuatPhieuMuonGUI extends JDialog{
    private PhieuMuonBUS phieuMuonBUS = new PhieuMuonBUS();
    private CTPhieuMuonBUS ctPhieuMuonBUS = new CTPhieuMuonBUS();
    private PhanSachBUS phanSachBUS = new PhanSachBUS();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnInPhieuMuon;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JScrollPane jScrollPane1;
    private JEditorPane txtPhieuMuon;

    public XuatPhieuMuonGUI() {
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
//        this.setSize(700,500);
        Image icon = Toolkit.getDefaultToolkit().getImage("image/ManagerUI/icon-app.png");
        this.setIconImage(icon);
//        customEvents();
    }

    private ArrayList<Vector> dsCTPhieuMuon;
    private int maPM;
    private String nhanVien, docGia;
    private String ngayMuon, ngayTra;
    private long tongTien;

    public XuatPhieuMuonGUI(ArrayList<Vector> dsCTPhieuMuon, int maPM, Object docGia, Object nhanVien, String ngayMuon, String ngayTra, long tongTien) {
        this();
        this.maPM = maPM;
        this.docGia = (String) docGia;
        this.nhanVien = (String) nhanVien;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.tongTien = tongTien;
        this.dsCTPhieuMuon = dsCTPhieuMuon;
        xuLyHienThiPhieuMuon();
    }

    private void xuLyHienThiPhieuMuon() {
        txtPhieuMuon.setContentType("text/html");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        DecimalFormat dcf = new DecimalFormat("###,### VND");

        String hd = "<style> "
                + "table {"
                + "border: 1px solid;"
                + "border-collapse: collapse;"
                + "border-bottom: none"
                + "}"
                + "tr {"
                + "border-bottom: 1px solid;"
                + "}"
                + "td {"
                + "padding: 8px;"
                + "} "
                + "th {"
                + "font-size:20px"
                + "}"
                + "</style>";
        hd += "<h1 style='text-align:center;'>PHIẾU MƯỢN SÁCH</h1>";
        hd += "<table style='width: 80%; margin: auto; border: none'>"; // Thiết lập chiều rộng là 80% của phần tử cha và canh giữa bảng
        hd += "<tr style='border: none'>";
        hd += "<td style='vertical-align: top;'>";
        hd += "<pre>Mã phiếu: "+maPM+"<br/></pre>";
        hd += "<pre>Nhân viên: " + nhanVien + "<br/>";
        hd += "Đọc giả: " + docGia + "</pre>";
        hd += "</td>";
        hd += "<td style='vertical-align: top;'>";
        hd += "<pre>                <br/></pre>";
        hd += "<pre>    Ngày mượn: " + ngayMuon + "<br/>";
        hd += "    Ngày trả: " + ngayTra + "</pre>";
        hd += "</td>";
        hd += "</tr>";
        hd += "</table>";
        hd += "<div style='text-align:center'>";
        hd += "<table style='max-width:100%; margin: auto;'>";
        hd += "<tr style='font-family: Tahoma; font-size: 14px;'>"
                + "<th>Mã Sách</th>"
                + "<th>Mã Phân Sách</th>"
                + "<th>Tên Sách</th>"
                + "<th>Tiền mượn</th>"
                + "<th>Trạng thái</th>"
                + "</tr>";
        for (Vector vec : dsCTPhieuMuon) {
            hd += "<tr>";
            hd += "<td style='text-align:center;'>" + vec.get(0) + "</td>";
            hd += "<td style='text-align:center;'>" + vec.get(1) + "</td>";
            hd += "<td style='text-align:center;'>" + vec.get(2) + "</td>";
            hd += "<td style='text-align:center;'>" + vec.get(3) + "</td>";
            hd += "<td style='text-align:center;'>" + phanSachBUS.trangThaiSach(String.valueOf(vec.get(0)),String.valueOf(vec.get(1))) + "</td>";
            hd += "</tr>";
        }
        hd += "<tr>";
        hd += "<td style='text-align:center;'>" + "</td>";
        hd += "<td style='text-align:center;font-weight:bold'>Tổng tiền mượn: </td>";
        hd += "<td style='text-align:center;'>" + dcf.format(tongTien) + "</td>";
        hd += "</tr>";
        hd += "</table>";
        hd += "</div>";
        hd += "<div><pre>  </pre></div>";
        hd += "<div><pre>================================================================= </pre></div>";
        hd += "<div><pre>  </pre></div>";
        txtPhieuMuon.setText(hd);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    public void initComponents(){
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jPanel3 = new JPanel();
        jLabel1 = new JLabel();
        jScrollPane1 = new JScrollPane();
        txtPhieuMuon = new JEditorPane();
        btnInPhieuMuon = new JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Phiếu mượn sách");
        jPanel1.add(jLabel1);

        btnInPhieuMuon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnInPhieuMuon.setText("In hoá đơn");
        btnInPhieuMuon.setPreferredSize(new java.awt.Dimension(128, 45));
        btnInPhieuMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInPhieuMuonActionPerformed(evt);
            }
        });
        jPanel2.add(btnInPhieuMuon);

        jScrollPane1.setViewportView(txtPhieuMuon);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE,  611, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup())
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 0 , Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING))
                                .addGap(38, 38, 38)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 335, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        pack();
    }

    private void btnInPhieuMuonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (!txtPhieuMuon.getText().equals("")) {
                txtPhieuMuon.print();
                this.dispose();
            }
        } catch (PrinterException ex) {
        }
    }

    // End of variables declaration//GEN-END:variables
}
