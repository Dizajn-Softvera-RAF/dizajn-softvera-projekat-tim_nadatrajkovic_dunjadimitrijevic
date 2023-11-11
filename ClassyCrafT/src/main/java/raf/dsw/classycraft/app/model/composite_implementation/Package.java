package raf.dsw.classycraft.app.model.composite_implementation;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNodeComposite;

public class Package extends ClassyNodeComposite {
    public static int brojacPaketa=1;
    public Package(String name, ClassyNode parent) {
        super(name, parent);
    }

    public Package(ClassyNode parent)
    {
        super("package"+ brojacPaketa,parent);
        brojacPaketa++;
    }

    @Override
    public void addChild(ClassyNode child) {
        //TODO
    }

    @Override
    public void removeChild() {
        //TODO
    }
}
