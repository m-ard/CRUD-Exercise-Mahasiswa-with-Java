/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Connection.ConnectionDB;
import DAOInterface.IDAOMahasiswa;
import Model.Mahasiswa;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ardab
 */
public class DAOMahasiswa implements IDAOMahasiswa{
    
    public DAOMahasiswa(){
        con = ConnectionDB.getConnection();
    }
            
    @Override
    public List<Mahasiswa> getAll() {
        List<Mahasiswa> lstMhs = null;
        try {
            lstMhs = new ArrayList<Mahasiswa>();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                Mahasiswa mhs = new Mahasiswa();
                mhs.setId(rs.getInt("id"));
                mhs.setNim(rs.getString("nim"));
                mhs.setNama(rs.getString("nama"));
                mhs.setJk(rs.getString("jk"));
                mhs.setAlamat(rs.getString("alamat"));
                lstMhs.add(mhs);
            }
        }        
        catch(SQLException ex){
            System.out.println("error"+ex);
        }
        return lstMhs;
    }

    @Override
    public List<Mahasiswa> getCariNama(String Nama) {
        List<Mahasiswa> lstMhs = null;
        try {
            lstMhs = new ArrayList<Mahasiswa>();
            PreparedStatement st = con.prepareStatement(carinama);
            st.setString(1,"%"+Nama+"%");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Mahasiswa mhs = new Mahasiswa();
                mhs.setId(rs.getInt("id"));
                mhs.setNim(rs.getString("nim"));
                mhs.setNama(rs.getString("nama"));
                mhs.setJk(rs.getString("jk"));
                mhs.setAlamat(rs.getString("alamat"));
                lstMhs.add(mhs);
            }
        }        
        catch(SQLException ex){
            System.out.println("error"+ex);
        }
        return lstMhs;
    }

    @Override
    public boolean insert(Mahasiswa b) {
        boolean result = true;
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(insert);
            statement.setInt(1, b.getId());
            statement.setString(2, b.getNim());
            statement.setString(3, b.getNama());
            statement.setString(4, b.getJk());
            statement.setString(5, b.getAlamat());
            statement.execute();
        }
        catch(SQLException ex) {
            System.out.println("Gagal Input");
            result = false;
        }
        finally {
            try {
                statement.close();
            }
            catch(SQLException ex) {
                System.out.println("Gagal Input");
                result = false;
            }
        }
        return result;
    }

    @Override
    public void update(Mahasiswa b) {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(update);
            statement.setString(1, b.getNim());
            statement.setString(2, b.getNama());
            statement.setString(3, b.getJk());
            statement.setString(4, b.getAlamat());
            statement.setInt(5, b.getId());
            statement.execute();
        }
        catch(SQLException ex) {
            System.out.println("Gagal Update");
        }
        finally {
            try {
                statement.close();
            }
            catch(SQLException ex) {
                System.out.println("Gagal Update");
            }
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(delete);
            statement.setInt(1, id);
            statement.execute();
        }
        catch(SQLException ex) {
            System.out.println("Gagal Delete");
        }
        finally {
            try {
                statement.close();
            }
            catch(SQLException ex) {
                System.out.println("Gagal Delete");
            }
        }
    }
    Connection con;
    final String insert = "INSERT INTO tblmahasiswa (id, nim, nama, jk, alamat) VALUES (?, ?, ?, ?, ?);";
    final String update = "UPDATE tblmahasiswa SET nim=?, nama=?, jk=?, alamat=? WHERE id=?;";
    final String delete = "DELETE FROM tblmahasiswa WHERE id=?;";
    final String select = "SELECT * FROM tblmahasiswa ORDER BY id asc;";
    final String carinama = "SELECT * FROM tblmahasiswa WHERE nama like ?;";

}
