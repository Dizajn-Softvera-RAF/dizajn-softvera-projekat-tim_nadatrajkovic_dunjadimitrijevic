package raf.dsw.classycraft.app.model.composite_implementation.diagramElementi;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;

public class Interfejs extends Interclass {
    public Interfejs(String name, ClassyNode parent) {
        super(name, parent);
    }

    public Interfejs(String name, ClassyNode parent, InterclassVidljivost vidljivost) {
        super(name, parent, vidljivost);
    }
}
