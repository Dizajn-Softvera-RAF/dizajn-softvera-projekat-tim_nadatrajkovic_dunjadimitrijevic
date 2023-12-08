package raf.dsw.classycraft.app.model.sadrzajInterclass;

import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.InterclassVidljivost;

public abstract class ClassContent {
    protected String naziv;
    protected InterclassVidljivost vidljivost;

    public ClassContent(String naziv, InterclassVidljivost vidljivost) {
        this.naziv = naziv;
        this.vidljivost = vidljivost;
    }

    @Override
    public String toString() {
        return vidljivost+" " + naziv;
    }
}
