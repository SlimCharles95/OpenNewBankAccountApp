/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import db.DBBroker;
import domen.KorisnikKartice;
import domen.OvlascenoLice;
import domen.PoslovnaJedinica;
import domen.PravnoLice;
import domen.Vlasnik;
import domen.ZahtevZaOtvaranjeRacuna;
import domen.Zahtev_detalji;
import domen.Zaposleni;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivan
 */
public class Kontroler {
    private static Kontroler instanca;
    DBBroker db;
    private Zaposleni ulogovani;

    private Kontroler() {
        db= new DBBroker();
         
    }
    
    
 public static Kontroler getInstanca() {
        if(instanca == null)
            instanca = new Kontroler();
        return instanca;
    }   

    public Zaposleni getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Zaposleni ulogovani) {
        this.ulogovani = ulogovani;
    }

    public Zaposleni login(String username, String password) {
        Zaposleni ulogovani=null;
db.ucitajDriver();
db.otvoriKonekciju();
ulogovani=db.login(username,password);
db.zatvoriKonekciju();
return ulogovani;
    }

    public ArrayList<PoslovnaJedinica> dajMiListuPoslovnihJednica() {
ArrayList<PoslovnaJedinica> lista=null;
db.ucitajDriver();
db.otvoriKonekciju();
lista=db.vratiPoslovneJedinice();
db.zatvoriKonekciju();
return lista;
    }

    public boolean sacuvajZaposlenog(PoslovnaJedinica pj, String pass) {
boolean uspesno=false;
db.ucitajDriver();
db.otvoriKonekciju();
        try {
            db.izmeniZaposlenog(pj,pass);
            uspesno=true;
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }



db.zatvoriKonekciju();
return uspesno;
    }

    public ArrayList<PravnoLice> vratiMalePrihode() {
ArrayList<PravnoLice> lista=null;
db.ucitajDriver();
db.otvoriKonekciju();
lista=db.vratiMalePrihode();
db.zatvoriKonekciju();
return lista;
    }

    public ArrayList<PravnoLice> vratiSrednjePrihode() {
ArrayList<PravnoLice> lista=null;
db.ucitajDriver();
db.otvoriKonekciju();
lista=db.vratiSrednjePrihode();
db.zatvoriKonekciju();
return lista;
    }

    public ArrayList<PravnoLice> vratiVelikePrihode() {
ArrayList<PravnoLice> lista=null;
db.ucitajDriver();
db.otvoriKonekciju();
lista=db.vratiVelikePrihode();
db.zatvoriKonekciju();
return lista;
    }

    public ArrayList<PravnoLice> vratiSvePrihode() {
ArrayList<PravnoLice> lista=null;
db.ucitajDriver();
db.otvoriKonekciju();
lista=db.vratiSvePrihode();
db.zatvoriKonekciju();
return lista;
    }

    public ArrayList<OvlascenoLice> dajMiListuOvlascenih() {
ArrayList<OvlascenoLice> lista=null;
db.ucitajDriver();
db.otvoriKonekciju();
lista=db.vratiOvlascenaLica();
db.zatvoriKonekciju();
return lista;
    }

    public ArrayList<Vlasnik> vratiVlasnike(PravnoLice pl) {
ArrayList<Vlasnik> lista=null;
        db.ucitajDriver();
db.otvoriKonekciju();
lista=db.vratiVlasnike(pl);
db.zatvoriKonekciju();
return lista;
    }

    

    public boolean sacuvajPravnoLive(PravnoLice pl, ArrayList<Vlasnik> lista) {
boolean uspesno=false;
db.ucitajDriver();
db.otvoriKonekciju();
        try {
            db.sacuvajPravnoLice(pl,lista);
           
            uspesno=true;
            db.commit();
             for (Vlasnik v : lista) {
           db.sacuvajVlasnika(pl.getMaticni_broj(),v,lista.indexOf(v));
           db.commit();
        }
        } catch (SQLException ex) {
            uspesno=false;
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }



db.zatvoriKonekciju();
return uspesno;
    }

    public boolean izmeniPravnoLive(PravnoLice pl, ArrayList<Vlasnik> lista,PravnoLice preIzmene,ArrayList<Vlasnik> ObrsianiVlasci) {
boolean uspesno=false;
db.ucitajDriver();
db.otvoriKonekciju();
        try {
            db.izmeniPravnoLice(pl,lista);
            uspesno=true;
            db.commit();
            ArrayList<Vlasnik> upd=new ArrayList<>();
            ArrayList<Vlasnik> ins=new ArrayList<>();
              for (Vlasnik vlasnik : lista) {
                  if(vlasnik.getSifra_vlasnika()==-1) ins.add(vlasnik);
                  else upd.add(vlasnik);
                  }

     
if(upd.size()>0){
               for (Vlasnik vlasnik : upd) {
                 
            db.izmeniVlasnika(vlasnik.getMaticni_broj(),vlasnik);
            db.commit();
        }
}
                      
if(ins.size()>0){
                     

              for (Vlasnik vlasnik1 : ins) {
                  int index=db.vratiMaxIndex(vlasnik1.getMaticni_broj());
                db.sacuvajVlasnika(vlasnik1.getMaticni_broj(),vlasnik1,index);
            db.commit();
            }
}
            
           

              for (Vlasnik vlasnik1 : ObrsianiVlasci) {
                if(vlasnik1.getSifra_vlasnika()!=-1)
                { db.ObrisiVlasnika(vlasnik1);
            db.commit();
                }
            }
        
 

 
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }



db.zatvoriKonekciju();
return uspesno;
    }

    public boolean izmeniVecinskogVlasnika(int maticniBroj, String vecinski) {
boolean uspesno=false;
db.ucitajDriver();
db.otvoriKonekciju();
        try {
            db.izmeniVecinskogVlasnika(maticniBroj,vecinski);
            uspesno=true;
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }



db.zatvoriKonekciju();
return uspesno;
    }

    public ArrayList<KorisnikKartice> vratiSveKorisnikeKartice() {
ArrayList<KorisnikKartice> lista=null;
        db.ucitajDriver();
db.otvoriKonekciju();
lista=db.vratiKorisnikeKartice();
db.zatvoriKonekciju();
return lista;
    }

    public ArrayList<ZahtevZaOtvaranjeRacuna> vratiSveZahteve() {
ArrayList<ZahtevZaOtvaranjeRacuna> lista=null;
        db.ucitajDriver();
db.otvoriKonekciju();
lista=db.vratiSveZahteve();
db.zatvoriKonekciju();
return lista;
    }

    public ArrayList<ZahtevZaOtvaranjeRacuna> vratiZahteveZaposlenog() {
ArrayList<ZahtevZaOtvaranjeRacuna> lista=null;
        db.ucitajDriver();
db.otvoriKonekciju();
lista=db.vratiZahteveZaposlenog(this.getUlogovani().getSifra_zaposlenog());
db.zatvoriKonekciju();
return lista;
    }

    public boolean obrisiPravnoLive(PravnoLice pl) {
boolean uspesno=false;
db.ucitajDriver();
db.otvoriKonekciju();
        try {
            db.obrisiPravnoLice(pl);
            uspesno=true;
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }



db.zatvoriKonekciju();
return uspesno;
    }

    public boolean obrisiZahtev(ZahtevZaOtvaranjeRacuna zah) {
boolean uspesno=false;
db.ucitajDriver();
db.otvoriKonekciju();
        try {
            db.obrisiZahtev(zah);
            uspesno=true;
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }



db.zatvoriKonekciju();
return uspesno;
    }

   

    public PravnoLice nadjiPravnoLice(String naziv) {
PravnoLice trazeno=null;
        db.ucitajDriver();
db.otvoriKonekciju();
trazeno=db.nadjiPravnoLice(naziv);
db.zatvoriKonekciju();
return trazeno;
    }

    public ArrayList<KorisnikKartice> vratiKorisnike(ZahtevZaOtvaranjeRacuna zah) {
ArrayList<KorisnikKartice> lista=null;
        db.ucitajDriver();
db.otvoriKonekciju();
lista=db.vratiKorisnikeKartice(zah);
db.zatvoriKonekciju();
return lista;
    }

    public ArrayList<Zaposleni> dajMiZaposlene() {
ArrayList<Zaposleni> lista=null;
        db.ucitajDriver();
db.otvoriKonekciju();
lista=db.vratiZaposlene();
db.zatvoriKonekciju();
return lista;
    }

    public Zahtev_detalji vratiDetalje(ZahtevZaOtvaranjeRacuna zah) {
Zahtev_detalji lista=null;
        db.ucitajDriver();
db.otvoriKonekciju();
lista=db.vratiZahteveDetalji(zah);
db.zatvoriKonekciju();
return lista;
    }

    public boolean sacuvajZahtev(ZahtevZaOtvaranjeRacuna zah, Zahtev_detalji detalji) {
boolean uspesno=false;
db.ucitajDriver();
db.otvoriKonekciju();
        try {
            db.sacuvajZahtev(zah,detalji);
            db.commit();
            for (KorisnikKartice k : detalji.getKorisniciKartica()) {
                int id=db.vratiMaxID();
            db.sacuvajKorisnikaKartice((id-1),k);
            db.commit();
        }
                        uspesno=true;

        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }



db.zatvoriKonekciju();
return uspesno;
    }

    

    public boolean izmeniStatus(String status, ZahtevZaOtvaranjeRacuna preIzmeneZah) {
boolean uspesno=false;
db.ucitajDriver();
db.otvoriKonekciju();
        try {
            db.izmeniStatus(status,preIzmeneZah);
            uspesno=true;
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }



db.zatvoriKonekciju();
return uspesno;
    }

    public boolean izmeniVrstu(String vrsta, ZahtevZaOtvaranjeRacuna preIzmeneZah) {
boolean uspesno=false;
db.ucitajDriver();
db.otvoriKonekciju();
        try {
            db.izmeniVrstu(vrsta,preIzmeneZah);
            uspesno=true;
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }



db.zatvoriKonekciju();
return uspesno;
    }

    public boolean izmeniZaposlenog(Zaposleni zap, ZahtevZaOtvaranjeRacuna preIzmeneZah) {
boolean uspesno=false;
db.ucitajDriver();
db.otvoriKonekciju();
        try {
            db.izmeniZaposlenog(zap,preIzmeneZah);
            uspesno=true;
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }



db.zatvoriKonekciju();
return uspesno;
    }

    public ArrayList<PoslovnaJedinica> dajMiJedinice() {
ArrayList<PoslovnaJedinica> lista=null;
        db.ucitajDriver();
db.otvoriKonekciju();
lista=db.vratiPJ();
db.zatvoriKonekciju();
return lista;
    }

    public boolean izmeniPj(PoslovnaJedinica pj, ZahtevZaOtvaranjeRacuna preIzmeneZah) {
    boolean uspesno=false;
db.ucitajDriver();
db.otvoriKonekciju();
        try {
            db.izmeniPJ(pj,preIzmeneZah);
            uspesno=true;
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }



db.zatvoriKonekciju();
return uspesno;
    }

    public boolean izmeniUkupanLimit(int ukupanLimit, ZahtevZaOtvaranjeRacuna preIzmeneZah) {
boolean uspesno=false;
db.ucitajDriver();
db.otvoriKonekciju();
        try {
            db.izmeniUkupanLimiT(ukupanLimit,preIzmeneZah);
            uspesno=true;
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }



db.zatvoriKonekciju();
return uspesno;
    }

    public boolean izmeniLimit(ArrayList<KorisnikKartice> lista, Zahtev_detalji preIzmene,ArrayList<KorisnikKartice> izbrisaneKartice) {
boolean uspesno=false;
db.ucitajDriver();
db.otvoriKonekciju();
        try {
            ArrayList<KorisnikKartice> ins=new ArrayList<>();
                        ArrayList<KorisnikKartice> upd=new ArrayList<>();
                   for (KorisnikKartice kk : lista) {
                  if(kk.getVrsta_racuna().equals("")) ins.add(kk);
                  else upd.add(kk);
                  }

            System.out.println(upd.size()+"");
if(upd.size()>0){
               for (KorisnikKartice kk2  : upd) {
                           db.izmeniLimiTTabela(kk2,preIzmene);
            db.commit();
        }
}
                      
if(ins.size()>0){
              for (KorisnikKartice kk3 : ins) {
            db.sacuvajKorisnikaKartice(preIzmene.getSifra_zahteva(),kk3);
            db.commit();
            }
}
            for (KorisnikKartice kk1 : izbrisaneKartice) {
              if(!(kk1.getVrsta_racuna().equals(""))){
                db.ObrisiKarticu(kk1);
            db.commit();
            }
            }
                    
                     uspesno=true;
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }



db.zatvoriKonekciju();
return uspesno;
    }

    public boolean izmeniVrstaUKart(ArrayList<KorisnikKartice> lista, Zahtev_detalji preIzmene,ArrayList<KorisnikKartice> izbrisaneKartice) {
boolean uspesno=true;
db.ucitajDriver();
db.otvoriKonekciju();
        try {
            ArrayList<KorisnikKartice> upd=new ArrayList<>();
            boolean izmenjen=false;
                   for (KorisnikKartice kk : lista) {
                       for (KorisnikKartice kk1 : preIzmene.getKorisniciKartica()) {
                           if(kk.getSifra_zahteva()==kk1.getSifra_zahteva()&&kk.getJMBG().equals(kk1.getJMBG())){
                 if(!(kk.getVrsta_racuna().equals(kk1.getVrsta_racuna()))) izmenjen=true; 
                       }
                  if(izmenjen){
                 upd.add(kk) ;
                 izmenjen=false;
                  }
                       }

                      }
for(KorisnikKartice kk2 : upd){
 db.izmeniVrstaTabela(kk2,preIzmene);
            db.commit();
                    uspesno=true;
}
        } catch (SQLException ex) {
            db.rollback();
            uspesno=false;
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }



db.zatvoriKonekciju();
return uspesno;
    }

    public int otvoriRacun(Zahtev_detalji preIzmene) {
int broj=-1;
db.ucitajDriver();
db.otvoriKonekciju();
        try {
            db.otvoriRacun(preIzmene);
            
            db.commit();
            broj=db.vratiBrojRacuna();
        } catch (SQLException ex) {
            db.rollback();
            broj=-1;
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }



db.zatvoriKonekciju();
return broj;
    }

    
    

    
   
}
