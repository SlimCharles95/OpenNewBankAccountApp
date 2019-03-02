/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.KorisnikKartice;
import domen.Vlasnik;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ivan
 */
public class ModelTabeleVlasnici extends AbstractTableModel{
    private ArrayList<Vlasnik> lista;

    public ModelTabeleVlasnici() {
        lista=new ArrayList<Vlasnik>();
    }

    public ModelTabeleVlasnici(ArrayList<Vlasnik> lista) {
        this.lista = lista;
    }
    

    @Override
    public int getRowCount() {
if(lista==null) return 0;
        return lista.size();
    }

    @Override
    public int getColumnCount() {
return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
Vlasnik v=lista.get(rowIndex);
 switch(columnIndex){
    case 0: return v.getNaziv();
    case 1: return v.getTip_vlasnika();
    case 2: return v.getProcenat_udela();
    case 3: return "Nivo "+v.getNivo();
    
    default: return "n/a"; 
    }
    }

    @Override
    public String getColumnName(int column) {
switch(column){
    case 0: return "Vlasnik";
    case 1: return "Tip vlasnika";
    case 2: return "Procenat udela(%)";
    case 3: return "Nivo";
    
    default: return "n/a"; 
    }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
if(columnIndex==2)return true;
return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
Vlasnik v = lista.get(rowIndex);

        switch (columnIndex) {
            
            case 2:
                String proc=(String) aValue;
                double procenat = Double.parseDouble(proc);
                v.setProcenat_udela(procenat);
                fireTableCellUpdated(rowIndex, 2);
                break;
        }
           
    }
    

    public ArrayList<Vlasnik> getLista() {
        return lista;
    }

    public void obrisi(int red) {
lista.remove(red);
fireTableDataChanged();
    }

     public void dodaj(Vlasnik v) {
lista.add(v);
fireTableDataChanged();
    }
    
}
