package raf.dsw.classycraft.app.tree;

import raf.dsw.classycraft.app.model.composite_implementation.ProjectExplorer;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.tree.view.ClassyTreeView;

public interface ClassyTree {
    ClassyTreeView generateTree(ProjectExplorer projectExplorer);
    void addChild(ClassyTreeItem parent);
    //todo dodati deleteChild
    ClassyTreeItem getSelectedNode();
}
