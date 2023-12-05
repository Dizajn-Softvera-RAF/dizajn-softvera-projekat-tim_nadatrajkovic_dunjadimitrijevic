package raf.dsw.classycraft.app.model.composite_implementation.diagramElementi;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;

public abstract class Connection extends DiagramElement {
    Interclass InterclassOd;
    Interclass InterclassDo;
    public Connection(String name, ClassyNode parent, Interclass od, Interclass dokle) {
        super(name, parent);
        InterclassOd=od;
        InterclassDo=dokle;
    }
}
