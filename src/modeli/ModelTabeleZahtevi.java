/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Vlasnik;
import domen.ZahtevZaOtvaranjeRacuna;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ivan
 */
public class ModelTabeleZahtevi extends AbstractTableModel {
private ArrayList<ZahtevZaOtvaranjeRacuna> lista;

    public ModelTabeleZahtevi() {
        lista=new ArrayList<ZahtevZaOtvaranjeRacuna>();
    }

    public ModelTabeleZahtevi(ArrayList<ZahtevZaOtvaranjeRacuna> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
if(lista==null) return 0;
        return lista.size();
    }

    @Override
    public int getColumnCount() {
return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
ZahtevZaOtvaranjeRacuna z=lista.get(rowIndex);
switch(columnIndex){
    case 0: return z.getSifra_zahteva();
    case 1: return z.getPravno();
    case 2: return z.getVrsta_racuna();
    case 3: return z.getStatus();
    case 4: return z.getPj();
    case 5: return z.getZaposleni();
    default: return "n/a"; 
    }

    }

    @Override
    public String getColumnName(int column) {
switch(column){
    case 0: return "Sifra zahteva";
    case 1: return "Pravno lice";
    case 2: return "Vrsta računa";
    case 3: return "Status";
    case 4: return "Poslovna jedinica";
    case 5: return "Zaposleni";
    default: return "n/a"; 
    }
    }

    public ArrayList<ZahtevZaOtvaranjeRacuna> getLista() {
        return lista;
    }

    public void obrisi(int red) {
lista.remove(red);
        fireTableDataChanged();
    }

    public ZahtevZaOtvaranjeRacuna vratiZahtev(int red) {
return lista.get(red);
    }
    
    
}
