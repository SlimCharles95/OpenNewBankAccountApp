/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.KorisnikKartice;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ivan
 */
public class ModelTabeleKorisnici extends AbstractTableModel {
private ArrayList<KorisnikKartice> lista;

    public ModelTabeleKorisnici() {
        lista=new ArrayList<KorisnikKartice>();
    }

    public ModelTabeleKorisnici(ArrayList<KorisnikKartice> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
if(lista==null) return 0;
        return lista.size();
    }

    @Override
    public int getColumnCount() {
return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
KorisnikKartice kk=lista.get(rowIndex);
switch(columnIndex){
    case 0: return kk.getIme_prezime();
    case 1: return kk.getJMBG();
    case 2: return kk.getVrsta_racuna();
    case 3: return kk.getLimit();
        case 4: return kk.getSifra_zahteva();

    default: return "n/a"; 
}
    }

    @Override
    public String getColumnName(int column) {
switch(column){
    case 0: return "Ime i prezime";
    case 1: return "JMBG";
    case 2: return "Vrsta racuna";
    case 3: return "Limit (RSD)";
        case 4: return "Sifra zahteva";
    default: return "n/a"; 
    }
    } 

    public ArrayList<KorisnikKartice> getLista() {
        return lista;
    }
    
}
