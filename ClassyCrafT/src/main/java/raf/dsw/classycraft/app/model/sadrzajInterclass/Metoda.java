package raf.dsw.classycraft.app.model.sadrzajInterclass;

import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.InterclassVidljivost;

import java.util.ArrayList;
import java.util.List;

public class Metoda extends ClassContent{

    private List<Atribut> clanoviFunkcije;
    public Metoda(String naziv, InterclassVidljivost vidljivost, String tip) {
        super(naziv, vidljivost, tip);
        clanoviFunkcije=new ArrayList<>();
    }

    public List<Atribut> getClanoviFunkcije() {
        return clanoviFunkcije;
    }

    @Override
    public String toString() {
        StringBuilder pocetak = new StringBuilder(this.znakZaVidljivost() + " " + tip + " " + naziv + "(");
        for (Atribut a:clanoviFunkcije) {
            pocetak.append(a.getNaziv()+", ");

        }
        pocetak.append(")");
        return pocetak.toString();
    }
}
