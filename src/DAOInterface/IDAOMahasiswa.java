/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOInterface;
import java.util.List;
import Model.Mahasiswa;
/**
 *
 * @author ardab
 */
public interface IDAOMahasiswa {
    //read data
    public List<Mahasiswa> getAll();
    // cari data
    public List<Mahasiswa> getCariNama(String Nama);
    // masukan data
    public boolean insert(Mahasiswa b);
    // ubah data
    public void update(Mahasiswa b);
    //hapus data
    public void delete(int id);
    
    
}
