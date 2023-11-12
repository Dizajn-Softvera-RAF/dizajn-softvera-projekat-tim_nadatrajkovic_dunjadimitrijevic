package raf.dsw.classycraft.app.tree.factoryNodes;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_implementation.Package;

public class PackageNodeFactory extends AbstractNodeFactory {


    @Override
    public ClassyNode createNode(ClassyNode parent) {
        return new Package(parent);
    }
}
