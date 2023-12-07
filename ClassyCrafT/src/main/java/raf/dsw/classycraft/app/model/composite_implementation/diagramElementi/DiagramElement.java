package raf.dsw.classycraft.app.model.composite_implementation.diagramElementi;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;

public abstract class DiagramElement extends ClassyNode {

    // da li nam treba ime, tj da li name da prebacimo u interclass?
    public DiagramElement(String name, ClassyNode parent) {
        super(name, parent);
    }
}
