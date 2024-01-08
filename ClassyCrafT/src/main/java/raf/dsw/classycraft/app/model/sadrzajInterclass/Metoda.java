package raf.dsw.classycraft.app.model.sadrzajInterclass;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.InterclassVidljivost;

import java.util.ArrayList;
import java.util.List;

@JsonTypeName("metoda")
public class Metoda extends ClassContent{

    private List<Atribut> parametriFunkcije;
    public Metoda(String naziv, InterclassVidljivost vidljivost, String tip) {
        super(naziv, vidljivost, tip);
        parametriFunkcije = new ArrayList<>();
    }
    @JsonCreator
    public Metoda(@JsonProperty("type") String type,@JsonProperty("naziv") String naziv,@JsonProperty("vidljivost") InterclassVidljivost vidljivost,
                  @JsonProperty("tip") String tip,@JsonProperty("parametriFunkcije") List<Atribut> parametriFunkcije) {
        super(naziv, vidljivost, tip);
        this.parametriFunkcije = parametriFunkcije;
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

    public void setParametriFunkcije(List<Atribut> parametriFunkcije) {
        this.parametriFunkcije = parametriFunkcije;
    }
}
