/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.KorisnikKartice;
import domen.PravnoLice;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ivan
 */
public class ModelTabelePravnaLica extends AbstractTableModel {
private ArrayList<PravnoLice> lista;

    public ModelTabelePravnaLica() {
        lista=new ArrayList<PravnoLice>();
    }

    public ModelTabelePravnaLica(ArrayList<PravnoLice> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
if(lista.isEmpty()) return 0;
        return lista.size();
    }

    @Override
    public int getColumnCount() {
return 4;       
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
if(lista.size()>rowIndex){
        PravnoLice pl=lista.get(rowIndex);
        switch(columnIndex){
    case 0: return pl.getNaziv();
    case 1: return pl.getMaticni_broj();
    case 2: return pl.getPrihodi();
    case 3: return pl.getNajveci_procenat_vlasnik();
    default: return "n/a"; 
    }
}
return "";
    }

    @Override
    public String getColumnName(int column) {
switch(column){
    case 0: return "Naziv";
    case 1: return "Maticni broj";
    case 2: return "Prihodi (RSD)";
    case 3: return "Vecinski vlasnik";
    default: return "n/a"; 
    }
    }

    public ArrayList<PravnoLice> getLista() {
        return lista;
    }

    public void obrisi(int red) {
lista.remove(red);
fireTableDataChanged();
    }

    public PravnoLice vratiPravnoLice(int red) {
return lista.get(red);
    }

    
    
}
