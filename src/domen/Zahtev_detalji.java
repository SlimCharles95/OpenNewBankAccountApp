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
public class Zahtev_detalji {
 private int sifra_zahteva;
 private String namena_racuna;
 private int obim_transkacija;
 private String inostrane_transakcije;
 private String dostava_izvoda;
 private int ukupan_limit;
    private ArrayList<KorisnikKartice> korisniciKartica;

    public Zahtev_detalji() {
        korisniciKartica=new ArrayList<>();
    }

    public Zahtev_detalji(int sifra_zahteva, String namena_racuna, int obim_transkacija, String inostrane_transakcije, String dostava_izvoda, int ukupan_limit, ArrayList<KorisnikKartice> korisniciKartica) {
        this.sifra_zahteva = sifra_zahteva;
        this.namena_racuna = namena_racuna;
        this.obim_transkacija = obim_transkacija;
        this.inostrane_transakcije = inostrane_transakcije;
        this.dostava_izvoda = dostava_izvoda;
        this.ukupan_limit = ukupan_limit;
        this.korisniciKartica = korisniciKartica;
    }

    public ArrayList<KorisnikKartice> getKorisniciKartica() {
        return korisniciKartica;
    }

    public void setKorisniciKartica(ArrayList<KorisnikKartice> korisniciKartica) {
        this.korisniciKartica = korisniciKartica;
    }

    public int getSifra_zahteva() {
        return sifra_zahteva;
    }

    public void setSifra_zahteva(int sifra_zahteva) {
        this.sifra_zahteva = sifra_zahteva;
    }

    public String getNamena_racuna() {
        return namena_racuna;
    }

    public void setNamena_racuna(String namena_racuna) {
        this.namena_racuna = namena_racuna;
    }

    public int getObim_transkacija() {
        return obim_transkacija;
    }

    public void setObim_transkacija(int obim_transkacija) {
        this.obim_transkacija = obim_transkacija;
    }

    public String getInostrane_transakcije() {
        return inostrane_transakcije;
    }

    public void setInostrane_transakcije(String inostrane_transakcije) {
        this.inostrane_transakcije = inostrane_transakcije;
    }

    public String getDostava_izvoda() {
        return dostava_izvoda;
    }

    public void setDostava_izvoda(String dostava_izvoda) {
        this.dostava_izvoda = dostava_izvoda;
    }

    public int getUkupan_limit() {
        return ukupan_limit;
    }

    public void setUkupan_limit(int ukupan_limit) {
        this.ukupan_limit = ukupan_limit;
    }
    
}
