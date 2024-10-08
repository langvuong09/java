package QuanLyThuVien.DAO;

import QuanLyThuVien.DTO.PhieuTra;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PhieuTraDAO {
    public ArrayList<PhieuTra> getListPhieuTra(){
        try {
            ArrayList<PhieuTra> dspt = new ArrayList<>();
            String sql = "SELECT * FROM phieutra";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                PhieuTra pt = new PhieuTra();
                pt.setMaPhieuTra(rs.getInt(1));
                pt.setMaPhieuMuon(rs.getInt(2));
                pt.setMaDocGia(rs.getInt(3));
                pt.setMaNhanVien(rs.getInt(4));
                pt.setNgayTraThuc(rs.getDate(5));
                dspt.add(pt);
            }
            return dspt;
        }catch (SQLException e){
        }
        return null;
    }

    public PhieuTra getPhieuTra(int ma){
        PhieuTra pt = null;
        try{
            String sql = "SELECT * FROM phieutra WHERE MaPhieuTra="+ma;
            Statement st =MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                pt = new PhieuTra();
                pt.setMaPhieuTra(rs.getInt(1));
                pt.setMaPhieuMuon(rs.getInt(2));
                pt.setMaDocGia(rs.getInt(3));
                pt.setMaNhanVien(rs.getInt(4));
                pt.setNgayTraThuc(rs.getDate(5));
            }
            return pt;
        }catch (SQLException e){
        }
        return null;
    }

    public boolean themPhieuTra(PhieuTra pt){
        try{
            String sql = "INSERT INTO phieutra VALUES(?,?,?,?,?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            // Chuyển java.util.Date thành java.sql.Date
            java.sql.Date ngayTraThuc = new java.sql.Date(pt.getNgayTraThuc().getTime());

            pre.setInt(1,pt.getMaPhieuTra());
            pre.setInt(2,pt.getMaPhieuMuon());
            pre.setInt(3,pt.getMaDocGia());
            pre.setInt(4,pt.getMaNhanVien());
            pre.setDate(5,ngayTraThuc);
            return pre.executeUpdate() > 0;
        }catch (SQLException e){
        }
        return false;
    }

    public boolean xoaPhieuTra(int ma){
        try{
            String sqlCheck = "SET FOREIGN_KEY_CHECKS=0";
            Statement stCheck = MyConnect.conn.createStatement();
            stCheck.execute(sqlCheck);

            String sql = "DELETE FROM phieutra WHERE MaPhieuTra="+ma;
            Statement st = MyConnect.conn.createStatement();
            st.execute(sql);

            String sqlChecks = "SET FOREIGN_KEY_CHECKS=1";
            Statement stChecks = MyConnect.conn.createStatement();
            stChecks.execute(sqlChecks);
            return true;
        }catch (SQLException e){
        }
        return false;
    }

    public int getMaPhieuTraMoiNhat(){
        try{
            String sql = "SELECT MAX(MaPhieuTra) FROM phieutra";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch (SQLException e){
        }
        return -1;
    }

    public boolean nhapPhieuTraTuExcel(PhieuTra pt){
        try{
            String sql = "DELETE * FROM phieutra; "+
                    "INSERT INTO phieutra(MaPhieuMuon, MaDocGia, MaNhanVien, NgayTraThuc) " +
                    "VALUES (?,?,?,?)";

            java.sql.Date ngayTraThuc = new java.sql.Date(pt.getNgayTraThuc().getTime());

            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,pt.getMaPhieuMuon());
            pre.setInt(2,pt.getMaDocGia());
            pre.setInt(3,pt.getMaNhanVien());
            pre.setDate(4,ngayTraThuc);

            pre.execute();
            return true;
        }catch (SQLException e){
        }
        return false;
    }

    public int sachTrongPhieuMuon(int maPM){
        try {
            String sql = "SELECT \n" +
                    "    (SELECT COUNT(*) \n" +
                    "     FROM ctphieumuon ctpm \n" +
                    "     WHERE ctpm.MaPhieuMuon = " + maPM + ") AS TongSoSachMuon,\n" +
                    "     \n" +
                    "    (SELECT COUNT(*) \n" +
                    "     FROM ctphieutra ctpt \n" +
                    "     JOIN phieutra pt ON ctpt.MaPhieuTra = pt.MaPhieuTra\n" +
                    "     WHERE pt.MaPhieuMuon = " + maPM + ") AS TongSoSachTra,\n" +
                    "\n" +
                    "    ( (SELECT COUNT(*) \n" +
                    "       FROM ctphieumuon ctpm \n" +
                    "       WHERE ctpm.MaPhieuMuon = " + maPM + ") \n" +
                    "     - \n" +
                    "     (SELECT COUNT(*) \n" +
                    "      FROM ctphieutra ctpt \n" +
                    "      JOIN phieutra pt ON ctpt.MaPhieuTra = pt.MaPhieuTra\n" +
                    "      WHERE pt.MaPhieuMuon = " + maPM + ") ) AS SoSachChuaTra;\n";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                return rs.getInt(3);
            }else {
                return -1;
            }
        }catch (SQLException e){
            return -1;
        }
    }

    public ArrayList<PhieuTra> timKiemTheoKhoang(Date min, Date max){
        try{
            String sql = "SELECT * FROM phieutra, phieumuon WHERE phieutra.MaPhieuMuon = phieumuon.MaPhieuMuon" +
                    "AND phieumuon.NgayMuon BETWEEN ? AND ?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ArrayList<PhieuTra> dspt = new ArrayList<>();
            java.sql.Date Min = new java.sql.Date(min.getTime());
            java.sql.Date Max = new java.sql.Date(max.getTime());
            pre.setDate(1, Max);
            pre.setDate(2, Min);
            ResultSet rs =  pre.executeQuery();
            while (rs.next()) {
                PhieuTra pt = new PhieuTra();
                pt.setMaPhieuTra(rs.getInt(1));
                pt.setMaPhieuMuon(rs.getInt(2));
                pt.setMaDocGia(rs.getInt(3));
                pt.setMaNhanVien(rs.getInt(4));
                pt.setNgayTraThuc(rs.getDate(5));
                dspt.add(pt);
            }
            return dspt;
        }catch (SQLException e){
        }
        return null;
    }
}
