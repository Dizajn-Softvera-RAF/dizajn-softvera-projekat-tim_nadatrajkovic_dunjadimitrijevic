package raf.dsw.classycraft.app.model.sadrzajInterclass;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.InterclassVidljivost;

@JsonTypeName("classContent")
public class ClanEnumeracije extends ClassContent{
    public ClanEnumeracije(String naziv) {
        super(naziv, InterclassVidljivost.PUBLIC, "int");
    }

    @JsonCreator
    public ClanEnumeracije(@JsonProperty("type") String type, @JsonProperty("naziv") String naziv, @JsonProperty("vidljivost") InterclassVidljivost vidljivost,
                  @JsonProperty("tip") String tip) {
        super(naziv, vidljivost, tip);
    }

    @Override
    public String toString() {
        return naziv;
    }
}
