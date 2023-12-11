package raf.dsw.classycraft.app.model.sadrzajInterclass;

import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.InterclassVidljivost;

public class Atribut extends ClassContent{
    public Atribut(String naziv, InterclassVidljivost vidljivost, String tip) {
        super(naziv, vidljivost, tip);
    }


    public Atribut(String naziv, InterclassVidljivost vidljivost) {
        super(naziv, vidljivost);
    }

    @Override
    public String toString() {
        return znakZaVidljivost()+" " +naziv+":"+tip;
    }
}
