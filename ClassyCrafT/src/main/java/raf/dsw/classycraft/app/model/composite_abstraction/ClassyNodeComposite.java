package raf.dsw.classycraft.app.model.composite_abstraction;


import java.util.ArrayList;

public abstract class ClassyNodeComposite extends ClassyNode{
    private ArrayList<ClassyNode> children;

    public ArrayList<ClassyNode> getChildren() {
        return children;
    }

    public ClassyNodeComposite(String name, ClassyNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }

    @Override
    public void removeNode() {
        super.removeNode();
        for (ClassyNode node:children) {
            node.removeNode();
        }
    }



    public abstract void addChild(ClassyNode child);
    public abstract void removeChild(ClassyNode child);
}
