package raf.dsw.classycraft.app.tree.factoryNodes;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;

public abstract class AbstractNodeFactory {

    public abstract ClassyNode createNode(ClassyNode parent);
}
