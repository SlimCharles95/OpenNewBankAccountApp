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
public class OvlascenoLice {
  private int sifra_ol;
private String ime_prezime;

    public OvlascenoLice(int sifra_ol, String ime_prezime) {
        this.sifra_ol = sifra_ol;
        this.ime_prezime = ime_prezime;
    }

    public OvlascenoLice() {
    }

    public int getSifra_ol() {
        return sifra_ol;
    }

    public void setSifra_ol(int sifra_ol) {
        this.sifra_ol = sifra_ol;
    }

    public String getIme_prezime() {
        return ime_prezime;
    }

    public void setIme_prezime(String ime_prezime) {
        this.ime_prezime = ime_prezime;
    }

    @Override
    public String toString() {
        return  ime_prezime;
    }

}
