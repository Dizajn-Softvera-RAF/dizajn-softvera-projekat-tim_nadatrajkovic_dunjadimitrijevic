package raf.dsw.classycraft.app.model.composite_implementation;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;

//a neki static brojac za default ime

public class Diagram extends ClassyNode {
    public static int brojacDijagrama=1;
    public Diagram(String name, ClassyNode parent) {
        super(name, parent);
    }

    public Diagram(ClassyNode parent) {
        super("dijagram"+brojacDijagrama,parent);
        brojacDijagrama++;
    }
}
