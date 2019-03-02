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
public class PoslovnaJedinica {
    private int sifra_pj;
    private String naziv;
    private String adresa;

    public PoslovnaJedinica() {
    }

    public PoslovnaJedinica(int sifra_pj, String naziv, String adresa) {
        this.sifra_pj = sifra_pj;
        this.naziv = naziv;
        this.adresa = adresa;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getSifra_pj() {
        return sifra_pj;
    }

    public void setSifra_pj(int sifra_pj) {
        this.sifra_pj = sifra_pj;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return  naziv ;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.sifra_pj;
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
        final PoslovnaJedinica other = (PoslovnaJedinica) obj;
        if (this.sifra_pj != other.sifra_pj) {
            return false;
        }
        return true;
    }
}

   
