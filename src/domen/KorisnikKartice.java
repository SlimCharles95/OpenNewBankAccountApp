/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

/**
 *
 * @author Ivan
 */
public class KorisnikKartice {
    private String JMBG;
    private String ime_prezime;
    private String vrsta_kartice;
    private String vrsta_racuna;
    private int limit;
      private int sifra_zahteva;
    public KorisnikKartice() {
    }

    public KorisnikKartice(String JMBG, String ime_prezime, String vrsta_kartice, String vrsta_racuna, int limit,int sifra_zahteva) {
        this.JMBG = JMBG;
        this.ime_prezime = ime_prezime;
        this.vrsta_kartice = vrsta_kartice;
        this.vrsta_racuna = vrsta_racuna;
        this.limit = limit;
       this. sifra_zahteva=sifra_zahteva;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getJMBG() {
        return JMBG;
    }

    public void setJMBG(String JMBG) {
        this.JMBG = JMBG;
    }

    public String getIme_prezime() {
        return ime_prezime;
    }

    public void setIme_prezime(String ime_prezime) {
        this.ime_prezime = ime_prezime;
    }

    public String getVrsta_kartice() {
        return vrsta_kartice;
    }

    public void setVrsta_kartice(String vrsta_kartice) {
        this.vrsta_kartice = vrsta_kartice;
    }

    public String getVrsta_racuna() {
        return vrsta_racuna;
    }

    public void setVrsta_racuna(String vrsta_racuna) {
        this.vrsta_racuna = vrsta_racuna;
    }

    public int getSifra_zahteva() {
        return sifra_zahteva;
    }

    public void setSifra_zahteva(int sifra_zahteva) {
        this.sifra_zahteva = sifra_zahteva;
    }
    
}
