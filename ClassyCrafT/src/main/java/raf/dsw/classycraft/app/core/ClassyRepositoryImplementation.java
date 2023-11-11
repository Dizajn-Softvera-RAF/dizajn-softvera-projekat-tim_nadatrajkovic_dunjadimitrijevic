package raf.dsw.classycraft.app.core;

import com.sun.tools.javac.Main;
import org.w3c.dom.Node;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_implementation.NodeType;
import raf.dsw.classycraft.app.model.composite_implementation.Project;
import raf.dsw.classycraft.app.model.composite_implementation.Package;
import raf.dsw.classycraft.app.model.composite_implementation.ProjectExplorer;
import raf.dsw.classycraft.app.model.message.MessageType;
import raf.dsw.classycraft.app.tree.factoryNodes.AbstractNodeFactory;
import raf.dsw.classycraft.app.tree.factoryNodes.DiagramNodeFactory;
import raf.dsw.classycraft.app.tree.factoryNodes.PackageNodeFactory;
import raf.dsw.classycraft.app.tree.factoryNodes.ProjectNodeFactory;
import raf.dsw.classycraft.app.view.MainFrame;

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
        if(parent instanceof ProjectExplorer)
            factory = new ProjectNodeFactory();
        else if(parent instanceof Project)
            factory = new PackageNodeFactory();
        else if (parent instanceof Package)
        {
            factory=new DiagramNodeFactory();
//            if(type == NodeType.DIAGRAM)
//                factory=new DiagramNodeFactory();//todo moze da bira izmedju Diagram i Package
//            else if(type == NodeType.PACKAGE)
//                factory = new PackageNodeFactory();
//            else
//                ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("nije izabran tip za dete paketa", MessageType.ERROR);
        }
//        if(type == NodeType.DIAGRAM)
//            factory = new DiagramNodeFactory();
//        else if(type == NodeType.PACKAGE)
//            factory = new PackageNodeFactory();
//        else if (type==NodeType.PROJECT)
//        {
//            factory=new ProjectNodeFactory();
//        }

        if(factory == null)
            return null;
        return factory.createNode(parent);
    }
}
