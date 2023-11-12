package raf.dsw.classycraft.app.tree.model;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public class ClassyTreeItem extends DefaultMutableTreeNode {
    private ClassyNode classyNode;

    public ClassyTreeItem(ClassyNode nodeModel) {
        this.classyNode = nodeModel;
    }

    @Override
    public String toString() {
        return classyNode.getName();
    }

    public void setName(String name) {
        this.classyNode.setName(name);
    }

    public ClassyNode getClassyNode() {
        return classyNode;
    }


    @Override
    public void removeFromParent() {
        super.removeFromParent();
        classyNode.removeNode();
    }


    @Override
    public void removeAllChildren() {
        super.removeAllChildren();
        classyNode.removeNode();
    }
}
