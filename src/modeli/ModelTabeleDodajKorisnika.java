/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class ModelTabeleDodajKorisnika extends AbstractTableModel {
private ArrayList<KorisnikKartice> lista;

    public ModelTabeleDodajKorisnika() {
        lista=new ArrayList<KorisnikKartice>();
    }

    public ModelTabeleDodajKorisnika(ArrayList<KorisnikKartice> lista) {
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
    case 2: return kk.getVrsta_kartice();
        case 3: return kk.getLimit();
    case 4: return kk.getVrsta_racuna();
        

    default: return "n/a"; 
}
    }

    @Override
    public String getColumnName(int column) {
switch(column){
    case 0: return "Ime i prezime";
    case 1: return "JMBG";
    case 2 : return "Vrsta kartice";
    case 3: return "Limit (RSD)";
    case 4: return "Vrsta racuna";
    default: return "n/a"; 
    }
    } 

    
      @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
if(columnIndex==3 ||columnIndex==1)return true;
return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
KorisnikKartice kk = lista.get(rowIndex);

        switch (columnIndex) {
            
            case 3:
                String lim= (String) aValue;
                int limit=Integer.parseInt(lim);
                kk.setLimit(limit);
                lista.get(rowIndex).setLimit(limit);
                System.out.println("limit"+limit);
                fireTableCellUpdated(rowIndex, 3);
                break;
               /*case 4:
                String vrsta = (String) aValue;
                kk.setVrsta_racuna(vrsta);
                fireTableCellUpdated(rowIndex, 4);
                break;*/
        }
           
    }
    public void obrisi(int red){
    lista.remove(red);
    fireTableDataChanged();
    }
    public void dodaj(KorisnikKartice kk){
    lista.add(kk);
    fireTableDataChanged();
    }
    public ArrayList<KorisnikKartice> getLista() {
        return lista;
    }
}
