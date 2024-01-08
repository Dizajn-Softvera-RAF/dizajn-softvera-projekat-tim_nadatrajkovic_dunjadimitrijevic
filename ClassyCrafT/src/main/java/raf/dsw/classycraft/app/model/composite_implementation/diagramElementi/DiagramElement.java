package raf.dsw.classycraft.app.model.composite_implementation.diagramElementi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Interclass.class, name = "interclass"),
        @JsonSubTypes.Type(value = Connection.class, name = "connection"),
})
@JsonTypeName("diagramElement")

public abstract class DiagramElement extends ClassyNode {
    @JsonIgnore
    transient String neki;  //zacrveni se inace ako nema nikakvo polje

    // da li nam treba ime, tj da li name da prebacimo u interclass?

    public DiagramElement(String name, ClassyNode parent) {
        super(name, parent);
    }

}
