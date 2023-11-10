package raf.dsw.classycraft.app.model.composite_implementation;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNodeComposite;

public class Package extends ClassyNodeComposite {
    public Package(String name, ClassyNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild() {
        //TODO
    }

    @Override
    public void removeChild() {
        //TODO
    }
}
