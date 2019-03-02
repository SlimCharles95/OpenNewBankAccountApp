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
public class ZahtevZaOtvaranjeRacuna {
  private int sifra_zahteva;
private String vrsta_racuna;
private String status;
private Zaposleni zaposleni;
private PravnoLice pravno;
private PoslovnaJedinica pj;

    public ZahtevZaOtvaranjeRacuna() {
    }

    public ZahtevZaOtvaranjeRacuna(int sifra_zahteva, String vrsta_racuna, String status, Zaposleni zaposleni, PravnoLice pravno, PoslovnaJedinica pj) {
        this.sifra_zahteva = sifra_zahteva;
        this.vrsta_racuna = vrsta_racuna;
        this.status = status;
        this.zaposleni = zaposleni;
        this.pravno = pravno;
        this.pj = pj;
    }

    public PoslovnaJedinica getPj() {
        return pj;
    }

    public void setPj(PoslovnaJedinica pj) {
        this.pj = pj;
    }

    public int getSifra_zahteva() {
        return sifra_zahteva;
    }

    public void setSifra_zahteva(int sifra_zahteva) {
        this.sifra_zahteva = sifra_zahteva;
    }

    public String getVrsta_racuna() {
        return vrsta_racuna;
    }

    public void setVrsta_racuna(String vrsta_racuna) {
        this.vrsta_racuna = vrsta_racuna;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public PravnoLice getPravno() {
        return pravno;
    }

    public void setPravno(PravnoLice pravno) {
        this.pravno = pravno;
    }

}
