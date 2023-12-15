package raf.dsw.classycraft.app.model.sadrzajInterclass;

import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.InterclassVidljivost;

import java.util.ArrayList;
import java.util.List;

public class Metoda extends ClassContent{

    private List<Atribut> parametriFunkcije;
    public Metoda(String naziv, InterclassVidljivost vidljivost, String tip) {
        super(naziv, vidljivost, tip);
        parametriFunkcije = new ArrayList<>(); //jel su ovo parametri?
    }
    public void addParametarFunkcije(Atribut p)
    {
        parametriFunkcije.add(p);
    }

    public List<Atribut> getParametriFunkcije() {
        return parametriFunkcije;
    }

    @Override
    public String toString() {
        StringBuilder pocetak = new StringBuilder(this.znakZaVidljivost()  + " " + naziv + "(");
        for (Atribut a: parametriFunkcije) {
            if(parametriFunkcije.indexOf(a) == 0){
                pocetak.append(a.getTip());
                pocetak.append(" ");
                pocetak.append(a.getNaziv());
            }
            else
            {
                pocetak.append(", " ); //a.getTip() + " " + a.getNaziv());
                pocetak.append(a.getTip());
                pocetak.append(" ");
                pocetak.append(a.getNaziv());
            }
        }
        pocetak.append(")");
        pocetak.append(":");
        pocetak.append(tip);
        return pocetak.toString();
    }
}
