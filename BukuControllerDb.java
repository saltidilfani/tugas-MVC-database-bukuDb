/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salti.controller;

import salti.Dao.BukuDb;
import salti.model.Buku;
import salti.view.FormBukuDb;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class BukuControllerDb {
    FormBukuDb formBukuDb;
    Buku buku;
    BukuDb bukuDb;
    
    public BukuControllerDb(FormBukuDb formBukuDb){
        this.formBukuDb = formBukuDb;
        bukuDb = new BukuDb();
    }

    public void cancel(){
        formBukuDb.getTxtKodeBuku().setText("");
        formBukuDb.getTxtJudul().setText("");
        formBukuDb.getTxtPengarang().setText("");
        formBukuDb.getTxtPenerbit().setText("");
        formBukuDb.getTxtTahun().setText("");
    } 
    
    public void insert(){
        try {
            buku = new Buku ();
            buku.setKodeBuku(formBukuDb.getTxtKodeBuku().getText());
            buku.setJudul(formBukuDb.getTxtJudul().getText());
            buku.setPengarang(formBukuDb.getTxtPengarang().getText());
            buku.setPenerbit(formBukuDb.getTxtPenerbit().getText());
            buku.setTahun(formBukuDb.getTxtTahun().getText());
            bukuDb.insert(buku);
            JOptionPane.showMessageDialog(formBukuDb, "Entri Data Ok");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(formBukuDb, "Error  "+ex.getMessage());
        }
    }
    
    public void viewData(){
        try {
            DefaultTableModel model = (DefaultTableModel)formBukuDb.getTabelBuku().getModel();
            model.setNumRows(0);
            List<Buku> list = bukuDb.getAllBuku();
            for(Buku buku : list){
                Object[] data = {
                    buku.getKodeBuku(),
                    buku.getJudul(),
                    buku.getPengarang(),
                    buku.getPenerbit(),
                    buku.getTahun()
                };
                model.addRow(data);
            }
        } catch (Exception ex) {
            Logger.getLogger(BukuControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public  void actionClickTabel(){
        try {
            String KodeBuku = formBukuDb.getTabelBuku()
                    .getValueAt(formBukuDb.getTabelBuku().getSelectedRow(), 0).toString();
            buku = bukuDb.getBuku(KodeBuku);
            formBukuDb.getTxtKodeBuku().setText(buku.getKodeBuku());
            formBukuDb.getTxtJudul().setText(buku.getJudul());
            formBukuDb.getTxtPengarang().setText(buku.getPengarang());
            formBukuDb.getTxtPenerbit().setText(buku.getPenerbit());
            formBukuDb.getTxtTahun().setText(buku.getTahun());
        } catch (Exception ex) {
            Logger.getLogger(BukuControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void update(){
        
        try {
            buku.setKodeBuku(formBukuDb.getTxtKodeBuku().getText());
            buku.setJudul(formBukuDb.getTxtJudul().getText());
            buku.setPengarang(formBukuDb.getTxtPengarang().getText());
            buku.setPenerbit(formBukuDb.getTxtPenerbit().getText());
            buku.setTahun(formBukuDb.getTxtTahun().getText());
            bukuDb.update(buku);
            JOptionPane.showMessageDialog(formBukuDb, "Update Data Ok");
        } catch (Exception ex) {
            Logger.getLogger(MahasiswaControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(){
        try {
            String nobp = formBukuDb.getTabelBuku()
                    .getValueAt(formBukuDb.getTabelBuku().getSelectedRow(), 0).toString();
            bukuDb.delete(nobp);
            JOptionPane.showMessageDialog(formBukuDb, "Delete Data Ok");
        } catch (Exception ex) {
            Logger.getLogger(BukuControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
