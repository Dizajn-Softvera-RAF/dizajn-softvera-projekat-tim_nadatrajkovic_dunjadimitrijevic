package raf.dsw.classycraft.app.model.sadrzajInterclass;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.InterclassVidljivost;

@JsonTypeName("atribut")
public class Atribut extends ClassContent{
    public Atribut(String naziv, InterclassVidljivost vidljivost, String tip) {
        super(naziv, vidljivost, tip);
    }

    @JsonCreator
    public Atribut(@JsonProperty("type") String type, @JsonProperty("naziv") String naziv, @JsonProperty("vidljivost") InterclassVidljivost vidljivost,
                  @JsonProperty("tip") String tip) {
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
