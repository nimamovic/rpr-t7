package ba.unsa.rpr.tutorijal7;

import java.io.Serializable;

public class Grad implements Serializable {
    private String naziv;
    private int brojStanovnika;
    private double[] temperature;

    public Grad(){
        setNaziv("");
        setBrojStanovnika(0);
        setTemperature(new double[1000]);
    }
    public Grad(String naziv, int brStanovnika, double[] temp){
        setNaziv(naziv);
        setBrojStanovnika(brStanovnika);
        setTemperature(temp);
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public double[] getTemperature() {
        return temperature;
    }

    public void setTemperature(double[] temperature) {
        this.temperature = temperature;
    }


}