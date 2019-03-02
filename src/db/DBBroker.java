/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.KorisnikKartice;
import domen.OvlascenoLice;
import domen.PoslovnaJedinica;
import domen.PravnoLice;
import domen.Vlasnik;
import domen.ZahtevZaOtvaranjeRacuna;
import domen.Zahtev_detalji;
import domen.Zaposleni;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import logika.Kontroler;
import oracle.jdbc.driver.OracleDriver;

/**
 *
 * @author Ivan
 */
public class DBBroker {
 Connection connection;
    public void ucitajDriver() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void otvoriKonekciju() {
    try {
        connection=DriverManager.getConnection("jdbc:oracle:thin:projekat:1521:orcl", "student", "student");
        connection.setAutoCommit(false);
    } catch (SQLException ex) {
        Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    public void commit() {
    try {
        connection.commit();
    } catch (SQLException ex) {
        Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    public void rollback() {
    try {
        connection.rollback();
    } catch (SQLException ex) {
        Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    public void zatvoriKonekciju() {
    try {
        connection.close();
    } catch (SQLException ex) {
        Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
    }
    }   

    public Zaposleni login(String username, String password) {
Zaposleni ulog=null;
        try {
       
        String sql="select * from Zaposleni z join Poslovna_jedinica pj on z.sifra_pj=pj.sifra_pj where z.korisnicko_ime=? and z.lozinka=?";
        PreparedStatement ps=connection.prepareStatement(sql);
          
                       

        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
                    ulog=new Zaposleni(rs.getInt(1), rs.getString(2), rs.getString(3),null,rs.getString(5),rs.getString(6) );
                    PoslovnaJedinica pj=new PoslovnaJedinica(rs.getInt(7), rs.getString(8), rs.getString(9));
ulog.setPj(pj);
        }
    } catch (SQLException ex) {
        Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
    }
     return ulog;  
    }

    public ArrayList<PoslovnaJedinica> vratiPoslovneJedinice() {
ArrayList<PoslovnaJedinica> lista=new ArrayList<>();
String sql="select *  from Poslovna_jedinica ";
        Statement s;
     try {
         s = connection.createStatement();
         ResultSet rs=s.executeQuery(sql);
         while(rs.next()){
                    PoslovnaJedinica pj=new PoslovnaJedinica(rs.getInt(1), rs.getString(2), rs.getString(3));
                    lista.add(pj);
        }
         
     } catch (SQLException ex) {
         Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
     }
        
        
return lista;
    }

    public void izmeniZaposlenog(PoslovnaJedinica pj, String pass) throws SQLException {
String sql="Update Zaposleni set sifra_pj=?,lozinka=? where sifra_zaposlenog=? ";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setInt(1, pj.getSifra_pj());
        ps.setString(2, pass);
        ps.setInt(3, Kontroler.getInstanca().getUlogovani().getSifra_zaposlenog());
        ps.executeUpdate();

    }

    public ArrayList<PravnoLice> vratiMalePrihode() {
ArrayList<PravnoLice> lista=new ArrayList<>();
long t=0;
String sql="select *  from Pravno_lice PARTITION (pl_mali_prihodi) pl join Ovlasceno_lice ol on pl.sifra_ol=ol.sifra_ol ";
        Statement s;
     try {
         s = connection.createStatement();
         ResultSet rs=s.executeQuery(sql);
         while(rs.next()){
             t=vratiTelefon(rs.getInt(1));
             PravnoLice pl=new PravnoLice(rs.getInt(1), rs.getString(2),rs.getInt(3), rs.getString(4), t,rs.getString(6),rs.getInt(7), rs.getString(8), new OvlascenoLice(rs.getInt(10),rs.getString(11)), new ArrayList<>());
                    lista.add(pl);
        }
         
     } catch (SQLException ex) {
         Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
     }
        
        
return lista;
    }

    public ArrayList<PravnoLice> vratiSrednjePrihode() {
ArrayList<PravnoLice> lista=new ArrayList<>();
String sql="select * from Pravno_lice PARTITION (pl_srednji_prihodi) pl join Ovlasceno_lice ol on pl.sifra_ol=ol.sifra_ol ";
        Statement s;
     try {
         s = connection.createStatement();
         ResultSet rs=s.executeQuery(sql);
         while(rs.next()){
             PravnoLice pl=new PravnoLice(rs.getInt(1), rs.getString(2),rs.getInt(3), rs.getString(4), vratiTelefon(rs.getInt(1)),rs.getString(6),rs.getInt(7), rs.getString(8), new OvlascenoLice(rs.getInt(10),rs.getString(11)), new ArrayList<>());
                    lista.add(pl);
        }
         
     } catch (SQLException ex) {
         Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
     }
        
        
return lista;
    }

    public ArrayList<PravnoLice> vratiVelikePrihode() {
ArrayList<PravnoLice> lista=new ArrayList<>();
String sql="select * from Pravno_lice PARTITION (pl_veliki_prihodi) pl join Ovlasceno_lice ol on pl.sifra_ol=ol.sifra_ol ";
        Statement s;
     try {
         s = connection.createStatement();
         ResultSet rs=s.executeQuery(sql);
         while(rs.next()){
             PravnoLice pl=new PravnoLice(rs.getInt(1), rs.getString(2),rs.getInt(3), rs.getString(4), vratiTelefon(rs.getInt(1)),rs.getString(6),rs.getInt(7), rs.getString(8), new OvlascenoLice(rs.getInt(10),rs.getString(11)), new ArrayList<>());
                    lista.add(pl);
        }
         
     } catch (SQLException ex) {
         Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
     }
        
        
return lista;
    }

    public ArrayList<PravnoLice> vratiSvePrihode() {
ArrayList<PravnoLice> lista=new ArrayList<>();
String sql="select * from Pravno_lice pl join Ovlasceno_lice ol on pl.sifra_ol=ol.sifra_ol ";
        Statement s;
     try {
         s = connection.createStatement();
         ResultSet rs=s.executeQuery(sql);
         while(rs.next()){
             PravnoLice pl=new PravnoLice(rs.getInt(1), rs.getString(2),rs.getInt(3), rs.getString(4), vratiTelefon(rs.getInt(1)),rs.getString(6),rs.getInt(7), rs.getString(8), new OvlascenoLice(rs.getInt(10),rs.getString(11)), new ArrayList<>());
                    lista.add(pl);
        }
         
     } catch (SQLException ex) {
         Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
     }
        
        
return lista;
    }

    public ArrayList<OvlascenoLice> vratiOvlascenaLica() {
ArrayList<OvlascenoLice> lista=new ArrayList<>();
String sql="select *  from Ovlasceno_lice ";
        Statement s;
     try {
         s = connection.createStatement();
         ResultSet rs=s.executeQuery(sql);
         while(rs.next()){
OvlascenoLice ol=new OvlascenoLice(rs.getInt(1),rs.getString(2));
             lista.add(ol);
        }
         
     } catch (SQLException ex) {
         Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
     }
        
        
return lista;
    }

    public ArrayList<Vlasnik> vratiVlasnike(PravnoLice pl) {
ArrayList<Vlasnik> lista=new ArrayList<>();
String sql="select *  from Vlasnik where sifra_podataka="+pl.getMaticni_broj()+"";
        Statement s;
     try {
         s = connection.createStatement();
         ResultSet rs=s.executeQuery(sql);
         while(rs.next()){
Vlasnik v=new Vlasnik(rs.getInt(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getInt(6),rs.getInt(1));
             lista.add(v);
        }
         
     } catch (SQLException ex) {
         Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
     }
        
        
return lista;
    }

    public void sacuvajPravnoLice(PravnoLice pl, ArrayList<Vlasnik> lista) throws SQLException {
String sql="insert into Pravno_Lice values(?,?,?,?,BROJ_TELEFONA_SRBIJA(?),?,?,?,?)";
PreparedStatement ps=connection.prepareStatement(sql);
ps.setInt(1, pl.getMaticni_broj());
ps.setString(2, pl.getNaziv());
       ps.setInt(3, pl.getPIB());
       ps.setString(4, pl.getAdresa());
       ps.setLong(5, pl.getTelefon());
              ps.setString(6, pl.getEmail());
       ps.setInt(7, pl.getPrihodi());
              ps.setString(8, "niko");
       ps.setInt(9,pl.getOl().getSifra_ol());
       
       ps.executeUpdate();
         

       
       
    }

    public void sacuvajVlasnika(int maticni_broj, Vlasnik v,int index) throws SQLException {
String sql="insert into Vlasnik values(?,?,?,?,?,?)";
PreparedStatement ps=connection.prepareStatement(sql);
      
ps.setInt(1, maticni_broj);
       ps.setInt(2,(index+1) );
       ps.setString(3, v.getNaziv());
              ps.setString(4, v.getTip_vlasnika());
              ps.setDouble(5, v.getProcenat_udela());
       ps.setInt(6, v.getNivo());
        

       ps.executeUpdate();
    }

    public void izmeniVecinskogVlasnika(int maticniBroj, String vecinski) throws SQLException {
String sql="Update Pravno_lice set najveci_procenat_vlasnik=? where maticni_broj=? ";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setString(1, vecinski);
        ps.setInt(2, maticniBroj);
        ps.executeUpdate();
               

    }

    public void izmeniPravnoLice(PravnoLice pl, ArrayList<Vlasnik> lista) throws SQLException {
String sql="Update Pravno_lice set sifra_ol=? where maticni_broj=? ";
        PreparedStatement ps=connection.prepareStatement(sql);
         ps.setInt(1, pl.getOl().getSifra_ol());
        ps.setInt(2, pl.getMaticni_broj());
        ps.executeUpdate();
       
    }

    public void izmeniVlasnika(int maticni_broj, Vlasnik vlasnik) throws SQLException {
String sql="Update Vlasnik set procenat_udela=? where sifra_podataka=? and sifra_vlasnika=?";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setDouble(1, vlasnik.getProcenat_udela());
        ps.setInt(2, maticni_broj);
        ps.setInt(3, vlasnik.getSifra_vlasnika());
    

        ps.executeUpdate();

    }

    public ArrayList<KorisnikKartice> vratiKorisnikeKartice() {
ArrayList<KorisnikKartice> lista=new ArrayList<>();
String sql="select *  from Korisnik_kartice ";
        Statement s;
     try {
         s = connection.createStatement();
         ResultSet rs=s.executeQuery(sql);
         while(rs.next()){
KorisnikKartice kk=new KorisnikKartice(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(1));
        //redoled atributa u bazi pogledaj   
        lista.add(kk);
        }
         
     } catch (SQLException ex) {
         Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
     }
        
        
return lista;
    }

    public ArrayList<ZahtevZaOtvaranjeRacuna> vratiSveZahteve() {
ArrayList<ZahtevZaOtvaranjeRacuna> lista=new ArrayList<>();
String sql="select *  from Zahtev_za_otvaranje_racuna ";
        Statement s;
     try {
         s = connection.createStatement();
         ResultSet rs=s.executeQuery(sql);
         Zaposleni z=null;
         PravnoLice pl=null;
         while(rs.next()){
             
              z=vratiZaposlenog(rs.getInt(4));
              pl=vratiPravnoLice(rs.getInt(5));
ZahtevZaOtvaranjeRacuna zah=new ZahtevZaOtvaranjeRacuna(rs.getInt(1),rs.getString(2),rs.getString(3),z,pl,z.getPj());
             lista.add(zah);
        }
         
     } catch (SQLException ex) {
         Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
     }
        
        
return lista;
    }

    public ArrayList<ZahtevZaOtvaranjeRacuna> vratiZahteveZaposlenog(int sifra_zaposlenog) {
ArrayList<ZahtevZaOtvaranjeRacuna> lista=new ArrayList<>();
String sql="select *  from Zahtev_za_otvaranje_racuna where sifra_zaposlenog="+sifra_zaposlenog+"";
        Statement s;
     try {
         s = connection.createStatement();
         ResultSet rs=s.executeQuery(sql);
         Zaposleni z=null;
         PravnoLice pl=null;
         while(rs.next()){
             
              z=vratiZaposlenog(rs.getInt(4));
              pl=vratiPravnoLice(rs.getInt(5));
ZahtevZaOtvaranjeRacuna zah=new ZahtevZaOtvaranjeRacuna(rs.getInt(1),rs.getString(2),rs.getString(3),z,pl,z.getPj());
             lista.add(zah);
        }
         
     } catch (SQLException ex) {
         Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
     }
        
        
return lista;
    }

    private Zaposleni vratiZaposlenog(int aInt) {
Zaposleni zap=null;
        try {
       
        String sql="select * from Zaposleni z join Poslovna_jedinica pj on z.sifra_pj=pj.sifra_pj where sifra_zaposlenog=?";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setInt(1, aInt);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
                   zap=new Zaposleni(rs.getInt(1), rs.getString(2), rs.getString(3),new PoslovnaJedinica(rs.getInt(7), rs.getString(8), rs.getString(9)),rs.getString(5),rs.getString(6) );

        }
    } catch (SQLException ex) {
        Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
    }
     return zap;  
    }

    private PravnoLice vratiPravnoLice(int aInt) {
PravnoLice pl=null;
String sql="select * from Pravno_lice pl join Ovlasceno_lice ol on pl.sifra_ol=ol.sifra_ol where maticni_broj="+aInt+"";
        Statement s;
     try {
         s = connection.createStatement();
         ResultSet rs=s.executeQuery(sql);
         while(rs.next()){
              pl=new PravnoLice(rs.getInt(1), rs.getString(2),rs.getInt(3), rs.getString(4), vratiTelefon(rs.getInt(1)),rs.getString(6),rs.getInt(7), rs.getString(8), new OvlascenoLice(rs.getInt(10),rs.getString(11)), null);
                    
        }
         
     } catch (SQLException ex) {
         Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
     }
        
        
return pl;
    }

    public void obrisiPravnoLice(PravnoLice pl) throws SQLException {
String sql2="delete from Vlasnik where sifra_podataka="+pl.getMaticni_broj()+"";
Statement ps2;
     ps2 = connection.createStatement();
       ps2.executeUpdate(sql2);
        
        String sql="delete from Pravno_lice where maticni_broj="+pl.getMaticni_broj()+"";
Statement ps;
     ps = connection.createStatement();
       ps.executeUpdate(sql);
       
        
        
    }

    public void obrisiZahtev(ZahtevZaOtvaranjeRacuna zah) throws SQLException {
String sql2="delete from Korisnik_kartice where sifra_zahteva="+zah.getSifra_zahteva()+"";
Statement ps2;
 ps2 = connection.createStatement();
       ps2.executeUpdate(sql2);
        String sql="delete from Zahtev_view where sifra_zahteva="+zah.getSifra_zahteva()+"";
Statement ps;
     ps = connection.createStatement();
       ps.executeUpdate(sql);
        
    
    }

    public PravnoLice nadjiPravnoLice(String naziv) {
PravnoLice pl=null;
String sql="select * from Pravno_lice pl join Ovlasceno_lice ol on pl.sifra_ol=ol.sifra_ol where pl.naziv='"+naziv+"'";
        Statement s;
     try {
         s = connection.createStatement();
         ResultSet rs=s.executeQuery(sql);
         while(rs.next()){
              pl=new PravnoLice(rs.getInt(1), rs.getString(2),rs.getInt(3), rs.getString(4), vratiTelefon(rs.getInt(1)),rs.getString(6),rs.getInt(7), rs.getString(8), new OvlascenoLice(rs.getInt(10),rs.getString(11)), null);
                    
        }
         
     } catch (SQLException ex) {
         Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
     }
        
        
return pl;
    }

    public ArrayList<Zaposleni> vratiZaposlene() {
ArrayList<Zaposleni> lista=new ArrayList<>();
        try {
       
        String sql="select * from Zaposleni z join Poslovna_jedinica pj on z.sifra_pj=pj.sifra_pj ";
        Statement ps=connection.createStatement();
        ResultSet rs=ps.executeQuery(sql);
        while(rs.next()){
                 Zaposleni zap=new Zaposleni(rs.getInt(1), rs.getString(2), rs.getString(3),new PoslovnaJedinica(rs.getInt(7), rs.getString(8), rs.getString(9)),rs.getString(5),rs.getString(6) );
lista.add(zap);
        }
    } catch (SQLException ex) {
        Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
    }
     return lista;
    }

    public ArrayList<KorisnikKartice> vratiKorisnikeKartice(ZahtevZaOtvaranjeRacuna zah) {
   ArrayList<KorisnikKartice> lista=new ArrayList<>();
        try {
       
        String sql="select * from Korisnik_kartice k where k.sifra_zahteva="+zah.getSifra_zahteva()+"";
        Statement ps=connection.createStatement();
        ResultSet rs=ps.executeQuery(sql);
        while(rs.next()){
KorisnikKartice kk= new KorisnikKartice(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getInt(6), rs.getInt(1));
            lista.add(kk);
        }
    } catch (SQLException ex) {
        Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
    }
     return lista;
    }

    public Zahtev_detalji vratiZahteveDetalji(ZahtevZaOtvaranjeRacuna zah) {
   Zahtev_detalji detalji=null;
   ArrayList<KorisnikKartice> lista=new ArrayList<>();
        try {
       
        String sql="select * from Zahtev_detalji z where z.sifra_zahteva="+zah.getSifra_zahteva()+"";
        Statement ps=connection.createStatement();
        ResultSet rs=ps.executeQuery(sql);
        while(rs.next()){
lista=vratiKorisnikeKartice(zah);
detalji=new Zahtev_detalji(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6), lista);
        }
    } catch (SQLException ex) {
        Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
    }
     return detalji;
    }

    public void sacuvajZahtev(ZahtevZaOtvaranjeRacuna zah, Zahtev_detalji detalji) throws SQLException {
String sql="insert into Zahtev_view values(?,?,?,?,?,?,?,?,?,?,?)";
PreparedStatement ps=connection.prepareStatement(sql);
  int id=vratiMaxID();    
ps.setInt(1, id);
ps.setString(2, zah.getVrsta_racuna());
       ps.setString(3, detalji.getNamena_racuna());
       ps.setInt(4, detalji.getObim_transkacija());
       ps.setString(5, detalji.getInostrane_transakcije());
              ps.setString(6, detalji.getDostava_izvoda());
       ps.setString(7, zah.getStatus());
              ps.setInt(8, 0);
       ps.setInt(9,zah.getZaposleni().getSifra_zaposlenog());
       ps.setInt(10, 0);
ps.setInt(11, zah.getPravno().getMaticni_broj());
       ps.executeUpdate();
        
       
    }

    public int vratiMaxID() throws SQLException {
 int maks=0;
String sql="select max(sifra_zahteva)as maks from Zahtev_view";
        Statement s=connection.createStatement();
        ResultSet rs=s.executeQuery(sql);
        while(rs.next()){
maks=rs.getInt("maks");
        }
        return ++maks;
    }

    public void sacuvajKorisnikaKartice(int id, KorisnikKartice k) throws SQLException {
String sql="insert into Korisnik_kartice values(?,?,?,?,?,?)";
PreparedStatement ps=connection.prepareStatement(sql);
   
ps.setInt(1, id);
ps.setString(2, k.getJMBG());
       ps.setString(3, k.getIme_prezime());
       ps.setString(4, k.getVrsta_kartice());
       ps.setString(5, "");
              ps.setInt(6, k.getLimit());
       
       ps.executeUpdate();
    }

    public void izmeniStatus(String status, ZahtevZaOtvaranjeRacuna preIzmeneZah) throws SQLException {
String sql="Update Zahtev_za_otvaranje_racuna set status=? where sifra_zahteva=?";
        PreparedStatement ps=connection.prepareStatement(sql);
                ps.setString(1, status);
        ps.setInt(2, preIzmeneZah.getSifra_zahteva());
        ps.executeUpdate();
    }

    public void izmeniVrstu(String vrsta, ZahtevZaOtvaranjeRacuna preIzmeneZah) throws SQLException {
String sql="Update Zahtev_za_otvaranje_racuna set vrsta_racuna=? where sifra_zahteva=?";
        PreparedStatement ps=connection.prepareStatement(sql);
                ps.setString(1, vrsta);
        ps.setInt(2, preIzmeneZah.getSifra_zahteva());
        ps.executeUpdate();
    }

    public void izmeniZaposlenog(Zaposleni zap, ZahtevZaOtvaranjeRacuna preIzmeneZah) throws SQLException {
String sql="Update Zahtev_za_otvaranje_racuna set sifra_zaposlenog=? where sifra_zahteva=?";
        PreparedStatement ps=connection.prepareStatement(sql);
                ps.setInt(1, zap.getSifra_zaposlenog());
        ps.setInt(2, preIzmeneZah.getSifra_zahteva());
        ps.executeUpdate();
    }

    public void izmeniPJ(PoslovnaJedinica pj, ZahtevZaOtvaranjeRacuna preIzmeneZah) throws SQLException {
      String sql="Update Zahtev_za_otvaranje_racuna set sifra_pj=? where sifra_zahteva=?";
        PreparedStatement ps=connection.prepareStatement(sql);
                ps.setInt(1, pj.getSifra_pj());
        ps.setInt(2, preIzmeneZah.getSifra_zahteva());
                ps.executeUpdate();

    }

    public void izmeniUkupanLimiT(int ukupanLimit, ZahtevZaOtvaranjeRacuna preIzmeneZah) throws SQLException {
        String sql="Update Zahtev_detalji set ukupan_limit=? where sifra_zahteva=?";
        PreparedStatement ps=connection.prepareStatement(sql);
                ps.setInt(1, ukupanLimit);
        ps.setInt(2, preIzmeneZah.getSifra_zahteva());
                ps.executeUpdate(); 

    }

    public ArrayList<PoslovnaJedinica> vratiPJ() {
ArrayList<PoslovnaJedinica> lista=new ArrayList<>();
try {
       
        String sql="select * from  Poslovna_jedinica pj ";
        Statement ps=connection.createStatement();
        ResultSet rs=ps.executeQuery(sql);
        while(rs.next()){
         PoslovnaJedinica pj;
            pj = new PoslovnaJedinica(rs.getInt(1), rs.getString(2), rs.getString(3));
        lista.add(pj);
        }
    } catch (SQLException ex) {
        Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
    }
     return lista;
    }

    public void izmeniLimiTTabela(KorisnikKartice korisnikKartice, Zahtev_detalji preIzmene) throws SQLException {
            
        String sql="Update Korisnik_kartice set limit=? where sifra_zahteva=? and JMBG=?";
        PreparedStatement ps=connection.prepareStatement(sql);
                ps.setInt(1, korisnikKartice.getLimit());
                ps.setInt(2, preIzmene.getSifra_zahteva());
        ps.setString(3, korisnikKartice.getJMBG());
                ps.executeUpdate();
        
    }

    public void izmeniVrstaTabela(KorisnikKartice korisnikKartice, Zahtev_detalji preIzmene) throws SQLException {

            
        String sql="Update Korisnik_kartice set vrsta_racuna=? where sifra_zahteva=? and JMBG=?";
        PreparedStatement ps=connection.prepareStatement(sql);
                ps.setString(1, korisnikKartice.getVrsta_racuna());
                ps.setInt(2, preIzmene.getSifra_zahteva());
        ps.setString(3, korisnikKartice.getJMBG());
                ps.executeUpdate();
        
    }

    private long vratiTelefon(int maticni) throws SQLException {
         String sql2="select pl.telefon.vrati_broj() from Pravno_lice pl where maticni_broj="+maticni+"";
Statement ps=connection.createStatement();
int broj=0;
        ResultSet rs=ps.executeQuery(sql2);
        while(rs.next()){
         
          broj= rs.getInt(1);
        
        }
        long povratak;
     povratak = 3816;
        povratak=povratak*100000000 +broj ;
        return povratak;
    }
     public int vratiBrojRacuna() throws SQLException {
 int maks=0;
String sql="select max(racun_id)as maks from racun";
        Statement s=connection.createStatement();
        ResultSet rs=s.executeQuery(sql);
        while(rs.next()){
maks=rs.getInt("maks");
        }
        return ++maks;
    }

    public void otvoriRacun(Zahtev_detalji preIzmene) throws SQLException {
String sql="insert into Racun values(?,?,?,racun_broj(?,?,?))";
PreparedStatement ps=connection.prepareStatement(sql);
  int  id=vratiBrojRacuna();
ps.setInt(1, id);
ps.setString(2, "RSD");
       ps.setInt(3, preIzmene.getSifra_zahteva());
       ps.setInt(4,256 );
       ps.setInt(5, id);
              ps.setInt(6,38);
       
       ps.executeUpdate();
    }

    public void ObrisiVlasnika(Vlasnik vlasnik) throws SQLException {
String sql="delete from Vlasnik where sifra_podataka=? and sifra_vlasnika=?";
PreparedStatement ps=connection.prepareStatement(sql);
ps.setInt(1, vlasnik.getMaticni_broj());
ps.setInt(2, vlasnik.getSifra_vlasnika());
       
       
       ps.executeUpdate();
    }

    public int vratiMaxIndex(int maticni_broj) throws SQLException {
int maks=0;
String sql="select max(sifra_vlasnika)as maks from Vlasnik where sifra_podataka="+maticni_broj+"";
        Statement s=connection.createStatement();
        ResultSet rs=s.executeQuery(sql);
        while(rs.next()){
maks=rs.getInt("maks");
        }
        return maks;
    }

    public void ObrisiKarticu(KorisnikKartice kk1) throws SQLException {
String sql="delete from Korisnik_kartice where sifra_zahteva=? and JMBG=?";
PreparedStatement ps=connection.prepareStatement(sql);
ps.setInt(1, kk1.getSifra_zahteva());
ps.setString(2, kk1.getJMBG());
       ps.executeUpdate();
    }
}
