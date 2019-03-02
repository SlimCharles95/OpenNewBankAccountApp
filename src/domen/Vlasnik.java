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
public class Vlasnik {
    private int sifra_vlasnika;
    private String naziv;
    private String tip_vlasnika;
    private double procenat_udela;
    private int nivo;
            private int maticni_broj;
    public Vlasnik() {
    }

    public Vlasnik(int sifra_vlasnika, String naziv, String tip_vlasnika, double procenat_udela, int nivo,int maticni_broj) {
        this.sifra_vlasnika = sifra_vlasnika;
        this.naziv = naziv;
        this.tip_vlasnika = tip_vlasnika;
        this.procenat_udela = procenat_udela;
        this.nivo = nivo;
        this.maticni_broj=maticni_broj;
    }

    public int getNivo() {
        return nivo;
    }

    public void setNivo(int nivo) {
        this.nivo = nivo;
    }

    public int getSifra_vlasnika() {
        return sifra_vlasnika;
    }

    public void setSifra_vlasnika(int sifra_vlasnika) {
        this.sifra_vlasnika = sifra_vlasnika;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip_vlasnika() {
        return tip_vlasnika;
    }

    public void setTip_vlasnika(String tip_vlasnika) {
        this.tip_vlasnika = tip_vlasnika;
    }

    public double getProcenat_udela() {
        return procenat_udela;
    }

    public void setProcenat_udela(double procenat_udela) {
        this.procenat_udela = procenat_udela;
    }

    public int getMaticni_broj() {
        return maticni_broj;
    }

    public void setMaticni_broj(int maticni_broj) {
        this.maticni_broj = maticni_broj;
    }
    
     
}
