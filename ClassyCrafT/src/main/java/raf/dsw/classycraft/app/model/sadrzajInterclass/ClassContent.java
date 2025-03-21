package raf.dsw.classycraft.app.model.sadrzajInterclass;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.InterclassVidljivost;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Atribut.class, name = "atribut"),
        @JsonSubTypes.Type(value = ClanEnumeracije.class, name = "clanEnumeracije"),
        @JsonSubTypes.Type(value = Metoda.class, name = "metoda"),
})
public abstract class ClassContent {
    protected String naziv;
    protected InterclassVidljivost vidljivost;
    protected String tip;

    public ClassContent(String naziv, InterclassVidljivost vidljivost) {
        this.naziv = naziv;
        this.vidljivost = vidljivost;
    }

    public ClassContent(String naziv, InterclassVidljivost vidljivost, String tip) {
        this.naziv = naziv;
        this.vidljivost = vidljivost;
        this.tip = tip;
    }

    @Override
    public String toString() {
        return naziv.toLowerCase()+" " + naziv; // cemu ovo toLowerCase()?
    }

    public String znakZaVidljivost()
    {
        if(vidljivost==InterclassVidljivost.PRIVATE)
            return "-";
        if(vidljivost==InterclassVidljivost.PUBLIC)
            return "+";
        else
            return "~";
    }


    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public InterclassVidljivost getVidljivost() {
        return vidljivost;
    }

    public void setVidljivost(InterclassVidljivost vidljivost) {
        this.vidljivost = vidljivost;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }


}
