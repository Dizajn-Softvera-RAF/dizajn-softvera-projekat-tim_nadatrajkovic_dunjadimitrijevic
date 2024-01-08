package raf.dsw.classycraft.app.model.composite_implementation.diagramElementi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;

import java.awt.*;

@JsonTypeName("agregacija")
public class Agregacija extends Connection {
    public Agregacija(String name, ClassyNode parent, Interclass odInterclass, Interclass doInterclass, Point odTacka, Point doTacka) {
        super(name+brojacVeza, parent, odInterclass, doInterclass, odTacka, doTacka);
        brojacVeza++;
    }

    @JsonCreator
    public Agregacija(@JsonProperty("type") String type, @JsonProperty("name") String name, @JsonProperty("odTacka") Point odTacka, @JsonProperty("doTacka") Point doTacka,
                      @JsonProperty("interclassOd") Interclass odakle, @JsonProperty("interclassDo") Interclass dokle) {
        super(name, null, odakle,dokle,odTacka,doTacka);

    }
}
