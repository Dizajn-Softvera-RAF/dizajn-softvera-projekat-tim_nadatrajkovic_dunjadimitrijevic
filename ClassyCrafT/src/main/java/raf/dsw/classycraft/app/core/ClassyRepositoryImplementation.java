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

    private NodeType selectedPackageChild;

    public NodeType getSelectedPackageChild() {
        if(selectedPackageChild == null)
        {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("nije izabran tip deteta paketa", MessageType.ERROR);
            return null;
        }
        else
        {
            return selectedPackageChild;
        }
    }

    public void setSelectedPackageChild(NodeType selectedPackageChild) {
        this.selectedPackageChild = selectedPackageChild;
        System.out.println("setovao type");
    }

    public ClassyRepositoryImplementation() {
        this.root = new ProjectExplorer("Project Explorer");
        System.out.println("napravio project explorer " + root.getName() + " " + this);
    }

    @Override
    public ProjectExplorer getRoot() {
        return root;
    }

    @Override
    public ClassyNode createNode(ClassyNode parent)
    {
        AbstractNodeFactory factory = null;
        if(parent instanceof ProjectExplorer)
            factory = new ProjectNodeFactory();
        else if(parent instanceof Project)
            factory = new PackageNodeFactory();
        else if (parent instanceof Package)
        {
            //factory=new DiagramNodeFactory();

            if(selectedPackageChild != null)
            {
                if(selectedPackageChild == NodeType.DIAGRAM) {
                    factory = new DiagramNodeFactory();//todo moze da bira izmedju Diagram i Package

                }
                else if(selectedPackageChild == NodeType.PACKAGE)
                    factory = new PackageNodeFactory();
                else
                    ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("nije izabran tip za dete paketa", MessageType.ERROR);
            }
            else{
                //throw new RuntimeException("nije izabran paket ili diagram");
            }
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
        ClassyNode noviNode=factory.createNode(parent);
        //if(selectedPackageChild == NodeType.DIAGRAM && parent instanceof Package)
        //    parent.notifySubscribers(noviNode);

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
