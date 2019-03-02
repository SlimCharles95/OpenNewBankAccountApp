/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.util.ArrayList;

/**
 *
 * @author Ivan
 */
public class PravnoLice {
 private int maticni_broj;
 private String naziv;
 private int PIB;
 private String adresa;
 private long telefon;
 private String email;
 private int prihodi;
 private String najveci_procenat_vlasnik;
 private OvlascenoLice ol;
    private ArrayList<Vlasnik> vlasnici;

    public PravnoLice() {
        vlasnici=new ArrayList<>();
    }

    public PravnoLice(int maticni_broj, String naziv, int PIB, String adresa, long telefon, String email, int prihodi, String najveci_procenat_vlasnik, OvlascenoLice ol, ArrayList<Vlasnik> vlasnici) {
        this.maticni_broj = maticni_broj;
        this.naziv = naziv;
        this.PIB = PIB;
        this.adresa = adresa;
        this.telefon = telefon;
        this.email = email;
        this.prihodi = prihodi;
        this.najveci_procenat_vlasnik = najveci_procenat_vlasnik;
        this.ol = ol;
        this.vlasnici = vlasnici;
    }

    public ArrayList<Vlasnik> getVlasnici() {
        return vlasnici;
    }

    public void setVlasnici(ArrayList<Vlasnik> vlasnici) {
        this.vlasnici = vlasnici;
    }

    public int getMaticni_broj() {
        return maticni_broj;
    }

    public void setMaticni_broj(int maticni_broj) {
        this.maticni_broj = maticni_broj;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getPIB() {
        return PIB;
    }

    public void setPIB(int PIB) {
        this.PIB = PIB;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public long getTelefon() {
        return telefon;
    }

    public void setTelefon(long telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPrihodi() {
        return prihodi;
    }

    public void setPrihodi(int prihodi) {
        this.prihodi = prihodi;
    }

    public String getNajveci_procenat_vlasnik() {
        return najveci_procenat_vlasnik;
    }

    public void setNajveci_procenat_vlasnik(String najveci_procenat_vlasnik) {
        this.najveci_procenat_vlasnik = najveci_procenat_vlasnik;
    }

    public OvlascenoLice getOl() {
        return ol;
    }

    public void setOl(OvlascenoLice ol) {
        this.ol = ol;
    }

    @Override
    public String toString() {
        return naziv; //To change body of generated methods, choose Tools | Templates.
    }
   
    
}
