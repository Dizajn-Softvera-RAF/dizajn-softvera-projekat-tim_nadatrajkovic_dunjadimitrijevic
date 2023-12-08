package raf.dsw.classycraft.app.model.composite_implementation.diagramElementi;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;

public class Klasa extends Interclass {
    public Klasa(String name, ClassyNode parent) {
        super(name, parent);
    }

    public Klasa(String name, ClassyNode parent, InterclassVidljivost vidljivost) {
        super(name, parent, vidljivost);
    }


}
