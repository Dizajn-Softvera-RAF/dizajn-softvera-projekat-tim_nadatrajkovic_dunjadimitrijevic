package raf.dsw.classycraft.app.core;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_implementation.NodeType;
import raf.dsw.classycraft.app.model.composite_implementation.Package;
import raf.dsw.classycraft.app.model.composite_implementation.Project;
import raf.dsw.classycraft.app.model.composite_implementation.ProjectExplorer;
import raf.dsw.classycraft.app.model.message.MessageType;
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
        AbstractNodeFactory factory = null;
        if(type == null)
        {
            if(parent instanceof ProjectExplorer)
                factory = new ProjectNodeFactory();
            else if(parent instanceof Project)
                factory = new PackageNodeFactory();
        }
        else
        {
            if(parent instanceof Package)
            {
                if(type == NodeType.DIAGRAM)
                {
                    factory = new DiagramNodeFactory();//todo moze da bira izmedju Diagram i Package
                }
                else if(type == NodeType.PACKAGE)
                {
                    factory = new PackageNodeFactory();
                }
                else
                {
                    ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("nije izabran tip za dete paketa", MessageType.ERROR);
                }
            }
        }
        if(factory == null)
            return null;
        ClassyNode noviNode=factory.createNode(parent);
        return noviNode;
    }

    @Override
    public void removeNode(ClassyNode node) {
        if(node instanceof ProjectExplorer)
        {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("ne sme da se brise project explorer", MessageType.ERROR);
            return;
        }
        node.removeNode();
    }
}
