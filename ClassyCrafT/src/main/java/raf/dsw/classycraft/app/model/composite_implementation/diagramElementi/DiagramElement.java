package raf.dsw.classycraft.app.model.composite_implementation.diagramElementi;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;

public abstract class DiagramElement extends ClassyNode {

    public DiagramElement(String name, ClassyNode parent) {
        super(name, parent);
    }
}
