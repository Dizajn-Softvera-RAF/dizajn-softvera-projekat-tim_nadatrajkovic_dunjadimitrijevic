package raf.dsw.classycraft.app.model.composite_implementation.diagramElementi;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;

public class Kompozicija extends Connection {
    public Kompozicija(String name, ClassyNode parent, Interclass od, Interclass dokle) {
        super(name, parent, od, dokle);
    }
}
