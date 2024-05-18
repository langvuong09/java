package QuanLyThuVien.DAO;

import QuanLyThuVien.DTO.Sach;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SachDAO {

    public ArrayList<Sach> getListSach(){
        try{
            String sql = "SELECT * FROM sach";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();

            ArrayList<Sach> dss = new ArrayList<>();
            while (rs.next()){
                Sach s = new Sach();
                s.setMaSach(rs.getInt(1));
                s.setMaLoaiSach(rs.getInt(2));
                s.setMaTacGia(rs.getInt(3));
                s.setTenSach(rs.getString(4));
                s.setGiaSach(rs.getLong(5));
                s.setGhiChu(rs.getString(6));
                s.setSoLuong(rs.getInt(7));
                dss.add(s);
            }
            return dss;
        }catch (SQLException e){
        }
        return null;
    }

    public Sach getSach(int ma){
        Sach s = null;
        try{
            String sql = "SELECT * FROM sach WHERE MaSach="+ma;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                s = new Sach();
                s.setMaSach(rs.getInt(1));
                s.setMaLoaiSach(rs.getInt(2));
                s.setMaTacGia(rs.getInt(3));
                s.setTenSach(rs.getString(4));
                s.setGiaSach(rs.getLong(5));
                s.setGhiChu(rs.getString(6));
                s.setSoLuong(rs.getInt(7));
            }
        }catch (SQLException e){
            return null;
        }
        return s;
    }

    public boolean themSach(Sach s){
        boolean result = false;
        try{
            String sql = "INSERT INTO sach VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,s.getMaSach());
            pre.setInt(2,s.getMaLoaiSach());
            pre.setInt(3,s.getMaTacGia());
            pre.setString(4,s.getTenSach());
            pre.setLong(5,s.getGiaSach());
            pre.setString(6,s.getGhiChu());
            pre.setInt(7,s.getSoLuong());
            result = pre.executeUpdate() > 0;
        }catch (SQLException e){
            return false;
        }
        return result;
    }

    public boolean xoaSach(int maSach){
        try{
            String sql = "DELETE  FROM sach WHERE MaSach="+maSach;
            Statement st = MyConnect.conn.createStatement();
            st.execute(sql);
            return true;
        }catch (SQLException e){
        }
        return  false;
    }

    public boolean suaSach(Sach s){
        boolean result = false;
        try{
            String sql = "UPDATE sach SET MaLoai=?, MaTacGia=?, TenSach=?, GiaSach=?, GhiChu=?, SoLuong=? WHERE MaSach=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,s.getMaLoaiSach());
            pre.setInt(2,s.getMaTacGia());
            pre.setString(3,s.getTenSach());
            pre.setLong(4,s.getGiaSach());
            pre.setString(5,s.getGhiChu());
            pre.setInt(6,s.getSoLuong());
            pre.setInt(7,s.getMaSach());
            result = pre.executeUpdate() > 0;
        }catch (SQLException e){
            return false;
        }
        return result;
    }

    public boolean chonSach(String maSach){
        boolean result = false;
        try{
            String sql = "UPDATE sach SET SoLuong = SoLuong - 1 WHERE MaSach=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            int maS = Integer.parseInt(maSach);
            pre.setInt(1,maS);
            result = pre.executeUpdate() > 0;
        }catch (SQLException e){
            return false;
        }
        return result;
    }

    public void capNhatTrangThaiSach(String ma) {
        try {
            String sql = "UPDATE sach SET SoLuong = SoLuong + 1 WHERE MaSach="+ma;
            Statement st = MyConnect.conn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean nhapSachTuExcel(Sach s){
        try{
            String sql = "DELETE * FROM sach; "+
                    "INSERT INTO sach(MaSach,MaLoai,MaTacGia,TenSach,GiaSach,GhiChu,SoLuong"+
                    "VALUES (?,?,?,?,?,?,?)";

            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,s.getMaSach());
            pre.setInt(2,s.getMaLoaiSach());
            pre.setInt(3,s.getMaTacGia());
            pre.setString(4,s.getTenSach());
            pre.setLong(5,s.getGiaSach());
            pre.setString(6,s.getGhiChu());
            pre.setInt(7,s.getSoLuong());
            return pre.executeUpdate() > 0;
        }catch (SQLException e){
        }
        return false;
    }
}
