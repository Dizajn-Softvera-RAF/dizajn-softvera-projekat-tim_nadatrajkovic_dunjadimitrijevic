package raf.dsw.classycraft.app.tree;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.composite_implementation.Diagram;
import raf.dsw.classycraft.app.model.composite_implementation.NodeType;
import raf.dsw.classycraft.app.model.composite_implementation.Project;
import raf.dsw.classycraft.app.model.composite_implementation.ProjectExplorer;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.tree.view.ClassyTreeView;

public interface ClassyTree {
    ClassyTreeView generateTree(ProjectExplorer projectExplorer);

    void addChild(ClassyTreeItem parent, NodeType type);

    void addDiagramElement(ClassyTreeItem parent, ClassyNode element);
    //todo dodati deleteChild
    void removeChild(ClassyTreeItem child);
    ClassyTreeItem getSelectedNode();

    ClassyTreeView getTreeView();

    ClassyTreeItem NadjiClassyTreePrekoClassyNode(ClassyNode node, ClassyTreeItem parent);

    ClassyTreeItem getRoot();

    public void loadProject(Project node);
    public void loadPattern(Diagram node);
}
