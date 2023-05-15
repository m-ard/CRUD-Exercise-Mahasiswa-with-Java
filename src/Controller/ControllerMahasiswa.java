/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DAOMahasiswa;
import DAOInterface.IDAOMahasiswa;
import Model.Mahasiswa;
import Model.TabelModelMahasiswa;
import View.CRUDMahasiswa;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ardab
 */
public class ControllerMahasiswa {
    CRUDMahasiswa frmMahasiswa;
    IDAOMahasiswa iMahasiswa;
    List<Mahasiswa> lstMhs;
    public ControllerMahasiswa(CRUDMahasiswa frmMahasiswa) {
        this.frmMahasiswa = frmMahasiswa;
        iMahasiswa = new DAOMahasiswa();
        lstMhs = iMahasiswa.getAll();
    }
    public void isiTable() {
        iMahasiswa.getAll();
        TabelModelMahasiswa tabelMhs = new TabelModelMahasiswa(iMahasiswa.getAll());
        frmMahasiswa.getTabelData().setModel(tabelMhs);
    }

    public void insert() {
        Mahasiswa b = new Mahasiswa();
        b.setId(Integer.valueOf(frmMahasiswa.gettxtID().getText()));
        b.setNim(frmMahasiswa.gettxtNim().getText());
        b.setNama(frmMahasiswa.gettxtNama().getText());
        b.setJk(frmMahasiswa.gettxtJk().getSelectedItem().toString());
        b.setAlamat(frmMahasiswa.gettxtAlamat().getText());
        boolean res = iMahasiswa.insert(b);
        if(res){
            JOptionPane.showMessageDialog(null, "Simpan Data Berhasil");
        }
        else {
            JOptionPane.showMessageDialog(null, "Gagal Input / Duplikasi");
        }
        lstMhs = iMahasiswa.getAll();
    }
    
    public void isiField(int row) {
        frmMahasiswa.gettxtID().setEnabled(false);
        // System.out.println(lstMhs);
        frmMahasiswa.gettxtID().setText(lstMhs.get(row).getId().toString());
        frmMahasiswa.gettxtNim().setText(lstMhs.get(row).getNim());
        frmMahasiswa.gettxtNama().setText(lstMhs.get(row).getNama());
        frmMahasiswa.gettxtJk().setSelectedItem(lstMhs.get(row).getJk());
        frmMahasiswa.gettxtAlamat().setText(lstMhs.get(row).getAlamat());
        lstMhs = iMahasiswa.getAll();
    }
    public void carinama() {
        lstMhs = iMahasiswa.getCariNama(frmMahasiswa.gettxtCariNama().getText());
        TabelModelMahasiswa tabelMhs = new TabelModelMahasiswa(lstMhs);
        frmMahasiswa.getTabelData().setModel(tabelMhs);
    }
    

    public void reset() {
        if(!frmMahasiswa.gettxtID().isEnabled()) {
            frmMahasiswa.gettxtID().setEnabled(true);
        }
        frmMahasiswa.gettxtID().setText("");
        frmMahasiswa.gettxtNim().setText("");
        frmMahasiswa.gettxtNama().setText("");
        frmMahasiswa.gettxtJk().setSelectedItem("");
        frmMahasiswa.gettxtAlamat().setText("");
        lstMhs = iMahasiswa.getAll();
    }

    public void update() {
        Mahasiswa b = new Mahasiswa();
        b.setNim(frmMahasiswa.gettxtNim().getText());
        b.setNama(frmMahasiswa.gettxtNama().getText());
        b.setJk(frmMahasiswa.gettxtJk().getSelectedItem().toString());
        b.setAlamat(frmMahasiswa.gettxtAlamat().getText());
        b.setId(Integer.valueOf(frmMahasiswa.gettxtID().getText()));
        iMahasiswa.update(b);
        JOptionPane.showMessageDialog(null, "Update Data Berhasil");
        lstMhs = iMahasiswa.getAll();
    }

    public void delete() {
        Mahasiswa b = new Mahasiswa();
        iMahasiswa.delete(Integer.valueOf(frmMahasiswa.gettxtID().getText()));
        JOptionPane.showMessageDialog(null, "Delete Data Berhasil");
        lstMhs = iMahasiswa.getAll();
    }

   

   
    
}
