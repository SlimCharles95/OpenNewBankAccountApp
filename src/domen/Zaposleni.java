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
public class Zaposleni {
   private int sifra_zaposlenog;
   private String ime_prezime;
   private String pozicija;
   private PoslovnaJedinica pj;
           private String username;
           private String password;

    public Zaposleni() {
    }

    public Zaposleni(int sifra_zaposlenog, String ime_prezime, String pozicija, PoslovnaJedinica pj,String username,String password) {
        this.sifra_zaposlenog = sifra_zaposlenog;
        this.ime_prezime = ime_prezime;
        this.pozicija = pozicija;
        this.pj = pj;
        this.username=username;
        this.password=password;
    }

    public PoslovnaJedinica getPj() {
        return pj;
    }

    public void setPj(PoslovnaJedinica pj) {
        this.pj = pj;
    }

    public int getSifra_zaposlenog() {
        return sifra_zaposlenog;
    }

    public void setSifra_zaposlenog(int sifra_zaposlenog) {
        this.sifra_zaposlenog = sifra_zaposlenog;
    }

    public String getIme_prezime() {
        return ime_prezime;
    }

    public void setIme_prezime(String ime_prezime) {
        this.ime_prezime = ime_prezime;
    }

    public String getPozicija() {
        return pozicija;
    }

    public void setPozicija(String pozicija) {
        this.pozicija = pozicija;
    }

    @Override
    public String toString() {
        return ime_prezime ;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Zaposleni other = (Zaposleni) obj;
        if (this.sifra_zaposlenog != other.sifra_zaposlenog) {
            return false;
        }
        return true;
    }

    
   
}
