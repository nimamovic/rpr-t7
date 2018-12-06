package ba.unsa.rpr.tutorijal7;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Tutorijal {

    public static ArrayList<Grad> ucitajGradove(){
        ArrayList<Grad> gradovi = new ArrayList<Grad>();
        Scanner ulaz=null;

        try{
            ulaz=new Scanner(new FileReader("mjerenja.txt"));
        } catch(FileNotFoundException e){
            System.out.println("Datoteka brojevi.txt ne postoji ili se ne može otvoriti.");
            System.out.println("Greska: " + e);
            System.exit(1);
        }

        try {
            while (ulaz.hasNext()) {
                String[] podaci = ulaz.nextLine().split(",");
                Grad grad = new Grad();
                grad.setNaziv(podaci[0]);
                double[] niz = new double[podaci.length - 1];
                for (int i = 1; i < 1000; i++) {
                    if (i == podaci.length) break;
                    niz[i - 1] = Double.parseDouble(podaci[i]);
                }
                grad.setTemperature(niz);
                gradovi.add(grad);
            }
        }
        catch(Exception e){

            System.out.println("Problem pri citanju!");
            System.out.println("Greska: " + e);

        }
        finally {
            ulaz.close();
        }
        return gradovi;
    }

    public static void main(String[] args) {
        ArrayList<Grad> gradovi = Tutorijal.ucitajGradove();

        for(var pom : gradovi){
            System.out.println(pom);
        }
    }
}