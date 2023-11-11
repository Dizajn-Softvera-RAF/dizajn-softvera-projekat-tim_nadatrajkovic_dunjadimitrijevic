package raf.dsw.classycraft.app.core;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_implementation.NodeType;
import raf.dsw.classycraft.app.model.composite_implementation.ProjectExplorer;
import raf.dsw.classycraft.app.tree.factoryNodes.AbstractNodeFactory;
import raf.dsw.classycraft.app.tree.factoryNodes.DiagramNodeFactory;
import raf.dsw.classycraft.app.tree.factoryNodes.PackageNodeFactory;
import raf.dsw.classycraft.app.tree.factoryNodes.ProjectNodeFactory;

public class ClassyRepositoryImplementation implements ClassyRepository{
    private ProjectExplorer root;

    public ClassyRepositoryImplementation() {
        this.root = new ProjectExplorer("Project Explorer");
        System.out.println("napravio project explorer " + root.getName() + " " + this);
    }

    @Override
    public ProjectExplorer getRoot() {
        return root;
    }

    @Override
    public ClassyNode createNode(ClassyNode parent, NodeType type)
    {
        //dal su nodovi samo dijagrami i paketi?
        AbstractNodeFactory factory = null;
        if(type == NodeType.DIAGRAM)
            factory = new DiagramNodeFactory();
        else if(type == NodeType.PACKAGE)
            factory = new PackageNodeFactory();
        else if (type==NodeType.PROJECT)
        {
            factory=new ProjectNodeFactory();
        }

        if(factory == null)
            return null;
        return factory.createNode(parent);
    }
}
