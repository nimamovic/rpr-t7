package ba.unsa.rpr.tutorijal7;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.beans.XMLEncoder;
import java.io.*;
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

    static UN ucitajXml(ArrayList<Grad> gradovi) {
        Document xmldoc = null;
        try {
            DocumentBuilder docReader = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xmldoc = docReader.parse(new File("drzave.xml"));
        } catch (Exception e) {
            System.out.println("drzave.xml nije validan XML dokument");
        }

        UN un = new UN();
        ArrayList<Drzava> drzave = new ArrayList<>();

        assert xmldoc != null;
        NodeList drzaveXml = xmldoc.getElementsByTagName("drzava");

        for(int i = 0; i < drzaveXml.getLength(); i++) {
            Node drzavaNode = drzaveXml.item(i);

            if(drzavaNode instanceof Element) {
                Element drzavaEl = (Element)drzavaNode;

                int stanovnika = Integer.parseInt(drzavaEl.getAttribute("brojStanovnika"));
                String naziv = drzavaEl.getElementsByTagName("naziv").item(0).getTextContent();

                Element gGradXml = (Element)drzavaEl.getElementsByTagName("glavniGrad").item(0);
                int gStanovnika = Integer.parseInt(gGradXml.getAttribute("brojStanovnika"));
                String nazivGrada = gGradXml.getTextContent().trim();

                Element povrsinaXml = (Element)drzavaEl.getElementsByTagName("povrsina").item(0);
                String jedinica = povrsinaXml.getAttribute("jedinicaZaPovrsinu");
                double povrsina = Double.parseDouble(drzavaEl.getElementsByTagName("povrsina").item(0).getTextContent());

                Grad glavniGrad = new Grad(nazivGrada, gStanovnika, null);
                drzave.add(new Drzava(naziv, stanovnika, povrsina, jedinica, glavniGrad));
            }
        }

        un.setDrzave(drzave);
        return un;
    }

    public static void zapisiXml(UN un) {
        try {
            XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("un.xml")));
            e.writeObject(un);
            e.close();
        } catch(Exception e) {
            System.out.println("Greška: " + e);
        }
    }

    public static void main(String[] args) {
        ArrayList<Grad> gradovi = Tutorijal.ucitajGradove();
        for(var pom : gradovi){
            System.out.println(pom);
        }
    }
}