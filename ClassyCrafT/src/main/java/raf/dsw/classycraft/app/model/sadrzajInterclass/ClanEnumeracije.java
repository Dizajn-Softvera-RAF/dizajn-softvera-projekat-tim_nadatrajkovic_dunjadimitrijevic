package raf.dsw.classycraft.app.model.sadrzajInterclass;

import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.InterclassVidljivost;

public class ClanEnumeracije extends ClassContent{
    public ClanEnumeracije(String naziv) {
        super(naziv, InterclassVidljivost.PUBLIC, "int");
    }

    @Override
    public String toString() {
        return naziv;
    }
}
